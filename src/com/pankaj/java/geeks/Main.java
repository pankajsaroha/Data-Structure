package com.pankaj.java.geeks;

public class Main {

    interface HelloWorld {
        public void greet();
        public void greetSomeone(String someone);
    }

    public void sayHello() {

        class EnglishGreeting implements HelloWorld {

            String world = "world";
            @Override
            public void greet() {
                greetSomeone(world);
            }

            @Override
            public void greetSomeone(String someone) {
                System.out.println("Hello " + someone);
            }
        }

        HelloWorld englishGreeting = new EnglishGreeting();

        HelloWorld frenchGreeting = new HelloWorld() {

            String frenchWorld = "frenchWorld";
            @Override
            public void greet() {
                greetSomeone(frenchWorld);
            }

            @Override
            public void greetSomeone(String someone) {
                System.out.println("Hello " + frenchWorld);
            }
        };

        HelloWorld spanishGreeting = new HelloWorld() {

            String spanishWorld = "spanishWorld";
            @Override
            public void greet() {
                greetSomeone(spanishWorld);
            }

            @Override
            public void greetSomeone(String someone) {
                System.out.println("Hello " + spanishWorld);
            }
        };
        englishGreeting.greet();
        frenchGreeting.greet();
        spanishGreeting.greet();
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.sayHello();
    }
}