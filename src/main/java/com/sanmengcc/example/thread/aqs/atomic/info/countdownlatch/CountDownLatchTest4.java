package com.sanmengcc.example.thread.aqs.atomic.info.countdownlatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassNameCountDownLatch4
 * @Description
 * @Author sanmengcc
 * @Date2020/7/10 22:28
 * @Version V1.0
 **/
public class CountDownLatchTest4 {

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

        public TrustSourceRecordCount(Table table) {
            this.table = table;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.sourceRecordCount = table.sourceRecordCount;
            System.out.println("The Table :" + table.tableName + " target record capture down.");
        }
    }

    static class TrustSourceColumnCount implements Runnable {
        private Table table;

        public TrustSourceColumnCount(Table table) {
            this.table = table;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.targetCount;
            System.out.println("The Table :" + table.tableName + " target columns capture down.");
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Event> events = Arrays.asList(new Event(1), new Event(2));
        events.forEach(e->{
            List<Table> tables = capture(e);
            tables.forEach(table->{
                TrustSourceRecordCount trustSourceRecordCount = new TrustSourceRecordCount(table);
                TrustSourceColumnCount trustSourceColumnCount = new TrustSourceColumnCount(table);
                executorService.execute(trustSourceRecordCount);
                executorService.execute(trustSourceColumnCount);
            });
        });
    }

}

