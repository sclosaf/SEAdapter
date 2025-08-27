package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HIterator;
import myAdapter.IteratorAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * NOMI METODI
 * RICONTROLLA COERENZA
 * */

public class IteratorAdapterTest
{
    private VectorAdapter vector;
    private SubListAdapter subList;
    private SubListAdapter fullSubList;
    private HIterator iterator;
    private HIterator subIterator;
    private HIterator fullSubIterator;

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

        fullSubList = (SubListAdapter) vector.subList(0, vector.size());
        fullSubIterator = fullSubList.iterator();
    }

    @Test
    public void testHasNextOnCollection()
    {
        assertTrue("Collection should have been created and filled properly",
            iterator.hasNext());
    }

    @Test
    public void testHasNextReturnsFalseAfterIteration()
    {
        while(iterator.hasNext())
            iterator.next();

        assertFalse("hasNext should return false after full emptying iteration",
            iterator.hasNext());
    }

    @Test
    public void testNextReturnsCorrectElements()
    {
        assertTrue("Collection should still have some elements", iterator.hasNext());
        assertEquals("First element should match", "1", iterator.next());

        assertTrue("Collection should still have some elements", iterator.hasNext());
        assertEquals("Second element should match", "2", iterator.next());

        assertTrue("Collection should still have some elements", iterator.hasNext());
        assertEquals("Third element should match", "3", iterator.next());

        assertTrue("Collection should still have some elements", iterator.hasNext());
        assertNull("Fourth element should be null", iterator.next());

        assertTrue("Collection should still have some elements", iterator.hasNext());
        assertEquals("Fifth element should match", "5", iterator.next());

        assertFalse("Collection should still no more elements", iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasNextThrowsExceptionWhenNoMoreElements()
    {
        while(iterator.hasNext())
            iterator.next();

        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasNextThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        HIterator iter = vec.iterator();
        iter.next();
    }

    @Test
    public void testHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HIterator iter = vec.iterator();

        assertFalse("Empty collection should have no elements",
            iter.hasNext());
    }

    @Test
    public void testNextOnSingleElementCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");

        HIterator iter = vec.iterator();

        assertTrue("Should have one element", iter.hasNext());
        assertEquals("Should return the single element", "0", iter.next());
        assertFalse("Should have no more elements", iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsExceptionWhenNoMoreElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");

        HIterator iter = vec.iterator();
        iter.next();
        iter.next();
    }

    @Test
    public void testMultipleIteratorsAreIndependent()
    {
        HIterator iter1 = vector.iterator();
        HIterator iter2 = vector.iterator();

        iter1.next();
        iter1.next();

        assertEquals("Second iterator should still be at the beginning",
            "1", iter2.next());
        assertEquals("First iterator should be at the beginning",
            "3", iter1.next());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionBeforeNext()
    {
        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsExceptionAfterRemove()
    {
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    @Test
    public void testRemoveAfterNext()
    {
        iterator.next();
        iterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertEquals("First element should be '2'", "2", vector.get(0));
        assertFalse("Should not contain removed element", vector.contains("1"));
    }

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
        assertEquals("First element should be null", null, vector.get(0));
        assertEquals("Second element should be '5'", "5", vector.get(1));
    }

    @Test
    public void testRemoveLastElement()
    {
        while(iterator.hasNext())
            iterator.next();

        iterator.remove();

        assertEquals("Size should be 4", 4, vector.size());
        assertEquals("Last element should now be null", null, vector.get(3));
        assertFalse("Should not contain the removed element", vector.contains("5"));
    }

    @Test
    public void testRemoveNullElement()
    {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();

        iterator.remove();

        assertEquals("Size should be 4", 4, vector.size());
        assertFalse("Null element should be removed", vector.contains(null));
    }

    @Test
    public void testRemoveThenHasNext()
    {
        iterator.next();
        iterator.remove();

        assertTrue("Should still have elements", iterator.hasNext());
        assertEquals("Next element should be '2'", "2", iterator.next());
    }

    @Test
    public void testRemoveAllElements()
    {
        while(iterator.hasNext())
        {
            iterator.next();
            iterator.remove();
        }

        assertTrue("Collection should be empty after removing all the elements", vector.isEmpty());
        assertEquals("Size should be 0", 0, vector.size());
        assertFalse("Iterator should have no more elements", iterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HIterator iter = vec.iterator();

        iter.remove();
    }

    @Test
    public void testRemoveThenAdd()
    {
        iterator.next();
        iterator.remove();

        vector.add("6");
        assertEquals("Size should be 5 after remove and add", 5, vector.size());
        assertEquals("Last element should be '6'", "6", vector.get(4));
    }

    @Test
    public void testRemoveAffectsOtherIterators()
    {
        HIterator iter1 = vector.iterator();
        HIterator iter2 = vector.iterator();

        iter1.next();
        iter1.remove();

        assertEquals("Second iterator should see the modificed collection", "2", iter2.next());
        assertFalse("Second iterator should not see the removed element", vector.contains("1"));
    }

    @Test
    public void testSubListHasNext()
    {
        assertTrue("SubList iterator should have next elements",
            subIterator.hasNext());
    }

    @Test
    public void testSubListHasNextReturnsFalseAfterIteration()
    {
        while(subIterator.hasNext())
            subIterator.next();

        assertFalse("SubList collection should return false after full emptying iteration",
            subIterator.hasNext());
    }

    @Test
    public void testSubListNextReturnsCorrectElements()
    {
        assertTrue("Collection should still have some elements", subIterator.hasNext());
        assertEquals("First element of subList should be '2'", "2", subIterator.next());

        assertTrue("Collection should still have some elements", subIterator.hasNext());
        assertEquals("Second element of subList should be '3'", "3", subIterator.next());

        assertTrue("Collection should still have some elements", subIterator.hasNext());
        assertNull("Third element of subList should be null", subIterator.next());

        assertFalse("Should have no more elements", subIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionWhenNoMoreElements()
    {
        while(subIterator.hasNext())
            subIterator.next();

        subIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HIterator subIter = sub.iterator();

        subIter.next();
    }

    @Test
    public void testIteratorOnEmptySubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HIterator iter = sub.iterator();

        assertFalse("Empty subList should have no elements",
            iter.hasNext());
    }

    @Test
    public void testIteratorOnSingleElementSubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 1);
        HIterator iter = sub.iterator();

        assertTrue("Should have one element", iter.hasNext());
        assertEquals("Should return the single element", "1", iter.next());
        assertFalse("Should have no more elements", iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionWithOneElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 2);

        HIterator subIter = sub.iterator();
        subIter.next();
        subIter.next();
    }

    @Test
    public void testSubListIteratorWithSingleNullElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(3, 4);
        HIterator subIter = sub.iterator();

        assertTrue("Should have one element", subIter.hasNext());
        assertNull("Should return null", subIter.next());
        assertFalse("Should have no more elements", subIter.hasNext());
    }

    @Test
    public void testSubListMultipleIterators()
    {
        HIterator subIter1 = subList.iterator();
        HIterator subIter2 = subList.iterator();

        subIter1.next();

        assertEquals("Second iterator should still be at the beginning",
            "2", subIter2.next());

        assertEquals("First iterator should be at the beginning",
            "3", subIter1.next());
    }

    @Test
    public void testRemoveAffectsSubList()
    {
        Object elem = subList.get(0);
        subIterator.next();
        subIterator.remove();

        assertEquals("SubList size should decrease", 2, subList.size());
        assertEquals("Original vector size should decrease", 4, vector.size());
        assertFalse("Element should be removed from subList", subList.contains(elem));
        assertFalse("Element should be removed from original vector", vector.contains(elem));
    }

    @Test
    public void testRemoveInMiddleOfSubList()
    {
        subIterator.next();
        subIterator.next();
        subIterator.remove();

        assertEquals("SubList size should be 2", 2, subList.size());
        assertEquals("Next element should be null", null, subIterator.next());
        assertEquals("SubList at index 1 should be null", null, subList.get(1));
    }

    @Test
    public void testRemoveInNestedSubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HIterator iter = subSub.iterator();

        iter.next();
        iter.remove();

        assertEquals("Nested sublist size should be 1", 1, subSub.size());
        assertEquals("Parent sublist size should be 3", 3, sub.size());
        assertEquals("Original vector size should be 4", 4, vector.size());
        assertEquals("Nested sublist should contain only null", null, subSub.get(0));
    }

    @Test
    public void testSubListOfSubListIterator()
    {
        SubListAdapter subSub = (SubListAdapter) subList.subList(1, 3);
        HIterator iter = subSub.iterator();

        assertTrue("Sub-subList should have elements", iter.hasNext());
        assertEquals("First element should be 3", "3", iter.next());

        assertTrue("Sub-subList should have elements", iter.hasNext());
        assertNull("Second element should be null", iter.next());

        assertFalse("Should have no more elements", iter.hasNext());
    }

    @Test
    public void testNestedSubListIteratorOrder()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HIterator iter = subSub.iterator();

        assertEquals("First element should be 3", "3", iter.next());
        assertNull("Second element should be null", iter.next());
        assertFalse("Should have no more elements", iter.hasNext());
    }

    @Test
    public void testEmptySubListOfSubListIterator()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HIterator iter = subSub.iterator();

        assertFalse("Empty sub-subList should have no elements", iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListOfSubListThrowsExceptionWhenEmpty()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HIterator iter = subSub.iterator();

        iter.next();
    }

    @Test
    public void testSubListOfSubListWithSingleElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 3);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 2);
        HIterator iter = subSub.iterator();

        assertTrue("Should have one element", iter.hasNext());
        assertEquals("Should return the single element", "2", iter.next());
        assertFalse("Should have no more elements", iter.hasNext());
    }

    @Test
    public void testDeepNestedSubListIterator()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 4);
        SubListAdapter subSubSub = (SubListAdapter) subSub.subList(1, 3);
        HIterator iter = subSubSub.iterator();

        assertEquals("First element should be 3", "3", iter.next());
        assertNull("Second element should be null", iter.next());
        assertFalse("Should have no more elements", iter.hasNext());
    }

    @Test(timeout = 1000)
    public void testIteratorPerformanceOnLargeCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        for(int i = 0; i < 10000; i++)
            vec.add(i);

        HIterator iter = vec.iterator();

        for(int i = 0; iter.hasNext(); i++)
        {
            assertEquals("The " + i + "-th element should be at the correct position", i, iter.next());
            iter.remove();
        }

        assertEquals("Size should be 0", 0, vec.size());
    }
}
