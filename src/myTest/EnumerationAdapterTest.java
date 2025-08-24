package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HEnumeration;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * RIVEDERE BENE
 * RICONTROLLARE CON DOCUMENTAZIONE
 * */

public class EnumerationAdapterTest
{
    private VectorAdapter vector;
    private SubListAdapter subList;
    private HEnumeration enumeration;
    private HEnumeration subEnumeration;

    @Before
    public void setUp()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add(null);
        vector.add("5");

        enumeration = vector.elements();

        subList = (SubListAdapter)vector.subList(1, 4);

        subEnumeration = subList.elements();
    }

    @Test
    public void testHasMoreElementsOnNonEmptyCollection()
    {
        assertTrue("Collection should have been created and filled properly",
            enumeration.hasMoreElements());
    }

    @Test
    public void testHasMoreElementsReturnsFalseAfterIteration()
    {
        while(enumeration.hasMoreElements())
            enumeration.nextElement();

        assertFalse("hasMoreElements should return false after full emptying iteration",
                   enumeration.hasMoreElements());
    }

    @Test
    public void testNextElementReturnsCorrectElements()
    {
        assertEquals("First element should match", "1", enumeration.nextElement());
        assertEquals("Second element should match", "2", enumeration.nextElement());
        assertEquals("Third element should match", "3", enumeration.nextElement());
        assertNull("Fourth element should be null", enumeration.nextElement());
        assertEquals("Fifth element should match", "5", enumeration.nextElement());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextElementThrowsExceptionWhenNoMoreElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");

        HEnumeration enumer = vec.elements();
        enumer.nextElement();
        enumer.nextElement();
    }

    @Test
    public void testHasMoreElementsOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        HEnumeration enumer = vec.elements();

        assertFalse("Empty collection should have no elements",
            enumer.hasMoreElements());
    }

    @Test
    public void testEnumerationWithSingleElement()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");

        HEnumeration enumer = vec.elements();

        assertTrue("Should have one element", enumer.hasMoreElements());
        assertEquals("Should return the single element", "1", enumer.nextElement());
        assertFalse("Should have no more elements after retrieval", enumer.hasMoreElements());
    }

    @Test
    public void testEnumerationWithAllNullElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        HEnumeration enumer = vec.elements();

        assertTrue("Should manage null elements", enumer.hasMoreElements());
        assertNull("First element should be null", enumer.nextElement());
        assertNull("Second element should be null", enumer.nextElement());
        assertNull("Third element should be null", enumer.nextElement());
        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testEnumerationOrderMatchesCollectionOrder()
    {
        for(int i = 0; i < vector.size(); i++)
        {
            Object expected = vector.get(i);
            Object actual = enumeration.nextElement();

            if(expected == null)
                assertNull("Element at index " + i + " should be null", actual);
            else
                assertEquals("Element at index " + i + " should match", expected, actual);
        }

        assertFalse("Should have no more elements after full iteration",
            enumeration.hasMoreElements());
    }

    @Test
    public void testMultipleEnumerationsAreIndependent()
    {
        HEnumeration enumer1 = vector.elements();
        HEnumeration enumer2 = vector.elements();

        enumer1.nextElement();
        enumer1.nextElement();

        assertEquals("Second enumeration should still be at the beginning",
            "1", enumer2.nextElement());

        assertEquals("First enumeration should be at the iterated position",
            "3", enumer1.nextElement());
    }

    @Test
    public void testSubListHasMoreElementsOnNonEmptyCollection()
    {
        assertTrue("SubList collection should have been created properly",
            subEnumeration.hasMoreElements());
    }

    @Test
    public void testSubListNextElementReturnsCorrectElements()
    {
        assertEquals("First element of subList should match", "2", subEnumeration.nextElement());
        assertEquals("Second element of subList should match", "3", subEnumeration.nextElement());
        assertNull("Third element of subList should be null", subEnumeration.nextElement());
        assertFalse("Should have no more elements after iteration", subEnumeration.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextElementThrowsExceptionWhenNoMoreElements()
    {
        while(subEnumeration.hasMoreElements())
            subEnumeration.nextElement();

        subEnumeration.nextElement();
    }

    @Test
    public void testSubListHasMoreElementsOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter)vector.subList(1, 1);
        HEnumeration subEnumer = sub.elements();

        assertFalse("Empty subList should have no elements",
            subEnumer.hasMoreElements());
    }

    @Test
    public void testSubListEnumerationWithSingleElement()
    {
        VectorAdapter sub = (SubListAdapter)vector.subList(0, 1);
        HEnumeration subEnumer = sub.elements();

        assertTrue("Should have one element", subEnumer.hasMoreElements());
        assertEquals("Should return the single element", "1", subEnumer.nextElement());
        assertFalse("Should have no more elements after retrieval", subEnumer.hasMoreElements());
    }

    @Test
    public void testSubListEnumerationWithAllNullElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);
        vec.add(null);

        SubListAdapter sub = (SubListAdapter)vec.subList(1, 4);
        HEnumeration subEnumer = sub.elements();

        assertTrue("Should have elements", subEnumer.hasMoreElements());
        assertNull("First element should be null", subEnumer.nextElement());
        assertNull("Second element should be null", subEnumer.nextElement());
        assertNull("Third element should be null", subEnumer.nextElement());
        assertFalse("Should have no more elements", subEnumer.hasMoreElements());
    }

    @Test
    public void testSubListEnumerationOrderMatchesSubListOrder()
    {
        for(int i = 0; i < subList.size(); i++)
        {
            Object expected = subList.get(i);
            Object actual = subEnumeration.nextElement();

           if(expected == null)
                assertNull("Element at index " + i + " should be null", actual);
            else
                assertEquals("Element at index " + i + " should match", expected, actual);
        }

        assertFalse("Should have no more elements after full emptying iteration",
            subEnumeration.hasMoreElements());
    }

    @Test
    public void testMultipleSubListEnumerationsAreIndependent()
    {
        HEnumeration subEnumer1 = subList.elements();
        HEnumeration subEnumer2 = subList.elements();

        subEnumer1.nextElement();

        assertEquals("Second enumeration should still be at the beginning",
            "2", subEnumer2.nextElement());

        assertEquals("First enumeration should be at iterated position",
           "3", subEnumer1.nextElement());
    }

    @Test
    public void testSubListHasMoreElementsReturnsFalseAfterIteration()
    {
        while(subEnumeration.hasMoreElements())
            subEnumeration.nextElement();

        assertFalse("hasMoreElements should return false after full iteration",
            subEnumeration.hasMoreElements());
    }

    @Test
    public void testSubListEnumerationWithSingleNullElement()
    {
        SubListAdapter sub = (SubListAdapter)vector.subList(3, 4);
        HEnumeration subEnumer = sub.elements();

        assertTrue("Should have one element", subEnumer.hasMoreElements());
        assertNull("Should return null", subEnumer.nextElement());
        assertFalse("Should have no more elements", subEnumer.hasMoreElements());
    }
}
