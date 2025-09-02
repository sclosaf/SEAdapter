package myTest;

import myAdapter.VectorAdapter;
import myAdapter.StackAdapter;
import myAdapter.HCollection;
import myAdapter.HList;

import java.util.EmptyStackException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;


/**
 * Test suite for the StackAdapter class.
 *
 * <p>This test suite verifies the functionality of the StackAdapter class,
 * which provides a last-in-first-out (LIFO) stack of objects. The adapter
 * uses a Vector as its underlying data structure and implements the standard
 * stack operations: push, pop, peek, empty, and search.</p>
 *
 * <h2>Test Case Design</h2>
 * <p>The test cases are designed to validate the behavior of StackAdapter
 * against the specifications of Java 1.4.2 Stack class. The test design
 * includes categories for basic functionality, edge cases, error conditions,
 * and compliance with the LIFO principle.</p>
 *
 * <p>Categories:</p>
 * <ul>
 *   <li>Constructor tests</li>
 *   <li>Push operation tests</li>
 *   <li>Pop operation tests</li>
 *   <li>Peek operation tests</li>
 *   <li>Empty operation tests</li>
 *   <li>Search operation tests</li>
 *   <li>Exception handling tests</li>
 * </ul>
 *
 * @author   Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see      HCollection
 * @see      HList
 * @see      VectorAdapter
 * @see      StackAdapter
 */
public class StackAdapterTest
{
    /**
     * The main StackAdapter instance used for testing.
     * Initialized before each test method execution.
     */
    private StackAdapter stack;

    /**
     * Sets up the testing environment before each test method execution.
     * Initializes the stack instance to null to ensure the use of a clean state.
     */
    @Before
    public void setUp()
    {
        stack = null;
    }

    /**
     * Cleans up test environment after each test method execution.
     * Sets the stack instance to null to assist garbage collection.
     */
    @After
    public void tearDown()
    {
        stack = null;
    }

    /**
     * Test method to verify that the default constructor creates an empty stack with initial capacity of 10.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the default constructor initializes a StackAdapter instance correctly with size 0,
     * capacity 10, and empty state.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic initialization behavior of the default constructor,
     * ensuring proper initial state for subsequent operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a new StackAdapter using the default constructor and verifies that it is not null,
     * has size 0, capacity 10, and is empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No stack instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- StackAdapter instance is created with correct initial state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Stack should be non-null, have size 0, capacity 10, and empty() should return true.</p>
     */
    @Test
    public void testDefaultConstructor()
    {
        stack = new StackAdapter();

        assertNotNull("Stack should be created", stack);
        assertEquals("New stack should have size 0", 0, stack.size());
        assertEquals("Initial capacity should be 10", 10, stack.capacity());
        assertTrue("New stack should be empty", stack.empty());
    }

    /**
     * Test method to verify capacity growth when pushing beyond initial capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the stack correctly grows its capacity when the number of elements
     * exceeds the initial capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the automatic capacity expansion mechanism of the underlying
     * Vector implementation when used by StackAdapter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes 11 elements onto a stack with initial capacity 10 and verifies that the capacity
     * doubles to 20 while maintaining correct element order and size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance created with default constructor (capacity 10)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack has size 11, capacity 20, and contains all pushed elements in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Stack should grow capacity to 20, maintain size 11, and preserve element insertion order.</p>
     */
    @Test
    public void testDefaultConstructorCapacityGrowth()
    {
        stack = new StackAdapter();

        for(int i = 0; i < 10; i++)
            stack.push("" + i);

        stack.push("10");

        assertNotNull("Should have been created", stack);
        assertEquals("Size should be 11", 11, stack.size());

        for(int i = 0; i < 11; i++)
            assertEquals("Element " + i + "-th should match", "" + i, stack.get(i));

        assertEquals("Capacity should be 20", 20, stack.capacity());
        assertFalse("Should not be empty", stack.empty());
    }

    /**
     * Test method to verify that push operations maintain LIFO order.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that push operations maintain proper Last-In-First-Out order in the stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the fundamental LIFO behavior of the stack data structure
     * through sequential push and pop operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements and verifies that pop operations return them in reverse
     * (LIFO) order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack becomes empty after all pops</p>
     * <p>- Elements are returned in LIFO order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Elements should be popped in reverse order of pushing (3, 2, 1).</p>
     */
    @Test
    public void testPushProperOrder()
    {
        stack = new StackAdapter();

        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("Third element should be at top", "3", stack.pop());
        assertEquals("Second element should be next", "2", stack.pop());
        assertEquals("First element should be last", "1", stack.pop());
    }

    /**
     * Test method to verify that null elements can be pushed onto the stack.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that null elements are handled correctly by the push operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the stack's ability to handle null elements, which is
     * an optional but commonly supported feature in collection implementations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes a null element and verifies that the operation returns null, size increases
     * to 1, and peek returns null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains one null element</p>
     * <p>- Size is 1</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>push(null) should return null, size should be 1, and peek() should return null.</p>
     */
    @Test
    public void testPushNullElement()
    {
        stack = new StackAdapter();

        assertNull("Should return null", stack.push(null));
        assertEquals("Stack size should be 1", 1, stack.size());
        assertNull("Null element should be at top", stack.peek());
    }

    /**
     * Test method to verify push operation after pop operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that push operations work correctly after previous pop operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the stack's behavior when alternating between push and pop
     * operations, ensuring proper state maintenance.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes an element, pops it (making stack empty), then pushes another element
     * and verifies the new state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains one element ("2")</p>
     * <p>- Size is 1</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Push after pop should work normally, returning the pushed element and updating size correctly.</p>
     */
    @Test
    public void testPushAfterPop()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.pop();

        assertEquals("Should return the pushed item", "2", stack.push("2"));
        assertEquals("Stack size should be 1", 1, stack.size());
        assertEquals("Pushed element should be at top", "2", stack.peek());
    }

    /**
     * Test method to verify push operation on empty stack.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies the basic push operation on an initially empty stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the most fundamental stack operation - pushing onto
     * an empty stack.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes one element onto an empty stack and verifies the return value,
     * size update, and empty state change.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains one element ("1")</p>
     * <p>- Size is 1</p>
     * <p>- Stack is not empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Push should return the element, size should become 1, and empty() should return false.</p>
     */
    @Test
    public void testPushToEmptyStack()
    {
        stack = new StackAdapter();

        assertEquals("Should return the pushed item", "1", stack.push("1"));
        assertEquals("Stack size should be 1", 1, stack.size());
        assertFalse("Stack should not be empty", stack.empty());
    }

    /**
     * Test method to verify pushing multiple identical elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the stack correctly handles multiple pushes of the same element.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that duplicate elements are handled correctly and
     * the stack maintains proper size and order.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes the same element four times and verifies the size and that the top
     * element remains the same.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains four identical elements</p>
     * <p>- Size is 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Stack should allow duplicate elements, size should be 4, and top element should be the same as pushed.</p>
     */
    @Test
    public void testPushMultipleSameElements()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("1");
        stack.push("1");
        stack.push("1");

        assertEquals("Stack size should be 4", 4, stack.size());
        assertEquals("Last pushed element should be at top", "1", stack.peek());
    }

    /**
     * Test method to verify pop operation returns elements in LIFO order.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that pop operations return elements in correct Last-In-First-Out order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the fundamental pop operation behavior in maintaining
     * LIFO order after multiple pushes.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements, then pops them one by one while verifying the return values,
     * size updates, and new top elements after each pop.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Stack with three elements pushed: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack becomes empty after three pops</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Pops should return elements in reverse push order (3, 2, 1) with proper size updates.</p>
     */
    @Test
    public void testPopProperOrder()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("Should return the top element", "3", stack.pop());
        assertEquals("Stack size should be 2 after pop", 2, stack.size());
        assertEquals("New top element should be second element", "2", stack.peek());
    }

    /**
     * Test method to verify that pop on empty stack throws EmptyStackException.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that attempting to pop from an empty stack throws the appropriate exception.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for the edge case of popping
     * from an empty stack.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to pop from an empty stack and expects an EmptyStackException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- EmptyStackException is thrown</p>
     * <p>- Stack remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>pop() should throw EmptyStackException when called on an empty stack.</p>
     */
    @Test(expected = EmptyStackException.class)
    public void testPopOnEmptyStackThrowsException()
    {
        stack = new StackAdapter();
        stack.pop();
    }

    /**
     * Test method to verify that multiple pop operations empty the stack completely.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that consecutive pop operations correctly remove all elements from the stack
     * and leave it in an empty state.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the complete emptying of the stack through multiple pop operations,
     * ensuring proper size management and state transitions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements, then performs three consecutive pop operations while verifying
     * the return values and finally checks that the stack is empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack becomes empty after all pop operations</p>
     * <p>- empty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Pop operations should return elements in LIFO order (3, 2, 1) and stack should be empty after all pops.</p>
     */
    @Test
    public void testPopUntilEmpty()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("First pop should return 3", "3", stack.pop());
        assertEquals("Second pop should return 2", "2", stack.pop());
        assertEquals("Third pop should return 1", "1", stack.pop());
        assertTrue("Stack should be empty after all pops", stack.empty());
    }

    /**
     * Test method to verify pop behavior after intermediate push operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that pop operations work correctly when interspersed with push operations,
     * maintaining proper LIFO order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the stack's behavior when push and pop operations are
     * alternated, ensuring correct element ordering and state management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes two elements, pops one, pushes another element, then verifies that subsequent
     * pops return elements in the correct LIFO order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with elements "1" and "2" pushed</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack becomes empty after all operations</p>
     * <p>- Elements are returned in correct LIFO order (3, 1)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Pop operations should maintain LIFO order regardless of intermediate push operations.</p>
     */
    @Test
    public void testPopAfterPush()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.pop();
        stack.push("3");

        assertEquals("Top element should be 3", "3", stack.pop());
        assertEquals("Next element should be 1", "1", stack.pop());
        assertTrue("Stack should be empty", stack.empty());
    }

    /**
     * Test method to verify pop operation with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that pop operations correctly handle null elements on the stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the stack's ability to handle null elements during pop operations,
     * ensuring proper return values and state updates.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes a null element, pops it, and verifies that null is returned and the stack becomes empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with one null element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack becomes empty after pop</p>
     * <p>- empty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>pop() should return null and the stack should become empty.</p>
     */
    @Test
    public void testPopNullElement()
    {
        stack = new StackAdapter();
        stack.push(null);

        assertNull("Should return null", stack.pop());
        assertTrue("Stack should be empty after pop", stack.empty());
    }

    /**
     * Test method to verify pop operations with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that pop operations work correctly when the stack contains duplicate elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the stack's handling of duplicate elements during pop operations,
     * ensuring that each element is treated independently.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three identical elements, then pops them all while verifying the return values
     * and final empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three identical elements "1"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack becomes empty after all pops</p>
     * <p>- All pop operations return "1"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>All pop operations should return "1" and stack should become empty after three pops.</p>
     */
    @Test
    public void testPopDuplicateElements()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("1");
        stack.push("1");

        assertEquals("First pop should return 1", "1", stack.pop());
        assertEquals("Second pop should return 1", "1", stack.pop());
        assertEquals("Third pop should return 1", "1", stack.pop());
        assertTrue("Stack should be empty", stack.empty());
    }

    /**
     * Test method to verify that peek returns the top element without removing it.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the peek operation correctly returns the top element without modifying
     * the stack's contents or size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the non-destructive nature of the peek operation, ensuring
     * that the stack state remains unchanged after peeking.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements, calls peek multiple times, and verifies that the return value
     * remains consistent and the stack size doesn't change.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack size remains 3 after multiple peek calls</p>
     * <p>- Top element remains "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>peek() should consistently return "3" without changing stack size or contents.</p>
     */
    @Test
    public void testPeekReturnsElementWithoutRemoving()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("Peek should return top element without removing", "3", stack.peek());
        assertEquals("Stack size should remain unchanged after peek", 3, stack.size());
        assertEquals("Top element should still be present after peek", "3", stack.peek());
    }

    /**
     * Test method to verify that peek on empty stack throws EmptyStackException.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that attempting to peek at an empty stack throws the appropriate exception.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for the edge case of peeking
     * at an empty stack.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to peek at an empty stack and expects an EmptyStackException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- EmptyStackException is thrown</p>
     * <p>- Stack remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>peek() should throw EmptyStackException when called on an empty stack.</p>
     */
    @Test(expected = EmptyStackException.class)
    public void testPeekOnEmptyStackThrowsException()
    {
        stack = new StackAdapter();
        stack.peek();
    }

    /**
     * Test method to verify peek behavior after push operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that peek correctly reflects the current top element after push operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that peek operations always return the most recently pushed
     * element, reflecting the current stack state.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes elements sequentially and verifies that peek returns the correct top element
     * after each push operation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with initial elements "1" and "2"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains three elements</p>
     * <p>- peek returns "3" after the final push</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>peek() should always return the most recently pushed element.</p>
     */
    @Test
    public void testPeekAfterPush()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");

        assertEquals("Peek should return last pushed element", "2", stack.peek());

        stack.push("3");

        assertEquals("Peek should return new top element after push", "3", stack.peek());
    }

    /**
     * Test method to verify peek behavior after pop operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that peek correctly reflects the new top element after pop operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that peek operations return the correct element after
     * the stack state has been modified by pop operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements, pops one, and verifies that peek returns the new top element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains two elements after pop</p>
     * <p>- peek returns "2" (the new top element)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>peek() should return the new top element "2" after popping the previous top element.</p>
     */
    @Test
    public void testPeekAfterPop()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        stack.pop();

        assertEquals("Peek should return the second-last pushed element", "2", stack.peek());
    }

    /**
     * Test method to verify peek operation with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that peek correctly handles null elements on the stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the stack's ability to handle null elements during peek operations,
     * ensuring proper return values and state preservation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes a null element, calls peek, and verifies that null is returned and the stack
     * size remains unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with one null element</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack size remains 1 after peek</p>
     * <p>- peek returns null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>peek() should return null and the stack size should remain 1.</p>
     */
    @Test
    public void testPeekNullElement()
    {
        stack = new StackAdapter();
        stack.push(null);

        assertNull("Peek should return null for null element", stack.peek());
        assertEquals("Stack size should be 1", 1, stack.size());
    }

    /**
     * Test method to verify peek operation with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that peek correctly returns the top element when the stack contains
     * multiple duplicate elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that peek operations work correctly with duplicate elements,
     * ensuring that the most recently pushed duplicate is returned.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three identical elements and verifies that peek returns the top element
     * while maintaining the correct stack size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three identical elements "1"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack size remains 3 after peek</p>
     * <p>- peek returns "1" (the top element)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>peek() should return "1" and stack size should remain 3.</p>
     */
    @Test
    public void testPeekDuplicateElements()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("1");
        stack.push("1");

        assertEquals("Peek should return top duplicate element", "1", stack.peek());
        assertEquals("Stack size should be 3", 3, stack.size());
    }

    /**
     * Test method to verify empty state of newly created stack.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a newly created stack is initially empty.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initial state of a stack after construction,
     * ensuring proper initialization.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a new stack and immediately checks that the empty() method returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Newly created StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack remains empty</p>
     * <p>- empty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>empty() should return true for a newly created stack.</p>
     */
    @Test
    public void testEmptyOnNewStack()
    {
        stack = new StackAdapter();
        assertTrue("New stack should be empty", stack.empty());
    }

    /**
     * Test method to verify empty state after push operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the stack is no longer empty after pushing an element.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the empty() method correctly reflects the state
     * change after a push operation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes one element onto an empty stack and verifies that empty() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains one element</p>
     * <p>- empty() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>empty() should return false after pushing an element onto the stack.</p>
     */
    @Test
    public void testEmptyAfterPush()
    {
        stack = new StackAdapter();
        stack.push("1");

        assertFalse("Should not be empty", stack.empty());
    }

    /**
     * Test method to verify empty state after multiple pop operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the stack becomes empty after removing all elements through pop operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the empty() method correctly reflects the empty state
     * after all elements have been removed from the stack.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements, then pops all three and verifies that empty() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack becomes empty after all pops</p>
     * <p>- empty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>empty() should return true after removing all elements from the stack.</p>
     */
    @Test
    public void testEmptyAfterPop()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        stack.pop();
        stack.pop();
        stack.pop();

        assertTrue("Stack should be empty", stack.empty());
    }

    /**
     * Test method to verify empty state with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the empty() method correctly handles stacks containing null elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that null elements are counted as valid elements
     * when determining if the stack is empty.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes two null elements and verifies that empty() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains two null elements</p>
     * <p>- empty() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>empty() should return false when the stack contains null elements.</p>
     */
    @Test
    public void testEmptyNullElements()
    {
        stack = new StackAdapter();
        stack.push(null);
        stack.push(null);

        assertFalse("Stack should not be empty", stack.empty());
    }

    /**
     * Test method to verify search operation for top element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation correctly returns position 1 for the top element.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the 1-based positioning system of the search operation,
     * where the top element is at position 1.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements and searches for the top element, verifying that it returns position 1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack remains unchanged</p>
     * <p>- search returns 1 for top element "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>search("3") should return 1 (top position).</p>
     */
    @Test
    public void testSearchTopElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("Top element should be at position 1", 1, stack.search("3"));
    }

    /**
     * Test method to verify search operation for middle element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation correctly returns the appropriate position
     * for a middle element in the stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the search operation's ability to locate elements
     * that are not at the top of the stack.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements and searches for the middle element, verifying that it returns position 2.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack remains unchanged</p>
     * <p>- search returns 2 for middle element "2"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>search("2") should return 2 (middle position).</p>
     */
    @Test
    public void testSearchMiddleElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("Middle element should be at position 2", 2, stack.search("2"));
    }

    /**
     * Test method to verify search operation for bottom element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation correctly returns the appropriate position
     * for the bottom element in the stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the search operation's ability to locate elements
     * at the bottom of the stack using the 1-based positioning system.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements and searches for the bottom element, verifying that it returns position 3.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack remains unchanged</p>
     * <p>- search returns 3 for bottom element "1"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>search("1") should return 3 (bottom position).</p>
     */
    @Test
    public void testSearchBottomElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("Bottom element should be at position 3", 3, stack.search("1"));
    }

    /**
     * Test method to verify search operation for non-existent element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation correctly returns -1 when searching for
     * an element that does not exist in the stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the search operation's behavior when the requested
     * element is not present in the stack, ensuring proper error indication.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements and searches for a non-existent element, verifying
     * that it returns -1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack remains unchanged</p>
     * <p>- search returns -1 for non-existent element "4"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>search("4") should return -1 indicating the element is not found.</p>
     */
    @Test
    public void testSearchNonExistentElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("Non-existent element should return -1", -1, stack.search("4"));
    }

    /**
     * Test method to verify search operation on empty stack.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation correctly returns -1 when performed
     * on an empty stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the search operation's edge case behavior when
     * the stack contains no elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty stack and searches for an element, verifying that it returns -1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty StackAdapter instance</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack remains empty</p>
     * <p>- search returns -1 for any element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>search("1") should return -1 when called on an empty stack.</p>
     */
    @Test
    public void testSearchOnEmptyStack()
    {
        stack = new StackAdapter();

        assertEquals("Search on empty stack should return -1", -1, stack.search("1"));
    }

    /**
     * Test method to verify search operation for null element when present.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation correctly locates null elements
     * in the stack and returns their proper 1-based position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the search operation's ability to handle
     * null elements mixed with regular elements in the stack.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements including a null element and searches for null,
     * verifying that it returns the correct position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with elements: "1", null, "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack remains unchanged</p>
     * <p>- search returns 2 for null element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>search(null) should return 2 (the position of the null element).</p>
     */
    @Test
    public void testSearchNullElementWhenPresent()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push(null);
        stack.push("3");

        assertEquals("Null element should be found at position 1", 2, stack.search(null));
    }

    /**
     * Test method to verify search operation with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation returns the position of the
     * topmost occurrence when duplicate elements exist in the stack.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the search operation's behavior with
     * duplicate elements, ensuring it follows the LIFO principle by returning
     * the position of the most recently pushed duplicate.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes multiple elements including duplicates and searches for
     * the duplicate element, verifying that it returns the position of the topmost occurrence.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with elements: "1", "2", "1", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack remains unchanged</p>
     * <p>- search returns 2 for element "1" (topmost duplicate)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>search("1") should return 2 (position of the topmost duplicate).</p>
     */
    @Test
    public void testSearchDuplicateElements()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("1");
        stack.push("3");

        assertEquals("Should return position of topmost duplicate element", 2, stack.search("1"));
    }

    /**
     * Test method to verify search operation after pop operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation correctly reflects changes
     * in element positions after a pop operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the search operation dynamically
     * updates element positions when the stack structure changes.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes three elements, pops the top element, and searches for
     * the new top element, verifying that it returns position 1.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains two elements after pop</p>
     * <p>- search returns 1 for new top element "2"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After popping "3", search("2") should return 1 (new top position).</p>
     */
    @Test
    public void testSearchAfterPop()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.pop();

        assertEquals("After pop, element should be at position 1", 1, stack.search("2"));
    }

    /**
     * Test method to verify search operation after push operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the search operation correctly reflects changes
     * in element positions after a push operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the search operation dynamically
     * updates element positions when new elements are added to the stack.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test pushes two elements, verifies the position of an element, then
     * pushes another element and verifies that the same element's position has changed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- StackAdapter instance with two elements: "1", "2"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Stack contains three elements after push</p>
     * <p>- Element "2" moves from position 1 to position 2</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After pushing "3", search("2") should return 2 (moved from position 1).</p>
     */
    @Test
    public void testSearchAfterPush()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");

        assertEquals("Element position should be 1", 1, stack.search("2"));

        stack.push("3");

        assertEquals("Element position should change after push", 2, stack.search("2"));
    }
}
