package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HIterator;
import myAdapter.HListIterator;
import myAdapter.ListIteratorAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for the ListIteratorAdapter class.
 *
 * <p>This test suite validates the functionality of the ListIteratorAdapter class,
 * which implements the HListIterator interface to provide bidirectional iteration
 * over elements of a VectorAdapter or SubListAdapter. The tests cover various
 * scenarios including forward and backward traversal, element modification,
 * insertion, removal, and edge cases.</p>
 *
 * <h2>Test Case Design</h2>
 * <p>The test cases are designed to validate the behavior of ListIteratorAdapter
 * against the specifications of Java 1.4.2 ListIterator interface. The test design
 * includes categories for basic functionality, edge cases, error conditions,
 * and sublist-specific behavior.</p>
 *
 * <h2>Testing Framework Information</h2>
 * <p>This test suite utilizes JUnit 4.13 testing framework with Hamcrest 1.3
 * matchers for assertions. These dependencies are located as follows:</p>
 * <ul>
 *   <li>JUnit 4.13: <code>lib/junit-4.13.jar</code></li>
 *   <li>Hamcrest Core 1.3: <code>Matcher/hamcrest-core-1.3.jar</code></li>
 * </ul>
 *
 * <p>Categories:</p>
 * <ul>
 *   <li>Basic bidirectional iteration tests</li>
 *   <li>Index position tracking tests</li>
 *   <li>Element modification tests (set, add, remove)</li>
 *   <li>Empty collection tests</li>
 *   <li>Null element handling tests</li>
 *   <li>SubList iterator tests</li>
 *   <li>Exception handling tests</li>
 *   <li>ListIterator with specific starting index tests</li>
 * </ul>
 *
 * @author   Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see      ListIteratorAdapter
 * @see      HIterator
 * @see      HListIterator
 */
public class ListIteratorAdapterTest
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
     * HListIterator instance for iterating over the main vector.
     * Created from the vector.listIterator() method.
     */
    private HListIterator listIterator;

    /**
     * HListIterator instance for iterating over the sublist.
     * Created from the subList.listIterator() method.
     */
    private HListIterator subListLIterator;

    /**
     * Default constructor
     */
    public ListIteratorAdapterTest()
    {}

    /**
     * Sets up the testing environment before each test method execution.
     * Initializes the vector and sublist with test data and creates iterator instances.
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

        listIterator = vector.listIterator();

        subList = (SubListAdapter) vector.subList(1, 4);
        subListLIterator = subList.listIterator();
    }

    /**
     * Cleans up test environment after each test method execution.
     * Sets all instance variables to null to assist garbage collection.
     */
    @After
    public void tearDown()
    {
        vector = null;
        subList = null;
        listIterator = null;
        subListLIterator = null;
    }

    /**
     * Test method to verify that nextIndex() returns the correct index when iterating through the list in forward order.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the nextIndex() method of a HListIterator correctly returns the index of the element
     * that would be returned by a subsequent call to next() at every step during a full forward traversal of the list.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the fundamental contract of the nextIndex() method during normal iteration.
     * It ensures that the reported index always correctly corresponds to the current position of the iterator
     * and matches the index of the element in the underlying list.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through the entire list using the iterator's hasNext() and next() methods. Before each call
     * to next(), it checks that the value returned by nextIndex() is equal to the index of the element that is about
     * to be returned. This element is also fetched directly from the underlying list using the get() method for comparison.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A HListIterator is obtained from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The iterator has been traversed to the end of the list</p>
     * <p>- All assertions have passed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>For every step of the iteration, the value returned by listIterator.nextIndex() must be equal to the index
     * of the element returned by the subsequent listIterator.next() call, and this element must equal the element
     * at that same index in the original vector (vector.get(index)).</p>
     */
    @Test
    public void testNextIndexProperOrder()
    {
        while(listIterator.hasNext())
            assertEquals("Element should match corresponding index at " + listIterator.nextIndex(),
                vector.get(listIterator.nextIndex()), listIterator.next());
    }

    /**
     * Test method to verify that nextIndex() returns 0 when the iterator is at the beginning of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the nextIndex() method correctly returns 0 when the HListIterator is positioned
     * at the start of the list, before any elements have been traversed.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to check the initial state of the iterator. This is a boundary condition test to ensure
     * the iterator's cursor is correctly initialized and reports the first valid index (0) before any navigation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Immediately after obtaining a new HListIterator from the vector, the test calls nextIndex() and asserts
     * that the returned value is 0.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A new HListIterator is obtained from the vector and has not been used</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The iterator's state is unchanged (still at the beginning of the list)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>listIterator.nextIndex() must return 0.</p>
     */
    @Test
    public void testNextIndexAtTheBeginning()
    {
        assertEquals("Should be 0 at beginning", 0, listIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() is updated correctly after calls to next().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the nextIndex() method is incremented correctly as the iterator advances through
     * the list using the next() method.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state transition of the iterator's cursor after each navigation operation.
     * It checks that the index reported by nextIndex() increases by one after each successful call to next().</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() twice on a fresh iterator. After the first call, it asserts that nextIndex() returns 1.
     * After the second call, it asserts that nextIndex() returns 2.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A new HListIterator is obtained from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The iterator has advanced two positions into the list</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After the first next() call, listIterator.nextIndex() must return 1.
     * After the second next() call, listIterator.nextIndex() must return 2.</p>
     */
    @Test
    public void testNextIndexAfterNext()
    {
        listIterator.next();
        assertEquals("Should be 1 after first next", 1, listIterator.nextIndex());

        listIterator.next();
        assertEquals("Should be 2 after second next", 2, listIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() is updated correctly after calls to previous().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the nextIndex() method is decremented correctly as the iterator moves backwards
     * through the list using the previous() method from the end of the list.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of nextIndex() during reverse traversal. It ensures that
     * after moving the iterator to the end and then calling previous(), the reported index correctly reflects
     * the new cursor position.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test first iterates to the end of the list using next() and hasNext(). It then calls previous() twice.
     * After the first previous() call, it asserts that nextIndex() returns the index of the last element (size() - 1).
     * After the second previous() call, it asserts that nextIndex() returns the index of the second-to-last element (size() - 2).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A HListIterator is obtained from the vector and advanced to the end</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The iterator has moved two positions backwards from the end of the list</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After the first previous() call from the end, listIterator.nextIndex() must return vector.size() - 1.
     * After the second previous() call, listIterator.nextIndex() must return vector.size() - 2.</p>
     */
    @Test
    public void testNextIndexAfterPrevious()
    {
        while(listIterator.hasNext())
            listIterator.next();

        listIterator.previous();
        assertEquals("Should be size() - 1 after previous", vector.size() - 1, listIterator.nextIndex());

        listIterator.previous();
        assertEquals("Should be size() - 2 after previous", vector.size() - 2, listIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() returns the list's size when the iterator is at the end.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the nextIndex() method correctly returns the size of the list (which is equal to
     * the index after the last element) when the iterator has been traversed to the end and there are no more elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to check a boundary condition at the end of the list. It ensures the iterator correctly
     * signals that it is past the last element by returning the list's size, as specified by the HListIterator contract.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through the entire list until hasNext() returns false. It then calls nextIndex() and asserts
     * that the returned value is equal to the size of the underlying vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A HListIterator is obtained from the vector and advanced to the end (hasNext() returns false)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The iterator is positioned at the end of the list</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>listIterator.nextIndex() must return vector.size().</p>
     */
    @Test
    public void testNextIndexAtTheEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertEquals("Should be size() at end", vector.size(), listIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() is updated correctly after adding an element using add().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the nextIndex() method correctly reflects the new list state after an element
     * is inserted using the iterator's add(Object) method.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the iterator's behavior after a structural modification is made through
     * itself. The add operation increases the list size and shifts subsequent elements, which should be reflected
     * in the cursor position reported by nextIndex().</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Without moving the iterator from its initial position (index 0), the test calls add("0") to insert an element.
     * It then asserts that nextIndex() returns 1, indicating the cursor is now positioned after the newly inserted element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A new HListIterator is obtained from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The list size has increased by 1</p>
     * <p>- A new element "0" has been inserted at index 0</p>
     * <p>- The iterator's cursor is positioned after the new element (at index 1)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After adding an element, listIterator.nextIndex() must return 1.</p>
     */
    @Test
    public void testNextIndexAfterAdd()
    {
        listIterator.add("0");
        assertEquals("Should update after add", 1, listIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() is updated correctly after removing an element using remove().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the nextIndex() method correctly reflects the new list state after an element
     * is removed using the iterator's remove() method.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the iterator's behavior after a structural modification that decreases
     * the list size. The remove operation should adjust the cursor position, and nextIndex() should report the
     * new correct index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to move the cursor to the first element (index 0) and then calls remove() to delete
     * that element. It then asserts that nextIndex() returns 0, indicating the cursor is now at the beginning of
     * the now-shorter list, which previously contained the element at index 1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A HListIterator is obtained from the vector</p>
     * <p>- The iterator's next() method has been called once (last returned element is at index 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The list size has decreased by 1</p>
     * <p>- The element "1" at index 0 has been removed</p>
     * <p>- The iterator's cursor is positioned at the new beginning of the list (index 0)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After removing the first element, listIterator.nextIndex() must return 0.</p>
     */
    @Test
    public void testNextIndexAfterRemove()
    {
        listIterator.next();
        listIterator.remove();
        assertEquals("Should update after remove", 0, listIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() remains unchanged after replacing an element using set().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the nextIndex() method is not affected by replacing an element using the
     * iterator's set(Object) method, as set() is not a structural modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that a non-structural modification (element replacement) does not
     * alter the iterator's cursor position. The index reported by nextIndex() should remain the same before
     * and after the set operation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to move the cursor to the first element (index 0) and advance the cursor to index 1.
     * It then calls set("6") to replace the last returned element (the element at index 0). Finally, it asserts
     * that nextIndex() still returns 1, confirming the cursor position was unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A HListIterator is obtained from the vector</p>
     * <p>- The iterator's next() method has been called once (cursor is at index 1, last returned element is at index 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The element at index 0 has been changed from "1" to "6"</p>
     * <p>- The list size is unchanged</p>
     * <p>- The iterator's cursor is still positioned at index 1</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After setting an element, listIterator.nextIndex() must remain unchanged and return 1.</p>
     */
    @Test
    public void testNextIndexAfterSet()
    {
        listIterator.next();
        listIterator.set("6");
        assertEquals("Should not update after set", 1, listIterator.nextIndex());
    }

    /**
     * Test method to verify that previousIndex() returns the correct index when iterating through the list in reverse order.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the previousIndex() method of a HListIterator correctly returns the index of the element
     * that would be returned by a subsequent call to previous() at every step during a full backward traversal of the list.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the fundamental contract of the previousIndex() method during reverse iteration.
     * It is the counterpart to testNextIndexProperOrder, ensuring correct behavior when moving from the end to the beginning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test first iterates to the end of the list. It then iterates backwards using hasPrevious() and previous().
     * Before each call to previous(), it checks that the value returned by previousIndex() is equal to the index of the
     * element that is about to be returned. This element is also fetched directly from the underlying list for comparison.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- A VectorAdapter is initialized with elements: ["1", "2", "3", null, "5"]</p>
     * <p>- A HListIterator is obtained from the vector and advanced to the end</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- The iterator has been traversed backwards to the beginning of the list</p>
     * <p>- All assertions have passed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>For every step of the reverse iteration, the value returned by listIterator.previousIndex() must be equal to the index
     * of the element returned by the subsequent listIterator.previous() call, and this element must equal the element
     * at that same index in the original vector (vector.get(index)).</p>
     */
    @Test
    public void testPreviousIndexProperOrder()
    {
        while(listIterator.hasNext())
            listIterator.next();

        while(listIterator.hasPrevious())
            assertEquals("Element should match corresponding index at " + listIterator.previousIndex(),
                vector.get(listIterator.previousIndex()), listIterator.previous());
    }

    /**
     * Test method to verify that previousIndex() returns -1 at the beginning of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() returns -1 when called at the beginning of the list,
     * indicating no previous element exists before the cursor position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of the list iterator, ensuring proper
     * index reporting when no elements have been traversed yet.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator and immediately calls previousIndex() without any
     * navigation operations, expecting a return value of -1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- previousIndex() returns -1</p>
     * <p>- Iterator state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return -1 when called at the beginning of the list.</p>
     */
    @Test
    public void testPreviousIndexAtTheBeginning()
    {
        assertEquals("Should be -1 at beginning", -1, listIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() returns correct values after next() operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() returns correct index values after navigating
     * forward through the list using next() method.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate index tracking during forward traversal, ensuring
     * the iterator maintains correct positional information.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() once and verifies previousIndex() returns 0, then calls next()
     * again and verifies previousIndex() returns 1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- previousIndex() returns correct values after each next() call</p>
     * <p>- Iterator position advances as expected</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return 0 after first next() and 1 after second next().</p>
     */
    @Test
    public void testPreviousIndexAfterNext()
    {
        listIterator.next();
        assertEquals("Should be 0 after first next", 0, listIterator.previousIndex());

        listIterator.next();
        assertEquals("Should be 1 after second next", 1, listIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() returns correct values after previous() operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() returns correct index values when navigating
     * backward through the list after reaching the end.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate index tracking during backward traversal, ensuring
     * the iterator maintains correct positional information when moving in reverse.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test first navigates to the end of the list, then calls previous() and verifies
     * previousIndex() returns appropriate values based on the current position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- previousIndex() returns correct values after each previous() call</p>
     * <p>- Iterator position moves backward as expected</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return size()-2 after first previous() and size()-3 after second previous().</p>
     */
    @Test
    public void testPreviousIndexAfterPrevious()
    {
        while(listIterator.hasNext())
            listIterator.next();

        listIterator.previous();
        assertEquals("Should be size() - 2 after previous", vector.size() - 2, listIterator.previousIndex());

        listIterator.previous();
        assertEquals("Should be size() - 3 after previous", vector.size() - 3, listIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() returns correct value at the end of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() returns the correct index when the iterator
     * is positioned at the end of the list.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the boundary condition at the end of the list,
     * ensuring proper index reporting when all elements have been traversed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test navigates to the end of the list and calls previousIndex() without calling
     * previous(), verifying it returns the index of the last element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- previousIndex() returns size()-1</p>
     * <p>- Iterator remains at end position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return size()-1 when at the end of the list.</p>
     */
    @Test
    public void testPreviousIndexAtTheEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertEquals("Should be size() - 1 at end",
            vector.size() - 1, listIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() updates correctly after add() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() returns the correct value after adding
     * an element to the list using the add() method.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the iterator maintains correct positional
     * information after structural modifications to the underlying collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test adds an element at the beginning of the list and verifies that
     * previousIndex() returns the correct updated value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "0" is added to the list</p>
     * <p>- previousIndex() returns updated position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return 0 after adding an element at the beginning.</p>
     */
    @Test
    public void testPreviousIndexAfterAdd()
    {
        listIterator.add("0");
        assertEquals("Should update after add", 0, listIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() updates correctly after remove() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() returns the correct value after removing
     * an element from the list using the remove() method.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the iterator maintains correct positional
     * information after structural modifications to the underlying collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test navigates to the first element, removes it, and verifies that
     * previousIndex() returns the correct updated value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First element is removed from the list</p>
     * <p>- previousIndex() returns updated position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return -1 after removing the first element.</p>
     */
    @Test
    public void testPreviousIndexAfterRemove()
    {
        listIterator.next();
        listIterator.remove();
        assertEquals("Should update after remove", -1, listIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() remains unchanged after set() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() returns the same value after replacing
     * an element using the set() method, as set() doesn't change the iterator position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that non-structural modifications (element replacement)
     * do not affect the iterator's positional information.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test navigates forward, then backward, replaces an element with set(), and
     * verifies that previousIndex() returns the same value as before the operation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element is replaced with new value "6"</p>
     * <p>- previousIndex() remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return the same value after set() operation.</p>
     */
    @Test
    public void testPreviousIndexAfterSet()
    {
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.set("6");

        assertEquals("Should not update after set", 0, listIterator.previousIndex());
    }

    /**
     * Test method to verify iterator behavior on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that all iterator methods behave correctly when operating on
     * an empty collection, ensuring proper boundary condition handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case behavior with empty collections,
     * ensuring all iterator methods return expected values and don't throw unexpected exceptions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and its list iterator, then verifies
     * that hasPrevious(), hasNext(), previousIndex(), and nextIndex() return appropriate
     * values for an empty collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty VectorAdapter is created</p>
     * <p>- HListIterator is created from the empty vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All iterator methods return expected values for empty collection</p>
     * <p>- No exceptions are thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() and hasNext() should return false, previousIndex() should return -1,
     * and nextIndex() should return 0 for an empty collection.</p>
     */
    @Test
    public void testOperationsEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Should not have previous", listIter.hasPrevious());
        assertFalse("Should not have next", listIter.hasNext());
        assertEquals("Should be -1", -1, listIter.previousIndex());
        assertEquals("Should be 0", 0, listIter.nextIndex());
    }

    /**
     * Test method to verify that hasNext() returns true at the beginning of a non-empty list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns true when called at the beginning of
     * a non-empty list, indicating elements are available for traversal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of the iterator for non-empty
     * collections, ensuring proper detection of available elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator from a non-empty vector and immediately calls
     * hasNext(), expecting it to return true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns true</p>
     * <p>- Iterator state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return true when called at the beginning of a non-empty list.</p>
     */
    @Test
    public void testHasNextAtTheBeginning()
    {
        assertTrue("Collection should have been created and filled properly",
            listIterator.hasNext());
    }

    /**
     * Test method to verify that hasNext() returns true in the middle of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns true when called in the middle of
     * the list, indicating more elements are available for traversal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate intermediate state detection during traversal,
     * ensuring the iterator correctly reports available elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test navigates to the middle of the list and calls hasNext(), expecting
     * it to return true since more elements remain.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns true</p>
     * <p>- Iterator position is in the middle of the list</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return true when called in the middle of the list.</p>
     */
    @Test
    public void testHasNextInTheMiddle()
    {
        listIterator.next();
        listIterator.next();
        listIterator.next();

        assertTrue("Collection should have some more elements", listIterator.hasNext());
    }

    /**
     * Test method to verify that hasNext() returns false at the end of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns false when called at the end of
     * the list, indicating no more elements are available for forward traversal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the end-of-list detection, ensuring the iterator
     * correctly reports when all elements have been traversed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test navigates to the end of the list and calls hasNext(), expecting
     * it to return false since no more elements remain.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns false</p>
     * <p>- Iterator position is at the end of the list</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false when called at the end of the list.</p>
     */
    @Test
    public void testHasNextAtTheEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertFalse("hasNext should return false after full emptying iteration",
            listIterator.hasNext());
    }

    /**
     * Test method to verify that hasPrevious() returns false at the beginning of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasPrevious() returns false when the list iterator is at the beginning
     * of the collection, ensuring proper behavior for boundary conditions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of a newly created list iterator. This ensures
     * that the iterator correctly reports its position when no elements have been traversed yet.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator from the main vector and immediately checks the hasPrevious()
     * method without any prior navigation. The expected result is false since the iterator is positioned
     * before the first element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from vector.listIterator()</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasPrevious() returns false</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() should return false when called at the beginning of the list.</p>
     */
    @Test
    public void testHasPreviousAtTheBeginning()
    {
        assertFalse("Should return false at the beginning",
            listIterator.hasPrevious());
    }

    /**
     * Test method to verify that hasPrevious() returns true after moving forward in the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasPrevious() returns true when the list iterator has moved forward
     * from its initial position, ensuring proper tracking of iterator position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the iterator correctly updates its internal state
     * after navigation operations and properly reports available previous elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator, calls next() twice to move forward two positions, then
     * checks hasPrevious(). The expected result is true since there are elements behind the current
     * position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from vector.listIterator()</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasPrevious() returns true after moving forward</p>
     * <p>- Iterator is positioned after the second element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() should return true after moving forward in the list.</p>
     */
    @Test
    public void testHasPreviousInTheMiddle()
    {
        listIterator.next();
        listIterator.next();
        assertTrue("Should return true after moving forward",
            listIterator.hasPrevious());
    }

    /**
     * Test method to verify that hasPrevious() returns true at the end of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasPrevious() returns true when the list iterator has reached
     * the end of the collection, ensuring proper behavior for the final position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the iterator correctly reports available previous
     * elements even when it has traversed all elements and reached the end of the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator, calls next() repeatedly until all elements are traversed,
     * then checks hasPrevious(). The expected result is true since there are elements behind the
     * final position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from vector.listIterator()</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasPrevious() returns true at the end</p>
     * <p>- Iterator is positioned after the last element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() should return true when at the end of the list.</p>
     */
    @Test
    public void testHasPreviousAtTheEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertTrue("Should return true even at the end", listIterator.hasPrevious());
    }

    /**
     * Test method to verify proper forward and backward iteration through the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() and previous() methods work correctly for complete
     * forward and backward traversal of the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the complete iteration functionality, including
     * handling of null values and proper sequence maintenance in both directions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test performs a complete forward iteration through all elements using next(),
     * verifying each element's value and order. Then it performs a complete backward
     * iteration using previous(), again verifying each element's value and order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from vector.listIterator()</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All elements are traversed in correct order in both directions</p>
     * <p>- Iterator returns to initial position</p>
     * <p>- hasNext() and hasPrevious() return appropriate values</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() and previous() should correctly traverse all elements in proper sequence
     * in both directions, handling null values appropriately.</p>
     */
    @Test
    public void testNextiAndPreviousProperIterationForwardAndBackward()
    {
        assertEquals("First element should match", "1", listIterator.next());
        assertEquals("Second element should match", "2", listIterator.next());
        assertEquals("Third element should match", "3", listIterator.next());
        assertNull("Fourth element should be null", listIterator.next());
        assertEquals("Fifth element should match", "5", listIterator.next());

        assertFalse("Collection should no more elements", listIterator.hasNext());

        assertEquals("Fifth element should match", "5", listIterator.previous());
        assertNull("Fourth element should be null", listIterator.previous());
        assertEquals("Third element should match", "3", listIterator.previous());
        assertEquals("Second element should match", "2", listIterator.previous());
        assertEquals("First element should match", "1", listIterator.previous());

        assertFalse("Collection should have no more elements", listIterator.hasPrevious());
    }

    /**
     * Test method to verify alternating next() and previous() calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that alternating calls to next() and previous() return the
     * same element repeatedly, ensuring proper cursor positioning behavior.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the iterator's cursor positioning semantics
     * when alternating between forward and backward navigation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test performs a series of next() and previous() calls in alternating pattern
     * for each element, verifying that the same element is returned when moving back
     * and forth over the same position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from vector.listIterator()</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Alternating next() and previous() calls return the same element</p>
     * <p>- Iterator position is correctly maintained</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Alternating calls to next() and previous() should return the same element
     * when moving back and forth over the same position.</p>
     */
    @Test
    public void testNextAndPreviousAlternation()
    {
        assertEquals("Should return '1'", "1", listIterator.next());
        assertEquals("Should return '1'", "1", listIterator.previous());
        assertEquals("Should return '1'", "1", listIterator.next());

        assertEquals("Should return '2'", "2", listIterator.next());
        assertEquals("Should return '2'", "2", listIterator.previous());
        assertEquals("Should return '2'", "2", listIterator.next());

        assertEquals("Should return '3'", "3", listIterator.next());
        assertEquals("Should return '3'", "3", listIterator.previous());
        assertEquals("Should return '3'", "3", listIterator.next());

        assertNull("Should return null", listIterator.next());
        assertNull("Should return null", listIterator.previous());
        assertNull("Should return null", listIterator.next());

        assertEquals("Should return '5'", "5", listIterator.next());
        assertEquals("Should return '5'", "5", listIterator.previous());
        assertEquals("Should return '5'", "5", listIterator.next());
    }

    /**
     * Test method to verify that next() throws NoSuchElementException when no more elements exist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() throws NoSuchElementException when called after
     * all elements have been traversed, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the boundary condition
     * of attempting to access elements beyond the end of the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test traverses all elements using next() until hasNext() returns false,
     * then attempts to call next() again, expecting a NoSuchElementException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from vector.listIterator()</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Iterator remains at end position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called after all elements
     * have been traversed.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsException()
    {
        while(listIterator.hasNext())
            listIterator.next();

        listIterator.next();
    }

    /**
     * Test method to verify that previous() throws NoSuchElementException at the beginning.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previous() throws NoSuchElementException when called at
     * the beginning of the collection, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the boundary condition
     * of attempting to access elements before the beginning of the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator and immediately calls previous() without any
     * prior navigation, expecting a NoSuchElementException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from vector.listIterator()</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previous() should throw NoSuchElementException when called at the beginning
     * of the list.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testPrevioudThrowsException()
    {
        listIterator.previous();
    }

    /**
     * Test method to verify that next() throws NoSuchElementException on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() throws NoSuchElementException when called on an
     * empty collection, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an
     * empty collection, ensuring robustness.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, creates a list iterator from it, and
     * immediately calls next(), expecting a NoSuchElementException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty VectorAdapter is created</p>
     * <p>- HListIterator is created from the empty vector</p>
     * <p>- Iterator is positioned before the first (non-existent) element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Iterator remains in initial position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called on an empty collection.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        listIter.next();
    }

    /**
     * Test method to verify that previous() throws NoSuchElementException on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previous() throws NoSuchElementException when called on an
     * empty collection, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an
     * empty collection, ensuring robustness.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, creates a list iterator from it, and
     * immediately calls previous(), expecting a NoSuchElementException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty VectorAdapter is created</p>
     * <p>- HListIterator is created from the empty vector</p>
     * <p>- Iterator is positioned before the first (non-existent) element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Iterator remains in initial position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previous() should throw NoSuchElementException when called on an empty collection.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testPreviousThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        listIter.previous();
    }

    /**
     * Test method to verify that hasNext() returns false on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns false when called on an empty
     * collection, ensuring proper behavior for boundary conditions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the correct reporting of available elements
     * for the edge case of an empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, creates a list iterator from it, and
     * checks hasNext(), expecting false since there are no elements to traverse.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty VectorAdapter is created</p>
     * <p>- HListIterator is created from the empty vector</p>
     * <p>- Iterator is positioned before the first (non-existent) element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns false</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false when called on an empty collection.</p>
     */
    @Test
    public void testHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Empty collection should have no elements",
            listIter.hasNext());
    }

    /**
     * Test method to verify that hasPrevious() returns false on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasPrevious() returns false when called on an empty
     * collection, ensuring proper behavior for boundary conditions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the correct reporting of available previous
     * elements for the edge case of an empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, creates a list iterator from it, and
     * checks hasPrevious(), expecting false since there are no elements behind the
     * current position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty VectorAdapter is created</p>
     * <p>- HListIterator is created from the empty vector</p>
     * <p>- Iterator is positioned before the first (non-existent) element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasPrevious() returns false</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() should return false when called on an empty collection.</p>
     */
    @Test
    public void testHasPreviousOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Empty collection should have no elements",
            listIter.hasPrevious());
    }

    /**
     * Test method to verify iteration through a list containing only null values.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() correctly handles collections containing
     * only null values, ensuring proper null value handling during iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the iterator's behavior with edge case
     * data where all elements are null, ensuring robustness.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing only null values, creates a
     * list iterator, and iterates through all elements using next(), verifying
     * that each returned value is null and that hasNext() reports correctly.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements: [null, null, null]</p>
     * <p>- HListIterator is created from the vector</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All three null elements are returned in sequence</p>
     * <p>- hasNext() returns false after last element</p>
     * <p>- Iterator is positioned after the last element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should correctly return null for each element and hasNext() should
     * properly report available elements in a collection containing only null values.</p>
     */
    @Test
    public void testNextNull()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        HListIterator listIter = vec.listIterator();

        assertTrue("Should have elements", listIter.hasNext());
        assertNull("First element should be null", listIter.next());
        assertNull("Second element should be null", listIter.next());
        assertNull("Third element should be null", listIter.next());
        assertFalse("Should have no more elements", listIter.hasNext());
    }

    /**
     * Test method to verify backward iteration through a list containing only null values.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previous() correctly handles collections containing
     * only null values, ensuring proper null value handling during backward iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the iterator's backward navigation behavior
     * with edge case data where all elements are null, ensuring robustness.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing only null values, creates a
     * list iterator, moves to the end using next(), then iterates backward through
     * all elements using previous(), verifying that each returned value is null and
     * that hasPrevious() reports correctly.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements: [null, null, null]</p>
     * <p>- HListIterator is created from the vector</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All three null elements are returned in reverse sequence</p>
     * <p>- hasPrevious() returns false after first element</p>
     * <p>- Iterator is positioned before the first element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previous() should correctly return null for each element and hasPrevious()
     * should properly report available elements in a collection containing only null values.</p>
     */
    @Test
    public void testPreviousNull()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        HListIterator listIter = vec.listIterator();

        while(listIter.hasNext())
            listIter.next();

        assertTrue("Should have elements", listIter.hasPrevious());
        assertNull("Third element should be null", listIter.previous());
        assertNull("Second element should be null", listIter.previous());
        assertNull("First element should be null", listIter.previous());
        assertFalse("Should have no more elements", listIter.hasPrevious());
    }

    /**
     * Test method to verify that remove() operation works correctly after next() call.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove() operation correctly removes the element returned by the most recent next() call,
     * reducing the collection size and ensuring the element is no longer present.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic functionality of the remove() operation after a next() call,
     * ensuring proper element removal and collection state maintenance.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to advance the iterator and then calls remove(). It verifies that the collection size decreases by one,
     * the first element changes to the next element in sequence, and the removed element is no longer contained in the collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size is reduced by 1</p>
     * <p>- First element becomes "2"</p>
     * <p>- Element "1" is no longer in the collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully remove the element returned by next(), decrease collection size by 1,
     * and update the collection contents appropriately.</p>
     */
    @Test
    public void testRemoveAfterNext()
    {
        listIterator.next();
        listIterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertEquals("First element should be '2'", "2", vector.get(0));
        assertFalse("Should not contain removed element", vector.contains("1"));
    }

    /**
     * Test method to verify that remove() operation works correctly after previous() call.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove() operation correctly removes the element returned by the most recent previous() call,
     * reducing the collection size and updating the collection state.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove() operation functionality after a previous() call,
     * ensuring proper element removal when traversing backwards through the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() twice to advance the iterator to the third element, then calls previous() to move back to the second element,
     * and finally calls remove(). It verifies that the collection size decreases by one and the element at the removed position is updated.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size is reduced by 1</p>
     * <p>- Element at index 2 becomes null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully remove the element returned by previous(), decrease collection size by 1,
     * and update the collection contents appropriately.</p>
     */
    @Test
    public void testRemoveAfterPrevious()
    {
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.remove();

        assertEquals("Size should be decreased by 1", 4, vector.size());
        assertNull("Element at index 2 should be now null", vector.get(2));
    }

    /**
     * Test method to verify that remove() throws IllegalStateException when called without prior next() or previous() call.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called without a preceding next() or previous() call,
     * ensuring proper exception handling for invalid operation sequences.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case where remove() is called without proper preparation,
     * ensuring the iterator maintains correct state management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an iterator and immediately calls remove() without any prior navigation calls,
     * expecting an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called without a preceding next() or previous() call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsException()
    {
        listIterator.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException when called consecutively without intermediate navigation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called consecutively without an intervening next() or previous() call,
     * ensuring proper operation sequencing enforcement.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the iterator properly tracks operation state and prevents multiple consecutive remove() calls,
     * maintaining collection integrity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to advance the iterator, calls remove() once successfully, then immediately calls remove() again
     * without an intervening navigation operation, expecting an IllegalStateException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown on second remove() call</p>
     * <p>- Collection reflects only the first removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called consecutively without an intervening next() or previous() call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterRemoveAndNext()
    {
        listIterator.next();
        listIterator.remove();
        listIterator.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException when called consecutively after previous() without intermediate navigation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called consecutively after a previous() operation
     * without an intervening navigation call, ensuring consistent state management in both directions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the iterator maintains proper state tracking for backward traversal operations
     * and prevents invalid consecutive remove() calls.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to advance the iterator, calls previous() to move back, calls remove() once successfully,
     * then immediately calls remove() again without an intervening navigation operation, expecting an IllegalStateException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown on second remove() call</p>
     * <p>- Collection reflects only the first removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called consecutively after previous() without an intervening navigation call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterRemoveAndPrevious()
    {
        listIterator.next();
        listIterator.previous();
        listIterator.remove();
        listIterator.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException when called on an empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called on an iterator from an empty collection,
     * ensuring proper error handling for edge cases.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the boundary condition of an empty collection,
     * ensuring the iterator behaves correctly when no elements are available for removal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, creates an iterator from it, and immediately calls remove(),
     * expecting an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty VectorAdapter is created</p>
     * <p>- HListIterator is created from the empty vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection remains empty and unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called on an iterator from an empty collection.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        listIter.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException after add() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called after an add() operation
     * without an intervening navigation call, ensuring proper operation sequencing.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the iterator properly invalidates the remove operation after an add operation,
     * maintaining collection modification consistency.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to advance the iterator, calls add() to insert a new element, then immediately calls remove()
     * without an intervening navigation operation, expecting an IllegalStateException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection contains the newly added element but no removal occurs</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called after an add() operation without an intervening navigation call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterAdd()
    {
        listIterator.next();
        listIterator.add("6");
        listIterator.remove();
    }

    /**
     * Test method to verify multiple consecutive remove() operations after next() calls work correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple remove() operations work correctly when interleaved with next() calls,
     * ensuring proper sequential element removal and collection state maintenance.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the iterator's ability to handle multiple removal operations in sequence,
     * maintaining correct position and collection state throughout the process.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test performs three consecutive next()-remove() operations, verifying that the collection size decreases appropriately
     * with each removal and the remaining elements are in the expected positions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size is reduced to 2</p>
     * <p>- Remaining elements are null at index 0 and "5" at index 1</p>
     * <p>- Elements "1", "2", and "3" are removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Multiple consecutive remove() operations after next() calls should correctly remove elements in sequence
     * and maintain proper collection state.</p>
     */
    @Test
    public void testRemoveMultipleNext()
    {
        listIterator.next();
        listIterator.remove();

        listIterator.next();
        listIterator.remove();

        listIterator.next();
        listIterator.remove();

        assertEquals("Size should be 2", 2, vector.size());
        assertNull("First element should be null", vector.get(0));
        assertEquals("Second element should be '5'", "5", vector.get(1));
    }

    /**
     * Test method to verify multiple consecutive remove() operations after previous() calls work correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple remove() operations work correctly when interleaved with previous() calls during backward traversal,
     * ensuring proper sequential element removal from the end of the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the iterator's ability to handle multiple removal operations during backward traversal,
     * maintaining correct position and collection state when removing elements from the end.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator to the end, then performs three consecutive previous()-remove() operations,
     * verifying that the collection size decreases appropriately with each removal and the remaining elements are in the expected positions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size is reduced to 2</p>
     * <p>- Remaining elements are "1" at index 0 and "2" at index 1</p>
     * <p>- Elements "5", null, and "3" are removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Multiple consecutive remove() operations after previous() calls should correctly remove elements in reverse sequence
     * and maintain proper collection state.</p>
     */
    @Test
    public void testRemoveMultiplePrevious()
    {
        while(listIterator.hasNext())
            listIterator.next();

        listIterator.previous();
        listIterator.remove();

        listIterator.previous();
        listIterator.remove();

        listIterator.previous();
        listIterator.remove();

        assertEquals("Size should be 2", 2, vector.size());
        assertEquals("Last element should be '2'", "2", vector.get(1));
        assertEquals("First element should be '1'", "1", vector.get(0));
    }

    /**
     * Test method to verify that remove() operation works correctly when called at the end of the collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the last element when called after traversing to the end of the collection,
     * ensuring proper boundary condition handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation at the collection's end boundary,
     * ensuring proper element removal and state maintenance when operating on the final element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator to the end using next() calls, then calls remove() to delete the last element,
     * verifying that the collection size decreases and the element is properly removed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size is reduced to 4</p>
     * <p>- Last element becomes null</p>
     * <p>- Element "5" is removed from the collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should correctly remove the last element when called at the end of the collection,
     * properly updating the collection size and contents.</p>
     */
    @Test
    public void testRemoveAtTheEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        listIterator.remove();

        assertEquals("Size should be 4", 4, vector.size());
        assertNull("Last element should now be null", vector.get(3));
        assertFalse("Should not contain the removed element", vector.contains("5"));
    }

    /**
     * Test method to verify that remove() operation works correctly with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly handles collections with duplicate elements,
     * ensuring proper element removal without affecting other identical elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation's behavior with duplicate elements,
     * ensuring that only the specific instance at the current iterator position is removed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a collection with three duplicate elements, advances the iterator to the first element,
     * calls remove(), and verifies that only one instance is removed while the others remain.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 duplicate elements: ["0", "0", "0"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size is reduced to 2</p>
     * <p>- Both remaining elements are "0"</p>
     * <p>- Only the first instance is removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should correctly remove only the current element when duplicates exist,
     * without affecting other identical elements in the collection.</p>
     */
    @Test
    public void testRemoveDuplicateElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("0");
        vec.add("0");

        HListIterator listIter = vec.listIterator();
        listIter.next();
        listIter.remove();

        assertEquals("Size should be 2", 2, vec.size());
        assertEquals("Both remaining elements should be 'duplicate'", "0", vec.get(0));
        assertEquals("Both remaining elements should be 'duplicate'", "0", vec.get(1));
    }

    /**
     * Test method to verify that set() operation works correctly after next().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() operation correctly replaces the last element returned by next()
     * and maintains the collection size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the set operation functionality when called after moving the iterator
     * forward with next(). This ensures proper element replacement without structural modification.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test moves the iterator forward using next(), then calls set() to replace the returned element.
     * It verifies that the collection size remains unchanged, the new element is present, and the original
     * element is no longer in the collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size remains 5</p>
     * <p>- Element at index 0 is replaced with "6"</p>
     * <p>- Original element "1" is removed from collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should replace the last returned element without changing collection size.</p>
     */
    @Test
    public void testSetAfterNext()
    {
        listIterator.next();
        listIterator.set("6");

        assertEquals("Size should still be 5", 5, vector.size());
        assertEquals("Should contain element '6'", "6", vector.get(0));
        assertFalse("Should not contain the overridden element", vector.contains("1"));
    }

    /**
     * Test method to verify that set() operation works correctly after previous().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() operation correctly replaces the last element returned by previous()
     * and maintains the collection size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the set operation functionality when called after moving the iterator
     * backward with previous(). This ensures proper element replacement in both directions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test moves the iterator forward twice with next(), then backward once with previous(), then calls set()
     * to replace the returned element. It verifies size consistency and element replacement.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size remains 5</p>
     * <p>- Element at index 1 is replaced with "6"</p>
     * <p>- Original element "2" is removed from collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should replace the last returned element regardless of direction movement.</p>
     */
    @Test
    public void testSetAfterPrevious()
    {
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.set("6");

        assertEquals("Size should still be 5", 5, vector.size());
        assertEquals("Should contain element '6'", "6", vector.get(1));
        assertFalse("Should not contain the overridden element", vector.contains("2"));
    }

    /**
     * Test method to verify that set() throws IllegalStateException when called without prior movement.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() throws IllegalStateException when called without first calling next() or previous().</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for illegal state conditions,
     * ensuring the iterator maintains correct state management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to call set() immediately after creating the iterator, without any prior movement
     * operations. It expects an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     * <p>- No next() or previous() calls have been made</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Iterator state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should throw IllegalStateException when no element has been returned by next() or previous().</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSetThrowsException()
    {
        listIterator.set("6");
    }

    /**
     * Test method to verify that set() throws IllegalStateException after remove() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() throws IllegalStateException when called after a remove() operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that structural modifications (remove) invalidate subsequent set operations,
     * maintaining iterator contract consistency.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test moves the iterator forward with next(), performs a remove() operation, then attempts to call set().
     * It expects an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     * <p>- next() has been called once</p>
     * <p>- remove() has been called immediately after next()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection size is reduced by 1</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should throw IllegalStateException after structural modification via remove().</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSetThrowsExceptionAfterRemove()
    {
        listIterator.next();
        listIterator.remove();
        listIterator.set("7");
    }

    /**
     * Test method to verify that set() throws IllegalStateException after add() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() throws IllegalStateException when called after an add() operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that structural modifications (add) invalidate subsequent set operations,
     * maintaining iterator contract consistency.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test moves the iterator forward with next(), performs an add() operation, then attempts to call set().
     * It expects an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     * <p>- next() has been called once</p>
     * <p>- add() has been called immediately after next()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection size is increased by 1</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should throw IllegalStateException after structural modification via add().</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSetThrowsExceptionAfterAdd()
    {
        listIterator.next();
        listIterator.add("6");
        listIterator.set("7");
    }

    /**
     * Test method to verify multiple consecutive set() operations work correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple set() operations can be performed consecutively without issues.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the iterator maintains proper state management
     * for multiple consecutive replacement operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test moves the iterator forward with next(), calls set() to replace the element,
     * moves forward again with next(), and calls set() again. It verifies both replacements occurred correctly.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size remains 5</p>
     * <p>- Elements at indices 0 and 1 are replaced with "6" and "7" respectively</p>
     * <p>- Original elements "1" and "2" are removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Multiple consecutive set() operations should work correctly without affecting iterator state.</p>
     */
    @Test
    public void testMultipleSetOperations()
    {
        listIterator.next();
        listIterator.set("6");
        listIterator.next();
        listIterator.set("7");

        assertEquals("First element should be modified", "6", vector.get(0));
        assertEquals("Second element should be modified", "7", vector.get(1));
    }

    /**
     * Test method to verify that set() can be overridden by subsequent set() calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a set() operation can be overridden by a subsequent set() call on the same position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element replacement behavior when multiple set() operations
     * target the same element position.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test moves the iterator forward twice with next(), calls set() to replace the element,
     * moves backward with previous(), and calls set() again on the same position. It verifies that
     * only the last replacement persists.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size remains 5</p>
     * <p>- Element at index 1 contains the value from the last set() call ("7")</p>
     * <p>- Intermediate set() value ("6") is not present in collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Subsequent set() operations on the same element should override previous replacements.</p>
     */
    @Test
    public void testOverrideWithSet()
    {
        listIterator.next();
        listIterator.next();
        listIterator.set("6");

        listIterator.previous();
        listIterator.set("7");

        assertEquals("Next element should be th latest added", "7", listIterator.next());
        assertFalse("First setted element should not appear", vector.contains("6"));
        assertEquals("Second element should be the latest setted", "7", vector.get(1));
    }

    /**
     * Test method to verify that add() operation works correctly at the beginning of collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that add() operation inserts elements correctly at the beginning of the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion behavior at the beginning boundary of the collection,
     * ensuring proper cursor positioning and element ordering.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls add() immediately after creating the iterator (before any movement),
     * verifying that the element is inserted at position 0, size increases, and subsequent next()
     * returns the original first element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     * <p>- Iterator cursor is at position 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size increases to 6</p>
     * <p>- New element "6" is inserted at index 0</p>
     * <p>- Original elements are shifted right by one position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should insert elements at beginning correctly and maintain proper cursor positioning.</p>
     */
    @Test
    public void testAddAtTheBeginning()
    {
        listIterator.add("6");

        assertEquals("Size should increase by 1", 6, vector.size());
        assertEquals("First element should be the new element", "6", vector.get(0));
        assertEquals("Next should return original first element", "1", listIterator.next());
    }

    /**
     * Test method to verify that add() operation works correctly in the middle of collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that add() operation inserts elements correctly in the middle of the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion behavior in the middle of the collection,
     * ensuring proper element shifting and cursor positioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test moves the iterator forward twice with next(), then calls add() to insert an element
     * at the current position. It verifies size increase, correct element placement, and that
     * subsequent next() returns the element following the inserted one.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     * <p>- Iterator cursor is at position 2 after two next() calls</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size increases to 6</p>
     * <p>- New element "6" is inserted at index 2</p>
     * <p>- Elements from index 2 onward are shifted right by one position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should insert elements in middle correctly and maintain proper cursor positioning.</p>
     */
    @Test
    public void testAddInMiddle()
    {
        listIterator.next();
        listIterator.next();
        listIterator.add("6");

        assertEquals("Size should increase by 1", 6, vector.size());
        assertEquals("Element at index 1 should be new element", "6", vector.get(2));
        assertEquals("Next should return element after inserted", "3", listIterator.next());
    }

    /**
     * Test method to verify that add() operation works correctly at the end of collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that add() operation inserts elements correctly at the end of the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion behavior at the end boundary of the collection,
     * ensuring proper cursor positioning and element ordering.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test moves the iterator to the end using hasNext()/next(), then calls add() to append an element.
     * It verifies size increase, correct element placement at the end, and that previous()
     * returns the newly added element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     * <p>- Iterator cursor is at the end after traversing all elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size increases to 6</p>
     * <p>- New element "6" is appended at index 5</p>
     * <p>- Iterator cursor remains at end position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should append elements at end correctly and maintain proper cursor positioning.</p>
     */
    @Test
    public void testAddAtEnd()
    {
        while (listIterator.hasNext())
            listIterator.next();

        listIterator.add("6");

        assertEquals("Size should increase by 1", 6, vector.size());
        assertEquals("Last element should be new element", "6", vector.get(5));
        assertEquals("Previous should return new element", "6", listIterator.previous());
    }

    /**
     * Test method to verify multiple consecutive add() operations work correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple add() operations can be performed consecutively with correct ordering.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate complex insertion scenarios with multiple consecutive additions
     * at different cursor positions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test performs two add() operations at the beginning, moves forward with next(),
     * then performs another add() operation. It verifies all elements are inserted in correct positions
     * and order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HListIterator is created from the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size increases to 8</p>
     * <p>- New elements are inserted in correct order at positions 0, 1, and 3</p>
     * <p>- Original elements are properly shifted</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Multiple consecutive add() operations should maintain correct element ordering and positioning.</p>
     */
    @Test
    public void testMultipleAddOperations()
    {
        listIterator.add("6");
        listIterator.add("7");

        listIterator.next();

        listIterator.add("8");

        assertEquals("Size should increase by 3", 8, vector.size());
        assertEquals("Elements should be added in correct order", "6", vector.get(0));
        assertEquals("Elements should be added in correct order", "7", vector.get(1));
        assertEquals("Elements should be added in correct order", "8", vector.get(3));
    }

    /**
     * Test method to verify that add() operation works correctly on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that add() operation works correctly when the collection is initially empty.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion behavior on empty collections, ensuring proper
     * initialization and cursor management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, gets its iterator, and calls add() to insert the first element.
     * It verifies collection size, element presence, and that previous() returns the added element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- HListIterator is created from the empty vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size becomes 1</p>
     * <p>- Element "0" is added at index 0</p>
     * <p>- Iterator has previous element available</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should work correctly on empty collections, properly initializing the collection.</p>
     */
    @Test
    public void testAddOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        listIter.add("0");

        assertEquals("Size should be 1", 1, vec.size());
        assertTrue("Should contain just one element", listIter.hasPrevious());
        assertEquals("Should return the added element", "0", listIter.previous());
    }

    /**
     * Test method to verify that listIterator(0) behaves correctly at the beginning of the vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created at index 0 correctly reports its position
     * and can retrieve the first element when moving forward.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of a list iterator created at the start
     * of a non-empty collection, ensuring proper boundary handling and navigation capabilities.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 0 and verifies that hasNext() returns true,
     * hasPrevious() returns false, nextIndex() returns 0, previousIndex() returns -1, and
     * next() returns the first element of the collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator position advances to index 1 after calling next()</p>
     * <p>- hasPrevious() returns true after moving forward</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>All iterator state checks should pass and the first element should be correctly retrieved.</p>
     */
    @Test
    public void testListIteratorFromIndexAtTheBeginning()
    {
        HListIterator listIter = vector.listIterator(0);

        assertTrue("Should have next at beginning", listIter.hasNext());
        assertFalse("Should not have previous at beginning", listIter.hasPrevious());
        assertEquals("Next index should be 0", 0, listIter.nextIndex());
        assertEquals("Previous index should be -1", -1, listIter.previousIndex());
        assertEquals("First element should be correct", "1", listIter.next());
    }

    /**
     * Test method to verify that listIterator(2) behaves correctly in the middle of the vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created at a middle index correctly reports its position
     * and can navigate both forward and backward.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate iterator behavior when starting from a middle position,
     * ensuring bidirectional navigation works correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 2 and verifies navigation in both directions,
     * checking position indices and element retrieval.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator can move forward to index 3 and backward to index 1</p>
     * <p>- Element retrieval works correctly in both directions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Iterator should correctly report positions and retrieve elements when navigating bidirectionally
     * from a middle starting point.</p>
     */
    @Test
    public void testListIteratorFromIndexInTheMiddle()
    {
        HListIterator listIter = vector.listIterator(2);

        assertTrue("Should have next at middle", listIter.hasNext());
        assertTrue("Should have previous at middle", listIter.hasPrevious());
        assertEquals("Next index should be 2", 2, listIter.nextIndex());
        assertEquals("Previous index should be 1", 1, listIter.previousIndex());
        assertEquals("Next element should be correct", "3", listIter.next());
        assertEquals("Previous element should be correct", "3", listIter.previous());
    }

    /**
     * Test method to verify that listIterator(size()) behaves correctly at the end of the vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created at the end index correctly reports its position
     * and can retrieve the last element when moving backward.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of a list iterator created at the end
     * of a collection, ensuring proper boundary handling and backward navigation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at the end index and verifies that hasNext() returns false,
     * hasPrevious() returns true, nextIndex() equals size, previousIndex() equals size-1, and
     * previous() returns the last element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator position moves to index size-1 after calling previous()</p>
     * <p>- hasNext() returns true after moving backward</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>All iterator state checks should pass and the last element should be correctly retrieved.</p>
     */
    @Test
    public void testListIteratorFromIndexAtTheEnd()
    {
        HListIterator listIter = vector.listIterator(vector.size());

        assertFalse("Should not have next at end", listIter.hasNext());
        assertTrue("Should have previous at end", listIter.hasPrevious());
        assertEquals("Next index should be size", vector.size(), listIter.nextIndex());
        assertEquals("Previous index should be size - 1", vector.size() - 1, listIter.previousIndex());
        assertEquals("Last element should be correct", "5", listIter.previous());
    }

    /**
     * Test method to verify that listIterator(-1) throws IndexOutOfBoundsException.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that creating a list iterator with a negative index throws
     * the appropriate exception.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for invalid negative indices
     * when creating list iterators.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a list iterator with index -1 and expects an
     * IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- No iterator is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IndexOutOfBoundsException should be thrown when creating iterator with negative index.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorFromIndexNegativeIndex()
    {
        vector.listIterator(-1);
    }

    /**
     * Test method to verify that listIterator(size+1) throws IndexOutOfBoundsException.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that creating a list iterator with an index greater than size
     * throws the appropriate exception.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for indices beyond the
     * collection size when creating list iterators.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a list iterator with index size+1 and expects an
     * IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- No iterator is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IndexOutOfBoundsException should be thrown when creating iterator with index bigger than the size.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorFromIndexIndexGreaterThanSize()
    {
        vector.listIterator(vector.size() + 1);
    }

    /**
     * Test method to verify that listIterator(0) behaves correctly on an empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created on an empty collection correctly
     * reports no available elements in either direction.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate iterator behavior on empty collections, ensuring
     * proper handling of edge cases.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector, creates a list iterator at index 0, and verifies
     * that both hasNext() and hasPrevious() return false, with correct index values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator remains in initial state with no navigation possible</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Iterator should report no elements available and correct index positions on empty collection.</p>
     */
    @Test
    public void testListIteratorFromIndexEmptyVector()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator(0);

        assertFalse("Collection should not have next", listIter.hasNext());
        assertFalse("Collection should not have previous", listIter.hasPrevious());
        assertEquals("Next index should be 0", 0, listIter.nextIndex());
        assertEquals("Previous index should be -1", -1, listIter.previousIndex());
    }

    /**
     * Test method to verify forward iteration from a middle starting point.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that forward iteration from index 2 retrieves all subsequent
     * elements in correct order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sequential forward navigation from a non-zero
     * starting position.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 2 and iterates forward through all
     * remaining elements, verifying each element is retrieved in sequence.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches the end of the collection</p>
     * <p>- hasNext() returns false after final element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>All elements from index 2 to the end should be retrieved in correct sequence.</p>
     */
    @Test
    public void testListIteratorFromIndexNextForwardIteration()
    {
        HListIterator listIter = vector.listIterator(2);

        assertEquals("First element from index 2", "3", listIter.next());
        assertNull("Second element from index 2", listIter.next());
        assertEquals("Third element from index 2", "5", listIter.next());
        assertFalse("Should have no more elements", listIter.hasNext());
    }

    /**
     * Test method to verify backward iteration from the end of the vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that backward iteration from index 4 retrieves all previous
     * elements in reverse order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sequential backward navigation from the end
     * position.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 4 and iterates backward through all
     * previous elements, verifying each element is retrieved in reverse sequence.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches the beginning of the collection</p>
     * <p>- hasPrevious() returns false after first element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>All elements from index 3 to the beginning should be retrieved in reverse sequence.</p>
     */
    @Test
    public void testListIteratorFromIndexPreviousBackwardIteration()
    {
        HListIterator listIter = vector.listIterator(4);

        assertEquals("First element backward from index 4", null, listIter.previous());
        assertEquals("Second element backward from index 4", "3", listIter.previous());
        assertEquals("Third element backward from index 4", "2", listIter.previous());
        assertEquals("Fourth element backward from index 4", "1", listIter.previous());
        assertFalse("Should have no more previous elements", listIter.hasPrevious());
    }


    /**
     * Test method to verify bidirectional navigation from a middle starting point.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that alternating next() and previous() calls work correctly
     * from a middle starting position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate complex bidirectional navigation patterns
     * and element retrieval consistency.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 3 and performs a sequence of next()
     * and previous() operations, verifying that elements are consistently retrieved
     * and positions are correctly maintained.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator returns to original position after bidirectional navigation</p>
     * <p>- Element retrieval remains consistent throughout navigation</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Bidirectional navigation should work correctly with consistent element retrieval
     * and position tracking.</p>
     */
    @Test
    public void testListIteratorFromIndexBidirectionalIteration()
    {
        HListIterator listIter = vector.listIterator(3);

        assertNull("Next should be null", listIter.next());
        assertEquals("Next should be 5", "5", listIter.next());

        assertEquals("Previous should be 5", "5", listIter.previous());
        assertNull("Previous should be null", listIter.previous());
        assertEquals("Previous should be 3", "3", listIter.previous());

        assertEquals("Next should be 3", "3", listIter.next());
        assertNull("Next should be null", listIter.next());
    }

    /**
     * Test method to verify remove operation after next() call.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the element last returned by next()
     * and updates the collection accordingly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation functionality when called
     * after forward navigation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 2, calls next() to retrieve an element,
     * then calls remove() and verifies the element is removed from the collection and
     * size is decreased.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size decreases by 1</p>
     * <p>- Removed element is no longer in collection</p>
     * <p>- Subsequent elements shift left</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully remove the current element and update collection state.</p>
     */
    @Test
    public void testListIteratorFromIndexRemovAfterNext()
    {
        HListIterator listIter = vector.listIterator(2);

        listIter.next();
        listIter.remove();

        assertEquals("Size should decrease", 4, vector.size());
        assertEquals("Element at index 2 should be null", null, vector.get(2));
        assertFalse("Should not contain removed element", vector.contains("3"));
    }

    /**
     * Test method to verify remove operation after previous() call.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the element last returned by previous()
     * and updates the collection accordingly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation functionality when called
     * after backward navigation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 4, calls previous() to retrieve an element,
     * then calls remove() and verifies the element is removed from the collection and
     * size is decreased.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size decreases by 1</p>
     * <p>- Removed element is no longer in collection</p>
     * <p>- Subsequent elements shift left</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully remove the current element and update collection state.</p>
     */
    @Test
    public void testListIteratorFromIndexRemoveAfterPrevious()
    {
        HListIterator listIter = vector.listIterator(4);

        listIter.previous();
        listIter.remove();

        assertEquals("Size should decrease", 4, vector.size());
        assertEquals("Element at index 3 should be 5", "5", vector.get(3));
        assertFalse("Should not contain null element", vector.contains(null));
    }

    /**
     * Test method to verify that remove() throws IllegalStateException without prior navigation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called without
     * a prior next() or previous() call.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for invalid remove() calls
     * without prior element retrieval.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 2 and immediately calls remove() without
     * any prior navigation, expecting IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- List iterator is created at index 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IllegalStateException should be thrown when remove() is called without prior navigation.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testListIteratorFromIndexRemoveWithoutNextPrevious()
    {
        HListIterator listIter = vector.listIterator(2);
        listIter.remove();
    }

    /**
     * Test method to verify set operation after next() call.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly replaces the element last returned by next()
     * and maintains iterator position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the set operation functionality when called
     * after forward navigation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 1, calls next() to retrieve an element,
     * then calls set() with a new value and verifies the element is replaced in the collection
     * while iterator position remains unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Target element is replaced with new value</p>
     * <p>- Collection size remains unchanged</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should successfully replace the current element without affecting collection size
     * or iterator position.</p>
     */
    @Test
    public void testListIteratorFromIndexSetAfterNext()
    {
        HListIterator listIter = vector.listIterator(1);

        listIter.next();
        listIter.set("6");

        assertEquals("Element should be modified", "6", vector.get(1));
        assertEquals("Next should return next element", "3", listIter.next());
    }

    /**
     * Test method to verify set operation after previous() call.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly replaces the element last returned by previous()
     * and maintains iterator position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the set operation functionality when called
     * after backward navigation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 3, calls previous() to retrieve an element,
     * then calls set() with a new value and verifies the element is replaced in the collection
     * while iterator position remains unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Target element is replaced with new value</p>
     * <p>- Collection size remains unchanged</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should successfully replace the current element without affecting collection size
     * or iterator position.</p>
     */
    @Test
    public void testListIteratorFromIndexSetAfterPrevious()
    {
        HListIterator listIter = vector.listIterator(3);

        listIter.previous();
        listIter.set("6");

        assertEquals("Element should be modified", "6", vector.get(2));
        assertEquals("Next should return modified element", "6", listIter.next());
    }

    /**
     * Test method to verify that set() throws IllegalStateException without prior navigation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() throws IllegalStateException when called without
     * a prior next() or previous() call.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for invalid set() calls
     * without prior element retrieval.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 2 and immediately calls set() without
     * any prior navigation, expecting IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- List iterator is created at index 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IllegalStateException should be thrown when set() is called without prior navigation.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testListIteratorFromIndexSetWithoutNextPrevious()
    {
        HListIterator listIter = vector.listIterator(2);
        listIter.set("6");
    }

    /**
     * Test method to verify that add() operation works correctly at the beginning of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add() method correctly inserts an element at the beginning
     * of the list when the iterator is positioned at index 0.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at the beginning boundary of the list,
     * ensuring proper size update and element positioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at index 0, calls add() to insert a new element,
     * and verifies that the collection size increases, the new element is placed at index 0,
     * and subsequent next() call returns the original first element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size increases by 1</p>
     * <p>- New element is inserted at position 0</p>
     * <p>- Original elements are shifted right</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should successfully insert element at beginning, update size, and maintain
     * iterator position correctly.</p>
     */
    @Test
    public void testListIteratorFromIndexAddAtTheBeginning()
    {
        HListIterator listIter = vector.listIterator(0);

        listIter.add("6");

        assertEquals("Size should increase", 6, vector.size());
        assertEquals("New element should be at index 0", "6", vector.get(0));
        assertEquals("Next should return original element", "1", listIter.next());
    }

    /**
     * Test method to verify that add() operation works correctly at the end of the list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add() method correctly appends an element at the end
     * of the list when the iterator is positioned at the last index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at the end boundary of the list,
     * ensuring proper size update and element positioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator at the end index (size()), calls add() to insert
     * a new element, and verifies that the collection size increases, the new element is
     * placed at the end, and previous() call returns the newly added element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size increases by 1</p>
     * <p>- New element is appended at the end</p>
     * <p>- Iterator position allows access to new element via previous()</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should successfully append element at end, update size, and allow backward
     * navigation to the new element.</p>
     */
    @Test
    public void testListIteratorFromIndexAddAtEnd()
    {
        HListIterator listIter = vector.listIterator(vector.size());

        listIter.add("6");

        assertEquals("Size should increase", 6, vector.size());
        assertEquals("New element should be at the end", "6", vector.get(5));
        assertEquals("Previous should return new element", "6", listIter.previous());
    }

    /**
     * Test method to verify that nextIndex() maintains proper order during forward iteration over sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextIndex() correctly returns the index of the element
     * that would be returned by subsequent next() calls during forward iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate index consistency during complete forward traversal
     * of a sublist, ensuring proper index-element correspondence.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through the entire sublist using hasNext() and next(), verifying
     * at each step that nextIndex() matches the index of the element returned by next(),
     * and that the element matches the corresponding element in the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches end of sublist</p>
     * <p>- All index-element correspondences are verified</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextIndex() should consistently return correct indices that match both the iterator
     * position and corresponding sublist elements throughout forward iteration.</p>
     */
    @Test
    public void testSubListNextIndexProperOrder()
    {
        while(subListLIterator.hasNext())
            assertEquals("Element should match corresponding at " + subListLIterator.nextIndex(),
                subList.get(subListLIterator.nextIndex()), subListLIterator.next());
    }

    /**
     * Test method to verify that nextIndex() returns 0 at the beginning of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextIndex() correctly returns 0 when the iterator is at
     * the starting position of the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of nextIndex() before any
     * navigation operations on the sublist iterator.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a sublist iterator and immediately checks that nextIndex()
     * returns 0, indicating the position before the first element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator remains at initial position</p>
     * <p>- No elements are consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextIndex() should return 0 at the beginning of iteration, indicating the position
     * before the first element.</p>
     */
    @Test
    public void testSubListNextIndexAtTheBeginning()
    {
        assertEquals("Should be 0 at beginning", 0, subListLIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() updates correctly after next() calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextIndex() increments appropriately after each next()
     * call during sublist iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the incremental behavior of nextIndex() during
     * forward navigation through the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() sequentially on the sublist iterator and verifies after
     * each call that nextIndex() returns the expected incremented value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator advances through multiple elements</p>
     * <p>- nextIndex() values are verified at each step</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextIndex() should increment by 1 after each next() call, accurately reflecting
     * the iterator's forward progression.</p>
     */
    @Test
    public void testSubListNextIndexAfterNext()
    {
        subListLIterator.next();
        assertEquals("Should be 1 after first next", 1, subListLIterator.nextIndex());

        subListLIterator.next();
        assertEquals("Should be 2 after second next", 2, subListLIterator.nextIndex());
    }


    /**
     * Test method to verify that nextIndex() updates correctly after previous() calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextIndex() decrements appropriately after each previous()
     * call during backward sublist iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the decremental behavior of nextIndex() during
     * backward navigation through the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test first advances to the end of the sublist, then calls previous() sequentially
     * and verifies after each call that nextIndex() returns the expected decremented value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator moves backward through multiple elements</p>
     * <p>- nextIndex() values are verified at each backward step</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextIndex() should decrement by 1 after each previous() call, accurately reflecting
     * the iterator's backward progression.</p>
     */
    @Test
    public void testSubListNextIndexAfterPrevious()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.previous();
        assertEquals("Should be size() - 1 after previous", subList.size() - 1, subListLIterator.nextIndex());

        subListLIterator.previous();
        assertEquals("Should be size() - 2 after previous", subList.size() - 2, subListLIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() returns sublist size at the end of iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextIndex() correctly returns the sublist size when the
     * iterator has reached the end position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the final state of nextIndex() after complete
     * forward traversal of the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator to the end of the sublist and verifies that
     * nextIndex() returns the sublist size, indicating the position after the last element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches end position</p>
     * <p>- No more elements available for next()</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextIndex() should return the sublist size at the end of iteration, indicating
     * the position after the last element.</p>
     */
    @Test
    public void testSubListNextIndexAtTheEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertEquals("Should be size() at end", subList.size(), subListLIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() updates correctly after add() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextIndex() reflects the correct position after an add()
     * operation on the sublist iterator.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate position maintenance after element insertion
     * via the add() method.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls add() on the sublist iterator and verifies that nextIndex()
     * updates to reflect the new position after the inserted element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- New element is inserted into sublist</p>
     * <p>- Iterator position advances past inserted element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextIndex() should increment by 1 after add() operation, positioning the iterator
     * after the newly inserted element.</p>
     */
    @Test
    public void testSubListNextAfterAdd()
    {
        subListLIterator.add("0");
        assertEquals("Should update after add", 1, subListLIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() updates correctly after remove() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextIndex() reflects the correct position after a remove()
     * operation on the sublist iterator.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate position maintenance after element removal
     * via the remove() method.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to position the iterator, then remove() to delete the current
     * element, and verifies that nextIndex() updates to reflect the new position after removal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element is removed from sublist</p>
     * <p>- Iterator position adjusts to account for removed element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextIndex() should decrement by 1 after remove() operation, positioning the iterator
     * at the same logical position relative to the remaining elements.</p>
     */
    @Test
    public void testSubListNextAfterRemove()
    {
        subListLIterator.next();
        subListLIterator.remove();
        assertEquals("Should update after remove", 0, subListLIterator.nextIndex());
    }

    /**
     * Test method to verify that nextIndex() remains unchanged after set() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that nextIndex() is not affected by set() operation on the
     * sublist iterator.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that element replacement via set() does not
     * alter the iterator position index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to position the iterator, then set() to replace the current
     * element, and verifies that nextIndex() remains unchanged after the operation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element is replaced in sublist</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>nextIndex() should remain unchanged after set() operation, as element replacement
     * does not affect iterator position.</p>
     */
    @Test
    public void testSubListNextAfterSet()
    {
        subListLIterator.next();
        subListLIterator.set("6");
        assertEquals("Should not update after set", 1, subListLIterator.nextIndex());
    }

    /**
     * Test method to verify that previousIndex() maintains proper order during backward iteration over sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() correctly returns the index of the element
     * that would be returned by subsequent previous() calls during backward iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate index consistency during complete backward traversal
     * of a sublist, ensuring proper index-element correspondence.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test first advances to the end of the sublist, then iterates backward using
     * hasPrevious() and previous(), verifying at each step that previousIndex() matches
     * the index of the element returned by previous(), and that the element matches the
     * corresponding element in the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches beginning of sublist</p>
     * <p>- All index-element correspondences are verified</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should consistently return correct indices that match both the iterator
     * position and corresponding sublist elements throughout backward iteration.</p>
     */
    @Test
    public void testSubListPreviousIndexProperOrder()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        while(subListLIterator.hasPrevious())
            assertEquals("Element should match corresponding index at " + subListLIterator.previousIndex(),
                subList.get(subListLIterator.previousIndex()), subListLIterator.previous());
    }

    /**
     * Test method to verify that previousIndex() returns -1 at the beginning of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() correctly returns -1 when the iterator is at
     * the starting position of the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of previousIndex() before any
     * navigation operations on the sublist iterator.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a sublist iterator and immediately checks that previousIndex()
     * returns -1, indicating the position before the first element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator remains at initial position</p>
     * <p>- No elements are consumed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return -1 at the beginning of iteration, indicating no
     * previous element available.</p>
     */
    @Test
    public void testSubListPreviousIndexAtTheBeginning()
    {
        assertEquals("Should be -1 at beginning", -1, subListLIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() updates correctly after next() calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() increments appropriately after each next()
     * call during sublist iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the incremental behavior of previousIndex() during
     * forward navigation through the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() sequentially on the sublist iterator and verifies after
     * each call that previousIndex() returns the expected incremented value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator advances through multiple elements</p>
     * <p>- previousIndex() values are verified at each step</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should increment by 1 after each next() call, accurately reflecting
     * the element that would be returned by previous().</p>
     */
    @Test
    public void testSubListPreviousIndexAfterNext()
    {
        subListLIterator.next();
        assertEquals("Should be 0 after first next", 0, subListLIterator.previousIndex());

        subListLIterator.next();
        assertEquals("Should be 1 after second next", 1, subListLIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() updates correctly after previous() calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() decrements appropriately after each previous()
     * call during backward sublist iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the decremental behavior of previousIndex() during
     * backward navigation through the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test first advances to the end of the sublist, then calls previous() sequentially
     * and verifies after each call that previousIndex() returns the expected decremented value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator moves backward through multiple elements</p>
     * <p>- previousIndex() values are verified at each backward step</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should decrement by 1 after each previous() call, accurately reflecting
     * the iterator's backward progression through elements.</p>
     */
    @Test
    public void testSubListPreviousIndexAfterPrevious()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.previous();
        assertEquals("Should be size() - 2 after previous", subList.size() - 2, subListLIterator.previousIndex());

        subListLIterator.previous();
        assertEquals("Should be size() - 3 after previous", subList.size() - 3, subListLIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() returns size-1 at the end of iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() correctly returns size-1 when the iterator
     * has reached the end position after forward traversal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state of previousIndex() after complete
     * forward traversal of the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator to the end of the sublist and verifies that
     * previousIndex() returns sublist size-1, indicating the position of the last element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches end position</p>
     * <p>- Previous element is available via previous()</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should return sublist size-1 at the end of forward iteration,
     * indicating the position of the last element.</p>
     */
    @Test
    public void testSubListPreviousIndexAtTheEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertEquals("Should be size() - 1 at end", subList.size() - 1, subListLIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() updates correctly after add() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() reflects the correct position after an add()
     * operation on the sublist iterator.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate position maintenance after element insertion
     * via the add() method.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls add() on the sublist iterator and verifies that previousIndex()
     * updates to reflect the new position relative to the inserted element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- New element is inserted into sublist</p>
     * <p>- Iterator position adjusts relative to inserted element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should reflect the position before the newly inserted element
     * after add() operation.</p>
     */
    @Test
    public void testSubListPreviousIndexAfterAdd()
    {
        subListLIterator.add("0");
        assertEquals("Should update after add", 0, subListLIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() updates correctly after remove() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() reflects the correct position after a remove()
     * operation on the sublist iterator.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate position maintenance after element removal
     * via the remove() method.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to position the iterator, then remove() to delete the current
     * element, and verifies that previousIndex() updates to reflect the new position after removal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element is removed from sublist</p>
     * <p>- Iterator position adjusts to account for removed element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should adjust appropriately after remove() operation, reflecting
     * the new logical position relative to the remaining elements.</p>
     */
    @Test
    public void testSubListPreviousIndexAfterRemove()
    {
        subListLIterator.next();
        subListLIterator.remove();
        assertEquals("SHould update after remove", -1, subListLIterator.previousIndex());
    }

    /**
     * Test method to verify that previousIndex() remains unchanged after set() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previousIndex() is not affected by set() operation on the
     * sublist iterator.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that element replacement via set() does not
     * alter the iterator position index during backward navigation context.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test navigates to a position using next() and previous(), then calls set()
     * to replace the current element, and verifies that previousIndex() remains unchanged
     * after the operation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element is replaced in sublist</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previousIndex() should remain unchanged after set() operation, as element replacement
     * does not affect iterator position during backward navigation.</p>
     */
    @Test
    public void testSubListPreviousAfterSet()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.set("6");

        assertEquals("Should not update after set", 0, subListLIterator.previousIndex());
    }

    /**
     * Test method to verify list iterator operations on an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created from an empty sublist correctly
     * reports its state with no available elements in either direction.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of list iterator boundary conditions
     * when operating on an empty collection, ensuring proper state reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector, obtains a list iterator from it, and verifies
     * that both hasNext() and hasPrevious() return false, while nextIndex() returns 0
     * and previousIndex() returns -1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- HListIterator is created from the empty vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator state remains consistent with empty collection</p>
     * <p>- No navigation operations are possible</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>List iterator should correctly report no available elements and proper index
     * positions when created from an empty collection.</p>
     */
    @Test
    public void testOperationOnEmptySubList()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Should not have previous", listIter.hasPrevious());
        assertFalse("Should not have next", listIter.hasNext());
        assertEquals("Should be at -1", -1, listIter.previousIndex());
        assertEquals("Should be at 0", 0, listIter.nextIndex());
    }

    /**
     * Test method to verify hasNext() returns true at the beginning of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() correctly returns true when the sublist iterator
     * is at the starting position with elements available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of hasNext() for a non-empty
     * sublist before any navigation operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a sublist iterator from a populated sublist and immediately
     * verifies that hasNext() returns true, indicating available elements for forward iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator remains at initial position</p>
     * <p>- Forward elements are available</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return true at the beginning of iteration for a non-empty sublist,
     * indicating available elements for forward navigation.</p>
     */
    @Test
    public void testSubListHasNextAtTheBeginning()
    {
        assertTrue("SubList should have been created and filled properly",
            subListLIterator.hasNext());
    }

    /**
     * Test method to verify hasNext() returns true in the middle of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() correctly returns true when the sublist iterator
     * is in the middle of iteration with more elements available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state of hasNext() during active iteration
     * through a sublist with remaining elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the sublist iterator by two positions and verifies that hasNext()
     * still returns true, indicating more elements are available for forward iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator is positioned in the middle of the sublist</p>
     * <p>- More elements remain available for forward iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return true when positioned in the middle of a sublist with
     * remaining elements available for forward navigation.</p>
     */
    @Test
    public void testSubListHasNextInTheMiddle()
    {
        subListLIterator.next();
        subListLIterator.next();

        assertTrue("SubList should have some more elements", subListLIterator.hasNext());
    }

    /**
     * Test method to verify hasNext() returns false at the end of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() correctly returns false when the sublist iterator
     * has reached the end of available elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the terminal state of hasNext() after complete
     * forward traversal of a sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through all elements of the sublist until hasNext() returns false,
     * then verifies that the iterator correctly reports no more elements available.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches end position</p>
     * <p>- No more elements available for forward iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false after iterating through all elements of the sublist,
     * indicating no more elements available for forward navigation.</p>
     */
    @Test
    public void testSubListHasNextAtTheEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertFalse("hasNext should return false after full emptying iteration",
            subListLIterator.hasNext());
    }

    /**
     * Test method to verify hasPrevious() returns false at the beginning of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasPrevious() correctly returns false when the sublist iterator
     * is at the starting position with no previous elements available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of hasPrevious() for a sublist
     * before any backward navigation operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a sublist iterator and immediately verifies that hasPrevious()
     * returns false, indicating no elements available for backward iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator remains at initial position</p>
     * <p>- No backward elements are available</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() should return false at the beginning of iteration, indicating no
     * elements available for backward navigation.</p>
     */
    @Test
    public void testSubListHasPreviousAtTheBeginning()
    {
        assertFalse("Should return false at the beginning",
            subListLIterator.hasPrevious());
    }

    /**
     * Test method to verify hasPrevious() returns true in the middle of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasPrevious() correctly returns true when the sublist iterator
     * has advanced from the starting position, making previous elements available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state of hasPrevious() after forward navigation
     * through a sublist, when previous elements become available.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the sublist iterator by two positions and verifies that hasPrevious()
     * returns true, indicating elements are available for backward iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator is positioned away from start</p>
     * <p>- Previous elements are available for backward iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() should return true after advancing from the start position, indicating
     * elements are available for backward navigation.</p>
     */
    @Test
    public void testSubListHasPreviousInTheMiddle()
    {
        subListLIterator.next();
        subListLIterator.next();

        assertTrue("Should return true after moving forward",
            subListLIterator.hasPrevious());
    }

    /**
     * Test method to verify hasPrevious() returns true at the end of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasPrevious() correctly returns true when the sublist iterator
     * has reached the end position, with previous elements still available.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state of hasPrevious() after complete forward
     * traversal of a sublist, when all elements are behind the iterator.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through all elements of the sublist until the end, then verifies
     * that hasPrevious() returns true, indicating elements are available for backward iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches end position</p>
     * <p>- All elements are available for backward iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() should return true at the end of forward iteration, indicating all
     * elements are available for backward navigation.</p>
     */
    @Test
    public void testSubListHasPreviousAtTheEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertTrue("Should return true even at the end", subListLIterator.hasPrevious());
    }

    /**
     * Test method to verify complete forward and backward iteration through sublist elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() and previous() operations correctly retrieve all
     * elements in sequence during complete forward and backward traversal of a sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate complete round-trip iteration through a sublist,
     * ensuring element retrieval consistency in both directions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test performs complete forward iteration through the sublist using next(),
     * verifying each element, then complete backward iteration using previous(), verifying
     * each element again in reverse order, finally ensuring no more elements remain.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator completes full round-trip traversal</p>
     * <p>- All elements are verified in both directions</p>
     * <p>- Iterator reaches start position with no more elements available</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() and previous() should correctly retrieve all elements in sequence during
     * complete forward and backward traversal, with proper boundary reporting at both ends.</p>
     */
    @Test
    public void testSubListNextAndPreviousProperIterationForwardAndBackward()
    {
        assertEquals("First element should match", "2", subListLIterator.next());
        assertEquals("Second element should match", "3", subListLIterator.next());
        assertNull("Third element should be null", subListLIterator.next());

        assertFalse("SubList should no more elements", subListLIterator.hasNext());

        assertNull("Third element should be null", subListLIterator.previous());
        assertEquals("Second element should match", "3", subListLIterator.previous());
        assertEquals("First element should match", "2", subListLIterator.previous());

        assertFalse("SubList should have no more elements", subListLIterator.hasPrevious());
    }

    /**
     * Test method to verify alternating next() and previous() operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that alternating calls to next() and previous() correctly
     * retrieve the same elements repeatedly, demonstrating bidirectional navigation stability.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of alternating navigation operations,
     * ensuring consistent element retrieval and position maintenance.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test performs a sequence of alternating next() and previous() calls, verifying
     * that the same elements are returned when moving back and forth over the same positions,
     * including proper handling of null elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator navigates bidirectionally through multiple elements</p>
     * <p>- Element retrieval remains consistent during alternation</p>
     * <p>- Null elements are handled correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Alternating next() and previous() calls should return the same elements consistently,
     * demonstrating stable bidirectional navigation and proper handling of all element types.</p>
     */
    @Test
    public void testSubListNextAndPreviousAlternation()
    {
        assertEquals("Should return '2'", "2", subListLIterator.next());
        assertEquals("Should return '2'", "2", subListLIterator.previous());
        assertEquals("Should return '2'", "2", subListLIterator.next());

        assertEquals("Should return '3'", "3", subListLIterator.next());
        assertEquals("Should return '3'", "3", subListLIterator.previous());
        assertEquals("Should return '3'", "3", subListLIterator.next());

        assertNull("Should return null", subListLIterator.next());
        assertNull("Should return null", subListLIterator.previous());
        assertNull("Should return null", subListLIterator.next());
    }


    /**
     * Test method to verify that next() throws NoSuchElementException at end of sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() correctly throws NoSuchElementException when called
     * after reaching the end of the sublist during iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for boundary violation
     * during forward iteration through a sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test iterates through all elements of the sublist until exhaustion, then
     * attempts to call next() again, expecting NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reaches end position</p>
     * <p>- NoSuchElementException is thrown on excessive next() call</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called after reaching the end
     * of the sublist during iteration.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsException()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.next();
    }

    /**
     * Test method to verify that previous() throws NoSuchElementException at beginning of sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previous() correctly throws NoSuchElementException when called
     * at the beginning of the sublist before any forward navigation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for boundary violation
     * during backward iteration from the start position.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to call previous() immediately after creating the sublist iterator,
     * expecting NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator remains at start position</p>
     * <p>- NoSuchElementException is thrown on premature previous() call</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previous() should throw NoSuchElementException when called at the beginning of
     * the sublist before any forward navigation.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubListPreviousThrowsException()
    {
        subListLIterator.previous();
    }


    /**
     * Test method to verify that next() throws NoSuchElementException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() correctly throws NoSuchElementException when called
     * on an iterator created from an empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for empty collection
     * boundary conditions during forward iteration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector, creates an empty sublist from it, obtains a
     * list iterator, and attempts to call next(), expecting NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- SubListAdapter is created from range 0-0 (empty)</p>
     * <p>- HListIterator is created for the empty sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator remains on empty collection</p>
     * <p>- NoSuchElementException is thrown on next() call</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called on an iterator created
     * from an empty sublist.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        subListLIter.next();
    }

    /**
     * Test method to verify that previous() throws NoSuchElementException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previous() correctly throws NoSuchElementException when called
     * on an iterator created from an empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for empty collection
     * boundary conditions during backward iteration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector, creates an empty sublist from it, obtains a
     * list iterator, and attempts to call previous(), expecting NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- SubListAdapter is created from range 0-0 (empty)</p>
     * <p>- HListIterator is created for the empty sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator remains on empty collection</p>
     * <p>- NoSuchElementException is thrown on previous() call</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previous() should throw NoSuchElementException when called on an iterator created
     * from an empty sublist.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubListPreviousThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        subListLIter.previous();
    }

    /**
     * Test method to verify hasNext() returns false on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() correctly returns false for an iterator created
     * from an empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state reporting of hasNext() for empty
     * collection boundary conditions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector, creates an empty sublist from it, obtains a
     * list iterator, and verifies that hasNext() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- SubListAdapter is created from range 0-0 (empty)</p>
     * <p>- HListIterator is created for the empty sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reports no available elements</p>
     * <p>- hasNext() returns false consistently</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false for an iterator created from an empty sublist,
     * indicating no elements available for forward iteration.</p>
     */
    @Test
    public void testSubListHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        assertFalse("Empty collection should have no elements",
            subListLIter.hasNext());
    }

    /**
     * Test method to verify hasPrevious() returns false on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasPrevious() correctly returns false for an iterator created
     * from an empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state reporting of hasPrevious() for empty
     * collection boundary conditions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector, creates an empty sublist from it, obtains a
     * list iterator, and verifies that hasPrevious() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- SubListAdapter is created from range 0-0 (empty)</p>
     * <p>- HListIterator is created for the empty sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator reports no available elements</p>
     * <p>- hasPrevious() returns false consistently</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasPrevious() should return false for an iterator created from an empty sublist,
     * indicating no elements available for backward iteration.</p>
     */
    @Test
    public void testSubListHasPreviousOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        assertFalse("Empty collection should have no elements",
            subListLIter.hasPrevious());
    }

    /**
     * Test method to verify next() correctly handles null elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() correctly retrieves null elements during forward
     * iteration through a sublist containing multiple null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements during
     * forward iteration through a sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector containing multiple null elements, creates a sublist
     * covering all elements, and iterates forward through all elements, verifying that
     * each null element is correctly retrieved and hasNext() reports accurately.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains 3 null elements</p>
     * <p>- SubListAdapter covers all elements</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All null elements are retrieved in sequence</p>
     * <p>- hasNext() correctly reports availability throughout iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should correctly retrieve null elements during forward iteration, and
     * hasNext() should accurately report element availability throughout.</p>
     */
    @Test
    public void testSubListNextNull()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        SubListAdapter sub = (SubListAdapter) vec.subList(0, vec.size());
        HListIterator subListLIter = sub.listIterator();

        assertTrue("Should have elements", subListLIter.hasNext());
        assertNull("First element should be null", subListLIter.next());
        assertNull("Second element should be null", subListLIter.next());
        assertNull("Third element should be null", subListLIter.next());
        assertFalse("Should have no more elements", subListLIter.hasNext());
    }

    /**
     * Test method to verify previous() correctly handles null elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that previous() correctly retrieves null elements during backward
     * iteration through a sublist containing multiple null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements during
     * backward iteration through a sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector containing multiple null elements, creates a sublist
     * covering all elements, iterates to the end, then iterates backward through all
     * elements, verifying that each null element is correctly retrieved and hasPrevious()
     * reports accurately.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains 3 null elements</p>
     * <p>- SubListAdapter covers all elements</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All null elements are retrieved in reverse sequence</p>
     * <p>- hasPrevious() correctly reports availability throughout iteration</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>previous() should correctly retrieve null elements during backward iteration, and
     * hasPrevious() should accurately report element availability throughout.</p>
     */
    @Test
    public void testSubListPreviousNull()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        SubListAdapter sub = (SubListAdapter) vec.subList(0, vec.size());
        HListIterator subListLIter = sub.listIterator();

        while(subListLIter.hasNext())
            subListLIter.next();

        assertTrue("Should have elements", subListLIter.hasPrevious());
        assertNull("First element should be null", subListLIter.previous());
        assertNull("Second element should be null", subListLIter.previous());
        assertNull("Third element should be null", subListLIter.previous());
        assertFalse("Should have no more elements", subListLIter.hasPrevious());
    }

    /**
     * Test method to verify remove() operation after next() call in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the element last returned by next()
     * from the sublist and updates the sublist state accordingly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal functionality during forward iteration
     * through a sublist, ensuring proper size adjustment and element repositioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to retrieve the first element of the sublist, then calls remove()
     * to delete it, and verifies that the sublist size decreases, the element is removed, and
     * subsequent elements shift to fill the gap.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases by 1</p>
     * <p>- First element "2" is removed from sublist</p>
     * <p>- Remaining elements shift left</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the current element, decrease sublist size by 1,
     * and reposition remaining elements correctly after next() operation.</p>
     */
    @Test
    public void testSubListRemoveAfterNext()
    {
        subListLIterator.next();
        subListLIterator.remove();

        assertEquals("Size should decrease by 1", 2, subList.size());
        assertEquals("First element should be '3'", "3", subList.get(0));
        assertFalse("Should not contain removed element", subList.contains("2"));
    }

    /**
     * Test method to verify remove() operation after previous() call in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the element last returned by previous()
     * from the sublist and updates the sublist state accordingly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal functionality during backward iteration
     * through a sublist, ensuring proper size adjustment and element repositioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator forward by two elements, moves back one element using previous(),
     * then calls remove() to delete the current element, and verifies the sublist state after removal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases by 1</p>
     * <p>- Element at position 1 (originally "3") is removed</p>
     * <p>- Remaining elements shift left</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the current element, decrease sublist size by 1,
     * and reposition remaining elements correctly after previous() operation.</p>
     */
    @Test
    public void testSubListRemoveAfterPrevious()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.remove();

        assertEquals("Size should be decreased by 1", 2, subList.size());
        assertNull("Element at index 1 should be now null", subList.get(1));
    }

    /**
     * Test method to verify that remove() throws IllegalStateException without prior navigation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly throws IllegalStateException when called
     * without a prior next() or previous() call on the sublist iterator.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for invalid remove() calls
     * without prior element retrieval in sublist iteration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to call remove() immediately after creating the sublist iterator,
     * expecting IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called without a prior next()
     * or previous() operation on the sublist iterator.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsException()
    {
        subListLIterator.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException after consecutive remove calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly throws IllegalStateException when called
     * consecutively without an intervening next() or previous() call.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for consecutive remove() calls
     * without intermediate navigation operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() followed by remove(), then attempts to call remove() again
     * without an intervening navigation operation, expecting IllegalStateException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown on second remove() call</p>
     * <p>- Sublist contains one removed element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called consecutively without
     * an intervening next() or previous() operation.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionAfterNextAndRemove()
    {
        subListLIterator.next();
        subListLIterator.remove();
        subListLIterator.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException after previous() and remove().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly throws IllegalStateException when called
     * consecutively after previous() and remove() operations without intermediate navigation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for remove() calls after
     * backward navigation and removal operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next(), then previous(), then remove(), then attempts to call remove()
     * again without an intervening navigation operation, expecting IllegalStateException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown on second remove() call</p>
     * <p>- Sublist contains one removed element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called consecutively after
     * previous() and remove() operations without intermediate navigation.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionAfterPreviousAndRemove()
    {
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.remove();
        subListLIterator.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly throws IllegalStateException when called
     * on an iterator created from an empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for remove() operations
     * on empty sublist collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector, creates an empty sublist from it, obtains a
     * list iterator, and attempts to call remove(), expecting IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- SubListAdapter is created from range 0-0 (empty)</p>
     * <p>- HListIterator is created for the empty sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Empty sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called on an iterator created
     * from an empty sublist.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        subListLIter.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException after add() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly throws IllegalStateException when called
     * after an add() operation without an intervening next() or previous() call.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for remove() calls following
     * add() operations in sublist iteration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to position the iterator, then add() to insert a new element,
     * then attempts to call remove() without an intervening navigation operation, expecting
     * IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- New element remains inserted in sublist</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called after add() operation
     * without an intervening next() or previous() call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionAfterAdd()
    {
        subListLIterator.next();
        subListLIterator.add("6");
        subListLIterator.remove();
    }

    /**
     * Test method to verify consecutive remove() operations with intermediate next() calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple remove() operations work correctly when separated
     * by next() calls during forward iteration through a sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sequential element removal functionality with
     * proper intermediate navigation operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test performs two remove() operations separated by next() calls, verifying
     * that each removal correctly decreases the sublist size and maintains proper element
     * positioning.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases by 2</p>
     * <p>- First two elements are removed</p>
     * <p>- Only null element remains at position 0</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Consecutive remove() operations with intermediate next() calls should successfully
     * delete multiple elements and maintain proper sublist state.</p>
     */
    @Test
    public void testSubListRemoveMultipleElementsNext()
    {
        subListLIterator.next();
        subListLIterator.remove();

        subListLIterator.next();
        subListLIterator.remove();

        assertEquals("Size should be 1", 1, subList.size());
        assertNull("Only element should be null", subList.get(0));
    }

    /**
     * Test method to verify consecutive remove() operations with intermediate previous() calls.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple remove() operations work correctly when separated
     * by previous() calls during backward iteration through a sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sequential element removal functionality with
     * proper intermediate backward navigation operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances to the end of the sublist, then performs two remove() operations
     * separated by previous() calls, verifying proper size reduction and element positioning.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases by 2</p>
     * <p>- Last two elements are removed</p>
     * <p>- Only first element "2" remains</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Consecutive remove() operations with intermediate previous() calls should successfully
     * delete multiple elements and maintain proper sublist state during backward iteration.</p>
     */
    @Test
    public void testSubListRemoveMultipleElementsPrevious()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.previous();
        subListLIterator.remove();

        subListLIterator.previous();
        subListLIterator.remove();

        assertEquals("Size should be 1", 1, subList.size());
        assertEquals("Only element should be '2'", "2", subList.get(0));
    }

    /**
     * Test method to verify remove() operation at the end of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the last element when called
     * at the end position of sublist iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal functionality at the end boundary
     * of sublist iteration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances to the end of the sublist and calls remove() to delete the last
     * element, verifying proper size reduction and element repositioning.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases by 1</p>
     * <p>- Last element (null) is removed</p>
     * <p>- Element "3" becomes the new last element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the last element when called at the end position,
     * properly reducing sublist size and repositioning remaining elements.</p>
     */
    @Test
    public void testSubListRemoveAtTheEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.remove();

        assertEquals("Size should be 2", 2, subList.size());
        assertEquals("Last element should now be '3'", "3", subList.get(1));
        assertFalse("Should not contain the removed element", subList.contains(null));
    }

    /**
     * Test method to verify remove() operation with duplicate elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly handles duplicate elements during
     * sublist iteration, removing only the current element without affecting duplicates.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal functionality when working with
     * duplicate values in a sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with duplicate elements, creates a sublist containing
     * some duplicates, removes one element, and verifies that only that instance is removed
     * while other duplicates remain.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains 5 duplicate "0" elements</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases by 1</p>
     * <p>- One duplicate element is removed</p>
     * <p>- Remaining duplicates are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should correctly remove only the current element instance without affecting
     * other duplicate elements in the sublist.</p>
     */
    @Test
    public void testSubListRemoveDuplicateElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("0");
        vec.add("0");
        vec.add("0");
        vec.add("0");

        SubListAdapter sub = (SubListAdapter) vec.subList(1, 4);
        HListIterator subListLIter = sub.listIterator();

        subListLIter.next();
        subListLIter.remove();

        assertEquals("Size should be 2", 2, sub.size());
        assertEquals("Both remaining elements should be 'duplicate'", "0", sub.get(0));
        assertEquals("Both remaining elements should be 'duplicate'", "0", sub.get(1));
    }

    /**
     * Test method to verify set() operation after next() call in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly replaces the element last returned by next()
     * in the sublist and maintains iterator position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element replacement functionality during forward
     * iteration through a sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to retrieve the first element, then set() to replace it with
     * a new value, and verifies the replacement and subsequent iteration behavior.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First element "2" is replaced with "6"</p>
     * <p>- Iterator position remains unchanged</p>
     * <p>- Subsequent iteration continues correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should successfully replace the current element, maintain iterator position,
     * and allow continued iteration through the sublist.</p>
     */
    @Test
    public void testSubListSetAfterNext()
    {
        subListLIterator.next();
        subListLIterator.set("6");

        assertEquals("Element should have been replaces", "6", subList.get(0));
        assertEquals("Next element should still be '3'", "3", subListLIterator.next());
    }

    /**
     * Test method to verify set() operation after previous() call in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly replaces the element last returned by previous()
     * in the sublist and maintains iterator position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element replacement functionality during backward
     * iteration through a sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances forward by two elements, moves back one element using previous(),
     * then calls set() to replace the current element, and verifies the replacement and
     * subsequent iteration behavior.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "3" is replaced with "6"</p>
     * <p>- Iterator position remains unchanged</p>
     * <p>- Subsequent iteration continues correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should successfully replace the current element, maintain iterator position,
     * and allow continued iteration through the sublist after previous() operation.</p>
     */
    @Test
    public void testSubListSetAfterPrevious()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.set("6");

        assertEquals("Element should have been replaced", "6", subList.get(1));
        assertEquals("Next element should be the replaced value", "6", subListLIterator.next());
    }

    /**
     * Test method to verify that set() throws IllegalStateException without prior navigation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly throws IllegalStateException when called
     * without a prior next() or previous() call on the sublist iterator.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for invalid set() calls
     * without prior element retrieval in sublist iteration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to call set() immediately after creating the sublist iterator,
     * expecting IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should throw IllegalStateException when called without a prior next()
     * or previous() operation on the sublist iterator.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListSetThrowsExceptionWithoutNextOrPrevious()
    {
        subListLIterator.set("6");
    }

    /**
     * Test method to verify that set() throws IllegalStateException after remove() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly throws IllegalStateException when called
     * after a remove() operation without an intervening next() or previous() call.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for set() calls following
     * remove() operations in sublist iteration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() followed by remove(), then attempts to call set() without
     * an intervening navigation operation, expecting IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Sublist contains one removed element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should throw IllegalStateException when called after remove() operation
     * without an intervening next() or previous() call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListSetThrowsExcpetionAfterRemove()
    {
        subListLIterator.next();
        subListLIterator.remove();
        subListLIterator.set("6");
    }

    /**
     * Test method to verify that set() throws IllegalStateException after add() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly throws IllegalStateException when called
     * after an add() operation without an intervening next() or previous() call.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling for set() calls following
     * add() operations in sublist iteration.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test calls next() to position the iterator, then add() to insert a new element,
     * then attempts to call set() without an intervening navigation operation, expecting
     * IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- HListIterator is created for the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- New element remains inserted in sublist</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() should throw IllegalStateException when called after add() operation
     * without an intervening next() or previous() call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListSetThrowsExceptionAfterAdd()
    {
        subListLIterator.next();
        subListLIterator.add("6");
        subListLIterator.set("7");
    }

    /**
     * Test method to verify multiple set operations on sublist elements using list iterator.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple consecutive set operations work correctly on a sublist
     * through its list iterator, ensuring that each modification is properly applied to the underlying collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the set operation functionality when called multiple times
     * in sequence on different elements of the sublist, ensuring that each set operation correctly
     * replaces the intended element without affecting other operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator to the first two elements, performs set operations on both,
     * and verifies that both elements are correctly modified in the sublist while maintaining
     * the integrity of other elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4 (elements ["2", "3", null])</p>
     * <p>- HListIterator positioned at beginning of sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First two elements of sublist are modified to "6" and "7"</p>
     * <p>- Iterator remains in valid state after operations</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Both set operations should successfully modify their respective elements in the sublist.</p>
     */
    @Test
    public void testSubListMultipleSetOperations()
    {
        subListLIterator.next();
        subListLIterator.set("6");
        subListLIterator.next();
        subListLIterator.set("7");

        assertEquals("First element should be modified", "6", subList.get(0));
        assertEquals("Second element should be modified", "7", subList.get(1));
    }

    /**
     * Test method to verify that set operation overrides previous set when called consecutively on same element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that when set is called multiple times on the same element without intermediate
     * operations, only the last set operation takes effect, overriding previous values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of consecutive set operations on the same element,
     * ensuring that the iterator correctly handles value replacement without creating duplicate or
     * intermediate states.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator, performs a set operation, moves back and performs another set
     * operation on the same element, then verifies that only the last set value is present in the collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     * <p>- HListIterator positioned at beginning of sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Second element of sublist contains the last set value "7"</p>
     * <p>- Previous set value "6" is not present in the collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Consecutive set operations on the same element should result in only the last value being retained.</p>
     */
    @Test
    public void testSubListOverrideWithSet()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.set("6");

        subListLIterator.previous();
        subListLIterator.set("7");

        assertEquals("Next element should be the latest added", "7", subListLIterator.next());
        assertFalse("First setted element should not appear", subList.contains("6"));
        assertEquals("Second element should be the latest setted", "7", subList.get(1));
    }

    /**
     * Test method to verify add operation at the beginning of a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element at the beginning of a sublist using list iterator
     * correctly inserts the element and maintains proper iterator positioning.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the add operation when performed at the very beginning of
     * the sublist, ensuring proper size increase, element placement, and iterator behavior.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test adds an element at the beginning of the sublist without advancing the iterator,
     * then verifies the new element is correctly inserted at position 0 and that subsequent next()
     * returns the original first element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     * <p>- HListIterator positioned at beginning of sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases by 1</p>
     * <p>- New element "6" is inserted at position 0</p>
     * <p>- Original elements are shifted right</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Add operation should insert element at beginning, increase size, and maintain correct iterator positioning.</p>
     */
    @Test
    public void testSubListAddAtTheBeginning()
    {
        subListLIterator.add("6");

        assertEquals("Size should increase by 1", 4, subList.size());
        assertEquals("First element should be the new element", "6", subList.get(0));
        assertEquals("Next should return original first element", "2", subListLIterator.next());
    }

    /**
     * Test method to verify add operation in the middle of a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element in the middle of a sublist correctly inserts
     * the element at the specified position and maintains collection integrity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate add operation functionality when performed in the middle
     * of the collection, ensuring proper element shifting and iterator behavior.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator to the middle position, adds a new element, then verifies
     * the element is correctly inserted and subsequent elements are properly shifted.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     * <p>- HListIterator advanced to position 1</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases by 1</p>
     * <p>- New element "6" is inserted at position 2</p>
     * <p>- Subsequent elements are shifted right</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Add operation should correctly insert element in middle position and shift subsequent elements.</p>
     */
    @Test
    public void testSubListAddInTheMiddle()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.add("6");

        assertEquals("Size should increase by 1", 4, subList.size());
        assertEquals("Element at index 2 should be new element", "6", subList.get(2));
        assertEquals("Next should return element after inserted", null, subListLIterator.next());
    }

    /**
     * Test method to verify add operation at the end of a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element at the end of a sublist correctly appends
     * the element and maintains proper iterator positioning.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate add operation functionality when performed at the end
     * of the collection, ensuring proper size increase and element placement.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test advances the iterator to the end, adds a new element, then verifies the element
     * is correctly appended and can be accessed through previous operation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     * <p>- HListIterator advanced to end position</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases by 1</p>
     * <p>- New element "6" is appended at the end</p>
     * <p>- Iterator can access new element through previous()</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Add operation should correctly append element at end and maintain iterator accessibility.</p>
     */
    @Test
    public void testSubListAddAtTheEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.add("6");

        assertEquals("Size should increase by 1", 4, subList.size());
        assertEquals("Last element should be new element", "6", subList.get(3));
        assertEquals("Previous should return new element", "6", subListLIterator.previous());
    }

    /**
     * Test method to verify multiple consecutive add operations on a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple consecutive add operations work correctly, maintaining
     * proper element ordering and collection integrity throughout the sequence.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of multiple add operations performed in
     * sequence, ensuring each insertion occurs at the correct position relative to iterator state.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test performs three add operations at different iterator positions and verifies
     * that all elements are inserted in correct order with proper size increase.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     * <p>- HListIterator positioned at beginning of sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases by 3</p>
     * <p>- Elements "6", "7", and "8" are inserted in correct positions</p>
     * <p>- Original elements maintain relative ordering</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Multiple add operations should correctly insert elements in sequence at proper positions.</p>
     */
    @Test
    public void testSubListMultipleAddOperations()
    {
        subListLIterator.add("6");
        subListLIterator.add("7");

        subListLIterator.next();

        subListLIterator.add("8");

        assertEquals("Size should increase by 3", 6, subList.size());
        assertEquals("Elements should be added in correct order", "6", subList.get(0));
        assertEquals("Elements should be added in correct order", "7", subList.get(1));
        assertEquals("Elements should be added in correct order", "8", subList.get(3));
    }

    /**
     * Test method to verify add operation on an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element to an empty sublist correctly creates
     * a single-element collection with proper iterator behavior.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of adding to an empty collection,
     * ensuring the operation correctly initializes the collection and maintains iterator consistency.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty sublist, adds an element, then verifies the collection
     * contains exactly one element that can be accessed through iterator operations.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty VectorAdapter created</p>
     * <p>- Empty SubListAdapter created from range 0-0</p>
     * <p>- HListIterator positioned at beginning of empty sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 1</p>
     * <p>- Single element "0" is added to collection</p>
     * <p>- Iterator can access element through previous()</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Add operation should successfully initialize empty collection with single element.</p>
     */
    @Test
    public void testSubListAddOnEmptyCollection()
    {

        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        subListLIter.add("0");

        assertEquals("Size should be 1", 1, sub.size());
        assertTrue("Should contain just one element", subListLIter.hasPrevious());
        assertEquals("Should return the added element", "0", subListLIter.previous());
    }

    /**
     * Test method to verify list iterator behavior when created from index 0 (beginning).
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created from the beginning index (0) has
     * correct initial state and navigation capabilities.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of a list iterator created from
     * the start position, ensuring proper index reporting and element access.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator from index 0 and verifies its initial state including
     * next/previous availability, index values, and correct element retrieval.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- List iterator created with correct initial state</p>
     * <p>- All index and navigation methods return expected values</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>List iterator created from beginning should have correct initial state and access to first element.</p>
     */
    @Test
    public void testSubListListIteratorFromIndexAtBeginning()
    {
        HListIterator subListLIter = subList.listIterator(0);

        assertTrue("Should have next at beginning", subListLIter.hasNext());
        assertFalse("Should not have previous at beginning", subListLIter.hasPrevious());
        assertEquals("Next index should be 0", 0, subListLIter.nextIndex());
        assertEquals("Previous index should be -1", -1, subListLIter.previousIndex());
        assertEquals("First element should be correct", "2", subListLIter.next());
    }

    /**
     * Test method to verify list iterator behavior when created from middle index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created from a middle index has correct
     * state with access to both previous and next elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state of a list iterator created from a
     * non-boundary position, ensuring bidirectional navigation works correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator from index 1 and verifies its state including
     * bidirectional navigation availability, correct index reporting, and element access in both directions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- List iterator created with correct middle position state</p>
     * <p>- Bidirectional navigation works correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>List iterator created from middle index should have access to both previous and next elements.</p>
     */
    @Test
    public void testSubListListIteratorFromIndexAtMiddle()
    {
        HListIterator subListLIter = subList.listIterator(1);

        assertTrue("Should have next at middle", subListLIter.hasNext());
        assertTrue("Should have previous at middle", subListLIter.hasPrevious());
        assertEquals("Next index should be 1", 1, subListLIter.nextIndex());
        assertEquals("Previous index should be 0", 0, subListLIter.previousIndex());
        assertEquals("Next element should be correct", "3", subListLIter.next());
        assertEquals("Previous element should be correct", "3", subListLIter.previous());
    }

    /**
     * Test method to verify list iterator behavior when created from end index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created from the end index has correct
     * state with access to previous elements but no next elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state of a list iterator created from the
     * end position, ensuring proper boundary condition handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a list iterator from the end index and verifies its state including
     * previous availability, absence of next elements, correct index reporting, and access to last element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- List iterator created with correct end position state</p>
     * <p>- Previous navigation works correctly</p>
     * <p>- Next navigation correctly reports no elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>List iterator created from end should have access to previous elements but no next elements.</p>
     */
    @Test
    public void testSubListListIteratorFromIndexAtEnd()
    {
        HListIterator subListLIter = subList.listIterator(subList.size());

        assertFalse("Should not have next at end", subListLIter.hasNext());
        assertTrue("Should have previous at end", subListLIter.hasPrevious());
        assertEquals("Next index should be size", subList.size(), subListLIter.nextIndex());
        assertEquals("Previous index should be size - 1", subList.size() - 1, subListLIter.previousIndex());
        assertNull("Last element should be correct", subListLIter.previous());
    }

    /**
     * Test method to verify that list iterator creation with negative index throws exception.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that creating a list iterator with a negative index correctly
     * throws IndexOutOfBoundsException, preventing invalid iterator creation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling when attempting to create
     * a list iterator from an invalid negative index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a list iterator with index -1 and expects an
     * IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- No iterator is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Creating list iterator with negative index should throw IndexOutOfBoundsException.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListListIteratorFromIndexNegativeIndex()
    {
        subList.listIterator(-1);
    }

    /**
     * Test method to verify that list iterator creation with index greater than size throws exception.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that creating a list iterator with an index greater than the
     * collection size correctly throws IndexOutOfBoundsException.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling when attempting to create
     * a list iterator from an index beyond the valid range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a list iterator with index size+1 and expects an
     * IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4 (size = 3)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- No iterator is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Creating list iterator with index greater than size should throw IndexOutOfBoundsException.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListListIteratorFromIndexIndexGreaterThanSize()
    {
        subList.listIterator(subList.size() + 1);
    }

    /**
     * Test method to verify that list iterator from index behaves correctly on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a list iterator created from index 0 on an empty sublist correctly
     * reports no next/previous elements and returns appropriate index values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of list iterators on empty collections,
     * ensuring proper handling of boundary conditions and correct index reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, then creates an empty sublist from it (range 0-0).
     * A list iterator is created from index 0 on this empty sublist. The test verifies that hasNext()
     * and hasPrevious() return false, and that nextIndex() returns 0 while previousIndex() returns -1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- SubListAdapter is created from indices 0 to 0 (empty range)</p>
     * <p>- HListIterator is created from index 0 on the empty sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All iterator methods return expected values for empty collection</p>
     * <p>- No elements are accessible through the iterator</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() and hasPrevious() return false, nextIndex() returns 0, previousIndex() returns -1.</p>
     */
    @Test
    public void testSubListListIteratorFromIndexEmptyVector()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator(0);

        assertFalse("Collection should not have next", subListLIter.hasNext());
        assertFalse("Collection should not have previous", subListLIter.hasPrevious());
        assertEquals("Next index should be 0", 0, subListLIter.nextIndex());
        assertEquals("Previous index should be -1", -1, subListLIter.previousIndex());
    }

    /**
     * Test method to verify forward iteration from specific index in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that forward iteration from a specific index in a sublist correctly
     * returns the expected elements in sequence.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's ability to start iteration from an arbitrary index
     * and traverse forward through the sublist elements correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 1 of the sublist (which contains ["2", "3", null]).
     * The test verifies that next() returns "3" (element at index 1), then null (element at index 2),
     * and finally that hasNext() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 1 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator correctly returns elements from starting index onward</p>
     * <p>- Iterator reaches end of collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>First next() returns "3", second next() returns null, hasNext() returns false after iteration.</p>
     */
    @Test
    public void testSubListListIteratorFromIndexForwardIteration()
    {
        HListIterator subListLIter = subList.listIterator(1);

        assertEquals("First element from index 1", "3", subListLIter.next());
        assertNull("Second element from index 1", subListLIter.next());
        assertFalse("Should have no more elements", subListLIter.hasNext());
    }

    /**
     * Test method to verify backward iteration from specific index in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that backward iteration from a specific index in a sublist correctly
     * returns the expected elements in reverse sequence.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's ability to start iteration from an arbitrary index
     * and traverse backward through the sublist elements correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 3 (end) of the sublist (which contains ["2", "3", null]).
     * The test verifies that previous() returns null (element at index 2), then "3" (element at index 1),
     * then "2" (element at index 0), and finally that hasPrevious() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 3 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator correctly returns elements backward from starting index</p>
     * <p>- Iterator reaches beginning of collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>First previous() returns null, second returns "3", third returns "2", hasPrevious() returns false.</p>
     */
    @Test
    public void testSubListListIteratorFromIndexBackwardIteration()
    {
        HListIterator subListLIter = subList.listIterator(3);

        assertNull("First element backward from index 3", subListLIter.previous());
        assertEquals("Second element backward from index 3", "3", subListLIter.previous());
        assertEquals("Third element backward from index 3", "2", subListLIter.previous());
        assertFalse("Should have no more previous elements", subListLIter.hasPrevious());
    }

    /**
     * Test method to verify bidirectional iteration from middle index in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that bidirectional iteration from a middle index in a sublist works correctly,
     * allowing both forward and backward traversal from the starting position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's ability to traverse in both directions from an arbitrary
     * starting point, ensuring proper element sequencing in both directions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 2 (middle) of the sublist (which contains ["2", "3", null]).
     * The test performs forward iteration (to end), then backward iteration (to beginning), then forward
     * again, verifying correct element sequence at each step.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 2 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Iterator correctly traverses both forward and backward from starting point</p>
     * <p>- All elements are accessed in correct sequence in both directions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Correct element sequence in both forward and backward directions from middle index.</p>
     */
    @Test
    public void testSubListListIteratorFromIndexBidirectionalIteration()
    {
        HListIterator subListLIter = subList.listIterator(2);

        assertNull("Next should be null", subListLIter.next());
        assertFalse("Should have no more next elements", subListLIter.hasNext());

        assertEquals("Previous should be null", null, subListLIter.previous());
        assertEquals("Previous should be 3", "3", subListLIter.previous());

        assertEquals("Previous should be 2", "2", subListLIter.previous());
        assertFalse("Should have no more previous elements", subListLIter.hasPrevious());

        assertEquals("Next should be 2", "2", subListLIter.next());
        assertEquals("Next should be 3", "3", subListLIter.next());
        assertNull("Next should be null", subListLIter.next());
    }

    /**
     * Test method to verify element removal after next() operation from specific index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove operation works correctly when called after next()
     * from a specific starting index in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's remove functionality when starting from an arbitrary
     * index, ensuring proper element removal and collection state update.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 1 of the sublist. next() is called to advance
     * to element "3", then remove() is called. The test verifies that the sublist size decreases,
     * the element is properly removed, and the collection no longer contains the removed element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 1 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "3" is removed from sublist</p>
     * <p>- Sublist size decreases by 1</p>
     * <p>- Remaining elements shift appropriately</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Element "3" is removed, size becomes 2, element at index 1 becomes null.</p>
     */
    @Test
    public void testSubListListIteratorFromIndexRemoveAfterNext()
    {
        HListIterator subListLIter = subList.listIterator(1);

        subListLIter.next();
        subListLIter.remove();

        assertEquals("Size should decrease", 2, subList.size());
        assertEquals("Element at index 1 should be null", null, subList.get(1));
        assertFalse("Should not contain removed element", subList.contains("3"));
    }

    /**
     * Test method to verify element removal after previous() operation from specific index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove operation works correctly when called after previous()
     * from a specific starting index in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's remove functionality when moving backward from an
     * arbitrary starting index, ensuring proper element removal and collection state update.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 3 (end) of the sublist. previous() is called
     * to move to element null, then remove() is called. The test verifies that the sublist size decreases,
     * the null element is properly removed, and the collection no longer contains null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 3 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Null element is removed from sublist</p>
     * <p>- Sublist size decreases by 1</p>
     * <p>- Remaining elements shift appropriately</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Null element is removed, size becomes 2, element at index 1 becomes "3".</p>
     */
    @Test
    public void testSubListListIteratorFromIndexRemoveAfterPrevious()
    {
        HListIterator subListLIter = subList.listIterator(3);

        subListLIter.previous();
        subListLIter.remove();

        assertEquals("Size should decrease", 2, subList.size());
        assertEquals("Element at index 1 should be 3", "3", subList.get(1));
        assertFalse("Should not contain null element", subList.contains(null));
    }

    /**
     * Test method to verify element modification after next() operation from specific index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set operation works correctly when called after next()
     * from a specific starting index in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's set functionality when starting from the beginning
     * and moving forward, ensuring proper element modification.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 0 of the sublist. next() is called to advance
     * to element "2", then set("6") is called to replace it. The test verifies that the element is
     * properly modified and that subsequent next() calls return the correct elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 0 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "2" is replaced with "6"</p>
     * <p>- Iterator continues to function correctly</p>
     * <p>- Subsequent elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Element at index 0 becomes "6", next() returns "3".</p>
     */
    @Test
    public void testSubListListIteratorFromIndexSetAfterNext()
    {
        HListIterator subListLIter = subList.listIterator(0);

        subListLIter.next();
        subListLIter.set("6");

        assertEquals("Element should be modified", "6", subList.get(0));
        assertEquals("Next should return next element", "3", subListLIter.next());
    }

    /**
     * Test method to verify element modification after previous() operation from specific index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set operation works correctly when called after previous()
     * from a specific starting index in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's set functionality when starting from the middle
     * and moving backward, ensuring proper element modification.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 2 of the sublist. previous() is called
     * to move to element "3", then set("6") is called to replace it. The test verifies that the
     * element is properly modified and that subsequent next() calls return the modified element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 2 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "3" is replaced with "6"</p>
     * <p>- Iterator continues to function correctly</p>
     * <p>- Subsequent elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Element at index 1 becomes "6", next() returns "6".</p>
     */
    @Test
    public void testSubListListIteratorFromIndexSetAfterPrevious()
    {
        HListIterator subListLIter = subList.listIterator(2);

        subListLIter.previous();
        subListLIter.set("6");

        assertEquals("Element should be modified", "6", subList.get(1));
        assertEquals("Next should return modified element", "6", subListLIter.next());
    }

    /**
     * Test method to verify element insertion at beginning from specific index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add operation works correctly when inserting at the beginning
     * of the sublist from a specific starting index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's add functionality when inserting at position 0,
     * ensuring proper element insertion and collection state update.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 0 of the sublist. add("6") is called to
     * insert a new element at the beginning. The test verifies that the sublist size increases,
     * the new element is properly inserted, and subsequent next() calls return the correct elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 0 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- New element "6" is inserted at index 0</p>
     * <p>- Sublist size increases by 1</p>
     * <p>- Original elements shift right appropriately</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Size becomes 4, element at index 0 is "6", next() returns original first element "2".</p>
     */
    @Test
    public void testSubListListIteratorFromIndexAddAtTheBeginning()
    {
        HListIterator subListLIter = subList.listIterator(0);

        subListLIter.add("6");

        assertEquals("Size should increase", 4, subList.size());
        assertEquals("New element should be at index 0", "6", subList.get(0));
        assertEquals("Next should return original element", "2", subListLIter.next());
    }

    /**
     * Test method to verify element insertion at end from specific index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add operation works correctly when inserting at the end
     * of the sublist from a specific starting index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the list iterator's add functionality when inserting at the end position,
     * ensuring proper element insertion and collection state update.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from the end index (size) of the sublist. add("6")
     * is called to append a new element. The test verifies that the sublist size increases, the
     * new element is properly appended, and previous() calls return the new element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 3 (end) on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- New element "6" is appended at the end</p>
     * <p>- Sublist size increases by 1</p>
     * <p>- Original elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Size becomes 4, element at index 3 is "6", previous() returns "6".</p>
     */
    @Test
    public void testSubListListIteratorFromIndexAddAtEnd()
    {
        HListIterator subListLIter = subList.listIterator(subList.size());

        subListLIter.add("6");

        assertEquals("Size should increase", 4, subList.size());
        assertEquals("New element should be at the end", "6", subList.get(3));
        assertEquals("Previous should return new element", "6", subListLIter.previous());
    }

    /**
     * Test method to verify exception when remove() is called without prior next()/previous().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called without a
     * preceding next() or previous() operation from a specific starting index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper exception handling for illegal remove operations, ensuring
     * that the iterator maintains correct state management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 1 of the sublist. remove() is called
     * immediately without any prior navigation operation. The test expects IllegalStateException
     * to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 1 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Iterator state remains unchanged</p>
     * <p>- Collection remains unmodified</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() throws IllegalStateException when called without prior next()/previous().</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListListIteratorFromIndexRemoveWithoutNextPrevious()
    {
        HListIterator subListLIter = subList.listIterator(1);
        subListLIter.remove();
    }

    /**
     * Test method to verify exception when set() is called without prior next()/previous().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() throws IllegalStateException when called without a
     * preceding next() or previous() operation from a specific starting index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper exception handling for illegal set operations, ensuring
     * that the iterator maintains correct state management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>A list iterator is created starting from index 1 of the sublist. set("6") is called
     * immediately without any prior navigation operation. The test expects IllegalStateException
     * to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HListIterator is created from index 1 on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Iterator state remains unchanged</p>
     * <p>- Collection remains unmodified</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set() throws IllegalStateException when called without prior next()/previous().</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListListIteratorFromIndexSetWithoutNextPrevious()
    {
        HListIterator subListLIter = subList.listIterator(1);
        subListLIter.set("6");
    }
}
