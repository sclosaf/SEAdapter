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
 * <h2>Testing Framework Information</h2>
 * <p>This test suite utilizes JUnit 4.13 testing framework with Hamcrest 1.3
 * matchers for assertions. These dependencies are located as follows:</p>
 * <ul>
 *   <li>JUnit 4.13: {@code lib/junit-4.13.jar}</li>
 *   <li>Hamcrest Core 1.3: {@code Matcher/hamcrest-core-1.3.jar}</li>
 * </ul>
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
     * Default constructor
     */
    public SubListAdapterTest()
    {}

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

    /**
     * Test method to verify that trimToSize() on a sublist only affects the capacity of the backing vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that calling trimToSize() on a SubListAdapter correctly trims the capacity
     * of the backing vector while maintaining the correct sublist size and element values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that capacity management operations on sublists properly
     * delegate to the backing vector while preserving the sublist's view boundaries and content.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 20 and adds 5 elements. A sublist is
     * created from indices 1 to 4. After calling trimToSize() on the sublist, the test verifies that
     * the capacity has been reduced to match the vector size, while the sublist size remains unchanged
     * and elements are preserved.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 20 and 5 string elements</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Backing vector capacity is trimmed to match its size</p>
     * <p>- Sublist capacity matches vector capacity</p>
     * <p>- Sublist size remains unchanged at 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After trimToSize(), sublist capacity should equal vector size (5), sublist size should remain 3,
     * and all elements should be preserved.</p>
     */
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

    /**
     * Test method to verify that trimToSize() works correctly with null elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that trimToSize() functions correctly when the sublist contains null elements,
     * ensuring proper capacity management without affecting element values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate capacity operations on sublists containing null values,
     * ensuring that null elements don't interfere with capacity management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 20 and adds 5 null elements. A sublist is
     * created from indices 1 to 4. After calling trimToSize() on the sublist, the test verifies that
     * the capacity has been properly reduced while maintaining the correct sublist size and null elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 20 and 5 null elements</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Backing vector capacity is trimmed to match its size</p>
     * <p>- Sublist capacity matches vector capacity</p>
     * <p>- Sublist size remains unchanged at 3 with null elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After trimToSize(), sublist capacity should equal vector size (5), sublist size should remain 3,
     * and all null elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() behavior when minimum capacity is smaller than current capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() has no effect when the requested minimum capacity
     * is smaller than the current capacity of the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that ensureCapacity() correctly handles cases where capacity
     * expansion is not needed, ensuring no unnecessary operations are performed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 10 and adds 3 elements. A sublist is
     * created covering all elements. ensureCapacity(5) is called, and the test verifies that capacity
     * remains unchanged since 5 is less than the current capacity of 10.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 10 and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains unchanged at 10</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity(5) should not change capacity (remains 10), size should remain 3, and all
     * elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() behavior when minimum capacity equals current capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() has no effect when the requested minimum capacity
     * exactly equals the current capacity of the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where the requested capacity matches the
     * current capacity, ensuring no unnecessary operations are performed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 10 and adds 3 elements. A sublist is
     * created covering all elements. ensureCapacity(10) is called, and the test verifies that capacity
     * remains unchanged since it matches the current capacity.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 10 and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains unchanged at 10</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity(10) should not change capacity (remains 10), size should remain 3, and all
     * elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() uses capacity increment correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() correctly uses the capacity increment value
     * when expanding the capacity of the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that capacity expansion follows the specified capacity
     * increment parameter, ensuring predictable growth behavior.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 5 and capacity increment 3, then
     * adds 3 elements. A sublist is created covering all elements. ensureCapacity(7) is called,
     * and the test verifies that capacity increases to 8 (5 + 3) using the capacity increment.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 5, increment 3, and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases to 8 using capacity increment</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity(7) should increase capacity to 8 (5 + 3), size should remain 3, and all
     * elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() uses minimum capacity when increment is insufficient.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() uses the requested minimum capacity when the
     * capacity increment would result in insufficient capacity expansion.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that capacity expansion prioritizes the minimum capacity
     * requirement over the capacity increment when the increment would be insufficient.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 5 and capacity increment 3, then
     * adds 3 elements. A sublist is created covering all elements. ensureCapacity(10) is called,
     * and the test verifies that capacity increases directly to 10 since the increment would be
     * insufficient (5 + 3 = 8).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 5, increment 3, and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases directly to 10 (minimum capacity)</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity(10) should increase capacity to 10, size should remain 3, and all
     * elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() uses doubling growth when capacity increment is zero.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() uses doubling growth behavior when the
     * capacity increment is set to zero.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the doubling growth algorithm used when capacity
     * increment is zero, ensuring predictable capacity expansion.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 5 and zero capacity increment,
     * then adds 3 elements. A sublist is created covering all elements. ensureCapacity(9) is
     * called, and the test verifies that capacity doubles to 10 (5 * 2 = 10 ≥ 9).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 5, zero increment, and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity doubles to 10</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity(9) should double capacity to 10, size should remain 3, and all
     * elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() uses minimum capacity when doubling is insufficient.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() uses the requested minimum capacity when
     * doubling the current capacity would still be insufficient.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that capacity expansion uses the minimum capacity
     * requirement when doubling growth would not meet the capacity needs.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 5 and zero capacity increment,
     * then adds 3 elements. A sublist is created covering all elements. ensureCapacity(20) is
     * called, and the test verifies that capacity increases directly to 20 since doubling
     * (5 * 2 = 10) would be insufficient.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 5, zero increment, and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases directly to 20 (minimum capacity)</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity(20) should increase capacity to 20, size should remain 3, and all
     * elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() works correctly after setSize() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() functions correctly after the sublist size
     * has been modified using setSize().</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate capacity management operations following size
     * modifications, ensuring proper coordination between size and capacity management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 5 and adds 4 elements. A sublist
     * is created covering the first 3 elements. setSize(4) is called to expand the sublist,
     * then ensureCapacity(20) is called. The test verifies that capacity expands correctly
     * while preserving all elements including the newly added null element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 5 and 4 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     * <p>- setSize(4) is called on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases to 20</p>
     * <p>- Size remains 4 with one null element added</p>
     * <p>- All original elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After setSize(4) and ensureCapacity(20), capacity should be 20, size should be 4,
     * with three original elements and one null element at the end.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() works correctly after trimToSize() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() functions correctly after the sublist capacity
     * has been reduced using trimToSize().</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate capacity expansion following a trim operation,
     * ensuring that capacity can be increased again when needed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 10 and adds 3 elements. A sublist
     * is created covering all elements. trimToSize() is called to reduce capacity, then
     * ensureCapacity(8) is called. The test verifies that capacity expands correctly while
     * preserving all elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 10 and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     * <p>- trimToSize() is called on the sublist</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases to 8</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After trimToSize() and ensureCapacity(8), capacity should be 8, size should remain 3,
     * and all elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() behavior with zero minimum capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() has no effect when called with zero
     * minimum capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of zero minimum capacity,
     * ensuring no unnecessary operations are performed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 10 and adds 3 elements.
     * A sublist is created covering all elements. ensureCapacity(0) is called, and the
     * test verifies that capacity remains unchanged since zero is less than current capacity.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 10 and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains unchanged at 10</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity(0) should not change capacity (remains 10), size should remain 3,
     * and all elements should be preserved.</p>
     */
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

    /**
     * Test method to verify ensureCapacity() behavior with negative minimum capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() has no effect when called with negative
     * minimum capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate error handling for invalid negative capacity values,
     * ensuring the method gracefully handles edge cases.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 10 and adds 3 elements.
     * A sublist is created covering all elements. ensureCapacity(-5) is called, and the
     * test verifies that capacity remains unchanged since negative values are invalid.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with capacity 10 and 3 string elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains unchanged at 10</p>
     * <p>- Size remains 3</p>
     * <p>- All elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity(-5) should not change capacity (remains 10), size should remain 3,
     * and all elements should be preserved.</p>
     */
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

    /**
     * Test method to verify that setSize() increases the size of an empty sublist and adds null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() correctly increases the size of an empty sublist by adding null elements
     * and updates both the sublist and backing vector accordingly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the setSize() method's behavior when increasing the size of an empty sublist,
     * ensuring proper element insertion and size management in both the sublist and backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, then creates an empty sublist (from index 1 to 1).
     * It calls setSize(2) on the empty sublist and verifies the new size, capacity, element values,
     * and the backing vector's size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 2</p>
     * <p>- Backing vector size becomes 5</p>
     * <p>- New elements in sublist are null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should increase sublist size to 2, add null elements, and increase backing vector size to 5.</p>
     */
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

    /**
     * Test method to verify that setSize() increases the size of a sublist with existing elements and adds null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() correctly increases the size of a sublist with existing elements,
     * preserving existing elements and adding null elements to the end.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the setSize() method's behavior when increasing the size of a non-empty sublist,
     * ensuring proper element preservation and null element insertion.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist from indices 0 to 2,
     * calls setSize(4) on the sublist, and verifies the new size, element values, and backing vector size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 4</p>
     * <p>- Backing vector size becomes 5</p>
     * <p>- Existing elements are preserved, new elements are null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should increase sublist size to 4, preserve existing elements, add null elements, and increase backing vector size to 5.</p>
     */
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

    /**
     * Test method to verify that setSize() decreases the size of a sublist and removes elements from the backing vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() correctly decreases the size of a sublist by removing elements
     * from the end and updates the backing vector accordingly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the setSize() method's behavior when decreasing the size of a sublist,
     * ensuring proper element removal and size management in both the sublist and backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with four elements, creates a sublist covering all elements,
     * calls setSize(2) on the sublist, and verifies the new size, remaining elements, and backing vector size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 0 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 2</p>
     * <p>- Backing vector size becomes 2</p>
     * <p>- Elements beyond new size are removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should decrease sublist size to 2, preserve first two elements, and decrease backing vector size to 2.</p>
     */
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

    /**
     * Test method to verify that setSize() with the same size does not modify the sublist or backing vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() with the current size does not modify the sublist or backing vector,
     * ensuring idempotent behavior.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the setSize() method's behavior when the new size equals the current size,
     * ensuring no unnecessary modifications occur.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist covering all elements,
     * calls setSize(3) on the sublist, and verifies that size and elements remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size remains 3</p>
     * <p>- Backing vector size remains 3</p>
     * <p>- All elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should not modify the sublist or backing vector when new size equals current size.</p>
     */
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

    /**
     * Test method to verify that setSize(0) clears the sublist and backing vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize(0) correctly empties the sublist and removes all elements
     * from the backing vector within the sublist range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the setSize() method's behavior when setting size to zero,
     * ensuring complete removal of elements from both sublist and backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist covering all elements,
     * calls setSize(0) on the sublist, and verifies that both sublist and backing vector become empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 0</p>
     * <p>- Backing vector size becomes 0</p>
     * <p>- All elements are removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize(0) should empty the sublist and remove all elements from the backing vector.</p>
     */
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

    /**
     * Test method to verify that setSize() throws ArrayIndexOutOfBoundsException for negative size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() throws ArrayIndexOutOfBoundsException when called with a negative size,
     * ensuring proper validation of input parameters.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative size values,
     * ensuring robust error reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist from indices 0 to 2,
     * calls setSize(-1) on the sublist, and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- SubList and backing vector remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should throw ArrayIndexOutOfBoundsException when called with negative size.</p>
     */
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

    /**
     * Test method to verify that setSize() works correctly after multiple size changes (decrease then increase).
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() correctly handles sequential size changes (decrease then increase),
     * preserving existing elements and properly managing null element insertion.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the setSize() method's behavior during multiple sequential operations,
     * ensuring consistent state management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with five elements, creates a sublist from indices 1 to 4,
     * calls setSize(2) then setSize(4) on the sublist, and verifies the final size, element values, and backing vector size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 4</p>
     * <p>- Backing vector size becomes 6</p>
     * <p>- Existing elements are preserved, new elements are null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sequential setSize() calls should correctly handle size changes, preserve elements, and properly manage backing vector.</p>
     */
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

    /**
     * Test method to verify that setSize() works correctly after multiple size changes (increase then decrease).
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() correctly handles sequential size changes (increase then decrease),
     * preserving existing elements and properly removing excess elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the setSize() method's behavior during multiple sequential operations,
     * ensuring consistent state management when increasing then decreasing size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with two elements, creates a sublist covering all elements,
     * calls setSize(4) then setSize(1) on the sublist, and verifies the final size, remaining elements, and backing vector size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 1</p>
     * <p>- Backing vector size becomes 1</p>
     * <p>- Only the first element is preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sequential setSize() calls should correctly handle size changes and preserve appropriate elements.</p>
     */
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

    /**
     * Test method to verify that setSize() works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() correctly handles sublists containing null elements,
     * preserving existing null elements and adding new null elements when increasing size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the setSize() method's behavior with null elements,
     * ensuring proper handling of null values during size operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three null elements, creates a sublist covering all elements,
     * calls setSize(5) on the sublist, and verifies the new size, element values, and backing vector size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 5</p>
     * <p>- Backing vector size becomes 5</p>
     * <p>- All elements remain null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should correctly handle null elements and maintain null values when increasing size.</p>
     */
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

    /**
     * Test method to verify that setSize() only affects the sublist range in the backing vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() operations only modify the portion of the backing vector
     * covered by the sublist, leaving other elements unaffected.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that setSize() correctly isolates changes to the sublist range,
     * ensuring integrity of elements outside the sublist range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with five elements, creates a sublist from indices 1 to 4,
     * calls setSize(2) on the sublist, and verifies that elements before and after the sublist range remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 2</p>
     * <p>- Backing vector size becomes 4</p>
     * <p>- Elements outside sublist range remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should only modify elements within the sublist range, preserving elements outside this range.</p>
     */
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

    /**
     * Test method to verify that capacity() returns correct value for empty vector sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that capacity() returns the correct capacity value for a sublist created from an empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the capacity() method's behavior for sublists created from empty vectors,
     * ensuring proper capacity reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector with default capacity, creates an empty sublist,
     * and verifies that capacity() returns the expected default capacity value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with default capacity (10)</p>
     * <p>- SubListAdapter is created from indices 0 to 0 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity value remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>capacity() should return the backing vector's capacity for empty sublists.</p>
     */
    @Test
    public void testSubListCapacityEmptyVector()
    {
        vector = new VectorAdapter();

        subList = (SubListAdapter) vector.subList(0, 0);

        assertEquals("Capacity should be 10", 10, subList.capacity());
    }

    /**
     * Test method to verify that capacity() returns correct value for custom initial capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that capacity() returns the correct capacity value for a sublist created from a vector
     * with custom initial capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the capacity() method's behavior for sublists created from vectors
     * with custom capacity, ensuring proper capacity reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector with custom capacity (15), creates an empty sublist,
     * and verifies that capacity() returns the expected custom capacity value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with custom capacity (15)</p>
     * <p>- SubListAdapter is created from indices 0 to 0 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity value remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>capacity() should return the backing vector's custom capacity for empty sublists.</p>
     */
    @Test
    public void testSubListCapacityAtInitialCapacity()
    {
        vector = new VectorAdapter(15);

        subList = (SubListAdapter) vector.subList(0, 0);

        assertEquals("Capacity should be 15", 15, subList.capacity());
    }

    /**
     * Test method to verify that size() returns 0 for empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that size() returns 0 for an empty sublist created from a non-empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the size() method's behavior for empty sublists,
     * ensuring accurate size reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates an empty sublist (from index 1 to 1),
     * and verifies that size() returns 0.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size remains 0</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should return 0 for empty sublists.</p>
     */
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

    /**
     * Test method to verify that size() increases after adding elements to sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that size() correctly reflects the increased size after adding elements to a sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the size() method's behavior after element addition operations,
     * ensuring accurate size tracking.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist from indices 0 to 2,
     * adds an element to the sublist, and verifies that size() returns the increased value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size increases to 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should return increased value after adding elements to sublist.</p>
     */
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

    /**
     * Test method to verify that size() decreases after removing elements from sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that size() correctly reflects the decreased size after removing elements from a sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the size() method's behavior after element removal operations,
     * ensuring accurate size tracking.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with four elements, creates a sublist from indices 1 to 4,
     * removes an element from the sublist, and verifies that size() returns the decreased value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size decreases to 2</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should return decreased value after removing elements from sublist.</p>
     */
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

    /**
     * Test method to verify that size() returns 0 after clearing sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that size() returns 0 after clearing a sublist using the clear() method.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the size() method's behavior after clearing operations,
     * ensuring accurate size reporting for empty sublists.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist covering all elements,
     * calls clear() on the sublist, and verifies that size() returns 0.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size becomes 0</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should return 0 after clearing the sublist.</p>
     */
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

    /**
     * Test method to verify that size() correctly counts null elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that size() correctly includes null elements when counting sublist size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the size() method's behavior with null elements,
     * ensuring accurate counting of all elements regardless of value.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three null elements, creates a sublist covering all elements,
     * and verifies that size() returns 3.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size remains 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should count null elements when determining sublist size.</p>
     */
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

    /**
     * Test method to verify that isEmpty() returns true for empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that isEmpty() returns true for an empty sublist created from a non-empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the isEmpty() method's behavior for empty sublists,
     * ensuring accurate empty status reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates an empty sublist (from index 1 to 1),
     * and verifies that isEmpty() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return true for empty sublists.</p>
     */
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

    /**
     * Test method to verify that isEmpty() returns false after adding elements to sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that isEmpty() returns false after adding elements to a previously empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the isEmpty() method's behavior after element addition operations,
     * ensuring accurate empty status reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist from indices 0 to 2,
     * adds an element to the sublist, and verifies that isEmpty() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return false after adding elements to sublist.</p>
     */
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

    /**
     * Test method to verify that isEmpty() returns true after clearing sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that isEmpty() returns true after clearing a sublist using the clear() method.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the isEmpty() method's behavior after clearing operations,
     * ensuring accurate empty status reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist covering all elements,
     * calls clear() on the sublist, and verifies that isEmpty() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return true after clearing the sublist.</p>
     */
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

    /**
     * Test method to verify that isEmpty() returns false for sublist containing null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that isEmpty() returns false for sublists containing null elements,
     * ensuring that null values are considered as valid elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the isEmpty() method's behavior with null elements,
     * ensuring accurate empty status reporting regardless of element values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with two null elements, creates a sublist covering all elements,
     * and verifies that isEmpty() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 null elements</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return false for sublists containing null elements.</p>
     */
    @Test
    public void testSubListIsEmptyNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);

        subList = (SubListAdapter) vector.subList(0, 2);
        assertFalse("SubList with null elements should not be empty", subList.isEmpty());
    }

    /**
     * Test method to verify that isEmpty() returns true after setSize(0).
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that isEmpty() returns true after setting sublist size to zero using setSize(0).</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the isEmpty() method's behavior after setSize(0) operations,
     * ensuring accurate empty status reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist covering all elements,
     * calls setSize(0) on the sublist, and verifies that isEmpty() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return true after setSize(0).</p>
     */
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

    /**
     * Test method to verify that contains() returns true for existing elements in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly identifies elements that exist within
     * the sublist range, ensuring proper element detection functionality.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic functionality of the contains() method for elements
     * that should be present in the sublist. This ensures the method works correctly for normal use cases.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3.
     * It verifies that contains() returns true for elements "2" and "3" which are within the sublist range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns true for elements within the sublist range</p>
     * <p>- No modifications to the underlying vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return true for elements "2" and "3" that exist within the sublist range.</p>
     */
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

    /**
     * Test method to verify that contains() returns false for non-existing elements in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly returns false for elements that do not
     * exist within the sublist range, ensuring proper element detection functionality.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the contains() method's ability to correctly identify when
     * elements are not present in the sublist, including elements outside the sublist range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3.
     * It verifies that contains() returns false for element "1" (which is before the sublist range)
     * and element "4" (which doesn't exist in the vector).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns false for elements not in the sublist range</p>
     * <p>- No modifications to the underlying vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return false for elements "1" and "4" that do not exist within the sublist range.</p>
     */
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

    /**
     * Test method to verify that contains() correctly handles null elements in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly identifies null elements within
     * the sublist range, ensuring proper handling of null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the contains() method's ability to detect null elements
     * in the sublist, which is important for completeness and edge case handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements including a null value, then creates a sublist
     * that includes the null element. It verifies that contains() returns true when checking for null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 (containing the null element)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns true for null element within the sublist</p>
     * <p>- No modifications to the underlying vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return true when checking for null in a sublist that contains a null element.</p>
     */
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

    /**
     * Test method to verify that contains() returns false for all elements on an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly returns false for any element when
     * called on an empty sublist, ensuring proper behavior for edge cases.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the contains() method's behavior on empty sublists, which
     * should never contain any elements, including null values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates an empty sublist (from index 1 to 1).
     * It verifies that contains() returns false for any element, including elements that exist in the
     * main vector but not in the empty sublist range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns false for all elements on empty sublist</p>
     * <p>- No modifications to the underlying vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return false for any element when called on an empty sublist.</p>
     */
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

    /**
     * Test method to verify that contains() correctly reflects element removal from a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly updates its return value after elements
     * are removed from the sublist, ensuring dynamic consistency with the sublist's contents.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the contains() method's ability to reflect changes to the
     * sublist's contents, specifically after element removal operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist from index 1 to 4.
     * It removes elements "2" and "3" from the sublist, then verifies that contains() returns false
     * for these removed elements but still returns true for remaining elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns false for removed elements "2" and "3"</p>
     * <p>- contains() returns true for remaining element "4"</p>
     * <p>- The underlying vector is modified to reflect the removals</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return false for removed elements and true for remaining elements after removal operations.</p>
     */
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

    /**
     * Test method to verify that contains() correctly reflects element replacement in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly updates its return value after an element
     * is replaced in the sublist, ensuring dynamic consistency with the sublist's contents.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the contains() method's ability to reflect changes to the
     * sublist's contents, specifically after element replacement operations using the set() method.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It replaces element "2" with "4" at index 1, then verifies that contains() returns false for the
     * replaced element "2" but returns true for the new element "4".</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns false for replaced element "2"</p>
     * <p>- contains() returns true for new element "4"</p>
     * <p>- The underlying vector is modified to reflect the replacement</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return false for replaced elements and true for new elements after replacement operations.</p>
     */
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

    /**
     * Test method to verify that contains() returns false for elements outside the sublist range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly returns false for elements that exist
     * in the backing vector but outside the current sublist range, ensuring proper range isolation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the contains() method's ability to respect sublist boundaries
     * and not report elements that exist in the backing vector but outside the sublist range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist from index 1 to 3.
     * It verifies that contains() returns false for element "1" (which is before the sublist range)
     * and element "4" (which is after the sublist range), even though these elements exist in the
     * backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns false for elements outside the sublist range</p>
     * <p>- No modifications to the underlying vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return false for elements that exist in the backing vector but outside the sublist range.</p>
     */
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

    /**
     * Test method to verify indexOf() returns correct index for existing elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf() correctly returns the index of existing elements within a sublist range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic indexOf functionality for elements that exist within the sublist boundaries,
     * ensuring proper index calculation relative to the sublist's view.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3. Verifies that indexOf
     * returns correct relative indices for elements "2" and "3" within the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter created from indices 1 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns 0 for "2"</p>
     * <p>- indexOf returns 1 for "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return correct relative indices for elements within the sublist range.</p>
     */
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

    /**
     * Test method to verify indexOf() returns -1 for non-existing elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf() correctly returns -1 for elements not present in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper handling of elements that are outside the sublist range or not present in the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3. Verifies that indexOf
     * returns -1 for elements "1" (before sublist range) and "4" (not in vector).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter created from indices 1 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns -1 for "1"</p>
     * <p>- indexOf returns -1 for "4"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return -1 for elements not present in the sublist.</p>
     */
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

    /**
     * Test method to verify indexOf() correctly handles null elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf() correctly identifies the index of null elements within a sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper handling of null values in the sublist, ensuring they are correctly located.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter containing a null element, then creates a sublist that includes the null element.
     * Verifies that indexOf returns the correct index for the null value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter created from indices 1 to 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns 0 for null element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return the correct index for null elements within the sublist.</p>
     */
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

    /**
     * Test method to verify indexOf() behavior on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf() returns -1 for any element when called on an empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates edge case behavior for empty sublists, ensuring no false positives occur.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with three elements, then creates an empty sublist (from index 1 to 1).
     * Verifies that indexOf returns -1 for both existing elements and null values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter created from indices 1 to 1 (empty range)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns -1 for "1"</p>
     * <p>- indexOf returns -1 for null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return -1 for any element when called on an empty sublist.</p>
     */
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

    /**
     * Test method to verify indexOf() returns first occurrence for duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf() returns the index of the first occurrence when duplicate elements exist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates that indexOf follows the specification of returning the first occurrence index for duplicates.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with duplicate elements, then creates a sublist containing the duplicates.
     * Verifies that indexOf returns the index of the first occurrence of the duplicate element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["0", "1", "1"]</p>
     * <p>- SubListAdapter created from indices 1 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns 0 for first occurrence of "1"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return the index of the first occurrence when duplicate elements exist.</p>
     */
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

    /**
     * Test method to verify indexOf() does not find elements outside sublist range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf() correctly returns -1 for elements outside the sublist's range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates that the sublist view properly restricts element search to its defined range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with four elements, then creates a sublist from index 1 to 3.
     * Verifies that indexOf returns -1 for elements before and after the sublist range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter created from indices 1 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns -1 for "1" (before range)</p>
     * <p>- indexOf returns -1 for "4" (after range)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should not find elements outside the sublist's defined range.</p>
     */
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

    /**
     * Test method to verify indexOf() works correctly with sublist offset.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf() correctly calculates indices relative to the sublist's offset.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper index calculation when the sublist starts at a non-zero offset.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with four elements, then creates a sublist starting at index 2.
     * Verifies that indexOf returns correct relative indices for elements within the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter created from indices 2 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns 0 for "2"</p>
     * <p>- indexOf returns 1 for "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return correct relative indices accounting for the sublist's offset.</p>
     */
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

    /**
     * Test method to verify indexOf(element, index) with existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf(element, index) correctly finds elements starting from specified index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates the search-from-index functionality for elements that exist in the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with five elements, then creates a sublist from index 1 to 4.
     * Verifies that indexOf finds elements correctly when starting from different indices.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf finds "3" at index 1 when starting from index 0</p>
     * <p>- indexOf finds "3" at index 1 when starting from index 1</p>
     * <p>- indexOf returns -1 when starting from index beyond element occurrence</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should correctly find elements when starting from specified indices.</p>
     */
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

    /**
     * Test method to verify indexOf(element, index) with non-existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf(element, index) returns -1 for non-existing elements regardless of start index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates that non-existing elements are properly handled across different start indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3.
     * Verifies that indexOf returns -1 for non-existing element "4" across different start indices.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter created from indices 1 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns -1 for "4" at all start indices</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return -1 for non-existing elements regardless of start index.</p>
     */
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

    /**
     * Test method to verify indexOf(null, index) with multiple null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf(null, index) correctly finds null elements starting from specified index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper handling of multiple null values with different start indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with multiple null elements, then creates a sublist containing them.
     * Verifies that indexOf correctly finds null elements based on start index.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", null, "3", null, "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf finds first null at index 0 when starting from index 0</p>
     * <p>- indexOf finds second null at index 2 when starting from index 1</p>
     * <p>- indexOf returns -1 when starting beyond last null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should correctly find null elements based on start index.</p>
     */
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

    /**
     * Test method to verify indexOf(element, index) with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf(element, index) correctly finds occurrences of duplicate elements based on start index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates that the method finds the correct occurrence of duplicate elements when using different start indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with duplicate elements, then creates a sublist containing them.
     * Verifies that indexOf returns correct indices for different occurrences based on start index.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["0", "1", "1", "2", "1"]</p>
     * <p>- SubListAdapter created from indices 1 to 5</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf finds correct occurrence indices for "1" based on start index</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return correct occurrence indices for duplicate elements based on start index.</p>
     */
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

    /**
     * Test method to verify indexOf(element, index) throws exception for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf(element, index) throws IndexOutOfBoundsException for negative start index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper exception handling for invalid negative start indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with three elements, then creates a sublist covering all elements.
     * Attempts to call indexOf with negative start index, expecting IndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter created from indices 0 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should throw IndexOutOfBoundsException for negative start index.</p>
     */
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

    /**
     * Test method to verify indexOf(element, index) returns -1 for indices equal or greater than size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf(element, index) returns -1 when start index is equal or greater than sublist size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper handling of start indices that are beyond the sublist's bounds.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with three elements, then creates a sublist from index 0 to 2.
     * Verifies that indexOf returns -1 when start index equals or exceeds sublist size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter created from indices 0 to 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns -1 when start index equals size</p>
     * <p>- indexOf returns -1 when start index exceeds size</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return -1 when start index is equal or greater than sublist size.</p>
     */
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

    /**
     * Test method to verify indexOf(element, index) edge cases.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies indexOf(element, index) behavior in various edge cases with exact indices.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates precise index matching and boundary conditions for the search-from-index functionality.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with five elements, then creates a sublist from index 1 to 4.
     * Tests various edge cases including exact index matches and boundary conditions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf finds elements at exact start indices</p>
     * <p>- indexOf returns -1 when element not found from start index</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should handle edge cases and exact index matches correctly.</p>
     */
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

    /**
     * Test method to verify indexOf(element, index) does not find elements outside sublist range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf(element, index) correctly returns -1 for elements outside sublist range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates that the search-from-index functionality respects sublist boundaries.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with four elements, then creates a sublist from index 1 to 3.
     * Verifies that indexOf returns -1 for elements outside the sublist range regardless of start index.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter created from indices 1 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns -1 for elements before and after sublist range</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should not find elements outside the sublist's defined range.</p>
     */
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

    /**
     * Test method to verify indexOf(element, index) works correctly with sublist offset.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf(element, index) correctly calculates indices relative to sublist offset.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test validates proper index calculation when using start index with sublist offset.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>Creates a VectorAdapter with four elements, then creates a sublist starting at index 2.
     * Verifies that indexOf returns correct relative indices when using different start indices.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter initialized with elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter created from indices 2 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns correct relative indices accounting for sublist offset</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return correct relative indices when using start index with sublist offset.</p>
     */
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

    /**
     * Test method to verify lastIndexOf returns correct index for existing elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly returns the index of the last occurrence
     * of existing elements within the sublist range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic functionality of lastIndexOf method for
     * elements that exist within the sublist boundaries, ensuring proper index calculation
     * with sublist offset.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from
     * index 1 to 3. It calls lastIndexOf for elements "2" and "3" and verifies the returned
     * indices match the expected positions within the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns correct indices for existing elements</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return 0 for element "2" and 1 for element "3" within the sublist.</p>
     */
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

    /**
     * Test method to verify lastIndexOf returns -1 for non-existing elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly returns -1 for elements that do not
     * exist within the sublist range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case behavior of lastIndexOf method for
     * elements outside the sublist content, ensuring proper handling of non-existent elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from
     * index 1 to 3. It calls lastIndexOf for elements "1" and "4" and verifies that -1 is
     * returned for both non-existing elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for non-existing elements</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return -1 for elements not present in the sublist.</p>
     */
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

    /**
     * Test method to verify lastIndexOf correctly handles null elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly identifies and returns the index of
     * null elements within the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the special case handling of null elements in
     * lastIndexOf method, ensuring proper null comparison semantics.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing a null element, then creates a sublist
     * that includes the null element. It calls lastIndexOf for null and verifies the correct
     * index is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns correct index for null element</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return the correct index of the null element within the sublist.</p>
     */
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

    /**
     * Test method to verify lastIndexOf behavior on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf returns -1 for any element when called on an
     * empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case behavior of lastIndexOf method on
     * empty sublists, ensuring proper handling of the empty collection scenario.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates an empty sublist
     * (from index 1 to 1). It calls lastIndexOf for both existing and null elements and
     * verifies that -1 is returned in all cases.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for all elements on empty sublist</p>
     * <p>- Empty sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return -1 for any element when the sublist is empty.</p>
     */
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

    /**
     * Test method to verify lastIndexOf returns last occurrence index for duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly returns the index of the last occurrence
     * when duplicate elements exist in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the duplicate element handling in lastIndexOf method,
     * ensuring it returns the highest index where the element appears.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements, then creates a sublist
     * containing the duplicates. It calls lastIndexOf for the duplicate element and verifies
     * the returned index matches the last occurrence.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "1"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns index of last occurrence for duplicate elements</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return the index of the last occurrence of duplicate elements.</p>
     */
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

    /**
     * Test method to verify lastIndexOf does not find elements outside sublist range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly returns -1 for elements that exist in
     * the backing vector but outside the sublist range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the range boundary handling in lastIndexOf method,
     * ensuring elements outside the sublist range are not found.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist from
     * index 1 to 3. It calls lastIndexOf for elements before and after the sublist range
     * and verifies that -1 is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for elements outside sublist range</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should not find elements that exist outside the sublist range.</p>
     */
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

    /**
     * Test method to verify lastIndexOf works correctly with sublist offset.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly handles the offset calculation when
     * the sublist starts from a non-zero index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the offset handling in lastIndexOf method, ensuring
     * indices are correctly calculated relative to the sublist start.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist starting
     * from index 2. It calls lastIndexOf for elements within the sublist and verifies the
     * returned indices are correct relative to the sublist start.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns correct indices accounting for sublist offset</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return indices relative to the sublist start, not the backing vector.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter finds elements correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter correctly finds elements
     * up to the specified index within the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the index parameter functionality in lastIndexOf
     * method, ensuring it searches backwards from the specified index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements, then creates a sublist. It calls
     * lastIndexOf with different index parameters and verifies the returned indices match
     * the expected occurrences within the specified range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf with index parameter returns correct indices</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return the correct index when searching backwards from specified index.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter returns -1 for non-existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter correctly returns -1 for
     * elements that do not exist within the search range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the index parameter functionality in lastIndexOf
     * method for non-existing elements, ensuring proper handling of absent elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist. It calls
     * lastIndexOf with index parameter for non-existing elements and verifies that -1 is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf with index parameter returns -1 for non-existing elements</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return -1 when element is not found within the specified index range.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter correctly identifies and
     * returns the index of null elements within the specified range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the index parameter functionality in lastIndexOf
     * method for null elements, ensuring proper null comparison with range limitation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple null elements, then creates a sublist.
     * It calls lastIndexOf with different index parameters for null and verifies the returned
     * indices match the expected occurrences within the specified range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3", null, "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf with index parameter returns correct indices for null elements</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should correctly find null elements within the specified index range.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter handles duplicate elements correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter correctly returns the
     * last occurrence index for duplicate elements within the specified range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the index parameter functionality in lastIndexOf
     * method for duplicate elements, ensuring it returns the correct occurrence within the
     * limited search range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple duplicate elements, then creates a
     * sublist. It calls lastIndexOf with different index parameters for the duplicate element
     * and verifies the returned indices match the expected occurrences within the specified range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "1", "2", "1"]</p>
     * <p>- SubListAdapter is created from indices 1 to 5 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf with index parameter returns correct occurrence indices for duplicates</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return the correct occurrence index within the specified search range.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter throws exception for invalid index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter throws IndexOutOfBoundsException
     * when the index is equal to the sublist size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the exception handling in lastIndexOf method when
     * provided with an invalid index parameter that equals the sublist size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist. It calls
     * lastIndexOf with an index parameter equal to the sublist size and expects an
     * IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should throw IndexOutOfBoundsException when index equals sublist size.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter returns -1 for negative indices.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter returns -1 when the
     * index parameter is negative.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case behavior of lastIndexOf method when
     * provided with negative index parameters.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist. It calls
     * lastIndexOf with negative index parameters and verifies that -1 is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for negative indices</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return -1 when the index parameter is negative.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter handles edge cases correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter correctly handles various
     * edge cases including exact matches and boundary conditions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the comprehensive edge case handling in lastIndexOf
     * method with index parameter, ensuring robust behavior in boundary scenarios.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements, then creates a sublist. It calls
     * lastIndexOf with various edge case index parameters and verifies the returned indices
     * match expected results for exact matches, boundary conditions, and range limitations.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf with index parameter returns correct results for edge cases</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should handle all edge cases correctly with index parameter.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter does not find elements outside range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter correctly returns -1 for
     * elements that exist in the backing vector but outside the sublist range, even when
     * searching within valid index bounds.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the range boundary handling in lastIndexOf method
     * with index parameter, ensuring elements outside the sublist are not found regardless
     * of search index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist. It calls
     * lastIndexOf with index parameter for elements before and after the sublist range and
     * verifies that -1 is returned even with valid index parameters.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for elements outside sublist range</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should not find elements that exist outside the sublist range.</p>
     */
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

    /**
     * Test method to verify lastIndexOf with index parameter works correctly with sublist offset.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with an index parameter correctly handles the
     * offset calculation when the sublist starts from a non-zero index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the offset handling in lastIndexOf method with
     * index parameter, ensuring indices are correctly calculated relative to the sublist start.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist starting
     * from index 2. It calls lastIndexOf with index parameter for elements within the sublist
     * and verifies the returned indices are correct relative to the sublist start.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf with index parameter returns correct indices accounting for sublist offset</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return indices relative to the sublist start when using index parameter.</p>
     */
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

    /**
     * Test method to verify that elementAt() returns correct elements for valid indices in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt() correctly returns elements at specified valid indices within a sublist,
     * ensuring proper element access functionality.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic element access functionality for valid indices in a sublist.
     * This ensures that the sublist correctly maps indices to the corresponding elements in the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3.
     * It calls elementAt() with indices 0 and 1 and verifies that the returned elements match the expected values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- elementAt(0) returns "2"</p>
     * <p>- elementAt(1) returns "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt() should return the correct elements for valid indices within the sublist.</p>
     */
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

    /**
     * Test method to verify that elementAt() throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt() throws ArrayIndexOutOfBoundsException when called with a negative index,
     * ensuring proper bounds checking.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative indices.
     * This ensures robustness and proper error reporting when attempting to access elements with invalid indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3.
     * It calls elementAt() with index -1 and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt() should throw ArrayIndexOutOfBoundsException when called with a negative index.</p>
     */
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

    /**
     * Test method to verify that elementAt() throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt() throws ArrayIndexOutOfBoundsException when called with an index equal to the sublist size,
     * ensuring proper bounds checking at the upper limit.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for indices at the upper boundary of the sublist.
     * This ensures robustness and proper error reporting when attempting to access elements beyond the valid range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3 (size 2).
     * It calls elementAt() with index 2 and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt() should throw ArrayIndexOutOfBoundsException when called with an index equal to the sublist size.</p>
     */
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

    /**
     * Test method to verify that elementAt() throws ArrayIndexOutOfBoundsException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt() throws ArrayIndexOutOfBoundsException when called on an empty sublist,
     * ensuring proper exception handling for edge cases.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an empty sublist.
     * This ensures robustness and proper error reporting when attempting to access elements from an empty sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates an empty sublist from index 1 to 1.
     * It calls elementAt() with index 0 and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt() should throw ArrayIndexOutOfBoundsException when called on an empty sublist.</p>
     */
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

    /**
     * Test method to verify that elementAt() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt() correctly returns null when accessing a position containing a null element,
     * ensuring proper handling of null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements in the sublist.
     * This ensures that the implementation correctly distinguishes between null elements and out-of-bounds access.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements including a null value, then creates a sublist containing the null element.
     * It calls elementAt() with the index of the null element and verifies that null is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- elementAt(0) returns null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt() should return null when accessing a position containing a null element.</p>
     */
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

    /**
     * Test method to verify that elementAt() correctly handles offset calculations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt() correctly calculates offsets when accessing elements in a sublist,
     * ensuring proper index mapping between the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper offset calculation in sublist element access.
     * This ensures that the sublist correctly maps its indices to the corresponding positions in the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist starting from index 2.
     * It calls elementAt() with indices 0 and 1 and verifies that the returned elements match the expected values
     * from the backing vector, considering the offset.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- elementAt(0) returns "2"</p>
     * <p>- elementAt(1) returns "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt() should return the correct elements considering the sublist offset from the backing vector.</p>
     */
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

    /**
     * Test method to verify that firstElement() returns the correct element in a non-empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement() returns the first element of a non-empty sublist,
     * ensuring proper functionality for accessing the first element.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic functionality of firstElement() for non-empty sublists.
     * This ensures that the method correctly identifies and returns the first element in the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3.
     * It calls firstElement() and verifies that the returned element matches the expected first element of the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- firstElement() returns "2"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement() should return the first element of a non-empty sublist.</p>
     */
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


    /**
     * Test method to verify that firstElement() throws NoSuchElementException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement() throws NoSuchElementException when called on an empty sublist,
     * ensuring proper exception handling for edge cases.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an empty sublist.
     * This ensures robustness and proper error reporting when attempting to access the first element of an empty sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates an empty sublist from index 1 to 1.
     * It calls firstElement() and expects NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement() should throw NoSuchElementException when called on an empty sublist.</p>
     */
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

    /**
     * Test method to verify that firstElement() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement() correctly returns null when the first element of the sublist is null,
     * ensuring proper handling of null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements as the first element of a sublist.
     * This ensures that the implementation correctly distinguishes between null elements and empty sublists.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements including a null value as the first element of the sublist,
     * then creates a sublist containing the null element at position 0.
     * It calls firstElement() and verifies that null is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- firstElement() returns null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement() should return null when the first element of the sublist is null.</p>
     */
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

    /**
     * Test method to verify that firstElement() correctly handles offset calculations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement() correctly calculates offsets when accessing the first element in a sublist,
     * ensuring proper index mapping between the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper offset calculation in sublist first element access.
     * This ensures that the sublist correctly maps its first element to the corresponding position in the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist starting from index 2.
     * It calls firstElement() and verifies that the returned element matches the expected first element
     * from the backing vector, considering the offset.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- firstElement() returns "2"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement() should return the correct element considering the sublist offset from the backing vector.</p>
     */
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

    /**
     * Test method to verify that lastElement() returns the correct element in a non-empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastElement() returns the last element of a non-empty sublist,
     * ensuring proper functionality for accessing the last element.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic functionality of lastElement() for non-empty sublists.
     * This ensures that the method correctly identifies and returns the last element in the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3.
     * It calls lastElement() and verifies that the returned element matches the expected last element of the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastElement() returns "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastElement() should return the last element of a non-empty sublist.</p>
     */
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

    /**
     * Test method to verify that lastElement() throws NoSuchElementException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastElement() throws NoSuchElementException when called on an empty sublist,
     * ensuring proper exception handling for edge cases.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an empty sublist.
     * This ensures robustness and proper error reporting when attempting to access the last element of an empty sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates an empty sublist from index 1 to 1.
     * It calls lastElement() and expects NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastElement() should throw NoSuchElementException when called on an empty sublist.</p>
     */
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

    /**
     * Test method to verify that lastElement() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastElement() correctly returns null when the last element of the sublist is null,
     * ensuring proper handling of null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements as the last element of a sublist.
     * This ensures that the implementation correctly distinguishes between null elements and empty sublists.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements including a null value as the last element of the sublist,
     * then creates a sublist containing the null element at the last position.
     * It calls lastElement() and verifies that null is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastElement() returns null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastElement() should return null when the last element of the sublist is null.</p>
     */
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

    /**
     * Test method to verify that lastElement() correctly handles offset calculations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastElement() correctly calculates offsets when accessing the last element in a sublist,
     * ensuring proper index mapping between the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper offset calculation in sublist last element access.
     * This ensures that the sublist correctly maps its last element to the corresponding position in the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist starting from index 2.
     * It calls lastElement() and verifies that the returned element matches the expected last element
     * from the backing vector, considering the offset.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastElement() returns "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastElement() should return the correct element considering the sublist offset from the backing vector.</p>
     */
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

    /**
     * Test method to verify that firstElement() and lastElement() return the same value for single-element sublists.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement() and lastElement() return the same value for a sublist containing only one element,
     * ensuring consistency in element access methods for single-element collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate consistency between firstElement() and lastElement() for single-element sublists.
     * This ensures that both methods return the same value when the sublist contains exactly one element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a single-element sublist from index 1 to 2.
     * It calls both firstElement() and lastElement() and verifies that they return the same value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- firstElement() returns "2"</p>
     * <p>- lastElement() returns "2"</p>
     * <p>- firstElement() equals lastElement()</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement() and lastElement() should return the same value for a single-element sublist.</p>
     */
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

    /**
     * Test method to verify that setElementAt() correctly updates elements at valid indices in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() correctly updates elements at specified valid indices within a sublist,
     * ensuring proper element modification functionality while maintaining the sublist and backing vector integrity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic element modification functionality for valid indices in a sublist.
     * This ensures that the sublist correctly updates elements while preserving size and maintaining consistency
     * with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from index 1 to 3.
     * It calls setElementAt() with index 0 and value "4", then verifies that the element was updated correctly,
     * the size remains unchanged, and unaffected elements in both sublist and backing vector remain intact.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at sublist index 0 is updated to "4"</p>
     * <p>- Sublist size remains 2</p>
     * <p>- Backing vector elements at indices 0 and 2 remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should update the element at the specified index while maintaining sublist and vector integrity.</p>
     */
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

    /**
     * Test method to verify that setElementAt() throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() throws ArrayIndexOutOfBoundsException when called with a negative index,
     * ensuring proper bounds checking for element modification operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative indices during element modification.
     * This ensures robustness and proper error reporting when attempting to update elements with invalid indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, then creates a sublist covering the entire vector.
     * It calls setElementAt() with index -1 and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No elements are modified in the sublist or backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should throw ArrayIndexOutOfBoundsException when called with a negative index.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.setElementAt("3", -1);
    }

    /**
     * Test method to verify that setElementAt() throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() throws ArrayIndexOutOfBoundsException when called with an index equal to the sublist size,
     * ensuring proper bounds checking at the upper limit for element modification operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for indices at the upper boundary of the sublist during element modification.
     * This ensures robustness and proper error reporting when attempting to update elements beyond the valid range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls setElementAt() with index 3 (equal to size) and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No elements are modified in the sublist or backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should throw ArrayIndexOutOfBoundsException when called with an index equal to the sublist size.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.setElementAt("3", 2);
    }

    /**
     * Test method to verify that setElementAt() throws ArrayIndexOutOfBoundsException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() throws ArrayIndexOutOfBoundsException when called on an empty sublist,
     * ensuring proper exception handling for edge cases during element modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an empty sublist during element modification.
     * This ensures robustness and proper error reporting when attempting to update elements in an empty sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, then creates an empty sublist from index 1 to 1.
     * It calls setElementAt() with index 0 and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No elements are modified in the sublist or backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should throw ArrayIndexOutOfBoundsException when called on an empty sublist.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetElementAtOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.setElementAt("1", 0);
    }

    /**
     * Test method to verify that setElementAt() correctly updates elements at the beginning of a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() correctly updates the first element of a sublist,
     * ensuring proper element modification functionality at boundary positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element modification functionality at the beginning of a sublist.
     * This ensures that the method correctly handles boundary conditions and maintains consistency with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls setElementAt() with index 0 and value "0", then verifies that the first element was updated correctly
     * while other elements remain unchanged in both sublist and backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First element of sublist is updated to "0"</p>
     * <p>- Other elements remain unchanged in sublist</p>
     * <p>- First element of backing vector is updated to "0"</p>
     * <p>- Other elements remain unchanged in backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should correctly update the first element while preserving other elements and maintaining vector consistency.</p>
     */
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

    /**
     * Test method to verify that setElementAt() correctly updates elements at the end of a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() correctly updates the last element of a sublist,
     * ensuring proper element modification functionality at boundary positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element modification functionality at the end of a sublist.
     * This ensures that the method correctly handles boundary conditions and maintains consistency with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls setElementAt() with index 2 and value "4", then verifies that the last element was updated correctly
     * while other elements remain unchanged in both sublist and backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Last element of sublist is updated to "4"</p>
     * <p>- Other elements remain unchanged in sublist</p>
     * <p>- Last element of backing vector is updated to "4"</p>
     * <p>- Other elements remain unchanged in backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should correctly update the last element while preserving other elements and maintaining vector consistency.</p>
     */
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

    /**
     * Test method to verify that setElementAt() correctly handles null values.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() correctly updates elements to null values,
     * ensuring proper handling of null elements during modification operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null values during element modification.
     * This ensures that the implementation correctly supports null elements and maintains consistency with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls setElementAt() with index 1 and value null, then verifies that the element was set to null correctly
     * while other elements remain unchanged in both sublist and backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 of sublist is set to null</p>
     * <p>- Other elements remain unchanged in sublist</p>
     * <p>- Element at index 1 of backing vector is set to null</p>
     * <p>- Other elements remain unchanged in backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should correctly set elements to null while preserving other elements and maintaining vector consistency.</p>
     */
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

    /**
     * Test method to verify that setElementAt() correctly handles multiple consecutive updates at the same index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() correctly handles multiple consecutive updates at the same index,
     * ensuring that the last update determines the final value and maintains consistency with the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of multiple modifications at the same position.
     * This ensures that the implementation correctly handles sequential updates and maintains data integrity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls setElementAt() three times at index 1 with different values, then verifies that the element
     * contains the last set value and that the backing vector is consistently updated.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 contains the last set value "6"</p>
     * <p>- Sublist size remains unchanged</p>
     * <p>- Backing vector element at index 1 contains the last set value "6"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should correctly handle multiple updates at the same index, with the last update determining the final value.</p>
     */
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

    /**
     * Test method to verify that setElementAt() correctly handles offset calculations during element modification.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt() correctly calculates offsets when updating elements in a sublist,
     * ensuring proper index mapping between the sublist and backing vector during modification operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper offset calculation in sublist element modification.
     * This ensures that the sublist correctly maps its indices to the corresponding positions in the backing vector
     * when updating elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist starting from index 3.
     * It calls setElementAt() with index 0 and value "4", then verifies that the element was updated correctly
     * in both the sublist and the corresponding position in the backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 3 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at sublist index 0 is updated to "4"</p>
     * <p>- Element at backing vector index 3 is updated to "4"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt() should correctly update elements considering the sublist offset from the backing vector.</p>
     */
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

    /**
     * Test method to verify that removeElementAt() correctly removes elements at valid indices in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() correctly removes elements at specified valid indices within a sublist,
     * ensuring proper element removal functionality while maintaining the sublist and backing vector integrity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic element removal functionality for valid indices in a sublist.
     * This ensures that the sublist correctly removes elements, decreases size, shifts subsequent elements,
     * and maintains consistency with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist from index 1 to 4.
     * It calls removeElementAt() with index 1, then verifies that the element was removed correctly,
     * the size was decreased, elements were properly shifted, and the backing vector was updated accordingly.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at sublist index 1 is removed</p>
     * <p>- Sublist size decreases to 2</p>
     * <p>- Remaining elements are properly shifted</p>
     * <p>- Backing vector size decreases to 3</p>
     * <p>- Backing vector elements are properly shifted</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should correctly remove the element at the specified index while maintaining sublist and vector integrity.</p>
     */
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

    /**
     * Test method to verify that removeElementAt() throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() throws ArrayIndexOutOfBoundsException when called with a negative index,
     * ensuring proper bounds checking for element removal operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative indices during element removal.
     * This ensures robustness and proper error reporting when attempting to remove elements with invalid indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls removeElementAt() with index -1 and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No elements are removed from the sublist or backing vector</p>
     * <p>- Sizes remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should throw ArrayIndexOutOfBoundsException when called with a negative index.</p>
     */
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

    /**
     * Test method to verify that removeElementAt() throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() throws ArrayIndexOutOfBoundsException when called with an index equal to the sublist size,
     * ensuring proper bounds checking at the upper limit for element removal operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for indices at the upper boundary of the sublist during element removal.
     * This ensures robustness and proper error reporting when attempting to remove elements beyond the valid range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls removeElementAt() with index 3 (equal to size) and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No elements are removed from the sublist or backing vector</p>
     * <p>- Sizes remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should throw ArrayIndexOutOfBoundsException when called with an index equal to the sublist size.</p>
     */
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

    /**
     * Test method to verify that removeElementAt() throws ArrayIndexOutOfBoundsException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() throws ArrayIndexOutOfBoundsException when called on an empty sublist,
     * ensuring proper exception handling for edge cases during element removal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for the edge case of an empty sublist during element removal.
     * This ensures robustness and proper error reporting when attempting to remove elements from an empty sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, then creates an empty sublist from index 1 to 1.
     * It calls removeElementAt() with index 0 and expects ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No elements are removed from the sublist or backing vector</p>
     * <p>- Sizes remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should throw ArrayIndexOutOfBoundsException when called on an empty sublist.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveElementAtOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.removeElementAt(0);
    }

    /**
     * Test method to verify that removeElementAt() correctly removes elements at the beginning of a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() correctly removes the first element of a sublist,
     * ensuring proper element removal functionality at boundary positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal functionality at the beginning of a sublist.
     * This ensures that the method correctly handles boundary conditions, shifts elements, and maintains
     * consistency with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls removeElementAt() with index 0, then verifies that the first element was removed correctly,
     * elements were properly shifted, and the backing vector was updated accordingly.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First element of sublist is removed</p>
     * <p>- Sublist size decreases to 2</p>
     * <p>- Remaining elements are properly shifted</p>
     * <p>- Backing vector size decreases to 2</p>
     * <p>- Backing vector elements are properly shifted</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should correctly remove the first element while shifting other elements and maintaining vector consistency.</p>
     */
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

    /**
     * Test method to verify that removeElementAt() correctly removes elements at the end of a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() correctly removes the last element of a sublist,
     * ensuring proper element removal functionality at boundary positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal functionality at the end of a sublist.
     * This ensures that the method correctly handles boundary conditions and maintains consistency
     * with the backing vector when removing the last element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist covering the entire vector.
     * It calls removeElementAt() with index 2, then verifies that the last element was removed correctly,
     * other elements remain unchanged, and the backing vector was updated accordingly.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Last element of sublist is removed</p>
     * <p>- Sublist size decreases to 2</p>
     * <p>- Other elements remain unchanged</p>
     * <p>- Backing vector size decreases to 2</p>
     * <p>- Backing vector elements remain unchanged except for removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should correctly remove the last element while preserving other elements and maintaining vector consistency.</p>
     */
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

    /**
     * Test method to verify that removeElementAt() correctly handles null elements during removal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() correctly removes null elements from a sublist,
     * ensuring proper handling of null values during removal operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements during element removal.
     * This ensures that the implementation correctly removes null elements and maintains consistency with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements including a null value, then creates a sublist covering the entire vector.
     * It calls removeElementAt() with the index of the null element, then verifies that the null element was removed correctly,
     * elements were properly shifted, and the backing vector was updated accordingly.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Null element is removed from sublist</p>
     * <p>- Sublist size decreases to 2</p>
     * <p>- Remaining elements are properly shifted</p>
     * <p>- Backing vector size decreases to 2</p>
     * <p>- Backing vector elements are properly shifted</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should correctly remove null elements while shifting other elements and maintaining vector consistency.</p>
     */
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

    /**
     * Test method to verify that removeElementAt() correctly handles multiple consecutive removals.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() correctly handles multiple consecutive removals from a sublist,
     * ensuring proper element removal functionality and maintenance of sublist and backing vector integrity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of multiple consecutive removal operations.
     * This ensures that the implementation correctly handles sequential removals, updates sizes,
     * and maintains data integrity in both sublist and backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements, then creates a sublist from index 1 to 4.
     * It calls removeElementAt() twice at consecutive positions, then verifies that the elements were removed correctly,
     * the size was properly decreased, and the backing vector was updated accordingly with proper element shifting.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Two elements are removed from sublist</p>
     * <p>- Sublist size decreases to 1</p>
     * <p>- Remaining element is properly positioned</p>
     * <p>- Backing vector size decreases to 3</p>
     * <p>- Backing vector elements are properly shifted</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should correctly handle multiple consecutive removals while maintaining sublist and vector integrity.</p>
     */
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

    /**
     * Test method to verify that removeElementAt() correctly handles offset calculations during element removal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt() correctly calculates offsets when removing elements from a sublist,
     * ensuring proper index mapping between the sublist and backing vector during removal operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper offset calculation in sublist element removal.
     * This ensures that the sublist correctly maps its indices to the corresponding positions in the backing vector
     * when removing elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, then creates a sublist starting from index 2.
     * It calls removeElementAt() with index 0, then verifies that the element was removed correctly
     * from both the sublist and the corresponding position in the backing vector, with proper size updates
     * and element shifting.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at sublist index 0 is removed</p>
     * <p>- Sublist size decreases to 1</p>
     * <p>- Remaining element is properly positioned</p>
     * <p>- Backing vector size decreases to 3</p>
     * <p>- Backing vector elements are properly shifted</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt() should correctly remove elements considering the sublist offset from the backing vector.</p>
     */
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

    /**
     * Test method to verify that insertElementAt() correctly inserts an element at a valid index within the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() correctly inserts an element at a valid index, shifting subsequent elements
     * and updating both sublist and backing vector sizes appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the core functionality of element insertion at a valid position within a sublist,
     * ensuring proper element shifting and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist covering all elements, inserts a new element
     * at index 1, and verifies the resulting element order and size changes in both the sublist and backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 3 to 4</p>
     * <p>- Element "2" is inserted at index 1</p>
     * <p>- Original elements are shifted right appropriately</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should successfully insert the element at the specified position, shift subsequent elements,
     * and update both sublist and backing vector sizes.</p>
     */
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

    /**
     * Test method to verify that insertElementAt() throws ArrayIndexOutOfBoundsException when inserting at a negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() throws ArrayIndexOutOfBoundsException when attempting to insert
     * an element at a negative index, ensuring proper bounds checking.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative index values, ensuring the method
     * maintains proper bounds validation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist, and attempts to insert an element
     * at index -1, expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Sublist and vector remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should throw ArrayIndexOutOfBoundsException when called with a negative index.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListInsertElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.insertElementAt("3", -1);
    }

    /**
     * Test method to verify that insertElementAt() throws ArrayIndexOutOfBoundsException when inserting at an index greater than size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() throws ArrayIndexOutOfBoundsException when attempting to insert
     * an element at an index greater than the current sublist size, ensuring proper bounds checking.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for index values beyond the current sublist size,
     * ensuring the method maintains proper bounds validation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist, and attempts to insert an element
     * at index 3 (which is beyond the sublist size of 2), expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Sublist and vector remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should throw ArrayIndexOutOfBoundsException when called with an index greater than size.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListInsertElementAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.insertElementAt("3", 3);
    }

    /**
     * Test method to verify that insertElementAt() correctly inserts an element into an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() correctly inserts an element into an empty sublist,
     * creating a single-element sublist and updating the backing vector appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion into empty sublists, ensuring proper handling
     * of edge cases where the sublist has no elements initially.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates an empty sublist (from index 1 to 1),
     * inserts an element at index 0, and verifies the resulting sublist size and content.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 1</p>
     * <p>- Inserted element is at index 0</p>
     * <p>- Backing vector size increases to 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should successfully insert an element into an empty sublist, creating a single-element
     * sublist and updating the backing vector size.</p>
     */
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

    /**
     * Test method to verify that insertElementAt() correctly inserts an element at the beginning of the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() correctly inserts an element at the beginning of the sublist,
     * shifting all existing elements right and updating sizes appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at the beginning of a sublist, ensuring proper
     * shifting of all existing elements and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist covering all elements,
     * inserts an element at index 0, and verifies the resulting element order and size changes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 3 to 4</p>
     * <p>- Element "1" is inserted at index 0</p>
     * <p>- All original elements are shifted right by one position</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should successfully insert an element at the beginning, shift all existing elements right,
     * and update both sublist and backing vector sizes.</p>
     */
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

    /**
     * Test method to verify that insertElementAt() correctly inserts an element at the end of the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() correctly inserts an element at the end of the sublist,
     * appending it without shifting existing elements and updating sizes appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at the end of a sublist, ensuring proper
     * appending behavior without affecting existing elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist covering all elements,
     * inserts an element at index 3 (the end), and verifies the resulting element order and size changes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 3 to 4</p>
     * <p>- Element "4" is appended at the end</p>
     * <p>- All original elements remain in their positions</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should successfully append an element at the end, maintain existing element positions,
     * and update both sublist and backing vector sizes.</p>
     */
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

    /**
     * Test method to verify that insertElementAt() correctly inserts a null element into the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() correctly handles null element insertion,
     * maintaining proper element shifting and size management.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate null element insertion, ensuring the method properly handles
     * null values without throwing exceptions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist covering all elements,
     * inserts a null element at index 1, and verifies the resulting element order and size changes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 2 to 3</p>
     * <p>- Null element is inserted at index 1</p>
     * <p>- Subsequent elements are shifted right appropriately</p>
     * <p>- Backing vector size increases to 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should successfully insert a null element, shift subsequent elements right,
     * and update both sublist and backing vector sizes.</p>
     */
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

    /**
     * Test method to verify that insertElementAt() correctly handles multiple consecutive insertions into the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() correctly handles multiple consecutive insertions,
     * maintaining proper element ordering and size management through multiple operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the sublist's behavior during multiple insertion operations,
     * ensuring proper element positioning and size tracking.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist covering all elements,
     * inserts two elements consecutively at different positions, and verifies the final element order and size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "4"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 4 after two insertions</p>
     * <p>- Elements are inserted in correct positions with proper shifting</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should successfully handle multiple consecutive insertions, maintaining
     * correct element ordering and updating sizes appropriately.</p>
     */
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

    /**
     * Test method to verify that insertElementAt() correctly handles insertions in a sublist with offset from the backing vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt() correctly handles element insertion in a sublist that has
     * an offset from the beginning of the backing vector, ensuring proper positioning in both sublist and vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion operations in sublists that don't start at vector index 0,
     * ensuring proper offset calculation and element positioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist starting at index 1,
     * inserts two elements at different positions, and verifies the element order in both sublist and backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 4 after two insertions</p>
     * <p>- Elements are inserted in correct positions with proper shifting</p>
     * <p>- Backing vector size increases to 6</p>
     * <p>- Elements outside sublist range remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt() should correctly handle insertions in offset sublists, maintaining proper
     * element positioning in both sublist and backing vector.</p>
     */
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

    /**
     * Test method to verify that addElement() correctly appends an element to a non-empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addElement() correctly appends an element to a non-empty sublist,
     * adding it to the end and updating sizes appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element appending to non-empty sublists, ensuring proper
     * addition at the end without affecting existing elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist covering the last two elements,
     * appends an element, and verifies the resulting element order and size changes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 2 to 3</p>
     * <p>- Element is appended at the end</p>
     * <p>- Existing elements remain unchanged</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addElement() should successfully append an element to the end of a non-empty sublist,
     * maintaining existing elements and updating sizes appropriately.</p>
     */
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

    /**
     * Test method to verify that addElement() correctly adds an element to an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addElement() correctly adds an element to an empty sublist,
     * creating a single-element sublist and updating the backing vector appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element addition to empty sublists, ensuring proper handling
     * of edge cases where the sublist has no elements initially.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates an empty sublist (from index 1 to 1),
     * adds an element, and verifies the resulting sublist size and content.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 1</p>
     * <p>- Element is added at index 0</p>
     * <p>- Backing vector size increases to 4</p>
     * <p>- Sublist is no longer empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addElement() should successfully add an element to an empty sublist, creating a single-element
     * sublist and updating the backing vector size.</p>
     */
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

    /**
     * Test method to verify that addElement() correctly handles null element addition to a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addElement() correctly handles null element addition,
     * appending it to the end of the sublist without throwing exceptions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate null element addition, ensuring the method properly handles
     * null values without throwing exceptions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist covering the last two elements,
     * adds a null element, and verifies the resulting element order and size changes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 2 to 3</p>
     * <p>- Null element is appended at the end</p>
     * <p>- Existing elements remain unchanged</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addElement() should successfully append a null element to the end of the sublist,
     * maintaining existing elements and updating sizes appropriately.</p>
     */
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

    /**
     * Test method to verify that addElement() correctly handles element addition in a sublist with offset from the backing vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addElement() correctly handles element addition in a sublist that has
     * an offset from the beginning of the backing vector, ensuring proper positioning in both sublist and vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate addition operations in sublists that don't start at vector index 0,
     * ensuring proper offset calculation and element positioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist starting at index 2,
     * adds an element, and verifies the element order in both sublist and backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 2 to 3</p>
     * <p>- Element is appended at the end of the sublist</p>
     * <p>- Backing vector size increases to 5</p>
     * <p>- Elements outside sublist range remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addElement() should correctly handle element addition in offset sublists, maintaining proper
     * element positioning in both sublist and backing vector.</p>
     */
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

    /**
     * Test method to verify that removeElement() correctly removes an existing element from the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement() successfully removes an existing element from the sublist,
     * decreases the sublist size, shifts subsequent elements, and updates the backing vector accordingly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the removal of existing elements from a sublist, ensuring proper
     * size management, element shifting, and synchronization with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist from indices 1 to 4,
     * calls removeElement("2") on the sublist, and verifies that the method returns true, the sublist size
     * decreases to 2, elements are properly shifted, and the backing vector size decreases to 3.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement() returns true</p>
     * <p>- Sublist size decreases from 3 to 2</p>
     * <p>- Elements are properly shifted within the sublist</p>
     * <p>- Backing vector size decreases from 4 to 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement() should return true, sublist size should be 2, elements should be properly
     * shifted, and backing vector size should be 3 after removing an existing element.</p>
     */
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

    /**
     * Test method to verify that removeElement() returns false when attempting to remove a non-existing element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement() returns false when attempting to remove an element
     * that doesn't exist in the sublist, and that both sublist and backing vector remain unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of removeElement() when attempting to remove
     * non-existing elements, ensuring no modifications occur to the sublist or backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from indices 1 to 3,
     * calls removeElement("1") on the sublist (which contains elements ["2", "3"]), and verifies that
     * the method returns false and both sublist and backing vector sizes remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement() returns false</p>
     * <p>- Sublist size remains unchanged at 2</p>
     * <p>- Backing vector size remains unchanged at 3</p>
     * <p>- All elements remain in their original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement() should return false, and both sublist and backing vector should remain
     * completely unchanged when attempting to remove a non-existing element.</p>
     */
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

    /**
     * Test method to verify that removeElement() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement() successfully removes null elements from the sublist
     * and properly updates both the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the removal of null elements from a sublist, ensuring
     * proper handling of null values and correct synchronization with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing a null element, creates a sublist containing
     * only the null element, calls removeElement(null) on the sublist, and verifies that the method
     * returns true, the sublist becomes empty, and the backing vector size decreases appropriately.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 (containing only the null element)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement() returns true</p>
     * <p>- Sublist becomes empty (size 0)</p>
     * <p>- Backing vector size decreases from 3 to 2</p>
     * <p>- Remaining elements are properly positioned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement() should return true when removing null elements, the sublist should become
     * empty, and the backing vector should be properly updated.</p>
     */
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

    /**
     * Test method to verify that removeElement() returns false when called on an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement() returns false when attempting to remove any element
     * from an empty sublist, and that both sublist and backing vector remain unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of removeElement() on empty sublists,
     * ensuring no modifications occur and the method correctly returns false.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates an empty sublist (from index 1 to 1),
     * calls removeElement("2") on the empty sublist, and verifies that the method returns false and
     * both sublist and backing vector remain completely unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement() returns false</p>
     * <p>- Sublist remains empty (size 0)</p>
     * <p>- Backing vector size remains unchanged at 3</p>
     * <p>- All elements remain in their original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement() should return false when called on an empty sublist, and no modifications
     * should occur to either the sublist or backing vector.</p>
     */
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

    /**
     * Test method to verify that removeElement() only removes the first occurrence of duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement() only removes the first occurrence when multiple
     * identical elements exist in the sublist, and properly updates the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that removeElement() follows the specified behavior of
     * removing only the first occurrence of duplicate elements, ensuring proper element shifting
     * and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements, creates a sublist containing
     * the duplicates, calls removeElement("2") on the sublist, and verifies that only the first
     * occurrence is removed, the sublist size decreases by 1, and elements are properly shifted.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement() returns true</p>
     * <p>- Sublist size decreases from 3 to 2</p>
     * <p>- First occurrence of "2" is removed</p>
     * <p>- Second occurrence of "2" remains and shifts to first position</p>
     * <p>- Backing vector size decreases from 4 to 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement() should return true and only remove the first occurrence of duplicate elements,
     * with proper shifting of remaining elements and size updates.</p>
     */
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


    /**
     * Test method to verify that removeElement() works correctly with sublists that have offset positions.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement() correctly handles elements in sublists with non-zero
     * offsets, ensuring proper removal and synchronization with the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of removeElement() on sublists that start at
     * non-zero indices, ensuring proper offset calculation and element management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist from the last element
     * (indices 3 to 4), calls removeElement("3") on the sublist, and verifies that the element is
     * correctly removed, the sublist becomes empty, and the backing vector is properly updated.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 3 to 4 (containing only "3")</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement() returns true</p>
     * <p>- Sublist becomes empty (size 0)</p>
     * <p>- Backing vector size decreases from 4 to 3</p>
     * <p>- Remaining elements are properly positioned with correct values</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement() should return true and correctly remove elements from sublists with non-zero
     * offsets, with proper updates to both sublist and backing vector.</p>
     */
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

    /**
     * Test method to verify that removeAllElements() works correctly on empty sublists.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAllElements() has no effect when called on empty sublists,
     * and that both sublist and backing vector remain unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of removeAllElements() on empty sublists,
     * ensuring no modifications occur and the method executes without errors.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates an empty sublist (from index 1 to 1),
     * calls removeAllElements() on the empty sublist, and verifies that the sublist remains empty
     * and the backing vector remains completely unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist remains empty (size 0)</p>
     * <p>- Sublist remains empty as verified by isEmpty()</p>
     * <p>- Backing vector size remains unchanged at 3</p>
     * <p>- All elements remain in their original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAllElements() should have no effect on empty sublists, and both sublist and backing
     * vector should remain completely unchanged.</p>
     */
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

    /**
     * Test method to verify that removeAllElements() correctly removes all elements from non-empty sublists.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAllElements() successfully removes all elements from non-empty
     * sublists and properly updates both the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the complete removal of all elements from sublists using
     * removeAllElements(), ensuring proper size management and synchronization with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist containing three elements,
     * calls removeAllElements() on the sublist, and verifies that the sublist becomes empty and the
     * backing vector size decreases appropriately while preserving elements outside the sublist range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist becomes empty (size 0)</p>
     * <p>- Sublist is empty as verified by isEmpty()</p>
     * <p>- Backing vector size decreases from 4 to 1</p>
     * <p>- Element before sublist range remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAllElements() should completely empty the sublist, decrease the backing vector size
     * appropriately, and preserve elements outside the sublist range.</p>
     */
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

    /**
     * Test method to verify that removeAllElements() correctly handles sublists containing null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAllElements() successfully removes all elements including
     * null values from sublists and properly updates both the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the removal of all elements including null values from
     * sublists using removeAllElements(), ensuring proper handling of null elements and correct
     * synchronization with the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing null elements, creates a sublist containing
     * the null elements, calls removeAllElements() on the sublist, and verifies that all elements
     * including nulls are removed and the backing vector is properly updated.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", null, "3", null]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist becomes empty (size 0)</p>
     * <p>- Sublist is empty as verified by isEmpty()</p>
     * <p>- Backing vector size decreases from 4 to 1</p>
     * <p>- Element before sublist range remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAllElements() should completely empty the sublist including null elements, decrease
     * the backing vector size appropriately, and preserve elements outside the sublist range.</p>
     */
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

    /**
     * Test method to verify that clone() works correctly on empty sublists.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone() creates a proper copy of empty sublists, maintaining
     * the empty state and size while creating an independent instance.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the cloning functionality for empty sublists, ensuring
     * that the clone is a proper independent copy with the same empty state.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates an empty sublist (from index 1 to 1),
     * calls clone() on the empty sublist, and verifies that the clone is not null, has the same size (0),
     * and is also empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Clone is not null</p>
     * <p>- Clone size matches original (0)</p>
     * <p>- Clone is empty as verified by isEmpty()</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone() should create a non-null copy of empty sublists with the same size (0) and empty state.</p>
     */
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

    /**
     * Test method to verify that clone() creates a proper copy of sublists with multiple elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone() creates an exact independent copy of sublists containing
     * multiple elements, preserving all element values and order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the cloning functionality for sublists with multiple elements,
     * ensuring that all elements are properly copied and the clone is independent from the original.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist containing all elements,
     * calls clone() on the sublist, and verifies that the clone contains all elements in the same order
     * and has the same size as the original.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Clone is not null</p>
     * <p>- Clone size matches original (3)</p>
     * <p>- All elements are preserved in the same order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone() should create an exact independent copy of the sublist with all elements preserved
     * in the same order and the same size.</p>
     */
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

    /**
     * Test method to verify that clone() correctly handles sublists containing null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone() properly copies sublists containing null elements,
     * preserving the null values in the cloned instance.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the cloning functionality for sublists containing null elements,
     * ensuring that null values are properly preserved in the clone.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with all null elements, creates a sublist containing these nulls,
     * calls clone() on the sublist, and verifies that all null elements are preserved in the clone
     * and the size matches the original.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements: [null, null, null]</p>
     * <p>- SubListAdapter is created from indices 0 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Clone is not null</p>
     * <p>- Clone size matches original (3)</p>
     * <p>- All null elements are preserved in the clone</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone() should create a copy of the sublist with all null elements preserved and the same size.</p>
     */
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
    /**
     * Test method to verify that clone() creates independent instances that don't affect each other.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone() creates truly independent instances, where modifications
     * to the original sublist don't affect the clone and vice versa.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the independence of cloned sublists, ensuring that
     * modifications to either the original or clone don't affect the other instance.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist, clones it,
     * modifies an element in both original and clone, and verifies that the changes are independent
     * and don't affect the other instance.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Clone is not null</p>
     * <p>- Modifications to clone don't affect original</p>
     * <p>- Modifications to original don't affect clone</p>
     * <p>- Both instances maintain their independent states</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone() should create independent instances where modifications to one don't affect the other.</p>
     */
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

    /**
     * Test method to verify that clone() works correctly with sublists that have offset positions.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone() correctly handles sublists with non-zero offsets,
     * preserving the correct elements and their values in the cloned instance.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the cloning functionality for sublists with offset positions,
     * ensuring that the clone contains the correct elements from the offset range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist from a non-zero offset,
     * calls clone() on the sublist, and verifies that the clone contains the correct elements from
     * the offset range and has the same size as the original sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Clone is not null</p>
     * <p>- Clone size matches original (2)</p>
     * <p>- Clone contains correct elements from offset range</p>
     * <p>- Element values are preserved correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone() should create a proper copy of sublists with offset positions, preserving the
     * correct elements and their values.</p>
     */
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

    /**
     * Test method to verify that toArray() returns an empty array for an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray() returns a non-null empty array when called on an empty sublist,
     * ensuring proper handling of empty ranges in sublist operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of toArray() on empty sublists, which is a boundary case
     * that must return a valid but empty array representation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, then creates an empty sublist (from index 1 to 1)
     * and calls toArray() on it. The returned array should be non-null and have length 0.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray() returns a non-null array</p>
     * <p>- Array length is 0</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray() should return an empty array when called on an empty sublist.</p>
     */
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

    /**
     * Test method to verify that toArray() correctly copies multiple elements from a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray() returns an array containing all elements from a non-empty sublist
     * in the correct order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the normal operation of toArray() on a sublist containing multiple elements,
     * ensuring proper element copying and order preservation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist from indices 0 to 2 (exclusive),
     * and calls toArray() on it. The returned array should contain the first two elements of the original vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray() returns a non-null array</p>
     * <p>- Array length is 2</p>
     * <p>- Array contains elements ["1", "2"] in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray() should return an array with all sublist elements in proper order.</p>
     */
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

    /**
     * Test method to verify that toArray() correctly handles null elements in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray() properly handles null elements within a sublist,
     * preserving the null values in the returned array.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that null elements are correctly transferred to the array
     * without conversion or alteration, maintaining the exact element representation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector containing a null element, creates a sublist encompassing the null element,
     * and calls toArray(). The returned array should preserve the null value at the correct position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray() returns a non-null array</p>
     * <p>- Array contains elements ["1", null]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray() should preserve null elements in the returned array.</p>
     */
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

    /**
     * Test method to verify that toArray() works correctly with sublists that have an offset from the main vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray() correctly handles sublists that start at a non-zero offset,
     * ensuring proper element indexing and copying.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublist offset calculations work correctly with toArray(),
     * particularly when the sublist doesn't start at the beginning of the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with four elements, creates a sublist starting at index 2,
     * and calls toArray(). The returned array should contain elements from the original vector starting at index 2.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray() returns a non-null array</p>
     * <p>- Array contains elements ["2", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray() should correctly handle sublists with non-zero offsets.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) returns the same empty array for an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) returns the input array unchanged when the sublist is empty,
     * following the contract for empty collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case behavior where an empty sublist is converted to an array
     * using a provided array reference, which should be returned unchanged.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates an empty sublist, and calls toArray()
     * with an empty array. The method should return the same array reference.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     * <p>- Empty object array is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray(T[]) returns the same array reference</p>
     * <p>- Array length remains 0</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should return the input array unchanged for empty sublists.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) works correctly with an array of exact size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) correctly populates and returns the input array
     * when it has exactly the same size as the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the optimal case where the provided array has exactly
     * the required size, avoiding unnecessary array creation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and calls toArray()
     * with an array of exactly the sublist size. The method should populate and return the same array.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- Object array of size 2 is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray(T[]) returns the same array reference</p>
     * <p>- Array contains elements ["1", "2"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should populate and return the input array when it has exact size.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) correctly handles larger arrays with null termination.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) properly handles arrays larger than the sublist size
     * by null-terminating the result and preserving existing elements beyond the sublist content.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the contract requirement that when the array is larger than
     * the collection, the element immediately following the collection content is set to null.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and calls toArray()
     * with a larger array containing pre-existing values. The method should populate the array,
     * null-terminate the result, and preserve existing elements beyond the sublist content.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     * <p>- Object array of size 5 with pre-populated values is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray(T[]) returns the same array reference</p>
     * <p>- Array contains elements ["2", "3", null, "0", "0"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should null-terminate and preserve existing elements in larger arrays.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) creates a new array when the input array is too small.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) creates and returns a new array when the provided array
     * is smaller than the sublist size, following the collection contract.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the case where the provided array is insufficiently sized,
     * requiring the method to allocate a new array of appropriate size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a full sublist, and calls toArray()
     * with a smaller array. The method should create and return a new array of correct size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     * <p>- Object array of size 2 is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray(T[]) returns a different array reference</p>
     * <p>- New array contains all three elements ["1", "2", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should create a new array when the input array is too small.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) correctly handles null elements in larger arrays.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) properly handles null elements within the sublist
     * when using a larger provided array, ensuring correct null termination and element preservation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the combination of null elements in the sublist and
     * the use of a larger array, testing both null handling and array size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector containing a null element, creates a sublist encompassing all elements,
     * and calls toArray() with a larger array. The method should correctly transfer null elements
     * and properly null-terminate the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     * <p>- Object array of size 5 is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray(T[]) returns the same array reference</p>
     * <p>- Array contains elements ["1", null, "3", null, unchanged]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should correctly handle null elements and proper null termination.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) throws NullPointerException when given a null array.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) throws NullPointerException when the provided array is null,
     * as required by the collection interface contract.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for invalid null input,
     * ensuring robustness and contract compliance.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and calls toArray()
     * with a null array parameter, expecting a NullPointerException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should throw NullPointerException when given a null array.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) properly null-terminates for empty sublists in larger arrays.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) correctly null-terminates the result when an empty sublist
     * is converted using a larger array, ensuring existing array elements are preserved.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the specific behavior of null-termination for empty collections
     * when using larger arrays, including preservation of existing array content.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates an empty sublist, and calls toArray()
     * with a larger array containing pre-existing values. The method should set the first element to null
     * and preserve other array elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     * <p>- Object array of size 3 with pre-populated values is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray(T[]) returns the same array reference</p>
     * <p>- Array contains elements [null, "0", "0"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should null-terminate empty sublists and preserve existing array elements.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) creates a new array when given a zero-length array.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) creates and returns a new array when provided with
     * a zero-length array, as the provided array is insufficient for any non-empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where the provided array has zero length,
     * which should always trigger creation of a new array for non-empty collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and calls toArray()
     * with a zero-length array. The method should create and return a new array of appropriate size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     * <p>- Zero-length object array is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray(T[]) returns a different array reference</p>
     * <p>- New array contains elements ["2", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should create a new array when given a zero-length array for non-empty sublists.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) works correctly with sublists that have an offset.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) correctly handles sublists with non-zero offsets
     * when using a provided array, ensuring proper element copying and null termination.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublist offset calculations work correctly with
     * the toArray(T[]) method, particularly when the sublist doesn't start at the vector beginning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with four elements, creates a sublist starting at index 2,
     * and calls toArray() with a provided array. The method should correctly copy the offset elements
     * and properly null-terminate the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     * <p>- Object array of size 3 is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toArray(T[]) returns the same array reference</p>
     * <p>- Array contains elements ["2", "3", null]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should correctly handle sublists with non-zero offsets.</p>
     */
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

    /**
     * Test method to verify that get() returns correct elements for valid indices in a sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the get() method correctly returns elements at valid indices
     * within a sublist, ensuring proper element retrieval functionality.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic element retrieval functionality for valid indices
     * in a sublist, ensuring that elements are correctly accessed and returned.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from index 1 to 3,
     * and verifies that get() returns the correct elements at indices 0 and 1 of the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- get() returns correct elements at specified indices</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get() should return "2" at index 0 and "3" at index 1 of the sublist.</p>
     */
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

    /**
     * Test method to verify that get() throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that get() throws ArrayIndexOutOfBoundsException when called with
     * a negative index, ensuring proper bounds checking.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative indices,
     * ensuring robust error handling in the sublist implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from index 1 to 3,
     * and attempts to call get(-1), expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get(-1) should throw ArrayIndexOutOfBoundsException.</p>
     */
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

    /**
     * Test method to verify that get() throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that get() throws ArrayIndexOutOfBoundsException when called with
     * an index equal to the sublist size, ensuring proper bounds checking.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for indices at the upper bound,
     * ensuring robust error handling in the sublist implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from index 1 to 3,
     * and attempts to call get(2) (index equal to sublist size), expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get(2) should throw ArrayIndexOutOfBoundsException.</p>
     */
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

    /**
     * Test method to verify that get() throws ArrayIndexOutOfBoundsException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that get() throws ArrayIndexOutOfBoundsException when called on
     * an empty sublist, ensuring proper bounds checking for edge cases.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for empty sublists,
     * ensuring robust error handling in edge cases.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates an empty sublist
     * from index 1 to 1, and attempts to call get(0), expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Empty sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get(0) should throw ArrayIndexOutOfBoundsException on empty sublist.</p>
     */
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

    /**
     * Test method to verify that get() correctly handles null elements in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that get() correctly returns null for null elements in a sublist,
     * ensuring proper handling of null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements in sublists,
     * ensuring the implementation correctly supports null values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements including a null value, creates a sublist
     * containing the null element, and verifies that get() returns null for that position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- get() returns null for the element at index 0</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get(0) should return null for the null element in the sublist.</p>
     */
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


    /**
     * Test method to verify that get() works correctly with offset in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that get() correctly handles offset calculations in sublists,
     * ensuring proper element retrieval when the sublist has a non-zero starting index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper offset handling in sublists,
     * ensuring elements are correctly mapped between the main vector and sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist starting at index 3,
     * and verifies that get(0) returns the correct element from the main vector considering the offset.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 3 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- get() returns the correct element considering the offset</p>
     * <p>- Sublist remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get(0) should return "3" (element at index 3 of the main vector).</p>
     */
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

    /**
     * Test method to verify that set() correctly updates elements at valid indices in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly updates elements at valid indices in a sublist
     * and returns the previous value, ensuring proper element modification functionality.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic element modification functionality for valid indices
     * in a sublist, ensuring that elements are correctly updated and previous values are returned.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from index 1 to 3,
     * calls set(0, "4"), and verifies the return value, sublist contents, and main vector update.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- set() returns the previous element value</p>
     * <p>- Element at specified index is updated in sublist</p>
     * <p>- Corresponding element is updated in main vector</p>
     * <p>- Other elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(0, "4") should return "2", update sublist element at index 0 to "4",
     * and update main vector element at index 1 to "4".</p>
     */
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

    /**
     * Test method to verify that set() throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() throws ArrayIndexOutOfBoundsException when called with
     * a negative index, ensuring proper bounds checking during element modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative indices
     * during element modification, ensuring robust error handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist from index 0 to 2,
     * and attempts to call set(-1, "3"), expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     * <p>- Main vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(-1, "3") should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.set(-1, "3");
    }

    /**
     * Test method to verify that set() throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() throws ArrayIndexOutOfBoundsException when called with
     * an index equal to the sublist size, ensuring proper bounds checking during element modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for indices at the upper bound
     * during element modification, ensuring robust error handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist from index 0 to 2,
     * and attempts to call set(2, "3") (index equal to sublist size), expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     * <p>- Main vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(2, "3") should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.set(2, "3");
    }

    /**
     * Test method to verify that set() throws ArrayIndexOutOfBoundsException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() throws ArrayIndexOutOfBoundsException when called on
     * an empty sublist, ensuring proper bounds checking for edge cases during element modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for empty sublists
     * during element modification, ensuring robust error handling in edge cases.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates an empty sublist
     * from index 1 to 1, and attempts to call set(0, "1"), expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Empty sublist remains unchanged</p>
     * <p>- Main vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(0, "1") should throw ArrayIndexOutOfBoundsException on empty sublist.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListSetOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.set(0, "1");
    }

    /**
     * Test method to verify that set() correctly updates elements at the beginning of sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly updates elements at the beginning of a sublist
     * and properly propagates changes to the main vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element modification at the beginning of a sublist,
     * ensuring proper index mapping and main vector synchronization.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from index 0 to 3,
     * calls set(0, "0") at the beginning, and verifies the return value and updates to both sublist and main vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- set() returns the previous first element value</p>
     * <p>- First element is updated in sublist</p>
     * <p>- Corresponding element is updated in main vector</p>
     * <p>- Other elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(0, "0") should return "1", update sublist element at index 0 to "0",
     * and update main vector element at index 0 to "0".</p>
     */
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

    /**
     * Test method to verify that set() correctly updates elements at the end of sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly updates elements at the end of a sublist
     * and properly propagates changes to the main vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element modification at the end of a sublist,
     * ensuring proper index mapping and main vector synchronization.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from index 0 to 3,
     * calls set(2, "4") at the end, and verifies the return value and updates to both sublist and main vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- set() returns the previous last element value</p>
     * <p>- Last element is updated in sublist</p>
     * <p>- Corresponding element is updated in main vector</p>
     * <p>- Other elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(2, "4") should return "3", update sublist element at index 2 to "4",
     * and update main vector element at index 2 to "4".</p>
     */
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

    /**
     * Test method to verify that set() correctly handles null values in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly handles setting elements to null values
     * in a sublist and properly propagates null values to the main vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null values during element modification,
     * ensuring the implementation correctly supports null assignments.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from index 0 to 3,
     * calls set(1, null) to set an element to null, and verifies the return value and updates.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- set() returns the previous element value</p>
     * <p>- Element at specified index is set to null in sublist</p>
     * <p>- Corresponding element is set to null in main vector</p>
     * <p>- Other elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(1, null) should return "2", set sublist element at index 1 to null,
     * and set main vector element at index 1 to null.</p>
     */
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

    /**
     * Test method to verify that set() works correctly with multiple consecutive updates.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly handles multiple consecutive updates
     * to the same position in a sublist and properly propagates changes to the main vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of multiple modifications
     * to the same element, ensuring the implementation maintains consistency.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist from index 0 to 3,
     * calls set(1, "4"), set(1, "5"), and set(1, "6") consecutively on the same position,
     * and verifies the final state of both sublist and main vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 contains the last set value in sublist</p>
     * <p>- Corresponding element contains the last set value in main vector</p>
     * <p>- Size remains unchanged in both sublist and main vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After multiple set() calls, element at index 1 should be "6" in both sublist and main vector.</p>
     */
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

    /**
     * Test method to verify that set() correctly handles offset calculations in sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that set() correctly handles offset calculations in sublists
     * during element modification, ensuring proper element mapping between main vector and sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper offset handling during element modification,
     * ensuring elements are correctly mapped between the main vector and sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist starting at index 2,
     * calls set(0, "4") on the sublist, and verifies the return value and updates to both sublist and main vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- set() returns the previous element value considering the offset</p>
     * <p>- Element at specified index is updated in sublist</p>
     * <p>- Corresponding element is updated in main vector considering the offset</p>
     * <p>- Other elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(0, "4") should return "2", update sublist element at index 0 to "4",
     * and update main vector element at index 2 to "4".</p>
     */
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

    /**
     * Test method to verify that adding an element to a non-empty sublist works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element to a non-empty sublist increases both the sublist
     * and backing vector sizes correctly and maintains element ordering.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the add operation on a non-empty sublist, ensuring proper
     * size management and element positioning in both the sublist and backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist covering the entire vector,
     * adds a third element to the sublist, and verifies the sizes and element positions in both the
     * sublist and backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 2 to 3</p>
     * <p>- Backing vector size increases from 2 to 3</p>
     * <p>- New element is added at the end of both collections</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should return true, sublist size should be 3, and elements should be in correct positions
     * in both sublist and backing vector.</p>
     */
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

    /**
     * Test method to verify that adding an element to an empty sublist works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element to an empty sublist properly initializes the sublist
     * and updates the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the add operation on an empty sublist, ensuring the sublist
     * becomes non-empty and the backing vector is updated accordingly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates an empty sublist (range 1-1),
     * adds an element to the sublist, and verifies the sizes and element positions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 1 (from empty)</p>
     * <p>- Backing vector size increases from 2 to 3</p>
     * <p>- New element is added at the correct position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should return true, sublist should contain one element, and backing vector should
     * have size 3 with proper element ordering.</p>
     */
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

    /**
     * Test method to verify that adding a null element to a sublist works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that null elements can be added to sublists and are properly handled
     * in both the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements in the add operation,
     * ensuring null values are stored correctly and sizes are updated properly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist, adds a null element,
     * and verifies the null is stored correctly and sizes are updated.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 2 to 3</p>
     * <p>- Backing vector size increases from 2 to 3</p>
     * <p>- Null element is stored at the end position</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should return true, sublist should contain null at the last position, and both
     * collections should have size 3.</p>
     */
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

    /**
     * Test method to verify that adding elements to a sublist with offset works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding elements to a sublist with a non-zero offset properly
     * updates both the sublist and backing vector with correct element positioning.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the add operation on sublists that don't start at
     * the beginning of the backing vector, ensuring proper offset handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist starting at
     * index 2, adds an element, and verifies element positions and sizes in both collections.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size increases from 2 to 3</p>
     * <p>- Backing vector size increases from 4 to 5</p>
     * <p>- Elements are properly shifted in the backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should return true, sublist should contain the new element at the end, and
     * backing vector should have proper element ordering after the addition.</p>
     */
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

    /**
     * Test method to verify that adding elements to an empty vector sublist works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding elements to a sublist of an empty vector properly
     * initializes both the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the add operation on sublists created from empty
     * vectors, ensuring proper initialization and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, creates an empty sublist, adds an element,
     * and verifies the sizes and element positions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized empty</p>
     * <p>- SubListAdapter is created from indices 0 to 0 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 1 (from empty)</p>
     * <p>- Backing vector size becomes 1 (from empty)</p>
     * <p>- Element is stored at position 0 in both collections</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add() should return true, sublist should contain one element, and backing vector
     * should have size 1 with the element at position 0.</p>
     */
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

    /**
     * Test method to verify that removing existing elements from a sublist works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing existing elements from a sublist properly decreases
     * sizes and maintains element ordering in both the sublist and backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation for existing elements, ensuring
     * proper size management and element shifting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist, removes an
     * element, and verifies the sizes and element positions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases from 3 to 2</p>
     * <p>- Backing vector size decreases from 4 to 3</p>
     * <p>- Elements are properly shifted in both collections</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should return true, sublist size should be 2, and elements should be
     * in correct positions after removal.</p>
     */
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

    /**
     * Test method to verify that removing non-existing elements from a sublist works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that attempting to remove non-existing elements from a sublist
     * does not modify the collections and returns the appropriate result.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation for elements not present in
     * the sublist, ensuring no modifications occur.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist, attempts to
     * remove a non-existing element, and verifies no changes occurred.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size remains unchanged at 2</p>
     * <p>- Backing vector size remains unchanged at 3</p>
     * <p>- Element ordering remains unchanged in both collections</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should return false, and both collections should remain unchanged in
     * size and element ordering.</p>
     */
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

    /**
     * Test method to verify that removing null elements from a sublist works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that null elements can be properly removed from sublists and
     * both collections are updated correctly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation for null elements, ensuring
     * proper handling of null values and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing a null element, creates a sublist
     * containing the null, removes the null, and verifies the sizes and element positions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases from 1 to 0</p>
     * <p>- Backing vector size decreases from 3 to 2</p>
     * <p>- Elements are properly shifted in the backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should return true, sublist should become empty, and backing vector
     * should have proper element ordering after removal.</p>
     */
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

    /**
     * Test method to verify that removing elements from an empty sublist works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that attempting to remove elements from an empty sublist
     * does not modify the collections and returns the appropriate result.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation on empty sublists, ensuring
     * no modifications occur and the correct value is returned.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates an empty sublist,
     * attempts to remove an element, and verifies no changes occurred.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size remains unchanged at 0</p>
     * <p>- Backing vector size remains unchanged at 3</p>
     * <p>- Element ordering remains unchanged in both collections</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should return false, and both collections should remain unchanged in
     * size and element ordering.</p>
     */
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

    /**
     * Test method to verify that only the first occurrence is removed when multiple identical elements exist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that when multiple identical elements exist in a sublist, only
     * the first occurrence is removed during a remove operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the remove operation only affects the first
     * occurrence of duplicate elements, ensuring proper element shifting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements, creates a sublist containing
     * the duplicates, removes one occurrence, and verifies the correct element was removed.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases from 3 to 2</p>
     * <p>- Backing vector size decreases from 4 to 3</p>
     * <p>- Only the first occurrence of the duplicate is removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should return true, and the remaining elements should maintain correct
     * ordering with only the first duplicate removed.</p>
     */
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

    /**
     * Test method to verify that removing elements from a sublist with offset works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing elements from a sublist with a non-zero offset
     * properly updates both the sublist and backing vector with correct element shifting.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the remove operation on sublists that don't start
     * at the beginning of the backing vector, ensuring proper offset handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist at the end,
     * removes the only element, and verifies the sizes and element positions.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 3 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size decreases from 1 to 0</p>
     * <p>- Backing vector size decreases from 4 to 3</p>
     * <p>- Elements are properly shifted in the backing vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove() should return true, sublist should become empty, and backing vector
     * should have proper element ordering after removal.</p>
     */
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

    /**
     * Test method to verify that add(int index, Object element) works correctly at a valid position within the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element at a valid middle position in the sublist correctly
     * inserts the element and shifts subsequent elements to the right.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic insertion functionality of the sublist at a middle position,
     * ensuring proper element shifting and size management in both the sublist and backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist covering the entire vector,
     * inserts an element at index 1, and verifies the resulting element order and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 3</p>
     * <p>- Element "2" is inserted at index 1</p>
     * <p>- Original element "3" is shifted to index 2</p>
     * <p>- Vector size becomes 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The sublist should have size 3 with elements ["1", "2", "3"] and the backing vector should reflect these changes.</p>
     */
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

    /**
     * Test method to verify that add(int index, Object element) works correctly at the beginning of the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element at the beginning of the sublist correctly
     * inserts the element and shifts all existing elements to the right.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion at the beginning boundary of the sublist,
     * ensuring proper element shifting and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist covering the entire vector,
     * inserts an element at index 0, and verifies the resulting element order and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 3</p>
     * <p>- Element "1" is inserted at index 0</p>
     * <p>- Original elements are shifted right by one position</p>
     * <p>- Vector size becomes 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The sublist should have size 3 with elements ["1", "2", "3"] and the backing vector should reflect these changes.</p>
     */
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

    /**
     * Test method to verify that add(int index, Object element) works correctly at the end of the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element at the end of the sublist correctly
     * appends the element without affecting existing elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion at the end boundary of the sublist,
     * ensuring proper appending behavior without unnecessary shifting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist covering the entire vector,
     * inserts an element at index 2, and verifies the resulting element order and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 3</p>
     * <p>- Element "3" is appended at index 2</p>
     * <p>- Original elements remain unchanged</p>
     * <p>- Vector size becomes 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The sublist should have size 3 with elements ["1", "2", "3"] and the backing vector should reflect these changes.</p>
     */
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

    /**
     * Test method to verify that add(int index, Object element) works correctly on an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element to an empty sublist correctly
     * creates the first element and updates sizes appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion into an empty sublist,
     * ensuring proper initialization of the previously empty range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates an empty sublist (range 1-1),
     * inserts an element at index 0, and verifies the resulting element and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 1</p>
     * <p>- Element "3" is inserted at index 0</p>
     * <p>- Vector size becomes 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The sublist should have size 1 with element ["3"] and the backing vector should have size 3.</p>
     */
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

    /**
     * Test method to verify that add(int index, Object element) throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element at a negative index throws the appropriate exception,
     * preventing invalid index access.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative index values,
     * ensuring robust error checking in the sublist implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist,
     * and attempts to add an element at index -1, expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No changes to sublist or vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add(-1, "3") should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListAddAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add(-1, "3");
    }

    /**
     * Test method to verify that add(int index, Object element) throws ArrayIndexOutOfBoundsException for index greater than size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding an element at an index greater than the current size throws the appropriate exception,
     * preventing index overflow.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for index values beyond the current size,
     * ensuring proper bounds checking in the sublist implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist,
     * and attempts to add an element at index 3, expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No changes to sublist or vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add(3, "3") should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListAddAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.add(3, "3");
    }

    /**
     * Test method to verify that add(int index, Object element) works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding a null element at a valid position works correctly,
     * ensuring proper handling of null values in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion of null elements,
     * ensuring the sublist implementation properly handles null values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist,
     * inserts a null element at index 1, and verifies the resulting element order and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 3</p>
     * <p>- Null element is inserted at index 1</p>
     * <p>- Element "3" is shifted to index 2</p>
     * <p>- Vector size becomes 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The sublist should have size 3 with elements ["1", null, "3"] and the backing vector should reflect these changes.</p>
     */
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

    /**
     * Test method to verify that add(int index, Object element) works correctly with offset sublists.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that adding elements to a sublist with an offset from the main vector
     * works correctly and maintains proper positioning in the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion in sublists that don't start at the beginning of the vector,
     * ensuring proper offset calculation and element positioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist from the middle range,
     * inserts two elements at different positions, and verifies the resulting element order and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 4</p>
     * <p>- Elements "2" and "3" are inserted at positions 1 and 2</p>
     * <p>- Element "4" is shifted to index 3</p>
     * <p>- Vector size becomes 6</p>
     * <p>- Elements outside sublist remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The sublist should have size 4 with elements ["1", "2", "3", "4"] and the backing vector should have elements ["0", "1", "2", "3", "4", "5"].</p>
     */
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

    /**
     * Test method to verify that remove(int index) works correctly at a valid position within the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing an element at a valid middle position in the sublist correctly
     * returns the removed element and shifts subsequent elements to the left.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic removal functionality of the sublist at a middle position,
     * ensuring proper element shifting and size management in both the sublist and backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist covering the entire vector,
     * removes the element at index 1, and verifies the returned value, resulting element order, and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returns removed element "2"</p>
     * <p>- Sublist size becomes 2</p>
     * <p>- Element "3" is shifted to index 1</p>
     * <p>- Vector size becomes 2</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The method should return "2", and the sublist should have size 2 with elements ["1", "3"].</p>
     */
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

    /**
     * Test method to verify that remove(int index) throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing an element at a negative index throws the appropriate exception,
     * preventing invalid index access.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative index values during removal,
     * ensuring robust error checking in the sublist implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates a sublist,
     * and attempts to remove an element at index -1, expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No changes to sublist or vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(-1) should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.remove(-1);
    }

    /**
     * Test method to verify that remove(int index) throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing an element at an index equal to the current size throws the appropriate exception,
     * preventing index overflow.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for index values equal to the size during removal,
     * ensuring proper bounds checking in the sublist implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist,
     * and attempts to remove an element at index 3, expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No changes to sublist or vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(3) should throw ArrayIndexOutOfBoundsException.</p>
     */
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

    /**
     * Test method to verify that remove(int index) throws ArrayIndexOutOfBoundsException on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing an element from an empty sublist throws the appropriate exception,
     * preventing operations on invalid ranges.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for removal attempts on empty sublists,
     * ensuring robust error checking for edge cases.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, creates an empty sublist (range 1-1),
     * and attempts to remove an element at index 0, expecting an ArrayIndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- No changes to sublist or vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(0) should throw ArrayIndexOutOfBoundsException when called on an empty sublist.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubListRemoveAtIndexOnEmptySubList()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(1, 1);
        subList.remove(0);
    }

    /**
     * Test method to verify that remove(int index) works correctly at the beginning of the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing the first element of the sublist correctly
     * returns the removed element and shifts all subsequent elements to the left.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal at the beginning boundary of the sublist,
     * ensuring proper element shifting and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist covering the entire vector,
     * removes the element at index 0, and verifies the returned value, resulting element order, and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returns removed element "1"</p>
     * <p>- Sublist size becomes 2</p>
     * <p>- Element "2" becomes first element</p>
     * <p>- Element "3" becomes second element</p>
     * <p>- Vector size becomes 2</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The method should return "1", and the sublist should have size 2 with elements ["2", "3"].</p>
     */
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

    /**
     * Test method to verify that remove(int index) works correctly at the end of the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing the last element of the sublist correctly
     * returns the removed element without affecting other elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal at the end boundary of the sublist,
     * ensuring proper size management without unnecessary shifting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist covering the entire vector,
     * removes the element at index 2, and verifies the returned value, resulting element order, and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returns removed element "3"</p>
     * <p>- Sublist size becomes 2</p>
     * <p>- First two elements remain unchanged</p>
     * <p>- Vector size becomes 2</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The method should return "3", and the sublist should have size 2 with elements ["1", "2"].</p>
     */
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

    /**
     * Test method to verify that remove(int index) works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing a null element from the sublist correctly
     * returns null and handles the removal properly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal of null elements,
     * ensuring the sublist implementation properly handles null values during removal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements including a null, creates a sublist,
     * removes the null element at index 1, and verifies the returned value, resulting element order, and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returns null</p>
     * <p>- Sublist size becomes 2</p>
     * <p>- Element "3" is shifted to index 1</p>
     * <p>- Vector size becomes 2</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The method should return null, and the sublist should have size 2 with elements ["1", "3"].</p>
     */
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

    /**
     * Test method to verify that remove(int index) works correctly with multiple consecutive removals.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that multiple consecutive removals from the sublist work correctly
     * and maintain proper element ordering and sizing.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate multiple removal operations in sequence,
     * ensuring the sublist maintains consistency through multiple modifications.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements, creates a sublist from the middle range,
     * performs two consecutive removals at the same index, and verifies the resulting element order and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Sublist size becomes 1</p>
     * <p>- Element "2" remains as the only element</p>
     * <p>- Vector size becomes 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After two removals, the sublist should have size 1 with element ["2"] and the backing vector should have size 3.</p>
     */
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

    /**
     * Test method to verify that remove(int index) works correctly with offset sublists.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removing an element from a sublist with an offset from the main vector
     * works correctly and maintains proper positioning in the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removal in sublists that don't start at the beginning of the vector,
     * ensuring proper offset calculation and element positioning during removal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements, creates a sublist from the end range,
     * removes the first element of the sublist, and verifies the returned value, resulting element order, and sizes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returns removed element "2"</p>
     * <p>- Sublist size becomes 1</p>
     * <p>- Element "3" becomes first element of sublist</p>
     * <p>- Vector size becomes 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The method should return "2", and the sublist should have size 1 with element ["3"].</p>
     */
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

    /**
     * Test method to verify that clear() operation on an empty sublist does not affect the backing vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that calling clear() on an empty sublist does not change the sublist's size or the backing vector's content.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of the clear() method when applied to an empty sublist, ensuring it remains empty and doesn't affect the original vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, then creates an empty sublist (from index 1 to 1). It calls clear() on the empty sublist and verifies that the sublist remains empty and the original vector size is unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty range) of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size remains 0</p>
     * <p>- SubList remains empty</p>
     * <p>- Vector size remains 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clear() should not modify an empty sublist and should not affect the backing vector.</p>
     */
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

    /**
     * Test method to verify that clear() operation removes multiple elements from sublist and updates backing vector correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clear() correctly removes multiple elements from a sublist and properly updates the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the clear() operation on a sublist containing multiple elements, ensuring proper element removal and vector updating.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with four elements, creates a sublist containing elements at indices 1-2, calls clear() on the sublist, and verifies that the sublist becomes empty and the vector size decreases appropriately with elements properly shifted.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 0</p>
     * <p>- SubList becomes empty</p>
     * <p>- Vector size decreases to 2</p>
     * <p>- Remaining elements are properly shifted</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clear() should remove all elements from the sublist and properly update the backing vector with correct element positions.</p>
     */
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

    /**
     * Test method to verify that clear() operation correctly handles sublists containing null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clear() properly handles sublists containing null elements and correctly updates the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the clear() operation on sublists containing null values, ensuring proper handling of null elements during removal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with mixed null and non-null elements, creates a sublist containing null elements, calls clear() on the sublist, and verifies proper removal and vector updating.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3", null]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 0</p>
     * <p>- SubList becomes empty</p>
     * <p>- Vector size decreases appropriately</p>
     * <p>- Null elements are properly handled and removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clear() should correctly remove null elements from sublist and properly update the backing vector.</p>
     */
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

    /**
     * Test method to verify that clear() operation works correctly when applied to a sublist covering the entire vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clear() correctly handles sublists that encompass the entire backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the clear() operation when the sublist represents the entire vector, ensuring complete element removal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist covering all elements (indices 0-3), calls clear() on the sublist, and verifies that both sublist and vector become empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 0</p>
     * <p>- SubList becomes empty</p>
     * <p>- Vector size becomes 0</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clear() should remove all elements when applied to a sublist covering the entire vector.</p>
     */
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

    /**
     * Test method to verify that clear() operation works correctly on sublists with offset positions.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clear() correctly handles sublists that start at non-zero positions in the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the clear() operation on sublists with positional offsets, ensuring proper element removal and vector updating.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with four elements, creates a sublist starting at index 2, calls clear() on the sublist, and verifies proper removal and element shifting in the backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- SubList size becomes 0</p>
     * <p>- SubList becomes empty</p>
     * <p>- Vector size decreases appropriately</p>
     * <p>- Remaining elements are properly positioned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clear() should correctly remove elements from offset sublists and properly update the backing vector.</p>
     */
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

    /**
     * Test method to verify that containsAll() returns true for empty collections on non-empty sublists.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly returns true when checking against an empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the containsAll() method's behavior when presented with an empty collection, ensuring it follows the collection contract.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and verifies that containsAll() returns true when checking against an empty collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     * <p>- Empty VectorAdapter collection is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when checking against an empty collection, following the collection contract.</p>
     */
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

    /**
     * Test method to verify that containsAll() returns true when all elements of the collection are present in the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly identifies when all elements of a collection are present in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the containsAll() method's positive case, ensuring it returns true when all elements are contained.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and verifies that containsAll() returns true when checking against a collection containing elements that exist in the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     * <p>- Collection contains elements ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when all elements of the collection are present in the sublist.</p>
     */
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

    /**
     * Test method to verify that containsAll() returns false when some elements of the collection are missing from the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly identifies when some elements of a collection are missing from the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the containsAll() method's negative case, ensuring it returns false when some elements are missing.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and verifies that containsAll() returns false when checking against a collection containing some elements not present in the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the vector</p>
     * <p>- Collection contains elements ["2", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return false when some elements of the collection are missing from the sublist.</p>
     */
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

    /**
     * Test method to verify that containsAll() returns false when all elements of the collection are missing from the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly identifies when all elements of a collection are missing from the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the containsAll() method's extreme negative case, ensuring it returns false when no elements are contained.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and verifies that containsAll() returns false when checking against a collection containing elements not present in the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     * <p>- Collection contains elements ["4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return false when all elements of the collection are missing from the sublist.</p>
     */
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

    /**
     * Test method to verify that containsAll() returns true regardless of element order in the collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() is order-independent and returns true when all elements are present regardless of their order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that containsAll() focuses on element presence rather than order, ensuring it follows the collection contract.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and verifies that containsAll() returns true when checking against a collection containing the same elements in different order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     * <p>- Collection contains elements ["3", "2"] in reverse order</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when all elements are present, regardless of their order in the collection.</p>
     */
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

    /**
     * Test method to verify that containsAll() correctly handles duplicate elements in the collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() properly handles collections with duplicate elements when the sublist contains sufficient copies.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the containsAll() method's behavior with duplicate elements, ensuring it checks for sufficient quantity of each element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with duplicate elements, creates a sublist containing those duplicates, and verifies that containsAll() returns true when checking against a collection with those elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the vector</p>
     * <p>- Collection contains elements ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when the sublist contains sufficient quantity of each element to satisfy the collection's requirements.</p>
     */
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

    /**
     * Test method to verify that containsAll() correctly handles null elements in both sublist and collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() properly handles null elements present in both the sublist and the collection being checked.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the containsAll() method's behavior with null values, ensuring proper null handling and comparison.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with null elements, creates a sublist containing null values, and verifies that containsAll() returns true when checking against a collection containing those null elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     * <p>- Collection contains elements [null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should correctly handle null elements and return true when null values are present in both sublist and collection.</p>
     */
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

    /**
     * Test method to verify that containsAll() returns false when called on an empty sublist with a non-empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly returns false when an empty sublist is checked against a non-empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the containsAll() method's behavior with empty sublists, ensuring it returns false for non-empty collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates an empty sublist, and verifies that containsAll() returns false when checking against a non-empty collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty) of the vector</p>
     * <p>- Collection contains elements ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return false when an empty sublist is checked against a non-empty collection.</p>
     */
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

    /**
     * Test method to verify that containsAll() throws NullPointerException when passed a null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() throws NullPointerException when the collection parameter is null.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling in containsAll() when presented with a null collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist, and verifies that containsAll() throws NullPointerException when passed a null collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should throw NullPointerException when the collection parameter is null.</p>
     */
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

    /**
     * Test method to verify that containsAll() returns true when a sublist is checked against itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() returns true when a sublist is checked against itself, ensuring reflexivity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the reflexive property of containsAll(), ensuring a collection always contains all its own elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, creates a sublist covering the entire vector, and verifies that containsAll() returns true when the sublist is checked against itself.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when a collection is checked against itself, following the reflexive property.</p>
     */
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

    /**
     * Test method to verify that containsAll() returns false for elements outside the sublist range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly returns false for elements that exist in the backing vector but outside the sublist range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that containsAll() only considers elements within the sublist's range, not the entire backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with four elements, creates a sublist covering a middle range, and verifies that containsAll() returns false when checking against a collection containing elements outside the sublist range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     * <p>- Collection contains elements ["1", "4"] from outside the sublist range</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return false for elements that exist in the backing vector but outside the current sublist range.</p>
     */
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

    /**
     * Test method to verify that containsAll() works correctly with sublists that have positional offsets.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly handles sublists with offset positions in the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the containsAll() method's behavior with sublists that start at non-zero positions, ensuring proper element checking within the offset range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with four elements, creates a sublist starting at index 2, and verifies that containsAll() returns true when checking against a collection containing elements within the sublist's offset range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the vector</p>
     * <p>- Collection contains elements ["2", "3"] within the sublist range</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should correctly identify elements within offset sublists and return true when all collection elements are present.</p>
     */
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

    /**
     * Test method to verify that addAll() returns true and correctly adds non-empty collection to sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() returns true when adding a non-empty collection to a sublist,
     * and that the elements are correctly appended while maintaining proper size and element order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the addAll operation with a non-empty collection, ensuring
     * proper integration of new elements into the sublist and verification of size changes.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial elements, creates a sublist from it, then adds
     * a non-empty collection using addAll(). It verifies the return value, new size, element order,
     * and backing vector size changes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- Second VectorAdapter is created with elements ["3", "4"] for adding</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Sublist size increases to 4</p>
     * <p>- Elements are in correct order: ["1", "2", "3", "4"]</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly append all elements from the collection to the sublist.</p>
     */
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

    /**
     * Test method to verify that addAll() returns false when adding empty collection to sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() returns false when attempting to add an empty collection
     * to a sublist, and that no changes occur to the sublist or backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of adding an empty collection, ensuring
     * the method returns false and maintains the original state unchanged.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial elements, creates a sublist from it, then adds
     * an empty collection using addAll(). It verifies the return value, unchanged size, and unchanged
     * backing vector size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- Empty VectorAdapter is created for adding</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns false</p>
     * <p>- Sublist size remains 2</p>
     * <p>- Backing vector size remains 2</p>
     * <p>- Original elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return false and leave the sublist unchanged when adding an empty collection.</p>
     */
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

    /**
     * Test method to verify that addAll() works correctly when adding to empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly adds elements to an initially empty sublist,
     * properly updating the size and element content.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate addAll operation on empty sublists, ensuring proper
     * initialization and element insertion into previously empty ranges.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates an empty sublist (range 1-1),
     * then adds a non-empty collection using addAll(). It verifies the return value, new size,
     * element content, and backing vector size changes.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     * <p>- Empty SubListAdapter is created from indices 1 to 1</p>
     * <p>- Second VectorAdapter is created with elements ["3", "4"] for adding</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Sublist size becomes 2</p>
     * <p>- Elements are correctly added: ["3", "4"]</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly populate an initially empty sublist with new elements.</p>
     */
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

    /**
     * Test method to verify that addAll() correctly handles null elements in collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() properly handles collections containing null elements,
     * ensuring they are correctly added to the sublist without issues.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate null element handling in addAll operation, ensuring
     * proper storage and retrieval of null values within the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist, then adds a collection
     * containing null elements using addAll(). It verifies the return value, size increase, and
     * correct positioning of null elements within the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2</p>
     * <p>- Second VectorAdapter is created with null elements for adding</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Sublist size increases to 4</p>
     * <p>- Original elements remain at positions 0 and 1</p>
     * <p>- Null elements are added at positions 2 and 3</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly add null elements to the sublist.</p>
     */
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

    /**
     * Test method to verify that addAll() throws NullPointerException with null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() throws NullPointerException when passed a null collection,
     * ensuring proper error handling for invalid input.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for null collection parameter,
     * ensuring the method fails fast with appropriate exception.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist, then attempts to add
     * a null collection using addAll(). It expects NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     * <p>- Backing vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw NullPointerException when passed a null collection.</p>
     */
    @Test(expected = NullPointerException.class)
    public void testSubListAddAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.addAll(null);
    }

    /**
     * Test method to verify that addAll() works correctly when adding sublist to itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly handles adding a sublist to itself,
     * effectively duplicating its elements while maintaining proper ordering.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of self-addition, ensuring proper
     * cloning and element duplication without infinite recursion or corruption.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist covering all elements,
     * then adds the sublist to itself using addAll(). It verifies the return value, doubled size,
     * and correct element duplication.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Sublist size doubles to 4</p>
     * <p>- Elements are duplicated in order: ["1", "2", "1", "2"]</p>
     * <p>- Backing vector size increases to 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly duplicate elements when adding a sublist to itself.</p>
     */
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

    /**
     * Test method to verify that addAll() works correctly with offset sublist positioning.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly handles elements added to a sublist that
     * doesn't start at the beginning of the backing vector, ensuring proper offset management.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate addAll operation on sublists with positional offsets,
     * ensuring elements are inserted at correct positions within the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist starting at offset 2,
     * then adds a collection using addAll(). It verifies the return value, size increase, element
     * ordering, and proper shifting of subsequent elements in the backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["0", "1", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4</p>
     * <p>- Second VectorAdapter is created with elements ["2", "3"] for adding</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Sublist size increases to 4</p>
     * <p>- Elements are in order: ["4", "5", "2", "3"]</p>
     * <p>- Backing vector size increases to 6</p>
     * <p>- Elements before and after sublist remain correctly positioned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly insert elements respecting sublist offset positioning.</p>
     */
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

    /**
     * Test method to verify that removeAll() returns true and removes all elements when all are present.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns true and correctly removes all elements
     * when all elements in the collection are present in the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removeAll operation when all collection elements
     * are present, ensuring complete removal and proper size updates.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist, then removes a collection
     * containing all sublist elements using removeAll(). It verifies the return value, empty sublist,
     * and proper backing vector size reduction.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- Second VectorAdapter is created with elements ["2", "3", "4"] for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Sublist becomes empty (size 0)</p>
     * <p>- Backing vector size reduces to 1</p>
     * <p>- Remaining element "1" is correctly positioned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and remove all elements when all collection elements are present.</p>
     */
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

    /**
     * Test method to verify that removeAll() returns true and removes only some elements when partially present.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns true and correctly removes only the elements
     * that are present in both the collection and sublist, leaving other elements unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate partial removal scenario, ensuring only matching elements
     * are removed while non-matching elements remain in place.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist, then removes a collection
     * containing some sublist elements using removeAll(). It verifies the return value, reduced size,
     * remaining elements, and proper backing vector updates.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- Second VectorAdapter is created with elements ["2", "3"] for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Sublist size reduces to 1</p>
     * <p>- Remaining element is "4"</p>
     * <p>- Backing vector size reduces to 2</p>
     * <p>- Elements are correctly shifted and positioned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and remove only the elements present in both collection and sublist.</p>
     */
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

    /**
     * Test method to verify that removeAll() returns false when removing empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns false when attempting to remove an empty
     * collection from a sublist, leaving the sublist unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of empty collection removal, ensuring
     * no changes occur to the sublist state.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist, then attempts to remove
     * an empty collection using removeAll(). It verifies the return value, unchanged size, and
     * unchanged backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3</p>
     * <p>- Empty VectorAdapter is created for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns false</p>
     * <p>- Sublist size remains unchanged (2)</p>
     * <p>- Backing vector size remains unchanged (3)</p>
     * <p>- All elements remain in original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return false and leave the sublist unchanged when removing empty collection.</p>
     */
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

    /**
     * Test method to verify that removeAll() returns false when no elements are present.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns false when none of the collection elements
     * are present in the sublist, leaving the sublist unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the scenario where no elements match between the
     * collection and sublist, ensuring no removal occurs.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist, then attempts to remove
     * a collection containing no matching elements using removeAll(). It verifies the return value,
     * unchanged size, and unchanged backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3</p>
     * <p>- Second VectorAdapter is created with elements ["4", "5"] for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns false</p>
     * <p>- Sublist size remains unchanged (2)</p>
     * <p>- Backing vector size remains unchanged (3)</p>
     * <p>- All elements remain in original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return false and leave the sublist unchanged when no elements match.</p>
     */
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

    /**
     * Test method to verify that removeAll() correctly handles duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() correctly removes all occurrences of duplicate
     * elements present in both the collection and sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate duplicate element handling, ensuring all instances
     * of matching elements are removed regardless of duplication.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements, creates a sublist containing
     * duplicates, then removes a collection containing the duplicate element using removeAll().
     * It verifies the return value, size reduction, and proper element removal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- Second VectorAdapter is created with element ["2"] for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Sublist size reduces to 1</p>
     * <p>- Remaining element is "3"</p>
     * <p>- Backing vector size reduces to 2</p>
     * <p>- Elements are correctly shifted and positioned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and remove all occurrences of duplicate matching elements.</p>
     */
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

    /**
     * Test method to verify that removeAll() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() correctly removes null elements when present
     * in both the collection and sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate null element removal, ensuring proper handling
     * of null values in the removal process.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with null elements, creates a sublist containing
     * nulls, then removes a collection containing null using removeAll(). It verifies the
     * return value, size reduction, and proper removal of null elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", null, "3", null]</p>
     * <p>- SubListAdapter is created from indices 1 to 4</p>
     * <p>- Second VectorAdapter is created with element [null] for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Sublist size reduces to 1</p>
     * <p>- Remaining element is "3"</p>
     * <p>- Backing vector size reduces to 2</p>
     * <p>- Elements are correctly shifted and positioned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and correctly remove null elements from the sublist.</p>
     */
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

    /**
     * Test method to verify that removeAll() returns false when called on empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns false when attempting to remove elements
     * from an empty sublist, regardless of collection content.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of empty sublist removal, ensuring
     * no changes occur and proper return value is provided.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates an empty sublist, then attempts
     * to remove a non-empty collection using removeAll(). It verifies the return value, unchanged
     * empty state, and unchanged backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     * <p>- Empty SubListAdapter is created from indices 1 to 1</p>
     * <p>- Second VectorAdapter is created with elements ["1", "2"] for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns false</p>
     * <p>- Sublist remains empty (size 0)</p>
     * <p>- Backing vector size remains unchanged (3)</p>
     * <p>- All elements remain in original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return false and leave empty sublist unchanged.</p>
     */
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

    /**
     * Test method to verify that removeAll() throws NullPointerException with null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() throws NullPointerException when passed a null
     * collection parameter, ensuring proper error handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for null collection parameter,
     * ensuring the method fails fast with appropriate exception.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist, then attempts to
     * remove a null collection using removeAll(). It expects NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     * <p>- Sublist remains unchanged</p>
     * <p>- Backing vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should throw NullPointerException when passed a null collection.</p>
     */
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

    /**
     * Test method to verify that removeAll() works correctly when removing sublist from itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() correctly handles removing a sublist from itself,
     * resulting in an empty sublist and proper backing vector updates.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of self-removal, ensuring all elements
     * are properly removed and state is correctly updated.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist covering all elements,
     * then removes the sublist from itself using removeAll(). It verifies the return value,
     * empty sublist, and empty backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Sublist becomes empty (size 0)</p>
     * <p>- Backing vector becomes empty (size 0)</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and completely empty the sublist and backing vector when removing itself.</p>
     */
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

    /**
     * Test method to verify that removeAll() returns false for elements outside sublist range.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns false when attempting to remove elements
     * that exist in the backing vector but outside the sublist range.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that removeAll() only considers elements within the
     * sublist range, ignoring elements outside this range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist covering a middle range,
     * then attempts to remove elements that exist but outside the sublist range using removeAll().
     * It verifies the return value, unchanged sublist, and unchanged backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3</p>
     * <p>- Second VectorAdapter is created with elements ["1", "4"] for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns false</p>
     * <p>- Sublist size remains unchanged (2)</p>
     * <p>- Backing vector size remains unchanged (4)</p>
     * <p>- All elements remain in original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return false when collection elements exist outside sublist range.</p>
     */
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

    /**
     * Test method to verify that removeAll() works correctly with offset sublist positioning.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() correctly handles elements removal from a sublist
     * that doesn't start at the beginning of the backing vector, ensuring proper offset management.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate removeAll operation on sublists with positional offsets,
     * ensuring elements are removed from correct positions within the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, creates a sublist starting at offset 2,
     * then removes a collection containing sublist elements using removeAll(). It verifies the
     * return value, size reduction, and proper shifting of subsequent elements in the backing vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4</p>
     * <p>- Second VectorAdapter is created with elements ["2", "3"] for removal</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Sublist becomes empty (size 0)</p>
     * <p>- Backing vector size reduces to 2</p>
     * <p>- Elements before sublist remain correctly positioned</p>
     * <p>- Elements are properly shifted after removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and correctly remove elements respecting sublist offset positioning.</p>
     */
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

    /**
     * Test method to verify that retainAll() returns false when all elements are present in the sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() returns false when called on a sublist where all elements
     * are present in the specified collection, indicating no modification occurred.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of retainAll() when the sublist already contains
     * all elements from the specified collection, ensuring no unnecessary modifications are made.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements and a sublist covering all elements.
     * A collection containing the same three elements is created. retainAll() is called and the
     * return value is verified to be false, and the sublist size remains unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns false</p>
     * <p>- SubList size remains 3</p>
     * <p>- SubList content remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return false and the sublist should remain unchanged when all elements
     * are already present in the collection.</p>
     */
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

    /**
     * Test method to verify that retainAll() removes some elements when only some are present in the collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() correctly removes elements not present in the specified
     * collection and returns true when modifications occur.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of retainAll() when only some elements from
     * the sublist are present in the specified collection, ensuring proper element retention.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements and a sublist covering indices 1 to 4.
     * A collection containing only elements "2" and "4" is created. retainAll() is called and
     * the return value is verified to be true, and the sublist is checked to contain only the
     * retained elements in the correct order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 4 of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["2", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns true</p>
     * <p>- SubList size becomes 2</p>
     * <p>- SubList contains only elements "2" and "4"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return true and the sublist should contain only elements present in
     * the specified collection.</p>
     */
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

    /**
     * Test method to verify that retainAll() removes all elements when none are present in the collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() correctly removes all elements when none of them are
     * present in the specified collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of retainAll() when no elements from the
     * sublist are present in the specified collection, ensuring complete element removal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements and a sublist covering indices 0 to 3.
     * A collection containing different elements ("5", "6") is created. retainAll() is called and
     * the return value is verified to be true, and the sublist is checked to be empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["5", "6"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns true</p>
     * <p>- SubList becomes empty (size 0)</p>
     * <p>- SubList isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return true and the sublist should become empty when none of its
     * elements are present in the specified collection.</p>
     */
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

    /**
     * Test method to verify that retainAll() with empty collection removes all elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() correctly removes all elements when called with an
     * empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of retainAll() when the specified collection
     * is empty, ensuring all elements are removed from the sublist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements and a sublist covering indices 0 to 2.
     * An empty collection is created. retainAll() is called and the return value is verified to be
     * true, and the sublist is checked to be empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- An empty VectorAdapter is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns true</p>
     * <p>- SubList becomes empty (size 0)</p>
     * <p>- SubList isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return true and the sublist should become empty when called with
     * an empty collection.</p>
     */
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

    /**
     * Test method to verify that retainAll() throws NullPointerException with null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() throws NullPointerException when called with a null collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when retainAll() is called with
     * a null collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering indices 0 to 2.
     * retainAll() is called with a null parameter, expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should throw NullPointerException when called with a null collection.</p>
     */
    @Test(expected = NullPointerException.class)
    public void testSubListRetainAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);

        subList.retainAll(null);
    }

    /**
     * Test method to verify that retainAll() works correctly with offset sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() works correctly when the sublist has an offset from
     * the beginning of the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of retainAll() with sublists that don't
     * start at index 0 of the backing vector, ensuring proper element retention considering
     * the offset.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements and a sublist covering indices 2 to 4.
     * A collection containing the same elements as the sublist is created. retainAll() is called
     * and the return value is verified to be false, and the sublist content remains unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 2 to 4 of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns false</p>
     * <p>- SubList size remains 2</p>
     * <p>- SubList content remains unchanged</p>
     * <p>- Vector size remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return false and the sublist should remain unchanged when all elements
     * are already present in the collection, even with offset sublist.</p>
     */
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

    /**
     * Test method to verify that addAll() at index works with non-empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() at a specific index correctly inserts all elements from
     * a non-empty collection into the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of addAll() when inserting a non-empty
     * collection at a specific index in the sublist, ensuring proper element insertion and
     * shifting of existing elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * A collection with two elements is created. addAll() is called at index 1 and the return
     * value is verified to be true. The sublist and vector sizes are checked, and the element
     * order is verified after insertion.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "4"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- SubList size becomes 4</p>
     * <p>- Elements are in correct order: ["1", "2", "3", "4"]</p>
     * <p>- Vector size becomes 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly insert all elements from the collection at
     * the specified index, shifting existing elements as needed.</p>
     */
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

    /**
     * Test method to verify that addAll() at index returns false with empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() at a specific index returns false when called with
     * an empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of addAll() when attempting to insert
     * an empty collection at a specific index, ensuring no modifications occur.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * An empty collection is created. addAll() is called at index 1 and the return value is
     * verified to be false. The sublist and vector sizes and contents are checked to ensure
     * no changes occurred.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- An empty VectorAdapter is created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns false</p>
     * <p>- SubList size remains 2</p>
     * <p>- SubList content remains unchanged</p>
     * <p>- Vector size remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return false and make no modifications when called with an empty collection.</p>
     */
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

    /**
     * Test method to verify that addAll() at beginning works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() at index 0 correctly inserts all elements from
     * a collection at the beginning of the sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of addAll() when inserting a collection
     * at the beginning of the sublist (index 0), ensuring proper element insertion and
     * shifting of existing elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * A collection with two elements is created. addAll() is called at index 0 and the return
     * value is verified to be true. The sublist and vector sizes are checked, and the element
     * order is verified after insertion.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["3", "4"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- SubList size becomes 4</p>
     * <p>- Elements are in correct order: ["1", "2", "3", "4"]</p>
     * <p>- Vector size becomes 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly insert all elements from the collection at
     * the beginning of the sublist, shifting existing elements as needed.</p>
     */
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

    /**
     * Test method to verify that addAll() at end works correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() at the end of the sublist correctly appends all
     * elements from a collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of addAll() when inserting a collection
     * at the end of the sublist, ensuring proper element appending without shifting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * A collection with two elements is created. addAll() is called at index 2 (the end) and
     * the return value is verified to be true. The sublist and vector sizes are checked, and
     * the element order is verified after appending.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["3", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- SubList size becomes 4</p>
     * <p>- Elements are in correct order: ["1", "2", "3", "4"]</p>
     * <p>- Vector size becomes 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly append all elements from the collection at
     * the end of the sublist.</p>
     */
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

    /**
     * Test method to verify that addAll() works with empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly inserts elements into an empty sublist.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of addAll() when inserting a collection
     * into an empty sublist, ensuring proper element insertion and sublist initialization.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and an empty sublist (indices 1 to 1).
     * A collection with two elements is created. addAll() is called at index 0 and the return
     * value is verified to be true. The sublist and vector sizes are checked, and the element
     * content is verified.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 1 to 1 (empty) of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["3", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- SubList size becomes 2</p>
     * <p>- Elements are in correct order: ["3", "4"]</p>
     * <p>- Vector size becomes 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly insert all elements from the collection into
     * the empty sublist, properly initializing it.</p>
     */
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

    /**
     * Test method to verify that addAll() throws ArrayIndexOutOfBoundsException with negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() throws ArrayIndexOutOfBoundsException when called with
     * a negative index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when addAll() is called with
     * an invalid negative index parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * A collection with one element is created. addAll() is called with index -1, expecting
     * an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- Another VectorAdapter is created with element: ["3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw ArrayIndexOutOfBoundsException when called with a negative index.</p>
     */
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

    /**
     * Test method to verify that addAll() throws ArrayIndexOutOfBoundsException with index greater than size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() throws ArrayIndexOutOfBoundsException when called with
     * an index greater than the sublist size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when addAll() is called with
     * an index parameter that exceeds the sublist size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * A collection with one element is created. addAll() is called with index 3 (greater than
     * size 2), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     * <p>- Another VectorAdapter is created with element: ["3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw ArrayIndexOutOfBoundsException when called with an index greater
     * than the sublist size.</p>
     */
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

    /**
     * Test method to verify that addAll() throws NullPointerException with null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() throws NullPointerException when called with a null collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when addAll() is called with
     * a null collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * addAll() is called with a null parameter, expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw NullPointerException when called with a null collection.</p>
     */
    @Test(expected = NullPointerException.class)
    public void testSubListAddAllAtIndexNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        subList = (SubListAdapter) vector.subList(0, 2);
        subList.addAll(1, null);
    }

    /**
     * Test method to verify that addAll() throws NullPointerException with null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() throws NullPointerException when called with a null collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when addAll() is called with
     * a null collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * addAll() is called with a null parameter, expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw NullPointerException when called with a null collection.</p>
     */
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

    /**
     * Test method to verify that addAll() works correctly when adding the sublist to itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly handles adding a sublist to itself.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of addAll() when the collection parameter
     * is the same sublist, ensuring proper element duplication and handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and a sublist covering all elements.
     * addAll() is called with the same sublist as the collection parameter at index 1. The
     * return value is verified to be true. The sublist and vector sizes are checked, and the
     * element content is verified after the operation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2"]</p>
     * <p>- SubListAdapter is created from indices 0 to 2 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- SubList size becomes 4</p>
     * <p>- Elements are in correct order: ["1", "1", "2", "2"]</p>
     * <p>- Vector size becomes 4</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly duplicate the sublist elements when adding
     * the sublist to itself.</p>
     */
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

    /**
     * Test method to verify that addAll() works correctly with offset sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() works correctly when the sublist has an offset from
     * the beginning of the backing vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of addAll() with sublists that don't
     * start at index 0 of the backing vector, ensuring proper element insertion considering
     * the offset and proper shifting of elements in the backing vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements and a sublist covering indices 1 to 3.
     * A collection with two elements is created. addAll() is called at index 1 and the return
     * value is verified to be true. The sublist and vector sizes are checked, and the element
     * content is verified after insertion, including elements before and after the sublist.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     * <p>- Another VectorAdapter is created with elements: ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- SubList size becomes 4</p>
     * <p>- Elements are in correct order: ["1", "2", "3", "4"]</p>
     * <p>- Vector size becomes 6</p>
     * <p>- Elements before and after sublist remain correct</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and correctly insert elements from the collection into
     * the offset sublist, properly handling the offset and maintaining elements outside
     * the sublist range.</p>
     */
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

    /**
     * Test method to verify that a sublist equals itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method returns true when comparing a sublist to itself,
     * ensuring reflexivity of the equals operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the reflexive property of the equals method for sublists.
     * This ensures that any sublist should always be equal to itself according to the equals contract.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates a sublist from indices 1 to 3.
     * It then calls the equals method on the sublist with itself as the parameter and verifies it returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the main vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns true</p>
     * <p>- No changes to the sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return true when comparing a sublist to itself.</p>
     */
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

    /**
     * Test method to verify that two equal sublists are considered equal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method returns true when comparing two sublists with identical content,
     * ensuring symmetry of the equals operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublists with the same elements in the same order are considered equal.
     * This ensures proper implementation of the equals method for sublists with identical content.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two separate VectorAdapters with identical elements, creates sublists from both with the same
     * range, and verifies that they are considered equal by the equals method.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Two VectorAdapters are initialized with identical elements: ["1", "2", "3"]</p>
     * <p>- Two SubListAdapters are created from indices 0 to 3 of both vectors</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns true</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return true when comparing two sublists with identical content.</p>
     */
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

    /**
     * Test method to verify that sublists of different sizes are not equal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method returns false when comparing sublists of different sizes,
     * ensuring that size difference is properly detected.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublists with different numbers of elements are not considered equal.
     * This ensures the equals method correctly handles size comparisons.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, then creates two sublists with different ranges
     * (one full range, one partial range) and verifies they are not equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- First SubListAdapter is created from indices 0 to 3 (full range)</p>
     * <p>- Second SubListAdapter is created from indices 1 to 3 (partial range)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns false</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return false when comparing sublists of different sizes.</p>
     */
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

    /**
     * Test method to verify that sublists with different elements are not equal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method returns false when comparing sublists with different elements,
     * ensuring content difference is properly detected.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublists with different elements are not considered equal.
     * This ensures the equals method correctly performs element-by-element comparison.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with different elements at the same positions, creates sublists
     * from both with the same range, and verifies they are not equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- First VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- Second VectorAdapter is initialized with elements: ["1", "4", "3"]</p>
     * <p>- SubListAdapters are created from indices 0 to 3 of both vectors</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns false</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return false when comparing sublists with different elements.</p>
     */
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

    /**
     * Test method to verify that sublists with elements in different order are not equal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method returns false when comparing sublists with elements in different order,
     * ensuring order sensitivity of the equals operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublists with the same elements but in different order are not considered equal.
     * This ensures the equals method is order-sensitive as required by the List contract.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with the same elements but in different order, creates sublists
     * from both with the same range, and verifies they are not equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- First VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- Second VectorAdapter is initialized with elements: ["1", "3", "2"]</p>
     * <p>- SubListAdapters are created from indices 0 to 3 of both vectors</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns false</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return false when comparing sublists with elements in different order.</p>
     */
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

    /**
     * Test method to verify that sublists with null elements are handled correctly in equality comparison.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method correctly handles null elements when comparing sublists,
     * ensuring proper null value comparison.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublists with identical content including null elements
     * are considered equal. This ensures proper handling of null values in the equals implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with identical elements including null values, creates sublists
     * from both with the same range, and verifies they are equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Both VectorAdapters are initialized with elements: ["1", null, "3"]</p>
     * <p>- SubListAdapters are created from indices 0 to 3 of both vectors</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns true</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return true when comparing sublists with identical content including null elements.</p>
     */
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

    /**
     * Test method to verify that empty sublists are considered equal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method returns true when comparing two empty sublists,
     * ensuring proper handling of empty collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that empty sublists from different backing vectors are considered equal.
     * This ensures the equals method correctly handles the edge case of empty collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with different content, creates empty sublists from both
     * (range 0 to 0), and verifies they are equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- First VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     * <p>- Second VectorAdapter is initialized with elements: ["4", "5", "6"]</p>
     * <p>- SubListAdapters are created from indices 0 to 0 (empty range) of both vectors</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns true</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return true when comparing two empty sublists.</p>
     */
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

    /**
     * Test method to verify that a sublist is not equal to a non-list object.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method returns false when comparing a sublist to a non-list object,
     * ensuring type safety in equality comparisons.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the equals method correctly handles comparisons with objects
     * of different types, specifically non-list objects.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist, and compares it to
     * a String object, verifying they are not equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 1 to 3 of the vector</p>
     * <p>- String object "1" is created for comparison</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns false</p>
     * <p>- No changes to the sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return false when comparing a sublist to a non-list object.</p>
     */
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

    /**
     * Test method to verify that sublists with offset positions are compared correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals method works correctly for sublists that have offset positions
     * within their backing vectors, ensuring proper handling of positional offsets.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublists with the same content but starting at different
     * positions in their backing vectors are correctly identified as equal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two identical VectorAdapters, creates sublists from both starting at index 2,
     * and verifies they are equal despite the positional offset.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Both VectorAdapters are initialized with 5 identical elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapters are created from indices 2 to 5 of both vectors</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals method returns true</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The equals method should return true when comparing sublists with identical content but positional offsets.</p>
     */
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

    /**
     * Test method to verify the hashCode of an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that an empty sublist produces the same hashCode as another empty sublist,
     * ensuring consistency in hashCode implementation for empty collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that empty sublists from the same vector produce consistent
     * hashCodes, which is important for proper functioning in hash-based collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, creates an empty sublist from it, and verifies its
     * hashCode matches that of another empty sublist from the same vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with no elements</p>
     * <p>- SubListAdapter is created from indices 0 to 0 (empty range)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hashCode values match</p>
     * <p>- No changes to the sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>An empty sublist should produce the same hashCode as another empty sublist from the same vector.</p>
     */
    @Test
    public void testSubListHashCodeOfEmptySubList()
    {
        vector = new VectorAdapter();
        subList = (SubListAdapter) vector.subList(0, 0);

        assertEquals("Hash code of empty vector should match empty vector", vector.subList(0, 0).hashCode(), subList.hashCode());
    }

    /**
     * Test method to verify the hashCode of sublists with multiple elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that sublists with identical content produce the same hashCode,
     * ensuring consistency in hashCode implementation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublists with the same elements produce identical
     * hashCodes, which is required for proper functioning in hash-based collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with identical elements, creates sublists from both
     * with the same range, and verifies their hashCodes match.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Both VectorAdapters are initialized with identical elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapters are created from indices 0 to 3 of both vectors</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hashCode values match</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublists with identical content should produce the same hashCode.</p>
     */
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

    /**
     * Test method to verify the hashCode of sublists with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that sublists with identical null elements produce the same hashCode,
     * ensuring proper handling of null values in hashCode calculation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that sublists with the same null elements produce identical
     * hashCodes, ensuring consistent behavior with null values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with identical null elements, creates sublists from both
     * with the same range, and verifies their hashCodes match.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Both VectorAdapters are initialized with identical null elements: [null, null, null]</p>
     * <p>- SubListAdapters are created from indices 0 to 3 of both vectors</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- hashCode values match</p>
     * <p>- No changes to either sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Sublists with identical null elements should produce the same hashCode.</p>
     */
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

    /**
     * Test method to verify the string representation of an empty sublist.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that an empty sublist produces the correct string representation,
     * ensuring proper formatting for empty collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method correctly represents empty sublists
     * using the standard collection formatting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, creates an empty sublist from it, and verifies
     * its string representation matches the expected format for empty collections.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with no elements</p>
     * <p>- SubListAdapter is created from indices 0 to 0 (empty range)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- String representation is "[]"</p>
     * <p>- No changes to the sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>An empty sublist should have the string representation "[]".</p>
     */
    @Test
    public void testSubListToStringEmptyVector()
    {
        vector = new VectorAdapter();
        subList = (SubListAdapter) vector.subList(0, 0);

        assertEquals("String representation of empty vector should match", "[]", subList.toString());
    }

    /**
     * Test method to verify the string representation of a sublist with multiple elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a sublist with multiple elements produces the correct string representation,
     * ensuring proper formatting and element ordering.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method correctly represents sublists with
     * multiple elements using the standard collection formatting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, creates a sublist containing all elements,
     * and verifies its string representation matches the expected format.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- String representation is "[1, 2, 3]"</p>
     * <p>- No changes to the sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>A sublist with elements ["1", "2", "3"] should have the string representation "[1, 2, 3]".</p>
     */
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

    /**
     * Test method to verify the string representation of a sublist with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a sublist with null elements produces the correct string representation,
     * ensuring proper handling of null values in string formatting.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method correctly represents sublists with
     * null elements using the standard collection formatting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements including null values, creates a sublist containing
     * all elements, and verifies its string representation matches the expected format.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- String representation is "[1, null, 3]"</p>
     * <p>- No changes to the sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>A sublist with elements ["1", null, "3"] should have the string representation "[1, null, 3]".</p>
     */
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

    /**
     * Test method to verify the string representation of a sublist with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a sublist with duplicate elements produces the correct string representation,
     * ensuring proper formatting of repeated values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method correctly represents sublists with
     * duplicate elements using the standard collection formatting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements, creates a sublist containing all elements,
     * and verifies its string representation matches the expected format.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with duplicate elements: ["1", "1", "1"]</p>
     * <p>- SubListAdapter is created from indices 0 to 3 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- String representation is "[1, 1, 1]"</p>
     * <p>- No changes to the sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>A sublist with duplicate elements ["1", "1", "1"] should have the string representation "[1, 1, 1]".</p>
     */
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

    /**
     * Test method to verify the string representation of a sublist with offset positioning.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a sublist with offset positioning within its backing vector produces
     * the correct string representation, ensuring proper handling of positional offsets.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method correctly represents sublists that
     * start at an offset position within their backing vectors.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements, creates a sublist starting at index 2,
     * and verifies its string representation matches the expected format for the subset of elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- SubListAdapter is created from indices 2 to 5 of the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- String representation is "[3, 4, 5]"</p>
     * <p>- No changes to the sublist state</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>A sublist starting at index 2 of ["1", "2", "3", "4", "5"] should have the string representation "[3, 4, 5]".</p>
     */
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
