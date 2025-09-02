package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HEnumeration;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Test suite for the EnumerationAdapter class.
 *
 * <p>This test suite verifies the functionality of the EnumerationAdapter class,
 * which implements the HEnumeration interface to iterate over elements of a
 * VectorAdapter or SubListAdapter. The tests cover various scenarios including
 * normal iteration, edge cases, and error conditions.</p>
 *
 * <h2>Test Case Design</h2>
 * <p>The test cases are designed to validate the behavior of EnumerationAdapter
 * against the specifications of Java 1.4.2 Enumeration interface. The test design
 * includes categories for basic functionality, edge cases, error conditions,
 * and sublist-specific behavior.</p>
 *
 * <p>Categories:
 * <ul>
 *   <li>Basic functionality tests</li>
 *   <li>Empty collection tests</li>
 *   <li>Null element tests</li>
 *   <li>Duplicate element tests</li>
 *   <li>SubList enumeration tests</li>
 *   <li>Nested SubList enumeration tests</li>
 *   <li>Exception handling tests</li>
 * </ul>
 * </p>
 *
 * @author   Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see      EnumerationAdapter
 * @see      HEnumeration
 */
public class EnumerationAdapterTest
{
    /**
     * The main VectorAdapter instance used for testing.
     * By default contains: ["1", "2", "3", null, "5"]
     */
    private VectorAdapter vector;

    /**
     * A SubListAdapter instance created from the main vector.
     * By default represents a sublist from index 1 to 4 (exclusive)
     * of the main vector.
     */
    private SubListAdapter subList;

    /**
     * HEnumeration instance for iterating over the main vector.
     * Created from the vector.elements() method.
     */
    private HEnumeration enumeration;

    /**
     * HEnumeration instance for iterating over the sublist.
     * Created from the subList.elements() method.
     */
    private HEnumeration subEnumeration;

    /**
     * Sets up the testing environment before each test method execution.
     * Initializes the vector and sublist with test data and creates enumeration instances.
     */
    @Before
    public void setUp()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add(null);
        vector.add("5");

        enumeration = vector.elements();

        subList = (SubListAdapter) vector.subList(1, 4);
        subEnumeration = subList.elements();
    }

    /**
     * Cleans up test environment after each test method execution.
     * Sets all instance variables to null to use the garbage collection.
     */
    @After
    public void tearDown()
    {
        vector = null;
        subList = null;
        enumeration = null;
        subEnumeration = null;
    }

    @Test
    public void testHasMoreElementsAtTheBeginning()
    {
        assertTrue("Collection should have been created and filled properly", enumeration.hasMoreElements());
    }

    @Test
    public void testHasMoreElementsInTheMiddle()
    {
        enumeration.nextElement();
        enumeration.nextElement();

        assertTrue("Collection should have some more elements", enumeration.hasMoreElements());
    }

    @Test
    public void testHasMoreElementsAtTheEnd()
    {
        while(enumeration.hasMoreElements())
            enumeration.nextElement();

        assertFalse("hasMoreElements should return false after full emptying iteration",
            enumeration.hasMoreElements());
    }

    @Test
    public void testNextElementProperIteration()
    {
        assertEquals("First element should match", "1", enumeration.nextElement());
        assertEquals("Second element should match", "2", enumeration.nextElement());
        assertEquals("Third element should match", "3", enumeration.nextElement());
        assertNull("Fourth element should be null", enumeration.nextElement());
        assertEquals("Fifth element should match", "5", enumeration.nextElement());

        assertFalse("Colleciton should have no more elements", enumeration.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextElementThrowsException()
    {
        while(enumeration.hasMoreElements())
            enumeration.nextElement();

        enumeration.nextElement();
    }

    @Test
    public void testHasMoreElementsOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HEnumeration enumer = vec.elements();

        assertFalse("Empty collection should have no elements",
            enumer.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextElementThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        HEnumeration enumer = vec.elements();
        enumer.nextElement();
    }

    @Test
    public void testNextElementDuplicates()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("0");
        vec.add("0");

        HEnumeration enumer = vec.elements();

        assertEquals("First element should be '0'", "0", enumer.nextElement());
        assertEquals("Second element should be '0'", "0", enumer.nextElement());
        assertEquals("Third element should be '0'", "0", enumer.nextElement());

        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testNextElementNull()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        HEnumeration enumer = vec.elements();

        assertTrue("Should manage null elements", enumer.hasMoreElements());
        assertNull("First element should be null", enumer.nextElement());
        assertNull("Second element should be null", enumer.nextElement());
        assertNull("Third element should be null", enumer.nextElement());
        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testSubListHasMoreElementsAtTheBeginning()
    {
        assertTrue("SubList collection should have been created properly",
            subEnumeration.hasMoreElements());
    }

    @Test
    public void testSubListHasMoreElementsInTheMiddle()
    {
        subEnumeration.nextElement();
        subEnumeration.nextElement();

        assertTrue("Collection should have some more elements", subEnumeration.hasMoreElements());
    }

    @Test
    public void testSubListHasMoreElementsAtTheEnd()
    {
        while(subEnumeration.hasMoreElements())
            subEnumeration.nextElement();

        assertFalse("hasMoreElements should return false after full emptying iteration",
            subEnumeration.hasMoreElements());
    }

    @Test
    public void testSubListNextElementProperIteration()
    {
        assertEquals("First element of subList should match", "2", subEnumeration.nextElement());
        assertEquals("Second element of subList should match", "3", subEnumeration.nextElement());
        assertNull("Third element of subList should be null", subEnumeration.nextElement());

        assertFalse("Collection should have no more elements after iteration", subEnumeration.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextElementThrowsException()
    {
        while(subEnumeration.hasMoreElements())
            subEnumeration.nextElement();

        subEnumeration.nextElement();
    }

    @Test
    public void testSubListHasMoreElementsOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 1);
        HEnumeration subEnumer = sub.elements();

        assertFalse("Empty subList should have no elements", subEnumer.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextElementThrowsExceptionOnEmptyCollecion()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HEnumeration subEnumer = sub.elements();

        subEnumer.nextElement();
    }

    @Test
    public void testSubListNextElementNull()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);
        vec.add(null);

        SubListAdapter sub = (SubListAdapter) vec.subList(1, 4);
        HEnumeration subEnumer = sub.elements();

        assertTrue("Should have elements", subEnumer.hasMoreElements());
        assertNull("First element should be null", subEnumer.nextElement());
        assertNull("Second element should be null", subEnumer.nextElement());
        assertNull("Third element should be null", subEnumer.nextElement());
        assertFalse("Should have no more elements", subEnumer.hasMoreElements());
    }

    @Test
    public void testNestedSubListNextElementProperIteration()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HEnumeration subEnumer = subSub.elements();

        assertEquals("First element should be 3", "3", subEnumer.nextElement());
        assertNull("Second element should be null", subEnumer.nextElement());
        assertFalse("Should have no more elements", subEnumer.hasMoreElements());
    }

    @Test
    public void testNestedSubListHasMoreElementsEmpty()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HEnumeration subEnumer = subSub.elements();

        assertFalse("Empty sub-subList should have no elements", subEnumer.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNestedSubListNextElementThrowsExceptionOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HEnumeration subEnumer = subSub.elements();

        subEnumer.nextElement();
    }
}
