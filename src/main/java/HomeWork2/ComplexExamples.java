package main.java.HomeWork2;

import java.util.*;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{

            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    public static void main(String[] args) {


        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        // Task 1

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        Map<String, Long> resultPersonMap = Arrays.stream(RAW_DATA)
                .filter(Objects::nonNull)
                .filter(person -> person.getName() != null)
                .distinct()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.groupingBy(Person::getName, TreeMap::new, Collectors.counting()));

        resultPersonMap.forEach((key, value) -> System.out.println("Key: " + key + "\n" + "Value: " + value));

        // Task 2

        int[] array = new int[]{3, 4, 2, 7};
        int requiredSum = 10;

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Pair of numbers that gives sum - " + requiredSum + ":");
        System.out.println();

        printPairOfNumbersThatGivesRequiredSum(array, requiredSum);

        // Task 3

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Implementation of the fuzzy search function");
        System.out.println();

        List<String> searchStrings = new ArrayList<>();
        List<String> givenStrings = new ArrayList<>();

        searchStrings.add("car");
        searchStrings.add("cwhl");
        searchStrings.add("cwhee");
        searchStrings.add("cartwheel");
        searchStrings.add("cwheeel");
        searchStrings.add("lw");

        givenStrings.add("ca6$$#_rtwheel");
        givenStrings.add("cartwheel");
        givenStrings.add("cartwheel");
        givenStrings.add("cartwheel");
        givenStrings.add("cartwheel");
        givenStrings.add("cartwheel");

        for (int i = 0; i < searchStrings.size(); i++) {
            System.out.println("Searching word: " + searchStrings.get(i));
            System.out.println("Given string: " + givenStrings.get(i));
            System.out.println("Result of fuzzy search function: " + fuzzySearch(searchStrings.get(i), givenStrings.get(i)));
            System.out.println("-------------------------------------");
        }
    }

    public static void printPairOfNumbersThatGivesRequiredSum(int[] array, int requiredSum) {

        HashSet<Integer> s = new HashSet<>();

        for (int number : array) {
            int temp = requiredSum - number;
            if (s.contains(temp)) {
                System.out.println("[" + temp + ", " + number + "]");
                break;
            } else {
                s.add(number);
            }
        }
    }

    public static boolean fuzzySearch(String searchString, String givenString) {

        if (searchString.length() > givenString.length())
            return false;

        for (int i = 0; i < givenString.length(); i++) {
            if (givenString.charAt(i) == searchString.charAt(0)) {
                searchString = searchString.substring(1);
                if (searchString.length() == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
