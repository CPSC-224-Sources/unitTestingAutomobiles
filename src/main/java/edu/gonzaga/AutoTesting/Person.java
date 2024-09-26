package edu.gonzaga.AutoTesting;

public class Person {
    private String name;
    private double mass_kg;

    public Person(String name, double mass_kg) {
        this.name = name;
        this.mass_kg = mass_kg;
    }

    public double getMass() {
        return mass_kg;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + mass_kg + " kg)";
    }
}