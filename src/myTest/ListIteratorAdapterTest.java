package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HListIterator;
import myAdapter.ListIteratorAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


// VEDERE TEST AI, tmp, ESPANDERE E AGGIUNGERE TEST SU METODI IN PIù


/*
 * RIVEDERE BENE
 * CONTROLLA DOCUMENTAZIONE
 * CONFRONTA CON ITERATOR E ENUMERATION
 * DA ESTENDERE
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
        listIterator.next();
        listIterator.next();
        listIterator.previous();

        assertEquals("Should be 1 after previous", 1, listIterator.nextIndex());
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
        assertEquals("nextIndex should update after remove", 0, listIterator.nextIndex());
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
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        assertEquals("Should be 0 after previous", 0, listIterator.previousIndex());
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
        assertEquals("nextIndex should update after add", 0, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexAfterRemove()
    {
        listIterator.next();
        listIterator.remove();
        assertEquals("nextIndex should update after remove", -1, listIterator.previousIndex());
    }

    @Test
    public void testHasNextOnCollection()
    {
        assertTrue("Collection should have been created and filled properly",
            listIterator.hasNext());
    }

    @Test
    public void testHasPreviousAtBeginning()
    {
        assertFalse("Should return false at the beginning",
            listIterator.hasPrevious());
    }

    @Test
    public void testHasPreviousAndNextAfterIteration()
    {
        listIterator.next();
        assertTrue("Should return true after moving forward",
            listIterator.hasPrevious());
    }

    @Test
    public void testHasNextReturnsFalseAfterIteration()
    {
        while(listIterator.hasNext())
            listIterator.next();

        assertFalse("hasNext should return false after full emptying iteration",
            listIterator.hasNext());
    }

    @Test
    public void testNextReturnsCorrectElementsForwardAndBackward()
    {
        assertTrue("Collection should have some elements", listIterator.hasNext());
        assertEquals("First element should match", "1", listIterator.next());

        assertTrue("Collection should have some elements", listIterator.hasNext());
        assertEquals("Second element should match", "2", listIterator.next());

        assertTrue("Collection should have some elements", listIterator.hasNext());
        assertEquals("Third element should match", "3", listIterator.next());

        assertTrue("Collection should have some elements", listIterator.hasNext());
        assertNull("Fourth element should be null", listIterator.next());

        assertTrue("Collection should have some elements", listIterator.hasNext());
        assertEquals("Fifth element should match", "5", listIterator.next());

        assertFalse("Collection should no more elements", listIterator.hasNext());

        assertTrue("Collection should have some elements", listIterator.hasPrevious());
        assertEquals("Fifth element should match", "5", listIterator.previous());

        assertTrue("Collection should have some elements", listIterator.hasPrevious());
        assertNull("Fourth element should be null", listIterator.previous());

        assertTrue("Collection should have some elements", listIterator.hasPrevious());
        assertEquals("Third element should match", "3", listIterator.previous());

        assertTrue("Collection should have some elements", listIterator.hasPrevious());
        assertEquals("Second element should match", "2", listIterator.previous());

        assertTrue("Collection should have some elements", listIterator.hasPrevious());
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

    @Test
    public void testHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Empty collection should have no elements",
            listIter.hasNext());
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

    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsExceptionWhenNoMoreElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");

        HListIterator listIter = vec.listIterator();
        listIter.next();
        listIter.next();
    }

    @Test
    public void testNextWithAllNullElements()
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
    public void testNextOrderMatchesCollectionOrder()
    {
        for(int i = 0; i < vector.size(); i++)
        {
            Object expected = vector.get(i);
            Object actual = listIterator.next();

            if(expected == null)
                assertNull("Element at index " + i + " should be null", actual);
            else
                assertEquals("Element at index " + i + " should match", expected, actual);

        }

        assertFalse("Should have no more elements after full emptying iteration",
            listIterator.hasNext());
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
        assertEquals("First iterator should be at the beginning",
            "3", listIter1.next());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionBeforeNext()
    {
        listIterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterRemove()
    {
        listIterator.next();
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
    public void testRemoveMultipleElements()
    {
        listIterator.next();
        listIterator.remove();

        listIterator.next();
        listIterator.remove();

        listIterator.next();
        listIterator.remove();

        assertEquals("Size should be 2", 2, vector.size());
        assertEquals("First element should be null", null, vector.get(0));
        assertEquals("Second element should be '5'", "5", vector.get(1));
    }

    @Test
    public void testRemoveLastElement()
    {
        while(listIterator.hasNext())
            listIterator.next();

        listIterator.remove();

        assertEquals("Size should be 4", 4, vector.size());
        assertEquals("Last element should now be null", null, vector.get(3));
        assertFalse("Should not contain the removed element", vector.contains("5"));
    }

    @Test
    public void testRemoveNullElement()
    {
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.next();

        listIterator.remove();

        assertEquals("Size should be 4", 4, vector.size());
        assertFalse("Null element should be removed", vector.contains(null));
    }

    @Test
    public void testRemoveThenHasNext()
    {
        listIterator.next();
        listIterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertTrue("Should still have elements", listIterator.hasNext());
        assertEquals("Next element should be '2'", "2", listIterator.next());
    }

    @Test
    public void testRemoveAllElements()
    {
        while(listIterator.hasNext())
        {
            listIterator.next();
            listIterator.remove();
        }

        assertTrue("Collection should be empty after removing all the elements", vector.isEmpty());
        assertEquals("Size should be 0", 0, vector.size());
        assertFalse("Iterator should have no more elements", listIterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        listIter.remove();
    }

    @Test
    public void testRemoveThenAdd()
    {
        listIterator.next();
        listIterator.remove();

        vector.add("6");

        assertEquals("Size should be 5 after remove and add", 5, vector.size());
        assertEquals("Last element should be '6'", "6", vector.get(4));
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

        assertEquals("Second iterator should see the modificed collection", "2", listIter2.next());
        assertFalse("Second iterator should not see the removed element", vector.contains("1"));
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
    public void testAddBeforeNextElement()
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
    public void testAddNullElement()
    {
        listIterator.add(null);

        assertEquals("Size should increase by 1", 6, vector.size());
        assertNull("First element should be null", vector.get(0));
        assertEquals("Next should return original first element", "1", listIterator.next());
    }

    @Test
    public void testMultipleAddOperations()
    {
        listIterator.add("6");
        listIterator.add("7");
        listIterator.add("8");


        assertEquals("Size should increase by 3", 8, vector.size());
        assertEquals("Elements should be added in correct order", "6", vector.get(0));
        assertEquals("Elements should be added in correct order", "7", vector.get(1));
        assertEquals("Elements should be added in correct order", "8", vector.get(2));
    }

    @Test
    public void testEmptyCollectionOperations()
    {
        VectorAdapter vec = new VectorAdapter();
        HListIterator listIter = vec.listIterator();

        assertFalse("Should not have previous", listIter.hasPrevious());
        assertFalse("Should not have next", listIter.hasNext());
        assertEquals("previousIndex should be -1", -1, listIter.previousIndex());
        assertEquals("nextIndex should be 0", 0, listIter.nextIndex());
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
        assertEquals("Previous index should be size-1", vector.size() - 1, listIter.previousIndex());
        assertEquals("Last element should be correct", "6", listIter.previous());
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
        assertEquals("Fourth element from index 2", "6", listIter.next());
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

        assertEquals("Size should decrease", 5, vector.size());
        assertEquals("Element at index 2 should be null", null, vector.get(2));
        assertFalse("Should not contain removed element", vector.contains("3"));
    }

    @Test
    public void testListIteratorRemoveAfterPreviousFromIndex()
    {
        HListIterator listIter = vector.listIterator(4);

        listIter.previous();
        listIter.remove();

        assertEquals("Size should decrease", 5, vector.size());
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

        assertEquals("Size should increase", 7, vector.size());
        assertEquals("New element should be at index 2", "6", vector.get(2));
        assertEquals("Next should return original element", "3", listIter.next());
    }

    @Test
    public void testListIteratorAddAtEndFromIndex()
    {
        HListIterator listIter = vector.listIterator(vector.size());

        listIter.add("6");

        assertEquals("Size should increase", 7, vector.size());
        assertEquals("New element should be at the end", "6", vector.get(6));
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
        assertEquals("Size after add", 7, vector.size());
        assertEquals("Next index after add", 3, listIter.nextIndex());

        assertEquals("Next after add", "3", listIter.next());
        listIter.set("7");
        assertEquals("Element should be set", "7", vector.get(3));

        assertEquals("Previous after set", "7", listIter.previous());

        listIter.remove();

        assertEquals("Size after remove", 6, vector.size());
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
    public void testListIteratorFromIndexAfterStructuralModifications()
    {
        HListIterator listIter = vector.listIterator(3);

        vector.remove(1);

        assertEquals("Should return element at new position", null, listIter.next());
        assertEquals("Should return next element", "5", listIter.next());
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

    // DA AGGIUNGERE I TEST CHE CI SONO SOPRA A QUELLI SOTTO



    @Test
    public void testSubListHasNext()
    {
        assertTrue("SubList list iterator should have next elements",
            subListLIterator.hasNext());
    }

    @Test
    public void testSubListHasNextReturnsFalseAfterIteration()
    {

        while(subListLIterator.hasNext())
            subListLIterator.next();

        assertFalse("SubList collection should return false after full emptying iteration",
            subListLIterator.hasNext());
    }

    @Test
    public void testSubListNextReturnsCorrectElements()
    {
        assertTrue("Collection should still have some elements", subListLIterator.hasNext());
        assertEquals("First element of subList should be '2'", "2", subListLIterator.next());

        assertTrue("Collection should still have some elements", subListLIterator.hasNext());
        assertEquals("Second element of subList should be '3'", "3", subListLIterator.next());

        assertTrue("Collection should still have some elements", subListLIterator.hasNext());
        assertNull("Third element of subList should be null", subListLIterator.next());

        assertFalse("Should have no more elements", subListLIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionWhenNoMoreElements()
    {
        while(subListLIterator.hasNext())
            subListLIterator.next();

        subListLIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HListIterator subListIter = sub.listIterator();

        subListIter.next();
    }

    @Test
    public void testListIteratorOnEmptySubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HListIterator subListIter = sub.listIterator();

        assertFalse("Empty subList should have no elements",
            subListIter.hasNext());
    }

    @Test
    public void testListIteratorOnSingleElementSubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 1);
        HListIterator subListIter = sub.listIterator();

        assertTrue("Should have one element", subListIter.hasNext());
        assertEquals("Should return the single element", "1", subListIter.next());
        assertFalse("Should have no more elements", subListIter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionWithOneElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 2);
        HListIterator subListIter = sub.listIterator();

        subListIter.next();
        subListIter.next();
    }

    @Test
    public void testSubListListIteratorWithSingleNullElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(3, 4);
        HListIterator subListIter = sub.listIterator();

        assertTrue("Should have one element", subListIter.hasNext());
        assertNull("Should return null", subListIter.next());
        assertFalse("Should have no more elements", subListIter.hasNext());
    }

    @Test
    public void testSubListListIteratorWithAllNullElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);
        vec.add(null);

        SubListAdapter sub = (SubListAdapter) vec.subList(1, 4);
        HListIterator subListIter = sub.listIterator();

        assertTrue("Should have elements", subListIter.hasNext());
        assertNull("First element should be null", subListIter.next());
        assertNull("Second element should be null", subListIter.next());
        assertNull("Third element should be null", subListIter.next());
        assertFalse("Should have no more elements", subListIter.hasNext());
    }

    @Test
    public void testSubListListIteratorOrderMatchesSubListOrder()
    {
        for(int i = 0; i < subList.size(); i++)
        {
            Object expected = subList.get(i);
            Object actual = subListLIterator.next();

            if(expected == null)
                assertNull("Element at index " + i + " should be null", actual);
            else
                assertEquals("Element at index " + i + " should match", expected, actual);
        }

        assertFalse("Should have no more elements after full emptying iteration",
            subListLIterator.hasNext());
    }

    @Test
    public void testSubListMultipleListIterators()
    {
        HListIterator subListIter1 = subList.listIterator();
        HListIterator subListIter2 = subList.listIterator();

        subListIter1.next();

        assertEquals("Second iterator should still be at the beginning",
            "2", subListIter2.next());

        assertEquals("First iterator should be at the beginning",
            "3", subListIter1.next());
    }
    @Test
    public void testRemoveAffectsSubList()
    {
        Object elem = subList.get(0);
        subListLIterator.next();
        subListLIterator.remove();

        assertEquals("SubList size should decrease", 2, subList.size());
        assertEquals("Original vector size should decrease", 4, vector.size());
        assertFalse("Element should be removed from subList", subList.contains(elem));
        assertFalse("Element should be removed from original vector", vector.contains(elem));

    }

    @Test
    public void testRemoveInMiddleOfSubList()
    {
        subListLIterator.next();
        subListLIterator.next();
        subListLIterator.remove();

        assertEquals("SubList size should be 2", 2, subList.size());
        assertEquals("Next element should be null", null, subListLIterator.next());
        assertEquals("SubList at index 1 should be null", null, subList.get(1));
    }

    @Test
    public void testRemoveInNestedSubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HListIterator subListIter = subSub.listIterator();

        subListIter.next();
        subListIter.remove();

        assertEquals("Nested sublist size should be 1", 1, subSub.size());
        assertEquals("Parent sublist size should be 3", 3, sub.size());
        assertEquals("Original vector size should be 4", 4, vector.size());
        assertEquals("Nested sublist should contain only null", null, subSub.get(0));
    }

    @Test
    public void testSubListOfSubListListIterator()
    {
        SubListAdapter subSub = (SubListAdapter) subList.subList(1, 3);
        HListIterator subListIter = subSub.listIterator();

        assertTrue("Sub-subList should have elements", subListIter.hasNext());
        assertEquals("First element should be 3", "3", subListIter.next());

        assertTrue("Sub-subList should have elements", subListIter.hasNext());
        assertNull("Second element should be null", subListIter.next());

        assertFalse("Should have no more elements", subListIter.hasNext());
    }

    @Test
    public void testNestedSubListListIteratorOrder()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HListIterator subListIter = subSub.listIterator();

        assertEquals("First element should be 3", "3", subListIter.next());
        assertNull("Second element should be null", subListIter.next());
        assertFalse("Should have no more elements", subListIter.hasNext());
    }

    @Test
    public void testEmptySubListOfSubListListIterator()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HListIterator subListIter = subSub.listIterator();

        assertFalse("Empty sub-subList should have no elements", subListIter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListOfSubListThrowsExceptionWhenEmpty()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HListIterator subListIter = subSub.listIterator();

        subListIter.next();
    }

    @Test
    public void testSubListOfSubListWithSingleElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 3);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 2);
        HListIterator subListIter = subSub.listIterator();

        assertTrue("Should have one element", subListIter.hasNext());
        assertEquals("Should return the single element", "2", subListIter.next());
        assertFalse("Should have no more elements", subListIter.hasNext());
    }

    @Test
    public void testDeepNestedSubListListIterator()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 4);
        SubListAdapter subSubSub = (SubListAdapter) subSub.subList(1, 3);
        HListIterator subListIter = subSubSub.listIterator();

        assertEquals("First element should be 3", "3", subListIter.next());
        assertNull("Second element should be null", subListIter.next());
        assertFalse("Should have no more elements", subListIter.hasNext());
    }










    @Test(timeout = 1000)
    public void testListIteratorPerformanceOnLargeCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        for(int i = 0; i < 10000; i++)
            vec.add(i);

        HListIterator listIter = vec.listIterator();

        for(int i = 0; listIter.hasNext(); i++)
        {
            assertEquals("The " + i + "-th element should be at the correct position", i, listIter.next());
            listIter.remove();
        }

        assertEquals("Size should be 0", 0, vec.size());
    }
}
