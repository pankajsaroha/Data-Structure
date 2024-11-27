package com.pankaj.java.geeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {

    public enum Sex {
        MALE, FEMALE
    }
    private String name;
    private int age;
    private Sex gender;
    private String email;

    Person (String name, int age, Sex gender, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getAge () {
        return this.age;
    }

    public void setAge (int age) {
        this.age = age;
    }

    public Sex getGender () {
        return this.gender;
    }

    public void setGender (Sex gender) {
        this.gender = gender;
    }

    public String getEmail () {
        return this.email;
    }

    public void setEmail () {
        this.email = email;
    }

    static List<Person> roster = new ArrayList<>();

    public static List<Person> createRoster () throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter details ..... ");
        String str[] = br.readLine().split(", ");
        System.out.println("Data : " + Arrays.toString(str));
        roster.add(new Person(str[0], Integer.parseInt(str[1]), Sex.valueOf(str[2]), str[3]));
        return roster;
    }

    public void printPerson() {
        System.out.println(this.name + " is " + this.gender + " whose age is " + this.age + " and the " +
                "email address is " + this.email);
    }
}
