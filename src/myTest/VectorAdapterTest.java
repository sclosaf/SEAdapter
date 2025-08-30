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
    public void testConstructorWithCapacityAndIncrementGrowth()
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
    public void testConstructorWithCapacityDoubleGrowth()
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
    public void testConstructorWithCollectionModificationIndependence()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("0");
        vec.add("1");
        vec.add("2");

        vector = new VectorAdapter(vec);

        vec.add("3");
        vec.add("4");
        vec.remove("0");
        vec.set(0, "5");

        assertEquals("Should remain unchanged", 3, vector.size());
        assertEquals("Single element should remain unchanged", "0", vector.get(0));
        assertFalse("Should not contain the other elements", vector.contains(vec.get(0)));
        assertTrue("Should contain the only unchanged element", vector.contains(vec.get(1)));
        assertFalse("Should not contain the other elements", vector.contains(vec.get(2)));
        assertFalse("Should not contain the other elements", vector.contains(vec.get(3)));
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

    @Test(expected = NullPointerException.class)
    public void testCopyIntoNullArray()
    {
        vector = new VectorAdapter();
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
    public void testTrimToSizeConstructor()
    {
        vector = new VectorAdapter(20, 50);

        vector.trimToSize();

        assertTrue("Should still be empty", vector.isEmpty());
        assertEquals("Capacity should equal to 0 after trimming", 0, vector.capacity());
    }

    @Test
    public void testTrimToSizeWithSingleElement()
    {
        vector = new VectorAdapter(10);
        vector.add("1");

        vector.trimToSize();

        assertEquals("Capacity should equal size after trimming", 1, vector.capacity());
        assertEquals("Size should remain unchanged", 1, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
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
    public void testEnsureCapacityConstructor()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(5);

        assertEquals("Capacity should remain unchanged when minCapacity is smaller", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.trimToSize();
        vector.ensureCapacity(15);

        assertEquals("Capacity should increase after trimming", 15, vector.capacity());
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
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
    public void testEnsureCapacityCapacityIncrementInsufficient()
    {
        vector = new VectorAdapter(5, 3);
        vector.ensureCapacity(10);

        assertEquals("Capacity should increase using minCapacity", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityWithDoubleCapacityIncrement()
    {
        vector = new VectorAdapter(5);
        vector.ensureCapacity(9);

        assertEquals("Capacity should double when capacity increment is zero", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityDoubleGrowthInsufficient()
    {
        vector = new VectorAdapter(5);
        vector.ensureCapacity(20);

        assertEquals("Capacity should be set to minCapacity when double growth is insufficient", 20, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
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
    public void testSizeWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertEquals("Size should count null elements", 3, vector.size());
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
        vector.add("0");
        vector.add("1");
        vector.add("1");

        assertEquals("Should return first occurrence index for duplicate elements", 1, vector.indexOf("1"));
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
    public void testIndexOfWithIndexWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("1");
        vector.add("2");
        vector.add("1");

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
    public void testIndexOfWithIndexEqualOrGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return -1 when index equals size", -1, vector.indexOf("1", 3));
        assertEquals("Should return -1 when index equals size", -1, vector.indexOf("2", 5));
        assertEquals("Should return -1 when index equals size", -1, vector.indexOf("3", 10));
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

        assertEquals("Should find element at exact index", 2, vector.indexOf("3", 2));
        assertEquals("Should find element at exact index", 3, vector.indexOf("4", 3));
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
        vector.add("0");

        assertEquals("Should return last occurrence index for duplicate elements", 1, vector.lastIndexOf("1"));
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
        vector.add("0");
        vector.add("1");
        vector.add("1");
        vector.add("2");
        vector.add("1");

        assertEquals("Should find last occurrence ending at index 4", 4, vector.lastIndexOf("1", 4));
        assertEquals("Should find third occurrence ending at index 3", 2, vector.lastIndexOf("1", 3));
        assertEquals("Should find second occurrence ending at index 2", 2, vector.lastIndexOf("1", 2));
        assertEquals("Should find first occurrence ending at index 1", 1, vector.lastIndexOf("1", 1));
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
    public void testLastIndexOfWithIndexNullOrNegativeIndex()
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
    public void testElementAtWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertNull("Should return null element at index 0", vector.elementAt(0));
        assertNull("Should return null element at index 2", vector.elementAt(2));
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
    public void testFirstElementWithNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
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
    public void testLastElementWithNullElement()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add(null);
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
    public void testFirstAndLastElementSameForSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");

        assertEquals("First and last element should be equal for single element", vector.firstElement(), vector.lastElement());
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
    public void testInsertElementAtEmptyVector()
    {
        vector = new VectorAdapter();

        vector.insertElementAt("1", 0);

        assertEquals("Size should be 1", 1, vector.size());
        assertEquals("Inserted element should be at index 0", "1", vector.get(0));
    }

    @Test
    public void testInsertElementAtFirstElement()
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
    public void testInsertElementAtLastElement()
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
    public void testInsertElementAtWithNullElement()
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
    public void testAddElementNullElement()
    {
        vector = new VectorAdapter();
        vector.addElement(null);

        assertEquals("Size should be 1 after adding null", 1, vector.size());
        assertNull("Element should be null", vector.get(0));
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
        vector.add(null);
        vector.add(null);
        vector.add(null);
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
        vector.add(null);
        vector.add(null);
        vector.add(null);
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());
        assertNull("Null element should be preserved", vec.get(0));
        assertNull("Null element should be preserved", vec.get(1));
        assertNull("Null element should be preserved", vec.get(2));
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
    public void testCloneIndependenceFromOriginal()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = (VectorAdapter) vector.clone();
        vector.set(1, "4");
        vec.set(1, "5");

        assertEquals("Clone should not be affected by original modification", "5", vec.get(1));
        assertEquals("Original should be modified", "4", vector.get(1));
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
        vector.add(null);
        vector.add(null);
        vector.add(null);
        Object[] array = vector.toArray();

        assertNotNull("Returned array should not be null", array);
        assertEquals("Array length should match vector size", 3, array.length);
        assertNull("First array element should be null", array[0]);
        assertNull("Second array element should be null", array[1]);
        assertNull("Third array element should null", array[2]);
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
        a[2] = "0";
        a[3] = "0";
        a[4] = "0";

        Object[] result = vector.toArray(a);

        assertSame("Should return the same array", a, result);
        assertEquals("First array element should match", "1", result[0]);
        assertEquals("Second array element should match", "2", result[1]);
        assertNull("Element after vector content should be null", result[2]);
        assertEquals("Existing elements should be preserved", "0", result[3]);
        assertEquals("Existing elements should be preserved", "0", result[4]);
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
        vector.add(null);
        vector.add(null);

        Object[] a = new Object[5];
        Object[] result = vector.toArray(a);

        assertSame("Should return the same array", a, result);
        assertNull("First array element should be null", result[0]);
        assertNull("Second array element should be null", result[1]);
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
        a[0] = "0";
        a[1] = "0";
        a[2] = "0";

        Object[] result = vector.toArray(a);

        assertSame("Should return the same array", a, result);
        assertNull("First element should be null for empty vector", result[0]);
        assertEquals("Second element should remain unchanged", "0", result[1]);
        assertEquals("Third element should remain unchanged", "0", result[2]);
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

        boolean result = vector.addAll(1, vector);

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
