package myTest;

import myAdapter.SubListAdapter;
import myAdapter.VectorAdapter;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/*
 * RIVEDERE BENE
 * CONTROLLA DOCUMENTAZIONE
 * CONFRONTA CON VECTOR
 * DA ESTENDERE
 * RICONTROLLA COERENZA
 * NOMI METODI
 * */
public class SubListAdapterTest
{
    private VectorAdapter vector;
    private SubListAdapter subList;

    @Before
    public void setUp()
    {
        vector = null;
        subList = null;
    }

    @After
    public void tearDown()
    {
        vector = null;
        subList = null;
    }

    @Test
    public void testSubListValidParameters()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 3", 3, subList.size());
        assertEquals("First element should match", "2", subList.get(0));
        assertEquals("Second element should match", "3", subList.get(1));
        assertEquals("Third element should match", "4", subList.get(2));
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListSingleElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 1", 1, subList.size());
        assertEquals("Element should match", "2", subList.get(0));
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListEmptyRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 0", 0, subList.size());
        assertTrue("Should be empty", subList.isEmpty());
    }

    @Test
    public void testSubListFullRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 3", 3, subList.size());
        assertEquals("First element should match", "1", subList.get(0));
        assertEquals("Second element should match", "2", subList.get(1));
        assertEquals("Third element should match", "3", subList.get(2));
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);

        subList = (SubListAdapter) vector.subList(1, 3);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 2", 2, subList.size());
        assertNull("First element should be null", subList.get(0));
        assertNull("Second element should be null", subList.get(1));
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubListConstructorFromIndexGreaterThanToIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(2, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListConstructorNegativeFromIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(-1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListConstructorToIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 3);
    }

    @Test
    public void testSubListConstructorDefaultVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 2", 2, subList.size());
        assertEquals("First element should match", "1", subList.get(0));
        assertEquals("Second element should match", "2", subList.get(1));
        assertEquals("Capacity should match", subList.capacity(), vector.capacity());
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListConstructorCapacityVector()
    {
        vector = new VectorAdapter(5);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 2", 2, subList.size());
        assertEquals("First element should match", "2", subList.get(0));
        assertEquals("Second element should match", "3", subList.get(1));
        assertEquals("Capacity should match", subList.capacity(), vector.capacity());
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListConstructorCapacityAndIncrementVector()
    {
        vector = new VectorAdapter(3, 2);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 3", 3, subList.size());
        assertEquals("First element should match", "2", subList.get(0));
        assertEquals("Second element should match", "3", subList.get(1));
        assertEquals("Third element should match", "4", subList.get(2));
        assertEquals("Capacity should match", subList.capacity(), vector.capacity());
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListConstructorCollectionVector()
    {
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        vector = new VectorAdapter(vec);
        subList = (SubListAdapter) vector.subList(0, 2);

        assertNotNull("Should have been created", subList);
        assertEquals("Initial size should be 2", 2, subList.size());
        assertEquals("First element should match", "1", subList.get(0));
        assertEquals("Second element should match", "2", subList.get(1));
        assertEquals("Capacity should match", subList.capacity(), vector.capacity());
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListCopyIntoCorrectOrder()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        Object[] arr = new Object[3];
        subList.copyInto(arr);

        assertEquals("First element should be '2'", "2", arr[0]);
        assertEquals("Second element should be '3'", "3", arr[1]);
        assertEquals("Third element should be '4'", "4", arr[2]);
    }

    @Test
    public void testSubListCopyIntoEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        Object[] arr = new Object[0];
        subList.copyInto(arr);

        assertEquals("Array should remain empty", 0, arr.length);
    }

    @Test
    public void testSubListCopyIntoLargerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 3);
        Object[] arr = new Object[3];
        arr[1] = "0";
        arr[2] = "0";

        subList.copyInto(arr);

        assertEquals("First element should be '2'", "2", arr[0]);
        assertEquals("Second element should be '3'", "3", arr[1]);
        assertEquals("Existing elements should be preserved", "0", arr[2]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListCopyIntoSmallerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        Object[] arr = new Object[2];
        subList.copyInto(arr);
    }

    @Test
    public void testSubListCopyIntoNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add(null);
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        Object[] arr = new Object[2];
        arr[0] = "0";
        arr[1] = "0";

        subList.copyInto(arr);

        assertNull("First element should be null", arr[0]);
        assertNull("Second element should be null", arr[1]);
    }

    @Test(expected = NullPointerException.class)
    public void testSubListCopyIntoNullArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.copyInto(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListCopyIntoZeroLengthArrayWithElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 1);
        Object[] arr = new Object[0];
        subList.copyInto(arr);
    }

    @Test
    public void testSubListTrimToSizeAffectsOnlyVectorCapacity()
    {
        vector = new VectorAdapter(20);
        vector.add("1");
        vector.add("1");
        vector.add("1");
        vector.add("1");
        vector.add("1");

        subList = (SubListAdapter) vector.subList(1, 4);

        assertEquals("Capacities should be the same", vector.capacity(), subList.capacity());

        subList.trimToSize();

        assertEquals("SubList capacity has been trimmed to Vector size", subList.capacity(), vector.size());
        assertEquals("SubList capacity should be equal to 5", 5, subList.capacity());
        assertEquals("SubList size should be 3", 3, subList.size());
    }

    @Test
    public void testSubListTrimToSizeAffectsOnlyVectorCapacityNullElements()
    {
        vector = new VectorAdapter(20);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);

        subList = (SubListAdapter) vector.subList(1, 4);

        assertEquals("Capacities should be the same", vector.capacity(), subList.capacity());

        subList.trimToSize();

        assertEquals("SubList capacity has been trimmed to Vector size", subList.capacity(), vector.size());
        assertEquals("SubList capacity should be 5", 5, subList.capacity());
        assertEquals("SubList size should be 3", 3, subList.size());
    }

    @Test
    public void testSubListEnsureCapacityConstructor()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.ensureCapacity(5);

        assertEquals("Capacity should remain unchanged when minCapacity is smaller", 10, subList.capacity());
        assertEquals("Size should remain 3", 3, subList.size());
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListEnsureCapacityExactCurrentCapacity()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.ensureCapacity(subList.capacity());

        assertEquals("Capacity should remain unchanged when minCapacity equals current capacity", 10, subList.capacity());
        assertEquals("Size should remain 3", 3, subList.size());
        assertFalse("Should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListEnsureCapacityCapacityIncrement()
    {
        vector = new VectorAdapter(5, 3);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.ensureCapacity(7);

        assertEquals("Capacity should increase using capacity increment", 8, subList.capacity());
        assertEquals("Size should remain 3", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
    }

    @Test
    public void testSubListEnsureCapacityCapacityIncrementInsufficient()
    {
        vector = new VectorAdapter(5, 3);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.ensureCapacity(10);

        assertEquals("Capacity should increase using minCapacity", 10, subList.capacity());
        assertEquals("Size should remain 3", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
    }

    @Test
    public void testSubListEnsureCapacityDoubleGrowthIncrement()
    {
        vector = new VectorAdapter(5);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.ensureCapacity(9);

        assertEquals("Capacity should double when capacity increment is zero", 10, subList.capacity());
        assertEquals("Size should remain 3", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
    }

    @Test
    public void testSubListEnsureCapacityDoubleGrowthInsufficient()
    {
        vector = new VectorAdapter(5);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.ensureCapacity(20);

        assertEquals("Capacity should be set to minCapacity when double growth is insufficient", 20, subList.capacity());
        assertEquals("Size should remain 3", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
    }

    @Test
    public void testSubListEnsureCapacityAfterSetSize()
    {
        vector = new VectorAdapter(5);
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setSize(4);
        subList.ensureCapacity(20);

        assertEquals("Capacity should increase", 20, subList.capacity());
        assertEquals("Size should remain 4", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
        assertNull("Fourth element should be null", subList.get(3));
    }

    @Test
    public void testSubListEnsureCapacityAfterTrimToSize()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.trimToSize();
        subList.ensureCapacity(8);

        assertEquals("Capacity should increase after trimming", 8, subList.capacity());
        assertEquals("Size should remain unchanged", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
    }

    @Test
    public void testSubListEnsureCapacityZeroMinCapacity()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.ensureCapacity(0);

        assertEquals("Capacity should remain unchanged for zero minCapacity", 10, subList.capacity());
        assertEquals("Size should remain 3", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
    }

    @Test
    public void testSubListEnsureCapacityNegativeMinCapacity()
    {
        vector = new VectorAdapter(10);
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.ensureCapacity(-5);

        assertEquals("Capacity should remain unchanged for negative minCapacity", 10, subList.capacity());
        assertEquals("Size should remain 3", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
    }

    @Test
    public void testSubListSetSizeIncreaseFromEmpty()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.setSize(2);

        assertEquals("Size should be increased to 2", 2, subList.size());
        assertEquals("Capacity should accommodate new size", vector.capacity(), subList.capacity());
        assertNull("First element should be null", subList.get(0));
        assertNull("Second element should be null", subList.get(1));
        assertEquals("Original vector size should be increased", 5, vector.size());
    }

    @Test
    public void testSubListSetSizeIncreaseExistingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.setSize(4);

        assertEquals("Size should be increased to 4", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertNull("Third element should be null", subList.get(2));
        assertNull("Fourth element should be null", subList.get(3));
        assertEquals("Original vector size should be increased", 5, vector.size());
    }

    @Test
    public void testSubListSetSizeDecreaseExistingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(0, 4);
        subList.setSize(2);

        assertEquals("Size should be decreased to 2", 2, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Original vector size should be decreased", 2, vector.size());
    }

    @Test
    public void testSubListSetSizeSameSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setSize(3);

        assertEquals("Size should remain 3", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
        assertEquals("Original vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListSetSizeToZero()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setSize(0);

        assertEquals("Size should be 0", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Original vector size should be decreased", 0, vector.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetSizeNegative()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.setSize(-1);
    }

    @Test
    public void testSubListSetSizeDecreaseThenIncrease()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.setSize(2);
        subList.setSize(4);

        assertEquals("Size should be increased to 4", 4, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should remain", "3", subList.get(1));
        assertNull("Third element should be null", subList.get(2));
        assertNull("Fourth element should be null", subList.get(3));
        assertEquals("Original vector size should be 6", 6, vector.size());
    }

    @Test
    public void testSubListSetSizeIncreaseThenDecrease()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.setSize(4);
        subList.setSize(1);

        assertEquals("Size should be decreased to 1", 1, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Original vector size should be 1", 1, vector.size());
    }

    @Test
    public void testSubListSetSizeNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setSize(5);

        assertEquals("Size should be increased to 5", 5, subList.size());
        assertNull("First element should remain null", subList.get(0));
        assertNull("Second element should remain null", subList.get(1));
        assertNull("Third element should remain null", subList.get(2));
        assertNull("Fourth element should be null", subList.get(3));
        assertNull("Fifth element should be null", subList.get(4));
        assertEquals("Original vector size should be 5", 5, vector.size());
    }

    @Test
    public void testSubListSetSizeAffectsOnlySubListRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.setSize(2);

        assertEquals("SubList size should be 2", 2, subList.size());
        assertEquals("Vector size should be decreased", 4, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
        assertEquals("First subList element should remain", "2", vector.get(1));
        assertEquals("Second subList element should remain", "3", vector.get(2));
        assertEquals("Element after subList should be shifted", "5", vector.get(3));
    }

    @Test
    public void testSubListCapacityEmptyVector()
    {
        vector = new VectorAdapter();

        subList = (SubListAdapter) vector.subList(0, 0);

        assertEquals("Capacity should be 10", 10, subList.capacity());
    }

    @Test
    public void testSubListCapacityAtInitialCapacity()
    {
        vector = new VectorAdapter(15);

        subList = (SubListAdapter) vector.subList(0, 0);

        assertEquals("Capacity should be 15", 15, subList.capacity());
    }

    @Test
    public void testSubListSizeEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        assertEquals("Size should be 0 for empty subList", 0, subList.size());
    }

    @Test
    public void testSubListSizeAfterAddingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add("4");
        assertEquals("Size should be 3 after adding one element", 3, subList.size());
    }

    @Test
    public void testSubListSizeAfterRemovingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.remove(1);
        assertEquals("Size should be 2 after removing one element", 2, subList.size());
    }

    @Test
    public void testSubListSizeAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.clear();
        assertEquals("Size should be 0 after clear", 0, subList.size());
    }

    @Test
    public void testSubListSizeNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        subList = (SubListAdapter) vector.subList(0, 3);
        assertEquals("Size should count null elements", 3, subList.size());
    }

    @Test
    public void testSubListIsEmptyEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        assertTrue("Empty subList should be empty", subList.isEmpty());
    }

    @Test
    public void testSubListIsEmptyAfterAddingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add("4");
        assertFalse("SubList should not be empty after adding element", subList.isEmpty());
    }

    @Test
    public void testSubListIsEmptyAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.clear();
        assertTrue("SubList should be empty after clear", subList.isEmpty());
    }

    @Test
    public void testSubListIsEmptyNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);

        subList = (SubListAdapter) vector.subList(0, 2);
        assertFalse("SubList with null elements should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListIsEmptyAfterSetSizeZero()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setSize(0);
        assertTrue("SubList should be empty after setSize(0)", subList.isEmpty());
    }

    @Test
    public void testSubListContainsExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertTrue("Should contain element '2'", subList.contains("2"));
        assertTrue("Should contain element '3'", subList.contains("3"));
    }

    @Test
    public void testSubListContainsNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertFalse("Should not contain element '1'", subList.contains("1"));
        assertFalse("Should not contain element '4'", subList.contains("4"));
    }

    @Test
    public void testSubListContainsNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertTrue("Should contain null element", subList.contains(null));
    }

    @Test
    public void testSubListContainsOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        assertFalse("Empty subList should not contain any element", subList.contains("1"));
        assertFalse("Empty subList should not contain any element", subList.contains("2"));
        assertFalse("Empty subList should not contain null", subList.contains(null));
    }

    @Test
    public void testSubListContainsAfterRemove()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.remove("2");
        subList.remove("3");

        assertFalse("Should not contain removed element '2'", subList.contains("2"));
        assertFalse("Should not contain removed element '3'", subList.contains("3"));
        assertTrue("Should still contain '4'", subList.contains("4"));
    }

    @Test
    public void testSubListContainsAfterSet()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.set(1, "4");

        assertFalse("Should not contain replaced element", subList.contains("2"));
        assertTrue("Should contain new element", subList.contains("4"));
    }

    @Test
    public void testSubListContainsElementsOutsideRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertFalse("Should not contain element before subList range", subList.contains("1"));
        assertFalse("Should not contain element after subList range", subList.contains("4"));
    }

    @Test
    public void testSubListIndexOfExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return correct index for first element", 0, subList.indexOf("2"));
        assertEquals("Should return correct index for second element", 1, subList.indexOf("3"));
    }

    @Test
    public void testSubListIndexOfNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return -1 for non-existing element", -1, subList.indexOf("1"));
        assertEquals("Should return -1 for non-existing element", -1, subList.indexOf("4"));
    }

    @Test
    public void testSubListIndexOfNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertEquals("Should return correct index for null element", 0, subList.indexOf(null));
    }

    @Test
    public void testSubListIndexOfOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        assertEquals("Should return -1 for any element in empty subList", -1, subList.indexOf("1"));
        assertEquals("Should return -1 for null in empty subList", -1, subList.indexOf(null));
    }

    @Test
    public void testSubListIndexOfDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("1");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return first occurrence index for duplicate elements", 0, subList.indexOf("1"));
    }

    @Test
    public void testSubListIndexOfAfterRemove()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.remove("2");

        assertEquals("Should still find remaining elements", 0, subList.indexOf("1"));
        assertEquals("Should not find removed element", -1, subList.indexOf("2"));
        assertEquals("Should still find remaining elements", 1, subList.indexOf("3"));
    }

    @Test
    public void testSubListIndexOfElementsOutsideRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should not find element before subList range", -1, subList.indexOf("1"));
        assertEquals("Should not find element after subList range", -1, subList.indexOf("4"));
    }

    @Test
    public void testSubListIndexOfWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return correct index with offset", 0, subList.indexOf("2"));
        assertEquals("Should return correct index with offset", 1, subList.indexOf("3"));
    }

    @Test
    public void testSubListIndexOfWithIndexExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertEquals("Should find element starting from index 0", 1, subList.indexOf("3", 0));
        assertEquals("Should find element starting from index 1", 1, subList.indexOf("3", 1));
        assertEquals("Should find element starting from index 2", -1, subList.indexOf("3", 2));
        assertEquals("Should find element starting from index 3", -1, subList.indexOf("3", 3));
    }

    @Test
    public void testSubListIndexOfWithIndexNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return -1 for non-existing element", -1, subList.indexOf("4", 0));
        assertEquals("Should return -1 for non-existing element", -1, subList.indexOf("4", 1));
        assertEquals("Should return -1 for non-existing element", -1, subList.indexOf("4", 2));
    }

    @Test
    public void testSubListIndexOfWithIndexNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertEquals("Should find first null starting from index 0", 0, subList.indexOf(null, 0));
        assertEquals("Should find second null starting from index 1", 2, subList.indexOf(null, 1));
        assertEquals("Should not find null starting from index 3", -1, subList.indexOf(null, 3));
    }

    @Test
    public void testSubListIndexOfWithIndexDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("1");
        vector.add("2");
        vector.add("1");

        subList = (SubListAdapter) vector.subList(1, 5);
        assertEquals("Should find second occurrence starting from index 0", 0, subList.indexOf("1", 0));
        assertEquals("Should find third occurrence starting from index 1", 1, subList.indexOf("1", 1));
        assertEquals("Should find fourth occurrence starting from index 2", 3, subList.indexOf("1", 2));
        assertEquals("Should find fourth occurrence starting from index 3", 3, subList.indexOf("1", 3));
        assertEquals("Should not find occurrence starting from index 4", -1, subList.indexOf("1", 4));
    }

    @Test
    public void testSubListIndexOfWithIndexAfterRemove()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.remove("2");

        assertEquals("Should find remaining elements starting from index 0", 0, subList.indexOf("3", 0));
        assertEquals("Should find remaining elements starting from index 1", 1, subList.indexOf("4", 1));
        assertEquals("Should not find removed element", -1, subList.indexOf("2", 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListIndexOfWithIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.indexOf("1", -1);
    }

    @Test
    public void testSubListIndexOfWithIndexEqualOrGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        assertEquals("Should return -1 when index equals size", -1, subList.indexOf("1", 2));
        assertEquals("Should return -1 when index greater than size", -1, subList.indexOf("2", 3));
    }

    @Test
    public void testSubListIndexOfWithIndexEdgeCases()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertEquals("Should find element at exact index", 1, subList.indexOf("3", 1));
        assertEquals("Should find element at exact index", 2, subList.indexOf("4", 2));
        assertEquals("Should not find element when start index is too high", -1, subList.indexOf("2", 2));
        assertEquals("Should find last element with max valid index", 2, subList.indexOf("4", 2));
    }

    @Test
    public void testSubListIndexOfWithIndexElementsOutsideRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should not find element before subList range", -1, subList.indexOf("1", 0));
        assertEquals("Should not find element after subList range", -1, subList.indexOf("4", 0));
    }

    @Test
    public void testSubListIndexOfWithIndexWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return correct index with offset", 0, subList.indexOf("2", 0));
        assertEquals("Should return correct index with offset", 1, subList.indexOf("3", 1));
    }

    @Test
    public void testSubListLastIndexOfExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return correct last index for first element", 0, subList.lastIndexOf("2"));
        assertEquals("Should return correct last index for second element", 1, subList.lastIndexOf("3"));
    }

    @Test
    public void testSubListLastIndexOfNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return -1 for non-existing element", -1, subList.lastIndexOf("1"));
        assertEquals("Should return -1 for non-existing element", -1, subList.lastIndexOf("4"));
    }

    @Test
    public void testSubListLastIndexOfNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertEquals("Should return correct last index for null element", 0, subList.lastIndexOf(null));
    }

    @Test
    public void testSubListLastIndexOfOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        assertEquals("Should return -1 for any element in empty subList", -1, subList.lastIndexOf("1"));
        assertEquals("Should return -1 for null in empty subList", -1, subList.lastIndexOf(null));
    }

    @Test
    public void testSubListLastIndexOfWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("1");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return last occurrence index for duplicate elements", 1, subList.lastIndexOf("1"));
    }

    @Test
    public void testSubListLastIndexOfAfterRemove()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.remove("2");

        assertEquals("Should still find remaining elements", 0, subList.lastIndexOf("1"));
        assertEquals("Should not find removed element", -1, subList.lastIndexOf("2"));
        assertEquals("Should still find remaining elements", 1, subList.lastIndexOf("3"));
    }

    @Test
    public void testSubListLastIndexOfElementsOutsideRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should not find element before subList range", -1, subList.lastIndexOf("1"));
        assertEquals("Should not find element after subList range", -1, subList.lastIndexOf("4"));
    }

    @Test
    public void testSubListLastIndexOfWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return correct index with offset", 0, subList.lastIndexOf("2"));
        assertEquals("Should return correct index with offset", 1, subList.lastIndexOf("3"));
    }

    @Test
    public void testSubListLastIndexOfWithIndexExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertEquals("Should find element ending at index 2", 1, subList.lastIndexOf("3", 2));
        assertEquals("Should find element ending at index 1", 1, subList.lastIndexOf("3", 1));
        assertEquals("Should find element ending at index 0", -1, subList.lastIndexOf("3", 0));
    }

    @Test
    public void testSubListLastIndexOfWithIndexNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return -1 for non-existing element", -1, subList.lastIndexOf("4", 1));
        assertEquals("Should return -1 for non-existing element", -1, subList.lastIndexOf("4", 0));
    }

    @Test
    public void testSubListLastIndexOfWithIndexNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertEquals("Should find last null ending at index 2", 2, subList.lastIndexOf(null, 2));
        assertEquals("Should find first null ending at index 1", 0, subList.lastIndexOf(null, 1));
        assertEquals("Should not find null ending at index 0", -1, subList.lastIndexOf(null, -1));
    }

    @Test
    public void testSubListLastIndexOfWithIndexDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("1");
        vector.add("2");
        vector.add("1");

        subList = (SubListAdapter) vector.subList(1, 5);
        assertEquals("Should find last occurrence ending at index 3", 3, subList.lastIndexOf("1", 3));
        assertEquals("Should find third occurrence ending at index 2", 1, subList.lastIndexOf("1", 2));
        assertEquals("Should find second occurrence ending at index 1", 1, subList.lastIndexOf("1", 1));
        assertEquals("Should not find occurrence ending at negative index", -1, subList.lastIndexOf("1", -1));
    }

    @Test
    public void testSubListLastIndexOfWithIndexAfterRemove()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.remove("2");

        assertEquals("Should find remaining elements ending at index 1", 0, subList.lastIndexOf("3", 1));
        assertEquals("Should find remaining elements ending at index 0", -1, subList.lastIndexOf("1", 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListLastIndexOfWithIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.lastIndexOf("1", 2);
    }

    @Test
    public void testSubListLastIndexOfWithIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        assertEquals("Should return -1 when index is negative", -1, subList.lastIndexOf("1", -1));
        assertEquals("Should return -1 when index is negative", -1, subList.lastIndexOf("2", -2));
    }

    @Test
    public void testSubListLastIndexOfWithIndexEdgeCases()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertEquals("Should find element at exact end index", 1, subList.lastIndexOf("3", 1));
        assertEquals("Should find element before end index", 0, subList.lastIndexOf("2", 1));
        assertEquals("Should not find element when end index is too low", -1, subList.lastIndexOf("4", 1));
        assertEquals("Should find first element with min valid index", 0, subList.lastIndexOf("2", 0));
    }

    @Test
    public void testSubListLastIndexOfWithIndexElementsOutsideRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should not find element before subList range", -1, subList.lastIndexOf("1", 1));
        assertEquals("Should not find element after subList range", -1, subList.lastIndexOf("4", 1));
    }

    @Test
    public void testSubListLastIndexOfWithIndexWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return correct index with offset", 0, subList.lastIndexOf("2", 0));
        assertEquals("Should return correct index with offset", 1, subList.lastIndexOf("3", 1));
    }

    @Test
    public void testSubListElementAtValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return element at index 0", "2", subList.elementAt(0));
        assertEquals("Should return element at index 1", "3", subList.elementAt(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.elementAt(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.elementAt(2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListElementAtOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.elementAt(0);
    }

    @Test
    public void testSubListElementAtNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertNull("Should return null element at index 0", subList.elementAt(0));
    }

    @Test
    public void testSubListElementAtWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return correct element with offset", "2", subList.elementAt(0));
        assertEquals("Should return correct element with offset", "3", subList.elementAt(1));
    }

    @Test
    public void testSubListFirstElementNonEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return first element of sublist", "2", subList.firstElement());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListFirstElementEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.firstElement();
    }

    @Test
    public void testSubListFirstElementNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertNull("Should return null first element", subList.firstElement());
    }

    @Test
    public void testSubListFirstElementWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return correct first element with offset", "2", subList.firstElement());
    }

    @Test
    public void testSubListLastElementNonEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return last element of sublist", "3", subList.lastElement());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubListLastElementEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.lastElement();
    }

    @Test
    public void testSubListLastElementNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertNull("Should return null last element", subList.lastElement());
    }

    @Test
    public void testSubListLastElementWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return correct last element with offset", "3", subList.lastElement());
    }

    @Test
    public void testSubListFirstAndLastElementInSingleElementSubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertEquals("First and last element should be equal for single element sublist",
                     subList.firstElement(), subList.lastElement());
    }

    @Test
    public void testSubListSetElementAtValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.setElementAt("4", 0);

        assertEquals("Element at index 0 should be updated", "4", subList.get(0));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("First element of vector should remain unchanged", "1", vector.get(0));
        assertEquals("Third element of vector should remain unchanged", "3", vector.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.setElementAt("3", -1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.setElementAt("3", 2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetElementAtOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.setElementAt("1", 0);
    }

    @Test
    public void testSubListSetElementAtAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setElementAt("0", 0);

        assertEquals("First element should be updated", "0", subList.get(0));
        assertEquals("Second element should remain unchanged", "2", subList.get(1));
        assertEquals("Third element should remain unchanged", "3", subList.get(2));
        assertEquals("Vector first element should be updated", "0", vector.get(0));
    }

    @Test
    public void testSubListSetElementAtAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setElementAt("4", 2);

        assertEquals("Last element should be updated", "4", subList.get(2));
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Second element should remain unchanged", "2", subList.get(1));
        assertEquals("Vector last element should be updated", "4", vector.get(2));
    }

    @Test
    public void testSubListSetElementAtNull()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setElementAt(null, 1);

        assertNull("Element at index 1 should be null", subList.get(1));
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Third element should remain unchanged", "3", subList.get(2));
        assertNull("Vector element at index 1 should be null", vector.get(1));
    }

    @Test
    public void testSubListSetElementAtMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.setElementAt("4", 1);
        subList.setElementAt("5", 1);
        subList.setElementAt("6", 1);

        assertEquals("Element at index 1 should be last set value", "6", subList.get(1));
        assertEquals("Size should remain unchanged", 3, subList.size());
        assertEquals("Vector element at index 1 should be updated", "6", vector.get(1));
    }

    @Test
    public void testSubListSetElementAtWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(3, 4);
        subList.setElementAt("4", 0);

        assertEquals("First element with offset should be updated", "4", subList.get(0));
    }

    @Test
    public void testSubListRemoveElementAtValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.removeElementAt(1);

        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should be shifted", "4", subList.get(1));
        assertEquals("Vector size should be decreased", 3, vector.size());
        assertEquals("Vector element should be shifted", "4", vector.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.removeElementAt(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.removeElementAt(3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveElementAtOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.removeElementAt(0);
    }

    @Test
    public void testSubListRemoveElementAtAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.removeElementAt(0);

        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should be shifted", "2", subList.get(0));
        assertEquals("Second element should be shifted", "3", subList.get(1));
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Vector first element should be shifted", "2", vector.get(0));
    }

    @Test
    public void testSubListRemoveElementAtAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.removeElementAt(2);

        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Vector elements should remain", "2", vector.get(1));
    }

    @Test
    public void testSubListRemoveElementAtNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.removeElementAt(1);

        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should be shifted", "3", subList.get(1));
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Vector element should be shifted", "3", vector.get(1));
    }

    @Test
    public void testSubListRemoveElementAtMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.removeElementAt(1);
        subList.removeElementAt(1);

        assertEquals("Size should be decreased to 1", 1, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Vector size should be decreased", 3, vector.size());
        assertEquals("Vector elements should be properly shifted", "5", vector.get(2));
    }

    @Test
    public void testSubListRemoveElementAtWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        subList.removeElementAt(0);

        assertEquals("Size should be decreased", 1, subList.size());
        assertEquals("First element should be shifted", "3", subList.get(0));
        assertEquals("Vector size should be decreased", 3, vector.size());
        assertEquals("Vector elements should be properly shifted", "3", vector.get(2));
    }

    @Test
    public void testSubListInsertElementAtValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.insertElementAt("2", 1);

        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Inserted element should be at index 1", "2", subList.get(1));
        assertEquals("Second element should be shifted right", "3", subList.get(2));
        assertEquals("Third element should be shifted right", "4", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListInsertElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.insertElementAt("3", -1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListInsertElementAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.insertElementAt("3", 3);
    }

    @Test
    public void testSubListInsertElementAtEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.insertElementAt("3", 0);

        assertEquals("Size should be 1 after insertion", 1, subList.size());
        assertEquals("Inserted element should be at index 0", "3", subList.get(0));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListInsertElementAtAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.insertElementAt("1", 0);

        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("Inserted element should be at beginning", "1", subList.get(0));
        assertEquals("First element should be shifted right", "2", subList.get(1));
        assertEquals("Second element should be shifted right", "3", subList.get(2));
        assertEquals("Third element should be shifted right", "4", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListInsertElementAtAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.insertElementAt("4", 3);

        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should remain", "3", subList.get(2));
        assertEquals("Inserted element should be at end", "4", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListInsertElementAtNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.insertElementAt(null, 1);

        assertEquals("Size should be increased", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertNull("Inserted element should be null", subList.get(1));
        assertEquals("Third element should be shifted right", "3", subList.get(2));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListInsertElementAtMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.insertElementAt("2", 1);
        subList.insertElementAt("3", 2);

        assertEquals("Size should be 4", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second inserted element should be at index 1", "2", subList.get(1));
        assertEquals("First inserted element should be at index 2", "3", subList.get(2));
        assertEquals("Original element should be shifted", "4", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListInsertElementAtWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.insertElementAt("2", 1);
        subList.insertElementAt("3", 2);

        assertEquals("Size should be 4", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("First inserted element should be at index 1", "2", subList.get(1));
        assertEquals("Second inserted element should be at index 2", "3", subList.get(2));
        assertEquals("Original element should be shifted", "4", subList.get(3));
        assertEquals("Vector size should be increased", 6, vector.size());
        assertEquals("Element before sublist should remain", "0", vector.get(0));
        assertEquals("Element after sublist should be shifted", "5", vector.get(5));
    }

    @Test
    public void testSubListAddElementToNonEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.addElement("4");

        assertEquals("Size should be 3 after adding element", 3, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should remain", "3", subList.get(1));
        assertEquals("Third element should be added", "4", subList.get(2));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddElementToEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.addElement("4");

        assertEquals("Size should be 1 after adding element", 1, subList.size());
        assertEquals("Element should be added at index 0", "4", subList.get(0));
        assertEquals("Vector size should be increased", 4, vector.size());
        assertFalse("SubList should not be empty", subList.isEmpty());
    }

    @Test
    public void testSubListAddElementNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.addElement(null);

        assertEquals("Size should be 3 after adding null", 3, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should remain", "3", subList.get(1));
        assertNull("Third element should be null", subList.get(2));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddElementWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        subList.addElement("4");

        assertEquals("Size should be 3 after adding element", 3, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should remain", "3", subList.get(1));
        assertEquals("Third element should be added", "4", subList.get(2));
        assertEquals("Vector size should be increased", 5, vector.size());
        assertEquals("Element after sublist should be shifted", "3", vector.get(3));
        assertEquals("New element should be at correct position", "4", vector.get(4));
    }

    @Test
    public void testSubListRemoveElementExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertTrue("Should return true for existing element", subList.removeElement("2"));
        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should remain", "3", subList.get(0));
        assertEquals("Second element should be shifted", "4", subList.get(1));
        assertEquals("Vector size should be decreased", 3, vector.size());
    }

    @Test
    public void testSubListRemoveElementNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertFalse("Should return false for non-existing element", subList.removeElement("1"));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should remain", "3", subList.get(1));
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListRemoveElementNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertTrue("Should return true for null element", subList.removeElement(null));
        assertEquals("Size should be decreased", 0, subList.size());
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
        assertEquals("Element after subList should be shifted", "3", vector.get(1));
    }

    @Test
    public void testSubListRemoveElementFromEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        assertFalse("Should return false for empty subList", subList.removeElement("2"));
        assertEquals("Size should remain 0", 0, subList.size());
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListRemoveElementFirstOccurrenceOnly()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertTrue("Should return true for existing element", subList.removeElement("2"));
        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First occurrence should be removed", "2", subList.get(0));
        assertEquals("Second element should be shifted", "3", subList.get(1));
        assertEquals("Vector size should be decreased", 3, vector.size());
    }

    @Test
    public void testSubListRemoveElementWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(3, 4);
        assertTrue("Should return true for existing element", subList.removeElement("3"));
        assertEquals("Size should be decreased", 0, subList.size());
        assertEquals("Vector size should be decreased", 3, vector.size());
        assertEquals("Element before subList should remain", "0", vector.get(0));
        assertEquals("Element in subList range should be shifted", "1", vector.get(1));
        assertEquals("Element after subList should be shifted", "2", vector.get(2));
    }

    @Test
    public void testSubListRemoveAllElementsEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.removeAllElements();

        assertEquals("Size should remain 0", 0, subList.size());
        assertTrue("SubList should remain empty", subList.isEmpty());
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListRemoveAllElementsMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be decreased", 1, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
    }

    @Test
    public void testSubListRemoveAllElementsNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.removeAllElements();

        assertEquals("Size should be 0 after removal", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be decreased", 1, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
    }

    @Test
    public void testSubListCloneEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        SubListAdapter cloned = (SubListAdapter) subList.clone();

        assertNotNull("Clone should not be null", cloned);
        assertEquals("Clone size should match original", subList.size(), cloned.size());
        assertTrue("Clone should be empty", cloned.isEmpty());
    }

    @Test
    public void testSubListCloneMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        SubListAdapter cloned = (SubListAdapter) subList.clone();

        assertNotNull("Clone should not be null", cloned);
        assertEquals("Clone size should match original", subList.size(), cloned.size());

        for(int i = 0; i < subList.size(); i++)
            assertEquals("Element " + i + " should match", subList.get(i), cloned.get(i));
    }

    @Test
    public void testSubListCloneNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        subList = (SubListAdapter) vector.subList(0, 3);
        SubListAdapter cloned = (SubListAdapter) subList.clone();

        assertNotNull("Clone should not be null", cloned);
        assertEquals("Clone size should match original", subList.size(), cloned.size());
        assertNull("Null element should be preserved", cloned.get(0));
        assertNull("Null element should be preserved", cloned.get(1));
        assertNull("Null element should be preserved", cloned.get(2));
    }

    @Test
    public void testSubListCloneIndependence()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        SubListAdapter cloned = (SubListAdapter) subList.clone();

        subList.set(0, "4");
        cloned.set(0, "5");

        assertEquals("Clone should not be affected by original modification", "5", cloned.get(0));
        assertEquals("Original should be modified", "4", subList.get(0));
    }

    @Test
    public void testSubListCloneWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        SubListAdapter cloned = (SubListAdapter) subList.clone();

        assertNotNull("Clone should not be null", cloned);
        assertEquals("Clone size should match original", subList.size(), cloned.size());
        assertEquals("First element should match with offset", "2", cloned.get(0));
        assertEquals("Second element should match with offset", "3", cloned.get(1));
    }

    @Test
    public void testSubListToArrayEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        Object[] arr = subList.toArray();

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should match subList size", 0, arr.length);
    }

    @Test
    public void testSubListToArrayMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        Object[] arr = subList.toArray();

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should match subList size", 2, arr.length);
        assertEquals("First array element should match", "1", arr[0]);
        assertEquals("Second array element should match", "2", arr[1]);
    }

    @Test
    public void testSubListToArrayNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        Object[] arr = subList.toArray();

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should match subList size", 2, arr.length);
        assertEquals("Array element should be '1'", "1", arr[0]);
        assertNull("Array element should be null", arr[1]);
    }

    @Test
    public void testSubListToArrayWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        Object[] arr = subList.toArray();

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should match subList size", 2, arr.length);
        assertEquals("First array element should match with offset", "2", arr[0]);
        assertEquals("Second array element should match with offset", "3", arr[1]);
    }

        @Test
    public void testSubListToArrayWithReferenceEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        Object[] array = new Object[0];
        Object[] arr = subList.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertEquals("Array length should be 0", 0, arr.length);
    }

    @Test
    public void testSubListToArrayWithReferenceExactSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        Object[] array = new Object[2];
        Object[] arr = subList.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertEquals("First array element should match", "1", arr[0]);
        assertEquals("Second array element should match", "2", arr[1]);
    }

    @Test
    public void testSubListToArrayWithReferenceLargerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        Object[] array = new Object[5];
        array[2] = "0";
        array[3] = "0";
        array[4] = "0";

        Object[] arr = subList.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertEquals("First array element should match", "2", arr[0]);
        assertEquals("Second array element should match", "3", arr[1]);
        assertNull("Element after subList content should be null", arr[2]);
        assertEquals("Existing elements should be preserved", "0", arr[3]);
        assertEquals("Existing elements should be preserved", "0", arr[4]);
    }

    @Test
    public void testSubListToArrayWithReferenceSmallerArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        Object[] array = new Object[2];
        Object[] arr = subList.toArray(array);

        assertNotSame("Should return a different array", array, arr);
        assertEquals("New array length should match subList size", 3, arr.length);
        assertEquals("First array element should match", "1", arr[0]);
        assertEquals("Second array element should match", "2", arr[1]);
        assertEquals("Third array element should match", "3", arr[2]);
    }

    @Test
    public void testSubListToArrayWithReferenceNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        Object[] array = new Object[5];
        Object[] arr = subList.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertEquals("First array element should match", "1", arr[0]);
        assertNull("Second array element should be null", arr[1]);
        assertEquals("Third array element should match", "3", arr[2]);
        assertNull("Element after subList content should be null", arr[3]);
    }

    @Test(expected = NullPointerException.class)
    public void testSubListToArrayWithReferenceNullArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.toArray(null);
    }

    @Test
    public void testSubListToArrayWithReferenceEmptySubListNullTermination()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        Object[] array = new Object[3];
        array[0] = "0";
        array[1] = "0";
        array[2] = "0";

        Object[] arr = subList.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertNull("First element should be null for empty subList", arr[0]);
        assertEquals("Second element should remain unchanged", "0", arr[1]);
        assertEquals("Third element should remain unchanged", "0", arr[2]);
    }

    @Test
    public void testSubListToArrayWithReferenceZeroLengthArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        Object[] array = new Object[0];
        Object[] arr = subList.toArray(array);

        assertNotSame("Should return a different array", array, arr);
        assertEquals("New array length should match subList size", 2, arr.length);
        assertEquals("First array element should match", "2", arr[0]);
        assertEquals("Second array element should match", "3", arr[1]);
    }

    @Test
    public void testSubListToArrayWithReferenceWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        Object[] array = new Object[3];
        Object[] arr = subList.toArray(array);

        assertSame("Should return the same array", array, arr);
        assertEquals("First array element should match with offset", "2", arr[0]);
        assertEquals("Second array element should match with offset", "3", arr[1]);
        assertNull("Element after subList content should be null", arr[2]);
    }

    @Test
    public void testSubListGetValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return element at index 0", "2", subList.get(0));
        assertEquals("Should return element at index 1", "3", subList.get(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListGetNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.get(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListGetIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.get(2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListGetOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.get(0);
    }

    @Test
    public void testSubListGetNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertNull("Should return null element at index 0", subList.get(0));
    }

    @Test
    public void testSubListGetWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(3, 4);
        assertEquals("Should return correct element with offset", "3", subList.get(0));
    }

    @Test
    public void testSubListSetValidIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertEquals("Should return previous element at index 1", "2", subList.set(0, "4"));
        assertEquals("Element at index 0 should be updated", "4", subList.get(0));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("Second element should remain unchanged", "3", subList.get(1));
        assertEquals("Original vector should be updated", "4", vector.get(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.set(-1, "3");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.set(2, "3");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.set(0, "1");
    }

    @Test
    public void testSubListSetAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertEquals("Should return previous first element", "1", subList.set(0, "0"));
        assertEquals("First element should be updated", "0", subList.get(0));
        assertEquals("Second element should remain unchanged", "2", subList.get(1));
        assertEquals("Third element should remain unchanged", "3", subList.get(2));
        assertEquals("Original vector should be updated", "0", vector.get(0));
    }

    @Test
    public void testSubListSetAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertEquals("Should return previous last element", "3", subList.set(2, "4"));
        assertEquals("Last element should be updated", "4", subList.get(2));
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Second element should remain unchanged", "2", subList.get(1));
        assertEquals("Original vector should be updated", "4", vector.get(2));
    }

    @Test
    public void testSubListSetNull()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertEquals("Should return previous element", "2", subList.set(1, null));
        assertNull("Element at index 1 should be null", subList.get(1));
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Third element should remain unchanged", "3", subList.get(2));
        assertNull("Original vector should be updated to null", vector.get(1));
    }

    @Test
    public void testSubListSetMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.set(1, "4");
        subList.set(1, "5");
        subList.set(1, "6");

        assertEquals("Element at index 1 should be last set value", "6", subList.get(1));
        assertEquals("Size should remain unchanged", 3, subList.size());
        assertEquals("Original vector should be updated", "6", vector.get(1));
    }

    @Test
    public void testSubListSetWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return previous element with offset", "2", subList.set(0, "4"));
        assertEquals("First element should be updated with offset", "4", subList.get(0));
        assertEquals("Second element should remain unchanged", "3", subList.get(1));
        assertEquals("Original vector should be updated", "4", vector.get(2));
    }

    @Test
    public void testSubListAddToNonEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        assertTrue("Should return true", subList.add("3"));
        assertEquals("Size should be 3 after adding elements", 3, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should be added", "3", subList.get(2));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListAddToEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        assertTrue("Should return true", subList.add("3"));
        assertEquals("Size should be 1 after adding element", 1, subList.size());
        assertEquals("Element should be added at index 0", "3", subList.get(0));
        assertFalse("SubList should not be empty", subList.isEmpty());
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListAddNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        assertTrue("Should return true", subList.add(null));
        assertEquals("Size should be 3 after adding null", 3, subList.size());
        assertNull("Element should be null", subList.get(2));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListAddWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertTrue("Should return true", subList.add("4"));
        assertEquals("Size should be 3 after adding element", 3, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should remain", "3", subList.get(1));
        assertEquals("Third element should be added", "4", subList.get(2));
        assertEquals("Vector size should be increased", 5, vector.size());
        assertEquals("Element after sublist should be shifted", "3", vector.get(3));
        assertEquals("New element should be at correct position", "4", vector.get(4));
    }

    @Test
    public void testSubListAddToEmptyVectorSubList()
    {
        vector = new VectorAdapter();

        subList = (SubListAdapter) vector.subList(0, 0);
        assertTrue("Should return true", subList.add("1"));

        assertEquals("Size should be 1 after addition", 1, subList.size());
        assertEquals("Element should be added", "1", subList.get(0));
        assertEquals("Vector size should be 1", 1, vector.size());
    }

    @Test
    public void testSubListRemoveExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertTrue("Should return true for existing element", subList.remove("2"));
        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should remain", "3", subList.get(0));
        assertEquals("Second element should be shifted", "4", subList.get(1));
        assertEquals("Vector size should be decreased", 3, vector.size());
    }

    @Test
    public void testSubListRemoveNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        assertFalse("Should return false for non-existing element", subList.remove("1"));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should remain", "3", subList.get(1));
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListRemoveNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 2);
        assertTrue("Should return true for null element", subList.remove(null));
        assertEquals("Size should be decreased", 0, subList.size());
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
        assertEquals("Element after subList should be shifted", "3", vector.get(1));
    }

    @Test
    public void testSubListRemoveFromEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        assertFalse("Should return false for empty subList", subList.remove("2"));
        assertEquals("Size should remain 0", 0, subList.size());
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListRemoveFirstOccurrenceOnly()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 4);
        assertTrue("Should return true for existing element", subList.remove("2"));
        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First occurrence should be removed", "2", subList.get(0));
        assertEquals("Second element should be shifted", "3", subList.get(1));
        assertEquals("Vector size should be decreased", 3, vector.size());
    }

    @Test
    public void testSubListRemoveWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(3, 4);
        assertTrue("Should return true for existing element", subList.remove("3"));
        assertEquals("Size should be decreased", 0, subList.size());
        assertEquals("Vector size should be decreased", 3, vector.size());
        assertEquals("Element before subList should remain", "0", vector.get(0));
        assertEquals("Element in subList range should be shifted", "1", vector.get(1));
        assertEquals("Element after subList should be shifted", "2", vector.get(2));
    }

    @Test
    public void testSubListAddAtIndexValidPosition()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add(1, "2");

        assertEquals("Size should be 3 after insertion", 3, subList.size());
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Inserted element should be at index 1", "2", subList.get(1));
        assertEquals("Third element should be shifted right", "3", subList.get(2));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListAddAtIndexAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add(0, "1");

        assertEquals("Size should be 3 after insertion", 3, subList.size());
        assertEquals("Inserted element should be at beginning", "1", subList.get(0));
        assertEquals("First element should be shifted right", "2", subList.get(1));
        assertEquals("Second element should be shifted right", "3", subList.get(2));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListAddAtIndexAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add(2, "3");

        assertEquals("Size should be 3 after insertion", 3, subList.size());
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Second element should remain unchanged", "2", subList.get(1));
        assertEquals("Inserted element should be at end", "3", subList.get(2));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListAddAtIndexEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.add(0, "3");

        assertEquals("Size should be 1 after insertion", 1, subList.size());
        assertEquals("Inserted element should be at index 0", "3", subList.get(0));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListAddAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add(-1, "3");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListAddAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add(3, "3");
    }

    @Test
    public void testSubListAddAtIndexNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add(1, null);

        assertEquals("Size should be 3 after insertion", 3, subList.size());
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertNull("Inserted element should be null", subList.get(1));
        assertEquals("Third element should be shifted right", "3", subList.get(2));
        assertEquals("Vector size should be increased", 3, vector.size());
    }

    @Test
    public void testSubListAddAtIndexWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.add(1, "2");
        subList.add(2, "3");

        assertEquals("Size should be 4 after insertions", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("First inserted element should be at index 1", "2", subList.get(1));
        assertEquals("Second inserted element should be at index 2", "3", subList.get(2));
        assertEquals("Original element should be shifted", "4", subList.get(3));
        assertEquals("Vector size should be increased", 6, vector.size());
        assertEquals("Element before sublist should remain", "0", vector.get(0));
        assertEquals("Element after sublist should be shifted", "5", vector.get(5));
    }

    @Test
    public void testSubListRemoveAtIndexValidPosition()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertEquals("Should return removed element", "2", subList.remove(1));
        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Third element should be shifted left", "3", subList.get(1));
        assertEquals("Vector size should be decreased", 2, vector.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.remove(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.remove(3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveAtIndexOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.remove(0);
    }

    @Test
    public void testSubListRemoveAtIndexAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertEquals("Should return first element", "1", subList.remove(0));
        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("Second element should become first", "2", subList.get(0));
        assertEquals("Third element should become second", "3", subList.get(1));
        assertEquals("Vector size should be decreased", 2, vector.size());
    }

    @Test
    public void testSubListRemoveAtIndexAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertEquals("Should return last element", "3", subList.remove(2));
        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Second element should remain unchanged", "2", subList.get(1));
        assertEquals("Vector size should be decreased", 2, vector.size());
    }

    @Test
    public void testSubListRemoveAtIndexNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertNull("Should return null element", subList.remove(1));
        assertEquals("Size should be decreased", 2, subList.size());
        assertEquals("First element should remain unchanged", "1", subList.get(0));
        assertEquals("Third element should be shifted left", "3", subList.get(1));
        assertEquals("Vector size should be decreased", 2, vector.size());
    }

    @Test
    public void testSubListRemoveAtIndexMultipleTimes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);
        subList.remove(1);
        subList.remove(1);

        assertEquals("Size should be decreased to 1", 1, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Vector size should be decreased", 3, vector.size());
    }

    @Test
    public void testSubListRemoveAtIndexWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        assertEquals("Should return element with offset", "2", subList.remove(0));
        assertEquals("Size should be decreased", 1, subList.size());
        assertEquals("First element should be shifted", "3", subList.get(0));
        assertEquals("Vector size should be decreased", 3, vector.size());
    }

    // CONTINUARE DA CLEAR
}
