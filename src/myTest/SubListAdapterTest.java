package myTest;

import myAdapter.SubListAdapter;
import myAdapter.VectorAdapter;
import myAdapter.HCollection;
import myAdapter.HList;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Test suite for the SubListAdapter class.
 *
 * <p>This test suite verifies the functionality of the SubListAdapter class,
 * which provides a view of a portion of a VectorAdapter or another SubListAdapter.
 * The tests cover various scenarios including construction, element access,
 * modification, and compliance with the HList and HCollection interfaces.</p>
 *
 * <h2>Test Case Design</h2>
 * <p>The test cases are designed to validate the behavior of SubListAdapter
 * against the specifications of Java 1.4.2 Vector class subList functionality.
 * The test design includes categories for basic functionality, edge cases,
 * error conditions, and interaction with the backing vector.</p>
 *
 * <p>Categories:</p>
 * <ul>
 *   <li>Constructor and initialization tests</li>
 *   <li>Element access and modification tests</li>
 *   <li>Size and capacity management tests</li>
 *   <li>Collection operation tests</li>
 *   <li>Iterator functionality tests</li>
 *   <li>Edge case and exception handling tests</li>
 * </ul>
 *
 * @author   Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see      HCollection
 * @see      HList
 * @see      VectorAdapter
 * @see      SubListAdapter
 */
public class SubListAdapterTest
{
    /**
     * The main VectorAdapter instance used as backing vector for testing.
     * Initialized before each test method execution.
     */
    private VectorAdapter vector;

    /**
     * The SubListAdapter instance being tested.
     * Initialized before each test method execution.
     */
    private SubListAdapter subList;

    /**
     * Sets up the testing environment before each test method execution.
     * Initializes the vector and subList instances to null to ensure the use of a clean state.
     */
    @Before
    public void setUp()
    {
        vector = null;
        subList = null;
    }

    /**
     * Cleans up test environment after each test method execution.
     * Sets the vector and subList instances to null to assist garbage collection.
     */
    @After
    public void tearDown()
    {
        vector = null;
        subList = null;
    }

    /**
     * Test method to verify that subList creation works correctly with valid parameters.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a sublist can be created with valid indices and contains the correct elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic sublist functionality with normal parameters,
     * ensuring proper element selection and size calculation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with 5 elements, then creates a sublist from indices 1 to 4.
     * It verifies that the sublist contains the correct elements at the expected positions
     * and has the correct size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with size 3</p>
     * <p>- Sublist contains elements ["2", "3", "4"] at indices 0, 1, 2 respectively</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublist should be created successfully with correct elements and size.</p>
     */
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

    /**
     * Test method to verify that subList creation works correctly for a single element range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a single-element sublist can be created and contains the correct element.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sublist functionality for the edge case of a single element range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with 3 elements, then creates a sublist from indices 1 to 2.
     * It verifies that the sublist contains exactly one element at position 0.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with size 1</p>
     * <p>- Sublist contains element "2" at index 0</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublist should be created successfully with one correct element.</p>
     */
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

    /**
     * Test method to verify that subList creation works correctly for an empty range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that an empty sublist can be created and has the correct properties.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sublist functionality for the edge case of an empty range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with 3 elements, then creates a sublist from indices 1 to 1.
     * It verifies that the sublist is empty and has size 0.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with size 0</p>
     * <p>- Sublist is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Empty sublist should be created successfully.</p>
     */
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

    /**
     * Test method to verify that subList creation works correctly for a full range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a sublist covering the entire vector can be created correctly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sublist functionality for the case of a full range selection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with 3 elements, then creates a sublist from indices 0 to 3.
     * It verifies that the sublist contains all elements in the correct order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with size 3</p>
     * <p>- Sublist contains all original elements in order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Full-range sublist should be created successfully with all elements preserved.</p>
     */
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

    /**
     * Test method to verify that subList creation works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a sublist containing null elements can be created and handled correctly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sublist functionality with null values,
     * ensuring proper handling of null elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with 4 null elements, then creates a sublist from indices 1 to 3.
     * It verifies that the sublist contains the correct number of null elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 null elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with size 2</p>
     * <p>- Sublist contains two null elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublist with null elements should be created successfully.</p>
     */
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

    /**
     * Test method to verify that subList constructor throws IllegalArgumentException when fromIndex bigger than toIndex.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies proper exception handling for invalid index parameters.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of fromIndex bigger than toIndex.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a sublist with fromIndex 2 and toIndex 1,
     * expecting an IllegalArgumentException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalArgumentException is thrown</p>
     * <p>- No sublist is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IllegalArgumentException should be thrown when fromIndex bigger than toIndex.</p>
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubListConstructorFromIndexGreaterThanToIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(2, 1);
    }

    /**
     * Test method to verify that subList constructor throws IndexOutOfBoundsException for negative fromIndex.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies proper exception handling for negative index parameters.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of negative fromIndex.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a sublist with fromIndex -1 and toIndex 1,
     * expecting an IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- No sublist is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IndexOutOfBoundsException should be thrown for negative fromIndex.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListConstructorNegativeFromIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(-1, 1);
    }

    /**
     * Test method to verify that subList constructor throws IndexOutOfBoundsException for toIndex bigger than size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies proper exception handling for out-of-bounds index parameters.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of toIndex exceeding vector size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a sublist with fromIndex 0 and toIndex 3 on a vector of size 2,
     * expecting an IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- No sublist is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IndexOutOfBoundsException should be thrown when toIndex bigger than the vector size.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListConstructorToIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 3);
    }

    /**
     * Test method to verify that subList constructor works correctly with default VectorAdapter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies sublist creation with a default-constructed VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sublist functionality with the default vector constructor.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter using the default constructor, adds elements,
     * then creates a sublist and verifies its properties match the backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is created with default constructor</p>
     * <p>- Three elements are added: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with correct elements</p>
     * <p>- Capacity matches backing vector capacity</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublist should be created successfully with matching capacity.</p>
     */
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

    /**
     * Test method to verify that subList constructor works correctly with capacity-specified VectorAdapter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies sublist creation with a capacity-specified VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sublist functionality with a vector created with specific capacity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 5, adds elements,
     * then creates a sublist and verifies its properties match the backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is created with initial capacity 5</p>
     * <p>- Three elements are added: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with correct elements</p>
     * <p>- Capacity matches backing vector capacity</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublist should be created successfully with matching capacity.</p>
     */
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

    /**
     * Test method to verify that subList constructor works correctly with capacity-and-increment VectorAdapter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies sublist creation with a VectorAdapter created with capacity and increment.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sublist functionality with a vector created with specific capacity and increment.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 3 and capacity increment 2,
     * adds elements beyond initial capacity, then creates a sublist and verifies its properties.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is created with capacity 3 and increment 2</p>
     * <p>- Four elements are added: ["1", "2", "3", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with correct elements</p>
     * <p>- Capacity matches expanded backing vector capacity</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublist should be created successfully with matching expanded capacity.</p>
     */
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

    /**
     * Test method to verify that subList constructor works correctly with collection-based VectorAdapter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies sublist creation with a VectorAdapter created from another collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate sublist functionality with a vector created from an existing collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter from another VectorAdapter, then creates a sublist
     * and verifies its properties match the backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Source VectorAdapter is created with elements ["1", "2", "3"]</p>
     * <p>- Target VectorAdapter is created from source collection</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubListAdapter is successfully created with correct elements</p>
     * <p>- Capacity matches backing vector capacity</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublist should be created successfully with matching capacity.</p>
     */
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

    /**
     * Test method to verify that copyInto copies elements in correct order.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto method copies sublist elements in the correct order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the element ordering preservation in copyInto operation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with 5 elements, creates a sublist,
     * and verifies that copyInto copies elements in the correct sequential order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- Sublist is created from indices 1 to 4</p>
     * <p>- Target array of size 3 is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Array contains elements ["2", "3", "4"] in positions 0, 1, 2 respectively</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>copyInto should copy elements in correct sequential order.</p>
     */
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

    /**
     * Test method to verify that copyInto works correctly with empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto method handles empty sublists correctly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate copyInto operation with empty sublists.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty sublist and verifies that copyInto doesn't modify
     * the target empty array.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- Empty sublist is created from indices 1 to 1</p>
     * <p>- Empty target array is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Array remains empty and unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>copyInto should not modify empty target array when copying from empty sublist.</p>
     */
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

    /**
     * Test method to verify that copyInto preserves existing array elements beyond copied range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto preserves array elements beyond the copied range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that copyInto doesn't overwrite array elements
     * beyond the copied sublist range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a sublist and copies it into an array with pre-existing elements,
     * verifying that elements beyond the copied range remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- Sublist is created from indices 1 to 3</p>
     * <p>- Target array of size 3 has pre-existing elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Array positions 0 and 1 contain copied elements</p>
     * <p>- Array position 2 retains original value</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>copyInto should preserve array elements beyond copied range.</p>
     */
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

    /**
     * Test method to verify that copyInto throws IndexOutOfBoundsException for smaller target array.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies proper exception handling when target array is too small.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for array size mismatch.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to copy a sublist into a smaller array,
     * expecting an IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- Sublist is created from indices 1 to 4 (size 3)</p>
     * <p>- Target array of size 2 is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IndexOutOfBoundsException should be thrown when target array is smaller than sublist.</p>
     */
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

    /**
     * Test method to verify that copyInto handles null elements correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto correctly handles sublists containing null elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate null element handling in copyInto operation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a sublist containing null elements and verifies that
     * copyInto correctly copies null values to the target array.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with mixed null and non-null elements</p>
     * <p>- Sublist is created containing null elements</p>
     * <p>- Target array has pre-existing values</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Array contains null values at copied positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>copyInto should correctly copy null elements to target array.</p>
     */
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

    /**
     * Test method to verify that copyInto throws NullPointerException for null array.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies proper exception handling for null array parameter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for null array reference.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to copy a sublist into a null array,
     * expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- Sublist is created from indices 0 to 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>NullPointerException should be thrown when target array is null.</p>
     */
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

    /**
     * Test method to verify that copyInto throws IndexOutOfBoundsException for zero-length array with elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies proper exception handling for zero-length array with non-empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for array size mismatch with zero-length array.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to copy a non-empty sublist into a zero-length array,
     * expecting an IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- Sublist is created from indices 0 to 1 (size 1)</p>
     * <p>- Zero-length target array is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IndexOutOfBoundsException should be thrown when target array is too small.</p>
     */
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
    public void testSubListIsEmptyOnEmptySubList()
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
    public void testSubListElementAtNullElement()
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

    @Test
    public void testSubListClearOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.clear();

        assertEquals("Size should be 0 after clearing empty subList", 0, subList.size());
        assertTrue("SubList should remain empty", subList.isEmpty());
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListClearMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.clear();

        assertEquals("Size should be 0 after clearing", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
        assertEquals("Element after subList should be shifted", "4", vector.get(1));
    }

    @Test
    public void testSubListClearNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.clear();

        assertEquals("Size should be 0 after clearing", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
        assertNull("Element after subList should be shifted", vector.get(1));
    }

    @Test
    public void testSubListClearFullRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        subList.clear();

        assertEquals("Size should be 0 after clearing", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be 0", 0, vector.size());
    }

    @Test
    public void testSubListClearWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        subList.clear();

        assertEquals("Size should be 0 after clearing", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "0", vector.get(0));
        assertEquals("Element after subList should be shifted", "1", vector.get(1));
    }

    @Test
    public void testSubListContainsAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();

        assertTrue("Should return true for empty collection", subList.containsAll(vec));
    }

    @Test
    public void testSubListContainsAllEveryElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true when all elements are present", subList.containsAll(vec));
    }

    @Test
    public void testSubListContainsAllSomeElementsMissing()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("4");

        assertFalse("Should return false when some elements are missing", subList.containsAll(vec));
    }

    @Test
    public void testSubListContainsAllEveryElementsMissing()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");

        assertFalse("Should return false when all elements are missing", subList.containsAll(vec));
    }

    @Test
    public void testSubListContainsAllSameElementsDifferentOrder()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add("3");
        vec.add("2");

        assertTrue("Should return true when all elements are present", subList.containsAll(vec));
    }

    @Test
    public void testSubListContainsAllDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true when subList has duplicates", subList.containsAll(vec));
    }

    @Test
    public void testSubListContainsAllNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add("3");

        assertTrue("Should return true for null elements", subList.containsAll(vec));
    }

    @Test
    public void testSubListContainsAllEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertFalse("Should return false for empty subList", subList.containsAll(vec));
    }

    @Test(expected = NullPointerException.class)
    public void testSubListContainsAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.containsAll(null);
    }

    @Test
    public void testSubListContainsAllSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertTrue("Should return true when collection is itself", subList.containsAll(subList));
    }

    @Test
    public void testSubListContainsAllElementsOutsideRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("4");

        assertFalse("Should return false for elements outside subList range", subList.containsAll(vec));
    }

    @Test
    public void testSubListContainsAllWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true for elements with offset", subList.containsAll(vec));
    }

    @Test
    public void testSubListAddAllNonEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();
        vec.add("3");
        vec.add("4");

        assertTrue("Should return true for non-empty collection", subList.addAll(vec));
        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should be added", "3", subList.get(2));
        assertEquals("Fourth element should be added", "4", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();

        assertFalse("Should return false for empty collection", subList.addAll(vec));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("Vector size should remain unchanged", 2, vector.size());
    }

    @Test
    public void testSubListAddAllToEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        VectorAdapter vec = new VectorAdapter();
        vec.add("3");
        vec.add("4");

        assertTrue("Should return true", subList.addAll(vec));
        assertEquals("Size should match collection size", 2, subList.size());
        assertEquals("First element should be added", "3", subList.get(0));
        assertEquals("Second element should be added", "4", subList.get(1));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddAllNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);

        assertTrue("Should return true", subList.addAll(vec));
        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertNull("Third element should be null", subList.get(2));
        assertNull("Fourth element should be null", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test(expected = NullPointerException.class)
    public void testSubListAddAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.addAll(null);
    }

    @Test
    public void testSubListAddAllToSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        assertTrue("Should return true when adding itself", subList.addAll(subList));
        assertEquals("Size should be doubled", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("First element should be added again", "1", subList.get(2));
        assertEquals("Second element should be added again", "2", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddAllWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(2, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true", subList.addAll(vec));
        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "4", subList.get(0));
        assertEquals("Second element should remain", "5", subList.get(1));
        assertEquals("Third element should be added", "2", subList.get(2));
        assertEquals("Fourth element should be added", "3", subList.get(3));
        assertEquals("Vector size should be increased", 6, vector.size());
        assertEquals("Element before sublist should remain", "0", vector.get(0));
        assertEquals("Element after sublist should be shifted", "3", vector.get(5));
    }

    @Test
    public void testSubListRemoveAllEveryElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");
        vec.add("4");

        assertTrue("Should return true when all elements are removed", subList.removeAll(vec));
        assertEquals("Size should be 0", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be decreased", 1, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
    }

    @Test
    public void testSubListRemoveAllSomeElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true when elements are removed", subList.removeAll(vec));
        assertEquals("Size should be decreased", 1, subList.size());
        assertEquals("Remaining element should be '4'", "4", subList.get(0));
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
        assertEquals("Element after subList should be shifted", "4", vector.get(1));
    }

    @Test
    public void testSubListRemoveAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();

        assertFalse("Should return false for empty collection", subList.removeAll(vec));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListRemoveAllNoElementsPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");

        assertFalse("Should return false when no elements are present", subList.removeAll(vec));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test
    public void testSubListRemoveAllDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");

        assertTrue("Should return true", subList.removeAll(vec));
        assertEquals("Size should be decreased", 1, subList.size());
        assertEquals("Remaining element should be '3'", "3", subList.get(0));
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
        assertEquals("Element after subList should be shifted", "3", vector.get(1));
    }

    @Test
    public void testSubListRemoveAllNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");
        vector.add(null);

        subList = (SubListAdapter) vector.subList(1, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);

        assertTrue("Should return true", subList.removeAll(vec));
        assertEquals("Size should be decreased", 1, subList.size());
        assertEquals("Remaining element should be '3'", "3", subList.get(0));
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "1", vector.get(0));
        assertEquals("Element after subList should be shifted", "3", vector.get(1));
    }

    @Test
    public void testSubListRemoveAllEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 1);
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertFalse("Should return false for empty subList", subList.removeAll(vec));
        assertEquals("Size should remain 0", 0, subList.size());
        assertEquals("Vector size should remain unchanged", 3, vector.size());
    }

    @Test(expected = NullPointerException.class)
    public void testSubListRemoveAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);
        subList.removeAll(null);
    }

    @Test
    public void testSubListRemoveAllSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        assertTrue("Should return true when removing itself", subList.removeAll(subList));
        assertEquals("Size should be 0", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be 0", 0, vector.size());
    }

    @Test
    public void testSubListRemoveAllElementsOutsideRange()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("4");

        assertFalse("Should return false for elements outside subList range", subList.removeAll(vec));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("Vector size should remain unchanged", 4, vector.size());
    }

    @Test
    public void testSubListRemoveAllWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true for elements with offset", subList.removeAll(vec));
        assertEquals("Size should be 0", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
        assertEquals("Vector size should be decreased", 2, vector.size());
        assertEquals("Element before subList should remain", "0", vector.get(0));
        assertEquals("Element after subList should be shifted", "1", vector.get(1));
    }

    @Test
    public void testSubListRetainAllEveryElementPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        assertFalse("SubList should not change", subList.retainAll(vec));
        assertEquals("SubList size should remain 3", 3, subList.size());
    }

    @Test
    public void testSubListRetainAllSomeElementPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 4);

        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("4");

        assertTrue("SubList should change", subList.retainAll(vec));
        assertEquals("SubList size should be 2", 2, subList.size());
        assertEquals("First retained element should be '2'", "2", subList.get(0));
        assertEquals("Second retained element should be '4'", "4", subList.get(1));
    }

    @Test
    public void testSubListRetainAllNoElementPresent()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(0, 3);

        VectorAdapter vec = new VectorAdapter();
        vec.add("5");
        vec.add("6");

        assertTrue("SubList should change", subList.retainAll(vec));
        assertEquals("SubList size should be 0", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
    }

    @Test
    public void testSubListRetainAllEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);

        VectorAdapter other = new VectorAdapter();

        assertTrue("SubList should change", subList.retainAll(other));
        assertEquals("SubList should be empty", 0, subList.size());
        assertTrue("SubList should be empty", subList.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testSubListRetainAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);

        subList.retainAll(null);
    }


    @Test
    public void testSubListRetainAllWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(2, 4);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertFalse("Should return false when retaining all elements with offset", subList.retainAll(vec));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("First element should remain", "2", subList.get(0));
        assertEquals("Second element should remain", "3", subList.get(1));
        assertEquals("Vector size should remain unchanged", 4, vector.size());
    }

    @Test
    public void testSubListAddAllAtIndexNonEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(0, 2);

        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true for non-empty collection", subList.addAll(1, vec));
        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should be added", "2", subList.get(1));
        assertEquals("Third element should be added", "3", subList.get(2));
        assertEquals("Fourth element should be shifted", "4", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddAllAtIndexEmptyCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();

        assertFalse("Should return false for empty collection", subList.addAll(1, vec));
        assertEquals("Size should remain unchanged", 2, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Vector size should remain unchanged", 2, vector.size());
    }

    @Test
    public void testSubListAddAllAtIndexAtTheBeginning()
    {
        vector = new VectorAdapter();
        vector.add("3");
        vector.add("4");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertTrue("Should return true", subList.addAll(0, vec));
        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should be added", "1", subList.get(0));
        assertEquals("Second element should be added", "2", subList.get(1));
        assertEquals("Third element should be shifted", "3", subList.get(2));
        assertEquals("Fourth element should be shifted", "4", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddAllAtIndexAtTheEnd()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();
        vec.add("3");
        vec.add("4");

        assertTrue("Should return true", subList.addAll(2, vec));
        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should remain", "2", subList.get(1));
        assertEquals("Third element should be added", "3", subList.get(2));
        assertEquals("Fourth element should be added", "4", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddAllAtIndexEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        VectorAdapter vec = new VectorAdapter();
        vec.add("3");
        vec.add("4");

        assertTrue("Should return true", subList.addAll(0, vec));
        assertEquals("Size should match collection size", 2, subList.size());
        assertEquals("First element should be added", "3", subList.get(0));
        assertEquals("Second element should be added", "4", subList.get(1));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListAddAllAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();
        vec.add("3");

        subList.addAll(-1, vec);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListAddAllAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();
        vec.add("3");

        subList.addAll(3, vec);
    }

    @Test(expected = NullPointerException.class)
    public void testSubListAddAllAtIndexNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.addAll(1, null);
    }

    @Test
    public void testSubListAddAllAtIndexNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 2);
        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);

        assertTrue("Should return true", subList.addAll(1, vec));
        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertNull("Second element should be null", subList.get(1));
        assertNull("Third element should be null", subList.get(2));
        assertEquals("Fourth element should be shifted", "3", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddAllAtIndexSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        assertTrue("Should return true when adding itself", subList.addAll(1, subList));
        assertEquals("Size should be doubled", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should be added", "1", subList.get(1));
        assertEquals("Third element should be added", "2", subList.get(2));
        assertEquals("Fourth element should be shifted", "2", subList.get(3));
        assertEquals("Vector size should be increased", 4, vector.size());
    }

    @Test
    public void testSubListAddAllAtIndexWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(1, 3);
        VectorAdapter vec = new VectorAdapter();
        vec.add("2");
        vec.add("3");

        assertTrue("Should return true", subList.addAll(1, vec));
        assertEquals("Size should be increased", 4, subList.size());
        assertEquals("First element should remain", "1", subList.get(0));
        assertEquals("Second element should be added", "2", subList.get(1));
        assertEquals("Third element should be added", "3", subList.get(2));
        assertEquals("Fourth element should be shifted", "4", subList.get(3));
        assertEquals("Vector size should be increased", 6, vector.size());
        assertEquals("Element before sublist should remain", "0", vector.get(0));
        assertEquals("Element after sublist should be shifted", "5", vector.get(5));
    }

    @Test
    public void testSubListEqualsSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);

        assertTrue("Should return true for same object", subList.equals(subList));
    }

    @Test
    public void testSubListEqualsEqualSubLists()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, 3);

        assertTrue("Should return true for equal lists", subList.equals(sub));
    }

    @Test
    public void testSubListEqualsDifferentSizes()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);
        SubListAdapter sub = (SubListAdapter) vector.subList(1, 3);

        assertFalse("Should return false for different sizes", subList.equals(sub));
    }

    @Test
    public void testSubListEqualsDifferentElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("4");
        vec.add("3");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, 3);

        assertFalse("Should return false for different elements", subList.equals(sub));
    }

    @Test
    public void testSubListEqualsDifferentOrder()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("3");
        vec.add("2");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, 3);

        assertFalse("Should return false for different order", subList.equals(sub));
    }

    @Test
    public void testSubListEqualsNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add(null);
        vec.add("3");

        SubListAdapter sub = (SubListAdapter) vector.subList(0, 3);

        assertTrue("Should return true for lists with null elements", subList.equals(sub));
    }

    @Test
    public void testSubListEqualsEmptySubLists()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 0);

        VectorAdapter vec = new VectorAdapter();
        vec.add("4");
        vec.add("5");
        vec.add("6");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, 0);

        assertTrue("Should return true for empty sublists", subList.equals(sub));
    }

    @Test
    public void testSubListEqualsNonListObject()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(1, 3);

        assertFalse("Should return false for non-list object", subList.equals("1"));
    }

    @Test
    public void testSubListEqualsWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(2, 5);

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");
        vec.add("4");
        vec.add("5");

        SubListAdapter sub = (SubListAdapter) vec.subList(2, 5);

        assertTrue("Should return true for subLists with offset", subList.equals(sub));
    }

    @Test
    public void testSubListHashCodeOfEmptySubList()
    {
        vector = new VectorAdapter();
        subList = (SubListAdapter) vector.subList(0, 0);

        assertEquals("Hash code of empty vector should match empty vector", vector.subList(0, 0).hashCode(), subList.hashCode());
    }

    @Test
    public void testSubListHashCodeOfMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");
        vec.add("3");

        SubListAdapter sub = (SubListAdapter) vec.subList(0, 3);

        assertEquals("Hash code should match for multiple elements", sub.hashCode(), subList.hashCode());
    }

    @Test
    public void testSubListHashCodeOfNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        subList = (SubListAdapter) vector.subList(0, 3);

        VectorAdapter vec = new VectorAdapter();
        vec.add(null);
        vec.add(null);
        vec.add(null);

        SubListAdapter sub = (SubListAdapter) vec.subList(0, 3);

        assertEquals("Hash code should match for null elements", sub.hashCode(), subList.hashCode());
    }

    @Test
    public void testSubListToStringEmptyVector()
    {
        vector = new VectorAdapter();
        subList = (SubListAdapter) vector.subList(0, 0);

        assertEquals("String representation of empty vector should match", "[]", subList.toString());
    }

    @Test
    public void testSubListToStringMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        subList = (SubListAdapter) vector.subList(0, 3);

        assertEquals("String representation of multiple elements should match", "[1, 2, 3]", subList.toString());
    }

    @Test
    public void testSubListToStringNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        subList = (SubListAdapter) vector.subList(0, 3);

        assertEquals("String representation with null elements should match", "[1, null, 3]", subList.toString());
    }

    @Test
    public void testSubListToStringDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("1");

        subList = (SubListAdapter) vector.subList(0, 3);

        assertEquals("String representation with duplicate elements should match", "[1, 1, 1]", subList.toString());
    }

    @Test
    public void testSubListToStringWithOffset()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");
        vector.add("5");

        subList = (SubListAdapter) vector.subList(2, 5);

        assertEquals("String representation should match with offset", "[3, 4, 5]", subList.toString());
    }
}
