package com.sanmengcc.example.thread.aqs.atomic;

/**
 * @ClassNameUnsafeClass
 * @Description
 * @Author sanmengcc
 * @Date2020/7/10 2:26
 * @Version V1.0
 **/
public class UnsafeClass{
    private int i = 0;

    public UnsafeClass() {
        this.i = 100;
    }

    public int getI() {
        return i;
    }
}
