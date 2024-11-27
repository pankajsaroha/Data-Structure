package com.pankaj.java.geeks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Roster {

    List<Dish> menu = Arrays.asList(
            new Dish("paneer", true, 800, Dish.Type.OTHER),
            new Dish("burger", true, 700, Dish.Type.OTHER),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    Trader pankaj = new Trader("Pankaj", "Dehradun");
    Trader nupur = new Trader("Nupur", "Meerut");
    Trader rohit = new Trader("Rohit", "Gurgaon");
    Trader anmol = new Trader("Anmol", "Sundarnagar");
    Trader snehika = new Trader("Snehika", "Meerut");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(anmol, 2011, 300),
            new Transaction(pankaj, 2012, 1000),
            new Transaction(pankaj, 2011, 400),
            new Transaction(nupur, 2012, 710),
            new Transaction(nupur, 2012, 700),
            new Transaction(rohit, 2012, 950),
            new Transaction(snehika, 2011, 500)
    );

    public void get3DishNames () {
        List<String> threeHighCaloriesDishNames = menu
                .stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(threeHighCaloriesDishNames);
    }

    public void vegDishes () {
        menu.stream()
                .filter(Dish::isVegeterian)
                .map(Dish::getName)
                .forEach(System.out::println);
    }

    public void transactions8Questions () {
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Meerut".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2));

        System.out.println(transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Dehradun")));

        System.out.println(transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Meerut"))
                .map(Transaction::getValue)
                .reduce(Integer::sum).orElse(0));

        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max).orElse(0));

        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min).orElse(0));
    }



    public static void main(String[] args) {
        Roster rs = new Roster();
        /*rs.get3DishNames();
        rs.vegDishes();

        List<String> list = Arrays.asList("Pankaj", "Manish", "Shivam", "Vishu", "Prince");
        list.stream().flatMap(n -> Stream.of(n)).forEach(System.out::println);*/

        rs.transactions8Questions();
    }
}