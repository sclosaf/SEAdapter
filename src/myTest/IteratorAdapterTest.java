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

/*
 * NOMI METODI
 * RICONTROLLA COERENZA
 * */

public class IteratorAdapterTest
{
    private VectorAdapter vector;
    private SubListAdapter subList;
    private HIterator iterator;
    private HIterator subIterator;

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

    @After
    public void tearDown()
    {
        vector = null;
        subList = null;
        iterator = null;
        subIterator = null;
    }

    @Test
    public void testHasNextAtTheBeginning()
    {
        assertTrue("Collection should have been created and filled properly",
            iterator.hasNext());
    }

    @Test
    public void testHasNextInTheMiddle()
    {
        iterator.next();
        iterator.next();

        assertTrue("Collection should have some more elements", iterator.hasNext());
    }

    @Test
    public void testHasNextAtTheEnd()
    {
        while(iterator.hasNext())
            iterator.next();

        assertFalse("hasNext should return false after full emptying iteration",
            iterator.hasNext());
    }

    @Test
    public void testNextReturnsCorrectElements()
    {
        assertEquals("First element should match", "1", iterator.next());
        assertEquals("Second element should match", "2", iterator.next());
        assertEquals("Third element should match", "3", iterator.next());
        assertNull("Fourth element should be null", iterator.next());
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

    @Test
    public void testHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HIterator iter = vec.iterator();

        assertFalse("Empty collection should have no elements",
            iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasNextThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        HIterator iter = vec.iterator();
        iter.next();
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
    public void testNextWithAllNullElements()
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

    @Test
    public void testMultipleIteratorsAreIndependent()
    {
        HIterator iter1 = vector.iterator();
        HIterator iter2 = vector.iterator();

        iter1.next();
        iter1.next();

        assertEquals("First iterator should be at the beginning",
            "3", iter1.next());
        assertEquals("Second iterator should still be at the beginning",
            "1", iter2.next());
    }

    @Test
    public void testRemoveAtTheBeginning()
    {
        iterator.next();
        iterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertEquals("First element should be '2'", "2", vector.get(0));
        assertFalse("Should not contain removed element", vector.contains("1"));
    }

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
        assertEquals("First element of subList should be '2'", "2", subIterator.next());
        assertEquals("Second element of subList should be '3'", "3", subIterator.next());
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

    @Test
    public void testIteratorOnEmptySubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HIterator iter = sub.iterator();

        assertFalse("Empty subList should have no elements",
            iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HIterator subIter = sub.iterator();

        subIter.next();
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
    public void testSubListIteratorWithAllNullElements()
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
    public void testRemoveAtTheBeginningOfSubList()
    {
        subIterator.next();
        subIterator.remove();

        assertEquals("SubList size should decrease", 2, subList.size());
        assertEquals("Original vector size should decrease", 4, vector.size());
        assertFalse("Element should be removed from subList", subList.contains("2"));
        assertFalse("Element should be removed from original vector", vector.contains("2"));
    }

    @Test
    public void testRemoveInMiddleOfSubList()
    {
        subIterator.next();
        subIterator.next();
        subIterator.remove();

        assertEquals("SubList size should be 2", 2, subList.size());
        assertTrue("Should contain other elements", subIterator.hasNext());
        assertFalse("Should not contain '3'", subList.contains("3"));
    }

    @Test
    public void testRemoveInTheEndOfSubList()
    {
        while(subIterator.hasNext())
            subIterator.next();

        subIterator.remove();

        assertEquals("SubList size should be 2", 2, subList.size());
        assertFalse("Should not contain other elements", subIterator.hasNext());
        assertFalse("Should not contain null", subList.contains(null));
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
    public void testNestedSubListThrowsExceptionWhenEmpty()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HIterator iter = subSub.iterator();

        iter.next();
    }

    @Test
    public void testNestedSubListWithSingleElement()
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

    @Test
    public void testIteratorWithPrimitiveTypes()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(Integer.valueOf(1));
        vec.add(Boolean.valueOf(true));
        vec.add(Double.valueOf(0.11));

        HIterator iter = vec.iterator();

        assertTrue("First element should be an Integer", iter.next() instanceof Integer);
        assertTrue("Second element should be a Boolean", iter.next() instanceof Boolean);
        assertTrue("Third element should be a Double", iter.next() instanceof Double);
        assertFalse("Should have no more elements", iter.hasNext());
    }

    @Test
    public void testRemoveWithPrimitiveTypes()
    {
        VectorAdapter vec = new VectorAdapter();

        vec.add(Integer.valueOf(1));
        vec.add(Double.valueOf(3.14));

        HIterator iter = vec.iterator();
        iter.next();
        iter.remove();

        assertEquals("Size should be 1", 1, vec.size());
        assertEquals("First element should be Double", Double.valueOf(3.14), vec.get(0));
    }

    @Test
    public void testIteratorWithMixedTypes()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("str");
        vec.add(123);
        vec.add(null);
        vec.add(new VectorAdapter());

        HIterator iter = vec.iterator();

        assertEquals("First element should be a string", "str", iter.next());
        assertEquals("Second element shoudl be an int", 123, iter.next());
        assertNull("Third element should be a null", iter.next());
        assertTrue("Fourth element should be a VectorAdapter", iter.next() instanceof VectorAdapter);

        assertFalse("Should have no more elements", iter.hasNext());
    }

    @Test
    public void testIteratorWithDuplicateElements()
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
