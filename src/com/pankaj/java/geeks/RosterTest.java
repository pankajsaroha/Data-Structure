package com.pankaj.java.geeks;

import com.pankaj.java.geeks.Person;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RosterTest {

    interface CheckPerson {
        boolean test (Person p);
    }

    //Approach 1 :
    public static void printPersonsWithOlderAge(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    //Approach 2: More generalized method
    public static void printPersonWithAgeRange(List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (p.getAge() >= low && p.getAge() <= high) {
                p.printPerson();
            }
        }
    }

    //Approach 3: Search criteria code in local class
    //Approach 4: Search criteria code in Anonumous class
    //Approach 5: Search criteria code in Lambda expression
    public static void printPersons (List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if(tester.test(p)) {
                p.printPerson();
            }
        }
    }

    //Approach 6: Use Standard Functional Interface with Lambda expressions
    public static void printPersonsWithPredicate (List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if(tester.test(p)) {
                p.printPerson();
            }
        }
    }

    //Approach 7: Use Lambda Expression throughout your application
    public static void processPersons (List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    //Approach 7: second example
    public static void processPersonWithFunction (List<Person> roster, Predicate<Person> tester,
                                           Function<Person, String> mapper, Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    //Approach 8: Use Generics more Extensively
    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block
    ) {
        for (X p : source) {
            Y data = mapper.apply(p);
            block.accept(data);
        }
    }

    //Input Data :
    // Pankaj, 26, MALE, pk89651@gmail.com
    // Manish, 19, MALE, manishsaroha@gmail.com
    // Dushyant, 27, FEMALE, dushyantkumar121.dk@gmail.com
    public static void main(String args []) throws IOException {
        List<Person> roster = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Number of person ....");
        int n = in.nextInt();
        for(int i=0; i<n; i++) {
            roster = Person.createRoster();
        }
        in.close();

        //Approach 1
        System.out.println("Persons older than 20: ");
        printPersonsWithOlderAge(roster, 20);
        System.out.println();

        //Approach 2
        System.out.println("Persons with age between 14 and 20");
        printPersonWithAgeRange(roster, 14, 20);
        System.out.println();

        //Approach 3
        System.out.println("Person who are eligible for selective services");

        class checkPersonEligibleForSelectiveService implements CheckPerson {
            @Override public boolean test (Person p) {
                return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        }

        printPersons(roster, new checkPersonEligibleForSelectiveService());
        System.out.println();

        //Approach 4
        System.out.println("Person who are eligible for selective services (Anonymous class)");

        printPersons(roster, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.FEMALE
                        && p.getAge() > 26;
            }
        });

        System.out.println();

        //Approach 5
        System.out.println("Persons who are eligible for selective services (Lambda Expression)");

        printPersons(roster,
                (Person p) -> p.getGender() == Person.Sex.MALE
                            && p.getAge() <= 26
                            && p.getAge() >= 18);

        System.out.println();

        //Approach 6
        System.out.println("Person who are eligible for selective services (with Predicate parameters)");

        printPersonsWithPredicate(roster,
                        p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() <= 26
                        && p.getAge() >= 18);
        System.out.println();

        // Approach 7
        System.out.println("Persons who are eligible for selective services (with Predicate and Consumer params");

        processPersons(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() <= 26
                        && p.getAge() >= 18,
                p -> p.printPerson());

        System.out.println();

        // Approach 7: second example
        System.out.println("Persons who are eligible for selective services (with Predicate," +
                "Function and Consumer params)");

        processPersonWithFunction(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() <=26
                        && p.getAge() >= 18,
                p -> p.getEmail(),
                email -> System.out.println(email));

        System.out.println();

        //Approach 8
        System.out.println("Persons who are eligible for selective services (generic version");

        processElements(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() <=26
                        && p.getAge() >= 18,
                p -> p.getEmail(),
                email -> System.out.println(email));
    }
}
