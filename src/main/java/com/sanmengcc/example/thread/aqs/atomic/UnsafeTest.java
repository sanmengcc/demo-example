package com.sanmengcc.example.thread.aqs.atomic;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassNameUnsafeTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/8 21:57
 * @Version V1.0
 **/
public class UnsafeTest {

    public static void main(String[] args) throws Exception {
        sizeOf(new Person());
    }

    /**
     * @Description unsafe获取字段偏移量
     * @Author  sanmengcc
     * @Date   2020/7/10 2:51
     */
    public static void sizeOf(Object object) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = getUnsafe();
        Set<Field> fieldSet = new HashSet<>();
        Class<?> aClass = object.getClass();
        while (aClass != Object.class) {
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                if ((field.getModifiers() & Modifier.STATIC )== 0) {
                    fieldSet.add(field);
                }
            }
            aClass = aClass.getSuperclass();
        }
        long maxOffSet = 0;
        for (Field field : fieldSet) {
            long offset = unsafe.objectFieldOffset(field);
            if (offset > maxOffSet) {
                maxOffSet = offset;
            }
        }
        System.out.println(((maxOffSet / 8) + 1) * 8);
    }

    /**
     * @Description 通过Unsafe加载类
     * @Author  sanmengcc
     * @Date   2020/7/10 2:43
     */
    public static void testUnsafeClassLoad() throws Exception{
        File classFile = new File("D:\\UnsafeClass.class");
        FileInputStream inputStream = new FileInputStream(classFile);
        byte[] bytes = new byte[(int) classFile.length()];
        inputStream.read(bytes);
        inputStream.close();
        //通过Unsafe加载
        Unsafe unsafe = getUnsafe();
        Class defineClass = unsafe.defineClass(null, bytes, 0, bytes.length, null, null);
        Method classMethod = defineClass.getMethod("getI");
        Object invoke = classMethod.invoke(defineClass.newInstance(), null);
        System.out.println(invoke);
    }

    /**
     * @Description 工作权限测试
     * @Author  sanmengc
     * @Date   2020/7/10 2:00
     */
    public static void testGuard() throws NoSuchFieldException, IllegalAccessException {
        Guard guard = new Guard();
        //肯定是无法工作的
        guard.work();

        //使用Unsafe
        Field field = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        Unsafe unsafe = getUnsafe();
        unsafe.putInt(guard, unsafe.objectFieldOffset(field), 42);
        //企图工作
        guard.work();
    }

    /**
     * @Description 测试unsafe对象实例化
     * @Author  sanmengcc
     * @Date   2020/7/10 1:51
     */
    public static void testSimple() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Unsafe unsafe = getUnsafe();
        //通过Unsafe创建对象
        Simple simple = (Simple) unsafe.allocateInstance(Simple.class);
        System.out.println("simple i = " + simple.getI());
        System.out.println("------------------------------------------");
        //直接创建对象
        System.out.println("simple i = " + new Simple().getI());
    }

    /**
     * @Description 反射获取unsafe
     * @Author  sanmengcc
     * @Date   2020/7/10 1:47
     */
    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return  (Unsafe) f.get(null);
    }
}



class Guard{
    private int ACCESS_ALLOWED = 1;

    //简单的权限校验
    private boolean allow() {
        return 42 == ACCESS_ALLOWED;
    }

    //满足条件进行工作
    public void work() {
        if (allow()) {
            System.out.println("I am do working.");
        }
    }
}

class Simple{

    private int i = 0;

    public Simple() {
        i = 1;
        System.out.println("simple init.");
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
