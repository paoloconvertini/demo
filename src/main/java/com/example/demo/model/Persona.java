package com.example.demo.model;

public class Persona {

    private String Name;

    @Override
    public String toString() {
        return "Persona{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
