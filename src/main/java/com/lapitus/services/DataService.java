package com.lapitus.services;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lapitus.objects.Person;
import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataService {

    public static List<Person> getPersonList() throws IOException {
        InputStream inputStream = Resources.getResource("person_data.json").openStream();
        String json = IOUtils.toString(inputStream);
        return new Gson().fromJson(json, new TypeToken<List<Person>>(){}.getType());
    }

    public static void printString() {
        System.out.println("Print this static string");
    }

    public static int compareId(Person p1, Person p2) {
        Long id = p2.getId();
        return id.compareTo(p1.getId());
    }

    public int instanceMethodCompareId(Person p1, Person p2) {
        Long id = p2.getId();
        return id.compareTo(p1.getId());
    }
}
