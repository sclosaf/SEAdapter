package myTest;

import myAdapter.VectorAdapter;
import myAdapter.HCollection;
import myAdapter.HEnumeration;
import myAdapter.HIterator;
import myAdapter.HListIterator;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/*
 * RIVEDERE BENE
 * CONTROLLA DOCUMENTAZIONE
 * DA ESTENDERE
 * RICONTROLLA COERENZA
 * NOMI METODI
 * */
public class VectorAdapterTest
{
    private VectorAdapter vector;

    @Before
    public void setUp()
    {
        vector = null;
    }

    @After
    public void tearDown()
    {
        vector = null;
    }

    @Test
    public void testConstructorWithCapacityAndIncrementValidParameters()
    {
        vector = new VectorAdapter(5, 2);

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 5", 5, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityAndIncrementZeroCapacity()
    {
        vector = new VectorAdapter(0, 5);

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 0", 0, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithCapacityAndIncrementNegativeCapacity()
    {
        vector = new VectorAdapter(-1, 5);
    }

    @Test
    public void testConstructorWithCapacityAndIncrementZeroIncrement()
    {
        vector = new VectorAdapter(10, 0);

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 10", 10, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityAndIncrementNegativeIncrement()
    {
        vector = new VectorAdapter(7, -2);

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 7", 7, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityAndIncrementCapacityGrowth()
    {
        vector = new VectorAdapter(2, 10);

        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 3 after adding three elements", 3, vector.size());
        assertEquals("First element should be '1'", "1", vector.get(0));
        assertEquals("Second element should be '2'", "2", vector.get(1));
        assertEquals("Third element should be '3'", "3", vector.get(2));
        assertEquals("Capacity should be 12", 12, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityAndIncrementDoubleGrowth()
    {
        vector = new VectorAdapter(3, 0);

        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 4 after adding elements", 4, vector.size());
        assertEquals("First element should be '1'", "1", vector.get(0));
        assertEquals("Second element should be '2'", "2", vector.get(1));
        assertEquals("Third element should be '3'", "3", vector.get(2));
        assertEquals("Fourth element should be '4'", "4", vector.get(3));
        assertEquals("Capacity should be 6", 6, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityAndIncrementFilledNullElements()
    {
        vector = new VectorAdapter(4, 5);

        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertNull("Element " + i + "-th should be null", vector.get(i));

        assertEquals("Capacity should be 9", 9, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityAndIncrementFilledMixedElements()
    {
        vector = new VectorAdapter(0, 0);

        vector.add(123);
        vector.add("str");
        vector.add(3.2);
        vector.add(new VectorAdapter());

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 4 after adding elements", 4, vector.size());
        assertEquals("First element should match", 123, vector.get(0));
        assertEquals("Second element should match", "str", vector.get(1));
        assertEquals("Third element should match", 3.2, vector.get(2));
        assertTrue("Fourth element should match", vector.get(3) instanceof VectorAdapter);
        assertEquals("Capacity should be 4", 4, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityAndIncrementFilledDuplicateElements()
    {
        vector = new VectorAdapter(5, 5);

        vector.add("0");
        vector.add("0");
        vector.add("0");
        vector.add("0");
        vector.add("0");

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertEquals("Element " + i + "-th should match", "0", vector.get(i));

        assertEquals("Capacity should be 5", 5, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityAndIncrementExtremeGrowth()
    {
        vector = new VectorAdapter(1, 1);

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);


        assertEquals("Should grow to accommodate 1000 elements", 1000, vector.size());
        assertEquals("Element 0 should be correct", "0", vector.get(0));
        assertEquals("Element 999 should be correct", "999", vector.get(999));
        assertEquals("Capacity should be 1000", 1000, vector.capacity());
    }

    @Test
    public void testConstructorWithCapacityValidParameter()
    {
        vector = new VectorAdapter(8);

        assertNotNull("Should have be created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 8", 8, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityZeroCapacity()
    {
        vector = new VectorAdapter(0);

        assertNotNull("Should be created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 0", 0, vector. capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithCapacityNegativeCapacity()
    {
        vector = new VectorAdapter(-5);
    }

    @Test
    public void testConstructorWithCapacityDefaultIncrement()
    {
        vector = new VectorAdapter(3);

        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 4 after adding elements", 4, vector.size());
        assertEquals("First element should be '1'", "1", vector.get(0));
        assertEquals("Second element should be '2'", "2", vector.get(1));
        assertEquals("Third element should be '3'", "3", vector.get(2));
        assertEquals("Fourth element should be '4'", "4", vector.get(3));
        assertEquals("Capacity should be 6", 6, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityFilledNullElements()
    {
        vector = new VectorAdapter(4);

        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertNull("Element " + i + "-th should be null", vector.get(i));

        assertEquals("Capacity should be 8", 8, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityFilledMixedElements()
    {
        vector = new VectorAdapter(0);

        vector.add(123);
        vector.add("str");
        vector.add(3.2);
        vector.add(new VectorAdapter());

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 4 after adding elements", 4, vector.size());
        assertEquals("First element should match", 123, vector.get(0));
        assertEquals("Second element should match", "str", vector.get(1));
        assertEquals("Third element should match", 3.2, vector.get(2));
        assertTrue("Fourth element should match", vector.get(3) instanceof VectorAdapter);
        assertEquals("Capacity should be 4", 4, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityFilledDuplicateElements()
    {
        vector = new VectorAdapter(5);

        vector.add("0");
        vector.add("0");
        vector.add("0");
        vector.add("0");
        vector.add("0");

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertEquals("Element " + i + "-th should match", "0", vector.get(i));

        assertEquals("Capacity should be 5", 5, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCapacityExtremeGrowth()
    {
        vector = new VectorAdapter(1);

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);


        assertEquals("Should grow to accommodate 1000 elements", 1000, vector.size());
        assertEquals("Element 0 should be correct", "0", vector.get(0));
        assertEquals("Element 999 should be correct", "999", vector.get(999));
        assertEquals("Capacity should be 1024", 1024, vector.capacity());
    }

    @Test
    public void testDefaultConstructor()
    {
        vector = new VectorAdapter();

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 10", 10, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    @Test
    public void testDefaultConstructorDefaultCapacity()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 10; i++)
            vector.add("" + i);

        vector.add("10");

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 11 after adding ten elements", 11, vector.size());

        for(int i = 0;  i < 11; i++)
            assertEquals("Element " + i + "-th should match", "" + i, vector.get(i));

        assertEquals("Capacity should be 20", 20, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testDefaultConstructorFilledNullElements()
    {
        vector = new VectorAdapter();

        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertNull("Element " + i + "-th should be null", vector.get(i));

        assertEquals("Capacity should be 10", 10, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testDefaultConstructorFilledMixedElements()
    {
        vector = new VectorAdapter();

        vector.add(123);
        vector.add("str");
        vector.add(3.2);
        vector.add(new VectorAdapter());

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 4 after adding elements", 4, vector.size());
        assertEquals("First element should match", 123, vector.get(0));
        assertEquals("Second element should match", "str", vector.get(1));
        assertEquals("Third element should match", 3.2, vector.get(2));
        assertTrue("Fourth element should match", vector.get(3) instanceof VectorAdapter);
        assertEquals("Capacity should be 10", 10, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testDefaultConstructorFilledDuplicateElements()
    {
        vector = new VectorAdapter();

        vector.add("0");
        vector.add("0");
        vector.add("0");
        vector.add("0");
        vector.add("0");

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertEquals("Element " + i + "-th should match", "0", vector.get(i));

        assertEquals("Capacity should be 10", 10, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testDefaultConstructorWithExtremeGrowth()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertEquals("Should grow to accommodate 1000 elements", 1000, vector.size());
        assertEquals("Element 0 should be correct", "0", vector.get(0));
        assertEquals("Element 999 should be correct", "999", vector.get(999));
        assertEquals("Capacity should be 1080", 1280, vector.capacity());
    }

    @Test
    public void testConstructorWithCollectionValidCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        vec.add("1");
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter(vec);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should match source size", vec.size(), vector.size());
        assertEquals("First element should match", vec.get(0), vector.get(0));
        assertEquals("Second element should match", vec.get(1), vector.get(1));
        assertEquals("Third element should match", vec.get(2), vector.get(2));
        assertEquals("Capacity should be 3", 3, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCollectionEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        vector = new VectorAdapter(vec);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 0 for empty collection", 0, vector.size());
        assertEquals("Capacity should be 0", 0, vector.capacity());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithCollectionNullCollection()
    {
        vector = new VectorAdapter(null);
    }

    @Test
    public void testConstructorWithCollectionFilledNullElements()
    {
        VectorAdapter vec = new VectorAdapter();

        vec.add(null);
        vec.add(null);
        vec.add(null);
        vec.add(null);
        vec.add(null);

        vector = new VectorAdapter(vec);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertNull("Element " + i + "-th should be null", vector.get(i));

        assertEquals("Capacity should be 5", 5, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCollectionFilledMixedElements()
    {
        VectorAdapter vec = new VectorAdapter();

        vec.add(123);
        vec.add("str");
        vec.add(3.2);
        vec.add(new VectorAdapter());

        vector = new VectorAdapter(vec);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 4 after adding elements", 4, vector.size());
        assertEquals("First element should match", 123, vector.get(0));
        assertEquals("Second element should match", "str", vector.get(1));
        assertEquals("Third element should match", 3.2, vector.get(2));
        assertTrue("Fourth element should match", vector.get(3) instanceof VectorAdapter);
        assertEquals("Capacity should be 4", 4, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCollectionFilledDuplicateElements()
    {
        VectorAdapter vec = new VectorAdapter();

        vec.add("0");
        vec.add("0");
        vec.add("0");
        vec.add("0");
        vec.add("0");

        vector = new VectorAdapter(vec);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertEquals("Element " + i + "-th should match", "0", vector.get(i));

        assertEquals("Capacity should be 5", 5, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCollectionLargeCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        for(int i = 0; i < 100; i++)
            vec.addElement("" + i);


        vector = new VectorAdapter(vec);

        assertNotNull("Should have been created", vector);
        assertEquals("Size should be 100", 100, vector.size());

        for (int i = 0; i < 100; i++)
            assertEquals("Element " + i + "-th should match", "" + i, vector.get(i));

        assertEquals("Capacity should be 100", 100, vector.capacity());
        assertFalse("Should not be empty", vector.isEmpty());
    }

    @Test
    public void testConstructorWithCollectionModificationIndependence()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");

        vector = new VectorAdapter(vec);

        vec.add("1");

        assertEquals("Should remain unchanged", 1, vector.size());
        assertEquals("Single element should remain unchanged", "0", vector.get(0));
        assertFalse("Should not contain the second added element", vector.contains(vec.get(1)));
    }

    @Test
    public void testConstructorWithCollectionModificationAfterCreation()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("1");

        vector = new VectorAdapter(vec);

        vec.set(0, "2");

        assertEquals("Should maintain original values", "0", vector.get(0));
        assertEquals("Should maintain original values", "1", vector.get(1));
        assertFalse("Should not contain the modified element", vector.contains(vec.get(0)));
    }

    @Test
    public void testConstructorWithCOllectionRemovalAfterCreation()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("1");

        vector = new VectorAdapter(vec);

        vec.remove("0");

        assertEquals("Should maintain original values", "0", vector.get(0));
        assertEquals("Should maintain original values", "1", vector.get(1));
        assertTrue("Should contain the removed element", vector.contains("0"));
    }

    @Test
    public void testCopyIntoCorrectOrder()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");


        Object[] array = new Object[3];
        vector.copyInto(array);

        assertEquals("First element should be '1'", "1", array[0]);
        assertEquals("Second element should be '2'", "2", array[1]);
        assertEquals("Third element should be '3'", "3", array[2]);
    }

    @Test
    public void testCopyIntoEmptyVector()
    {
        vector = new VectorAdapter();

        Object[] array = new Object[0];
        vector.copyInto(array);

        assertEquals("Array should remain empty", 0, array.length);
    }

    @Test
    public void testCopyIntoEmptyVectorWiderArray()
    {
        vector = new VectorAdapter();

        Object[] array = new Object[10];
        vector.copyInto(array);

        for(int i = 0; i < 10; i++)
            assertNull("Element " + i + "-th should still be null", array[i]);

        assertEquals("Array should be unmodified", 10, array.length);
    }

    @Test
    public void testCopyIntoLargerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        Object[] array = new Object[5];
        array[2] = "0";
        array[3] = "0";
        array[4] = "0";

        vector.copyInto(array);

        assertEquals("First element should be '1'", "1", array[0]);
        assertEquals("Second element should be '2'", "2", array[1]);
        assertEquals("Existing elements should be preserved", "0", array[2]);
        assertEquals("Existing elements should be preserved", "0", array[3]);
        assertEquals("Existing elements should be preserved", "0", array[4]);
    }

    @Test
    public void testCopyIntoWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        Object[] array = new Object[3];
        array[0] = "0";
        array[1] = "0";
        array[2] = "0";

        vector.copyInto(array);

        assertNull("First element should be null", array[0]);
        assertNull("Second element should be null", array[1]);
        assertNull("Third element should be null", array[2]);
    }

    @Test
    public void testCopyIntoWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");

        Object[] array = new Object[3];
        vector.copyInto(array);

        assertEquals("All elements should be '1'", "1", array[0]);
        assertEquals("All elements should be '1'", "1", array[1]);
        assertEquals("All elements should be '1'", "1", array[2]);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyIntoNullArray()
    {
        vector = new VectorAdapter();
        vector.copyInto(null);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyIntoNullArrayWithElements()
    {
        vector = new VectorAdapter();
        vector.add("1");

        vector.copyInto(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCopyIntoSmallerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object[] array = new Object[2];
        vector.copyInto(array);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCopyIntoZeroLengthArrayWithElements()
    {
        vector = new VectorAdapter();
        vector.add("1");

        Object[] array = new Object[0];
        vector.copyInto(array);
    }

    @Test
    public void testCopyIntoAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");

        Object[] array1 = new Object[1];
        vector.copyInto(array1);

        vector.add("2");
        vector.set(0, "3");

        Object[] array2 = new Object[2];
        vector.copyInto(array2);

        assertEquals("First copy should contain original value", "1", array1[0]);
        assertEquals("Second copy should contain modified first element", "3", array2[0]);
        assertEquals("Second copy should contain new second element", "2", array2[1]);
    }

    @Test
    public void testCopyIntoLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("1");

        Object[] array = new Object[1000];
        vector.copyInto(array);

        for(int i = 0; i < 1000; i++)
            assertEquals("All elements should be '1'", "1", array[i]);
    }

    @Test
    public void testCopyIntoArrayWithExistingData()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        Object[] array = new Object[4];
        array[0] = "0";
        array[1] = "0";
        array[2] = "3";
        array[3] = "4";

        vector.copyInto(array);

        assertEquals("First element should be '1'", "1", array[0]);
        assertEquals("Second element should be '2'", "2", array[1]);
        assertEquals("Existing elements should be preserved", "3", array[2]);
        assertEquals("Existing elements should be preserved", "4", array[3]);
    }


    @Test
    public void testTrimToSizeConstructorCapacityAndIncrement()
    {
        vector = new VectorAdapter(20, 50);

        vector.trimToSize();

        assertEquals("Capacity should equal size after trimming", 0, vector.capacity());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testTrimToSizeConstructorCapacity()
    {
        vector = new VectorAdapter(20);

        vector.trimToSize();

        assertEquals("Capacity should equal size after trimming", 0, vector.capacity());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testTrimToSizeDefaultConstructor()
    {
        vector = new VectorAdapter();

        vector.trimToSize();

        assertEquals("Capacity should equal size after trimming", 0, vector.capacity());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testTrimToSizeConstructorCollection()
    {
        VectorAdapter vec = new VectorAdapter(10, 20);

        vector = new VectorAdapter(vec);

        vector.trimToSize();

        assertEquals("Capacity should equal size after trimming", 0, vector.capacity());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testTrimToSizeAfterAddingElements()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.trimToSize();

        assertEquals("Capacity should equal size after trimming", 3, vector.capacity());
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testTrimToSizeAlreadyTrimmed()
    {
        vector = new VectorAdapter(3);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.trimToSize();

        int capacityAfterFirstTrim = vector.capacity();

        vector.trimToSize();

        assertEquals("Capacity should remain unchanged after second trim",
            capacityAfterFirstTrim, vector.capacity());
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testTrimToSizeAfterRemovingElements()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        vector.remove(2);
        vector.remove(3);
        vector.trimToSize();

        assertEquals("Capacity should equal reduced size", 3, vector.capacity());
        assertEquals("Size should be 3", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should be '4'", "4", vector.get(2));
    }

    @Test
    public void testTrimToSizeWithNullElements()
    {
        vector = new VectorAdapter(5);
        vector.add(null);
        vector.add(null);
        vector.add(null);

        vector.trimToSize();

        assertEquals("Capacity should equal size", 3, vector.capacity());
        assertEquals("Size should remain 3", 3, vector.size());
        assertNull("First element should remain null", vector.get(0));
        assertNull("Second element should remain null", vector.get(1));
        assertNull("Third element should remain null", vector.get(2));
    }

    @Test
    public void testTrimToSizeAfterClear()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.clear();
        vector.trimToSize();


        assertEquals("Capacity should be 0 after clearing", 0, vector.capacity());
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testTrimToSizeWithSingleElement()
    {
        vector = new VectorAdapter(10);
        vector.add("1");

        vector.trimToSize();

        assertEquals("Capacity should be 1", 1, vector.capacity());
        assertEquals("Size should be 1", 1, vector.size());
        assertEquals("Element should remain", "1", vector.get(0));
    }

    @Test
    public void testTrimToSizeAfterMultipleOperations()
    {
        vector = new VectorAdapter(20);

        for(int i = 0; i < 15; i++)
            vector.add("" + i);

        for(int i = 5; i < 10; i++)
            vector.remove(i);

        vector.trimToSize();
        int capacityAfterFirstTrim = vector.capacity();

        vector.add("15");
        vector.add("16");
        vector.trimToSize();

        assertEquals("Capacity should equal new size", 12, vector.capacity());
        assertEquals("Size should be 12", 12, vector.size());
        assertEquals("Last element should be '16'", "16", vector.get(11));
    }

    @Test
    public void testTrimToSizeWithDuplicateElements()
    {
        vector = new VectorAdapter(8);
        vector.add("1");
        vector.add("1");
        vector.add("1");
        vector.add("1");

        vector.trimToSize();

        assertEquals("Capacity should equal size", 4, vector.capacity());
        assertEquals("Size should remain 4", 4, vector.size());

        for(int i = 0; i < 4; i++)
            assertEquals("All elements should be '1'", "1", vector.get(i));
    }

    @Test
    public void testTrimToSizeAfterSetSizeSmaller()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setSize(2);
        vector.trimToSize();

        assertEquals("Capacity should equal reduced size", 2, vector.capacity());
        assertEquals("Size should be 2", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
    }

    @Test
    public void testTrimToSizeAfterSetSizeLarger()
    {
        vector = new VectorAdapter(5);
        vector.add("1");
        vector.add("2");

        vector.setSize(4);
        vector.trimToSize();

        assertEquals("Capacity should equal increased size", 4, vector.capacity());
        assertEquals("Size should be 4", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
        assertNull("Fourth element should be null", vector.get(3));
    }

    @Test
    public void testTrimToSizeWithMixedElementTypes()
    {
        vector = new VectorAdapter(6);
        vector.add("str");
        vector.add(2);
        vector.add(3.0);

        vector.trimToSize();

        assertEquals("Capacity should equal size", 3, vector.capacity());
        assertEquals("Size should remain 3", 3, vector.size());
        assertEquals("First element should remain", "str", vector.get(0));
        assertEquals("Second element should remain", 2, vector.get(1));
        assertEquals("Third element should remain", 3.0, vector.get(2));
    }

    @Test
    public void testTrimToSizeDoesNotAffectElementOrder()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        vector.trimToSize();

        assertEquals("Element order should be preserved", "1", vector.get(0));
        assertEquals("Element order should be preserved", "2", vector.get(1));
        assertEquals("Element order should be preserved", "3", vector.get(2));
        assertEquals("Element order should be preserved", "4", vector.get(3));
        assertEquals("Element order should be preserved", "5", vector.get(4));
    }

    @Test
    public void testTrimToSizeAfterEnsureCapacity()
    {
        vector = new VectorAdapter(5);
        vector.add("1");
        vector.add("2");

        vector.ensureCapacity(20);
        vector.trimToSize();

        assertEquals("Capacity should equal size after trimming", 2, vector.capacity());
        assertEquals("Size should remain unchanged", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
    }

    @Test
    public void testEnsureCapacityAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");

        vector.trimToSize();
        vector.ensureCapacity(15);

        assertEquals("Capacity should increase after trimming", 15, vector.capacity());
        assertEquals("Size should remain unchanged", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
    }

    @Test
    public void testEnsureCapacityNoChange()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(5);

        assertEquals("Capacity should remain unchanged when minCapacity is smaller", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityExactCurrentCapacity()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(10);

        assertEquals("Capacity should remain unchanged when minCapacity equals current capacity", 10, vector.capacity());

        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityWithCapacityIncrement()
    {
        vector = new VectorAdapter(5, 3);
        vector.ensureCapacity(7);

        assertEquals("Capacity should increase using capacity increment", 8, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityWithMoreThanCapacityIncrement()
    {
        vector = new VectorAdapter(5, 3);
        vector.ensureCapacity(10);

        assertEquals("Capacity should increase using minCapacity", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityWithZeroCapacityIncrement()
    {
        vector = new VectorAdapter(5, 0);
        vector.ensureCapacity(9);

        assertEquals("Capacity should double when capacity increment is zero", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityWithNegativeCapacityIncrement()
    {
        vector = new VectorAdapter(5, -2);
        vector.ensureCapacity(9);

        assertEquals("Capacity should double when capacity increment is negative", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityDoubleGrowthInsufficient()
    {
        vector = new VectorAdapter(5, 0);
        vector.ensureCapacity(20);

        assertEquals("Capacity should be set to minCapacity when double growth is insufficient", 20, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityWithElementsPresent()
    {
        vector = new VectorAdapter(3);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.ensureCapacity(10);

        assertEquals("Capacity should increase to specified minimum", 10, vector.capacity());
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testEnsureCapacityWithElementsAndCapacityIncrement()
    {
        vector = new VectorAdapter(3, 10);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.ensureCapacity(15);

        assertEquals("Capacity should increase to by capacity increment", 23, vector.capacity());
        assertEquals("Size should remain unchanged", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
        assertEquals("Fourth element should remain", "4", vector.get(3));
    }

    @Test
    public void testEnsureCapacityMultipleCalls()
    {
        vector = new VectorAdapter(5);

        vector.ensureCapacity(10);

        int capacityAfterFirstCall = vector.capacity();

        vector.ensureCapacity(25);

        int capacityAfterSecondCall = vector.capacity();

        vector.ensureCapacity(20);

        assertEquals("Second call should increase capacity further", 25, capacityAfterSecondCall);
        assertEquals("Third call should not reduce capacity", capacityAfterSecondCall, vector.capacity());
    }

    @Test
    public void testEnsureCapacityWithNullElements()
    {
        vector = new VectorAdapter(3);
        vector.add(null);
        vector.add(null);
        vector.add(null);

        vector.ensureCapacity(10);

        assertEquals("Capacity should increase", 10, vector.capacity());
        assertEquals("Size should remain unchanged",3, vector.size());
        assertNull("First element should remain null", vector.get(0));
        assertNull("Second element should remain null", vector.get(1));
        assertNull("Third element should remain null", vector.get(2));
    }

    @Test
    public void testEnsureCapacityAfterSetSize()
    {
        vector = new VectorAdapter(5);
        vector.setSize(3);

        vector.ensureCapacity(20);

        assertEquals("Capacity should increase", 20, vector.capacity());
        assertEquals("Size should remain 3", 3, vector.size());
        assertNull("First element should be null", vector.get(0));
        assertNull("Second element should be null", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
    }

    @Test
    public void testEnsureCapacityAfterRemoveOperations()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.remove(2);
        vector.remove(1);
        vector.ensureCapacity(20);

        assertEquals("Capacity should increase", 20, vector.capacity());
        assertEquals("Size should be 2", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be '4'", "4", vector.get(1));
    }

    @Test
    public void testEnsureCapacityWithMixedElementTypes()
    {
        vector = new VectorAdapter(2);
        vector.add("str");
        vector.add(123);
        vector.add(3.12);

        vector.ensureCapacity(10);

        assertEquals("Capacity should increase", 10, vector.capacity());
        assertEquals("Size should remain 3", 3, vector.size());
        assertEquals("First element should remain", "str", vector.get(0));
        assertEquals("Second element should remain", 123, vector.get(1));
        assertEquals("Third element should remain", 3.12, vector.get(2));
    }

    @Test
    public void testEnsureCapacityZeroMinCapacity()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(0);

        assertEquals("Capacity should remain unchanged for zero minCapacity", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityNegativeMinCapacity()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(-5);

        assertEquals("Capacity should remain unchanged for negative minCapacity", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityExtremeValue()
    {
        vector = new VectorAdapter();
        vector.ensureCapacity(100000);

        assertEquals("Capacity should handle extreme values", 100000, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityDoesNotAffectElementOrder()
    {
        vector = new VectorAdapter(3);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.ensureCapacity(20);

        assertEquals("Element order should be preserved", "1", vector.get(0));
        assertEquals("Element order should be preserved", "2", vector.get(1));
        assertEquals("Element order should be preserved", "3", vector.get(2));
    }

    @Test
    public void testEnsureCapacityWithDuplicateElements()
    {
        vector = new VectorAdapter(2);
        vector.add("1");
        vector.add("1");
        vector.add("1");

        vector.ensureCapacity(15);

        assertEquals("Capacity should increase", 15, vector.capacity());
        assertEquals("Size should remain 3", 3, vector.size());

        for(int i = 0; i < 3; i++)
            assertEquals("All elements should remain '1'", "1", vector.get(i));
    }

    @Test
    public void testSetSizeIncreaseFromEmpty()
    {
        vector = new VectorAdapter();
        vector.setSize(3);

        assertEquals("Size should be increased to 3", 3, vector.size());
        assertEquals("Capacity should accommodate new size", 10, vector.capacity());
        assertNull("First element should be null", vector.get(0));
        assertNull("Second element should be null", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
    }

    @Test
    public void testSetSizeIncreaseWithExistingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setSize(4);

        assertEquals("Size should be increased to 4", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
        assertNull("Fourth element should be null", vector.get(3));
    }

    @Test
    public void testSetSizeDecreaseWithExistingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.setSize(2);

        assertEquals("Size should be decreased to 2", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
    }

    @Test
    public void testSetSizeSameSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setSize(3);

        assertEquals("Size should remain 3", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testSetSizeToZero()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setSize(0);

        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetSizeNegative()
    {
        vector = new VectorAdapter();
        vector.setSize(-1);
    }

    @Test
    public void testSetSizeLargeIncrease()
    {
        vector = new VectorAdapter();
        vector.setSize(1000);

        assertEquals("Size should be increased to 1000", 1000, vector.size());
        assertEquals("Capacity should accommodate large size", 1000, vector.capacity());

        for(int i = 0; i < 1000; i++)
            assertNull("Element " + i + " should be null", vector.get(i));
    }

    @Test
    public void testSetSizeFromEmptyToZero()
    {
        vector = new VectorAdapter();
        vector.setSize(0);

        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should remain empty", vector.isEmpty());
    }

    @Test
    public void testSetSizeDecreaseThenIncrease()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        vector.setSize(3);
        vector.setSize(7);

        assertEquals("Size should be decreased to 7", 7, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
        assertNull("Fourth element should be null",  vector.get(3));
        assertNull("Fifth element should be null", vector.get(4));
        assertNull("Sixth element should be null", vector.get(5));
        assertNull("Seventh element should be null", vector.get(6));
    }

    @Test
    public void testSetSizeIncreaseThenDecrease()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setSize(4);
        vector.setSize(1);

        assertEquals("Size should be decreased to 1", 1, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
    }

    @Test
    public void testSetSizeWithNullElements()
    {

        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        vector.setSize(5);

        assertEquals("Size should be increased to 5", 5, vector.size());
        assertNull("First element should remain null", vector.get(0));
        assertNull("Second element should remain null", vector.get(1));
        assertNull("Third element should remain null", vector.get(2));
        assertNull("Fourth element should be null", vector.get(3));
        assertNull("Fifth element should be null", vector.get(4));
    }

    @Test
    public void testSetSizeWithMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("str");
        vector.add(123);
        vector.add(3.14);

        vector.setSize(2);
        vector.setSize(5);

        assertEquals("Size should be increased to 5", 5, vector.size());
        assertEquals("First element should remain", "str", vector.get(0));
        assertEquals("Second element should remain", 123, vector.get(1));
        assertNull("Third element should be null", vector.get(2));
        assertNull("Fourth element should be null", vector.get(3));
        assertNull("Fifth element should be null", vector.get(4));
    }

    @Test
    public void testSetSizeCapacityGrowth()
    {
        vector = new VectorAdapter(2);
        vector.setSize(10);

        assertEquals("Size should be 10", 10, vector.size());
        assertTrue("Capacity should grow to accommodate size", vector.capacity() >= 10);

        for(int i = 0; i < 10; i++)
            assertNull("Element " + i + "-th should be null", vector.get(i));
    }

    @Test
    public void testSetSizeMultipleOperations()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        vector.setSize(3);
        int sizeAfterFirst = vector.size();

        vector.setSize(1);
        int sizeAfterSecond = vector.size();

        vector.setSize(5);
        int sizeAfterThird = vector.size();

        assertEquals("First setSize should set size to 3", 3, sizeAfterFirst);
        assertEquals("Second setSize should set size to 1", 1, sizeAfterSecond);
        assertEquals("Third setSize should set size to 5", 5, sizeAfterThird);

        assertEquals("First element should be '1'", "1", vector.get(0));
        assertNull("Second element should be null", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
        assertNull("Fourth element should be null", vector.get(3));
        assertNull("Fifth element should be null", vector.get(4));
    }

    @Test
    public void testSetSizeAfterAddOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setSize(4);
        vector.add("5");

        assertEquals("Size should be 5 after add", 5, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
        assertNull("Fourth element should be null", vector.get(3));
        assertEquals("Fifth element should be new element", "5", vector.get(4));
    }

    @Test
    public void testSetSizeAfterRemoveOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.remove(1);
        vector.setSize(2);

        assertEquals("Size should be 2", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be '3'", "3", vector.get(1));
    }

    @Test
    public void testSetSizeBeforeRemoveOperation()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        vector.setSize(2);
        vector.remove(1);

        assertEquals("Size should be 1", 1, vector.size());
        assertEquals("Only element should be '1'", "1", vector.get(0));
    }

    @Test
    public void testSetSizeAfterSetOperation()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        vector.set(2, "0");
        vector.set(3, "0");


        vector.setSize(3);

        assertEquals("Size should be 3", 3, vector.size());
        assertEquals("First element should be '1'", "1", vector.get(0));
        assertEquals("Second element should be '2'", "2", vector.get(1));
        assertEquals("Third element should be '0'", "0", vector.get(2));
    }

    @Test
    public void testSetSizeWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        vector.add("1");

        vector.setSize(2);
        vector.setSize(6);

        assertEquals("Size should be 6", 6, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "1", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
        assertNull("Fourth element should be null", vector.get(3));
        assertNull("Fifth element should be null", vector.get(4));
        assertNull("Sixth element should be null", vector.get(5));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetSizeThenIndexOutOfBounds()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        assertEquals("Fourth element should be accessible", "4", vector.get(3));

        vector.setSize(2);

        vector.get(3);
    }

    @Test
    public void testCapacityEmptyVector()
    {
        vector = new VectorAdapter();
        assertEquals("Capacity should be 10 for default constructor", 10, vector.capacity());
    }

    @Test
    public void testCapacityWithInitialCapacity()
    {
        vector = new VectorAdapter(15);
        assertEquals("Capacity should match initial capacity", 15, vector.capacity());
    }

    @Test
    public void testCapacityAfterAddingElements()
    {
        vector = new VectorAdapter(5);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Capacity should remain unchanged after adding elements", 5, vector.capacity());
    }

    @Test
    public void testCapacityAfterEnsureCapacity()
    {
        vector = new VectorAdapter(5);
        vector.ensureCapacity(20);
        assertEquals("Capacity should increase after ensureCapacity", 20, vector.capacity());
    }

    @Test
    public void testCapacityAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.trimToSize();
        assertEquals("Capacity should equal size after trimToSize", 2, vector.capacity());
    }

    @Test
    public void testCapacityAfterSetSizeIncrease()
    {
        vector = new VectorAdapter(5);
        vector.setSize(10);
        assertEquals("Capacity should grow to accommodate larger size", 10, vector.capacity());
    }

    @Test
    public void testCapacityAfterSetSizeDecrease()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.setSize(1);
        assertEquals("Capacity should remain unchanged after decreasing size", 10, vector.capacity());
    }

    @Test
    public void testCapacityWithCapacityIncrement()
    {
        vector = new VectorAdapter(5, 3);
        vector.ensureCapacity(9);
        assertEquals("Capacity should grow by capacity increment", 9, vector.capacity());
    }

    @Test
    public void testCapacityWithZeroCapacityIncrement()
    {
        vector = new VectorAdapter(5, 0);
        vector.ensureCapacity(9);
        assertEquals("Capacity should double when increment is zero", 10, vector.capacity());
    }

    @Test
    public void testCapacityExtremeGrowth()
    {
        vector = new VectorAdapter(1);

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertTrue("Capacity should grow to accommodate many elements", vector.capacity() >= 1000);
    }

    @Test
    public void testSizeEmptyVector()
    {
        vector = new VectorAdapter();
        assertEquals("Size should be 0 for empty vector", 0, vector.size());
    }

    @Test
    public void testSizeAfterAddingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        assertEquals("Size should be 3 after adding three elements", 3, vector.size());
    }

    @Test
    public void testSizeAfterRemovingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.remove(1);

        assertEquals("Size should be 2 after removing one element", 2, vector.size());
    }

    @Test
    public void testSizeAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.clear();
        assertEquals("Size should be 0 after clear", 0, vector.size());
    }

    @Test
    public void testSizeAfterSetSizeIncrease()
    {
        vector = new VectorAdapter();
        vector.setSize(5);
        assertEquals("Size should be 5 after setSize increase", 5, vector.size());
    }

    @Test
    public void testSizeAfterSetSizeDecrease()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.setSize(1);

        assertEquals("Size should be 1 after setSize decrease", 1, vector.size());
    }

    @Test
    public void testSizeWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertEquals("Size should count null elements", 3, vector.size());
    }

    @Test
    public void testSizeWithMixedElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        assertEquals("Size should count mixed type elements", 3, vector.size());
    }

    @Test
    public void testSizeAfterMultipleOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.remove(0);

        vector.add("3");
        vector.add("4");

        vector.remove(1);

        assertEquals("Size should reflect all operations", 2, vector.size());
    }

    @Test
    public void testIsEmptyNewVector()
    {
        vector = new VectorAdapter();
        assertTrue("New vector should be empty", vector.isEmpty());
    }

    @Test
    public void testIsEmptyAfterAddingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        assertFalse("Vector should not be empty after adding element", vector.isEmpty());
    }

    @Test
    public void testIsEmptyAfterRemovingAllElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.clear();

        assertTrue("Vector should be empty after clear", vector.isEmpty());
    }

    @Test
    public void testIsEmptyAfterSetSizeZero()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setSize(0);

        assertTrue("Vector should be empty after setSize(0)", vector.isEmpty());
    }

    @Test
    public void testIsEmptyAfterRemoveOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.remove(0);
        vector.remove(0);

        assertTrue("Vector should be empty after removing all elements", vector.isEmpty());
    }

    @Test
    public void testIsEmptyWithNullElements()
    {
        vector = new VectorAdapter();

        vector.add(null);

        assertFalse("Vector with null element should not be empty", vector.isEmpty());
    }

    @Test
    public void testIsEmptyAfterSetSizeWithNulls()
    {
        vector = new VectorAdapter();

        vector.setSize(3);

        assertFalse("Vector with null elements from setSize should not be empty", vector.isEmpty());
    }

    @Test
    public void testCapacitySizeRelationship()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertTrue("Capacity should always be >= size", vector.capacity() >= vector.size());
    }

    @Test
    public void testIsEmptySizeConsistency()
    {
        vector = new VectorAdapter();
        assertEquals("isEmpty should be true when size is 0", vector.isEmpty(), vector.size() == 0);

        vector.add("1");
        assertEquals("isEmpty should be false when size > 0", vector.isEmpty(), vector.size() == 0);
    }

    @Test
    public void testCapacityAfterConstructorWithCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter(vec);
        assertEquals("Capacity should match source size", 3, vector.capacity());
    }

    @Test
    public void testSizeAfterConstructorWithCollection()
    {
        VectorAdapter vec = new VectorAdapter();

        vec.add("1");
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter(vec);
        assertEquals("Size should match source size", 3, vector.size());
    }

    @Test
    public void testIsEmptyAfterConstructorWithEmptyCollection()
    {
        VectorAdapter vec = new VectorAdapter();
        vector = new VectorAdapter(vec);
        assertTrue("Vector from empty collection should be empty", vector.isEmpty());
    }

    @Test
    public void testContainsExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertTrue("Should contain element '1'", vector.contains("1"));
        assertTrue("Should contain element '2'", vector.contains("2"));
        assertTrue("Should contain element '3'", vector.contains("3"));
    }

    @Test
    public void testContainsNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        assertFalse("Should not contain element '3'", vector.contains("3"));
        assertFalse("Should not contain element '4'", vector.contains("4"));
    }

    @Test
    public void testContainsNullElementWhenPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertTrue("Should contain null element", vector.contains(null));
    }

    @Test
    public void testContainsNullElementWhenAbsent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertFalse("Should not contain null when not present", vector.contains(null));
    }

    @Test
    public void testContainsOnEmptyVector()
    {
        vector = new VectorAdapter();

        assertFalse("Empty vector should not contain any element", vector.contains("1"));
        assertFalse("Empty vector should not contain any element", vector.contains("2"));
        assertFalse("Empty vector should not contain any element", vector.contains("3"));
        assertFalse("Empty vector should not contain any element", vector.contains("4"));
        assertFalse("Empty vector should not contain any element", vector.contains("5"));
        assertFalse("Empty vector should not contain null", vector.contains(null));
    }

    @Test
    public void testContainsWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");

        assertTrue("Should contain duplicate element", vector.contains("1"));
    }

    @Test
    public void testContainsAfterElementRemoval()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove("2");

        assertTrue("Should still contain '1'", vector.contains("1"));
        assertFalse("Should not contain removed element '2'", vector.contains("2"));
        assertTrue("Should still contain '3'", vector.contains("3"));
    }

    @Test
    public void testContainsAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.clear();

        assertFalse("Should not contain any element after clear", vector.contains("1"));
        assertFalse("Should not contain any element after clear", vector.contains("2"));
        assertFalse("Should not contain any element after clear", vector.contains("3"));
    }

    @Test
    public void testContainsWithDifferentObjectTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        assertTrue("Should contain string '1'", vector.contains("1"));
        assertTrue("Should contain integer 2", vector.contains(2));
        assertTrue("Should contain double 3.0", vector.contains(3.0));
        assertFalse("Should not contain mismatched types", vector.contains(1));
    }

    @Test
    public void testContainsAfterSetOperation()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");

        assertFalse("Should not contain replaced element", vector.contains("2"));
        assertTrue("Should contain new element", vector.contains("4"));
    }

    @Test
    public void testContainsWithLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertTrue("Should contain element at beginning", vector.contains("0"));
        assertTrue("Should contain element in middle", vector.contains("500"));
        assertTrue("Should contain element at end", vector.contains("999"));
        assertFalse("Should not contain non-existent element", vector.contains("1000"));
    }

    @Test
    public void testContainsWithObjectIdentity()
    {
        vector = new VectorAdapter();

        String str1 = new String("0");
        String str2 = new String("0");

        vector.add(str1);

        assertTrue("Should contain equal string", vector.contains(str2));
    }

    @Test
    public void testContainsAfterAddAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.addAll(vec);

        assertTrue("Should contain original elements", vector.contains("1"));
        assertTrue("Should contain added elements", vector.contains("4"));
        assertTrue("Should contain added elements", vector.contains("5"));
    }

    @Test
    public void testContainsAfterRemoveAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.removeAll(vec);

        assertTrue("Should contain remaining elements", vector.contains("1"));
        assertFalse("Should not contain removed elements", vector.contains("2"));
        assertFalse("Should not contain removed elements", vector.contains("3"));
        assertTrue("Should contain remaining elements", vector.contains("4"));
    }

    @Test
    public void testContainsAfterRetainAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.retainAll(vec);

        assertFalse("Should not contain non-retained elements", vector.contains("1"));
        assertTrue("Should contain retained elements", vector.contains("2"));
        assertTrue("Should contain retained elements", vector.contains("3"));
        assertFalse("Should not contain non-retained elements", vector.contains("4"));
    }

    @Test
    public void testIndexOfExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return correct index for first element", 0, vector.indexOf("1"));
        assertEquals("Should return correct index for second element", 1, vector.indexOf("2"));
        assertEquals("Should return correct index for third element", 2, vector.indexOf("3"));
    }

    @Test
    public void testIndexOfNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 for non-existing element", -1, vector.indexOf("4"));
        assertEquals("Should return -1 for non-existing element", -1, vector.indexOf("0"));
    }

    @Test
    public void testIndexOfNullElementWhenPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertEquals("Should return correct index for null element", 1, vector.indexOf(null));
    }

    @Test
    public void testIndexOfNullElementWhenAbsent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 for null when not present", -1, vector.indexOf(null));
    }

    @Test
    public void testIndexOfOnEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Should return -1 for any element in empty vector", -1, vector.indexOf("1"));
        assertEquals("Should return -1 for null in empty vector", -1, vector.indexOf(null));
    }

    @Test
    public void testIndexOfWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");

        assertEquals("Should return first occurrence index for duplicate elements", 0, vector.indexOf("1"));
    }

    @Test
    public void testIndexOfAfterElementRemoval()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove("2");

        assertEquals("Should still find remaining elements", 0, vector.indexOf("1"));
        assertEquals("Should not find removed element", -1, vector.indexOf("2"));
        assertEquals("Should still find remaining elements", 1, vector.indexOf("3"));
    }

    @Test
    public void testIndexOfAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.clear();

        assertEquals("Should not find any element after clear", -1, vector.indexOf("1"));
        assertEquals("Should not find any element after clear", -1, vector.indexOf("2"));
        assertEquals("Should not find any element after clear", -1, vector.indexOf("3"));
    }

    @Test
    public void testIndexOfWithDifferentObjectTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        assertEquals("Should find string element", 0, vector.indexOf("1"));
        assertEquals("Should find integer element", 1, vector.indexOf(2));
        assertEquals("Should find double element", 2, vector.indexOf(3.0));
        assertEquals("Should not find mismatched types", -1, vector.indexOf(1));
    }

    @Test
    public void testIndexOfAfterSetOperation()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");

        assertEquals("Should not find replaced element", -1, vector.indexOf("2"));
        assertEquals("Should find new element", 1, vector.indexOf("4"));
    }

    @Test
    public void testIndexOfWithLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertEquals("Should find element at beginning", 0, vector.indexOf("0"));
        assertEquals("Should find element in middle", 500, vector.indexOf("500"));
        assertEquals("Should find element at end", 999, vector.indexOf("999"));
        assertEquals("Should not find non-existent element", -1, vector.indexOf("1000"));
    }

    @Test
    public void testIndexOfAfterAddAll()
    {
        VectorAdapter vec = new VectorAdapter();

        vec.add("4");
        vec.add("5");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.addAll(vec);

        assertEquals("Should find original elements", 0, vector.indexOf("1"));
        assertEquals("Should find added elements", 3, vector.indexOf("4"));
        assertEquals("Should find added elements", 4, vector.indexOf("5"));
    }

    @Test
    public void testIndexOfAfterRemoveAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.removeAll(vec);

        assertEquals("Should find remaining elements", 0, vector.indexOf("1"));
        assertEquals("Should not find removed elements", -1, vector.indexOf("2"));
        assertEquals("Should not find removed elements", -1, vector.indexOf("3"));
        assertEquals("Should find remaining elements", 1, vector.indexOf("4"));
    }

    @Test
    public void testIndexOfAfterRetainAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.retainAll(vec);

        assertEquals("Should not find non-retained elements", -1, vector.indexOf("1"));
        assertEquals("Should find retained elements", 0, vector.indexOf("2"));
        assertEquals("Should find retained elements", 1, vector.indexOf("3"));
        assertEquals("Should not find non-retained elements", -1, vector.indexOf("4"));
    }

    @Test
    public void testIndexOfWithMixedNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("2");
        vector.add(null);
        vector.add("3");

        assertEquals("Should find first occurrence of null", 1, vector.indexOf(null));
        assertEquals("Should find string elements correctly", 0, vector.indexOf("1"));
        assertEquals("Should find string elements correctly", 2, vector.indexOf("2"));
        assertEquals("Should find string elements correctly", 4, vector.indexOf("3"));
    }

    @Test
    public void testIndexOfWithEmptyString()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("");

        assertEquals("Should find empty string at first occurrence", 1, vector.indexOf(""));
    }

    @Test
    public void testIndexOfWithSameObjectMultipleTimes()
    {
        Object obj = new Object();
        vector = new VectorAdapter();
        vector.add(obj);
        vector.add("1");
        vector.add(obj);

        assertEquals("Should find first occurrence of object", 0, vector.indexOf(obj));
    }

    @Test
    public void testIndexOfAfterMultipleOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove(1);
        vector.add("4");
        vector.set(0, "5");

        assertEquals("Should find correct index after multiple operations", 0, vector.indexOf("5"));
        assertEquals("Should find correct index after multiple operations", 1, vector.indexOf("3"));
        assertEquals("Should find correct index after multiple operations", 2, vector.indexOf("4"));
        assertEquals("Should not find removed element", -1, vector.indexOf("2"));
    }

    @Test
    public void testIndexOfAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.trimToSize();

        assertEquals("Should find elements correctly after trim", 0, vector.indexOf("1"));
        assertEquals("Should find elements correctly after trim", 1, vector.indexOf("2"));
        assertEquals("Should find elements correctly after trim", 2, vector.indexOf("3"));
    }

    @Test
    public void testIndexOfAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setSize(5);
        vector.setSize(2);

        assertEquals("Should find elements correctly after setSize", 0, vector.indexOf("1"));
        assertEquals("Should find elements correctly after setSize", 1, vector.indexOf("2"));
        assertEquals("Should not find elements beyond new size", -1, vector.indexOf("3"));
    }

    @Test
    public void testIndexOfWithBooleanElements()
    {
        vector = new VectorAdapter();
        vector.add(true);
        vector.add(false);
        vector.add(true);

        assertEquals("Should find boolean elements correctly", 0, vector.indexOf(true));
        assertEquals("Should find boolean elements correctly", 1, vector.indexOf(false));
    }

    @Test
    public void testIndexOfWithIndexExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        assertEquals("Should find element starting from index 0", 2, vector.indexOf("3", 0));
        assertEquals("Should find element starting from index 2", 2, vector.indexOf("3", 2));
        assertEquals("Should find element starting from index 3", -1, vector.indexOf("3", 3));
        assertEquals("Should find element starting from index 4", -1, vector.indexOf("3", 4));
    }

    @Test
    public void testIndexOfWithIndexNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 for non-existing element", -1, vector.indexOf("4", 0));
        assertEquals("Should return -1 for non-existing element", -1, vector.indexOf("4", 1));
        assertEquals("Should return -1 for non-existing element", -1, vector.indexOf("4", 2));
    }

    @Test
    public void testIndexOfWithIndexNullElementWhenPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);
        vector.add("5");

        assertEquals("Should find first null starting from index 0", 1, vector.indexOf(null, 0));
        assertEquals("Should find second null starting from index 2", 3, vector.indexOf(null, 2));
        assertEquals("Should not find null starting from index 4", -1, vector.indexOf(null, 4));
    }

    @Test
    public void testIndexOfWithIndexNullElementWhenAbsent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 for null when not present", -1, vector.indexOf(null, 0));
        assertEquals("Should return -1 for null when not present", -1, vector.indexOf(null, 1));
        assertEquals("Should return -1 for null when not present", -1, vector.indexOf(null, 2));
    }

    @Test
    public void testIndexOfWithIndexOnEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Should return -1 for any element in empty vector", -1, vector.indexOf("1", 0));
        assertEquals("Should return -1 for null in empty vector", -1, vector.indexOf(null, 0));
    }

    @Test
    public void testIndexOfWithIndexWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        vector.add("2");
        vector.add("1");

        assertEquals("Should find first occurrence starting from index 0", 0, vector.indexOf("1", 0));
        assertEquals("Should find second occurrence starting from index 1", 1, vector.indexOf("1", 1));
        assertEquals("Should find third occurrence starting from index 2", 2, vector.indexOf("1", 2));
        assertEquals("Should find fourth occurrence starting from index 3", 4, vector.indexOf("1", 3));
        assertEquals("Should find fourth occurrence starting from index 4", 4, vector.indexOf("1", 4));
        assertEquals("Should not find occurrence starting from index 5", -1, vector.indexOf("1", 5));
    }

    @Test
    public void testIndexOfWithIndexAfterElementRemoval()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.remove("2");

        assertEquals("Should find remaining elements starting from index 0", 0, vector.indexOf("1", 0));
        assertEquals("Should find remaining elements starting from index 1", 1, vector.indexOf("3", 1));
        assertEquals("Should not find removed element", -1, vector.indexOf("2", 0));
    }

    @Test
    public void testIndexOfWithIndexAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.clear();

        assertEquals("Should not find any element after clear", -1, vector.indexOf("1", 0));
        assertEquals("Should not find any element after clear", -1, vector.indexOf("2", 0));
        assertEquals("Should not find any element after clear", -1, vector.indexOf("3", 0));
    }

    @Test
    public void testIndexOfWithIndexWithDifferentObjectTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        vector.add("4");
        vector.add(5);

        assertEquals("Should find string element starting from index 0", 0, vector.indexOf("1", 0));
        assertEquals("Should find integer element starting from index 1", 1, vector.indexOf(2, 1));
        assertEquals("Should find double element starting from index 2", 2, vector.indexOf(3.0, 2));
        assertEquals("Should not find mismatched types", -1, vector.indexOf(1, 0));
    }

    @Test
    public void testIndexOfWithIndexAfterSetOperation()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.set(1, "5");

        assertEquals("Should not find replaced element", -1, vector.indexOf("2", 0));
        assertEquals("Should find new element starting from index 0", 1, vector.indexOf("5", 0));
        assertEquals("Should find new element starting from index 1", 1, vector.indexOf("5", 1));
    }

    @Test
    public void testIndexOfWithIndexWithLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertEquals("Should find element at beginning starting from index 0", 0, vector.indexOf("0", 0));
        assertEquals("Should find element in middle starting from index 500", 500, vector.indexOf("500", 500));
        assertEquals("Should find element in middle starting from lower index", 500, vector.indexOf("500", 400));
        assertEquals("Should find element at end starting from index 999", 999, vector.indexOf("999", 999));
        assertEquals("Should not find element starting beyond its position", -1, vector.indexOf("500", 501));
        assertEquals("Should not find non-existent element", -1, vector.indexOf("1000", 0));
    }

    @Test
    public void testIndexOfWithIndexAfterAddAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");
        vec.add("6");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.addAll(vec);

        assertEquals("Should find added elements starting from index 3", 3, vector.indexOf("4", 3));
        assertEquals("Should find added elements starting from index 4", 4, vector.indexOf("5", 4));
        assertEquals("Should not find added elements before their position", -1, vector.indexOf("4", 4));
    }

    @Test
    public void testIndexOfWithIndexAfterRemoveAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.removeAll(vec);

        assertEquals("Should find remaining elements starting from index 0", 0, vector.indexOf("1", 0));
        assertEquals("Should find remaining elements starting from index 1", 1, vector.indexOf("4", 1));
        assertEquals("Should not find removed elements", -1, vector.indexOf("2", 0));
    }

    @Test
    public void testIndexOfWithIndexWithMixedNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("2");
        vector.add(null);
        vector.add("3");

        assertEquals("Should find first null starting from index 0", 1, vector.indexOf(null, 0));
        assertEquals("Should find second null starting from index 2", 3, vector.indexOf(null, 2));
        assertEquals("Should not find null starting from index 4", -1, vector.indexOf(null, 4));
        assertEquals("Should find string elements correctly", 0, vector.indexOf("1", 0));
        assertEquals("Should find string elements correctly", 2, vector.indexOf("2", 2));
    }

    @Test
    public void testIndexOfWithIndexWithEmptyString()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("");
        vector.add("2");
        vector.add("");

        assertEquals("Should find empty string starting from index 0", 1, vector.indexOf("", 0));
        assertEquals("Should find second empty string starting from index 2", 3, vector.indexOf("", 2));
    }

    @Test
    public void testIndexOfWithIndexWithSameObjectMultipleTimes()
    {
        Object obj = new Object();
        vector = new VectorAdapter();
        vector.add(obj);
        vector.add("1");
        vector.add(obj);
        vector.add("2");
        vector.add(obj);

        assertEquals("Should find first occurrence starting from index 0", 0, vector.indexOf(obj, 0));
        assertEquals("Should find second occurrence starting from index 1", 2, vector.indexOf(obj, 1));
        assertEquals("Should find third occurrence starting from index 3", 4, vector.indexOf(obj, 3));
    }

    @Test
    public void testIndexOfWithIndexAfterMultipleOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.remove(1);
        vector.add("5");
        vector.set(0, "6");

        assertEquals("Should find correct index after multiple operations", 0, vector.indexOf("6", 0));
        assertEquals("Should find correct index after multiple operations", 2, vector.indexOf("4", 1));
        assertEquals("Should find correct index after multiple operations", 3, vector.indexOf("5", 3));
        assertEquals("Should not find removed element", -1, vector.indexOf("2", 0));
    }

    @Test
    public void testIndexOfWithIndexAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.trimToSize();

        assertEquals("Should find elements correctly after trim", 0, vector.indexOf("1", 0));
        assertEquals("Should find elements correctly after trim", 2, vector.indexOf("3", 2));
        assertEquals("Should not find elements beyond range", -1, vector.indexOf("1", 4));
    }

    @Test
    public void testIndexOfWithIndexAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.setSize(6);
        vector.setSize(2);

        assertEquals("Should find elements correctly after setSize", 0, vector.indexOf("1", 0));
        assertEquals("Should find elements correctly after setSize", 1, vector.indexOf("2", 1));
        assertEquals("Should not find elements beyond new size", -1, vector.indexOf("3", 0));
    }

    @Test
    public void testIndexOfWithIndexWithBooleanElements()
    {
        vector = new VectorAdapter();
        vector.add(true);
        vector.add(false);
        vector.add(true);
        vector.add(false);

        assertEquals("Should find boolean elements correctly", 0, vector.indexOf(true, 0));
        assertEquals("Should find boolean elements correctly", 1, vector.indexOf(false, 0));
        assertEquals("Should find second occurrence of boolean", 2, vector.indexOf(true, 1));
        assertEquals("Should find second occurrence of boolean", 3, vector.indexOf(false, 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOfWithIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.indexOf("1", -1);
    }

    @Test
    public void testIndexOfWithIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 when index equals size", -1, vector.indexOf("1", 3));
        assertEquals("Should return -1 when index equals size", -1, vector.indexOf("2", 3));
        assertEquals("Should return -1 when index equals size", -1, vector.indexOf("3", 3));
    }

    @Test
    public void testIndexOfWithIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 when index greater than size", -1, vector.indexOf("1", 4));
        assertEquals("Should return -1 when index greater than size", -1, vector.indexOf("2", 5));
        assertEquals("Should return -1 when index greater than size", -1, vector.indexOf("3", 10));
    }

    @Test
    public void testIndexOfWithIndexZeroOnEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Should return -1 for any element when vector is empty", -1, vector.indexOf("1", 0));
        assertEquals("Should return -1 for null when vector is empty", -1, vector.indexOf(null, 0));
    }

    @Test
    public void testIndexOfWithIndexEdgeCases()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        assertEquals("Should find element at exact start index", 2, vector.indexOf("3", 2));
        assertEquals("Should find element after start index", 3, vector.indexOf("4", 2));
        assertEquals("Should not find element when start index is too high", -1, vector.indexOf("2", 3));
        assertEquals("Should find last element with max valid index", 4, vector.indexOf("5", 4));
    }

    @Test
    public void testLastIndexOfExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return correct last index for first element", 0, vector.lastIndexOf("1"));
        assertEquals("Should return correct last index for second element", 1, vector.lastIndexOf("2"));
        assertEquals("Should return correct last index for third element", 2, vector.lastIndexOf("3"));
    }

    @Test
    public void testLastIndexOfNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 for non-existing element", -1, vector.lastIndexOf("4"));
        assertEquals("Should return -1 for non-existing element", -1, vector.lastIndexOf("0"));
    }

    @Test
    public void testLastIndexOfNullElementWhenPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertEquals("Should return correct last index for null element", 1, vector.lastIndexOf(null));
    }

    @Test
    public void testLastIndexOfNullElementWhenAbsent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 for null when not present", -1, vector.lastIndexOf(null));
    }

    @Test
    public void testLastIndexOfOnEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Should return -1 for any element in empty vector", -1, vector.lastIndexOf("1"));
        assertEquals("Should return -1 for null in empty vector", -1, vector.lastIndexOf(null));
    }

    @Test
    public void testLastIndexOfWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");

        assertEquals("Should return last occurrence index for duplicate elements", 2, vector.lastIndexOf("1"));
    }

    @Test
    public void testLastIndexOfAfterElementRemoval()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove("2");

        assertEquals("Should still find remaining elements", 0, vector.lastIndexOf("1"));
        assertEquals("Should not find removed element", -1, vector.lastIndexOf("2"));
        assertEquals("Should still find remaining elements", 1, vector.lastIndexOf("3"));
    }

    @Test
    public void testLastIndexOfAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.clear();

        assertEquals("Should not find any element after clear", -1, vector.lastIndexOf("1"));
        assertEquals("Should not find any element after clear", -1, vector.lastIndexOf("2"));
        assertEquals("Should not find any element after clear", -1, vector.lastIndexOf("3"));
    }

    @Test
    public void testLastIndexOfWithDifferentObjectTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        assertEquals("Should find string element", 0, vector.lastIndexOf("1"));
        assertEquals("Should find integer element", 1, vector.lastIndexOf(2));
        assertEquals("Should find double element", 2, vector.lastIndexOf(3.0));
        assertEquals("Should not find mismatched types", -1, vector.lastIndexOf(1));
    }

    @Test
    public void testLastIndexOfAfterSetOperation()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");

        assertEquals("Should not find replaced element", -1, vector.lastIndexOf("2"));
        assertEquals("Should find new element", 1, vector.lastIndexOf("4"));
    }

    @Test
    public void testLastIndexOfWithLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertEquals("Should find element at beginning", 0, vector.lastIndexOf("0"));
        assertEquals("Should find element in middle", 500, vector.lastIndexOf("500"));
        assertEquals("Should find element at end", 999, vector.lastIndexOf("999"));
        assertEquals("Should not find non-existent element", -1, vector.lastIndexOf("1000"));
    }

    @Test
    public void testLastIndexOfAfterAddAll()
    {
        VectorAdapter vec = new VectorAdapter();

        vec.add("4");
        vec.add("5");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.addAll(vec);

        assertEquals("Should find original elements", 0, vector.lastIndexOf("1"));
        assertEquals("Should find added elements", 3, vector.lastIndexOf("4"));
        assertEquals("Should find added elements", 4, vector.lastIndexOf("5"));
    }

    @Test
    public void testLastIndexOfAfterRemoveAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.removeAll(vec);

        assertEquals("Should find remaining elements", 0, vector.lastIndexOf("1"));
        assertEquals("Should not find removed elements", -1, vector.lastIndexOf("2"));
        assertEquals("Should not find removed elements", -1, vector.lastIndexOf("3"));
        assertEquals("Should find remaining elements", 1, vector.lastIndexOf("4"));
    }

    @Test
    public void testLastIndexOfAfterRetainAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.retainAll(vec);

        assertEquals("Should not find non-retained elements", -1, vector.lastIndexOf("1"));
        assertEquals("Should find retained elements", 0, vector.lastIndexOf("2"));
        assertEquals("Should find retained elements", 1, vector.lastIndexOf("3"));
        assertEquals("Should not find non-retained elements", -1, vector.lastIndexOf("4"));
    }

    @Test
    public void testLastIndexOfWithMixedNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("2");
        vector.add(null);
        vector.add("3");

        assertEquals("Should find last occurrence of null", 3, vector.lastIndexOf(null));
        assertEquals("Should find string elements correctly", 0, vector.lastIndexOf("1"));
        assertEquals("Should find string elements correctly", 2, vector.lastIndexOf("2"));
        assertEquals("Should find string elements correctly", 4, vector.lastIndexOf("3"));
    }

    @Test
    public void testLastIndexOfWithEmptyString()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("");

        assertEquals("Should find empty string at last occurrence", 1, vector.lastIndexOf(""));
    }

    @Test
    public void testLastIndexOfWithSameObjectMultipleTimes()
    {
        Object obj = new Object();
        vector = new VectorAdapter();
        vector.add(obj);
        vector.add("1");
        vector.add(obj);

        assertEquals("Should find last occurrence of object", 2, vector.lastIndexOf(obj));
    }

    @Test
    public void testLastIndexOfAfterMultipleOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove(1);
        vector.add("4");
        vector.set(0, "5");

        assertEquals("Should find correct last index after multiple operations", 0, vector.lastIndexOf("5"));
        assertEquals("Should find correct last index after multiple operations", 1, vector.lastIndexOf("3"));
        assertEquals("Should find correct last index after multiple operations", 2, vector.lastIndexOf("4"));
        assertEquals("Should not find removed element", -1, vector.lastIndexOf("2"));
    }

    @Test
    public void testLastIndexOfAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.trimToSize();

        assertEquals("Should find elements correctly after trim", 0, vector.lastIndexOf("1"));
        assertEquals("Should find elements correctly after trim", 1, vector.lastIndexOf("2"));
        assertEquals("Should find elements correctly after trim", 2, vector.lastIndexOf("3"));
    }

    @Test
    public void testLastIndexOfAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setSize(5);
        vector.setSize(2);

        assertEquals("Should find elements correctly after setSize", 0, vector.lastIndexOf("1"));
        assertEquals("Should find elements correctly after setSize", 1, vector.lastIndexOf("2"));
        assertEquals("Should not find elements beyond new size", -1, vector.lastIndexOf("3"));
    }

    @Test
    public void testLastIndexOfWithBooleanElements()
    {
        vector = new VectorAdapter();
        vector.add(true);
        vector.add(false);
        vector.add(true);

        assertEquals("Should find boolean elements correctly", 2, vector.lastIndexOf(true));
        assertEquals("Should find boolean elements correctly", 1, vector.lastIndexOf(false));
    }

    @Test
    public void testLastIndexOfWithIndexExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        assertEquals("Should find element ending at index 4", 2, vector.lastIndexOf("3", 4));
        assertEquals("Should find element ending at index 2", 2, vector.lastIndexOf("3", 2));
        assertEquals("Should not find element ending at index 1", -1, vector.lastIndexOf("3", 1));
        assertEquals("Should not find element ending at index 0", -1, vector.lastIndexOf("3", 0));
    }

    @Test
    public void testLastIndexOfWithIndexNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 for non-existing element", -1, vector.lastIndexOf("4", 2));
        assertEquals("Should return -1 for non-existing element", -1, vector.lastIndexOf("4", 1));
        assertEquals("Should return -1 for non-existing element", -1, vector.lastIndexOf("4", 0));
    }

    @Test
    public void testLastIndexOfWithIndexNullElementWhenPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);
        vector.add("5");

        assertEquals("Should find last null ending at index 4", 3, vector.lastIndexOf(null, 4));
        assertEquals("Should find first null ending at index 2", 1, vector.lastIndexOf(null, 2));
        assertEquals("Should not find null ending at index 0", -1, vector.lastIndexOf(null, 0));
    }

    @Test
    public void testLastIndexOfWithIndexNullElementWhenAbsent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 for null when not present", -1, vector.lastIndexOf(null, 2));
        assertEquals("Should return -1 for null when not present", -1, vector.lastIndexOf(null, 1));
        assertEquals("Should return -1 for null when not present", -1, vector.lastIndexOf(null, 0));
    }

    @Test
    public void testLastIndexOfWithIndexWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        vector.add("2");
        vector.add("1");

        assertEquals("Should find last occurrence ending at index 4", 4, vector.lastIndexOf("1", 4));
        assertEquals("Should find third occurrence ending at index 3", 2, vector.lastIndexOf("1", 3));
        assertEquals("Should find second occurrence ending at index 2", 2, vector.lastIndexOf("1", 2));
        assertEquals("Should find first occurrence ending at index 1", 1, vector.lastIndexOf("1", 1));
        assertEquals("Should find first occurrence ending at index 0", 0, vector.lastIndexOf("1", 0));
        assertEquals("Should not find occurrence ending at negative index", -1, vector.lastIndexOf("1", -1));
    }

    @Test
    public void testLastIndexOfWithIndexAfterElementRemoval()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.remove("2");

        assertEquals("Should find remaining elements ending at index 2", 1, vector.lastIndexOf("3", 2));
        assertEquals("Should find remaining elements ending at index 1", 0, vector.lastIndexOf("1", 1));
    }

    @Test
    public void testLastIndexOfWithIndexWithDifferentObjectTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        vector.add("4");
        vector.add(5);

        assertEquals("Should find integer element ending at index 4", 1, vector.lastIndexOf(2, 4));
        assertEquals("Should find double element ending at index 3", 2, vector.lastIndexOf(3.0, 3));
        assertEquals("Should find string element ending at index 0", 0, vector.lastIndexOf("1", 0));
        assertEquals("Should not find mismatched types", -1, vector.lastIndexOf(1, 4));
    }


    @Test
    public void testLastIndexOfWithIndexAfterSetOperation()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.set(1, "5");

        assertEquals("Should not find replaced element", -1, vector.lastIndexOf("2", 3));
        assertEquals("Should find new element ending at index 3", 1, vector.lastIndexOf("5", 3));
        assertEquals("Should find new element ending at index 1", 1, vector.lastIndexOf("5", 1));
    }

    @Test
    public void testLastIndexOfWithIndexWithLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertEquals("Should find element at end ending at index 999", 999, vector.lastIndexOf("999", 999));
        assertEquals("Should find element in middle ending at index 500", 500, vector.lastIndexOf("500", 500));
        assertEquals("Should find element in middle ending at higher index", 500, vector.lastIndexOf("500", 600));
        assertEquals("Should find element at beginning ending at index 0", 0, vector.lastIndexOf("0", 0));
        assertEquals("Should not find element ending before its position", -1, vector.lastIndexOf("500", 499));
        assertEquals("Should not find non-existent element", -1, vector.lastIndexOf("1000", 999));
    }

    @Test
    public void testLastIndexOfWithIndexAfterAddAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");
        vec.add("6");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.addAll(vec);

        assertEquals("Should find added elements ending at index 5", 5, vector.lastIndexOf("6", 5));
        assertEquals("Should find added elements ending at index 4", 4, vector.lastIndexOf("5", 4));
        assertEquals("Should not find added elements after their position", -1, vector.lastIndexOf("6", 3));
    }

    @Test
    public void testLastIndexOfWithIndexAfterRemoveAll()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.removeAll(vec);

        assertEquals("Should find remaining elements ending at index 1", 1, vector.lastIndexOf("4", 1));
        assertEquals("Should find remaining elements ending at index 0", 0, vector.lastIndexOf("1", 0));
    }

    @Test
    public void testLastIndexOfWithIndexWithMixedNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("2");
        vector.add(null);
        vector.add("3");

        assertEquals("Should find last null ending at index 4", 3, vector.lastIndexOf(null, 4));
        assertEquals("Should find first null ending at index 2", 1, vector.lastIndexOf(null, 2));
        assertEquals("Should not find null ending at index 0", -1, vector.lastIndexOf(null, 0));
        assertEquals("Should find string elements correctly", 4, vector.lastIndexOf("3", 4));
        assertEquals("Should find string elements correctly", 2, vector.lastIndexOf("2", 3));
    }

    @Test
    public void testLastIndexOfWithIndexWithEmptyString()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("");
        vector.add("2");
        vector.add("");

        assertEquals("Should find last empty string ending at index 3", 3, vector.lastIndexOf("", 3));
        assertEquals("Should find first empty string ending at index 2", 1, vector.lastIndexOf("", 2));
    }

    @Test
    public void testLastIndexOfWithIndexWithSameObjectMultipleTimes()
    {
        Object obj = new Object();
        vector = new VectorAdapter();
        vector.add(obj);
        vector.add("1");
        vector.add(obj);
        vector.add("2");
        vector.add(obj);

        assertEquals("Should find last occurrence ending at index 4", 4, vector.lastIndexOf(obj, 4));
        assertEquals("Should find second occurrence ending at index 3", 2, vector.lastIndexOf(obj, 3));
        assertEquals("Should find first occurrence ending at index 1", 0, vector.lastIndexOf(obj, 1));
    }

    @Test
    public void testLastIndexOfWithIndexAfterMultipleOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.remove(1);
        vector.add("5");
        vector.set(0, "6");

        assertEquals("Should find correct last index after multiple operations", 3, vector.lastIndexOf("5", 3));
        assertEquals("Should find correct last index after multiple operations", 2, vector.lastIndexOf("4", 2));
        assertEquals("Should find correct last index after multiple operations", 0, vector.lastIndexOf("6", 1));
        assertEquals("Should not find removed element", -1, vector.lastIndexOf("2", 3));
    }

    @Test
    public void testLastIndexOfWithIndexAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.trimToSize();

        assertEquals("Should find elements correctly after trim", 3, vector.lastIndexOf("4", 3));
        assertEquals("Should find elements correctly after trim", 2, vector.lastIndexOf("3", 3));
        assertEquals("Should not find elements beyond range", -1, vector.lastIndexOf("1", -1));
    }

    @Test
    public void testLastIndexOfWithIndexAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.setSize(6);
        vector.setSize(2);

        assertEquals("Should find elements correctly after setSize", 1, vector.lastIndexOf("2", 1));
        assertEquals("Should find elements correctly after setSize", 0, vector.lastIndexOf("1", 0));
    }

    @Test
    public void testLastIndexOfWithIndexWithBooleanElements()
    {
        vector = new VectorAdapter();
        vector.add(true);
        vector.add(false);
        vector.add(true);
        vector.add(false);

        assertEquals("Should find boolean elements correctly", 3, vector.lastIndexOf(false, 3));
        assertEquals("Should find boolean elements correctly", 2, vector.lastIndexOf(true, 3));
        assertEquals("Should find first occurrence of boolean", 0, vector.lastIndexOf(true, 1));
        assertEquals("Should find first occurrence of boolean", 1, vector.lastIndexOf(false, 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLastIndexOfWithIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.lastIndexOf("1", 4);
    }

    @Test
    public void testLastIndexOfWithIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 when index is negative", -1, vector.lastIndexOf("1", -1));
        assertEquals("Should return -1 when index is negative", -1, vector.lastIndexOf("2", -2));
        assertEquals("Should return -1 when index is negative", -1, vector.lastIndexOf("3", -3));
    }

    @Test
    public void testLastIndexOfWithIndexEqualToSizeMinusOne()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should find element when index equals size-1", 2, vector.lastIndexOf("3", 2));
        assertEquals("Should find element when index equals size-1", 1, vector.lastIndexOf("2", 2));
        assertEquals("Should find element when index equals size-1", 0, vector.lastIndexOf("1", 2));
    }

    @Test
    public void testLastIndexOfWithIndexEdgeCases()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        assertEquals("Should find element at exact end index", 2, vector.lastIndexOf("3", 2));
        assertEquals("Should find element before end index", 1, vector.lastIndexOf("2", 2));
        assertEquals("Should not find element when end index is too low", -1, vector.lastIndexOf("4", 2));
        assertEquals("Should find first element with min valid index", 0, vector.lastIndexOf("1", 0));
    }

    // TO CONTINUE

    @Test
    public void testElementAtValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return element at index 0", "1", vector.elementAt(0));
        assertEquals("Should return element at index 1", "2", vector.elementAt(1));
        assertEquals("Should return element at index 2", "3", vector.elementAt(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.elementAt(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.elementAt(3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testElementAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.elementAt(5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testElementAtOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.elementAt(0);
    }

    @Test
    public void testElementAtAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");
        vector.remove(2);

        assertEquals("Should return modified element at index 1", "4", vector.elementAt(1));
        assertEquals("Should return element at index 0", "1", vector.elementAt(0));
    }

    @Test

    public void testElementAtWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add("2");
        vector.add(null);

        assertNull("Should return null element at index 0", vector.elementAt(0));
        assertNull("Should return null element at index 2", vector.elementAt(2));
    }

    @Test
    public void testElementAtWithMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        assertEquals("Should return string element", "1", vector.elementAt(0));
        assertEquals("Should return integer element", 2, vector.elementAt(1));
        assertEquals("Should return double element", 3.0, vector.elementAt(2));
    }

    @Test
    public void testElementAtAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setSize(5);
        vector.setSize(2);

        assertEquals("Should return element at index 0", "1", vector.elementAt(0));
        assertEquals("Should return element at index 1", "2", vector.elementAt(1));
    }

    @Test
    public void testElementAtWithLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertEquals("Should return element at index 0", "0", vector.elementAt(0));
        assertEquals("Should return element at index 500", "500", vector.elementAt(500));
        assertEquals("Should return element at index 999", "999", vector.elementAt(999));
    }

    @Test
    public void testFirstElementNonEmptyVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return first element", "1", vector.firstElement());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFirstElementEmptyVector()
    {
        vector = new VectorAdapter();
        vector.firstElement();
    }

    @Test
    public void testFirstElementAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(0, "4");
        vector.remove(0);

        assertEquals("Should return new first element", "2", vector.firstElement());
    }

    @Test
    public void testFirstElementWithNullElement()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add("2");
        vector.add("3");

        assertNull("Should return null first element", vector.firstElement());
    }

    @Test
    public void testFirstElementWithSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");

        assertEquals("Should return the single element", "1", vector.firstElement());
    }

    @Test
    public void testFirstElementAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.clear();
        vector.add("4");

        assertEquals("Should return new first element after clear", "4", vector.firstElement());
    }

    @Test
    public void testFirstElementAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.setSize(3);
        vector.set(0, "1");

        assertEquals("Should return element at index 0", "1", vector.firstElement());
    }

    @Test
    public void testLastElementNonEmptyVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return last element", "3", vector.lastElement());
    }

    @Test(expected = NoSuchElementException.class)
    public void testLastElementEmptyVector()
    {
        vector = new VectorAdapter();
        vector.lastElement();
    }

    @Test
    public void testLastElementAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(2, "4");
        vector.remove(2);
        vector.add("5");

        assertEquals("Should return new last element", "5", vector.lastElement());
    }

    @Test
    public void testLastElementWithNullElement()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add("2");
        vector.add(null);

        assertNull("Should return null last element", vector.lastElement());
    }

    @Test
    public void testLastElementWithSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");

        assertEquals("Should return the single element", "1", vector.lastElement());
    }

    @Test
    public void testLastElementAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.clear();
        vector.add("4");

        assertEquals("Should return new last element after clear", "4", vector.lastElement());
    }

    @Test
    public void testLastElementAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.setSize(4);
        vector.set(3, "3");

        assertEquals("Should return element at last index", "3", vector.lastElement());
    }

    @Test
    public void testLastElementWithLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertEquals("Should return last element of large vector", "999", vector.lastElement());
    }

    @Test
    public void testFirstAndLastElementSameForSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");

        assertEquals("First and last element should be equal for single element", vector.firstElement(), vector.lastElement());
    }

    @Test
    public void testElementAtFirstElementConsistency()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("elementAt(0) should equal firstElement()", vector.elementAt(0), vector.firstElement());
    }

    @Test
    public void testElementAtLastElementConsistency()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("elementAt(size-1) should equal lastElement()", vector.elementAt(vector.size() - 1), vector.lastElement());
    }

    @Test
    public void testSetElementAtValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setElementAt("4", 1);

        assertEquals("Element at index 1 should be updated", "4", vector.get(1));
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should remain unchanged", "3", vector.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setElementAt("3", -1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setElementAt("3", 2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetElementAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setElementAt("3", 5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetElementAtOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.setElementAt("1", 0);
    }

    @Test
    public void testSetElementAtFirstElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setElementAt("0", 0);

        assertEquals("First element should be updated", "0", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
        assertEquals("Third element should remain unchanged", "3", vector.get(2));
    }

    @Test
    public void testSetElementAtLastElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setElementAt("4", 2);

        assertEquals("Last element should be updated", "4", vector.get(2));
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
    }

    @Test
    public void testSetElementAtWithNull()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setElementAt(null, 1);

        assertNull("Element at index 1 should be null", vector.get(1));
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should remain unchanged", "3", vector.get(2));
    }

    @Test
    public void testSetElementAtWithDifferentTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setElementAt(4, 1);
        vector.setElementAt(5.0, 2);

        assertEquals("Element at index 1 should be integer", 4, vector.get(1));
        assertEquals("Element at index 2 should be double", 5.0, vector.get(2));
        assertEquals("First element should remain unchanged", "1", vector.get(0));
    }

    @Test
    public void testSetElementAtMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setElementAt("4", 1);
        vector.setElementAt("5", 1);
        vector.setElementAt("6", 1);

        assertEquals("Element at index 1 should be last set value", "6", vector.get(1));
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSetElementAtAfterCapacityChange()
    {
        vector = new VectorAdapter(2);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setElementAt("4", 1);

        assertEquals("Element should be updated after capacity growth", "4", vector.get(1));
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testRemoveElementAtValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.removeElementAt(1);

        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.removeElementAt(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.removeElementAt(2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.removeElementAt(5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementAtOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.removeElementAt(0);
    }

    @Test
    public void testRemoveElementAtFirstElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.removeElementAt(0);

        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should be shifted", "2", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveElementAtLastElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.removeElementAt(2);

        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
    }

    @Test
    public void testRemoveElementAtWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        vector.removeElementAt(1);

        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveElementAtMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        vector.removeElementAt(1);
        vector.removeElementAt(1);
        vector.removeElementAt(1);

        assertEquals("Size should be decreased to 2", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be last remaining", "5", vector.get(1));
    }

    @Test
    public void testRemoveElementAtAllElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.removeElementAt(0);
        vector.removeElementAt(0);
        vector.removeElementAt(0);

        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testInsertElementAtValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        vector.insertElementAt("2", 1);

        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Inserted element should be at index 1", "2", vector.get(1));
        assertEquals("Third element should be shifted", "3", vector.get(2));
    }

    @Test
    public void testInsertElementAtBeginning()
    {
        vector = new VectorAdapter();
        vector.add("2");
        vector.add("3");

        vector.insertElementAt("1", 0);

        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("Inserted element should be at beginning", "1", vector.get(0));
        assertEquals("First element should be shifted", "2", vector.get(1));
        assertEquals("Second element should be shifted", "3", vector.get(2));
    }

    @Test
    public void testInsertElementAtEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.insertElementAt("3", 2);

        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Inserted element should be at end", "3", vector.get(2));
    }

    @Test
    public void testInsertElementAtEmptyVector()
    {
        vector = new VectorAdapter();

        vector.insertElementAt("1", 0);

        assertEquals("Size should be 1", 1, vector.size());
        assertEquals("Inserted element should be at index 0", "1", vector.get(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInsertElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.insertElementAt("3", -1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInsertElementAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.insertElementAt("3", 3);
    }

    @Test
    public void testInsertElementAtWithNull()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        vector.insertElementAt(null, 1);

        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertNull("Inserted element should be null", vector.get(1));
        assertEquals("Third element should be shifted", "3", vector.get(2));
    }

    @Test
    public void testInsertElementAtWithDifferentTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        vector.insertElementAt(2, 1);

        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Inserted element should be integer", 2, vector.get(1));
        assertEquals("Third element should be shifted", "3", vector.get(2));
    }

    @Test
    public void testInsertElementAtMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("4");

        vector.insertElementAt("2", 1);
        vector.insertElementAt("3", 2);

        assertEquals("Size should be 4", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second inserted element should be at index 1", "2", vector.get(1));
        assertEquals("First inserted element should be at index 2", "3", vector.get(2));
        assertEquals("Original element should be shifted", "4", vector.get(3));
    }

    @Test
    public void testInsertElementAtCapacityGrowth()
    {
        vector = new VectorAdapter(2);
        vector.add("1");
        vector.add("2");

        vector.insertElementAt("3", 1);

        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("Capacity should grow to accommodate", 4, vector.capacity());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Inserted element should be at index 1", "3", vector.get(1));
        assertEquals("Second element should be shifted", "2", vector.get(2));
    }

    @Test
    public void testInsertElementAtAfterRemoveOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.removeElementAt(1);
        vector.removeElementAt(1);

        vector.insertElementAt("5", 1);

        assertEquals("Size should be 3", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Inserted element should be at index 1", "5", vector.get(1));
        assertEquals("Last element should remain", "4", vector.get(2));
    }

    @Test
    public void testAddElementToEmptyVector()
    {
        vector = new VectorAdapter();
        vector.addElement("1");

        assertEquals("Size should be 1 after adding element", 1, vector.size());
        assertEquals("Element should be added at index 0", "1", vector.get(0));
        assertFalse("Vector should not be empty", vector.isEmpty());
    }

    @Test
    public void testAddElementToNonEmptyVector()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");

        assertEquals("Size should be 2 after adding elements", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
    }

    @Test
    public void testAddElementMultipleElements()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 5; i++)
            vector.addElement("" + i);

        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertEquals("Element " + i + " should match", "" + i, vector.get(i));
    }

    @Test
    public void testAddElementNullElement()
    {
        vector = new VectorAdapter();
        vector.addElement(null);

        assertEquals("Size should be 1 after adding null", 1, vector.size());
        assertNull("Element should be null", vector.get(0));
    }

    @Test
    public void testAddElementWithCapacityGrowth()
    {
        vector = new VectorAdapter(2);
        vector.addElement("1");
        vector.addElement("2");
        vector.addElement("3");

        assertEquals("Size should be 3 after adding elements", 3, vector.size());
        assertTrue("Capacity should grow to accommodate elements", vector.capacity() >= 3);
        assertEquals("First element should be '1'", "1", vector.get(0));
        assertEquals("Second element should be '2'", "2", vector.get(1));
        assertEquals("Third element should be '3'", "3", vector.get(2));
    }

    @Test
    public void testAddElementMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement(2);
        vector.addElement(3.0);

        assertEquals("Size should be 3 after adding elements", 3, vector.size());
        assertEquals("First element should be string", "1", vector.get(0));
        assertEquals("Second element should be integer", 2, vector.get(1));
        assertEquals("Third element should be double", 3.0, vector.get(2));
    }

    @Test
    public void testAddElementDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("1");
        vector.addElement("1");

        assertEquals("Size should be 3 after adding duplicate elements", 3, vector.size());
        assertEquals("All elements should be '1'", "1", vector.get(0));
        assertEquals("All elements should be '1'", "1", vector.get(1));
        assertEquals("All elements should be '1'", "1", vector.get(2));
    }

    @Test
    public void testAddElementLargeNumberOfElements()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.addElement("" + i);

        assertEquals("Size should be 1000 after adding elements", 1000, vector.size());
        assertEquals("First element should be '0'", "0", vector.get(0));
        assertEquals("Last element should be '999'", "999", vector.get(999));
        assertTrue("Capacity should accommodate large number of elements", vector.capacity() >= 1000);
    }

    @Test
    public void testAddElementAfterRemoveOperations()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");
        vector.addElement("3");

        vector.removeElement("2");
        vector.addElement("4");

        assertEquals("Size should be 3 after operations", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be '3'", "3", vector.get(1));
        assertEquals("New element should be added at end", "4", vector.get(2));
    }

    @Test
    public void testAddElementAfterClear()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");

        vector.clear();
        vector.addElement("3");

        assertEquals("Size should be 1 after clear and add", 1, vector.size());
        assertEquals("Element should be the new one", "3", vector.get(0));
    }

    @Test
    public void testRemoveElementExistingElement()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");
        vector.addElement("3");

        boolean result = vector.removeElement("2");

        assertTrue("Should return true for existing element", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveElementNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");
        vector.addElement("3");

        boolean result = vector.removeElement("4");

        assertFalse("Should return false for non-existing element", result);
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testRemoveElementNullElementWhenPresent()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement(null);
        vector.addElement("3");

        boolean result = vector.removeElement(null);

        assertTrue("Should return true for null element", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveElementNullElementWhenAbsent()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");
        vector.addElement("3");

        boolean result = vector.removeElement(null);

        assertFalse("Should return false for null when not present", result);
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testRemoveElementFromEmptyVector()
    {
        vector = new VectorAdapter();

        boolean result = vector.removeElement("1");

        assertFalse("Should return false for empty vector", result);
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    @Test
    public void testRemoveElementFirstOccurrenceOnly()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");
        vector.addElement("1");
        vector.addElement("3");

        boolean result = vector.removeElement("1");

        assertTrue("Should return true for existing element", result);
        assertEquals("Size should be decreased", 3, vector.size());
        assertEquals("First occurrence should be removed", "2", vector.get(0));
        assertEquals("Second occurrence should remain", "1", vector.get(1));
        assertEquals("Third element should be shifted", "3", vector.get(2));
    }

    @Test
    public void testRemoveElementAllOccurrences()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("1");
        vector.addElement("1");

        boolean firstResult = vector.removeElement("1");
        boolean secondResult = vector.removeElement("1");
        boolean thirdResult = vector.removeElement("1");

        assertTrue("First removal should return true", firstResult);
        assertTrue("Second removal should return true", secondResult);
        assertTrue("Third removal should return true", thirdResult);
        assertEquals("Size should be 0 after all removals", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveElementWithDifferentObjectTypes()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement(2);
        vector.addElement(3.0);

        boolean stringResult = vector.removeElement("1");
        boolean intResult = vector.removeElement(2);
        boolean doubleResult = vector.removeElement(3.0);

        assertTrue("Should remove string element", stringResult);
        assertTrue("Should remove integer element", intResult);
        assertTrue("Should remove double element", doubleResult);
        assertEquals("Size should be 0 after all removals", 0, vector.size());
    }

    @Test
    public void testRemoveElementAfterAddOperations()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");

        boolean result = vector.removeElement("2");
        vector.addElement("3");

        assertTrue("Should return true for existing element", result);
        assertEquals("Size should be 2 after operations", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("New element should be added", "3", vector.get(1));
    }

    @Test
    public void testRemoveElementAfterSetOperations()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");
        vector.addElement("3");

        vector.set(1, "4");
        boolean result = vector.removeElement("4");

        assertTrue("Should return true for modified element", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Third element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveElementLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.addElement("" + i);

        boolean result = vector.removeElement("500");

        assertTrue("Should return true for existing element", result);
        assertEquals("Size should be decreased", 999, vector.size());
        assertEquals("Element before removed should remain", "499", vector.get(499));
        assertEquals("Element after removed should be shifted", "501", vector.get(500));
    }

    @Test
    public void testRemoveElementWithObjectIdentity()
    {
        String str1 = new String("test");
        String str2 = new String("test");

        vector = new VectorAdapter();
        vector.addElement(str1);

        boolean result = vector.removeElement(str2);

        assertTrue("Should remove equal string", result);
        assertEquals("Size should be decreased", 0, vector.size());
    }

    @Test
    public void testRemoveElementMultipleCallsSameElement()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");
        vector.addElement("3");

        boolean firstCall = vector.removeElement("2");
        boolean secondCall = vector.removeElement("2");

        assertTrue("First call should return true", firstCall);
        assertFalse("Second call should return false", secondCall);
        assertEquals("Size should be 2 after first removal", 2, vector.size());
    }

    @Test
    public void testRemoveElementThenAddSameElement()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement("2");

        boolean removeResult = vector.removeElement("2");
        vector.addElement("2");

        assertTrue("Should return true for removal", removeResult);
        assertEquals("Size should be 2 after operations", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Element should be re-added", "2", vector.get(1));
    }

    @Test
    public void testRemoveElementWithMixedNullElements()
    {
        vector = new VectorAdapter();
        vector.addElement("1");
        vector.addElement(null);
        vector.addElement("2");
        vector.addElement(null);
        vector.addElement("3");

        boolean firstNullResult = vector.removeElement(null);
        boolean secondNullResult = vector.removeElement(null);

        assertTrue("First null removal should return true", firstNullResult);
        assertTrue("Second null removal should return true", secondNullResult);
        assertEquals("Size should be 3 after null removals", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Third element should be shifted", "2", vector.get(1));
        assertEquals("Fifth element should be shifted", "3", vector.get(2));
    }

    @Test
    public void testRemoveAllElementsEmptyVector()
    {
        vector = new VectorAdapter();
        vector.removeAllElements();

        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        vector.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        vector.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        vector.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsAfterAddOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.removeAllElements();
        vector.add("4");

        assertEquals("Size should be 1 after adding new element", 1, vector.size());
        assertEquals("New element should be added", "4", vector.get(0));
    }

    @Test
    public void testRemoveAllElementsAfterSetOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");
        vector.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsAfterRemoveOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove(1);
        vector.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsCapacityRemains()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        int capacityBefore = vector.capacity();
        vector.removeAllElements();

        assertEquals("Capacity should remain unchanged", capacityBefore, vector.capacity());
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllElementsMultipleCalls()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.removeAllElements();
        vector.removeAllElements();

        assertEquals("Size should remain 0 after second call", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    @Test
    public void testCloneEmptyVector()
    {
        vector = new VectorAdapter();
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());
        assertTrue("Clone should be empty", vec.isEmpty());
    }

    @Test
    public void testCloneSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());
        assertEquals("Clone element should match original", vector.get(0), vec.get(0));
    }

    @Test
    public void testCloneMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());

        for(int i = 0; i < vector.size(); i++)
            assertEquals("Element " + i + " should match", vector.get(i), vec.get(i));
    }

    @Test
    public void testCloneWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());
        assertEquals("First element should match", vector.get(0), vec.get(0));
        assertNull("Null element should be preserved", vec.get(1));
        assertEquals("Third element should match", vector.get(2), vec.get(2));
    }

    @Test
    public void testCloneMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());
        assertEquals("String element should match", vector.get(0), vec.get(0));
        assertEquals("Integer element should match", vector.get(1), vec.get(1));
        assertEquals("Double element should match", vector.get(2), vec.get(2));
    }

    @Test
    public void testCloneDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());

        for(int i = 0; i < vector.size(); i++)
            assertEquals("All elements should be '1'", "1", vec.get(i));
    }

    @Test
    public void testCloneLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());

        for(int i = 0; i < vector.size(); i++)
            assertEquals("Element " + i + " should match", vector.get(i), vec.get(i));
    }

    @Test
    public void testCloneIndependenceFromOriginal()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = (VectorAdapter) vector.clone();
        vector.set(1, "4");

        assertEquals("Clone should not be affected by original modification", "2", vec.get(1));
        assertEquals("Original should be modified", "4", vector.get(1));
    }

    @Test
    public void testCloneAddToOriginalAfterClone()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter vec = (VectorAdapter) vector.clone();
        vector.add("3");

        assertEquals("Clone size should remain unchanged", 2, vec.size());
        assertEquals("Original size should increase", 3, vector.size());
    }

    @Test
    public void testCloneRemoveFromOriginalAfterClone()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = (VectorAdapter) vector.clone();
        vector.remove(1);

        assertEquals("Clone size should remain unchanged", 3, vec.size());
        assertEquals("Original size should decrease", 2, vector.size());
        assertEquals("Clone should preserve removed element", "2", vec.get(1));
    }

    @Test
    public void testCloneModifyClone()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = (VectorAdapter) vector.clone();
        vec.set(1, "4");

        assertEquals("Original should not be affected by clone modification", "2", vector.get(1));
        assertEquals("Clone should be modified", "4", vec.get(1));
    }

    @Test
    public void testCloneCapacityMatchesOriginal()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertEquals("Clone size should match original", vector.size(), vec.size());
    }

    @Test
    public void testCloneAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.setSize(5);

        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertEquals("Clone size should match original", vector.size(), vec.size());
        assertEquals("Clone null elements should match original", vector.get(4), vec.get(4));
    }

    @Test
    public void testCloneAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.trimToSize();

        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertEquals("Clone capacity should match original", vector.capacity(), vec.capacity());
        assertEquals("Clone size should match original", vector.size(), vec.size());
    }

    @Test
    public void testToArrayEmptyVector()
    {
        vector = new VectorAdapter();
        Object[] array = vector.toArray();

        assertNotNull("Returned array should not be null", array);
        assertEquals("Array length should match vector size", 0, array.length);
    }

    @Test
    public void testToArraySingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        Object[] array = vector.toArray();

        assertNotNull("Returned array should not be null", array);
        assertEquals("Array length should match vector size", 1, array.length);
        assertEquals("Array element should match vector element", "1", array[0]);
    }

    @Test
    public void testToArrayMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        Object[] array = vector.toArray();

        assertNotNull("Returned array should not be null", array);
        assertEquals("Array length should match vector size", 3, array.length);
        assertEquals("First array element should match", "1", array[0]);
        assertEquals("Second array element should match", "2", array[1]);
        assertEquals("Third array element should match", "3", array[2]);
    }

    @Test
    public void testToArrayWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        Object[] array = vector.toArray();

        assertNotNull("Returned array should not be null", array);
        assertEquals("Array length should match vector size", 3, array.length);
        assertEquals("First array element should match", "1", array[0]);
        assertNull("Second array element should be null", array[1]);
        assertEquals("Third array element should match", "3", array[2]);
    }

    @Test
    public void testToArrayMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        Object[] array = vector.toArray();

        assertNotNull("Returned array should not be null", array);
        assertEquals("Array length should match vector size", 3, array.length);
        assertEquals("First array element should match", "1", array[0]);
        assertEquals("Second array element should match", 2, array[1]);
        assertEquals("Third array element should match", 3.0, array[2]);
    }

    @Test
    public void testToArrayDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        Object[] array = vector.toArray();

        assertNotNull("Returned array should not be null", array);
        assertEquals("Array length should match vector size", 3, array.length);
        assertEquals("All array elements should be '1'", "1", array[0]);
        assertEquals("All array elements should be '1'", "1", array[1]);
        assertEquals("All array elements should be '1'", "1", array[2]);
    }

    @Test
    public void testToArrayLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        Object[] array = vector.toArray();

        assertNotNull("Returned array should not be null", array);
        assertEquals("Array length should match vector size", 1000, array.length);

        for(int i = 0; i < 1000; i++)
            assertEquals("Array element " + i + " should match", "" + i, array[i]);
    }

    @Test
    public void testToArrayAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object[] array1 = vector.toArray();

        vector.set(1, "4");
        vector.remove(2);

        Object[] array2 = vector.toArray();

        assertEquals("First array should contain original elements", "2", array1[1]);
        assertEquals("Second array should contain modified elements", "4", array2[1]);
        assertEquals("Second array length should match modified size", 2, array2.length);
    }

    @Test
    public void testToArrayWithParameterEmptyVector()
    {
        vector = new VectorAdapter();
        Object[] a = new Object[0];
        Object[] result = vector.toArray(a);

        assertNotNull("Returned array should not be null", result);
        assertEquals("Array length should be 0", 0, result.length);
    }

    @Test
    public void testToArrayWithParameterExactSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object[] a = new Object[3];
        Object[] result = vector.toArray(a);

        assertSame("Should return the same array", a, result);
        assertEquals("First array element should match", "1", result[0]);
        assertEquals("Second array element should match", "2", result[1]);
        assertEquals("Third array element should match", "3", result[2]);
    }

    @Test
    public void testToArrayWithParameterLargerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        Object[] a = new Object[5];
        a[2] = "existing";
        a[3] = "existing";
        a[4] = "existing";

        Object[] result = vector.toArray(a);

        assertSame("Should return the same array", a, result);
        assertEquals("First array element should match", "1", result[0]);
        assertEquals("Second array element should match", "2", result[1]);
        assertNull("Element after vector content should be null", result[2]);
        assertEquals("Existing elements should be preserved", "existing", result[3]);
        assertEquals("Existing elements should be preserved", "existing", result[4]);
    }

    @Test
    public void testToArrayWithParameterSmallerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object[] a = new Object[2];
        Object[] result = vector.toArray(a);

        assertNotSame("Should return a different array", a, result);
        assertEquals("New array length should match vector size", 3, result.length);
        assertEquals("First array element should match", "1", result[0]);
        assertEquals("Second array element should match", "2", result[1]);
        assertEquals("Third array element should match", "3", result[2]);
    }

    @Test
    public void testToArrayWithParameterNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add("2");
        vector.add(null);

        Object[] a = new Object[5];
        Object[] result = vector.toArray(a);

        assertSame("Should return the same array", a, result);
        assertNull("First array element should be null", result[0]);
        assertEquals("Second array element should match", "2", result[1]);
        assertNull("Third array element should be null", result[2]);
        assertNull("Element after vector content should be null", result[3]);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayWithParameterNullArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.toArray(null);
    }

    @Test
    public void testToArrayWithParameterEmptyVectorNullTermination()
    {
        vector = new VectorAdapter();
        Object[] a = new Object[3];
        a[0] = "existing";
        a[1] = "existing";
        a[2] = "existing";

        Object[] result = vector.toArray(a);

        assertSame("Should return the same array", a, result);
        assertNull("First element should be null for empty vector", result[0]);
        assertEquals("Second element should remain unchanged", "existing", result[1]);
        assertEquals("Third element should remain unchanged", "existing", result[2]);
    }

    @Test
    public void testToArrayWithParameterSameRuntimeType()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        String[] a = new String[2];
        String[] result = (String[]) vector.toArray(a);

        assertSame("Should return the same array", a, result);
        assertEquals("First array element should match", "1", result[0]);
        assertEquals("Second array element should match", "2", result[1]);
    }

    @Test
    public void testToArrayWithParameterAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        Object[] a1 = new Object[2];
        Object[] result1 = vector.toArray(a1);

        vector.add("3");
        vector.set(0, "4");

        Object[] a2 = new Object[3];
        Object[] result2 = vector.toArray(a2);

        assertEquals("First result should contain original elements", "1", result1[0]);
        assertEquals("Second result should contain modified elements", "4", result2[0]);
        assertEquals("Second result should contain new element", "3", result2[2]);
    }

    @Test
    public void testToArrayWithParameterLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        Object[] a = new Object[1000];
        Object[] result = vector.toArray(a);

        assertSame("Should return the same array", a, result);

        for(int i = 0; i < 1000; i++)
            assertEquals("Array element " + i + " should match", "" + i, result[i]);

    }

    @Test
    public void testToArrayWithParameterZeroLengthArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        Object[] a = new Object[0];
        Object[] result = vector.toArray(a);

        assertNotSame("Should return a different array", a, result);
        assertEquals("New array length should match vector size", 2, result.length);
        assertEquals("First array element should match", "1", result[0]);
        assertEquals("Second array element should match", "2", result[1]);
    }

    @Test
    public void testGetValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return element at index 0", "1", vector.get(0));
        assertEquals("Should return element at index 1", "2", vector.get(1));
        assertEquals("Should return element at index 2", "3", vector.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.get(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.get(3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.get(5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.get(0);
    }

    @Test
    public void testGetAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");
        vector.remove(2);

        assertEquals("Should return modified element at index 1", "4", vector.get(1));
        assertEquals("Should return element at index 0", "1", vector.get(0));
    }

    @Test
    public void testGetWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add("2");
        vector.add(null);

        assertNull("Should return null element at index 0", vector.get(0));
        assertNull("Should return null element at index 2", vector.get(2));
    }

    @Test
    public void testGetWithMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        assertEquals("Should return string element", "1", vector.get(0));
        assertEquals("Should return integer element", 2, vector.get(1));
        assertEquals("Should return double element", 3.0, vector.get(2));
    }

    @Test
    public void testGetAfterSetSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.setSize(5);
        vector.setSize(2);

        assertEquals("Should return element at index 0", "1", vector.get(0));
        assertEquals("Should return element at index 1", "2", vector.get(1));
    }

    @Test
    public void testGetWithLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        assertEquals("Should return element at index 0", "0", vector.get(0));
        assertEquals("Should return element at index 500", "500", vector.get(500));
        assertEquals("Should return element at index 999", "999", vector.get(999));
    }

    @Test
    public void testSetValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result = vector.set(1, "4");

        assertEquals("Should return previous element at index 1", "2", result);
        assertEquals("Element at index 1 should be updated", "4", vector.get(1));
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should remain unchanged", "3", vector.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.set(-1, "3");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.set(2, "3");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.set(5, "3");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.set(0, "1");
    }

    @Test
    public void testSetFirstElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result = vector.set(0, "0");

        assertEquals("Should return previous first element", "1", result);
        assertEquals("First element should be updated", "0", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
        assertEquals("Third element should remain unchanged", "3", vector.get(2));
    }

    @Test
    public void testSetLastElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result = vector.set(2, "4");

        assertEquals("Should return previous last element", "3", result);
        assertEquals("Last element should be updated", "4", vector.get(2));
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
    }

    @Test
    public void testSetWithNull()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result = vector.set(1, null);

        assertEquals("Should return previous element", "2", result);
        assertNull("Element at index 1 should be null", vector.get(1));
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should remain unchanged", "3", vector.get(2));
    }

    @Test
    public void testSetWithDifferentTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result1 = vector.set(1, 4);
        Object result2 = vector.set(2, 5.0);

        assertEquals("Should return previous element", "2", result1);
        assertEquals("Should return previous element", "3", result2);
        assertEquals("Element at index 1 should be integer", 4, vector.get(1));
        assertEquals("Element at index 2 should be double", 5.0, vector.get(2));
        assertEquals("First element should remain unchanged", "1", vector.get(0));
    }

    @Test
    public void testSetMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");
        vector.set(1, "5");
        vector.set(1, "6");

        assertEquals("Element at index 1 should be last set value", "6", vector.get(1));
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSetAfterCapacityChange()
    {
        vector = new VectorAdapter(2);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result = vector.set(1, "4");

        assertEquals("Should return previous element", "2", result);
        assertEquals("Element should be updated after capacity growth", "4", vector.get(1));
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testAddToEmptyVector()
    {
        vector = new VectorAdapter();
        boolean result = vector.add("1");

        assertTrue("Should return true", result);
        assertEquals("Size should be 1 after adding element", 1, vector.size());
        assertEquals("Element should be added at index 0", "1", vector.get(0));
        assertFalse("Vector should not be empty", vector.isEmpty());
    }

    @Test
    public void testAddToNonEmptyVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        boolean result = vector.add("2");

        assertTrue("Should return true", result);
        assertEquals("Size should be 2 after adding elements", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
    }

    @Test
    public void testAddMultipleElements()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 5; i++)
        {
            boolean result = vector.add("" + i);
            assertTrue("Should return true for each add", result);
        }

        assertEquals("Size should be 5 after adding elements", 5, vector.size());

        for(int i = 0; i < 5; i++)
            assertEquals("Element " + i + " should match", "" + i, vector.get(i));
    }

    @Test
    public void testAddNullElement()
    {
        vector = new VectorAdapter();
        boolean result = vector.add(null);

        assertTrue("Should return true", result);
        assertEquals("Size should be 1 after adding null", 1, vector.size());
        assertNull("Element should be null", vector.get(0));
    }

    @Test
    public void testAddWithCapacityGrowth()
    {
        vector = new VectorAdapter(2);
        vector.add("1");
        vector.add("2");
        boolean result = vector.add("3");

        assertTrue("Should return true", result);
        assertEquals("Size should be 3 after adding elements", 3, vector.size());
        assertTrue("Capacity should grow to accommodate elements", vector.capacity() >= 3);
        assertEquals("First element should be '1'", "1", vector.get(0));
        assertEquals("Second element should be '2'", "2", vector.get(1));
        assertEquals("Third element should be '3'", "3", vector.get(2));
    }

    @Test
    public void testAddMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        assertEquals("Size should be 3 after adding elements", 3, vector.size());
        assertEquals("First element should be string", "1", vector.get(0));
        assertEquals("Second element should be integer", 2, vector.get(1));
        assertEquals("Third element should be double", 3.0, vector.get(2));
    }

    @Test
    public void testAddDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");

        assertEquals("Size should be 3 after adding duplicate elements", 3, vector.size());
        assertEquals("All elements should be '1'", "1", vector.get(0));
        assertEquals("All elements should be '1'", "1", vector.get(1));
        assertEquals("All elements should be '1'", "1", vector.get(2));
    }

    @Test
    public void testAddLargeNumberOfElements()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
        {
            boolean result = vector.add("" + i);
            assertTrue("Should return true for each add", result);
        }

        assertEquals("Size should be 1000 after adding elements", 1000, vector.size());
        assertEquals("First element should be '0'", "0", vector.get(0));
        assertEquals("Last element should be '999'", "999", vector.get(999));
        assertTrue("Capacity should accommodate large number of elements", vector.capacity() >= 1000);
    }

    @Test
    public void testAddAfterRemoveOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove("2");
        boolean result = vector.add("4");

        assertTrue("Should return true", result);
        assertEquals("Size should be 3 after operations", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be '3'", "3", vector.get(1));
        assertEquals("New element should be added at end", "4", vector.get(2));
    }

    @Test
    public void testAddAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.clear();
        boolean result = vector.add("3");

        assertTrue("Should return true", result);
        assertEquals("Size should be 1 after clear and add", 1, vector.size());
        assertEquals("Element should be the new one", "3", vector.get(0));
    }

    @Test
    public void testRemoveExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        boolean result = vector.remove("2");

        assertTrue("Should return true for existing element", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        boolean result = vector.remove("4");

        assertFalse("Should return false for non-existing element", result);
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testRemoveNullElementWhenPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        boolean result = vector.remove(null);

        assertTrue("Should return true for null element", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveNullElementWhenAbsent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        boolean result = vector.remove(null);

        assertFalse("Should return false for null when not present", result);
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testRemoveFromEmptyVector()
    {
        vector = new VectorAdapter();

        boolean result = vector.remove("1");

        assertFalse("Should return false for empty vector", result);
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    @Test
    public void testRemoveFirstOccurrenceOnly()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("1");
        vector.add("3");

        boolean result = vector.remove("1");

        assertTrue("Should return true for existing element", result);
        assertEquals("Size should be decreased", 3, vector.size());
        assertEquals("First occurrence should be removed", "2", vector.get(0));
        assertEquals("Second occurrence should remain", "1", vector.get(1));
        assertEquals("Third element should be shifted", "3", vector.get(2));
    }

    @Test
    public void testRemoveAllOccurrences()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");

        boolean firstResult = vector.remove("1");
        boolean secondResult = vector.remove("1");
        boolean thirdResult = vector.remove("1");

        assertTrue("First removal should return true", firstResult);
        assertTrue("Second removal should return true", secondResult);
        assertTrue("Third removal should return true", thirdResult);
        assertEquals("Size should be 0 after all removals", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAfterAddOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        boolean result = vector.remove("2");
        vector.add("3");

        assertTrue("Should return true for existing element", result);
        assertEquals("Size should be 2 after operations", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("New element should be added", "3", vector.get(1));
    }

    @Test
    public void testRemoveAfterSetOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");
        boolean result = vector.remove("4");

        assertTrue("Should return true for modified element", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Third element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        boolean result = vector.remove("500");

        assertTrue("Should return true for existing element", result);
        assertEquals("Size should be decreased", 999, vector.size());
        assertEquals("Element before removed should remain", "499", vector.get(499));
        assertEquals("Element after removed should be shifted", "501", vector.get(500));
    }

    @Test
    public void testRemoveWithObjectIdentity()
    {
        String str1 = new String("test");
        String str2 = new String("test");

        vector = new VectorAdapter();
        vector.add(str1);

        boolean result = vector.remove(str2);

        assertTrue("Should remove equal string", result);
        assertEquals("Size should be decreased", 0, vector.size());
    }

    @Test
    public void testRemoveMultipleCallsSameElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        boolean firstCall = vector.remove("2");
        boolean secondCall = vector.remove("2");

        assertTrue("First call should return true", firstCall);
        assertFalse("Second call should return false", secondCall);
        assertEquals("Size should be 2 after first removal", 2, vector.size());
    }

    @Test
    public void testRemoveThenAddSameElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        boolean removeResult = vector.remove("2");
        vector.add("2");

        assertTrue("Should return true for removal", removeResult);
        assertEquals("Size should be 2 after operations", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Element should be re-added", "2", vector.get(1));
    }

    @Test
    public void testRemoveWithMixedNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("2");
        vector.add(null);
        vector.add("3");

        boolean firstNullResult = vector.remove(null);
        boolean secondNullResult = vector.remove(null);

        assertTrue("First null removal should return true", firstNullResult);
        assertTrue("Second null removal should return true", secondNullResult);
        assertEquals("Size should be 3 after null removals", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Third element should be shifted", "2", vector.get(1));
        assertEquals("Fifth element should be shifted", "3", vector.get(2));
    }

    @Test
    public void testAddAtIndexValidPosition()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        vector.add(1, "2");

        assertEquals("Size should be 3 after insertion", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Inserted element should be at index 1", "2", vector.get(1));
        assertEquals("Third element should be shifted right", "3", vector.get(2));
    }

    @Test
    public void testAddAtIndexBeginning()
    {
        vector = new VectorAdapter();
        vector.add("2");
        vector.add("3");

        vector.add(0, "1");

        assertEquals("Size should be 3 after insertion", 3, vector.size());
        assertEquals("Inserted element should be at beginning", "1", vector.get(0));
        assertEquals("First element should be shifted right", "2", vector.get(1));
        assertEquals("Second element should be shifted right", "3", vector.get(2));
    }

    @Test
    public void testAddAtIndexEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.add(2, "3");

        assertEquals("Size should be 3 after insertion", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
        assertEquals("Inserted element should be at end", "3", vector.get(2));
    }

    @Test
    public void testAddAtIndexEmptyVector()
    {
        vector = new VectorAdapter();

        vector.add(0, "1");

        assertEquals("Size should be 1 after insertion", 1, vector.size());
        assertEquals("Inserted element should be at index 0", "1", vector.get(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.add(-1, "3");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.add(3, "3");
    }

    @Test
    public void testAddAtIndexWithNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        vector.add(1, null);

        assertEquals("Size should be 3 after insertion", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertNull("Inserted element should be null", vector.get(1));
        assertEquals("Third element should be shifted right", "3", vector.get(2));
    }

    @Test
    public void testAddAtIndexWithDifferentTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        vector.add(1, 2);

        assertEquals("Size should be 3 after insertion", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Inserted element should be integer", 2, vector.get(1));
        assertEquals("Third element should be shifted right", "3", vector.get(2));
    }

    @Test
    public void testAddAtIndexMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("4");

        vector.add(1, "2");
        vector.add(2, "3");

        assertEquals("Size should be 4 after insertions", 4, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("First inserted element should be at index 1", "2", vector.get(1));
        assertEquals("Second inserted element should be at index 2", "3", vector.get(2));
        assertEquals("Original element should be shifted right", "4", vector.get(3));
    }

    @Test
    public void testAddAtIndexCapacityGrowth()
    {
        vector = new VectorAdapter(2);
        vector.add("1");
        vector.add("2");

        vector.add(1, "3");

        assertEquals("Size should be 3 after insertion", 3, vector.size());
        assertTrue("Capacity should grow to accommodate", vector.capacity() >= 3);
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Inserted element should be at index 1", "3", vector.get(1));
        assertEquals("Second element should be shifted right", "2", vector.get(2));
    }

    @Test
    public void testAddAtIndexAfterRemoveOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        vector.remove(1);
        vector.remove(1);

        vector.add(1, "5");

        assertEquals("Size should be 3 after operations", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Inserted element should be at index 1", "5", vector.get(1));
        assertEquals("Last element should remain unchanged", "4", vector.get(2));
    }

    @Test
    public void testAddAtIndexLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        vector.add(500, "inserted");

        assertEquals("Size should be 1001 after insertion", 1001, vector.size());
        assertEquals("Element before insertion point should remain", "499", vector.get(499));
        assertEquals("Inserted element should be at index 500", "inserted", vector.get(500));
        assertEquals("Element after insertion point should be shifted", "500", vector.get(501));
        assertEquals("Last element should be shifted", "999", vector.get(1000));
    }

    @Test
    public void testRemoveAtIndexValidPosition()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result = vector.remove(1);

        assertEquals("Should return removed element", "2", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should be shifted left", "3", vector.get(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.remove(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.remove(2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.remove(5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveAtIndexOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.remove(0);
    }

    @Test
    public void testRemoveAtIndexFirstElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result = vector.remove(0);

        assertEquals("Should return first element", "1", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("Second element should become first", "2", vector.get(0));
        assertEquals("Third element should become second", "3", vector.get(1));
    }

    @Test
    public void testRemoveAtIndexLastElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object result = vector.remove(2);

        assertEquals("Should return last element", "3", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
    }

    @Test
    public void testRemoveAtIndexWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        Object result = vector.remove(1);

        assertNull("Should return null element", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should be shifted left", "3", vector.get(1));
    }

    @Test
    public void testRemoveAtIndexMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        vector.remove(1);
        vector.remove(1);
        vector.remove(1);

        assertEquals("Size should be decreased to 2", 2, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Last element should be shifted left", "5", vector.get(1));
    }

    @Test
    public void testRemoveAtIndexAllElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove(0);
        vector.remove(0);
        vector.remove(0);

        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAtIndexAfterAddOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.add(1, "inserted");
        Object result = vector.remove(2);

        assertEquals("Should return original second element", "2", result);
        assertEquals("Size should be 3 after operations", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Inserted element should remain", "inserted", vector.get(1));
        assertEquals("Third element should be shifted left", "3", vector.get(2));
    }

    @Test
    public void testRemoveAtIndexAfterSetOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "modified");
        Object result = vector.remove(1);

        assertEquals("Should return modified element", "modified", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should be shifted left", "3", vector.get(1));
    }

    @Test
    public void testRemoveAtIndexLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        Object result = vector.remove(500);

        assertEquals("Should return element at index 500", "500", result);
        assertEquals("Size should be decreased", 999, vector.size());
        assertEquals("Element before removal should remain", "499", vector.get(499));
        assertEquals("Element after removal should be shifted left", "501", vector.get(500));
        assertEquals("Last element should be shifted left", "999", vector.get(998));
    }

    @Test
    public void testRemoveAtIndexWithMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        Object result = vector.remove(1);

        assertEquals("Should return integer element", 2, result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should be shifted left", 3.0, vector.get(1));
    }

    @Test
    public void testRemoveAtIndexThenAddSameElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object removed = vector.remove(1);
        vector.add(1, removed);

        assertEquals("Size should be 3 after operations", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Removed element should be reinserted", "2", vector.get(1));
        assertEquals("Third element should be shifted back", "3", vector.get(2));
    }

    @Test
    public void testRemoveAtIndexWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        Object result = vector.remove(1);

        assertEquals("Should return first occurrence of duplicate", "2", result);
        assertEquals("Size should be decreased", 3, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Second occurrence should be shifted left", "2", vector.get(1));
        assertEquals("Third element should be shifted left", "3", vector.get(2));
    }

    @Test
    public void testClearEmptyVector()
    {
        vector = new VectorAdapter();
        vector.clear();

        assertEquals("Size should be 0 after clearing empty vector", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    @Test
    public void testClearSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.clear();

        assertEquals("Size should be 0 after clearing", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.clear();

        assertEquals("Size should be 0 after clearing", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add("2");
        vector.add(null);
        vector.clear();

        assertEquals("Size should be 0 after clearing", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        vector.clear();

        assertEquals("Size should be 0 after clearing", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        vector.clear();

        assertEquals("Size should be 0 after clearing", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        vector.clear();

        assertEquals("Size should be 0 after clearing large vector", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearAfterAddOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.clear();
        vector.add("3");

        assertEquals("Size should be 1 after adding new element", 1, vector.size());
        assertEquals("New element should be added", "3", vector.get(0));
    }

    @Test
    public void testClearAfterSetOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.set(1, "4");
        vector.clear();

        assertEquals("Size should be 0 after clearing", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearAfterRemoveOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove(1);
        vector.clear();

        assertEquals("Size should be 0 after clearing", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearCapacityRemains()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        int capacityBefore = vector.capacity();
        vector.clear();

        assertEquals("Capacity should remain unchanged", capacityBefore, vector.capacity());
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testClearMultipleCalls()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.clear();
        vector.clear();

        assertEquals("Size should remain 0 after second clear", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    @Test
    public void testContainsAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter emptyCollection = new VectorAdapter();
        boolean result = vector.containsAll(emptyCollection);

        assertTrue("Should return true for empty collection", result);
    }

    @Test
    public void testContainsAllAllElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");

        boolean result = vector.containsAll(collection);

        assertTrue("Should return true when all elements are present", result);
    }

    @Test
    public void testContainsAllSomeElementsMissing()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("4");

        boolean result = vector.containsAll(collection);

        assertFalse("Should return false when some elements are missing", result);
    }

    @Test
    public void testContainsAllAllElementsMissing()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("4");
        collection.add("5");

        boolean result = vector.containsAll(collection);

        assertFalse("Should return false when all elements are missing", result);
    }

    @Test
    public void testContainsAllDuplicateElementsInCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("1");

        boolean result = vector.containsAll(collection);

        assertTrue("Should return true for duplicate elements in collection", result);
    }

    @Test
    public void testContainsAllDuplicateElementsInVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("2");

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");

        boolean result = vector.containsAll(collection);

        assertTrue("Should return true when vector has duplicates", result);
    }

    @Test
    public void testContainsAllNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add(null);
        collection.add("1");

        boolean result = vector.containsAll(collection);

        assertTrue("Should return true for null elements", result);
    }

    @Test
    public void testContainsAllNullElementMissing()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add(null);

        boolean result = vector.containsAll(collection);

        assertFalse("Should return false when null element is missing", result);
    }

    @Test
    public void testContainsAllMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add(2);

        boolean result = vector.containsAll(collection);

        assertTrue("Should return true for mixed element types", result);
    }

    @Test
    public void testContainsAllEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");

        boolean result = vector.containsAll(collection);

        assertFalse("Should return false for empty vector", result);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.containsAll(null);
    }

    @Test
    public void testContainsAllSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        boolean result = vector.containsAll(vector);

        assertTrue("Should return true when collection is itself", result);
    }

    @Test
    public void testContainsAllLargeCollection()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        VectorAdapter collection = new VectorAdapter();

        for(int i = 0; i < 500; i++)
            collection.add("" + i);

        boolean result = vector.containsAll(collection);

        assertTrue("Should return true for large collection", result);
    }

    @Test
    public void testContainsAllAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");

        boolean resultBefore = vector.containsAll(collection);

        vector.remove("2");
        boolean resultAfter = vector.containsAll(collection);

        assertTrue("Should return true before modification", resultBefore);
        assertFalse("Should return false after modification", resultAfter);
    }

    @Test
    public void testAddAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter emptyCollection = new VectorAdapter();
        boolean result = vector.addAll(emptyCollection);

        assertFalse("Should return false for empty collection", result);
        assertEquals("Size should remain unchanged", 2, vector.size());
    }

    @Test
    public void testAddAllNonEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter collection = new VectorAdapter();
        collection.add("3");
        collection.add("4");

        boolean result = vector.addAll(collection);

        assertTrue("Should return true for non-empty collection", result);
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
        assertEquals("Fourth element should be added", "4", vector.get(3));
    }

    @Test
    public void testAddAllToEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");
        collection.add("3");

        boolean result = vector.addAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should match collection size", 3, vector.size());
        assertEquals("First element should be added", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
    }

    @Test
    public void testAddAllWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");

        VectorAdapter collection = new VectorAdapter();
        collection.add(null);
        collection.add("3");

        boolean result = vector.addAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertNull("Second element should be null", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
    }

    @Test
    public void testAddAllMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");

        VectorAdapter collection = new VectorAdapter();
        collection.add(2);
        collection.add(3.0);

        boolean result = vector.addAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be integer", 2, vector.get(1));
        assertEquals("Third element should be double", 3.0, vector.get(2));
    }

    @Test
    public void testAddAllDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");

        VectorAdapter collection = new VectorAdapter();
        collection.add("2");
        collection.add("2");

        boolean result = vector.addAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be added", "2", vector.get(2));
    }

    @Test
    public void testAddAllLargeCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");

        VectorAdapter collection = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            collection.add("" + (i + 2));

        boolean result = vector.addAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 1001, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));

        for(int i = 0; i < 1000; i++)
            assertEquals("Element " + (i + 2) + " should be added", "" + (i + 2), vector.get(i + 1));
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.addAll(null);
    }

    @Test
    public void testAddAllSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        boolean result = vector.addAll(vector);

        assertTrue("Should return true when adding itself", result);
        assertEquals("Size should be doubled", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("First element should be added again", "1", vector.get(2));
        assertEquals("Second element should be added again", "2", vector.get(3));
    }

    @Test
    public void testAddAllAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.clear();

        VectorAdapter collection = new VectorAdapter();
        collection.add("3");
        collection.add("4");

        boolean result = vector.addAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should match collection size", 2, vector.size());
        assertEquals("First element should be added", "3", vector.get(0));
        assertEquals("Second element should be added", "4", vector.get(1));
    }

    @Test
    public void testAddAllCapacityGrowth()
    {
        vector = new VectorAdapter(2);
        vector.add("1");
        vector.add("2");

        VectorAdapter collection = new VectorAdapter();
        collection.add("3");
        collection.add("4");
        collection.add("5");

        boolean result = vector.addAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 5, vector.size());
        assertTrue("Capacity should grow to accommodate", vector.capacity() >= 5);
    }

    @Test
    public void testRemoveAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter emptyCollection = new VectorAdapter();
        boolean result = vector.removeAll(emptyCollection);

        assertFalse("Should return false for empty collection", result);
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testRemoveAllSomeElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        VectorAdapter collection = new VectorAdapter();
        collection.add("2");
        collection.add("3");

        boolean result = vector.removeAll(collection);

        assertTrue("Should return true when elements are removed", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Fourth element should remain", "4", vector.get(1));
    }

    @Test
    public void testRemoveAllAllElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");
        collection.add("3");

        boolean result = vector.removeAll(collection);

        assertTrue("Should return true when all elements are removed", result);
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllNoElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("4");
        collection.add("5");

        boolean result = vector.removeAll(collection);

        assertFalse("Should return false when no elements are present", result);
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testRemoveAllDuplicateElementsInCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("2");

        VectorAdapter collection = new VectorAdapter();
        collection.add("2");
        collection.add("2");

        boolean result = vector.removeAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Third element should remain", "3", vector.get(1));
    }

    @Test
    public void testRemoveAllDuplicateElementsInVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("2");

        boolean result = vector.removeAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Fourth element should remain", "3", vector.get(1));
    }

    @Test
    public void testRemoveAllNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);

        VectorAdapter collection = new VectorAdapter();
        collection.add(null);

        boolean result = vector.removeAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Third element should remain", "3", vector.get(1));
    }

    @Test
    public void testRemoveAllMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        vector.add("4");

        VectorAdapter collection = new VectorAdapter();
        collection.add(2);
        collection.add(3.0);

        boolean result = vector.removeAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Fourth element should remain", "4", vector.get(1));
    }

    @Test
    public void testRemoveAllEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");

        boolean result = vector.removeAll(collection);

        assertFalse("Should return false for empty vector", result);
        assertEquals("Size should remain 0", 0, vector.size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.removeAll(null);
    }

    @Test
    public void testRemoveAllSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        boolean result = vector.removeAll(vector);

        assertTrue("Should return true when removing itself", result);
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllLargeCollection()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        VectorAdapter collection = new VectorAdapter();

        for(int i = 500; i < 1000; i++)
            collection.add("" + i);

        boolean result = vector.removeAll(collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be decreased", 500, vector.size());

        for(int i = 0; i < 500; i++)
            assertEquals("Element " + i + " should remain", "" + i, vector.get(i));
    }

    @Test
    public void testRemoveAllAfterAddOperations()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("2");

        boolean resultBefore = vector.removeAll(collection);
        vector.add("2");
        boolean resultAfter = vector.removeAll(collection);

        assertTrue("Should return true before add", resultBefore);
        assertTrue("Should return true after add", resultAfter);
        assertEquals("Size should be 2 after operations", 2, vector.size());
    }

    @Test
    public void testRetainAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter emptyCollection = new VectorAdapter();
        boolean result = vector.retainAll(emptyCollection);

        assertTrue("Should return true when retaining empty collection", result);
        assertEquals("Size should be 0 after retaining empty collection", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRetainAllNoElementsInCommon()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("4");
        collection.add("5");

        boolean result = vector.retainAll(collection);

        assertTrue("Should return true when no elements in common", result);
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRetainAllAllElementsInCommon()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");
        collection.add("3");

        boolean result = vector.retainAll(collection);

        assertFalse("Should return false when all elements are retained", result);
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testRetainAllSomeElementsInCommon()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        VectorAdapter collection = new VectorAdapter();
        collection.add("2");
        collection.add("3");

        boolean result = vector.retainAll(collection);

        assertTrue("Should return true when some elements are retained", result);
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First retained element should be '2'", "2", vector.get(0));
        assertEquals("Second retained element should be '3'", "3", vector.get(1));
    }

    @Test
    public void testRetainAllWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add("2");

        boolean result = vector.retainAll(collection);

        assertTrue("Should return true when retaining duplicates", result);
        assertEquals("Size should be 2", 2, vector.size());
        assertEquals("First element should be '2'", "2", vector.get(0));
        assertEquals("Second element should be '2'", "2", vector.get(1));
    }

    @Test
    public void testRetainAllWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add(null);

        boolean result = vector.retainAll(collection);

        assertTrue("Should return true when retaining null elements", result);
        assertEquals("Size should be 1", 1, vector.size());
        assertNull("Only null element should remain", vector.get(0));
    }

    @Test
    public void testRetainAllEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");

        boolean result = vector.retainAll(collection);

        assertFalse("Should return false for empty vector", result);
        assertEquals("Size should remain 0", 0, vector.size());
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.retainAll(null);
    }

    @Test
    public void testRetainAllSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        boolean result = vector.retainAll(vector);

        assertFalse("Should return false when retaining itself", result);
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testRetainAllLargeCollection()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        VectorAdapter collection = new VectorAdapter();

        for(int i = 500; i < 1000; i++)
            collection.add("" + i);

        boolean result = vector.retainAll(collection);

        assertTrue("Should return true for large collection", result);
        assertEquals("Size should be 500", 500, vector.size());

        for(int i = 0; i < 500; i++)
            assertEquals("Element " + (i + 500) + " should be retained", "" + (i + 500), vector.get(i));
    }

    @Test
    public void testAddAllAtIndexEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter emptyCollection = new VectorAdapter();
        boolean result = vector.addAll(1, emptyCollection);

        assertFalse("Should return false for empty collection", result);
        assertEquals("Size should remain unchanged", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
    }

    @Test
    public void testAddAllAtIndexNonEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("4");

        VectorAdapter collection = new VectorAdapter();
        collection.add("2");
        collection.add("3");

        boolean result = vector.addAll(1, collection);

        assertTrue("Should return true for non-empty collection", result);
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
        assertEquals("Fourth element should be shifted", "4", vector.get(3));
    }

    @Test
    public void testAddAllAtIndexBeginning()
    {
        vector = new VectorAdapter();
        vector.add("3");
        vector.add("4");

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");

        boolean result = vector.addAll(0, collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should be added", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be shifted", "3", vector.get(2));
        assertEquals("Fourth element should be shifted", "4", vector.get(3));
    }

    @Test
    public void testAddAllAtIndexEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter collection = new VectorAdapter();
        collection.add("3");
        collection.add("4");

        boolean result = vector.addAll(2, collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
        assertEquals("Fourth element should be added", "4", vector.get(3));
    }

    @Test
    public void testAddAllAtIndexEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter collection = new VectorAdapter();
        collection.add("1");
        collection.add("2");
        collection.add("3");

        boolean result = vector.addAll(0, collection);

        assertTrue("Should return true", result);
        assertEquals("Size should match collection size", 3, vector.size());
        assertEquals("First element should be added", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddAllAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter collection = new VectorAdapter();
        collection.add("3");

        vector.addAll(-1, collection);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddAllAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter collection = new VectorAdapter();
        collection.add("3");

        vector.addAll(3, collection);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllAtIndexNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.addAll(1, null);
    }

    @Test
    public void testAddAllAtIndexWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        VectorAdapter collection = new VectorAdapter();
        collection.add(null);
        collection.add("2");

        boolean result = vector.addAll(1, collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertNull("Second element should be null", vector.get(1));
        assertEquals("Third element should be added", "2", vector.get(2));
        assertEquals("Fourth element should be shifted", "3", vector.get(3));
    }

    @Test
    public void testAddAllAtIndexSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        System.out.println(vector);
        boolean result = vector.addAll(1, vector);

        System.out.println(vector);

        assertTrue("Should return true when adding itself", result);
        assertEquals("Size should be doubled", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "1", vector.get(1));
        assertEquals("Third element should be added", "2", vector.get(2));
        assertEquals("Fourth element should be shifted", "2", vector.get(3));
    }

    @Test
    public void testAddAllAtIndexLargeCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("5");

        VectorAdapter collection = new VectorAdapter();

        for(int i = 2; i < 5; i++)
            collection.add("" + i);

        boolean result = vector.addAll(1, collection);

        assertTrue("Should return true", result);
        assertEquals("Size should be increased", 5, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
        assertEquals("Fourth element should be added", "4", vector.get(3));
        assertEquals("Fifth element should be shifted", "5", vector.get(4));
    }

    @Test
    public void testEqualsSameObject()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        boolean result = vector.equals(vector);

        assertTrue("Should return true for same object", result);
    }

    @Test
    public void testEqualsEqualLists()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add("2");
        other.add("3");

        boolean result = vector.equals(other);

        assertTrue("Should return true for equal lists", result);
    }

    @Test
    public void testEqualsDifferentSizes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add("2");

        boolean result = vector.equals(other);

        assertFalse("Should return false for different sizes", result);
    }

    @Test
    public void testEqualsDifferentElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add("4");
        other.add("3");

        boolean result = vector.equals(other);

        assertFalse("Should return false for different elements", result);
    }

    @Test
    public void testEqualsDifferentOrder()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add("3");
        other.add("2");

        boolean result = vector.equals(other);

        assertFalse("Should return false for different order", result);
    }

    @Test
    public void testEqualsWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add(null);
        other.add("3");

        boolean result = vector.equals(other);

        assertTrue("Should return true for lists with null elements", result);
    }

    @Test
    public void testEqualsDifferentNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add("2");
        other.add("3");

        boolean result = vector.equals(other);

        assertFalse("Should return false for different null elements", result);
    }

    @Test
    public void testEqualsEmptyLists()
    {
        vector = new VectorAdapter();
        VectorAdapter other = new VectorAdapter();

        boolean result = vector.equals(other);

        assertTrue("Should return true for empty lists", result);
    }

    @Test
    public void testEqualsNonListObject()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        String notAList = "not a list";

        boolean result = vector.equals(notAList);

        assertFalse("Should return false for non-list object", result);
    }

    @Test
    public void testEqualsMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add(2);
        other.add(3.0);

        boolean result = vector.equals(other);

        assertTrue("Should return true for mixed element types", result);
    }

    @Test
    public void testEqualsDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("2");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add("1");
        other.add("2");

        boolean result = vector.equals(other);

        assertTrue("Should return true for duplicate elements", result);
    }

    @Test
    public void testEqualsLargeLists()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        VectorAdapter other = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            other.add("" + i);

        boolean result = vector.equals(other);

        assertTrue("Should return true for large equal lists", result);
    }

    @Test
    public void testEqualsAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add("2");
        other.add("3");

        boolean resultBefore = vector.equals(other);

        vector.set(1, "4");
        boolean resultAfter = vector.equals(other);

        assertTrue("Should return true before modification", resultBefore);
        assertFalse("Should return false after modification", resultAfter);
    }

    @Test
    public void testHashCodeEmptyVector()
    {
        vector = new VectorAdapter();
        int hashCode = vector.hashCode();

        assertEquals("Hash code of empty vector should match empty vector", new VectorAdapter().hashCode(), hashCode);
    }

    @Test
    public void testHashCodeSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        int hashCode = vector.hashCode();

        VectorAdapter expected = new VectorAdapter();
        expected.add("1");

        assertEquals("Hash code should match for single element", expected.hashCode(), hashCode);
    }

    @Test
    public void testHashCodeMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        int hashCode = vector.hashCode();

        VectorAdapter expected = new VectorAdapter();
        expected.add("1");
        expected.add("2");
        expected.add("3");

        assertEquals("Hash code should match for multiple elements", expected.hashCode(), hashCode);
    }

    @Test
    public void testHashCodeWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        int hashCode = vector.hashCode();

        VectorAdapter expected = new VectorAdapter();
        expected.add("1");
        expected.add(null);
        expected.add("3");

        assertEquals("Hash code should match for null elements", expected.hashCode(), hashCode);
    }

    @Test
    public void testHashCodeMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        int hashCode = vector.hashCode();

        VectorAdapter expected = new VectorAdapter();
        expected.add("1");
        expected.add(2);
        expected.add(3.0);

        assertEquals("Hash code should match for mixed element types", expected.hashCode(), hashCode);
    }

    @Test
    public void testHashCodeDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        int hashCode = vector.hashCode();

        VectorAdapter expected = new VectorAdapter();
        expected.add("1");
        expected.add("1");
        expected.add("1");

        assertEquals("Hash code should match for duplicate elements", expected.hashCode(), hashCode);
    }

    @Test
    public void testHashCodeLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            vector.add("" + i);

        int hashCode = vector.hashCode();

        VectorAdapter expected = new VectorAdapter();

        for(int i = 0; i < 1000; i++)
            expected.add("" + i);

        assertEquals("Hash code should match for large vector", expected.hashCode(), hashCode);
    }

    @Test
    public void testHashCodeAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        int hashCodeBefore = vector.hashCode();

        vector.set(1, "4");
        vector.remove(2);
        vector.add("5");

        int hashCodeAfter = vector.hashCode();

        VectorAdapter expected = new VectorAdapter();
        expected.add("1");
        expected.add("4");
        expected.add("5");

        assertEquals("Hash code should match after modifications", expected.hashCode(), hashCodeAfter);
        assertNotEquals("Hash code should change after modifications", hashCodeBefore, hashCodeAfter);
    }

    @Test
    public void testHashCodeConsistency()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        int firstHash = vector.hashCode();
        int secondHash = vector.hashCode();

        assertEquals("Hash code should be consistent across calls", firstHash, secondHash);
    }

    @Test
    public void testHashCodeEqualVectorsHaveEqualHashCodes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter other = new VectorAdapter();
        other.add("1");
        other.add("2");
        other.add("3");

        assertTrue("Equal vectors should have equal hash codes", vector.equals(other) && vector.hashCode() == other.hashCode());
    }

    @Test
    public void testToStringEmptyVector()
    {
        vector = new VectorAdapter();
        String result = vector.toString();

        assertEquals("String representation of empty vector should match", "[]", result);
    }

    @Test
    public void testToStringSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        String result = vector.toString();

        assertEquals("String representation of single element should match", "[1]", result);
    }

    @Test
    public void testToStringMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        String result = vector.toString();

        assertEquals("String representation of multiple elements should match", "[1, 2, 3]", result);
    }

    @Test
    public void testToStringWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        String result = vector.toString();

        assertEquals("String representation with null elements should match", "[1, null, 3]", result);
    }

    @Test
    public void testToStringMixedElementTypes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(2);
        vector.add(3.0);
        String result = vector.toString();

        assertEquals("String representation with mixed types should match", "[1, 2, 3.0]", result);
    }

    @Test
    public void testToStringDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");
        String result = vector.toString();

        assertEquals("String representation with duplicate elements should match", "[1, 1, 1]", result);
    }

    @Test
    public void testToStringLargeVector()
    {
        vector = new VectorAdapter();

        for(int i = 0; i < 5; i++)
            vector.add("" + i);

        String result = vector.toString();

        assertEquals("String representation of large vector should match", "[0, 1, 2, 3, 4]", result);
    }

    @Test
    public void testToStringAfterModifications()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        String before = vector.toString();

        vector.set(1, "4");
        vector.remove(2);
        vector.add("5");

        String after = vector.toString();

        assertEquals("String representation should change after modifications", "[1, 4, 5]", after);
        assertNotEquals("String representation should not match previous state", before, after);
    }

    @Test
    public void testToStringConsistency()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        String firstString = vector.toString();
        String secondString = vector.toString();

        assertEquals("String representation should be consistent across calls", firstString, secondString);
    }

    @Test
    public void testToStringFormat()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        String result = vector.toString();

        assertTrue("String should start with '['", result.startsWith("["));
        assertTrue("String should end with ']'", result.endsWith("]"));
        assertTrue("String should contain commas between elements", result.contains(", "));
    }
}
