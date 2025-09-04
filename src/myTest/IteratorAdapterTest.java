package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HIterator;
import myAdapter.IteratorAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for the IteratorAdapter class.
 *
 * <p>This test suite verifies the functionality of the IteratorAdapter class,
 * which implements the HIterator interface to iterate over elements of a
 * VectorAdapter or SubListAdapter. The tests cover various scenarios including
 * normal iteration, element removal, edge cases, and error conditions.</p>
 *
 * <h2>Test Case Design</h2>
 * <p>The test cases are designed to validate the behavior of IteratorAdapter
 * against the specifications of Java 1.4.2 Iterator interface. The test design
 * includes categories for basic functionality, edge cases, error conditions,
 * and sublist-specific behavior.</p>
 *
 * <p>Categories:</p>
 * <ul>
 *   <li>Basic functionality tests</li>
 *   <li>Empty collection tests</li>
 *   <li>Null element tests</li>
 *   <li>Duplicate element tests</li>
 *   <li>Element removal tests</li>
 *   <li>SubList iterator tests</li>
 *   <li>Nested SubList iterator tests</li>
 *   <li>Exception handling tests</li>
 * </ul>
 *
 * @author   Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see      IteratorAdapter
 * @see      HIterator
 */
public class IteratorAdapterTest
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
     * HIterator instance for iterating over the main vector.
     * Created from the vector.iterator() method.
     */
    private HIterator iterator;

    /**
     * HIterator instance for iterating over the sublist.
     * Created from the subList.iterator() method.
     */
    private HIterator subIterator;

    /**
     * Default constructor
     */
    public IteratorAdapterTest()
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

        iterator = vector.iterator();

        subList = (SubListAdapter) vector.subList(1, 4);
        subIterator = subList.iterator();
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
        iterator = null;
        subIterator = null;
    }

    /**
     * Test method to verify that hasNext() returns true at the beginning of iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns true when called at the beginning of iteration
     * on a non-empty collection, ensuring proper detection of available elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic functionality of hasNext() method at the start
     * of iteration. This ensures that iterators correctly identify available elements before
     * any elements have been consumed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with predefined elements and obtains an iterator.
     * Without calling next(), it immediately checks if hasNext() returns true, indicating
     * that elements are available for iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- No next() calls have been made on the iterator</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns true</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return true when called at the beginning of iteration on a non-empty collection.</p>
     */
    @Test
    public void testHasNextAtTheBeginning()
    {
        assertTrue("Collection should have been created and filled properly",
            iterator.hasNext());
    }

    /**
     * Test method to verify that hasNext() returns true in the middle of iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns true when called in the middle of iteration,
     * ensuring continuous detection of available elements during traversal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate hasNext() behavior after partially consuming elements
     * from the iterator. This ensures the method correctly reports remaining elements
     * throughout the iteration process.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * It calls next() three times to advance the iterator position, then checks if
     * hasNext() still returns true, indicating more elements are available.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- next() has been called three times</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns true</p>
     * <p>- Iterator position is at the fourth element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return true when called in the middle of iteration with remaining elements.</p>
     */
    @Test
    public void testHasNextInTheMiddle()
    {
        iterator.next();
        iterator.next();
        iterator.next();

        assertTrue("Collection should have some more elements", iterator.hasNext());
    }

    /**
     * Test method to verify that hasNext() returns false at the end of iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns false after all elements have been consumed,
     * ensuring proper detection of iteration completion.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the terminal behavior of hasNext() method.
     * This ensures that iterators correctly report when no more elements are available
     * after full traversal of the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * It iterates through all elements using a while loop with hasNext() and next(),
     * then checks if hasNext() returns false after exhaustion.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- All elements have been consumed by calling next() until hasNext() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns false</p>
     * <p>- Iterator position is at the end of the collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false after all elements have been consumed from the iterator.</p>
     */
    @Test
    public void testHasNextAtTheEnd()
    {
        while(iterator.hasNext())
            iterator.next();

        assertFalse("hasNext should return false after full emptying iteration",
            iterator.hasNext());
    }

    /**
     * Test method to verify proper sequential element retrieval using next().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() returns elements in the correct order during iteration,
     * ensuring proper sequential access to collection elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the fundamental iteration functionality.
     * This ensures that elements are returned in the proper sequence and that
     * the iterator maintains correct position throughout traversal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with mixed elements (including null) and obtains an iterator.
     * It calls next() sequentially and verifies each returned element matches the expected value
     * in the correct order. Finally, it confirms that hasNext() returns false after all elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All elements are returned in correct sequence: "1", "2", "3", null, "5"</p>
     * <p>- hasNext() returns false after the last element</p>
     * <p>- Iterator position is at the end of the collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should return elements in the correct order, and hasNext() should return false
     * after all elements have been consumed.</p>
     */
    @Test
    public void testNextProperIteration()
    {
        assertEquals("First element should match", "1", iterator.next());
        assertEquals("Second element should match", "2", iterator.next());
        assertEquals("Third element should match", "3", iterator.next());
        assertNull("Fourth element should be null", iterator.next());
        assertEquals("Fifth element should match", "5", iterator.next());

        assertFalse("Collection should still no more elements", iterator.hasNext());
    }

    /**
     * Test method to verify that next() throws NoSuchElementException after iteration completion.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() throws NoSuchElementException when called after all
     * elements have been consumed, ensuring proper exception handling at iteration end.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the boundary condition
     * of calling next() on an exhausted iterator. This ensures robustness and proper
     * error reporting when attempting to access elements beyond collection limits.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * It consumes all elements using a while loop, then attempts to call next() again,
     * expecting a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- All elements have been consumed by calling next() until hasNext() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Iterator remains in exhausted state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called on an exhausted iterator.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsException()
    {
        while(iterator.hasNext())
            iterator.next();

        iterator.next();
    }

    /**
     * Test method to verify that hasNext() returns false on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns false when called on an iterator
     * created from an empty collection, ensuring proper handling of empty collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate iterator behavior with empty collections.
     * This ensures that hasNext() correctly identifies the absence of elements
     * without throwing exceptions or returning incorrect values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and obtains an iterator.
     * It immediately checks if hasNext() returns false, indicating no elements
     * are available for iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with no elements</p>
     * <p>- HIterator is created from vector.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns false</p>
     * <p>- Iterator remains at initial position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false when called on an iterator from an empty collection.</p>
     */
    @Test
    public void testHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HIterator iter = vec.iterator();

        assertFalse("Empty collection should have no elements",
            iter.hasNext());
    }

    /**
     * Test method to verify that next() throws NoSuchElementException on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() throws NoSuchElementException when called on an iterator
     * created from an empty collection, ensuring proper exception handling for empty collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of empty collections.
     * This ensures robustness and proper error reporting when attempting to access elements
     * from an empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and obtains an iterator.
     * It immediately attempts to call next(), expecting a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with no elements</p>
     * <p>- HIterator is created from vector.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Iterator remains in initial state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called on an iterator from an empty collection.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        HIterator iter = vec.iterator();
        iter.next();
    }

    /**
     * Test method to verify proper iteration over collections with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that iterators correctly handle collections containing duplicate elements,
     * ensuring proper element retrieval regardless of value repetition.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate iterator behavior with homogeneous collections
     * containing duplicate values. This ensures that value repetition doesn't affect
     * the iteration process or element counting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three identical string elements "0".
     * It obtains an iterator and verifies that next() returns "0" three times in sequence,
     * and that hasNext() returns false after the third element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["0", "0", "0"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Three elements are returned, all with value "0"</p>
     * <p>- hasNext() returns false after the third element</p>
     * <p>- Iterator position is at the end of the collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should return all duplicate elements in sequence, and hasNext() should return false
     * after all elements have been consumed.</p>
     */
    @Test
    public void testNextDuplicateElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("0");
        vec.add("0");

        HIterator iter = vec.iterator();

        assertEquals("First element should be '0'", "0", iter.next());
        assertEquals("Second element should be '0'", "0", iter.next());
        assertEquals("Third element should be '0'", "0", iter.next());

        assertFalse("Should have no more elements", iter.hasNext());
    }

    /**
     * Test method to verify proper iteration over collections with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that iterators correctly handle collections containing null elements,
     * ensuring proper retrieval and handling of null values during iteration.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate iterator behavior with collections containing
     * null values. This ensures that null elements are properly returned by next()
     * and don't interfere with the iteration process.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three null elements.
     * It obtains an iterator and verifies that hasNext() returns true initially,
     * that next() returns null three times in sequence, and that hasNext() returns
     * false after the third element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: [null, null, null]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Three elements are returned, all with value null</p>
     * <p>- hasNext() returns false after the third element</p>
     * <p>- Iterator position is at the end of the collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should return null for each null element, and hasNext() should correctly
     * report available elements regardless of null values.</p>
     */
    @Test
    public void testNextNull()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        HIterator iter = vec.iterator();

        assertTrue("Should have elements", iter.hasNext());
        assertNull("First element should be null", iter.next());
        assertNull("Second element should be null", iter.next());
        assertNull("Third element should be null", iter.next());
        assertFalse("Should have no more elements", iter.hasNext());
    }

    /**
     * Test method to verify proper element removal at the beginning of iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the first element when called
     * after the first next() call, ensuring proper element removal and collection modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal functionality at the start of iteration.
     * This ensures that the iterator properly handles structural modifications and
     * maintains collection integrity when removing the first element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * It calls next() once to position at the first element, then calls remove() to delete it.
     * The test verifies that the collection size decreases, the first element changes,
     * and the removed element is no longer present.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- next() has been called once, positioning at the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size decreases from 5 to 4</p>
     * <p>- First element becomes "2" instead of "1"</p>
     * <p>- Element "1" is no longer in the collection</p>
     * <p>- Iterator position is adjusted accordingly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the first element, decrease collection size,
     * and maintain proper collection structure and iterator position.</p>
     */
    @Test
    public void testRemoveAtTheBeginning()
    {
        iterator.next();
        iterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertEquals("First element should be '2'", "2", vector.get(0));
        assertFalse("Should not contain removed element", vector.contains("1"));
    }

    /**
     * Test method to verify proper element removal in the middle of iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes a middle element when called
     * after multiple next() calls, ensuring proper element removal and collection modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal functionality in the middle of iteration.
     * This ensures that the iterator properly handles structural modifications and
     * maintains collection integrity when removing elements from the middle.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * It calls next() three times to position at the third element ("3"), then calls remove()
     * to delete it. The test verifies that the collection size decreases, the element
     * at position 2 becomes null, and the removed element is no longer present.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- next() has been called three times, positioning at the third element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size decreases from 5 to 4</p>
     * <p>- Element at index 2 becomes null instead of "3"</p>
     * <p>- Element "3" is no longer in the collection</p>
     * <p>- Iterator position is adjusted accordingly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the middle element, decrease collection size,
     * and maintain proper collection structure and iterator position.</p>
     */
    @Test
    public void testRemoveInTheMiddle()
    {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertNull("Third element should be null", vector.get(2));
        assertFalse("Should not contain removed element", vector.contains("3"));
    }

    /**
     * Test method to verify proper element removal at the end of iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the last element when called
     * after consuming all elements, ensuring proper element removal and collection modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal functionality at the end of iteration.
     * This ensures that the iterator properly handles structural modifications and
     * maintains collection integrity when removing the last element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * It iterates through all elements using a while loop, then calls remove() to delete
     * the last returned element ("5"). The test verifies that the collection size decreases,
     * the last element becomes null, and the removed element is no longer present.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- All elements have been consumed by calling next() until hasNext() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size decreases from 5 to 4</p>
     * <p>- Last element becomes null instead of "5"</p>
     * <p>- Element "5" is no longer in the collection</p>
     * <p>- Iterator position remains at the end</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the last element, decrease collection size,
     * and maintain proper collection structure.</p>
     */
    @Test
    public void testRemoveAtTheEnd()
    {
        while(iterator.hasNext())
            iterator.next();

        iterator.remove();

        assertEquals("Size should be 4", 4, vector.size());
        assertEquals("Last element should now be null", null, vector.get(3));
        assertFalse("Should not contain the removed element", vector.contains("5"));
    }

    /**
     * Test method to verify that remove() throws IllegalStateException without prior next() call.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called without
     * a preceding next() call, ensuring proper usage of the iterator remove operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for improper remove() usage.
     * This ensures that the iterator enforces the proper sequence of operations and
     * prevents invalid removal attempts.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * Without calling next() first, it immediately attempts to call remove(), expecting
     * an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- No next() calls have been made</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection remains unchanged</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called without a preceding next() call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionWithoutNext()
    {
        iterator.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException when called consecutively.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called twice
     * without an intervening next() call, ensuring proper usage of the iterator remove operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for consecutive remove() calls.
     * This ensures that the iterator enforces the proper sequence of operations and
     * prevents multiple consecutive removals without repositioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * It calls next() once to position at the first element, calls remove() to delete it,
     * then immediately attempts to call remove() again without an intervening next() call,
     * expecting an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- next() has been called once and remove() has been called once</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown on second remove() call</p>
     * <p>- Collection contains 4 elements after first removal</p>
     * <p>- Iterator position remains after first removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called consecutively without
     * an intervening next() call.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterRemove()
    {
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    /**
     * Test method to verify proper removal of multiple elements during iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly handles multiple element removals
     * during iteration, ensuring proper collection modification and iterator behavior.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate multiple removal operations during a single iteration.
     * This ensures that the iterator properly handles consecutive structural modifications
     * and maintains correct positioning throughout the process.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements and obtains an iterator.
     * It removes the first three elements in sequence by calling next() followed by remove()
     * three times. The test verifies that the collection size decreases appropriately
     * and the remaining elements are in the expected positions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size decreases from 5 to 2</p>
     * <p>- First element becomes null</p>
     * <p>- Second element becomes "5"</p>
     * <p>- Elements "1", "2", and "3" are no longer in the collection</p>
     * <p>- Iterator position is after the third removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete multiple elements in sequence, properly
     * decrease collection size, and maintain correct collection structure.</p>
     */
    @Test
    public void testRemoveMultipleElements()
    {
        iterator.next();
        iterator.remove();

        iterator.next();
        iterator.remove();

        iterator.next();
        iterator.remove();

        assertEquals("Size should be 2", 2, vector.size());
        assertNull("First element should be null", vector.get(0));
        assertEquals("Second element should be '5'", "5", vector.get(1));
    }

    /**
     * Test method to verify that remove() throws IllegalStateException on empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called on
     * an iterator from an empty collection, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for remove() operation
     * on empty collections. This ensures robustness and proper error reporting when
     * attempting removal from an empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and obtains an iterator.
     * It immediately attempts to call remove(), expecting an IllegalStateException
     * to be thrown since no elements are available for removal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with no elements</p>
     * <p>- HIterator is created from vector.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Collection remains empty</p>
     * <p>- Iterator remains in initial state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called on an iterator
     * from an empty collection.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HIterator iter = vec.iterator();

        iter.remove();
    }

    /**
     * Test method to verify proper removal of duplicate elements during iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly handles removal of duplicate elements,
     * ensuring proper collection modification regardless of value repetition.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal functionality with duplicate values.
     * This ensures that the iterator properly handles element removal based on position
     * rather than value, and maintains collection integrity with duplicate elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three identical string elements "0".
     * It obtains an iterator, calls next() to position at the first element, then
     * calls remove() to delete it. The test verifies that the collection size decreases
     * and the remaining elements maintain their duplicate values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["0", "0", "0"]</p>
     * <p>- HIterator is created from vector.iterator()</p>
     * <p>- next() has been called once, positioning at the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Collection size decreases from 3 to 2</p>
     * <p>- Both remaining elements have value "0"</p>
     * <p>- Iterator position is after the first element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the first occurrence of duplicate elements,
     * decrease collection size, and maintain the remaining duplicate values.</p>
     */
    @Test
    public void testRemoveDuplicateElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("0");
        vec.add("0");

        HIterator iter = vec.iterator();
        iter.next();
        iter.remove();

        assertEquals("Size should be 2", 2, vec.size());
        assertEquals("Both remaining elements should be 'duplicate'", "0", vec.get(0));
        assertEquals("Both remaining elements should be 'duplicate'", "0", vec.get(1));
    }

    /**
     * Test method to verify that hasNext() returns true at the beginning of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns true when called at the beginning of iteration
     * on a sublist iterator, ensuring proper detection of available elements in sublists.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic functionality of hasNext() method
     * for sublist iterators. This ensures that sublist iterators correctly identify
     * available elements before any elements have been consumed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. Without calling next(), it immediately checks if
     * hasNext() returns true, indicating that elements are available for iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- No next() calls have been made on the sublist iterator</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns true</p>
     * <p>- Sublist iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return true when called at the beginning of iteration
     * on a non-empty sublist iterator.</p>
     */
    @Test
    public void testSubListHasNextAtTheBeginning()
    {
        assertTrue("SubList iterator should have next elements",
            subIterator.hasNext());
    }

    /**
     * Test method to verify that hasNext() returns true in the middle of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns true when called in the middle of iteration
     * on a sublist iterator, ensuring continuous detection of available elements during traversal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate hasNext() behavior after partially consuming elements
     * from a sublist iterator. This ensures that sublist iterators correctly report
     * remaining elements throughout the iteration process.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. It calls next() twice to advance the iterator position,
     * then checks if hasNext() still returns true, indicating more elements are available.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- next() has been called twice</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns true</p>
     * <p>- Sublist iterator position is at the third element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return true when called in the middle of iteration
     * with remaining elements in the sublist.</p>
     */
    @Test
    public void testSubListHasNextInTheMiddle()
    {
        subIterator.next();
        subIterator.next();

        assertTrue("SubList should have more elements", subIterator.hasNext());
    }

    /**
     * Test method to verify that hasNext() returns false at the end of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns false after all elements have been consumed
     * from a sublist iterator, ensuring proper detection of iteration completion in sublists.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the terminal behavior of hasNext() method
     * for sublist iterators. This ensures that sublist iterators correctly report
     * when no more elements are available after full traversal of the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. It iterates through all elements using a while loop
     * with hasNext() and next(), then checks if hasNext() returns false after exhaustion.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- All elements have been consumed by calling next() until hasNext() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns false</p>
     * <p>- Sublist iterator position is at the end of the sublist</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false after all elements have been consumed from
     * the sublist iterator.</p>
     */
    @Test
    public void testSubListHasNextAtTheEnd()
    {
        while(subIterator.hasNext())
            subIterator.next();

        assertFalse("SubList collection should return false after full emptying iteration",
            subIterator.hasNext());
    }

    /**
     * Test method to verify proper sequential element retrieval using next() on sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() returns elements in the correct order during
     * sublist iteration, ensuring proper sequential access to sublist elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the fundamental iteration functionality
     * for sublist iterators. This ensures that elements are returned in the proper
     * sequence and that the iterator maintains correct position throughout traversal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. It calls next() sequentially and verifies each
     * returned element matches the expected value in the correct order. Finally,
     * it confirms that hasNext() returns false after all elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All sublist elements are returned in correct sequence: "2", "3", null</p>
     * <p>- hasNext() returns false after the last element</p>
     * <p>- Iterator position is at the end of the sublist</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should return sublist elements in the correct order, and hasNext()
     * should return false after all elements have been consumed.</p>
     */
    @Test
    public void testSubListNextProperIteration()
    {
        assertEquals("First element of subList should be '2'", "2", subIterator.next());
        assertEquals("Second element of subList should be '3'", "3", subIterator.next());
        assertNull("Third element of subList should be null", subIterator.next());

        assertFalse("Should have no more elements", subIterator.hasNext());
    }

    /**
     * Test method to verify that next() throws NoSuchElementException after sublist iteration completion.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() throws NoSuchElementException when called after all
     * elements have been consumed from a sublist iterator, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the boundary condition
     * of calling next() on an exhausted sublist iterator. This ensures robustness and
     * proper error reporting when attempting to access elements beyond sublist limits.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. It consumes all elements using a while loop, then
     * attempts to call next() again, expecting a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- All elements have been consumed by calling next() until hasNext() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Sublist iterator remains in exhausted state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called on an exhausted
     * sublist iterator.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsException()
    {
        while(subIterator.hasNext())
            subIterator.next();

        subIterator.next();
    }

    /**
     * Test method to verify that hasNext() returns false on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns false when called on an iterator
     * created from an empty sublist, ensuring proper handling of empty sublists.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate iterator behavior with empty sublists.
     * This ensures that hasNext() correctly identifies the absence of elements
     * without throwing exceptions or returning incorrect values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty SubListAdapter (from index 0 to 0) from the main vector
     * and obtains an iterator from the empty sublist. It immediately checks if
     * hasNext() returns false, indicating no elements are available for iteration.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 0 to 0 (empty range)</p>
     * <p>- HIterator is created from subList.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns false</p>
     * <p>- Iterator remains at initial position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false when called on an iterator from an empty sublist.</p>
     */
    @Test
    public void testSubListHasNextOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HIterator subIter = sub.iterator();

        assertFalse("Empty subList should have no elements",
            subIter.hasNext());
    }

    /**
     * Test method to verify that next() throws NoSuchElementException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() throws NoSuchElementException when called on
     * an iterator created from an empty sublist, ensuring proper exception handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of
     * empty sublists. This ensures robustness and proper error reporting when
     * attempting to access elements from an empty sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty SubListAdapter (from index 0 to 0) from the main vector
     * and obtains an iterator from the empty sublist. It immediately attempts to
     * call next(), expecting a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 0 to 0 (empty range)</p>
     * <p>- HIterator is created from subList.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Iterator remains in initial state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called on an iterator
     * from an empty sublist.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HIterator subIter = sub.iterator();

        subIter.next();
    }

    /**
     * Test method to verify proper iteration over sublists with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that sublist iterators correctly handle collections containing
     * null elements, ensuring proper retrieval and handling of null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate iterator behavior with sublists containing
     * null values. This ensures that null elements are properly returned by next()
     * and don't interfere with the iteration process.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple null elements, then creates
     * a SubListAdapter covering indices 1 to 4 (three null elements). It obtains
     * an iterator from the sublist and verifies that hasNext() returns true initially,
     * that next() returns null three times in sequence, and that hasNext() returns
     * false after the third element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: [null, null, null, null]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: [null, null, null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Three elements are returned, all with value null</p>
     * <p>- hasNext() returns false after the third element</p>
     * <p>- Iterator position is at the end of the sublist</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should return null for each null element in the sublist, and hasNext()
     * should correctly report available elements regardless of null values.</p>
     */
    @Test
    public void testSubListNull()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);
        vec.add(null);

        SubListAdapter sub = (SubListAdapter) vec.subList(1, 4);
        HIterator subIter = sub.iterator();

        assertTrue("Should have elements", subIter.hasNext());
        assertNull("First element should be null", subIter.next());
        assertNull("Second element should be null", subIter.next());
        assertNull("Third element should be null", subIter.next());
        assertFalse("Should have no more elements", subIter.hasNext());
    }

    /**
     * Test method to verify proper element removal at the beginning of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the first element from a sublist
     * when called after the first next() call, ensuring proper element removal and
     * collection modification in both sublist and original vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal functionality at the start of
     * sublist iteration. This ensures that sublist iterators properly handle
     * structural modifications and maintain collection integrity across both
     * sublist and original vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. It calls next() once to position at the first
     * element ("2"), then calls remove() to delete it. The test verifies that both
     * the sublist size decreases and the element is removed from the original vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- next() has been called once, positioning at the first element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases from 3 to 2</p>
     * <p>- Element "2" is removed from both sublist and original vector</p>
     * <p>- Iterator position is adjusted accordingly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the first element from the sublist,
     * decrease sublist size, and remove the element from the original vector.</p>
     */
    @Test
    public void testSubListRemoveAtTheBeginning()
    {
        subIterator.next();
        subIterator.remove();

        assertEquals("SubList size should decrease", 2, subList.size());
        assertFalse("Element should be removed from subList", subList.contains("2"));
        assertFalse("Element should be removed from original vector", vector.contains("2"));
    }

    /**
     * Test method to verify proper element removal in the middle of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes a middle element from a sublist
     * when called after multiple next() calls, ensuring proper element removal and
     * collection modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal functionality in the middle of
     * sublist iteration. This ensures that sublist iterators properly handle
     * structural modifications and maintain collection integrity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. It calls next() twice to position at the second
     * element ("3"), then calls remove() to delete it. The test verifies that the
     * sublist size decreases and the element is properly removed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- next() has been called twice, positioning at the second element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases from 3 to 2</p>
     * <p>- Element "3" is removed from the sublist</p>
     * <p>- Iterator has more elements available</p>
     * <p>- Original vector reflects the removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the middle element from the sublist,
     * decrease sublist size, and maintain proper collection structure.</p>
     */
    @Test
    public void testSubListRemoveInTheMiddle()
    {
        subIterator.next();
        subIterator.next();
        subIterator.remove();

        assertEquals("SubList size should be 2", 2, subList.size());
        assertTrue("Should contain other elements", subIterator.hasNext());
        assertFalse("Should not contain '3'", subList.contains("3"));
    }


    /**
     * Test method to verify proper element removal at the end of sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes the last element from a sublist
     * when called after consuming all elements, ensuring proper element removal and
     * collection modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal functionality at the end of
     * sublist iteration. This ensures that sublist iterators properly handle
     * structural modifications when removing the last element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. It iterates through all elements using a while loop,
     * then calls remove() to delete the last returned element (null). The test verifies
     * that the sublist size decreases and the element is properly removed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- All elements have been consumed by calling next() until hasNext() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases from 3 to 2</p>
     * <p>- Null element is removed from the sublist</p>
     * <p>- Iterator has no more elements available</p>
     * <p>- Original vector reflects the removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete the last element from the sublist,
     * decrease sublist size, and maintain proper collection structure.</p>
     */
    @Test
    public void testSubListRemoveAnTheEnd()
    {
        while(subIterator.hasNext())
            subIterator.next();

        subIterator.remove();

        assertEquals("SubList size should be 2", 2, subList.size());
        assertFalse("Should not contain other elements", subIterator.hasNext());
        assertFalse("Should not contain null", subList.contains(null));
    }

    /**
     * Test method to verify that remove() throws IllegalStateException without prior next() call on sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called without
     * a preceding next() call on a sublist iterator, ensuring proper usage of the remove operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for improper remove() usage
     * on sublist iterators. This ensures that sublist iterators enforce the proper
     * sequence of operations and prevent invalid removal attempts.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. Without calling next() first, it immediately
     * attempts to call remove(), expecting an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- No next() calls have been made</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     * <p>- Iterator position remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called without a preceding
     * next() call on a sublist iterator.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionWithoutNext()
    {
        subIterator.remove();
    }

    /**
     * Test method to verify that remove() throws IllegalStateException when called consecutively on sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() throws IllegalStateException when called twice
     * without an intervening next() call on a sublist iterator, ensuring proper usage.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for consecutive remove() calls
     * on sublist iterators. This ensures that sublist iterators enforce the proper
     * sequence of operations and prevent multiple consecutive removals.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a SubListAdapter from the main vector (indices 1 to 4) and obtains
     * an iterator from the sublist. It calls next() once to position at the first element,
     * calls remove() to delete it, then immediately attempts to call remove() again
     * without an intervening next() call, expecting an IllegalStateException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- HIterator is created from subList.iterator()</p>
     * <p>- next() has been called once and remove() has been called once</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalStateException is thrown on second remove() call</p>
     * <p>- Sublist contains 2 elements after first removal</p>
     * <p>- Iterator position remains after first removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should throw IllegalStateException when called consecutively without
     * an intervening next() call on a sublist iterator.</p>
     */
    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionAfterRemove()
    {
        subIterator.next();
        subIterator.remove();
        subIterator.remove();
    }

    /**
     * Test method to verify proper element removal in nested sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that remove() correctly removes elements from nested sublists,
     * ensuring proper element removal and collection modification across multiple levels
     * of sublist hierarchy.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal functionality in deeply nested sublists.
     * This ensures that iterators properly handle structural modifications across
     * multiple levels of sublist hierarchy and maintain collection integrity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a two-level nested sublist structure: a sublist from indices 1-4
     * of the main vector, and a sub-sublist from indices 1-3 of the first sublist.
     * It obtains an iterator from the nested sublist, calls next() to position at the
     * first element, then calls remove() to delete it. The test verifies that the
     * nested sublist size decreases and the remaining elements are correct.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- First SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- Second SubListAdapter is created from indices 1 to 3 of first sublist: ["3", null]</p>
     * <p>- HIterator is created from the nested sublist iterator</p>
     * <p>- next() has been called once, positioning at the first element ("3")</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Nested sublist size decreases from 2 to 1</p>
     * <p>- Element "3" is removed from the nested sublist</p>
     * <p>- Nested sublist contains only null element</p>
     * <p>- Original vector and parent sublists reflect the removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should successfully delete elements from nested sublists, decrease
     * sublist size, and maintain proper collection structure across all hierarchy levels.</p>
     */
    @Test
    public void testNestedSubListRemove()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HIterator subIter = subSub.iterator();

        subIter.next();
        subIter.remove();

        assertEquals("Nested sublist size should be 1", 1, subSub.size());
        assertEquals("Nested sublist should contain only null", null, subSub.get(0));
    }

    /**
     * Test method to verify proper sequential element retrieval in nested sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() returns elements in the correct order during
     * nested sublist iteration, ensuring proper sequential access to elements across
     * multiple levels of sublist hierarchy.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the fundamental iteration functionality
     * for nested sublist iterators. This ensures that elements are returned in the
     * proper sequence and that the iterator maintains correct position throughout
     * traversal of nested sublists.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a two-level nested sublist structure: a sublist from indices 1-5
     * of the main vector, and a sub-sublist from indices 1-3 of the first sublist.
     * It obtains an iterator from the nested sublist and verifies that next() returns
     * elements in the correct order: "3" followed by null. Finally, it confirms that
     * hasNext() returns false after all elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- First SubListAdapter is created from indices 1 to 5: ["2", "3", null, "5"]</p>
     * <p>- Second SubListAdapter is created from indices 1 to 3 of first sublist: ["3", null]</p>
     * <p>- HIterator is created from the nested sublist iterator</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All nested sublist elements are returned in correct sequence: "3", null</p>
     * <p>- hasNext() returns false after the last element</p>
     * <p>- Iterator position is at the end of the nested sublist</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should return nested sublist elements in the correct order, and
     * hasNext() should return false after all elements have been consumed.</p>
     */
    @Test
    public void testNestedSubListProperIteration()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HIterator subIter = subSub.iterator();

        assertEquals("First element should be 3", "3", subIter.next());
        assertNull("Second element should be null", subIter.next());
        assertFalse("Should have no more elements", subIter.hasNext());
    }

    /**
     * Test method to verify that hasNext() returns false on empty nested sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that hasNext() returns false when called on an iterator
     * created from an empty nested sublist, ensuring proper handling of empty
     * sublists at multiple hierarchy levels.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate iterator behavior with empty nested sublists.
     * This ensures that hasNext() correctly identifies the absence of elements
     * without throwing exceptions or returning incorrect values across sublist levels.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a two-level nested sublist structure with an empty sub-sublist:
     * a sublist from indices 1-4 of the main vector, and an empty sub-sublist from
     * indices 1-1 of the first sublist. It obtains an iterator from the empty nested
     * sublist and immediately checks if hasNext() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- First SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- Second SubListAdapter is created from indices 1 to 1 (empty range) of first sublist</p>
     * <p>- HIterator is created from the empty nested sublist iterator</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hasNext() returns false</p>
     * <p>- Iterator remains at initial position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hasNext() should return false when called on an iterator from an empty
     * nested sublist.</p>
     */
    @Test
    public void testNestedSubListNextOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HIterator subIter = subSub.iterator();

        assertFalse("Empty sub-subList should have no elements", subIter.hasNext());
    }

    /**
     * Test method to verify that next() throws NoSuchElementException on empty nested sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() throws NoSuchElementException when called on
     * an iterator created from an empty nested sublist, ensuring proper exception
     * handling for empty sublists at multiple hierarchy levels.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of
     * empty nested sublists. This ensures robustness and proper error reporting when
     * attempting to access elements from empty nested sublists.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a two-level nested sublist structure with an empty sub-sublist:
     * a sublist from indices 1-4 of the main vector, and an empty sub-sublist from
     * indices 1-1 of the first sublist. It obtains an iterator from the empty nested
     * sublist and immediately attempts to call next(), expecting a NoSuchElementException
     * to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- First SubListAdapter is created from indices 1 to 4: ["2", "3", null]</p>
     * <p>- Second SubListAdapter is created from indices 1 to 1 (empty range) of first sublist</p>
     * <p>- HIterator is created from the empty nested sublist iterator</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     * <p>- Iterator remains in initial state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should throw NoSuchElementException when called on an iterator
     * from an empty nested sublist.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNestedSubListNextThrowsExceptionWhenEmpty()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HIterator subIter = subSub.iterator();

        subIter.next();
    }

    /**
     * Test method to verify proper sequential element retrieval in deeply nested sublist iteration.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that next() returns elements in the correct order during
     * deeply nested sublist iteration, ensuring proper sequential access to elements
     * across multiple levels of sublist hierarchy.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the fundamental iteration functionality
     * for deeply nested sublist iterators. This ensures that elements are returned
     * in the proper sequence and that the iterator maintains correct position
     * throughout traversal of deeply nested sublists.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a three-level nested sublist structure: a sublist from indices 0-5
     * of the main vector, a sub-sublist from indices 1-4 of the first sublist, and
     * a sub-sub-sublist from indices 1-3 of the second sublist. It obtains an iterator
     * from the deepest nested sublist and verifies that next() returns elements in
     * the correct order: "3" followed by null. Finally, it confirms that hasNext()
     * returns false after all elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", null, "5"]</p>
     * <p>- First SubListAdapter is created from indices 0 to 5: ["1", "2", "3", null, "5"]</p>
     * <p>- Second SubListAdapter is created from indices 1 to 4 of first sublist: ["2", "3", null]</p>
     * <p>- Third SubListAdapter is created from indices 1 to 3 of second sublist: ["3", null]</p>
     * <p>- HIterator is created from the deepest nested sublist iterator</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All deepest nested sublist elements are returned in correct sequence: "3", null</p>
     * <p>- hasNext() returns false after the last element</p>
     * <p>- Iterator position is at the end of the deepest nested sublist</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>next() should return deeply nested sublist elements in the correct order,
     * and hasNext() should return false after all elements have been consumed.</p>
     */
    @Test
    public void testDeepNestedSubListNextProperIteration()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 4);
        SubListAdapter subSubSub = (SubListAdapter) subSub.subList(1, 3);
        HIterator subIter = subSubSub.iterator();

        assertEquals("First element should be 3", "3", subIter.next());
        assertNull("Second element should be null", subIter.next());
        assertFalse("Should have no more elements", subIter.hasNext());
    }
}
