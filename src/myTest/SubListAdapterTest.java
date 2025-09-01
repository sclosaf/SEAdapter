package myTest;

import myAdapter.SubListAdapter;
import myAdapter.VectorAdapter;

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
}
