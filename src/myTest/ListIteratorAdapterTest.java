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
    private SubListAdapter fullSubList;
    private HListIterator listIterator;
    private HListIterator subListIterator;
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
        subListIterator = subList.listIterator();

        fullSubList = (SubListAdapter) vector.subList(0, vector.size());
        subListLIterator = subList.listIterator();
    }

    @Test
    public void testHasNextOnCollection()
    {
        assertTrue("Collection should have been created and filled properly",
            listIterator.hasNext());
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
    public void testNextReturnsCorrectElements()
    {
        assertTrue("Collection should still have some elements", listIterator.hasNext());
        assertEquals("First element should match", "1", listIterator.next());

        assertTrue("Collection should still have some elements", listIterator.hasNext());
        assertEquals("Second element should match", "2", listIterator.next());

        assertTrue("Collection should still have some elements", listIterator.hasNext());
        assertEquals("Third element should match", "3", listIterator.next());

        assertTrue("Collection should still have some elements", listIterator.hasNext());
        assertNull("Fourth element should be null", listIterator.next());

        assertTrue("Collection should still have some elements", listIterator.hasNext());
        assertEquals("Fifth element should match", "5", listIterator.next());

        assertFalse("Collection should still no more elements", listIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasNextThrowsExceptionWhenNoMoreElements()
    {
        while(listIterator.hasNext())
            listIterator.next();

        listIterator.next();
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
}
