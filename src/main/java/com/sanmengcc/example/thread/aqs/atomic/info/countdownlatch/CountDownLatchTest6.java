package com.sanmengcc.example.thread.aqs.atomic.info.countdownlatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassNameCountDownLatch4
 * @Description
 * @Author sanmengcc
 * @Date2020/7/10 22:28
 * @Version V1.0
 **/
public class CountDownLatchTest6 {

    static class Event{

        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    static class Table{
        String tableName;

        long sourceRecordCount = 10;

        long targetCount;

        String sourceColumnSchema = "<table name='a'><column name='coll' type='varchar2'></column></table>";

        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    public static List<Table> capture(Event event) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("T-" + event.id + "-" + i, i * 1000));
        }
        return list;
    }

    private static final Random random = new Random(System.currentTimeMillis());


    static class TrustSourceRecordCount implements Runnable {
        private Table table;
        private TaskBatch taskBatch;
        public TrustSourceRecordCount(Table table,TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.sourceRecordCount = table.sourceRecordCount;
            taskBatch.down(table);
        }
    }

    static class TrustSourceColumnCount implements Runnable {
        private Table table;

        private TaskBatch taskBatch;
        public TrustSourceColumnCount(Table table,TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.targetCount;
            taskBatch.down(table);
        }
    }

    static interface Watcher {
        void down(Table table);
    }

    static class TaskBatch implements Watcher {

        private CountDownLatch latch;

        private TaskGroup taskGroup;

        public TaskBatch(int size,TaskGroup taskGroup) {
            this.taskGroup = taskGroup;
            this.latch = new CountDownLatch(size);
        }

        @Override
        public void down(Table table) {
            latch.countDown();
            if (latch.getCount() == 0) {
               // System.out.println("The table :" + table.tableName + " finish down. table:" + table);
                taskGroup.down(table);
            }
        }
    }

    static class TaskGroup implements Watcher {

        private CountDownLatch latch;

        private Event event;

        public TaskGroup(int size,Event event) {
            this.event = event;
            this.latch = new CountDownLatch(size);
        }

        @Override
        public void down(Table table) {
            latch.countDown();
            if (latch.getCount() == 0) {
                System.out.println("The event :" + event.id + " finish down.");
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Event> events = Arrays.asList(new Event(1), new Event(2));
        events.forEach(e->{
            List<Table> tables = capture(e);
            TaskGroup taskGroup = new TaskGroup(tables.size(), e);
            tables.forEach(table->{
                TaskBatch taskBatch = new TaskBatch(2,taskGroup);
                TrustSourceRecordCount trustSourceRecordCount = new TrustSourceRecordCount(table,taskBatch);
                TrustSourceColumnCount trustSourceColumnCount = new TrustSourceColumnCount(table, taskBatch);
                executorService.execute(trustSourceRecordCount);
                executorService.execute(trustSourceColumnCount);
            });
        });
    }

}

