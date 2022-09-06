package main.java.HomeWork2;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

        List<Person> personList = Arrays.asList(RAW_DATA);

        Map<String, Long> resultPersonMap = personList.stream()
                .distinct()
                .collect(Collectors.collectingAndThen(groupingBy(Person::getName, Collectors.counting()),
                        map -> map.entrySet()
                                .stream()
                                .sorted(Map.Entry.comparingByKey())
                                .collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                        (e1, e2) -> e1, TreeMap::new)))));

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

        int[] resultArray = new int[2];
        boolean pairOfNumbersExists = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == requiredSum) {
                    resultArray[0] = array[i];
                    resultArray[1] = array[j];
                    pairOfNumbersExists = true;
                    break;
                }
            }
        }
        String result = (pairOfNumbersExists ?
                Arrays.toString(array) + ", " + requiredSum + " -> " + Arrays.toString(resultArray) :
                "There are no numbers in the array whose sum is equal to " + requiredSum);

        System.out.println(result);
    }

    private static boolean fuzzySearch(String searchString, String givenString) {

        int countPointer = -1;
        int matchedChars = 0;

        for (int i = 0; i < searchString.length(); i++) {
            for (int j = 0; j < givenString.length(); j++) {
                if (searchString.charAt(i) == givenString.charAt(j) && j > countPointer) {
                    countPointer = j;
                    matchedChars++;
                    break;
                }
            }
        }
        return matchedChars == searchString.length();
    }
}
