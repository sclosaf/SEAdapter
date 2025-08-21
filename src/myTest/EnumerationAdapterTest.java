package myTest;

import myAdapter.HEnumeration;
import myAdapter.VectorAdapter;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// DA RICONTROLLARE CON DOCUMENTAZIONE

public class EnumerationAdapterTest
{
    private VectorAdapter vector;
    private HEnumeration enumeration;

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
    }

    @Test
    public void testHasMoreElementsOnNonEmptyCollection()
    {
        assertTrue("Non-empty collection should have more elements",
                  enumeration.hasMoreElements());
    }

    @Test
    public void testNextElementReturnsCorrectElements()
    {
        assertEquals("First element should match", "1", enumeration.nextElement());
        assertEquals("Second element should match", "2", enumeration.nextElement());
        assertEquals("Third element should match", "3", enumeration.nextElement());
        assertNull("Fourth element should be null", enumeration.nextElement());
        assertEquals("Fifth element should match", "", enumeration.nextElement());
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

        assertFalse("Empty collection should have no more elements",
                   enumer.hasMoreElements());
    }

    @Test
    public void testEnumerationWithSingleElement()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("Single element");

        HEnumeration enumer = vec.elements();

        assertTrue("Should have one element", enumer.hasMoreElements());
        assertEquals("Should return the single element", "Single element", enumer.nextElement());
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

        assertTrue("Should have elements", enumer.hasMoreElements());
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

            if (expected == null)
                assertNull("Element at index " + i + " should be null", actual);
            else
                assertEquals("Element at index " + i + " should match",
                           expected, actual);
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

        assertEquals("Second enumeration should start from beginning",
                   "1", enumer2.nextElement());

        assertEquals("First enumeration should be at correct position",
                   "3", enumer1.nextElement());
    }

    @Test
    public void testEnumerationIsNotAffectedByCollectionModifications()
    {
        vector.remove(0);

        assertEquals("Should return original first element", "1", enumeration.nextElement());
        assertEquals("Should return original second element", "2", enumeration.nextElement());
    }

    @Test
    public void testHasMoreElementsReturnsFalseAfterIteration()
    {
        while(enumeration.hasMoreElements())
            enumeration.nextElement();

        assertFalse("hasMoreElements should return false after full iteration",
                   enumeration.hasMoreElements());
    }
}
