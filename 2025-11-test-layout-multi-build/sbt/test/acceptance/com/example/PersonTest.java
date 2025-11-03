package com.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {
    private Person person;

    @Before
    public void setUp() {
        person = new Person("John", "Doe", 30);
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", person.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", person.getLastName());
    }

    @Test
    public void testGetAge() {
        assertEquals(30, person.getAge());
    }

    @Test
    public void testGetFullName() {
        assertEquals("John Doe", person.getFullName());
    }

    @Test
    public void testSetFirstName() {
        person.setFirstName("Jane");
        assertEquals("Jane", person.getFirstName());
    }

    @Test
    public void testSetLastName() {
        person.setLastName("Smith");
        assertEquals("Smith", person.getLastName());
    }

    @Test
    public void testSetAge() {
        person.setAge(25);
        assertEquals(25, person.getAge());
    }

    @Test
    public void testDefaultConstructor() {
        Person emptyPerson = new Person();
        assertNull(emptyPerson.getFirstName());
        assertNull(emptyPerson.getLastName());
        assertEquals(0, emptyPerson.getAge());
    }

    @Test
    public void testEquals() {
        Person person1 = new Person("John", "Doe", 30);
        Person person2 = new Person("John", "Doe", 30);
        Person person3 = new Person("Jane", "Doe", 30);

        assertEquals(person1, person2);
        assertNotEquals(person1, person3);
    }

    @Test
    public void testHashCode() {
        Person person1 = new Person("John", "Doe", 30);
        Person person2 = new Person("John", "Doe", 30);

        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Person{firstName='John', lastName='Doe', age=30}";
        assertEquals(expected, person.toString());
    }
}
