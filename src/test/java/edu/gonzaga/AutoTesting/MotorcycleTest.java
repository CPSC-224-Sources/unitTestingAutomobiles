package edu.gonzaga.AutoTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MotorcycleTest {

    private Motorcycle motorcycle;

    @BeforeEach
    void setUp() {
        motorcycle = new Motorcycle(); // Motorcycle has 1 seat, 200 kg, max velocity 65 mps
    }

    @Test
    void testInitialValues() {
        // Ensure that the motorcycle is initialized correctly
        assertEquals(1, motorcycle.seats);
        assertEquals(200, motorcycle.mass_kg);
        assertEquals(65, motorcycle.maxVelocity_mps);
        assertFalse(motorcycle.isRunning);
        assertEquals(0, motorcycle.getVelocity());
    }

    @Test
    void testStartMotorcycle() {
        motorcycle.addFuel(5.0);
        motorcycle.start();
        assertTrue(motorcycle.isRunning);
    }

    @Test
    void testAccelerateMotorcycle() {
        motorcycle.addFuel(5.0);
        motorcycle.start();
        motorcycle.accelerate_mps(30); // Accelerate by 30 mps
        assertEquals(30, motorcycle.getVelocity());

        motorcycle.accelerate_mps(40); // This should cap the velocity at maxVelocity_mps
        assertEquals(65, motorcycle.getVelocity());
    }

    @Test
    void testStopMotorcycle() {
        motorcycle.addFuel(5.0);
        motorcycle.start();
        motorcycle.accelerate_mps(50);
        motorcycle.stop();
        assertEquals(0, motorcycle.getVelocity());
        assertFalse(motorcycle.isRunning);
    }

    @Test
    void testFuelConsumptionDuringTick() {
        // Arrange: Add a rider and fuel to the motorcycle
        motorcycle.addFuel(10.0);  // Add 10 liters of fuel
        motorcycle.addRider(new Person("Alice", 60)); // Add a passenger with 60 kg mass
        motorcycle.start();
        motorcycle.accelerate_mps(10); // Accelerate to 10 mps

        // Act: Call tick for 1 second of driving
        motorcycle.tick(1.0);

        // Assert: Ensure that the motorcycle has moved and burned some fuel
        assertEquals(10, motorcycle.getVelocity()); // Still moving at 10 mps
        assertEquals(10, motorcycle.getPosition()); // Should have moved 10 meters (velocity * time)
        assertTrue(motorcycle.getFuel() < 10.0);    // Some fuel should have been consumed
    }

    @Test
    void testNoFuelMotorcycleStops() {
        // Arrange: Motorcycle starts with a small amount of fuel
        motorcycle.addFuel(0.1); // 0.1 liters of fuel
        motorcycle.start();
        motorcycle.accelerate_mps(10);

        // Act: Simulate some time passing
        motorcycle.tick(10.0); // 10 seconds of driving

        // Assert: The motorcycle should have run out of fuel and stopped
        assertEquals(0, motorcycle.getVelocity()); // Should have stopped
        assertFalse(motorcycle.isRunning);         // Engine should be off
        assertEquals(0, motorcycle.getFuel());     // Fuel should be 0
    }
}
