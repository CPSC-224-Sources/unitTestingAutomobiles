package edu.gonzaga.AutoTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {

    @Test
    void testAutomobileTypeName() {
        Truck truck = new Truck();
        String expected = "Truck";

        String actual = truck.getTypeName();

        assertEquals(expected, actual);
    }

    // Tests
    // Velocity (at start == 0)
    // Velocity (after accelerating)
    // Velocity (after accelerating and decelerating)
    // Num passengers
    // Add some passengers
    // Is a given passenger in the Truck?
    // Add some fuel, is it correct?
    // Drive for a bit, how far did you go?
    
}
