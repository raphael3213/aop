package com.learning.aop;


import com.learning.aop.custom_annotation.Init;
import com.learning.aop.custom_annotation.JsonElement;
import com.learning.aop.custom_annotation.JsonSerializable;

@JsonSerializable
public class Person {

    @JsonElement(key = "first_name")
    private String firstName;

    @JsonElement(key = "last_name")
    private String lastName;


    @JsonElement(key = "age")
    private String age;

    public Person(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age  = age;
    }


    @Init
    private void initNames(){
        this.firstName = this.firstName.substring(0,1).toUpperCase() + this.firstName.substring(1);
        this.lastName = this.lastName.substring(0,1).toUpperCase() + this.lastName.substring(1);
    }
}
