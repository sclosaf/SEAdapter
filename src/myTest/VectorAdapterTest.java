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
        vector.ensureCapacity(8);

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
    public void testEnsureCapacityMultipleCalls()
    {
        vector = new VectorAdapter(5);

        vector.ensureCapacity(10);

        int capacityAfterFirstCall = vector.capacity();

        vector.ensureCapacity(25);

        int capacityAfterSecondCall = vector.capacity();

        vector.ensureCapacity(8);

        assertEquals("Second call should increase capacity further", 25, capacityAfterSecondCall);
        assertEquals("Third call should not reduce capacity", capacityAfterSecondCall, vector.capacity());
    }

    @Test
    public void testEnsureCapacityWithNullElements()
    {
        vector = new VectorAdapter(3);
        vector.add(null);
        vector.add(null);

        vector.ensureCapacity(10);

        assertEquals("Capacity should increase", 10, vector.capacity());
        assertEquals("Size should remain unchanged", 2, vector.size());
        assertNull("First element should remain null", vector.get(0));
        assertNull("Second element should remain null", vector.get(1));
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

        vector.ensureCapacity(10);

        assertEquals("Capacity should increase", 10, vector.capacity());
        assertEquals("Size should remain 2", 2, vector.size());
        assertEquals("First element should remain", "str", vector.get(0));
        assertEquals("Second element should remain", 123, vector.get(1));
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
}
