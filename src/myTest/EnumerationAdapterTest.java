package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HEnumeration;
import myAdapter.EnumerationAdapter;

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
 * <h2>Testing Framework Information</h2>
 * <p>This test suite utilizes JUnit 4.13 testing framework with Hamcrest 1.3
 * matchers for assertions. These dependencies are located as follows:</p>
 * <ul>
 *   <li>JUnit 4.13: {@code lib/junit-4.13.jar}</li>
 *   <li>Hamcrest Core 1.3: {@code Matcher/hamcrest-core-1.3.jar}</li>
 * </ul>
 *
 * <p>Categories:</p>
 * <ul>
 *   <li>Basic functionality tests</li>
 *   <li>Empty collection tests</li>
 *   <li>Null element tests</li>
 *   <li>Duplicate element tests</li>
 *   <li>SubList enumeration tests</li>
 *   <li>Nested SubList enumeration tests</li>
 *   <li>Exception handling tests</li>
 * </ul>
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
     * Default constructor
     */
    public EnumerationAdapterTest()
    {}

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

    /**
     * Test method to verify the behavior of hasMoreElements() at the beginning of enumeration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns true when the enumeration is at the beginning
     * of a non-empty collection, indicating that there are elements available for iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of the enumeration. After setup, the enumeration
     * should be positioned at the start of the collection and should report that more elements are available.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test immediately checks if hasMoreElements() returns true, since the enumeration
     * hasn't started iterating yet.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HEnumeration is created from vector.elements()</p>
     * <p>- Enumeration is at the starting position (cursor = 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration state remains unchanged</p>
     * <p>- No elements have been consumed by the enumeration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return true, indicating there are elements available for iteration.</p>
     */
    @Test
    public void testHasMoreElementsAtTheBeginning()
    {
        assertTrue("Collection should have been created and filled properly", enumeration.hasMoreElements());
    }

    /**
     * Test method to verify the behavior of hasMoreElements() during middle of enumeration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns true when the enumeration is in the middle
     * of iterating through a collection, indicating that there are still more elements available.
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the intermediate state of the enumeration. After consuming
     * some elements, the enumeration should still report that more elements are available if the
     * end hasn't been reached.</p>
     *
     * <p><b>Test Description:</b>
     * <p>The test creates an enumeration and consumes the first two elements. It then checks if
     * hasMoreElements() returns true, indicating that there are still elements remaining for iteration.</p>
     *
     * <p><b>Pre-Condition:</b>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HEnumeration is created from vector.elements()</p>
     * <p>- Two elements have been consumed using nextElement()</p>
     *
     * <p><b>Post-Condition:</b>
     * <p>- Enumeration cursor has advanced to position 2</p>
     * <p>- Two elements have been consumed</p>
     *
     * <p><b>Expected Results:</b>
     * <p>hasMoreElements() should return true, indicating there are still elements available for iteration.</p>
     */
    @Test
    public void testHasMoreElementsInTheMiddle()
    {
        enumeration.nextElement();
        enumeration.nextElement();
        enumeration.nextElement();

        assertTrue("Collection should have some more elements", enumeration.hasMoreElements());
    }

    /**
     * Test method to verify the behavior of hasMoreElements() at the end of enumeration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns false when all elements in the collection
     * have been consumed, indicating that no more elements are available for iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the terminal state of the enumeration, after consuming all
     * elements, since no more elements are available.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an enumeration and iterates through all elements until hasMoreElements()
     * returns false. It then verifies that hasMoreElements() consistently returns false after
     * all elements have been consumed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HEnumeration is created from vector.elements()</p>
     * <p>- All elements have been consumed using nextElement()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor is at the end position (cursor = 5)</p>
     * <p>- All elements have been consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return false, indicating no more elements are available for iteration.</p>
     */
    @Test
    public void testHasMoreElementsAtTheEnd()
    {
        while(enumeration.hasMoreElements())
            enumeration.nextElement();

        assertFalse("hasMoreElements should return false after full emptying iteration",
            enumeration.hasMoreElements());
    }

    /**
     * Test method to verify proper iteration sequence using nextElement().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() returns elements in the correct order throughout
     * the whole iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the complete iteration sequence and
     * verify proper exhaustion of the enumeration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through all elements of the enumeration, verifying each element matches
     * the expected value. Finally, it verifies that hasMoreElements() returns false after all elements
     * have been consumed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HEnumeration is created from vector.elements()</p>
     * <p>- Enumeration is at the starting position</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor is at the end position (cursor = 5)</p>
     * <p>- All elements have been consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Elements should be returned in sequence: "1", "2", "3", null, "5"</p>
     * <p>hasMoreElements() should return false after the fifth element is consumed</p>
     */
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

    /**
     * Test method to verify that nextElement() throws NoSuchElementException when exhausted.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() throws NoSuchElementException
     * when called after complete consumption, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the exception handling behavior of the enumeration
     * when it's exhausted.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test first iterates through all elements until hasMoreElements() returns false.
     * Then attempts to call nextElement() again and expects a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HEnumeration is created from vector.elements()</p>
     * <p>- All elements have been consumed using nextElement()</p>
     * <p>- hasMoreElements() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration remains in exhausted state</p>
     * <p>- NoSuchElementException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextElement() should throw NoSuchElementException when called on an exhausted enumeration.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextElementThrowsException()
    {
        while(enumeration.hasMoreElements())
            enumeration.nextElement();

        enumeration.nextElement();
    }

    /**
     * Test method to verify the behavior of hasMoreElements() on an empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns false when the enumeration is created
     * from an empty collection, since no elements are available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>This ensures that the enumeration correctly handles collections with no elements.
     * The test is designed to validate the edge case of an empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test checks if hasMoreElements() returns false, validating that no elements
     * are available for iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     * <p>- HEnumeration is created from the empty vector.elements()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration state remains unchanged</p>
     * <p>- No elements are available for iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return false, indicating no elements are available for iteration.</p>
     */
    @Test
    public void testHasMoreElementsOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HEnumeration enumer = vec.elements();

        assertFalse("Empty collection should have no elements",
            enumer.hasMoreElements());
    }

    /**
     * Test method to verify that nextElement() throws NoSuchElementException on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() throws NoSuchElementException when called on an
     * enumeration created from an empty collection, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case
     * of an empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to call nextElement() and expects a NoSuchElementException to be thrown
     * since there are no elements to return.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     * <p>- HEnumeration is created from the empty vector.elements()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Enumeration remains in exhausted state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextElement() should throw NoSuchElementException when called on an empty collection.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextElementThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        HEnumeration enumer = vec.elements();
        enumer.nextElement();
    }

    /**
     * Test method to verify proper iteration with duplicate elements using nextElement().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() correctly handles collections containing duplicate
     * elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate enumeration behavior with duplicate values,
     * to ensure no other operation is performed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three string of "0". It then
     * iterates through the enumeration, verifying that each call to nextElement() returns
     * the same value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 identical elements: ["0", "0", "0"]</p>
     * <p>- HEnumeration is created from vector.elements()</p>
     * <p>- Enumeration is at the starting position</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor is at the end position</p>
     * <p>- All three elements have been consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>All three calls to nextElement() should return "0"</p>
     * <p>hasMoreElements() should return false after the third element is consumed</p>
     */
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

    /**
     * Test method to verify proper iteration with null elements using nextElement().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() correctly handles collections containing null
     * elements and properly returns them during iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate enumeration behavior with null values, without throwing exceptions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three null elements. It verifies that hasMoreElements()
     * returns true initially, then iterates through the enumeration, checking that each call to
     * nextElement() returns null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements: [null, null, null]</p>
     * <p>- HEnumeration is created from vector.elements()</p>
     * <p>- Enumeration is at the starting position</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor is at the end position</p>
     * <p>- All three null elements have been consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return true initially</p>
     * <p>All three calls to nextElement() should return null</p>
     * <p>hasMoreElements() should return false after the third element is consumed</p>
     */
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

    /**
     * Test method to verify the behavior of hasMoreElements() at the beginning of sublist enumeration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns true when the sublist enumeration is at the
     * beginning of a non-empty sublist, indicating that there are elements available for iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of a sublist enumeration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test immediately checks if hasMoreElements() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 (exclusive) of the main vector</p>
     * <p>- HEnumeration is created from subList.elements()</p>
     * <p>- Enumeration is at the starting position</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration state remains unchanged</p>
     * <p>- No elements have been consumed by the enumeration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return true, indicating there are elements available for iteration.</p>
     */
    @Test
    public void testSubListHasMoreElementsAtTheBeginning()
    {
        assertTrue("SubList collection should have been created properly",
            subEnumeration.hasMoreElements());
    }

    /**
     * Test method to verify the behavior of hasMoreElements() during middle of sublist enumeration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns true when the sublist enumeration is in the
     * middle of iterating through the sublist, indicating that there are still more elements available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the intermediate state of a sublist enumeration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test consumes the first two elements of the sublist. It then checks if hasMoreElements()
     * returns true, indicating that there is still elements to iterate.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- SubListAdapter contains 3 elements: ["2", "3", null]</p>
     * <p>- HEnumeration is created from subList.elements()</p>
     * <p>- Two elements have been consumed using nextElement()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor has advanced to position 2</p>
     * <p>- Two elements have been consumed</p>
     * <p>- One element remains</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return true, indicating there is still one element available for iteration.</p>
     */
    @Test
    public void testSubListHasMoreElementsInTheMiddle()
    {
        subEnumeration.nextElement();
        subEnumeration.nextElement();

        assertTrue("Collection should have some more elements", subEnumeration.hasMoreElements());
    }

    /**
     * Test method to verify the behavior of hasMoreElements() at the end of sublist enumeration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns false when all elements in the sublist
     * have been consumed, indicating that no more elements are available for iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the terminal state of a sublist enumeration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through all elements until hasMoreElements() returns false.
     * It then verifies that hasMoreElements() consistently returns false after all elements have been consumed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- SubListAdapter contains 3 elements: ["2", "3", null]</p>
     * <p>- HEnumeration is created from subList.elements()</p>
     * <p>- All elements have been consumed using nextElement()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor is at the end position</p>
     * <p>- All three elements have been consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return false, indicating no more elements are available for iteration.</p>
     */
    @Test
    public void testSubListHasMoreElementsAtTheEnd()
    {
        while(subEnumeration.hasMoreElements())
            subEnumeration.nextElement();

        assertFalse("hasMoreElements should return false after full emptying iteration",
            subEnumeration.hasMoreElements());
    }

    /**
     * Test method to verify proper iteration sequence using nextElement() on a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() returns elements in the correct order from a sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the complete iteration sequence of a sublist enumeration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through all elements of the sublist enumeration, verifying each element matches
     * the expected value in sequence. Finally, it verifies that hasMoreElements() returns false after all elements
     * have been consumed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 (exclusive) of the main vector</p>
     * <p>- HEnumeration is created from subList.elements()</p>
     * <p>- Enumeration is at the starting position</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor is at the end position</p>
     * <p>- All three sublist elements have been consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Elements should be returned in sequence: "2", "3", null</p>
     * <p>hasMoreElements() should return false after the third element is consumed</p>
     */
    @Test
    public void testSubListNextElementProperIteration()
    {
        assertEquals("First element of subList should match", "2", subEnumeration.nextElement());
        assertEquals("Second element of subList should match", "3", subEnumeration.nextElement());
        assertNull("Third element of subList should be null", subEnumeration.nextElement());

        assertFalse("Collection should have no more elements after iteration", subEnumeration.hasMoreElements());
    }

    /**
     * Test method to verify that nextElement() throws NoSuchElementException when exhausted on a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() throws NoSuchElementException when called after
     * all elements in a sublist have been consumed, ensuring proper exception handling for sublist enumerations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the exception handling behavior of a sublist enumeration
     * when it's exhausted.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test first exhausts the sublist enumeration by iterating through all elements until
     * hasMoreElements() returns false. It then attempts to call nextElement() again and expects
     * a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- SubListAdapter contains 3 elements: ["2", "3", null]</p>
     * <p>- HEnumeration is created from subList.elements()</p>
     * <p>- All elements have been consumed using nextElement()</p>
     * <p>- hasMoreElements() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration remains in exhausted state</p>
     * <p>- NoSuchElementException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextElement() should throw NoSuchElementException when called on an exhausted sublist enumeration.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubListNextElementThrowsException()
    {
        while(subEnumeration.hasMoreElements())
            subEnumeration.nextElement();

        subEnumeration.nextElement();
    }

    /**
     * Test method to verify the behavior of hasMoreElements() on an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns false when the enumeration is created
     * from an empty sublist, indicating that no elements are available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of an empty sublist. This ensures that
     * sublist enumerations correctly handle sublists with no elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty sublist and creates an enumeration from it. Then
     * checks if hasMoreElements() returns false, confirming that no elements are available.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 of the main vector</p>
     * <p>- HEnumeration is created from the empty sublist.elements()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration state remains unchanged</p>
     * <p>- No elements are available for iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return false, indicating no elements are available for iteration.</p>
     */
    @Test
    public void testSubListHasMoreElementsOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 1);
        HEnumeration subEnumer = sub.elements();

        assertFalse("Empty subList should have no elements", subEnumer.hasMoreElements());
    }

    /**
     * Test method to verify that nextElement() throws NoSuchElementException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() throws NoSuchElementException when called on an
     * enumeration created from an empty sublist, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an empty sublist.
     * This ensures proper exception reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty sublist and creates an enumeration from it.
     * It then attempts to call nextElement() and expects a NoSuchElementException
     * to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 0 to 0 of the main vector</p>
     * <p>- HEnumeration is created from the empty sublist.elements()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Enumeration remains in exhausted state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextElement() should throw NoSuchElementException when called on an empty sublist.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubListNextElementThrowsExceptionOnEmptyCollecion()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HEnumeration subEnumer = sub.elements();

        subEnumer.nextElement();
    }

    /**
     * Test method to verify proper iteration with null elements using nextElement() on a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() correctly handles sublists containing null
     * elements and returns them properly during iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate enumeration behavior with null values
     * in sublists, without throwing exceptions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with null elements, then creates a sublist
     * and creates an enumeration from it. It verifies that hasMoreElements() returns true initially,
     * then iterates checking that each call to nextElement() returns null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 null elements: [null, null, null, null]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     * <p>- HEnumeration is created from sublist.elements()</p>
     * <p>- Enumeration is at the starting position</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor is at the end position</p>
     * <p>- All three null elements have been consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return true initially</p>
     * <p>All three calls to nextElement() should return null</p>
     * <p>hasMoreElements() should return false after the third element is consumed</p>
     */
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

    /**
     * Test method to verify proper iteration sequence using nextElement() on a nested sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() returns elements in the correct order from a nested
     * sublist (sublist of a sublist).</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the complete iteration sequence of a nested sublist enumeration.
     * This ensures that sublist operations work correctly even with multiple nested levels.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a multiple sublist from the main vector. It then iterates
     * through the nested sublist enumeration, verifying each element matches the expected value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- First SubListAdapter is created from indices 1 to 5 of the main vector</p>
     * <p>- Second SubListAdapter is created from indices 1 to 3 of the first sublist</p>
     * <p>- HEnumeration is created from the nested sublist.elements()</p>
     * <p>- Enumeration is at the starting position</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration cursor is at the end position</p>
     * <p>- Both elements of the nested sublist have been consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Elements should be returned in sequence: "3", null</p>
     * <p>hasMoreElements() should return false after the second element is consumed</p>
     */
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

    /**
     * Test method to verify the behavior of hasMoreElements() on an empty nested sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasMoreElements() returns false when the enumeration is created
     * from an empty nested sublist, indicating that no elements are available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of an empty nested sublist. This ensures
     * that nested sublist enumerations correctly handle empty ranges.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a sublist from the main vector, then creates an empty sub-sublist
     * from that sublist. It creates an enumeration from the empty nested sublist and checks
     * if hasMoreElements() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- First SubListAdapter is created from indices 1 to 4 of the main vector</p>
     * <p>- Second SubListAdapter is created from indices 1 to 1 of the first sublist</p>
     * <p>- HEnumeration is created from the empty nested sublist.elements()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Enumeration state remains unchanged</p>
     * <p>- No elements are available for iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasMoreElements() should return false, indicating no elements are available for iteration.</p>
     */
    @Test
    public void testNestedSubListHasMoreElementsEmpty()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HEnumeration subEnumer = subSub.elements();

        assertFalse("Empty sub-subList should have no elements", subEnumer.hasMoreElements());
    }

    /**
     * Test method to verify that nextElement() throws NoSuchElementException on empty nested sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextElement() throws NoSuchElementException when called on an
     * enumeration created from an empty nested sublist, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an empty nested sublist.
     * This ensures proper exception handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a sublist from the main vector, then creates an empty sub-sublist
     * from that sublist. It creates an enumeration from the empty nested sublist and attempts
     * to call nextElement(), expecting a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- First SubListAdapter is created from indices 1 to 4 of the main vector</p>
     * <p>- Second SubListAdapter is created from indices 1 to 1 of the first sublist</p>
     * <p>- HEnumeration is created from the empty nested sublist.elements()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Enumeration remains in exhausted state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextElement() should throw NoSuchElementException when called on an empty nested sublist.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNestedSubListNextElementThrowsExceptionOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HEnumeration subEnumer = subSub.elements();

        subEnumer.nextElement();
    }
}
