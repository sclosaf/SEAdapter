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
 * RIVEDERE BENE
 * CONTROLLA DOCUMENTAZIONE
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

    @Test
    public void testHasNextOnNonEmptyCollection()
    {
        assertTrue("Iterator should have next elements on non-empty collection",
                   iterator.hasNext());
    }

    @Test
    public void testHasNextReturnsFalseAfterIteration()
    {
        while(iterator.hasNext())
            iterator.next();

        assertFalse("hasNext should return false after full iteration",
                    iterator.hasNext());
    }

    @Test
    public void testHasNextOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HIterator iter = vec.iterator();

        assertFalse("Empty collection should have no next elements",
                    iter.hasNext());
    }

    @Test
    public void testHasNextOnSubList()
    {
        assertTrue("SubList iterator should have next elements",
                   subIterator.hasNext());
    }

    @Test
    public void testNextReturnsCorrectElements()
    {
        assertEquals("First element should match", "1", iterator.next());
        assertEquals("Second element should match", "2", iterator.next());
        assertEquals("Third element should match", "3", iterator.next());
        assertNull("Fourth element should be null", iterator.next());
        assertEquals("Fifth element should match", "5", iterator.next());
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
    public void testNextOnSingleElementCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("single");

        HIterator iter = vec.iterator();

        assertTrue("Should have one element", iter.hasNext());
        assertEquals("Should return the single element", "single", iter.next());
        assertFalse("Should have no more elements", iter.hasNext());
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
    public void testNextOrderMatchesCollectionOrder()
    {
        for(int i = 0; i < vector.size(); i++)
        {
            Object expected = vector.get(i);
            Object actual = iterator.next();

            if(expected == null)
                assertNull("Element at index " + i + " should be null", actual);
            else
                assertEquals("Element at index " + i + " should match", expected, actual);

        }

        assertFalse("Should have no more elements after full iteration",
                    iterator.hasNext());
    }

    @Test
    public void testRemoveRemovesCurrentElement()
    {
        iterator.next();
        iterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertEquals("First element should now be '2'", "2", vector.get(0));
        assertFalse("Should not contain removed element", vector.contains("1"));
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
    public void testRemoveCanBeCalledAfterNext()
    {
        iterator.next();
        iterator.remove();

        iterator.next();
        iterator.remove();

        assertEquals("Size should be 3 after two removals", 3, vector.size());
        assertEquals("First element should now be '3'", "3", vector.get(0));
    }

    @Test
    public void testRemoveNullElement()
    {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();

        iterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertFalse("Should not contain null element", vector.contains(null));
    }

    @Test
    public void testRemoveLastElement()
    {
        while(iterator.hasNext())
            iterator.next();

        iterator.remove();

        assertEquals("Size should decrease by 1", 4, vector.size());
        assertEquals("Last element should now be null", null, vector.get(3));
        assertFalse("Should not contain removed element", vector.contains("5"));
    }

    @Test
    public void testRemoveAllElements()
    {
        while(iterator.hasNext())
        {
            iterator.next();
            iterator.remove();
        }

        assertTrue("Collection should be empty after removing all elements",
                   vector.isEmpty());
        assertEquals("Size should be 0", 0, vector.size());
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
        assertEquals("First iterator should be at iterated position",
                     "3", iter1.next());
    }

    @Test
    public void testRemoveAffectsOtherIterators()
    {
        HIterator iter1 = vector.iterator();
        HIterator iter2 = vector.iterator();

        iter1.next();
        iter1.remove();

        assertEquals("Second iterator should see modified collection",
                     "2", iter2.next());
        assertFalse("Second iterator should not see removed element",
                    vector.contains("1"));
    }

    @Test
    public void testSubListHasNext()
    {
        assertTrue("SubList iterator should have next elements",
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
    public void testSubListMultipleIterators()
    {
        HIterator subIter1 = subList.iterator();
        HIterator subIter2 = subList.iterator();

        subIter1.next();

        assertEquals("Second iterator should still be at the beginning",
                     "2", subIter2.next());
        assertEquals("First iterator should be at iterated position",
                     "3", subIter1.next());
    }

    @Test
    public void testIteratorOnEmptySubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(2, 2);
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

    @Test
    public void testIteratorConsistencyAfterStructuralModifications()
    {
        iterator.next();

        vector.add("6");

        assertEquals("Should continue with original iteration", "2", iterator.next());
        assertEquals("Should continue with original iteration", "3", iterator.next());
    }
}
