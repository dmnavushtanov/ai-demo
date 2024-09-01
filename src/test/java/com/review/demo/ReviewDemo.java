package com.review.demo;

import java.util.ArrayList;
import java.util.List;

public class ReviewDemo {

    void someNotVeryWellWrittenCode() {
        var a = new int[]{1, 2, 3, 4, 5};
        var sum = 0;
        var events = new ArrayList<Integer>();

        for(int i = 0; i<a.length; i++) {
            if(a[i] % 2 == 0) {
                events.add(a[i]);
            }
        }
        for (int i =0; i<events.size(); i++) {
            sum += events.get(i);
        }
        System.out.println(sum);
    }
}
