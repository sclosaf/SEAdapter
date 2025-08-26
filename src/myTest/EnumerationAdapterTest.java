package myTest;

import myAdapter.VectorAdapter;
import myAdapter.SubListAdapter;
import myAdapter.HEnumeration;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * RICONTROLLARE COERENZA
 * */

public class EnumerationAdapterTest
{
    private VectorAdapter vector;
    private SubListAdapter subList;
    private SubListAdapter fullSubList;
    private HEnumeration enumeration;
    private HEnumeration subEnumeration;
    private HEnumeration fullSubEnumeration;

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

        subList = (SubListAdapter) vector.subList(1, 4);
        subEnumeration = subList.elements();

        fullSubList = (SubListAdapter) vector.subList(0, vector.size());
        fullSubEnumeration = fullSubList.elements();
    }

    @Test
    public void testHasMoreElementsOnCollection()
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
    public void testProperIterationOverElements()
    {
        assertTrue("Collection should still have some elements", enumeration.hasMoreElements());
        assertEquals("First element should match", "1", enumeration.nextElement());

        assertTrue("Collection should still have some elements", enumeration.hasMoreElements());
        assertEquals("Second element should match", "2", enumeration.nextElement());

        assertTrue("Collection should still have some elements", enumeration.hasMoreElements());
        assertEquals("Third element should match", "3", enumeration.nextElement());

        assertTrue("Collection should still have some elements", enumeration.hasMoreElements());
        assertNull("Fourth element should be null", enumeration.nextElement());

        assertTrue("Collection should still have some elements", enumeration.hasMoreElements());
        assertEquals("Fifth element should match", "5", enumeration.nextElement());

        assertFalse("Colleciton should have no more elements", enumeration.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextElementThrowsExceptionWhenNoMoreElements()
    {
        while(enumeration.hasMoreElements())
            enumeration.nextElement();

        enumeration.nextElement();
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextElementThrowsExceptionOnEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        HEnumeration enumer = vec.elements();
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

    @Test(expected = NoSuchElementException.class)
    public void testNextElementThrowsExceptionWithOneElement()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");

        HEnumeration enumer = vec.elements();
        enumer.nextElement();
        enumer.nextElement();
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

        assertEquals("First enumeration should be at the iterated position",
            "3", enumer1.nextElement());

        assertEquals("Second enumeration should still be at the beginning",
            "1", enumer2.nextElement());
    }

    @Test
    public void testSubListHasMoreElementsOnCollection()
    {
        assertTrue("SubList collection should have been created properly",
            subEnumeration.hasMoreElements());
    }

    @Test
    public void testSubListHasMoreElementsReturnFalseAfterIteration()
    {
        while(subEnumeration.hasMoreElements())
            subEnumeration.nextElement();

        assertFalse("hasMoreElements should return false after full emptying iteration",
            subEnumeration.hasMoreElements());
    }

    @Test
    public void testSubListNextElementReturnsCorrectElements()
    {
        assertTrue("Collection should still have some elements", subEnumeration.hasMoreElements());
        assertEquals("First element of subList should match", "2", subEnumeration.nextElement());

        assertTrue("Collection should still have some elements", subEnumeration.hasMoreElements());
        assertEquals("Second element of subList should match", "3", subEnumeration.nextElement());

        assertTrue("Collection should still have some elements", subEnumeration.hasMoreElements());
        assertNull("Third element of subList should be null", subEnumeration.nextElement());

        assertFalse("Collection should have no more elements after iteration", subEnumeration.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextElementThrowsExceptionWhenNoMoreElements()
    {
        while(subEnumeration.hasMoreElements())
            subEnumeration.nextElement();

        subEnumeration.nextElement();
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextElementThrowsExceptionOnEmptyCollecion()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 0);
        HEnumeration subEnumer = sub.elements();

        subEnumer.nextElement();
    }

    @Test
    public void testSubListHasMoreElementsOnEmptyCollection()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 1);
        HEnumeration subEnumer = sub.elements();

        assertFalse("Empty subList should have no elements",
            subEnumer.hasMoreElements());
    }

    @Test
    public void testSubListEnumerationWithSingleElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 1);
        HEnumeration subEnumer = sub.elements();

        assertTrue("Should have one element", subEnumer.hasMoreElements());
        assertEquals("Should return the single element", "1", subEnumer.nextElement());
        assertFalse("Should have no more elements after retrieval", subEnumer.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListNextElementThrowsExceptionWithOneElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 2);

        HEnumeration subEnumer = sub.elements();
        subEnumer.nextElement();
        subEnumer.nextElement();
    }

    @Test
    public void testSubListEnumerationWithSingleNullElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(3, 4);
        HEnumeration subEnumer = sub.elements();

        assertTrue("Should have one element", subEnumer.hasMoreElements());
        assertNull("Should return null", subEnumer.nextElement());
        assertFalse("Should have no more elements", subEnumer.hasMoreElements());
    }

    @Test
    public void testSubListEnumerationWithAllNullElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);
        vec.add(null);

        SubListAdapter sub = (SubListAdapter) vec.subList(1, 4);
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
    public void testFullSubListHasMoreElementsOnCollection()
    {
        assertTrue("Collection should have been created and filled properly",
            fullSubEnumeration.hasMoreElements());
    }

    @Test
    public void testFullSubListHasMoreElementsReturnsFalseAfterIteration()
    {
        while(fullSubEnumeration.hasMoreElements())
            fullSubEnumeration.nextElement();

        assertFalse("hasMoreElements should return false after full iteration",
            fullSubEnumeration.hasMoreElements());
    }

    @Test
    public void testFullSubListProperIterationOverElements()
    {

        assertTrue("Should have elements", fullSubEnumeration.hasMoreElements());
        assertEquals("First element should match", "1", fullSubEnumeration.nextElement());

        assertTrue("Should have elements", fullSubEnumeration.hasMoreElements());
        assertEquals("Second element should match", "2", fullSubEnumeration.nextElement());

        assertTrue("Should have elements", fullSubEnumeration.hasMoreElements());
        assertEquals("Third element should match", "3", fullSubEnumeration.nextElement());

        assertTrue("Should have elements", fullSubEnumeration.hasMoreElements());
        assertNull("Fourth element should be null", fullSubEnumeration.nextElement());

        assertTrue("Should have elements", fullSubEnumeration.hasMoreElements());
        assertEquals("Fifth element should match", "5", fullSubEnumeration.nextElement());

        assertFalse("Should have no more elements", fullSubEnumeration.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFullSubListNextElementThrowsExceptionWhenNoMoreElements()
    {
        while(fullSubEnumeration.hasMoreElements())
            fullSubEnumeration.nextElement();

        fullSubEnumeration.nextElement();
    }

    @Test
    public void testFullSubListEnumerationOrderMatchesCollectionOrder()
    {
        for(int i = 0; i < fullSubList.size(); i++)
        {
            Object expected = fullSubList.get(i);
            Object actual = fullSubEnumeration.nextElement();

            if(expected == null)
                assertNull("Element at index " + i + " should be null", actual);
            else
                assertEquals("Element at index " + i + " should match", expected, actual);
        }

        assertFalse("Should have no more elements",
            fullSubEnumeration.hasMoreElements());
    }

    @Test
    public void testMultipleFullSubListEnumerationsAreIndependent()
    {
        HEnumeration fullSubEnumer1 = fullSubList.elements();
        HEnumeration fullSubEnumer2 = fullSubList.elements();

        fullSubEnumer1.nextElement();
        fullSubEnumer1.nextElement();

        assertEquals("First enumeration should be at position 2",
            "3", fullSubEnumer1.nextElement());
        assertEquals("Second enumeration should be at beginning",
            "1", fullSubEnumer2.nextElement());
    }

    @Test
    public void testSubListOfSubListEnumeration()
    {
        SubListAdapter subSub = (SubListAdapter) subList.subList(1, 3);
        HEnumeration enumer = subSub.elements();

        assertTrue("Sub-subList should have elements", enumer.hasMoreElements());
        assertEquals("First element should be 3", "3", enumer.nextElement());

        assertTrue("Sub-subList should have elements", enumer.hasMoreElements());
        assertNull("Second element should be null", enumer.nextElement());

        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testNestedSubListEnumerationOrder()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 3);
        HEnumeration enumer = subSub.elements();

        assertEquals("First element should be 3", "3", enumer.nextElement());
        assertNull("Second element should be null", enumer.nextElement());
        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testEmptySubListOfSubList()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HEnumeration enumer = subSub.elements();

        assertFalse("Empty sub-subList should have no elements", enumer.hasMoreElements());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListOfSubListThrowsExceptionWhenEmpty()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 4);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 1);
        HEnumeration enumer = subSub.elements();

        enumer.nextElement();
    }

    @Test
    public void testSubListOfSubListWithSingleElement()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 3);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 2);
        HEnumeration enumer = subSub.elements();

        assertTrue("Should have one element", enumer.hasMoreElements());
        assertEquals("Should return the single element", "2", enumer.nextElement());
        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testDeepNestedSubListEnumeration()
    {
        SubListAdapter sub = (SubListAdapter) vector.subList(0, 5);
        SubListAdapter subSub = (SubListAdapter) sub.subList(1, 4);
        SubListAdapter subSubSub = (SubListAdapter) subSub.subList(1, 3);
        HEnumeration enumer = subSubSub.elements();

        assertEquals("First element should be 3", "3", enumer.nextElement());
        assertNull("Second element should be null", enumer.nextElement());
        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testEnumerationWithPrimitiveTypes()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add(Integer.valueOf(1));
        vec.add(Boolean.valueOf(true));
        vec.add(Double.valueOf(0.11));

        HEnumeration enumer = vec.elements();

        assertTrue("First element should be a Integer", enumer.nextElement() instanceof Integer);
        assertTrue("Second element should be a Boolean", enumer.nextElement() instanceof Boolean);
        assertTrue("Third element should be a Double", enumer.nextElement() instanceof Double);
        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testEnumerationWithMixedElementTypes()
    {
        VectorAdapter mixedVec = new VectorAdapter();
        mixedVec.add("str");
        mixedVec.add(123);
        mixedVec.add(null);
        mixedVec.add(new VectorAdapter());

        HEnumeration enumer = mixedVec.elements();

        assertTrue("First element should be a string", enumer.nextElement() instanceof String);
        assertEquals("Second element should be an int", 123, enumer.nextElement());
        assertNull("Third element should be null", enumer.nextElement());
        assertTrue("Fourth element should be a VectorAdapter", enumer.nextElement() instanceof VectorAdapter);
        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test
    public void testEnumerationWithDuplicateElements()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("0");
        vec.add("0");

        HEnumeration enumer = vec.elements();
        assertEquals("First element should be '0'", "0", enumer.nextElement());
        assertEquals("Second element should be '0'", "0", enumer.nextElement());
        assertEquals("Third element should be '0'", "0", enumer.nextElement());
        assertFalse("Should have no more elements", enumer.hasMoreElements());
    }

    @Test(timeout = 1000)
    public void testEnumerationPerformanceOnLargeCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        for(int i = 0; i < 10000; i++)
            vec.add(i);


        HEnumeration enumer = vec.elements();

        for(int i = 0; i < 10000 && enumer.hasMoreElements(); i++)
            assertEquals("The " + i + "-th element should be at the correct position", i, enumer.nextElement());
    }
}
