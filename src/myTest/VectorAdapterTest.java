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
    public void testConstructorWithCapacityAndIncrementGrowthIncrement()
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
    public void testDefaultConstructorDoubleGrowth()
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
    public void testConstructorWithCollectionIndependence()
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

        Object[] arr = new Object[3];
        vector.copyInto(arr);

        assertEquals("First element should be '1'", "1", arr[0]);
        assertEquals("Second element should be '2'", "2", arr[1]);
        assertEquals("Third element should be '3'", "3", arr[2]);
    }

    @Test
    public void testCopyIntoEmptyVector()
    {
        vector = new VectorAdapter();

        Object[] arr = new Object[0];
        vector.copyInto(arr);

        assertEquals("Array should remain empty", 0, arr.length);
    }

    @Test
    public void testCopyIntoLargerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        Object[] arr = new Object[5];
        arr[2] = "0";
        arr[3] = "0";
        arr[4] = "0";

        vector.copyInto(arr);

        assertEquals("First element should be '1'", "1", arr[0]);
        assertEquals("Second element should be '2'", "2", arr[1]);
        assertEquals("Existing elements should be preserved", "0", arr[2]);
        assertEquals("Existing elements should be preserved", "0", arr[3]);
        assertEquals("Existing elements should be preserved", "0", arr[4]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCopyIntoSmallerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object[] arr = new Object[2];
        vector.copyInto(arr);
    }

    @Test
    public void testCopyIntoNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        Object[] arr = new Object[3];
        arr[0] = "0";
        arr[1] = "0";
        arr[2] = "0";

        vector.copyInto(arr);


        assertNull("First element should be null", arr[0]);
        assertNull("Second element should be null", arr[1]);
        assertNull("Third element should be null", arr[2]);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyIntoNullArray()
    {
        vector = new VectorAdapter();
        vector.copyInto(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCopyIntoZeroLengthArrayWithElements()
    {
        vector = new VectorAdapter();
        vector.add("1");

        Object[] arr = new Object[0];
        vector.copyInto(arr);
    }

    @Test
    public void testTrimToSizeOnConstructor()
    {
        vector = new VectorAdapter(20, 50);

        vector.trimToSize();

        assertTrue("Should still be empty", vector.isEmpty());
        assertEquals("Capacity should equal to 0 after trimming", 0, vector.capacity());
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
    public void testTrimToSizeNullElements()
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
    public void testEnsureCapacityExactCurrentCapacity()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(vector.capacity());

        assertEquals("Capacity should remain unchanged when minCapacity equals current capacity", 10, vector.capacity());

        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    @Test
    public void testEnsureCapacityCapacityIncrement()
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
    public void testEnsureCapacityDoubleGrowthIncrement()
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
    public void testEnsureCapacityAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.trimToSize();
        vector.ensureCapacity(8);

        assertEquals("Capacity should increase after trimming", 8, vector.capacity());
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
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
    public void testSetSizeIncreaseExistingElements()
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
    public void testSetSizeDecreaseExistingElements()
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
    public void testSetSizeNullElements()
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
    public void testCapacityAtInitialCapacity()
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
    public void testSizeNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertEquals("Size should count null elements", 3, vector.size());
    }

    @Test
    public void testIsEmptyEmptyVector()
    {
        vector = new VectorAdapter();
        assertTrue("New vector should be empty", vector.isEmpty());
    }

    @Test
    public void testIsEmptyAfterAddingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        assertFalse("Vector should not be empty after adding element", vector.isEmpty());
    }

    @Test
    public void testIsEmptyAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.clear();

        assertTrue("Vector should be empty after clear", vector.isEmpty());
    }

    @Test
    public void testIsEmptyNullElements()
    {
        vector = new VectorAdapter();

        vector.add(null);
        vector.add(null);

        assertFalse("Vector with null element should not be empty", vector.isEmpty());
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
    public void testContainsNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertTrue("Should contain null element", vector.contains(null));
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
    public void testContainsAfterRemove()
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
    public void testContainsAfterSet()
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
    public void testIndexOfNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertEquals("Should return correct index for null element", 1, vector.indexOf(null));
    }

    @Test
    public void testIndexOfOnEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Should return -1 for any element in empty vector", -1, vector.indexOf("1"));
        assertEquals("Should return -1 for null in empty vector", -1, vector.indexOf(null));
    }

    @Test
    public void testIndexOfDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("1");

        assertEquals("Should return first occurrence index for duplicate elements", 1, vector.indexOf("1"));
    }

    @Test
    public void testIndexOfAfterRemove()
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
    public void testIndexOfWithIndexNullElement()
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
    public void testIndexOfWithIndexDuplicateElements()
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
    public void testIndexOfWithIndexAfterRemove()
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
    public void testLastIndexOfNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertEquals("Should return correct last index for null element", 1, vector.lastIndexOf(null));
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
    public void testLastIndexOfAfterRemove()
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
    public void testLastIndexOfWithIndexNullElement()
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
    public void testLastIndexOfWithIndexDuplicateElements()
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
    public void testLastIndexOfWithIndexAfterRemove()
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLastIndexOfWithIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.lastIndexOf("1", 3);
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
    public void testElementAtOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.elementAt(0);
    }

    @Test
    public void testElementAtNullElement()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add("1");
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
    public void testFirstElementNullElement()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add("3");

        assertNull("Should return null first element", vector.firstElement());
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
    public void testLastElementNullElement()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add(null);
        vector.add(null);

        assertNull("Should return null last element", vector.lastElement());
    }

    @Test
    public void testFirstAndLastElementInSingleElementCollection()
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
    public void testSetElementAtAtTheBeginning()
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
    public void testSetElementAtAtTheEnd()
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
    public void testSetElementAtNull()
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
    public void testRemoveElementAtAtTheBeginning()
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
    public void testRemoveElementAtAtTheEnd()
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
    public void testRemoveElementAtNullElements()
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
    public void testInsertElementAtAtTheBeginning()
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
    public void testInsertElementAtAtTheEnd()
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
    public void testInsertElementAtNullElement()
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
    public void testAddElementToNonEmptyVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.addElement("3");

        assertEquals("Size should be 3 after adding elements", 3, vector.size());
        assertEquals("First element should remain",  "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
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
    public void testAddElementNullElement()
    {
        vector = new VectorAdapter();
        vector.addElement(null);

        assertEquals("Size should be 1 after adding null", 1, vector.size());
        assertNull("Element should be null", vector.get(0));
    }

    @Test
    public void testRemoveElementExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertTrue("Should return true for existing element", vector.removeElement("2"));
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveElementNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertFalse("Should return false for non-existing element", vector.removeElement("4"));
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testRemoveElementNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertTrue("Should return true for null element", vector.removeElement(null));
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveElementFromEmptyVector()
    {
        vector = new VectorAdapter();

        assertFalse("Should return false for empty vector", vector.removeElement("1"));
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    @Test
    public void testRemoveElementFirstOccurrenceOnly()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("1");
        vector.add("3");

        assertTrue("Should return true for existing element", vector.removeElement("1"));
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
    public void testRemoveAllElementsNullElements()
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
    public void testCloneEmptyVector()
    {
        vector = new VectorAdapter();
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());
        assertTrue("Clone should be empty", vec.isEmpty());
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
    public void testCloneNullElements()
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
    public void testCloneIndependence()
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
        Object[] arr = vector.toArray();

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should match vector size", 0, arr.length);
    }

    @Test
    public void testToArrayMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object[] arr = vector.toArray();

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should match vector size", 3, arr.length);
        assertEquals("First array element should match", "1", arr[0]);
        assertEquals("Second array element should match", "2", arr[1]);
        assertEquals("Third array element should match", "3", arr[2]);
    }

    @Test
    public void testToArrayNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        Object[] arr = vector.toArray();

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should match vector size", 3, arr.length);
        assertNull("First array element should be null", arr[0]);
        assertNull("Second array element should be null", arr[1]);
        assertNull("Third array element should null", arr[2]);
    }

    @Test
    public void testToArrayWithReferenceEmptyVector()
    {
        vector = new VectorAdapter();
        Object[] array = new Object[0];
        Object[] arr = vector.toArray(array);

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should be 0", 0, arr.length);
    }

    @Test
    public void testToArrayWithReferenceExactSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object[] array = new Object[3];
        Object[] arr = vector.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertEquals("First array element should match", "1", arr[0]);
        assertEquals("Second array element should match", "2", arr[1]);
        assertEquals("Third array element should match", "3", arr[2]);
    }

    @Test
    public void testToArrayWithReferenceLargerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        Object[] array = new Object[5];
        array[2] = "0";
        array[3] = "0";
        array[4] = "0";

        Object[] arr = vector.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertEquals("First array element should match", "1", arr[0]);
        assertEquals("Second array element should match", "2", arr[1]);
        assertNull("Element after vector content should be null", arr[2]);
        assertEquals("Existing elements should be preserved", "0", arr[3]);
        assertEquals("Existing elements should be preserved", "0", arr[4]);
    }

    @Test
    public void testToArrayWithReferenceSmallerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Object[] array = new Object[2];
        Object[] arr = vector.toArray(array);

        assertNotSame("Should return a different array", array, arr);
        assertEquals("New array length should match vector size", 3, arr.length);
        assertEquals("First array element should match", "1", arr[0]);
        assertEquals("Second array element should match", "2", arr[1]);
        assertEquals("Third array element should match", "3", arr[2]);
    }

    @Test
    public void testToArrayWithReferenceNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        Object[] array = new Object[5];
        Object[] arr = vector.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertNull("First array element should be null", arr[0]);
        assertNull("Second array element should be null", arr[1]);
        assertNull("Third array element should be null", arr[2]);
        assertNull("Element after vector content should be null", arr[3]);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayWithReferenceNullArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.toArray(null);
    }

    @Test
    public void testToArrayWithReferenceEmptyVectorNullTermination()
    {
        vector = new VectorAdapter();
        Object[] array = new Object[3];
        array[0] = "0";
        array[1] = "0";
        array[2] = "0";

        Object[] arr = vector.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertNull("First element should be null for empty vector", arr[0]);
        assertEquals("Second element should remain unchanged", "0", arr[1]);
        assertEquals("Third element should remain unchanged", "0", arr[2]);
    }

    @Test
    public void testToArrayWithReferenceZeroLengthArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        Object[] array = new Object[0];
        Object[] arr = vector.toArray(array);

        assertNotSame("Should return a different array", array, arr);
        assertEquals("New array length should match vector size", 2, arr.length);
        assertEquals("First array element should match", "1", arr[0]);
        assertEquals("Second array element should match", "2", arr[1]);
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
    public void testGetOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.get(0);
    }

    @Test
    public void testGetNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertNull("Should return null element at index 0", vector.get(0));
        assertNull("Should return null element at index 1", vector.get(1));
        assertNull("Should return null element at index 2", vector.get(2));
    }

    @Test
    public void testSetValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return previous element at index 1", "2", vector.set(1, "4"));
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
    public void testSetOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.set(0, "1");
    }

    @Test
    public void testSetAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return previous first element", "1", vector.set(0, "0"));
        assertEquals("First element should be updated", "0", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
        assertEquals("Third element should remain unchanged", "3", vector.get(2));
    }

    @Test
    public void testSetAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return previous last element", "3", vector.set(2, "4"));
        assertEquals("Last element should be updated", "4", vector.get(2));
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
    }

    @Test
    public void testSetNull()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return previous element", "2", vector.set(1, null));
        assertNull("Element at index 1 should be null", vector.get(1));
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Third element should remain unchanged", "3", vector.get(2));
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
    public void testAddToNonEmptyVector()
    {
        vector = new VectorAdapter();
        vector.add("1");

        assertTrue("Should return true", vector.add("2"));
        assertEquals("Size should be 2 after adding elements", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
    }

    @Test
    public void testAddToEmptyVector()
    {
        vector = new VectorAdapter();

        assertTrue("Should return true", vector.add("1"));
        assertEquals("Size should be 1 after adding element", 1, vector.size());
        assertEquals("Element should be added at index 0", "1", vector.get(0));
        assertFalse("Vector should not be empty", vector.isEmpty());
    }

    @Test
    public void testAddNullElement()
    {
        vector = new VectorAdapter();

        assertTrue("Should return true", vector.add(null));
        assertEquals("Size should be 1 after adding null", 1, vector.size());
        assertNull("Element should be null", vector.get(0));
    }

    @Test
    public void testRemoveExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertTrue("Should return true for existing element", vector.remove("2"));
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

        assertFalse("Should return false for non-existing element", vector.remove("4"));
        assertEquals("Size should remain unchanged", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should remain", "3", vector.get(2));
    }

    @Test
    public void testRemoveNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertTrue("Should return true for null element", vector.remove(null));
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testRemoveFromEmptyVector()
    {
        vector = new VectorAdapter();

        assertFalse("Should return false for empty vector", vector.remove("1"));
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

        assertTrue("Should return true for existing element", vector.remove("1"));
        assertEquals("Size should be decreased", 3, vector.size());
        assertEquals("First occurrence should be removed", "2", vector.get(0));
        assertEquals("Second occurrence should remain", "1", vector.get(1));
        assertEquals("Third element should be shifted", "3", vector.get(2));
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
    public void testAddAtIndexAtTheBeginning()
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
    public void testAddAtIndexAtTheEnd()
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
    public void testAddAtIndexNullElement()
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
    public void testRemoveAtIndexValidPosition()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return removed element", "2", vector.remove(1));
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
        vector.add("3");

        vector.remove(3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveAtIndexOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.remove(0);
    }

    @Test
    public void testRemoveAtIndexAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return first element", "1", vector.remove(0));
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("Second element should become first", "2", vector.get(0));
        assertEquals("Third element should become second", "3", vector.get(1));
    }

    @Test
    public void testRemoveAtIndexAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return last element", "3", vector.remove(2));
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain unchanged", "1", vector.get(0));
        assertEquals("Second element should remain unchanged", "2", vector.get(1));
    }

    @Test
    public void testRemoveAtIndexNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertNull("Should return null element", vector.remove(1));
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
    public void testClearOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.clear();

        assertEquals("Size should be 0 after clearing empty vector", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
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
    public void testClearNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.clear();

        assertEquals("Size should be 0 after clearing", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testContainsAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();

        assertTrue("Should return true for empty collection", vector.containsAll(vec));
    }

    @Test
    public void testContainsAllEveryElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertTrue("Should return true when all elements are present", vector.containsAll(vec));
    }

    @Test
    public void testContainsAllSomeElementsMissing()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("4");

        assertFalse("Should return false when some elements are missing", vector.containsAll(vec));
    }

    @Test
    public void testContainsAllEveryElementsMissing()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");
        vec.add("6");

        assertFalse("Should return false when all elements are missing", vector.containsAll(vec));
    }

    @Test
    public void testContainsAllSameElementsDifferentOrder()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");
        vec.add("1");

        assertTrue("Should return true when all elements are present", vector.containsAll(vec));
    }

    @Test
    public void testContainsAllDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("2");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertTrue("Should return true when vector has duplicates", vector.containsAll(vec));
    }

    @Test
    public void testContainsAllNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add("1");

        assertTrue("Should return true for null elements", vector.containsAll(vec));
    }

    @Test
    public void testContainsAllEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertFalse("Should return false for empty vector", vector.containsAll(vec));
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

        assertTrue("Should return true when collection is itself", vector.containsAll(vector));
    }

    @Test
    public void testAddAllNonEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter vec = new VectorAdapter();
        vec.add("3");
        vec.add("4");

        assertTrue("Should return true for non-empty collection", vector.addAll(vec));
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
        assertEquals("Fourth element should be added", "4", vector.get(3));
    }

    @Test
    public void testAddAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter vec = new VectorAdapter();

        assertFalse("Should return false for empty collection", vector.addAll(vec));
        assertEquals("Size should remain unchanged", 2, vector.size());
    }

    @Test
    public void testAddAllToEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true", vector.addAll(vec));
        assertEquals("Size should match collection size", 3, vector.size());
        assertEquals("First element should be added", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
    }

    @Test
    public void testAddAllNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");

        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);

        assertTrue("Should return true", vector.addAll(vec));
        assertEquals("Size should be increased", 3, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertNull("Second element should be null", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
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
    public void testAddAllToSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        assertTrue("Should return true when adding itself", vector.addAll(vector));
        assertEquals("Size should be doubled", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
        assertEquals("First element should be added again", "1", vector.get(2));
        assertEquals("Second element should be added again", "2", vector.get(3));
    }

    @Test
    public void testRemoveAllEveryElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true when all elements are removed", vector.removeAll(vec));
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRemoveAllSomeElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true when elements are removed", vector.removeAll(vec));
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Fourth element should remain", "4", vector.get(1));
    }

    @Test
    public void testRemoveAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();

        assertFalse("Should return false for empty collection", vector.removeAll(vec));
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testRemoveAllNoElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");

        assertFalse("Should return false when no elements are present", vector.removeAll(vec));
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testRemoveAllDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("2");

        assertTrue("Should return true", vector.removeAll(vec));
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

        VectorAdapter vec = new VectorAdapter();
        vec.add(null);

        assertTrue("Should return true", vector.removeAll(vec));
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Third element should remain", "3", vector.get(1));
    }

    @Test
    public void testRemoveAllEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertFalse("Should return false for empty vector", vector.removeAll(vec));
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

        assertTrue("Should return true when removing itself", vector.removeAll(vector));
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRetainAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();

        assertTrue("Should return true when retaining empty collection", vector.retainAll(vec));
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

        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");

        assertTrue("Should return true when no elements in common", vector.retainAll(vec));
        assertEquals("Size should be 0", 0, vector.size());
        assertTrue("Vector should be empty", vector.isEmpty());
    }

    @Test
    public void testRetainAllEveryElementsInCommon()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        assertFalse("Should return false when all elements are retained", vector.retainAll(vec));
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

        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true when some elements are retained", vector.retainAll(vec));
        assertEquals("Size should be decreased", 2, vector.size());
        assertEquals("First retained element should be '2'", "2", vector.get(0));
        assertEquals("Second retained element should be '3'", "3", vector.get(1));
    }

    @Test
    public void testRetainAllDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("2");

        assertTrue("Should return true when retaining duplicates", vector.retainAll(vec));
        assertEquals("Size should be 2", 2, vector.size());
        assertEquals("First element should be '2'", "2", vector.get(0));
        assertEquals("Second element should be '2'", "2", vector.get(1));
    }

    @Test
    public void testRetainAllNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);

        VectorAdapter vec = new VectorAdapter();
        vec.add(null);

        assertTrue("Should return true when retaining null elements", vector.retainAll(vec));
        assertEquals("Size should be 2", 2, vector.size());
        assertNull("Only null elements should remain", vector.get(0));
        assertNull("Only null elements should remain", vector.get(1));
    }

    @Test
    public void testRetainAllEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertFalse("Should return false for empty vector", vector.retainAll(vec));
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

        assertFalse("Should return false when retaining itself", vector.retainAll(vector));
        assertEquals("Size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testAddAllAtIndexNonEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("4");

        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true for non-empty collection", vector.addAll(1, vec));
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be added", "3", vector.get(2));
        assertEquals("Fourth element should be shifted", "4", vector.get(3));
    }

    @Test
    public void testAddAllAtIndexEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter vec = new VectorAdapter();

        assertFalse("Should return false for empty collection", vector.addAll(1, vec));
        assertEquals("Size should remain unchanged", 2, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should remain", "2", vector.get(1));
    }

    @Test
    public void testAddAllAtIndexAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("3");
        vector.add("4");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertTrue("Should return true", vector.addAll(0, vec));
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should be added", "1", vector.get(0));
        assertEquals("Second element should be added", "2", vector.get(1));
        assertEquals("Third element should be shifted", "3", vector.get(2));
        assertEquals("Fourth element should be shifted", "4", vector.get(3));
    }

    @Test
    public void testAddAllAtIndexAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter vec = new VectorAdapter();
        vec.add("3");
        vec.add("4");

        assertTrue("Should return true", vector.addAll(2, vec));
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

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true", vector.addAll(0, vec));
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

        VectorAdapter vec = new VectorAdapter();
        vec.add("3");

        vector.addAll(-1, vec);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddAllAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        VectorAdapter vec = new VectorAdapter();
        vec.add("3");

        vector.addAll(3, vec);
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
    public void testAddAllAtIndexNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);

        assertTrue("Should return true", vector.addAll(1, vec));
        assertEquals("Size should be increased", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertNull("Second element should be null", vector.get(1));
        assertNull("Third element should be null", vector.get(2));
        assertEquals("Fourth element should be shifted", "3", vector.get(3));
    }

    @Test
    public void testAddAllAtIndexSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        assertTrue("Should return true when adding itself", vector.addAll(1, vector));
        assertEquals("Size should be doubled", 4, vector.size());
        assertEquals("First element should remain", "1", vector.get(0));
        assertEquals("Second element should be added", "1", vector.get(1));
        assertEquals("Third element should be added", "2", vector.get(2));
        assertEquals("Fourth element should be shifted", "2", vector.get(3));
    }

    @Test
    public void testEqualsSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertTrue("Should return true for same object", vector.equals(vector));
    }

    @Test
    public void testEqualsEqualLists()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true for equal lists", vector.equals(vec));
    }

    @Test
    public void testEqualsDifferentSizes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertFalse("Should return false for different sizes", vector.equals(vec));
    }

    @Test
    public void testEqualsDifferentElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("4");
        vec.add("3");

        assertFalse("Should return false for different elements", vector.equals(vec));
    }

    @Test
    public void testEqualsDifferentOrder()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("3");
        vec.add("2");

        assertFalse("Should return false for different order", vector.equals(vec));
    }

    @Test
    public void testEqualsNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add(null);
        vec.add("3");

        assertTrue("Should return true for lists with null elements", vector.equals(vec));
    }

    @Test
    public void testEqualsEmptyLists()
    {
        vector = new VectorAdapter();
        VectorAdapter vec = new VectorAdapter();

        assertTrue("Should return true for empty lists", vector.equals(vec));
    }

    @Test
    public void testEqualsNonListObject()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertFalse("Should return false for non-list object", vector.equals("1"));
    }

    @Test
    public void testHashCodeOfEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Hash code of empty vector should match empty vector", new VectorAdapter().hashCode(), vector.hashCode());
    }

    @Test
    public void testHashCodeOfMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        assertEquals("Hash code should match for multiple elements", vec.hashCode(), vector.hashCode());
    }

    @Test
    public void testHashCodeOfNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        assertEquals("Hash code should match for null elements", vec.hashCode(), vector.hashCode());
    }

    @Test
    public void testToStringEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("String representation of empty vector should match", "[]", vector.toString());
    }

    @Test
    public void testToStringMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("String representation of multiple elements should match", "[1, 2, 3]", vector.toString());
    }

    @Test
    public void testToStringNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertEquals("String representation with null elements should match", "[1, null, 3]", vector.toString());
    }

    @Test
    public void testToStringDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");

        assertEquals("String representation with duplicate elements should match", "[1, 1, 1]", vector.toString());
    }
}
