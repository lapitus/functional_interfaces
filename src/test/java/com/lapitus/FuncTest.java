package com.lapitus;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.lapitus.interfaces.MultiArgInterface;
import com.lapitus.interfaces.MyFunctionalInterface;
import com.lapitus.objects.Person;
import com.lapitus.services.DataService;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class FuncTest {

    @Test
    public void test() throws IOException {
        List<Person> personList = DataService.getPersonList();
//        for (Person person: personList) {
//            System.out.println(person);
//        }
        Assertions.assertThat(personList).hasSize(1000);
    }

    @Test
    public void threadLambdas() {
//        Runnable r1 = new Runnable() {
//            public void run() {
//                System.out.println("Running thread r1");
//            }
//        };
//
//        Runnable r2 = new Runnable() {
//            public void run() {
//                System.out.println("Running thread r2");
//            }
//        };
//
//        new Thread(r1).start();
//        new Thread(r2).start();

        Runnable r1 = () -> System.out.println("Running thread r1");
        Runnable r2 = () -> System.out.println("Running thread r2");

        new Thread(r1).start();
        new Thread(r2).start();
    }

    @Test
    public void multiCommand() {
        Runnable r1 = () -> {
            System.out.println("Line 1");
            System.out.println("Line 2");
        };
        new Thread(r1).start();
    }

    @Test
    public void testInterface() {
        MyFunctionalInterface myFunctionalInterface = () -> System.out.println("Message from functional interface");
        myFunctionalInterface.printMessage();
    }

    @Test
    public void multiArgInterface() {
        MultiArgInterface multiArgInterface = (x, y) -> {
            int sum = x + y;
            System.out.println("Sum = " + sum);
        };
        multiArgInterface.sum(3, 10);
    }

    //Collection
    @Test
    public void collectionLambdas() throws IOException {
        List<Person> personList = DataService.getPersonList();
        //sort
//        Collections.sort(personList, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
//            }
//        });
//
//        for (Person person: personList) {
//            System.out.println(person.getFirstName());
//        }

        Comparator<Person> comparator = (p1, p2) -> p2.getFirstName().compareToIgnoreCase(p1.getFirstName());
        personList.sort(comparator);
        personList.forEach(person -> System.out.println(person.getFirstName()));
    }

    //predicate
    @Test
    public void testPredicate() throws IOException {
        List<Person> personList = DataService.getPersonList();
//        Predicate<Person> predicate = new Predicate<Person>() {
//            @Override
//            public boolean test(Person person) {
//                return person.getGender().equalsIgnoreCase("Male");
//            }
//        };
//
//        for (Person person: personList) {
//            if(predicate.test(person)) {
//                System.out.println(person.getFirstName() + " Gender " + person.getGender());
//            }
//        }
        Predicate<Person> predicate = p -> p.getGender().equalsIgnoreCase("Female");
        personList.forEach(person -> {
            if (predicate.test(person)) {
                System.out.println("Predicate name:"
                        + person.getFirstName() + " Gender: "
                        + person.getGender());
            }
        });
    }

    @Test
    public void referenceMethod() throws IOException {
        Thread thread = new Thread(DataService::printString);
        thread.start();

        List<Person> personList = DataService.getPersonList();
        personList.sort(DataService::compareId);
        personList.forEach(System.out::println);

    }

    @Test
    public void instanceMethod() throws IOException {
        List<Person> personList = DataService.getPersonList();
        DataService dataService = new DataService();
        personList.sort(dataService::instanceMethodCompareId);
        personList.forEach(System.out::println);
    }


}

