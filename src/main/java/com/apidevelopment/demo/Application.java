package com.apidevelopment.demo;

import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {


        System.out.println("Sa");

        Micronaut.run(Application.class, args);
    }
}