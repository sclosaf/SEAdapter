package myTest;

import myAdapter.VectorAdapter;
import myAdapter.HCollection;
import myAdapter.HEnumeration;
import myAdapter.HIterator;
import myAdapter.HListIterator;

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
}
