package myTest;

import myAdapter.StackAdapter;
import java.util.EmptyStackException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/*
 * RIVEDERE BENE
 * CONTROLLA DOCUMENTAZIONE
 * CONFRONTA CON VECTOR
 * DA ESTENDERE
 * RICONTROLLA COERENZA
 * NOMI METODI
 * */
public class StackAdapterTest
{
    private StackAdapter stack;

    @Before
    public void setUp()
    {
        stack = null;
    }

    @After
    public void tearDown()
    {
        stack = null;
    }

    @Test
    public void testDefaultConstructor()
    {
        stack = new StackAdapter();

        assertNotNull("Stack should be created", stack);
        assertEquals("New stack should have size 0", 0, stack.size());
        assertEquals("Initial capacity should be 10", 10, stack.capacity());
        assertTrue("New stack should be empty", stack.empty());
    }

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

    @Test
    public void testPushMaintainsOrder()
    {
        stack = new StackAdapter();

        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("Third element should be at top", "3", stack.pop());
        assertEquals("Second element should be next", "2", stack.pop());
        assertEquals("First element should be last", "1", stack.pop());
    }

    @Test
    public void testPushNullElement()
    {
        stack = new StackAdapter();
        Object result = stack.push(null);

        assertNull("Should return null", result);
        assertEquals("Stack size should be 1", 1, stack.size());
        assertNull("Null element should be at top", stack.peek());
    }

    @Test
    public void testPushAfterPop()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.pop();
        Object result = stack.push("2");

        assertEquals("Should return the pushed item", "2", result);
        assertEquals("Stack size should be 1", 1, stack.size());
        assertEquals("Pushed element should be at top", "2", stack.peek());
    }

    @Test
    public void testPushToEmptyStack()
    {
        stack = new StackAdapter();
        Object result = stack.push("1");

        assertEquals("Should return the pushed item", "1", result);
        assertEquals("Stack size should be 1", 1, stack.size());
        assertFalse("Stack should not be empty", stack.empty());
    }

    @Test
    public void testPushSameElementMultipleTimes()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("1");
        stack.push("1");
        stack.push("1");

        assertEquals("Stack size should be 4", 4, stack.size());
        assertEquals("Last pushed element should be at top", "1", stack.peek());
    }

    @Test
    public void testPopRemovesTopElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        Object result = stack.pop();

        assertEquals("Should return the top element", "3", result);
        assertEquals("Stack size should be 2 after pop", 2, stack.size());
        assertEquals("New top element should be second element", "2", stack.peek());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopOnEmptyStackThrowsException()
    {
        stack = new StackAdapter();
        stack.pop();
    }

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

    @Test
    public void testPopAfterPushMaintainsOrder()
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

    @Test
    public void testPopNullElement()
    {
        stack = new StackAdapter();
        stack.push(null);

        Object result = stack.pop();

        assertNull("Should return null", result);
        assertTrue("Stack should be empty after pop", stack.empty());
    }

    @Test
    public void testPopOnStackWithDuplicateElements()
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

    @Test
    public void testPeekReturnsTopElementWithoutRemoving()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        Object result = stack.peek();

        assertEquals("Peek should return top element without removing", "3", result);
        assertEquals("Stack size should remain unchanged after peek", 3, stack.size());
        assertEquals("Top element should still be present after peek", "3", stack.peek());
    }

    @Test(expected = EmptyStackException.class)
    public void testPeekOnEmptyStackThrowsException()
    {
        stack = new StackAdapter();
        stack.peek();
    }

    @Test
    public void testPeekAfterPushOperation()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");

        Object result = stack.peek();

        assertEquals("Peek should return last pushed element", "2", result);
        stack.push("3");
        result = stack.peek();
        assertEquals("Peek should return new top element after push", "3", result);
    }

    @Test
    public void testPeekNullElement()
    {
        stack = new StackAdapter();
        stack.push(null);

        Object result = stack.peek();

        assertNull("Peek should return null for null element", result);
        assertEquals("Stack size should be 1", 1, stack.size());
    }

    @Test
    public void testMultiplePeekCallsReturnSameElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");

        Object result1 = stack.peek();
        Object result2 = stack.peek();
        Object result3 = stack.peek();

        assertEquals("First peek should return top element", "2", result1);
        assertEquals("Second peek should return same element", "2", result2);
        assertEquals("Third peek should return same element", "2", result3);
        assertEquals("Stack size should remain unchanged after multiple peeks", 2, stack.size());
    }

    @Test
    public void testPeekWithDuplicateElements()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("1");
        stack.push("1");

        Object result = stack.peek();

        assertEquals("Peek should return top duplicate element", "1", result);
        assertEquals("Stack size should be 3", 3, stack.size());
    }

    @Test
    public void testEmptyNewStack()
    {
        stack = new StackAdapter();
        assertTrue("New stack should be empty", stack.empty());
    }

    @Test
    public void testEmptyAfterPushingElement()
    {
        stack = new StackAdapter();
        stack.push("1");

        assertFalse("Should not be empty", stack.empty());
    }

    @Test
    public void testEmptyAfterRemovingAllElements()
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

    @Test
    public void testEmptyWithNullElements()
    {
        stack = new StackAdapter();
        stack.push(null);
        stack.push(null);

        assertFalse("Stack should not be empty", stack.empty());
    }

    @Test
    public void testSearchTopElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        int position = stack.search("3");

        assertEquals("Top element should be at position 1", 1, position);
    }

    @Test
    public void testSearchMiddleElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        int position = stack.search("2");

        assertEquals("Middle element should be at position 2", 2, position);
    }

    @Test
    public void testSearchBottomElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        int position = stack.search("1");

        assertEquals("Bottom element should be at position 3", 3, position);
    }

    @Test
    public void testSearchNonExistentElement()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        int position = stack.search("4");

        assertEquals("Non-existent element should return -1", -1, position);
    }

    @Test
    public void testSearchOnEmptyStack()
    {
        stack = new StackAdapter();

        int position = stack.search("1");

        assertEquals("Search on empty stack should return -1", -1, position);
    }

    @Test
    public void testSearchNullElementWhenPresent()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push(null);
        stack.push("3");

        int position = stack.search(null);

        assertEquals("Null element should be found at position 1", 2, position);
    }

    @Test
    public void testSearchDuplicateElements()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("1");
        stack.push("3");

        int position = stack.search("1");

        assertEquals("Should return position of topmost duplicate element", 2, position);
    }

    @Test
    public void testSearchAfterPopOperations()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.pop();

        int position = stack.search("2");

        assertEquals("After pop, element should be at position 1", 1, position);
    }

    @Test
    public void testSearchAfterPushOperations()
    {
        stack = new StackAdapter();
        stack.push("1");
        stack.push("2");
        int position1 = stack.search("2");
        stack.push("3");
        int position2 = stack.search("2");

        assertEquals("Element position should change after push", 2, position2);
        assertEquals("Original position should be 1", 1, position1);
    }
}
