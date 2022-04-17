package com.example.anime;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class hey {
    class User {
        String name;
        int age;

        User(String n, int a) {
            name = n;
            age = a;
        }
    }
    public static void main(String[] args) {
        Set<User> users = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        TreeSet<User> tree = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        });
    }
}
