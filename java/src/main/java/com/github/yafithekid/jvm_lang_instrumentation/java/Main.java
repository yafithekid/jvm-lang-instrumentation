package com.github.yafithekid.jvm_lang_instrumentation.java;

public class Main {
    public static void main(String[] args){
        try {
            Thread.sleep(100);
            System.out.println("java sleep for 100 ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}