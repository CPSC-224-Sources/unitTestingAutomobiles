package edu.gonzaga.AutoTesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPersonCreation() {
        // Arrange
        String name = "Alice";
        double mass_kg = 65.0;

        // Act
        Person person = new Person(name, mass_kg);

        // Assert
        assertEquals("Alice", person.getName());
        assertEquals(65.0, person.getMass());
    }

    @Test
    void testGetName() {
        // Arrange
        Person person = new Person("Bob", 70.5);

        // Act & Assert
        assertEquals("Bob", person.getName());
    }

    @Test
    void testGetMass() {
        // Arrange
        Person person = new Person("Charlie", 80.3);

        // Act & Assert
        assertEquals(80.3, person.getMass());
    }

    @Test
    void testToString() {
        // Arrange
        Person person = new Person("David", 75.2);

        // Act
        String result = person.toString();

        // Assert
        assertEquals("David (75.2 kg)", result);
    }
}
