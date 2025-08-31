package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HIterator;

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
    public void testNextProperIteration()
    {
        assertEquals("First element should match", "1", iterator.next());
        assertEquals("Second element should match", "2", iterator.next());
        assertEquals("Third element should match", "3", iterator.next());
        assertNull("Fourth element should be null", iterator.next());
        assertEquals("Fifth element should match", "5", iterator.next());

        assertFalse("Collection should still no more elements", iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasNextThrowsException()
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
    public void testNextWithAllNull()
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
    public void testRemoveThrowsExceptionWithoutNext()
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

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HIterator iter = vec.iterator();

        iter.remove();
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

    @Test
    public void testSubListHasNextAtTheBeginning()
    {
        assertTrue("SubList iterator should have next elements",
            subIterator.hasNext());
    }

    @Test
    public void testSubListHasNextInTheMiddle()
    {
        subIterator.next();
        subIterator.next();

        assertTrue("SubList should have more elements", subIterator.hasNext());
    }

    @Test
    public void testSubListHasNextAtTheEnd()
    {
        while(subIterator.hasNext())
            subIterator.next();

        assertFalse("SubList collection should return false after full emptying iteration",
            subIterator.hasNext());
    }

    @Test
    public void testSubListNextProperIteration()
    {
        assertEquals("First element of subList should be '2'", "2", subIterator.next());
        assertEquals("Second element of subList should be '3'", "3", subIterator.next());
        assertNull("Third element of subList should be null", subIterator.next());

        assertFalse("Should have no more elements", subIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsException()
    {
        while(subIterator.hasNext())
            subIterator.next();

        subIterator.next();
    }

    @Test
    public void testSubListHasNextOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HIterator subIter = sub.iterator();

        assertFalse("Empty subList should have no elements",
            subIter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextThrowsExceptionOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HIterator subIter = sub.iterator();

        subIter.next();
    }

    @Test
    public void testSubListWithAllNull()
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
    public void testSubListRemoveAtTheBeginning()
    {
        subIterator.next();
        subIterator.remove();

        assertEquals("SubList size should decrease", 2, subList.size());
        assertEquals("Original vector size should decrease", 4, vector.size());
        assertFalse("Element should be removed from subList", subList.contains("2"));
        assertFalse("Element should be removed from original vector", vector.contains("2"));
    }

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

    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionWithoutNext()
    {
        subIterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testSubListRemoveThrowsExceptionAfterRemove()
    {
        subIterator.next();
        subIterator.remove();
        subIterator.remove();
    }

    @Test
    public void testNestedSubListRemove()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HIterator subIter = subSub.iterator();

        subIter.next();
        subIter.remove();

        assertEquals("Nested sublist size should be 1", 1, subSub.size());
        assertEquals("Parent sublist size should be 3", 3, sub.size());
        assertEquals("Original vector size should be 4", 4, vector.size());
        assertEquals("Nested sublist should contain only null", null, subSub.get(0));
    }

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

    @Test
    public void testNestedSubListNextOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HIterator subIter = subSub.iterator();

        assertFalse("Empty sub-subList should have no elements", subIter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNestedSubListThrowsExceptionWhenEmpty()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HIterator subIter = subSub.iterator();

        subIter.next();
    }

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
