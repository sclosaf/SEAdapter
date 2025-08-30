package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HListIterator;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * RICONTROLLA COERENZA
 * NOMI METODI
 * */

public class ListIteratorAdapterTest
{
    private VectorAdapter vector;
    private SubListAdapter subList;
    private HListIterator listIterator;
    private HListIterator subListLIterator;

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

    @After
    public void tearDown()
    {
        vector = null;
        subList = null;
        listIterator = null;
        subListLIterator = null;
    }

    @Test
    public void testNextIndexCorrectElements()
    {
        while(listIterator.hasNext())
            assertEquals("Element should match corresponding index at " + listIterator.nextIndex(),
                vector.get(listIterator.nextIndex()), listIterator.next());
    }

    @Test
    public void testNextIndexAtBeginning()
    {
        assertEquals("Should be 0 at beginning", 0, listIterator.nextIndex());
    }

    @Test
    public void testNextIndexAfterNext()
    {
        listIterator.next();
        assertEquals("Should be 1 after first next", 1, listIterator.nextIndex());

        listIterator.next();
        assertEquals("Should be 2 after second next", 2, listIterator.nextIndex());
    }

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

    @Test
    public void testNextIndexAtEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertEquals("Should be size() at end", vector.size(), listIterator.nextIndex());
    }

    @Test
    public void testNextIndexAfterAdd()
    {
        listIterator.add("0");
        assertEquals("Should update after add", 1, listIterator.nextIndex());
    }

    @Test
    public void testNextIndexAfterRemove()
    {
        listIterator.next();
        listIterator.remove();
        assertEquals("Should update after remove", 0, listIterator.nextIndex());
    }

    @Test
    public void testNextIndexAfterSet()
    {
        listIterator.next();
        listIterator.set("6");
        assertEquals("Should not update after set", 1, listIterator.nextIndex());
    }

    @Test
    public void testPreviousIndexCorrectElements()
    {
        while(listIterator.hasNext())
            listIterator.next();

        while(listIterator.hasPrevious())
            assertEquals("Element should match corresponding index at " + listIterator.previousIndex(),
                vector.get(listIterator.previousIndex()), listIterator.previous());
    }

    @Test
    public void testPreviousIndexAtBeginning()
    {
        assertEquals("Should be -1 at beginning", -1, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexAfterNext()
    {
        listIterator.next();
        assertEquals("Should be 0 after first next", 0, listIterator.previousIndex());

        listIterator.next();
        assertEquals("Should be 1 after second next", 1, listIterator.previousIndex());
    }

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

    @Test
    public void testPreviousIndexAtEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertEquals("Should be size() - 1 at end",
            vector.size() - 1, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexAfterAdd()
    {
        listIterator.add("0");
        assertEquals("Should update after add", 0, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexAfterRemove()
    {
        listIterator.next();
        listIterator.remove();
        assertEquals("Should update after remove", -1, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexAfterSet()
    {
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.set("6");

        assertEquals("Should not update after set", 0, listIterator.previousIndex());
    }

    @Test
    public void testEmptyCollectionOperations()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Should not have previous", listIter.hasPrevious());
        assertFalse("Should not have next", listIter.hasNext());
        assertEquals("Should be -1", -1, listIter.previousIndex());
        assertEquals("Should be 0", 0, listIter.nextIndex());
    }

    @Test
    public void testHasNextAtTheBeginning()
    {
        assertTrue("Collection should have been created and filled properly",
            listIterator.hasNext());
    }

    @Test
    public void testHasNextInTheMiddle()
    {
        listIterator.next();
        listIterator.next();
        listIterator.next();

        assertTrue("Collection should have some more elements", listIterator.hasNext());
    }

    @Test
    public void testHasNextAtTheEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertFalse("hasNext should return false after full emptying iteration",
            listIterator.hasNext());
    }

    @Test
    public void testHasPreviousAtTheBeginning()
    {
        assertFalse("Should return false at the beginning",
            listIterator.hasPrevious());
    }

    @Test
    public void testHasPreviousInTheMiddle()
    {
        listIterator.next();
        listIterator.next();
        assertTrue("Should return true after moving forward",
            listIterator.hasPrevious());
    }

    @Test
    public void testHasPreviousAtTheEnd()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertTrue("Should return true even at the end", listIterator.hasPrevious());
    }

    @Test
    public void testNextReturnsCorrectElementsForwardAndBackward()
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

    @Test(expected = NoSuchElementException.class)
    public void testHasNextThrowsExceptionWhenNoMoreElements()
    {
        while(listIterator.hasNext())
            listIterator.next();

        listIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPrevioudThrowsExceptionAtBeginning()
    {
        listIterator.previous();
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasNextThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        listIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasPreviousThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        listIter.previous();
    }

    @Test
    public void testHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Empty collection should have no elements",
            listIter.hasNext());
    }

    @Test
    public void testHasPreviousOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Empty collection should have no elements",
            listIter.hasPrevious());
    }

    @Test
    public void testNextOnSingleElementCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");

        HListIterator listIter = vec.listIterator();

        assertTrue("Should have one element", listIter.hasNext());
        assertEquals("Should return the single element", "0", listIter.next());
        assertFalse("Should have no more elements", listIter.hasNext());
    }

    @Test
    public void testPreviousOnSingleElementCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");

        HListIterator listIter = vec.listIterator();
        listIter.next();

        assertFalse("Should have one element", listIter.hasNext());
        assertEquals("Should return the single element", "0", listIter.previous());
        assertFalse("Should have no more elements", listIter.hasPrevious());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsExceptionWhenNoMoreElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");

        HListIterator listIter = vec.listIterator();
        listIter.next();
        listIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPreviousThrowsExceptionWhenNoMoreElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");

        HListIterator listIter = vec.listIterator();

        listIter.next();
        listIter.previous();
        listIter.previous();
    }

    @Test
    public void testNextWithAllNull()
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

    @Test
    public void testPreviousWithAllNull()
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

    @Test
    public void testMultipleIteratorsAreIndependent()
    {
        HListIterator listIter1 = vector.listIterator();
        HListIterator listIter2 = vector.listIterator();

        listIter1.next();
        listIter1.next();

        assertEquals("Second iterator should still be at the beginning",
            "1", listIter2.next());
        assertEquals("First iterator should have iterated",
            "3", listIter1.next());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsException()
    {
        listIterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterNext()
    {
        listIterator.next();
        listIterator.remove();
        listIterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterPrevious()
    {
        listIterator.next();
        listIterator.previous();
        listIterator.remove();
        listIterator.remove();
    }

    @Test
    public void testRemoveAfterNext()
    {
        listIterator.next();
        listIterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertEquals("First element should be '2'", "2", vector.get(0));
        assertFalse("Should not contain removed element", vector.contains("1"));
    }

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

    @Test
    public void testRemoveMultipleElementsNext()
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

    @Test
    public void testRemoveMultipleElementsPrevious()
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

    @Test
    public void testRemoveThenHasNext()
    {
        listIterator.next();
        listIterator.remove();

        assertEquals("Size should be 4", 4, vector.size());
        assertTrue("Should still have elements", listIterator.hasNext());
        assertEquals("Next element should be '2'", "2", listIterator.next());
    }

    @Test
    public void testRemoveThenHasPrevious()
    {
        listIterator.next();
        listIterator.previous();
        listIterator.remove();

        assertEquals("Size should be 4", 4, vector.size());
        assertFalse("Should not have previous elements", listIterator.hasPrevious());
    }

    @Test
    public void testRemoveAllElementsNext()
    {
        while(listIterator.hasNext())
        {
            listIterator.next();
            listIterator.remove();
        }

        assertTrue("Collection should be empty after removing all the elements", vector.isEmpty());
        assertEquals("Size should be 0", 0, vector.size());
        assertFalse("Iterator should have no more elements", listIterator.hasNext());
        assertFalse("Iterator should have no more elements", listIterator.hasPrevious());
    }

    @Test
    public void testRemoveAllElementsPrevious()
    {
        while(listIterator.hasNext())
            listIterator.next();

        while(listIterator.hasPrevious())
        {
            listIterator.previous();
            listIterator.remove();
        }

        assertTrue("Collection should be empty after removing all the elements", vector.isEmpty());
        assertEquals("Size should be 0", 0, vector.size());
        assertFalse("Iterator should have no more elements", listIterator.hasNext());
        assertFalse("Iterator should have no more elements", listIterator.hasPrevious());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        listIter.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterAdd()
    {
        listIterator.next();
        listIterator.add("6");
        listIterator.remove();
    }

    @Test
    public void testRemoveAffectsOtherIterators()
    {
        HListIterator listIter1 = vector.listIterator();
        HListIterator listIter2 = vector.listIterator();

        listIter1.next();
        listIter1.remove();

        assertEquals("Second iterator should see the modified collection", "2", listIter2.next());
    }

    @Test
    public void testSetAfterNext()
    {
        listIterator.next();
        listIterator.set("6");

        assertEquals("Element should have been replaces", "6", vector.get(0));
        assertEquals("Next element should still be '2'", "2", listIterator.next());
    }

    @Test
    public void testSetAfterPrevious()
    {
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.set("6");

        assertEquals("Element should have been replaced", "6", vector.get(1));
        assertEquals("Next element should be the replaced value", "6", listIterator.next());
    }

    @Test
    public void testSetReplacesLastReturedElement()
    {
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.next();
        listIterator.previous();
        listIterator.previous();
        listIterator.next();
        listIterator.set("6");

        assertEquals("Element should have been replaced", "6", vector.get(1));
        assertEquals("Previous element should be the replaced value", "6", listIterator.previous());
    }

    @Test(expected = IllegalStateException.class)
    public void testSetThrowsExceptionWithoutNextOrPrevious()
    {
        listIterator.set("6");
    }

    @Test(expected = IllegalStateException.class)
    public void testSetThrowsExceptionAfterRemove()
    {
        listIterator.next();
        listIterator.remove();
        listIterator.set("7");
    }

    @Test(expected = IllegalStateException.class)
    public void testSetThrowsExceptionAfterAdd()
    {
        listIterator.next();
        listIterator.add("6");
        listIterator.set("7");
    }

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

    @Test
    public void testAddBeforeNext()
    {
        listIterator.add("6");

        assertEquals("Size should increase by 1", 6, vector.size());
        assertEquals("First element should be the new element", "6", vector.get(0));
        assertEquals("Next should return original first element", "1", listIterator.next());
    }

    @Test
    public void testAddBeforePrevious()
    {
        listIterator.add("6");
        assertEquals("Previous should return the added element", "6", listIterator.previous());
    }

    @Test
    public void testAddAfterNext()
    {
        listIterator.next();
        listIterator.add("6");

        assertEquals("Size should increase by 1", 6, vector.size());
        assertEquals("Second element should be the new element", "6", vector.get(1));
        assertEquals("Next should return original first element", "2", listIterator.next());
    }

    @Test
    public void testAddAfterPrevious()
    {
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.add("6");

        assertEquals("Size should increase by 1", 6, vector.size());
        assertEquals("Second element should be the new element", "6", vector.get(1));
        assertEquals("Next should return original second element", "2", listIterator.next());
    }

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

    @Test
    public void testSingleElementCollectionBackAndForth()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        HListIterator listIter = vec.listIterator();

        assertTrue("Should have next", listIter.hasNext());
        assertFalse("Should not have previous", listIter.hasPrevious());
        assertEquals("Should return the single element", "1", listIter.next());
        assertFalse("Should not have next", listIter.hasNext());
        assertTrue("Should have previous", listIter.hasPrevious());
    }

    @Test
    public void testListIteratorAtBeginning()
    {
        HListIterator listIter = vector.listIterator(0);

        assertTrue("Should have next at beginning", listIter.hasNext());
        assertFalse("Should not have previous at beginning", listIter.hasPrevious());
        assertEquals("Next index should be 0", 0, listIter.nextIndex());
        assertEquals("Previous index should be -1", -1, listIter.previousIndex());
        assertEquals("First element should be correct", "1", listIter.next());
    }

    @Test
    public void testListIteratorAtMiddle()
    {
        HListIterator listIter = vector.listIterator(2);

        assertTrue("Should have next at middle", listIter.hasNext());
        assertTrue("Should have previous at middle", listIter.hasPrevious());
        assertEquals("Next index should be 2", 2, listIter.nextIndex());
        assertEquals("Previous index should be 1", 1, listIter.previousIndex());
        assertEquals("Next element should be correct", "3", listIter.next());
        assertEquals("Previous element should be correct", "3", listIter.previous());
    }

    @Test
    public void testListIteratorAtEnd()
    {
        HListIterator listIter = vector.listIterator(vector.size());

        assertFalse("Should not have next at end", listIter.hasNext());
        assertTrue("Should have previous at end", listIter.hasPrevious());
        assertEquals("Next index should be size", vector.size(), listIter.nextIndex());
        assertEquals("Previous index should be size - 1", vector.size() - 1, listIter.previousIndex());
        assertEquals("Last element should be correct", "5", listIter.previous());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorWithNegativeIndex()
    {
        vector.listIterator(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorWithIndexGreaterThanSize()
    {
        vector.listIterator(vector.size() + 1);
    }

    @Test
    public void testListIteratorEmptyVector()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator(0);

        assertFalse("Collection should not have next", listIter.hasNext());
        assertFalse("Collection should not have previous", listIter.hasPrevious());
        assertEquals("Next index should be 0", 0, listIter.nextIndex());
        assertEquals("Previous index should be -1", -1, listIter.previousIndex());
    }

    @Test
    public void testListIteratorSingleElementVector()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");

        HListIterator listIter = vec.listIterator(0);
        assertEquals("Should return single element", "1", listIter.next());

        listIter = vec.listIterator(1);
        assertEquals("Should return single element via previous", "1", listIter.previous());
    }

    @Test
    public void testListIteratorForwardIterationFromIndex()
    {
        HListIterator listIter = vector.listIterator(2);

        assertEquals("First element from index 2", "3", listIter.next());
        assertNull("Second element from index 2", listIter.next());
        assertEquals("Third element from index 2", "5", listIter.next());
        assertFalse("Should have no more elements", listIter.hasNext());
    }

    @Test
    public void testListIteratorBackwardIterationFromIndex()
    {
        HListIterator listIter = vector.listIterator(4);

        assertEquals("First element backward from index 4", null, listIter.previous());
        assertEquals("Second element backward from index 4", "3", listIter.previous());
        assertEquals("Third element backward from index 4", "2", listIter.previous());
        assertEquals("Fourth element backward from index 4", "1", listIter.previous());
        assertFalse("Should have no more previous elements", listIter.hasPrevious());
    }

    @Test
    public void testListIteratorBidirectionalIterationFromIndex()
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

    @Test
    public void testListIteratorRemoveFromMiddleIndex()
    {
        HListIterator listIter = vector.listIterator(2);

        listIter.next();
        listIter.remove();

        assertEquals("Size should decrease", 4, vector.size());
        assertEquals("Element at index 2 should be null", null, vector.get(2));
        assertFalse("Should not contain removed element", vector.contains("3"));
    }

    @Test
    public void testListIteratorRemoveAfterPreviousFromIndex()
    {
        HListIterator listIter = vector.listIterator(4);

        listIter.previous();
        listIter.remove();

        assertEquals("Size should decrease", 4, vector.size());
        assertEquals("Element at index 3 should be 5", "5", vector.get(3));
        assertFalse("Should not contain null element", vector.contains(null));
    }

    @Test
    public void testListIteratorSetFromIndex()
    {
        HListIterator listIter = vector.listIterator(1);

        listIter.next();
        listIter.set("6");

        assertEquals("Element should be modified", "6", vector.get(1));
        assertEquals("Next should return next element", "3", listIter.next());
    }

    @Test
    public void testListIteratorSetAfterPreviousFromIndex()
    {
        HListIterator listIter = vector.listIterator(3);

        listIter.previous();
        listIter.set("6");

        assertEquals("Element should be modified", "6", vector.get(2));
        assertEquals("Next should return modified element", "6", listIter.next());
    }

    @Test
    public void testListIteratorAddFromIndex()
    {
        HListIterator listIter = vector.listIterator(2);

        listIter.add("6");

        assertEquals("Size should increase", 6, vector.size());
        assertEquals("New element should be at index 2", "6", vector.get(2));
        assertEquals("Next should return original element", "3", listIter.next());
    }

    @Test
    public void testListIteratorAddAfterNext()
    {
        HListIterator listIter = vector.listIterator(2);

        listIter.next();
        listIter.add("6");

        assertEquals("Size should increase", 6, vector.size());
        assertEquals("New element should be at index 3", "6", vector.get(3));
        assertNull("Next should return the original element", listIter.next());
    }

    @Test
    public void testListIteratorAddAfterPrevious()
    {
        HListIterator listIter = vector.listIterator(2);

        listIter.previous();
        listIter.add("6");

        assertEquals("Size should increase", 6, vector.size());
        assertEquals("New element shoudl be at index 1", "6", vector.get(1));
        assertEquals("Next should return the original element", "2",  listIter.next());
    }

    @Test
    public void testListIteratorAddAtEndFromIndex()
    {
        HListIterator listIter = vector.listIterator(vector.size());

        listIter.add("6");

        assertEquals("Size should increase", 6, vector.size());
        assertEquals("New element should be at the end", "6", vector.get(5));
        assertEquals("Previous should return new element", "6", listIter.previous());
    }

    @Test
    public void testListIteratorIndexConsistency()
    {
        HListIterator listIter = vector.listIterator(2);

        assertEquals(2, listIter.nextIndex());
        assertEquals(1, listIter.previousIndex());

        listIter.next();
        assertEquals(3, listIter.nextIndex());
        assertEquals(2, listIter.previousIndex());

        listIter.previous();
        assertEquals(2, listIter.nextIndex());
        assertEquals(1, listIter.previousIndex());
    }

    @Test
    public void testListIteratorMultipleOperationsFromIndex()
    {
        HListIterator listIter = vector.listIterator(2);

        listIter.add("6");
        assertEquals("Size after add", 6, vector.size());
        assertEquals("Next index after add", 3, listIter.nextIndex());

        assertEquals("Next after add", "3", listIter.next());
        listIter.set("7");
        assertEquals("Element should be set", "7", vector.get(3));

        assertEquals("Previous after set", "7", listIter.previous());

        listIter.remove();

        assertEquals("Size after remove", 5, vector.size());
        assertEquals("Next index after remove", 3, listIter.nextIndex());
    }

    @Test
    public void testListIteratorWithSubList()
    {
        SubListAdapter subList = (SubListAdapter) vector.subList(1, 4);
        HListIterator subListLIter = subList.listIterator(1);

        assertEquals("Should start from subList index 1", "3", subListLIter.next());
        assertEquals("Should get null element", null, subListLIter.next());
        assertFalse("Should have no more elements in subList", subListLIter.hasNext());
    }

    @Test
    public void testListIteratorFromIndexWithNestedSubList()
    {
        SubListAdapter subList = (SubListAdapter) vector.subList(1, 5);
        SubListAdapter subSubList = (SubListAdapter) subList.subList(1, 3);
        HListIterator subListLIter = subSubList.listIterator(1);

        assertEquals("Should get element from nested subList", null, subListLIter.next());
        assertFalse("Should have no more elements", subListLIter.hasNext());
        assertEquals("Should get element via previous", null, subListLIter.previous());
    }

    @Test
    public void testListIteratorFromIndexEqualsRegularIterator()
    {
        HListIterator listIter1 = vector.listIterator();
        HListIterator listIter2 = vector.listIterator(2);

        listIter1.next();
        listIter1.next();

        assertEquals("Both iterators should return same element",
            listIter1.next(), listIter2.next());
        assertEquals("Both iterators should return same element",
            listIter1.next(), listIter2.next());
    }

    @Test
    public void testListIteratorFromIndexWithConsecutiveCalls()
    {
        HListIterator listIter1 = vector.listIterator(2);
        HListIterator listIter2 = vector.listIterator(2);

        assertEquals("Both should return same first element", listIter1.next(), listIter2.next());
        assertEquals("Both should return same second element", listIter1.next(), listIter2.next());
    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorRemoveWithoutNextPrevious()
    {
        HListIterator listIter = vector.listIterator(2);
        listIter.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorSetWithoutNextPrevious()
    {
        HListIterator listIter = vector.listIterator(2);
        listIter.set("6");
    }

    @Test
    public void testListIteratorWithDuplicateElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("0");
        vec.add("0");

        HListIterator listIter = vec.listIterator();

        assertEquals("First element should be '0'", "0", listIter.next());
        assertEquals("Second element should be '0'", "0", listIter.next());
        assertEquals("Third element should be '0'", "0", listIter.next());

        assertFalse("Should have no more elements", listIter.hasNext());
    }

    @Test
    public void testListIteratorRemoveDuplicateElements()
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

    @Test
    public void testSubListNextIndexCorrectElements()
    {
        while(subListLIterator.hasNext())
            assertEquals("Element should match corresponding at " + subListLIterator.nextIndex(),
                subList.get(subListLIterator.nextIndex()), subListLIterator.next());
    }

    @Test
    public void testSubListNextIndexAtBeginning()
    {
        assertEquals("Should be 0 at beginning", 0, subListLIterator.nextIndex());
    }

    @Test
    public void testSubListNextIndexAfterNext()
    {
        subListLIterator.next();
        assertEquals("Should be 1 after first next", 1, subListLIterator.nextIndex());

        subListLIterator.next();
        assertEquals("Should be 2 after second next", 2, subListLIterator.nextIndex());
    }

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

    @Test
    public void testSubListNextIndexAtEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertEquals("Should be size() at end", subList.size(), subListLIterator.nextIndex());
    }

    @Test
    public void testSubListNextAfterAdd()
    {
        subListLIterator.add("0");
        assertEquals("Should update after add", 1, subListLIterator.nextIndex());
    }

    @Test
    public void testSubListNextAfterRemove()
    {
        subListLIterator.next();
        subListLIterator.remove();
        assertEquals("Should update after remove", 0, subListLIterator.nextIndex());
    }

    @Test
    public void testSubListNextAfterSet()
    {
        subListLIterator.next();
        subListLIterator.set("6");
        assertEquals("Should not update after set", 1, subListLIterator.nextIndex());
    }

    @Test
    public void testSubListPreviousIndexCorrectElements()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        while(subListLIterator.hasPrevious())
            assertEquals("Element should match corresponding index at " + subListLIterator.previousIndex(),
                subList.get(subListLIterator.previousIndex()), subListLIterator.previous());
    }

    @Test
    public void testSubListPreviousIndexAtBeginning()
    {
        assertEquals("Should be -1 at beginning", -1, subListLIterator.previousIndex());
    }

    @Test
    public void testSubListPreviousIndexAfterNext()
    {
        subListLIterator.next();
        assertEquals("Should be 0 after first next", 0, subListLIterator.previousIndex());

        subListLIterator.next();
        assertEquals("Should be 1 after second next", 1, subListLIterator.previousIndex());
    }

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

    @Test
    public void testSubListPreviousIndexAtEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertEquals("Should be size() - 1 at end", subList.size() - 1, subListLIterator.previousIndex());
    }

    @Test
    public void testSubListPreviousIndexAfterAdd()
    {
        subListLIterator.add("0");
        assertEquals("Should update after add", 0, subListLIterator.previousIndex());
    }

    @Test
    public void testSubListPreviousIndexAfterRemove()
    {
        subListLIterator.next();
        subListLIterator.remove();
        assertEquals("SHould update after remove", -1, subListLIterator.previousIndex());
    }

    @Test
    public void testSubListPreviousAfterSet()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.set("6");

        assertEquals("Should not update after set", 0, subListLIterator.previousIndex());
    }

    @Test
    public void testEmptySubListOperations()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Should not have previous", listIter.hasPrevious());
        assertFalse("Should not have next", listIter.hasNext());
        assertEquals("Should be at -1", -1, listIter.previousIndex());
        assertEquals("Should be at 0", 0, listIter.nextIndex());
    }

    @Test
    public void testSubListHasNextAtTheBeginning()
    {
        assertTrue("SubList should have been created and filled properly",
            subListLIterator.hasNext());
    }

    @Test
    public void testSubListHasNextInTheMiddle()
    {
        subListLIterator.next();
        subListLIterator.next();

        assertTrue("SubList should have some more elements", subListLIterator.hasNext());
    }

    @Test
    public void testSubListHasNextAtTheEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertFalse("hasNext should return false after full emptying iteration",
            subListLIterator.hasNext());
    }

    @Test
    public void testSubListHasPreviousAtTheBeginning()
    {
        assertFalse("Should return false at the beginning",
            subListLIterator.hasPrevious());
    }

    @Test
    public void testSubListHasPreviousInTheMiddle()
    {
        subListLIterator.next();
        subListLIterator.next();

        assertTrue("Should return true after moving forward",
            subListLIterator.hasPrevious());
    }

    @Test
    public void testSubListHasPreviousAtTheEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertTrue("Should return true even at the end", subListLIterator.hasPrevious());
    }

    @Test
    public void testSubListNextReturnsCorrectElementsForwardAndBackward()
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

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionWhenNoMoreElements()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListPreviousThrowsExceptionAtBeginning()
    {
        subListLIterator.previous();
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListHasNextThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        subListLIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListHasPreviousThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        subListLIter.previous();
    }

    @Test
    public void testSubListHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        assertFalse("Empty collection should have no elements",
            subListLIter.hasNext());
    }

    @Test
    public void testSubListHasPreviousOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        assertFalse("Empty collection should have no elements",
            subListLIter.hasPrevious());
    }

    @Test
    public void testSubListNextOnSingleElementCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, vec.size());
        HListIterator subListLIter = sub.listIterator();

        assertTrue("Should have one element", subListLIter.hasNext());
        assertEquals("Should return the single element", "0", subListLIter.next());
        assertFalse("Should have no more elements", subListLIter.hasNext());
    }

    @Test
    public void testSubListPreviousOnSingleElementCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, vec.size());
        HListIterator subListLIter = sub.listIterator();

        subListLIter.next();
        assertTrue("Should have one element", subListLIter.hasPrevious());
        assertEquals("Should return the single element", "0", subListLIter.previous());
        assertFalse("Should have no more elements", subListLIter.hasPrevious());
    }

    @Test(expected = NoSuchElementException.class)
    public void testsubListNextThrowsExceptionWhenNoMoreElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, vec.size());
        HListIterator subListLIter = sub.listIterator();

        subListLIter.next();
        subListLIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListPreviousThrowsExceptionWhenNoMoreElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, vec.size());
        HListIterator subListLIter = sub.listIterator();

        subListLIter.next();
        subListLIter.previous();
        subListLIter.previous();
    }

    @Test
    public void testSubListNextWithAllNull()
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

    @Test
    public void testSubListPreviousWithAllNull()
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

    @Test
    public void testSubListMultipleIteratorsAreIndependent()
    {
        HListIterator subListLIter1 = subList.listIterator();
        HListIterator subListLIter2 = subList.listIterator();

        subListLIter1.next();
        subListLIter1.next();

        assertEquals("Second iterator should still be at the beginning",
            "2", subListLIter2.next());
        assertNull("First iterator should have iterated",
            subListLIter1.next());
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsException()
    {
        subListLIterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionAfterNext()
    {
        subListLIterator.next();
        subListLIterator.remove();
        subListLIterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionAfterPrevious()
    {
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.remove();
        subListLIterator.remove();
    }

    @Test
    public void testSubListRemoveAfterNext()
    {
        subListLIterator.next();
        subListLIterator.remove();

        assertEquals("Size should decrease by 1", 2, subList.size());
        assertEquals("First element should be '3'", "3", subList.get(0));
        assertFalse("Should not contain removed element", subList.contains("2"));
    }

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

    @Test
    public void testSubListRemoveThenHasNext()
    {
        subListLIterator.next();
        subListLIterator.remove();

        assertEquals("Size should be 2", 2, subList.size());
        assertTrue("Should still have elements", subListLIterator.hasNext());
        assertEquals("Next element should be '3'", "3", subListLIterator.next());
    }

    @Test
    public void testSubListRemoveThenHasPrevious()
    {
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.remove();

        assertEquals("Size should be 2", 2, subList.size());
        assertFalse("Should not have previous elements", subListLIterator.hasPrevious());
    }

    @Test
    public void testSubListRemoveAllElementsNext()
    {
        while(subListLIterator.hasNext())
        {
            subListLIterator.next();
            subListLIterator.remove();
        }

        assertTrue("Collection should be empty after removing all the elements", subList.isEmpty());
        assertEquals("Size should be 0", 0, subList.size());
        assertFalse("Iterator should have no more elements", subListLIterator.hasNext());
        assertFalse("Iterator should have no more elements", subListLIterator.hasPrevious());
    }

    @Test
    public void testSubListRemoveAllElementsPrevious()
    {
        while(subListLIterator.hasNext())
        {
            subListLIterator.next();
        }

        while(subListLIterator.hasPrevious())
        {
            subListLIterator.previous();
            subListLIterator.remove();
        }

        assertTrue("Collection should be empty after removing all the elements", subList.isEmpty());
        assertEquals("Size should be 0", 0, subList.size());
        assertFalse("Iterator should have no more elements", subListLIterator.hasNext());
        assertFalse("Iterator should have no more elements", subListLIterator.hasPrevious());
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator();

        subListLIter.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionAfterAdd()
    {
        subListLIterator.next();
        subListLIterator.add("6");
        subListLIterator.remove();
    }

    @Test
    public void testSubListRemoveAffectsOtherIterators()
    {
        HListIterator subListLIter1 = subList.listIterator();
        HListIterator subListLIter2 = subList.listIterator();

        subListLIter1.next();
        subListLIter1.remove();

        assertEquals("Second iterator should see the modified collection", "3", subListLIter2.next());
    }

    @Test
    public void testSubListSetAfterNext()
    {
        subListLIterator.next();
        subListLIterator.set("6");

        assertEquals("Element should have been replaces", "6", subList.get(0));
        assertEquals("Next element should still be '3'", "3", subListLIterator.next());
    }

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

    @Test
    public void testSubListSetReplacesLastReturnedElement()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.previous();
        subListLIterator.next();
        subListLIterator.set("6");

        assertEquals("Element should have been replaced", "6", subList.get(1));
        assertEquals("Previous element shoudl be the replaced value", "6", subListLIterator.previous());
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListSetThrowsExceptionWithoutNextOrPrevious()
    {
        subListLIterator.set("6");
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListSetThrowsExcpetionAfterRemove()
    {
        subListLIterator.next();
        subListLIterator.remove();
        subListLIterator.set("6");
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListSetThrowsExceptionAfterAdd()
    {
        subListLIterator.next();
        subListLIterator.add("6");
        subListLIterator.set("7");
    }

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

    @Test
    public void testSubListAddBeforeNext()
    {
        subListLIterator.add("6");

        assertEquals("Size should increase by 1", 4, subList.size());
        assertEquals("First element should be the new element", "6", subList.get(0));
        assertEquals("Next should return original first element", "2", subListLIterator.next());
    }

    @Test
    public void testSubListAddBeforePrevious()
    {
        subListLIterator.add("6");
        assertEquals("Previous should return the added element", "6", subListLIterator.previous());
    }

    @Test
    public void testSubListAddAfterNext()
    {
        subListLIterator.next();
        subListLIterator.add("6");

        assertEquals("Size should increase by 1", 4, subList.size());
        assertEquals("Second element should be the new element", "6", subList.get(1));
        assertEquals("Next should return original second element", "3", subListLIterator.next());
    }

    @Test
    public void testSubListAddAfterPrevious()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.previous();
        subListLIterator.add("6");

        assertEquals("Size should increase by 1", 4, subList.size());
        assertEquals("Second element should be the new element", "6", subList.get(1));
        assertEquals("Next should return original second element", "3", subListLIterator.next());
    }

    @Test
    public void testSubListAddAtEnd()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.add("6");

        assertEquals("Size should increase by 1", 4, subList.size());
        assertEquals("Last element should be new element", "6", subList.get(3));
        assertEquals("Previous should return new element", "6", subListLIterator.previous());
    }

    @Test
    public void testSubListAddInMiddle()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.add("6");

        assertEquals("Size should increase by 1", 4, subList.size());
        assertEquals("Element at index 2 should be new element", "6", subList.get(2));
        assertEquals("Next should return element after inserted", null, subListLIterator.next());
    }

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

    @Test
    public void testSubListSingleElementCollectionBackAndForth()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 1);
        HListIterator subListLIter = sub.listIterator();

        assertTrue("Should have next", subListLIter.hasNext());
        assertFalse("Should not have previous", subListLIter.hasPrevious());
        assertEquals("Should return the single element", "1", subListLIter.next());
        assertFalse("Should not have next", subListLIter.hasNext());
        assertTrue("Should have previous", subListLIter.hasPrevious());
    }

    @Test
    public void testSubListListIteratorAtBeginning()
    {

        HListIterator subListLIter = subList.listIterator(0);

        assertTrue("Should have next at beginning", subListLIter.hasNext());
        assertFalse("Should not have previous at beginning", subListLIter.hasPrevious());
        assertEquals("Next index should be 0", 0, subListLIter.nextIndex());
        assertEquals("Previous index should be -1", -1, subListLIter.previousIndex());
        assertEquals("First element should be correct", "2", subListLIter.next());
    }

    @Test
    public void testSubListListIteratorAtMiddle()
    {
        HListIterator subListLIter = subList.listIterator(1);

        assertTrue("Should have next at middle", subListLIter.hasNext());
        assertTrue("Should have previous at middle", subListLIter.hasPrevious());
        assertEquals("Next index should be 1", 1, subListLIter.nextIndex());
        assertEquals("Previous index should be 0", 0, subListLIter.previousIndex());
        assertEquals("Next element should be correct", "3", subListLIter.next());
        assertEquals("Previous element should be correct", "3", subListLIter.previous());
    }

    @Test
    public void testSubListListIteratorAtEnd()
    {
        HListIterator subListLIter = subList.listIterator(subList.size());

        assertFalse("Should not have next at end", subListLIter.hasNext());
        assertTrue("Should have previous at end", subListLIter.hasPrevious());
        assertEquals("Next index should be size", subList.size(), subListLIter.nextIndex());
        assertEquals("Previous index should be size - 1", subList.size() - 1, subListLIter.previousIndex());
        assertNull("Last element should be correct", subListLIter.previous());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListListIteratorWithNegativeIndex()
    {
        subList.listIterator(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListListIteratorWithIndexGreaterThanSize()
    {
        subList.listIterator(subList.size() + 1);
    }

    @Test
    public void testSubListListIteratorEmptyVector()
    {
        VectorAdapter vec = new VectorAdapter();
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);
        HListIterator subListLIter = sub.listIterator(0);

        assertFalse("Collection should not have next", subListLIter.hasNext());
        assertFalse("Collection should not have previous", subListLIter.hasPrevious());
        assertEquals("Next index should be 0", 0, subListLIter.nextIndex());
        assertEquals("Previous index should be -1", -1, subListLIter.previousIndex());
    }

    @Test
    public void testSubListListIteratorSingleElementVector()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        SubListAdapter sub = (SubListAdapter) vec.subList(0, 1);

        HListIterator subListLIter = sub.listIterator(0);
        assertEquals("Should return single element", "1", subListLIter.next());

        subListLIter = sub.listIterator(1);
        assertEquals("Should return single element via previous", "1", subListLIter.previous());
    }

    @Test
    public void testSubListListIteratorForwardIterationFromIndex()
    {
        HListIterator subListLIter = subList.listIterator(1);

        assertEquals("First element from index 1", "3", subListLIter.next());
        assertNull("Second element from index 1", subListLIter.next());
        assertFalse("Should have no more elements", subListLIter.hasNext());
    }

    @Test
    public void testSubListListIteratorBackwardIterationFromIndex()
    {
        HListIterator subListLIter = subList.listIterator(3);

        assertNull("First element backward from index 3", subListLIter.previous());
        assertEquals("Second element backward from index 3", "3", subListLIter.previous());
        assertEquals("Third element backward from index 3", "2", subListLIter.previous());
        assertFalse("Should have no more previous elements", subListLIter.hasPrevious());
    }

    @Test
    public void testSubListListIteratorBidirectionalIterationFromIndex()
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

    @Test
    public void testSubListListIteratorRemoveFromMiddleIndex()
    {
        HListIterator subListLIter = subList.listIterator(1);

        subListLIter.next();
        subListLIter.remove();

        assertEquals("Size should decrease", 2, subList.size());
        assertEquals("Element at index 1 should be null", null, subList.get(1));
        assertFalse("Should not contain removed element", subList.contains("3"));
    }

    @Test
    public void testSubListListIteratorRemoveAfterPreviousFromIndex()
    {
        HListIterator subListLIter = subList.listIterator(3);

        subListLIter.previous();
        subListLIter.remove();

        assertEquals("Size should decrease", 2, subList.size());
        assertEquals("Element at index 1 should be 3", "3", subList.get(1));
        assertFalse("Should not contain null element", subList.contains(null));
    }

    @Test
    public void testSubListListIteratorSetFromIndex()
    {
        HListIterator subListLIter = subList.listIterator(0);

        subListLIter.next();
        subListLIter.set("6");

        assertEquals("Element should be modified", "6", subList.get(0));
        assertEquals("Next should return next element", "3", subListLIter.next());
    }

    @Test
    public void testSubListListIteratorSetAfterPreviousFromIndex()
    {
        HListIterator subListLIter = subList.listIterator(2);

        subListLIter.previous();
        subListLIter.set("6");

        assertEquals("Element should be modified", "6", subList.get(1));
        assertEquals("Next should return modified element", "6", subListLIter.next());
    }

    @Test
    public void testSubListListIteratorAddFromIndex()
    {
        HListIterator subListLIter = subList.listIterator(1);

        subListLIter.add("6");

        assertEquals("Size should increase", 4, subList.size());
        assertEquals("New element should be at index 1", "6", subList.get(1));
        assertEquals("Next should return original element", "3", subListLIter.next());
    }

    @Test
    public void testSubListListIteratorAddAfterNext()
    {
        HListIterator subListLIter = subList.listIterator(1);

        subListLIter.next();
        subListLIter.add("6");

        assertEquals("Size should increase", 4, subList.size());
        assertEquals("New element should be at index 2", "6", subList.get(2));
        assertNull("Next should return the original element", subListLIter.next());
    }

    @Test
    public void testSubListListIteratorAddAfterPrevious()
    {
        HListIterator subListLIter = subList.listIterator(2);

        subListLIter.previous();
        subListLIter.add("6");

        assertEquals("Size should increase", 4, subList.size());
        assertEquals("New element should be at index 1", "6", subList.get(1));
        assertEquals("Next should return the original element", "3", subListLIter.next());
    }

    @Test
    public void testSubListListIteratorAddAtEndFromIndex()
    {
        HListIterator subListLIter = subList.listIterator(subList.size());

        subListLIter.add("6");

        assertEquals("Size should increase", 4, subList.size());
        assertEquals("New element should be at the end", "6", subList.get(3));
        assertEquals("Previous should return new element", "6", subListLIter.previous());
    }

    @Test
    public void testSubListListIteratorIndexConsistency()
    {
        HListIterator subListLIter = subList.listIterator(1);

        assertEquals(1, subListLIter.nextIndex());
        assertEquals(0, subListLIter.previousIndex());

        subListLIter.next();
        assertEquals(2, subListLIter.nextIndex());
        assertEquals(1, subListLIter.previousIndex());

        subListLIter.previous();
        assertEquals(1, subListLIter.nextIndex());
        assertEquals(0, subListLIter.previousIndex());
    }

    @Test
    public void testSubListListIteratorMultipleOperationsFromIndex()
    {
        HListIterator subListLIter = subList.listIterator(1);

        subListLIter.add("6");
        assertEquals("Size after add", 4, subList.size());
        assertEquals("Next index after add", 2, subListLIter.nextIndex());

        assertEquals("Next after add", "3", subListLIter.next());
        subListLIter.set("7");
        assertEquals("Element should be set", "7", subList.get(2));

        assertEquals("Previous after set", "7", subListLIter.previous());

        subListLIter.remove();

        assertEquals("Size after remove", 3, subList.size());
        assertEquals("Next index after remove", 2, subListLIter.nextIndex());
    }

    @Test
    public void testSubListListIteratorFromIndexEqualsRegularIterator()
    {
        HListIterator subListLIter1 = subList.listIterator();
        HListIterator subListLIter2 = subList.listIterator(2);

        subListLIter1.next();
        subListLIter1.next();

        assertEquals("Both iterators should return same element",
            subListLIter1.next(), subListLIter2.next());
        assertFalse("Both iterators should have no more elements", subListLIter1.hasNext());
        assertFalse("Both iterators should have no more elements", subListLIter2.hasNext());
    }

    @Test
    public void testSubListListIteratorFromIndexWithConsecutiveCalls()
    {
        HListIterator subListLIter1 = subList.listIterator(1);
        HListIterator subListLIter2 = subList.listIterator(1);

        assertEquals("Both should return same first element", subListLIter1.next(), subListLIter2.next());
        assertEquals("Both should return same second element", subListLIter1.next(), subListLIter2.next());
        assertFalse("Both should have no more elements", subListLIter1.hasNext());
        assertFalse("Both should have no more elements", subListLIter2.hasNext());
    }


    @Test(expected = IllegalStateException.class)
    public void testSubListListIteratorRemoveWithoutNextPrevious()
    {
        HListIterator subListLIter = subList.listIterator(1);
        subListLIter.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListListIteratorSetWithoutNextPrevious()
    {
        HListIterator subListLIter = subList.listIterator(1);
        subListLIter.set("6");
    }

    @Test
    public void testSubListListIteratorWithDuplicateElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("0");
        vec.add("0");
        SubListAdapter subList = (SubListAdapter) vec.subList(0, 3);
        HListIterator subListLIter = subList.listIterator();

        assertEquals("First element should be '0'", "0", subListLIter.next());
        assertEquals("Second element should be '0'", "0", subListLIter.next());
        assertEquals("Third element should be '0'", "0", subListLIter.next());

        assertFalse("Should have no more elements", subListLIter.hasNext());
    }
}
