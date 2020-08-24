package com.gz.javastudy.jvm;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Spirit
 * @since 2019-02-28
 */
public class GcTest {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        List<byte[]> caches = new ArrayList<byte[]>();

        for (int i = 0; i < 11; i++){
            caches.add(new byte[3 * _1MB]);
        }


        caches.add(new byte[3 * _1MB]);

        caches.remove(0);
        caches.add(new byte[3 * _1MB]);


        for (int i = 0; i < 8; i++) {
            caches.remove(0);
        }
        caches.add(new byte[3 * _1MB]);


        for (int i = 0; i < 7; i++){
            caches.add(new byte[3 * _1MB]);
        }
    }
}