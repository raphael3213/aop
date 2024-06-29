package com.learning.aop.service;

import org.springframework.stereotype.Component;

@Component
public class GreetService {


    public void greetPeople(){
        System.out.println("Greet People is called");
    }
}
