package edu.gonzaga.AutoTesting;

import java.util.ArrayList;

public abstract class Automobile {
    protected double fuel_liters;
    protected double velocity_mps;
    protected int seats;
    protected double mass_kg;
    protected double maxVelocity_mps;
    protected boolean isRunning;
    protected double position_m;
    protected ArrayList<Person> passengers;
    protected String typeName;

    public Automobile(String typeName, int seats, double mass_kg, double maxVelocity_mps) {
        this.typeName = typeName;
        this.seats = seats;
        this.mass_kg = mass_kg;
        this.maxVelocity_mps = maxVelocity_mps;
        this.fuel_liters = 0;
        this.velocity_mps = 0;
        this.position_m = 0;
        this.isRunning = false;
        this.passengers = new ArrayList<>();
    }

    public void accelerate_mps(double value) {
        velocity_mps = Math.min(velocity_mps + value, maxVelocity_mps);
    }

    public void brake_mps(double value) {
        velocity_mps = Math.max(velocity_mps - value, 0);
    }

    public void addRider(Person person) {
        if (passengers.size() < seats) {
            passengers.add(person);
        } else {
            System.out.println("No more room in the " + typeName);
        }
    }

    public void remRider(Person person) {
        passengers.remove(person);
    }

    public void stop() {
        velocity_mps = 0;
        isRunning = false;
    }

    public void start() {
        if (fuel_liters > 0) {
            isRunning = true;
        } else {
            System.out.println("No fuel to start the " + typeName);
        }
    }

    public void addFuel(double liters) {
        fuel_liters += liters;
    }

    public void tick(double seconds) {
        if (!isRunning || fuel_liters <= 0) {
            stop();
            return;
        }

        double distance_m = velocity_mps * seconds;
        position_m += distance_m;

        // Calculate fuel consumption based on mass and distance
        double fuelConsumed = distance_m / (calcKilometersPerLiter() * 1000); // 1 kilometer = 1000 meters
        fuel_liters = Math.max(fuel_liters - fuelConsumed, 0);

        if (fuel_liters <= 0) {
            stop();
            System.out.println(typeName + " has run out of fuel.");
        }
    }

    public double calcKilometersPerLiter() {
        // Basic formula: the heavier the vehicle, the less efficient
        double totalMass = mass_kg;
        for( Person currPerson : passengers ) {
            mass_kg += currPerson.getMass();
        }
        return 100.0 / totalMass; // Arbitrary formula for simplicity
    }

    public int getNumPassengers() {
        return passengers.size();
    }

    public double getVelocity() {
        return velocity_mps;
    }

    public double getPosition() {
        return position_m;
    }

    public double getFuel() {
        return fuel_liters;
    }

    public double getKPL() {
        return calcKilometersPerLiter();
    }

    public boolean containsPassenger(String name) {
        for (Person passenger : passengers) {
            if (passenger.getName().equalsIgnoreCase(name)) {
                return true; // Found a matching passenger
            }
        }
        return false; // No matching passenger found
    }

    public String getTypeName() {
        return this.typeName;
    }

    @Override
    public String toString() {
        return String.format("%s [Passengers: %d, Velocity: %.2f mps, Position: %.2f m, Fuel: %.2f L]",
                typeName, getNumPassengers(), velocity_mps, position_m, fuel_liters);
    }
}
