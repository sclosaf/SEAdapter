package myTest;

import myAdapter.VectorAdapter;
import myAdapter.HCollection;
import myAdapter.HEnumeration;
import myAdapter.HIterator;
import myAdapter.HListIterator;
import myAdapter.HList;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Test suite for the VectorAdapter class.
 *
 * <p>This test suite verifies the functionality of the VectorAdapter class,
 * which implements the HList and HCollection interfaces to provide a
 * growable dynamic array of objects.
 * The tests cover various scenarios including construction, capacity management,
 * element manipulation, and collection operations.</p>
 *
 * <h2>Test Case Design</h2>
 * <p>The test cases are designed to validate the behavior of VectorAdapter
 * against the specifications of Java 1.4.2 Vector class. The test design
 * includes categories for basic functionality, edge cases, error conditions,
 * and compliance with the HList and HCollection interfaces.</p>
 *
 * <p>Categories:</p>
 * <ul>
 *   <li>Constructor tests</li>
 *   <li>Capacity and size management tests</li>
 *   <li>Element access and modification tests</li>
 *   <li>Collection operation tests</li>
 *   <li>Iterator and enumeration tests</li>
 *   <li>Edge case and exception handling tests</li>
 * </ul>
 *
 * <h2>Testing Framework Information</h2>
 * <p>This test suite utilizes JUnit 4.13 testing framework with Hamcrest 1.3
 * matchers for assertions. These dependencies  are located as follows:</p>
 * <ul>
 *   <li>JUnit 4.13: {@code lib/junit-4.13.jar}</li>
 *   <li>Hamcrest Core 1.3: {@code Matcher/hamcrest-core-1.3.jar}</li>
 * </ul>
 *
 * @author   Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see      HCollection
 * @see      HList
 * @see      VectorAdapter
 */
public class VectorAdapterTest
{
    /**
     * The main VectorAdapter instance used for testing.
     * Initialized before each test method execution.
     */
    private VectorAdapter vector;

    /**
     * Default constructor
     */
    public VectorAdapterTest()
    {}

    /**
     * Sets up the testing environment before each test method execution.
     * Initializes the vector instance to null to ensure the use of a clean state.
     */
    @Before
    public void setUp()
    {
        vector = null;
    }

    /**
     * Cleans up test environment after each test method execution.
     * Sets the vector instance to null to assist garbage collection.
     */
    @After
    public void tearDown()
    {
        vector = null;
    }

    /**
     * Test method to verify that constructor with valid parameters initializes correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the VectorAdapter constructor with capacity and increment parameters
     * correctly initializes the vector with specified capacity and zero size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper initialization of VectorAdapter when created with
     * valid capacity and increment values, ensuring basic constructor functionality works as expected.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance with capacity 5 and increment 2, then verifies
     * that the instance is not null, size is 0, capacity is 5, and the vector is empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created with specified parameters</p>
     * <p>- Size is 0, capacity is 5, and vector is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized with capacity 5, size 0, and empty state.</p>
     */
    @Test
    public void testConstructorWithCapacityAndIncrementValidParameters()
    {
        vector = new VectorAdapter(5, 2);

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 5", 5, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that constructor with zero capacity initializes correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the VectorAdapter constructor with zero capacity and positive increment
     * correctly initializes the vector with zero capacity and size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case handling when capacity is zero, ensuring the
     * constructor can handle minimum capacity values correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance with capacity 0 and increment 5, then verifies
     * that the instance is not null, size is 0, capacity is 0, and the vector is empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created with zero capacity</p>
     * <p>- Size is 0, capacity is 0, and vector is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized with zero capacity, zero size, and empty state.</p>
     */
    @Test
    public void testConstructorWithCapacityAndIncrementZeroCapacity()
    {
        vector = new VectorAdapter(0, 5);

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 0", 0, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that constructor throws IllegalArgumentException for negative capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the VectorAdapter constructor throws IllegalArgumentException
     * when negative capacity is provided.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid constructor parameters,
     * ensuring proper error reporting for negative capacity values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a VectorAdapter instance with capacity -1 and increment 5,
     * expecting an IllegalArgumentException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalArgumentException is thrown</p>
     * <p>- No VectorAdapter instance is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Constructor should throw IllegalArgumentException for negative capacity.</p>
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithCapacityAndIncrementNegativeCapacity()
    {
        vector = new VectorAdapter(-1, 5);
    }

    /**
     * Test method to verify that constructor with zero increment initializes correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the VectorAdapter constructor with positive capacity and zero increment
     * correctly initializes the vector and uses doubling strategy for growth.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that zero increment parameter triggers the doubling
     * growth strategy in the underlying Vector implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance with capacity 10 and increment 0, then verifies
     * basic initialization properties.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created with specified parameters</p>
     * <p>- Size is 0, capacity is 10, and vector is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized with capacity 10, size 0, and empty state.</p>
     */
    @Test
    public void testConstructorWithCapacityAndIncrementZeroIncrement()
    {
        vector = new VectorAdapter(10, 0);

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 10", 10, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that constructor with negative increment initializes correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the VectorAdapter constructor with positive capacity and negative increment
     * correctly initializes the vector and uses doubling strategy for growth.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that negative increment parameter triggers the doubling
     * growth strategy in the underlying Vector implementation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance with capacity 7 and increment -2, then verifies
     * basic initialization properties.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created with specified parameters</p>
     * <p>- Size is 0, capacity is 7, and vector is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized with capacity 7, size 0, and empty state.</p>
     */
    @Test
    public void testConstructorWithCapacityAndIncrementNegativeIncrement()
    {
        vector = new VectorAdapter(7, -2);

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 7", 7, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    /**
     * Test method to verify growth behavior with positive increment.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that VectorAdapter grows according to the specified increment value
     * when adding elements beyond initial capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the capacity growth mechanism when a positive increment
     * value is specified, ensuring proper memory management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 2 and increment 10, adds three elements,
     * and verifies that capacity grows to 12 while maintaining correct element order and values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created and populated with three elements</p>
     * <p>- Capacity grows to 12, size is 3, elements are in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should grow by specified increment and maintain element integrity.</p>
     */
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

    /**
     * Test method to verify doubling growth behavior with zero increment.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that VectorAdapter doubles its capacity when adding elements beyond
     * initial capacity with zero increment specified.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the doubling growth strategy when increment is zero,
     * ensuring proper memory management for the default growth behavior.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 3 and increment 0, adds four elements,
     * and verifies that capacity doubles to 6 while maintaining correct element order and values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created and populated with four elements</p>
     * <p>- Capacity doubles to 6, size is 4, elements are in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should double its capacity and maintain element integrity.</p>
     */
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

    /**
     * Test method to verify constructor with valid capacity parameter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the single-parameter VectorAdapter constructor correctly initializes
     * the vector with specified capacity and zero increment.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the simplified constructor that only takes capacity,
     * ensuring it delegates to the main constructor with zero increment.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance with capacity 8, then verifies that the instance
     * is properly initialized with correct capacity and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created with capacity 8</p>
     * <p>- Size is 0, capacity is 8, and vector is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized with capacity 8, size 0, and empty state.</p>
     */
    @Test
    public void testConstructorWithCapacityValidParameter()
    {
        vector = new VectorAdapter(8);

        assertNotNull("Should have be created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 8", 8, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    /**
     * Test method to verify constructor with zero capacity parameter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the single-parameter VectorAdapter constructor correctly handles
     * zero capacity value.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case handling for the capacity-only constructor,
     * ensuring it can handle minimum capacity values correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance with capacity 0, then verifies that the instance
     * is properly initialized with zero capacity and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created with zero capacity</p>
     * <p>- Size is 0, capacity is 0, and vector is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized with zero capacity, zero size, and empty state.</p>
     */
    @Test
    public void testConstructorWithCapacityZeroCapacity()
    {
        vector = new VectorAdapter(0);

        assertNotNull("Should be created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 0", 0, vector. capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    /**
     * Test method to verify constructor throws IllegalArgumentException for negative capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the single-parameter VectorAdapter constructor throws
     * IllegalArgumentException when negative capacity is provided.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid constructor parameters
     * in the simplified capacity-only constructor.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a VectorAdapter instance with capacity -5,
     * expecting an IllegalArgumentException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IllegalArgumentException is thrown</p>
     * <p>- No VectorAdapter instance is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Constructor should throw IllegalArgumentException for negative capacity.</p>
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithCapacityNegativeCapacity()
    {
        vector = new VectorAdapter(-5);
    }

    /**
     * Test method to verify doubling growth behavior with capacity-only constructor.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that VectorAdapter created with capacity-only constructor doubles
     * its capacity when adding elements beyond initial capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the doubling growth strategy when using the
     * capacity-only constructor, which implicitly uses zero increment.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity 3, adds four elements,
     * and verifies that capacity doubles to 6 while maintaining correct element order and values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created and populated with four elements</p>
     * <p>- Capacity doubles to 6, size is 4, elements are in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should double its capacity and maintain element integrity.</p>
     */
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

    /**
     * Test method to verify default constructor initialization.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the no-argument VectorAdapter constructor correctly initializes
     * the vector with default capacity of 10.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the default constructor behavior, ensuring it provides
     * sensible default values for capacity and initial state.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance using the default constructor, then verifies
     * that the instance is properly initialized with default capacity of 10 and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created with default capacity</p>
     * <p>- Size is 0, capacity is 10, and vector is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized with default capacity 10, size 0, and empty state.</p>
     */
    @Test
    public void testDefaultConstructor()
    {
        vector = new VectorAdapter();

        assertNotNull("Should have been created", vector);
        assertEquals("Initial size should be 0", 0, vector.size());
        assertEquals("Initial capacity should be 10", 10, vector.capacity());
        assertTrue("Should be empty", vector.isEmpty());
    }

    /**
     * Test method to verify doubling growth behavior with default constructor.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that VectorAdapter created with default constructor doubles
     * its capacity when adding elements beyond initial capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the doubling growth strategy when using the
     * default constructor, which implicitly uses zero increment.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with default constructor, adds 11 elements,
     * and verifies that capacity doubles to 20 while maintaining correct element order and values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter instance is created and populated with 11 elements</p>
     * <p>- Capacity doubles to 20, size is 11, elements are in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should double its capacity and maintain element integrity.</p>
     */
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

    /**
     * Test method to verify collection constructor with valid collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the collection-based VectorAdapter constructor correctly
     * initializes the vector with elements from the source collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the collection constructor functionality, ensuring
     * it properly copies elements from the source collection while maintaining order.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a source VectorAdapter with three elements, then creates a new
     * VectorAdapter using the collection constructor and verifies that all elements are
     * correctly copied with matching order and values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Source VectorAdapter with three elements exists</p>
     * <p>- No target VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Target VectorAdapter instance is created with copied elements</p>
     * <p>- Size is 3, capacity is 3, elements match source collection</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized with elements copied from source collection.</p>
     */
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

    /**
     * Test method to verify collection constructor with empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the collection-based VectorAdapter constructor correctly
     * handles empty source collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case handling for the collection constructor
     * when the source collection is empty, ensuring proper initialization.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty source VectorAdapter, then creates a new VectorAdapter
     * using the collection constructor and verifies that the target vector is also empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Empty source VectorAdapter exists</p>
     * <p>- No target VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Target VectorAdapter instance is created empty</p>
     * <p>- Size is 0, capacity is 0, vector is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>VectorAdapter should be initialized as empty when source collection is empty.</p>
     */
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

    /**
     * Test method to verify collection constructor throws NullPointerException for null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the collection-based VectorAdapter constructor throws
     * NullPointerException when null collection is provided.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid null parameter
     * in the collection constructor.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test attempts to create a VectorAdapter instance with null collection parameter,
     * expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- No VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     * <p>- No VectorAdapter instance is created</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Constructor should throw NullPointerException for null collection.</p>
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorWithCollectionNullCollection()
    {
        vector = new VectorAdapter(null);
    }

    /**
     * Test method to verify independence between source and target collections.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that modifications to the source collection after constructor
     * invocation do not affect the target VectorAdapter instance.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the collection constructor creates an
     * independent copy of the source collection, ensuring data integrity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a source VectorAdapter, creates a target VectorAdapter from it,
     * modifies the source collection, and verifies that the target collection remains unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Source VectorAdapter with three elements exists</p>
     * <p>- No target VectorAdapter instance exists</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Target VectorAdapter instance is created with initial source elements</p>
     * <p>- Source collection is modified but target remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Target VectorAdapter should remain independent of source collection modifications.</p>
     */
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

    /**
     * Test method to verify that copyInto() copies elements in correct order.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the copyInto method correctly copies elements from the vector to the target array
     * in the proper sequential order, maintaining the same element positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic functionality of the copyInto method when copying elements
     * to an array of exactly the same size as the vector. This ensures proper element ordering during the copy operation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three string elements, creates a target array of matching size,
     * and calls copyInto. It then verifies that each element in the target array matches the corresponding
     * element from the vector in both value and position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with three elements: ["1", "2", "3"]</p>
     * <p>- Target array is created with length 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Target array contains elements in order: ["1", "2", "3"]</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>All elements should be copied to the target array in their original order with correct values.</p>
     */
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

    /**
     * Test method to verify that copyInto() handles empty vectors correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the copyInto method works correctly when copying from an empty vector
     * to an empty array, ensuring no elements are copied and the array remains unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case handling when both the vector and target array are empty,
     * ensuring the method doesn't attempt to copy non-existent elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and an empty target array, then calls copyInto.
     * It verifies that the array remains empty and no exceptions are thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- Target array is created with length 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Target array remains empty</p>
     * <p>- VectorAdapter remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>The empty array should remain unchanged after the copy operation.</p>
     */
    @Test
    public void testCopyIntoEmptyVector()
    {
        vector = new VectorAdapter();

        Object[] arr = new Object[0];
        vector.copyInto(arr);

        assertEquals("Array should remain empty", 0, arr.length);
    }

    /**
     * Test method to verify that copyInto() preserves existing array elements beyond vector size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto only overwrites the initial portion of the target array
     * corresponding to the vector's size, leaving subsequent elements unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the method correctly handles cases where the target array
     * is larger than the vector, ensuring existing elements beyond the vector size are preserved.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, a target array with five elements (three pre-filled),
     * calls copyInto, and verifies that the first two elements are overwritten while the last three remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains two elements: ["1", "2"]</p>
     * <p>- Target array of length 5 with elements at indices 2,3,4 set to "0"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First two array elements become ["1", "2"]</p>
     * <p>- Last three array elements remain ["0", "0", "0"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Only the initial portion of the array should be modified, preserving existing elements beyond the vector size.</p>
     */
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

    /**
     * Test method to verify that copyInto() throws IndexOutOfBoundsException for insufficient array size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto throws IndexOutOfBoundsException when the target array
     * is smaller than the vector size, preventing array overflow.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper error handling when the target array cannot accommodate
     * all vector elements, ensuring the method fails safely rather than causing array overflow.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements and a target array with only two elements,
     * then expects IndexOutOfBoundsException to be thrown when copyInto is called.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements: ["1", "2", "3"]</p>
     * <p>- Target array is created with length 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- Array remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IndexOutOfBoundsException should be thrown when target array is too small.</p>
     */
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

    /**
     * Test method to verify that copyInto() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto correctly copies null elements from the vector to the target array,
     * ensuring proper handling of null values during the copy operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that null elements are properly handled and copied to the target array
     * without conversion issues or exceptions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three null elements, a target array with three non-null elements,
     * calls copyInto, and verifies that all array elements become null after the operation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three null elements</p>
     * <p>- Target array contains three non-null elements ["0", "0", "0"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All array elements become null</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>All elements in the target array should be set to null, matching the vector's contents.</p>
     */
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

    /**
     * Test method to verify that copyInto() throws NullPointerException for null array parameter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto throws NullPointerException when provided with a null array parameter,
     * ensuring proper validation of input parameters.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate parameter validation by passing a null array reference,
     * ensuring the method fails gracefully with appropriate exception.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and calls copyInto with a null array parameter,
     * expecting NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- Null array reference is passed to copyInto</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>NullPointerException should be thrown when null array is provided.</p>
     */
    @Test(expected = NullPointerException.class)
    public void testCopyIntoNullArray()
    {
        vector = new VectorAdapter();
        vector.copyInto(null);
    }

    /**
     * Test method to verify that copyInto() throws IndexOutOfBoundsException for zero-length array with elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that copyInto throws IndexOutOfBoundsException when attempting to copy
     * elements into a zero-length array while the vector contains elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case handling when the target array has zero length
     * but the vector contains elements, ensuring proper exception is thrown.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with one element and a zero-length target array,
     * then expects IndexOutOfBoundsException to be thrown when copyInto is called.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains one element: ["1"]</p>
     * <p>- Target array is created with length 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- Array remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>IndexOutOfBoundsException should be thrown when target array has insufficient capacity.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCopyIntoZeroLengthArrayWithElements()
    {
        vector = new VectorAdapter();
        vector.add("1");

        Object[] arr = new Object[0];
        vector.copyInto(arr);
    }

    /**
     * Test method to verify that trimToSize() works correctly on newly constructed vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that trimToSize correctly reduces the capacity of a newly constructed
     * vector with initial capacity to match its actual size (zero for empty vector).</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that trimToSize properly handles vectors created with
     * initial capacity but no elements, reducing capacity to zero.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 20 and capacity increment of 50,
     * calls trimToSize, and verifies that capacity becomes zero while the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is created with initial capacity 20 and capacity increment 50</p>
     * <p>- Vector is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector capacity becomes 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Capacity should be reduced to 0 for an empty vector after trimToSize.</p>
     */
    @Test
    public void testTrimToSizeOnConstructor()
    {
        vector = new VectorAdapter(20, 50);

        vector.trimToSize();

        assertTrue("Should still be empty", vector.isEmpty());
        assertEquals("Capacity should equal to 0 after trimming", 0, vector.capacity());
    }

    /**
     * Test method to verify that trimToSize() is idempotent (multiple calls have same effect).
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that calling trimToSize multiple times has the same effect as calling it once,
     * ensuring the method is idempotent and doesn't unnecessarily modify the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that subsequent calls to trimToSize don't change the vector's
     * state when no modifications occur between calls.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, calls trimToSize twice, and verifies that
     * capacity, size, and elements remain unchanged after the second call.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements: ["1", "2", "3"]</p>
     * <p>- Initial capacity is exactly 3 (after first trim)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains unchanged after second trim</p>
     * <p>- Size remains 3</p>
     * <p>- Elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Multiple calls to trimToSize should not change the vector's state when no modifications occur between calls.</p>
     */
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

    /**
     * Test method to verify that trimToSize() reduces capacity after element removal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that trimToSize correctly reduces the vector's capacity to match its
     * current size after elements have been removed, optimizing memory usage.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that trimToSize properly handles the case where elements
     * have been removed from the vector, reducing capacity to match the new smaller size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements, removes two elements, calls trimToSize,
     * and verifies that capacity matches the new size and remaining elements are preserved correctly.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains five elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- Two elements are removed (index 2 and 3)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity equals reduced size (3)</p>
     * <p>- Remaining elements are ["1", "2", "4"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Capacity should be reduced to match the current size after element removal and trimToSize.</p>
     */
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

    /**
     * Test method to verify that trimToSize() works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that trimToSize correctly handles vectors containing null elements,
     * reducing capacity while preserving all null values in their original positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that null elements are properly preserved during
     * the capacity reduction operation performed by trimToSize.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three null elements, calls trimToSize, and verifies
     * that capacity matches size and all elements remain null after the operation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three null elements</p>
     * <p>- Initial capacity is 5</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity equals size (3)</p>
     * <p>- All three elements remain null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>trimToSize should preserve null elements while reducing capacity to match size.</p>
     */
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

    /**
     * Test method to verify that trimToSize() works after reducing size via setSize().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that trimToSize correctly reduces capacity after the vector size
     * has been explicitly reduced using setSize, optimizing memory usage for the new smaller size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the interaction between setSize and trimToSize methods,
     * ensuring proper capacity management when size is explicitly reduced.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, reduces size to 2 using setSize,
     * calls trimToSize, and verifies that capacity matches the new size and remaining elements are preserved.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements: ["1", "2", "3"]</p>
     * <p>- Size is reduced to 2 using setSize</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity equals reduced size (2)</p>
     * <p>- Remaining elements are ["1", "2"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Capacity should be reduced to match the explicitly set smaller size after trimToSize.</p>
     */
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

    /**
     * Test method to verify that trimToSize() works after increasing size via setSize().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that trimToSize correctly increases capacity after the vector size
     * has been explicitly increased using setSize, ensuring capacity matches the new larger size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the interaction between setSize and trimToSize methods
     * when size is increased, ensuring proper capacity allocation for the new size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, increases size to 4 using setSize,
     * calls trimToSize, and verifies that capacity matches the new size and element values are correct
     * (original elements preserved, new elements null).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains two elements: ["1", "2"]</p>
     * <p>- Size is increased to 4 using setSize</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity equals increased size (4)</p>
     * <p>- Elements are ["1", "2", null, null]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Capacity should be increased to match the explicitly set larger size after trimToSize,
     * with new elements initialized to null.</p>
     */
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

    /**
     * Test method to verify that trimToSize() overrides previous ensureCapacity().
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that trimToSize correctly reduces capacity to match current size
     * even after ensureCapacity has been called to increase capacity, prioritizing actual size over reserved capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that trimToSize takes precedence over ensureCapacity,
     * ensuring optimal memory usage regardless of previous capacity reservations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, calls ensureCapacity to reserve additional space,
     * then calls trimToSize and verifies that capacity is reduced to match the actual size rather than
     * the previously reserved capacity.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains two elements: ["1", "2"]</p>
     * <p>- ensureCapacity(20) is called to reserve additional space</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity equals actual size (2)</p>
     * <p>- Elements remain unchanged: ["1", "2"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>trimToSize should reduce capacity to match current size, overriding previous ensureCapacity calls.</p>
     */
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

    /**
     * Test method to verify that ensureCapacity() does not change capacity when minCapacity is smaller than current capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() maintains the current capacity when the requested minimum capacity
     * is smaller than the current capacity, ensuring no unnecessary capacity changes occur.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the capacity management behavior when the requested minimum capacity
     * is less than the current capacity. This ensures the vector optimizes memory usage by avoiding unnecessary expansions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 10. It then calls ensureCapacity(5) and verifies
     * that the capacity remains unchanged at 10, the size remains 0, and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity of 10</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains 10</p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should not change capacity when minCapacity is smaller than current capacity.</p>
     */
    @Test
    public void testEnsureCapacityConstructor()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(5);

        assertEquals("Capacity should remain unchanged when minCapacity is smaller", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that ensureCapacity() does not change capacity when minCapacity equals current capacity.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() maintains the current capacity when the requested minimum capacity
     * exactly equals the current capacity, ensuring no unnecessary capacity changes occur.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the capacity management behavior when the requested minimum capacity
     * equals the current capacity. This ensures the vector maintains stability when no expansion is needed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 10. It then calls ensureCapacity(10) and verifies
     * that the capacity remains unchanged at 10, the size remains 0, and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity of 10</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains 10</p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should not change capacity when minCapacity equals current capacity.</p>
     */
    @Test
    public void testEnsureCapacityExactCurrentCapacity()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(vector.capacity());

        assertEquals("Capacity should remain unchanged when minCapacity equals current capacity", 10, vector.capacity());

        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that ensureCapacity() increases capacity using capacity increment when needed.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() properly increases capacity using the specified capacity increment
     * when the requested minimum capacity exceeds the current capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the capacity expansion mechanism using the capacity increment parameter.
     * This ensures the vector grows in controlled increments as specified during construction.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 5 and capacity increment of 3. It then calls
     * ensureCapacity(7) and verifies that the capacity increases to 8 (5 + 3), the size remains 0, and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity 5 and capacity increment 3</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases to 8</p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should increase capacity using capacity increment when minCapacity exceeds current capacity.</p>
     */
    @Test
    public void testEnsureCapacityCapacityIncrement()
    {
        vector = new VectorAdapter(5, 3);
        vector.ensureCapacity(7);

        assertEquals("Capacity should increase using capacity increment", 8, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that ensureCapacity() uses minCapacity when capacity increment is insufficient.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() uses the requested minimum capacity directly when the capacity
     * increment would result in an insufficient capacity increase.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the vector properly handles cases where the standard capacity increment
     * would not meet the minimum capacity requirement, ensuring adequate capacity is always available.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 5 and capacity increment of 3. It then calls
     * ensureCapacity(10) and verifies that the capacity increases directly to 10 (bypassing the increment),
     * the size remains 0, and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity 5 and capacity increment 3</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases to 10</p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should use minCapacity directly when capacity increment would be insufficient.</p>
     */
    @Test
    public void testEnsureCapacityCapacityIncrementInsufficient()
    {
        vector = new VectorAdapter(5, 3);
        vector.ensureCapacity(10);

        assertEquals("Capacity should increase using minCapacity", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that ensureCapacity() doubles capacity when capacity increment is zero.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() doubles the current capacity when the capacity increment is zero
     * and the requested minimum capacity exceeds the current capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the doubling behavior that occurs when no specific capacity increment
     * is specified (capacity increment = 0), ensuring the vector grows exponentially.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 5 and default capacity increment (0). It then calls
     * ensureCapacity(9) and verifies that the capacity doubles to 10, the size remains 0, and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity 5</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity doubles to 10</p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should double capacity when capacity increment is zero and minCapacity exceeds current capacity.</p>
     */
    @Test
    public void testEnsureCapacityDoubleGrowthIncrement()
    {
        vector = new VectorAdapter(5);
        vector.ensureCapacity(9);

        assertEquals("Capacity should double when capacity increment is zero", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that ensureCapacity() uses minCapacity when double growth is insufficient.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() uses the requested minimum capacity directly when doubling
     * the current capacity would still be insufficient to meet the minimum requirement.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the vector properly handles cases where exponential growth
     * would not meet the minimum capacity requirement, ensuring adequate capacity is always available.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 5 and default capacity increment (0). It then calls
     * ensureCapacity(20) and verifies that the capacity increases directly to 20, the size remains 0, and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity 5</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases to 20</p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should use minCapacity directly when double growth would be insufficient.</p>
     */
    @Test
    public void testEnsureCapacityDoubleGrowthInsufficient()
    {
        vector = new VectorAdapter(5);
        vector.ensureCapacity(20);

        assertEquals("Capacity should be set to minCapacity when double growth is insufficient", 20, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that ensureCapacity() works correctly after setSize() has been called.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() properly increases capacity while preserving existing elements
     * and size after setSize() has been used to expand the vector with null elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the interaction between setSize() and ensureCapacity(), ensuring that
     * capacity expansion doesn't affect the logical size or content of the vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 5, calls setSize(3) to create a vector with
     * 3 null elements, then calls ensureCapacity(20). It verifies that capacity increases to 20, size remains 3,
     * and all three elements remain null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity 5</p>
     * <p>- setSize(3) has been called, creating 3 null elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases to 20</p>
     * <p>- Size remains 3</p>
     * <p>- All three elements remain null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should increase capacity while preserving size and element values after setSize().</p>
     */
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

    /**
     * Test method to verify that ensureCapacity() works correctly after trimToSize() has been called.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() properly increases capacity while preserving existing elements
     * and size after trimToSize() has been used to reduce capacity to match size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the interaction between trimToSize() and ensureCapacity(), ensuring that
     * capacity expansion after trimming doesn't affect the logical content of the vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 10, adds three elements, calls trimToSize()
     * to reduce capacity to 3, then calls ensureCapacity(8). It verifies that capacity increases to 8, size remains 3,
     * and all three original elements are preserved.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity 10</p>
     * <p>- Three elements ["1", "2", "3"] are added</p>
     * <p>- trimToSize() has been called, reducing capacity to 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity increases to 8</p>
     * <p>- Size remains 3</p>
     * <p>- All three original elements are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should increase capacity while preserving elements and size after trimToSize().</p>
     */
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

    /**
     * Test method to verify that ensureCapacity() handles zero minCapacity correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() maintains the current capacity when the requested minimum capacity
     * is zero, ensuring no unnecessary capacity changes occur for edge case values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the capacity management behavior when the requested minimum capacity
     * is zero, ensuring the vector handles this edge case appropriately.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 10. It then calls ensureCapacity(0) and verifies
     * that the capacity remains unchanged at 10, the size remains 0, and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity of 10</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains 10</p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should not change capacity when minCapacity is zero.</p>
     */
    @Test
    public void testEnsureCapacityZeroMinCapacity()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(0);

        assertEquals("Capacity should remain unchanged for zero minCapacity", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that ensureCapacity() handles negative minCapacity correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that ensureCapacity() maintains the current capacity when the requested minimum capacity
     * is negative, ensuring no unnecessary capacity changes occur for invalid input values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the capacity management behavior when the requested minimum capacity
     * is negative, ensuring the vector handles invalid input gracefully without side effects.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 10. It then calls ensureCapacity(-5) and verifies
     * that the capacity remains unchanged at 10, the size remains 0, and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with initial capacity of 10</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity remains 10</p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>ensureCapacity() should not change capacity when minCapacity is negative.</p>
     */
    @Test
    public void testEnsureCapacityNegativeMinCapacity()
    {
        vector = new VectorAdapter(10);
        vector.ensureCapacity(-5);

        assertEquals("Capacity should remain unchanged for negative minCapacity", 10, vector.capacity());
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Should still be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that setSize() increases size from empty vector by adding null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() properly increases the size of an empty vector by adding null elements
     * to reach the specified size, while maintaining appropriate capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic size expansion behavior of setSize() on an empty vector,
     * ensuring that null elements are correctly added and capacity is managed appropriately.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter with default constructor (capacity 10). It then calls setSize(3)
     * and verifies that the size increases to 3, capacity remains at least 10, and all three elements are null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with default constructor</p>
     * <p>- Vector is empty with size 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size increases to 3</p>
     * <p>- Capacity remains at least 10</p>
     * <p>- All three elements are null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should increase size by adding null elements while maintaining capacity.</p>
     */
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

    /**
     * Test method to verify that setSize() increases size while preserving existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() properly increases the size of a non-empty vector by adding null elements
     * while preserving existing elements in their original positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the size expansion behavior of setSize() on a vector with existing elements,
     * ensuring that original elements are preserved and new positions are filled with null values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter, adds two elements ["1", "2"], then calls setSize(4). It verifies that
     * size increases to 4, the original two elements are preserved at indices 0 and 1, and new elements at indices
     * 2 and 3 are null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains two elements: ["1", "2"]</p>
     * <p>- Size is 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size increases to 4</p>
     * <p>- Original elements preserved at indices 0 and 1</p>
     * <p>- New elements at indices 2 and 3 are null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should increase size while preserving existing elements and adding nulls to new positions.</p>
     */
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

    /**
     * Test method to verify that setSize() decreases size by removing trailing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() properly decreases the size of a vector by removing elements from
     * the end while preserving elements at the beginning.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the size reduction behavior of setSize(), ensuring that elements
     * beyond the new size are effectively removed while elements within the new size are preserved.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with four elements ["1", "2", "3", "4"], then calls setSize(2).
     * It verifies that size decreases to 2, and only the first two elements ["1", "2"] are preserved.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains four elements: ["1", "2", "3", "4"]</p>
     * <p>- Size is 4</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size decreases to 2</p>
     * <p>- Only first two elements ["1", "2"] are preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should decrease size by removing trailing elements while preserving leading elements.</p>
     */
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

    /**
     * Test method to verify that setSize() with same size maintains current state.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() maintains the current size and all elements when the requested
     * size equals the current size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the idempotent behavior of setSize() when the size doesn't change,
     * ensuring no unnecessary modifications occur.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements ["1", "2", "3"], then calls setSize(3).
     * It verifies that size remains 3, and all three elements are preserved unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements: ["1", "2", "3"]</p>
     * <p>- Size is 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size remains 3</p>
     * <p>- All three elements are preserved unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should maintain current state when new size equals current size.</p>
     */
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

    /**
     * Test method to verify that setSize() can reduce size to zero, clearing the vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize(0) effectively clears the vector by reducing size to zero,
     * making the vector empty while potentially maintaining capacity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the clear behavior of setSize(0), ensuring that all elements
     * are removed and the vector becomes empty.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements ["1", "2", "3"], then calls setSize(0).
     * It verifies that size becomes 0 and the vector becomes empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements: ["1", "2", "3"]</p>
     * <p>- Size is 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size becomes 0</p>
     * <p>- Vector becomes empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize(0) should clear the vector by reducing size to zero.</p>
     */
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

    /**
     * Test method to verify that setSize() throws ArrayIndexOutOfBoundsException for negative size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() throws ArrayIndexOutOfBoundsException when attempting to
     * set a negative size, ensuring proper validation of input parameters.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the error handling behavior of setSize() for invalid negative
     * size values, ensuring the vector maintains integrity when faced with invalid input.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and attempts to call setSize(-1). It expects
     * ArrayIndexOutOfBoundsException to be thrown, indicating invalid negative size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- Size is 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Vector state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should throw ArrayIndexOutOfBoundsException for negative size values.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetSizeNegative()
    {
        vector = new VectorAdapter();
        vector.setSize(-1);
    }

    /**
     * Test method to verify that setSize() works correctly with multiple sequential size changes.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() handles multiple sequential size changes correctly,
     * preserving appropriate elements through both reductions and expansions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state management of setSize() through multiple operations,
     * ensuring element preservation and proper null handling during complex size manipulation sequences.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements ["1", "2", "3", "4", "5"], calls setSize(3)
     * to reduce size, then calls setSize(7) to expand. It verifies that after reduction, only first three
     * elements are preserved, and after expansion, the original three elements are preserved with new null
     * elements added.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains five elements: ["1", "2", "3", "4", "5"]</p>
     * <p>- Size is 5</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- After first setSize(3): size 3, elements ["1", "2", "3"]</p>
     * <p>- After second setSize(7): size 7, elements ["1", "2", "3", null, null, null, null]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should handle sequential size changes correctly, preserving appropriate elements.</p>
     */
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

    /**
     * Test method to verify that setSize() works correctly with increase followed by decrease.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() handles size increase followed by decrease correctly,
     * preserving appropriate elements through both operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the state management of setSize() through opposite operations,
     * ensuring element preservation and proper handling when size is increased then decreased.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements ["1", "2"], calls setSize(4) to expand
     * with nulls, then calls setSize(1) to reduce. It verifies that after expansion, original elements
     * are preserved with nulls added, and after reduction, only the first element is preserved.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains two elements: ["1", "2"]</p>
     * <p>- Size is 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- After first setSize(4): size 4, elements ["1", "2", null, null]</p>
     * <p>- After second setSize(1): size 1, elements ["1"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should handle size increase followed by decrease correctly, preserving appropriate elements.</p>
     */
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

    /**
     * Test method to verify that setSize() works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setSize() properly handles vectors containing null elements,
     * preserving null values during size expansion operations.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the null element handling of setSize(), ensuring that
     * null elements are preserved and additional null elements are correctly added during expansion.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three null elements, calls setSize(5) to expand.
     * It verifies that size increases to 5, all original null elements are preserved, and new
     * elements are also null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three null elements</p>
     * <p>- Size is 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size increases to 5</p>
     * <p>- All five elements are null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setSize() should preserve null elements and add new null elements during expansion.</p>
     */
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

    /**
     * Test method to verify default capacity for empty vector created with default constructor.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a VectorAdapter created with the default constructor has
     * the expected default capacity of 10.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the default capacity value specified by the
     * VectorAdapter implementation, ensuring consistency with expected behavior.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter using the default constructor and verifies that
     * the capacity() method returns 10.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is created with default constructor</p>
     * <p>- Vector is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity is 10</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Default constructor should create vector with capacity 10.</p>
     */
    @Test
    public void testCapacityEmptyVector()
    {
        vector = new VectorAdapter();
        assertEquals("Capacity should be 10 for default constructor", 10, vector.capacity());
    }

    /**
     * Test method to verify capacity matches initial capacity when specified in constructor.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that a VectorAdapter created with a specific initial capacity
     * has exactly that capacity value.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the capacity parameter in the constructor
     * is properly honored and reflected in the capacity() method.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with initial capacity of 15 and verifies that
     * the capacity() method returns 15.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is created with initial capacity 15</p>
     * <p>- Vector is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Capacity is 15</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Constructor with initial capacity should create vector with specified capacity.</p>
     */
    @Test
    public void testCapacityAtInitialCapacity()
    {
        vector = new VectorAdapter(15);
        assertEquals("Capacity should match initial capacity", 15, vector.capacity());
    }

    /**
     * Test method to verify that size() returns 0 for an empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the size() method correctly returns 0 for a newly created empty vector,
     * ensuring proper initialization and size reporting.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic size reporting functionality for an empty vector,
     * ensuring that the size method accurately reflects the number of elements in the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter using the default constructor and immediately calls size(),
     * verifying that it returns 0 for the empty vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is created with default constructor</p>
     * <p>- Vector is empty with no elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size remains 0</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should return 0 for an empty vector.</p>
     */
    @Test
    public void testSizeEmptyVector()
    {
        vector = new VectorAdapter();
        assertEquals("Size should be 0 for empty vector", 0, vector.size());
    }


    /**
     * Test method to verify that size() correctly increments after adding elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the size() method properly increments and reports the correct count
     * after adding multiple elements to the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the dynamic size tracking functionality as elements are added,
     * ensuring the size accurately reflects the current number of elements in the vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, adds three elements ["1", "2", "3"], then calls size()
     * and verifies that it returns 3.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- Size is 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size becomes 3</p>
     * <p>- Vector contains three elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should return 3 after adding three elements.</p>
     */
    @Test
    public void testSizeAfterAddingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        assertEquals("Size should be 3 after adding three elements", 3, vector.size());
    }

    /**
     * Test method to verify that size() correctly decrements after removing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the size() method properly decrements and reports the correct count
     * after removing elements from the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the dynamic size tracking functionality as elements are removed,
     * ensuring the size accurately reflects the current number of elements remaining in the vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements ["1", "2", "3"], removes the element at index 1,
     * then calls size() and verifies that it returns 2.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements</p>
     * <p>- Size is 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size becomes 2</p>
     * <p>- Vector contains two elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should return 2 after removing one element from three.</p>
     */
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

    /**
     * Test method to verify that size() returns 0 after clear() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the size() method correctly returns 0 after the clear() operation
     * removes all elements from the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the clear() operation properly resets the size to 0
     * and that the size() method accurately reports this state.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements ["1", "2", "3"], calls clear() to remove
     * all elements, then calls size() and verifies that it returns 0.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements</p>
     * <p>- Size is 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size becomes 0</p>
     * <p>- Vector becomes empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should return 0 after clear() operation.</p>
     */
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

    /**
     * Test method to verify that size() correctly counts null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the size() method properly includes null elements in its count,
     * ensuring that all elements regardless of value contribute to the size calculation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that null elements are treated equally to non-null elements
     * when calculating the size of the vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and adds three null elements, then calls size() and verifies
     * that it returns 3, confirming that null elements are counted.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- Size is 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Size becomes 3</p>
     * <p>- Vector contains three null elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>size() should count null elements and return 3 for three null elements.</p>
     */
    @Test
    public void testSizeNullElements()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add(null);

        assertEquals("Size should count null elements", 3, vector.size());
    }

    /**
     * Test method to verify that isEmpty() returns true for a newly created empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the isEmpty() method correctly returns true for a newly created
     * empty vector, indicating the absence of elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic empty state detection functionality,
     * ensuring that isEmpty() accurately reports when the vector contains no elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter using the default constructor and immediately calls isEmpty(),
     * verifying that it returns true for the empty vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is created with default constructor</p>
     * <p>- Vector is empty with no elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns true</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return true for a new empty vector.</p>
     */
    @Test
    public void testIsEmptyOnEmptyVector()
    {
        vector = new VectorAdapter();
        assertTrue("New vector should be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that isEmpty() returns false after adding elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the isEmpty() method correctly returns false after elements
     * are added to the vector, indicating the presence of elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that isEmpty() properly detects when the vector
     * transitions from empty to non-empty state after element addition.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, adds two elements ["1", "2"], then calls isEmpty()
     * and verifies that it returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- isEmpty() returns true</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns false</p>
     * <p>- Vector contains two elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return false after adding elements to the vector.</p>
     */
    @Test
    public void testIsEmptyAfterAddingElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        assertFalse("Vector should not be empty after adding element", vector.isEmpty());
    }

    /**
     * Test method to verify that isEmpty() returns true after clear() operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the isEmpty() method correctly returns true after the clear()
     * operation removes all elements from the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that isEmpty() properly detects when the vector
     * transitions from non-empty to empty state after clear() operation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements ["1", "2"], calls clear() to remove
     * all elements, then calls isEmpty() and verifies that it returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains two elements</p>
     * <p>- isEmpty() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns true</p>
     * <p>- Vector becomes empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return true after clear() operation.</p>
     */
    @Test
    public void testIsEmptyAfterClear()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.clear();

        assertTrue("Vector should be empty after clear", vector.isEmpty());
    }

    /**
     * Test method to verify that isEmpty() returns false for vectors containing null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the isEmpty() method correctly returns false when the vector
     * contains null elements, confirming that null values are considered as existing elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that isEmpty() treats null elements as valid content
     * and correctly indicates that the vector is not empty when it contains null values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter, adds two null elements, then calls isEmpty() and verifies
     * that it returns false, confirming that null elements make the vector non-empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- isEmpty() returns true</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns false</p>
     * <p>- Vector contains two null elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return false when vector contains null elements.</p>
     */
    @Test
    public void testIsEmptyNullElements()
    {
        vector = new VectorAdapter();

        vector.add(null);
        vector.add(null);

        assertFalse("Vector with null element should not be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that isEmpty() returns true after setSize(0) operation.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the isEmpty() method correctly returns true after setSize(0)
     * effectively clears the vector by setting size to zero.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that isEmpty() properly detects the empty state
     * after setSize(0) operation, which should have the same effect as clear().</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements ["1", "2"], calls setSize(0) to
     * effectively clear the vector, then calls isEmpty() and verifies that it returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains two elements</p>
     * <p>- isEmpty() returns false</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- isEmpty() returns true</p>
     * <p>- Vector becomes empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>isEmpty() should return true after setSize(0) operation.</p>
     */
    @Test
    public void testIsEmptyAfterSetSizeZero()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setSize(0);

        assertTrue("Vector should be empty after setSize(0)", vector.isEmpty());
    }

    /**
     * Test method to verify that contains() returns true for existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly identifies existing elements
     * in the vector, returning true for elements that are present.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the positive detection functionality of contains(),
     * ensuring it can correctly identify all elements that exist in the vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements ["1", "2", "3"], then calls contains()
     * for each element and verifies that it returns true for all three.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns true for all three elements</p>
     * <p>- Vector content remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return true for all existing elements in the vector.</p>
     */
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

    /**
     * Test method to verify that contains() returns false for non-existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly returns false for elements
     * that are not present in the vector, ensuring accurate negative detection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the negative detection functionality of contains(),
     * ensuring it correctly identifies when elements are not present in the vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements ["1", "2"], then calls contains()
     * for elements ["3", "4"] and verifies that it returns false for both.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains two elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns false for non-existing elements</p>
     * <p>- Vector content remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return false for elements not present in the vector.</p>
     */
    @Test
    public void testContainsNonExistingElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        assertFalse("Should not contain element '3'", vector.contains("3"));
        assertFalse("Should not contain element '4'", vector.contains("4"));
    }

    /**
     * Test method to verify that contains() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method can correctly detect null elements
     * when they are present in the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that contains() properly handles null values
     * and can identify their presence in the vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", null, "3"], then calls contains(null)
     * and verifies that it returns true, confirming that null elements are detectable.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains(null) returns true</p>
     * <p>- Vector content remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return true when null element is present in the vector.</p>
     */
    @Test
    public void testContainsNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertTrue("Should contain null element", vector.contains(null));
    }

    /**
     * Test method to verify that contains() returns false for all elements on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly returns false for any element
     * when called on an empty vector, ensuring proper behavior for the empty collection case.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that contains() behaves correctly on an empty vector,
     * returning false for all element queries including null.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and calls contains() for various elements
     * including strings "1" through "5" and null, verifying that it returns false for all queries.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains() returns false for all element queries</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should return false for all elements when vector is empty.</p>
     */
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

    /**
     * Test method to verify that contains() reflects element removal operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly updates its results after
     * elements are removed from the vector, ensuring dynamic accuracy.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that contains() dynamically reflects changes to
     * the vector's content, particularly after element removal operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements ["1", "2", "3"], removes element "2",
     * then calls contains() for all original elements and verifies that "2" is no longer found
     * while "1" and "3" are still present.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains("2") returns false after removal</p>
     * <p>- contains("1") and contains("3") return true</p>
     * <p>- Vector contains two elements: ["1", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should reflect element removal by returning false for removed elements
     * and true for remaining elements.</p>
     */
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

    /**
     * Test method to verify that contains() reflects element replacement operations.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the contains() method correctly updates its results after
     * elements are replaced in the vector, ensuring dynamic accuracy after modification.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that contains() dynamically reflects changes to
     * the vector's content, particularly after element replacement operations using set().</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements ["1", "2", "3"], replaces element
     * at index 1 with "4", then calls contains() and verifies that "2" is no longer found
     * while "4" is now present.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains three elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- contains("2") returns false after replacement</p>
     * <p>- contains("4") returns true after replacement</p>
     * <p>- Vector contains elements: ["1", "4", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>contains() should reflect element replacement by returning false for replaced elements
     * and true for new elements.</p>
     */
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

    /**
     * Test method to verify that indexOf returns correct indices for existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf correctly returns the index of existing elements in the vector,
     * ensuring proper element location functionality.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic indexOf functionality with typical element distribution.
     * This ensures the method correctly identifies positions of elements in non-empty collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three string elements ["1", "2", "3"] and verifies that
     * indexOf returns the correct indices (0, 1, 2) for each respective element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with three elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All indexOf calls return expected indices</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return correct indices (0, 1, 2) for elements "1", "2", "3" respectively.</p>
     */
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

    /**
     * Test method to verify that indexOf returns -1 for non-existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf correctly returns -1 when searching for elements not present
     * in the vector, ensuring proper handling of absent elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate negative search results functionality. This ensures the method
     * correctly identifies when elements are not present in the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three string elements ["1", "2", "3"] and verifies that
     * indexOf returns -1 when searching for non-existing elements "4" and "0".</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with three elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All indexOf calls return -1 for non-existing elements</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return -1 for elements not present in the vector.</p>
     */
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

    /**
     * Test method to verify that indexOf correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf correctly identifies and returns the index of null elements
     * in the vector, ensuring proper null value handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate null element search functionality. This ensures the method
     * can locate null values among other elements in the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", null, "3"] and verifies that indexOf
     * returns the correct index (1) for the null element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns correct index for null element</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return the correct index (1) for the null element.</p>
     */
    @Test
    public void testIndexOfNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertEquals("Should return correct index for null element", 1, vector.indexOf(null));
    }

    /**
     * Test method to verify that indexOf returns -1 for any element in empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf consistently returns -1 when called on an empty vector,
     * regardless of the searched element, ensuring proper empty collection handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case behavior with empty collections. This ensures
     * the method correctly handles the boundary condition of no elements present.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and verifies that indexOf returns -1 for both
     * non-null ("1") and null elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All indexOf calls return -1</p>
     * <p>- VectorAdapter remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return -1 for any element when called on an empty vector.</p>
     */
    @Test
    public void testIndexOfOnEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Should return -1 for any element in empty vector", -1, vector.indexOf("1"));
        assertEquals("Should return -1 for null in empty vector", -1, vector.indexOf(null));
    }

    /**
     * Test method to verify that indexOf returns first occurrence index for duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf returns the index of the first occurrence when duplicate
     * elements exist in the vector, ensuring proper duplicate element handling.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate duplicate element search behavior. This ensures the method
     * follows the specification of returning the first occurrence index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements ["0", "1", "1"] and verifies that
     * indexOf returns the index of the first occurrence (1) for element "1".</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "1"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns first occurrence index for duplicates</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return the index of the first occurrence (1) for duplicate elements.</p>
     */
    @Test
    public void testIndexOfDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("0");
        vector.add("1");
        vector.add("1");

        assertEquals("Should return first occurrence index for duplicate elements", 1, vector.indexOf("1"));
    }

    /**
     * Test method to verify that indexOf with starting index finds elements correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf with specified starting index correctly locates elements
     * at or after the given index, ensuring proper indexed search functionality.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the indexed search variant of indexOf. This ensures the method
     * can search from arbitrary positions within the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3", "4", "5"] and verifies various
     * search scenarios with different starting indices, including successful finds and -1 returns
     * when elements are not found beyond the starting index.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns correct indices based on starting position</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return correct indices when searching from specified starting positions,
     * and -1 when elements are not found beyond the starting index.</p>
     */
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

    /**
     * Test method to verify that indexOf with starting index returns -1 for non-existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf with starting index consistently returns -1 for elements
     * not present in the vector, regardless of the starting position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate negative search results with indexed searches. This ensures
     * the method correctly handles absent elements when searching from specific positions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"] and verifies that indexOf
     * returns -1 for non-existing element "4" when searching from different starting indices (0, 1, 2).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All indexOf calls return -1 for non-existing elements</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return -1 for non-existing elements regardless of starting index.</p>
     */
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

    /**
     * Test method to verify that indexOf with starting index correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf with starting index correctly locates null elements
     * at or after the given index, ensuring proper null handling in indexed searches.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate null element search behavior with indexed searches.
     * This ensures the method can find null values from arbitrary starting positions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", null, "3", null, "5"] and verifies
     * that indexOf correctly finds null elements from different starting indices, including
     * finding the first null from index 0, second null from index 2, and no null from index 4.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", null, "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns correct indices for null elements based on starting position</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should correctly locate null elements when searching from specified starting positions.</p>
     */
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

    /**
     * Test method to verify that indexOf with starting index handles duplicate elements correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf with starting index returns the correct occurrence index
     * for duplicate elements based on the starting position, ensuring proper duplicate handling
     * in indexed searches.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate duplicate element search behavior with indexed searches.
     * This ensures the method can find specific occurrences of duplicate elements from different
     * starting positions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements ["0", "1", "1", "2", "1"] and
     * verifies that indexOf returns the correct occurrence index for element "1" when searching
     * from different starting indices (1, 2, 3, 4, 5).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["0", "1", "1", "2", "1"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf returns correct occurrence indices for duplicates based on starting position</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return the correct occurrence index for duplicate elements based on the
     * specified starting position.</p>
     */
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

    /**
     * Test method to verify that indexOf with negative starting index throws IndexOutOfBoundsException.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf throws IndexOutOfBoundsException when called with a negative
     * starting index, ensuring proper validation of input parameters.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid starting indices. This ensures
     * the method properly rejects negative index values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"] and attempts to call indexOf
     * with a negative starting index (-1), expecting an IndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should throw IndexOutOfBoundsException when called with a negative starting index.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOfWithIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.indexOf("1", -1);
    }

    /**
     * Test method to verify that indexOf returns -1 when starting index equals or exceeds vector size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that indexOf returns -1 when the starting index is equal to or greater
     * than the vector size, ensuring proper handling of out-of-bounds starting positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate boundary condition handling with starting indices at or
     * beyond the collection size. This ensures the method correctly handles these edge cases.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"] and verifies that indexOf
     * returns -1 when called with starting indices equal to (3) or greater than (5, 10) the
     * vector size for various elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- All indexOf calls return -1 for out-of-bounds starting indices</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should return -1 when starting index is equal to or greater than vector size.</p>
     */
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

    /**
     * Test method to verify edge case behavior of indexOf with starting index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies specific edge case scenarios for indexOf with starting index, including
     * exact index matches and boundary conditions, ensuring comprehensive test coverage.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate precise boundary conditions and exact matches. This ensures
     * the method handles these specific scenarios correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3", "4", "5"] and verifies
     * various edge cases including finding elements at exact starting indices, not finding elements
     * when start index is beyond their position, and finding the last element with the maximum
     * valid starting index.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements: ["1", "2", "3", "4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- indexOf handles all edge cases correctly</p>
     * <p>- VectorAdapter state remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>indexOf should correctly handle all edge case scenarios including exact index matches
     * and boundary conditions.</p>
     */
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

    /**
     * Test method to verify that lastIndexOf returns correct indices for existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly returns the last occurrence index for elements that exist in the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the basic functionality of lastIndexOf method with non-duplicate elements.
     * It ensures the method returns the correct index for each element's last occurrence.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three distinct elements and verifies that lastIndexOf returns
     * the correct index for each element when they appear only once in the vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns correct indices for all existing elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return correct last occurrence indices for all existing elements.</p>
     */
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

    /**
     * Test method to verify that lastIndexOf returns -1 for non-existing elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly returns -1 for elements that do not exist in the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's behavior when searching for elements not present in the vector.
     * It ensures proper handling of non-existent element cases.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements and verifies that lastIndexOf returns -1
     * when searching for elements that are not present in the vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for all non-existing elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return -1 for elements not present in the vector.</p>
     */
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

    /**
     * Test method to verify that lastIndexOf correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly identifies and returns the index of null elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's ability to handle null values in the vector.
     * It ensures proper null element detection and index reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector containing a null element among other elements and verifies
     * that lastIndexOf correctly returns the index of the null element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns correct index for null element</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should correctly identify and return the index of null elements.</p>
     */
    @Test
    public void testLastIndexOfNullElement()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertEquals("Should return correct last index for null element", 1, vector.lastIndexOf(null));
    }

    /**
     * Test method to verify that lastIndexOf returns -1 when called on an empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly returns -1 for any element when the vector is empty.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case behavior with empty vectors.
     * It ensures the method handles the empty collection scenario properly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector and verifies that lastIndexOf returns -1
     * for both regular elements and null values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for all search queries</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return -1 for any element when the vector is empty.</p>
     */
    @Test
    public void testLastIndexOfOnEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Should return -1 for any element in empty vector", -1, vector.lastIndexOf("1"));
        assertEquals("Should return -1 for null in empty vector", -1, vector.lastIndexOf(null));
    }

    /**
     * Test method to verify that lastIndexOf returns the last occurrence index for duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly identifies the last occurrence of duplicate elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's behavior with duplicate elements.
     * It ensures the method returns the highest index where the element appears.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with duplicate elements and verifies that lastIndexOf
     * returns the index of the last occurrence for the duplicated element.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "1", "0"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns the highest index for duplicate elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return the index of the last occurrence for duplicate elements.</p>
     */
    @Test
    public void testLastIndexOfWithDuplicateElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("1");
        vector.add("0");

        assertEquals("Should return last occurrence index for duplicate elements", 1, vector.lastIndexOf("1"));
    }

    /**
     * Test method to verify that lastIndexOf with index parameter correctly finds elements within specified bounds.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with index parameter correctly searches backwards from the specified index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the bounded search functionality of lastIndexOf.
     * It ensures the method respects the index boundary and searches backwards correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with multiple elements and verifies that lastIndexOf
     * with index parameter correctly finds elements within the specified search bounds.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns correct indices when searching within bounds</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf with index parameter should correctly find elements within the specified search range.</p>
     */
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

    /**
     * Test method to verify that lastIndexOf with index parameter returns -1 for non-existing elements within bounds.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with index parameter correctly returns -1 for non-existing elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's behavior when searching for non-existent elements
     * within specified bounds.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with elements and verifies that lastIndexOf with index parameter
     * returns -1 when searching for elements that don't exist in the vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for non-existing elements at all index bounds</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf with index parameter should return -1 for non-existing elements.</p>
     */
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

    /**
     * Test method to verify that lastIndexOf with index parameter correctly handles null elements within bounds.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with index parameter correctly identifies null elements within specified bounds.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's ability to handle null values
     * when searching within specified index bounds.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with multiple null elements and verifies that lastIndexOf
     * with index parameter correctly finds null elements within the specified search range.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", null, "3", null, "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf correctly identifies null elements within index bounds</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf with index parameter should correctly handle null elements within specified bounds.</p>
     */
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

    /**
     * Test method to verify that lastIndexOf with index parameter correctly handles duplicate elements within bounds.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with index parameter correctly identifies the last occurrence
     * of duplicate elements within specified bounds.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's behavior with duplicate elements
     * when searching within specified index ranges.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with duplicate elements and verifies that lastIndexOf
     * with index parameter correctly returns the last occurrence within the specified bounds.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["0", "1", "1", "2", "1"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns correct last occurrence indices within bounds</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf with index parameter should correctly handle duplicate elements within specified bounds.</p>
     */
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

    /**
     * Test method to verify that lastIndexOf with index parameter throws IndexOutOfBoundsException when index equals size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf throws IndexOutOfBoundsException when the index parameter equals the vector size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid index values.
     * It ensures proper error reporting when index equals vector size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with elements and attempts to call lastIndexOf with index
     * equal to the vector size, expecting an IndexOutOfBoundsException.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- IndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should throw IndexOutOfBoundsException when index equals vector size.</p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testLastIndexOfWithIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.lastIndexOf("1", 3);
    }

    /**
     * Test method to verify that lastIndexOf with index parameter returns -1 for negative indices.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf correctly returns -1 when the index parameter is negative.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's behavior with negative index values.
     * It ensures proper handling of invalid negative indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with elements and verifies that lastIndexOf returns -1
     * when called with negative index values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf returns -1 for all negative indices</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf should return -1 when index parameter is negative.</p>
     */
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

    /**
     * Test method to verify that lastIndexOf with index parameter handles edge cases correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastIndexOf with index parameter correctly handles various edge cases.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's behavior with boundary conditions
     * and edge cases in index parameter usage.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with multiple elements and verifies that lastIndexOf
     * with index parameter handles various edge cases including exact index matches,
     * boundary conditions, and minimum valid indices.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastIndexOf handles all edge cases correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastIndexOf with index parameter should correctly handle all edge cases and boundary conditions.</p>
     */
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

    /**
     * Test method to verify that elementAt returns correct elements at valid indices.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt correctly returns the element at specified valid indices.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic functionality of elementAt method with valid index values.
     * It ensures the method returns the correct element for each valid index position.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements and verifies that elementAt returns
     * the correct element for each valid index (0, 1, and 2).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- elementAt returns correct elements for all valid indices</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt should return the correct element for each valid index position.</p>
     */
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

    /**
     * Test method to verify that elementAt throws ArrayIndexOutOfBoundsException for negative indices.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt throws ArrayIndexOutOfBoundsException when called with negative indices.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative index values.
     * It ensures proper error reporting when accessing elements with negative indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with elements and attempts to call elementAt with a negative index (-1),
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt should throw ArrayIndexOutOfBoundsException when called with negative indices.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.elementAt(-1);
    }

    /**
     * Test method to verify that elementAt throws ArrayIndexOutOfBoundsException when index equals size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt throws ArrayIndexOutOfBoundsException when index equals vector size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for index values equal to vector size.
     * It ensures proper error reporting when accessing elements beyond valid range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements and attempts to call elementAt with index 3
     * (equal to vector size), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt should throw ArrayIndexOutOfBoundsException when index equals vector size.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.elementAt(3);
    }

    /**
     * Test method to verify that elementAt throws ArrayIndexOutOfBoundsException on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt throws ArrayIndexOutOfBoundsException when called on an empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for empty vectors.
     * It ensures proper error reporting when attempting to access elements from an empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector and attempts to call elementAt with index 0,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt should throw ArrayIndexOutOfBoundsException when called on an empty vector.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testElementAtOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.elementAt(0);
    }

    /**
     * Test method to verify that elementAt correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that elementAt correctly returns null for elements that are null.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's ability to handle null values in the vector.
     * It ensures proper null element retrieval and reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector containing null elements and verifies that elementAt
     * correctly returns null when accessing indices containing null values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: [null, "1", null]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- elementAt returns null for indices containing null elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>elementAt should correctly return null for elements that are null.</p>
     */
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

    /**
     * Test method to verify that firstElement returns the first element of non-empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement correctly returns the first element of a non-empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic functionality of firstElement method with non-empty vectors.
     * It ensures the method returns the correct first element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements and verifies that firstElement
     * returns the first element ("1") of the vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- firstElement returns the first element correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement should return the first element of a non-empty vector.</p>
     */
    @Test
    public void testFirstElementNonEmptyVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return first element", "1", vector.firstElement());
    }

    /**
     * Test method to verify that firstElement throws NoSuchElementException on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement throws NoSuchElementException when called on an empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for empty vectors.
     * It ensures proper error reporting when attempting to access first element from empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector and attempts to call firstElement,
     * expecting a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement should throw NoSuchElementException when called on an empty vector.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testFirstElementEmptyVector()
    {
        vector = new VectorAdapter();
        vector.firstElement();
    }

    /**
     * Test method to verify that firstElement correctly handles null first elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement correctly returns null when the first element is null.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's ability to handle null values as first element.
     * It ensures proper null element retrieval and reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with null as the first element and verifies that firstElement
     * correctly returns null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: [null, null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- firstElement returns null correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement should correctly return null when the first element is null.</p>
     */
    @Test
    public void testFirstElementNullElement()
    {
        vector = new VectorAdapter();
        vector.add(null);
        vector.add(null);
        vector.add("3");

        assertNull("Should return null first element", vector.firstElement());
    }

    /**
     * Test method to verify that lastElement returns the last element of non-empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastElement correctly returns the last element of a non-empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic functionality of lastElement method with non-empty vectors.
     * It ensures the method returns the correct last element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements and verifies that lastElement
     * returns the last element ("3") of the vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastElement returns the last element correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastElement should return the last element of a non-empty vector.</p>
     */
    @Test
    public void testLastElementNonEmptyVector()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("Should return last element", "3", vector.lastElement());
    }

    /**
     * Test method to verify that lastElement throws NoSuchElementException on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastElement throws NoSuchElementException when called on an empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for empty vectors.
     * It ensures proper error reporting when attempting to access last element from empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector and attempts to call lastElement,
     * expecting a NoSuchElementException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NoSuchElementException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastElement should throw NoSuchElementException when called on an empty vector.</p>
     */
    @Test(expected = NoSuchElementException.class)
    public void testLastElementEmptyVector()
    {
        vector = new VectorAdapter();
        vector.lastElement();
    }

    /**
     * Test method to verify that lastElement correctly handles null last elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that lastElement correctly returns null when the last element is null.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's ability to handle null values as last element.
     * It ensures proper null element retrieval and reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with null as the last element and verifies that lastElement
     * correctly returns null.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, null]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- lastElement returns null correctly</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>lastElement should correctly return null when the last element is null.</p>
     */
    @Test
    public void testLastElementNullElement()
    {
        vector = new VectorAdapter();

        vector.add("1");
        vector.add(null);
        vector.add(null);

        assertNull("Should return null last element", vector.lastElement());
    }

    /**
     * Test method to verify that firstElement and lastElement return same value for single-element collections.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that firstElement and lastElement return the same value for single-element vectors.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case behavior with single-element collections.
     * It ensures both methods return identical values when the vector contains only one element.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with a single element and verifies that firstElement
     * and lastElement return the same value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 1 element: ["1"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- firstElement and lastElement return identical values</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>firstElement and lastElement should return the same value for single-element vectors.</p>
     */
    @Test
    public void testFirstAndLastElementInSingleElementCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");

        assertEquals("First and last element should be equal for single element", vector.firstElement(), vector.lastElement());
    }

    /**
     * Test method to verify that setElementAt correctly updates elements at valid indices.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt correctly updates elements at specified valid indices.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic functionality of setElementAt method with valid index values.
     * It ensures the method correctly updates elements while preserving other elements and vector size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, updates the element at index 1,
     * and verifies that the update was successful while other elements remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 is updated, other elements unchanged, size remains same</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt should correctly update elements at valid indices while preserving other elements and size.</p>
     */
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

    /**
     * Test method to verify that setElementAt throws ArrayIndexOutOfBoundsException for negative indices.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt throws ArrayIndexOutOfBoundsException when called with negative indices.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for invalid negative index values.
     * It ensures proper error reporting when attempting to update elements with negative indices.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with elements and attempts to call setElementAt with a negative index (-1),
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt should throw ArrayIndexOutOfBoundsException when called with negative indices.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setElementAt("3", -1);
    }

    /**
     * Test method to verify that setElementAt throws ArrayIndexOutOfBoundsException when index equals size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt throws ArrayIndexOutOfBoundsException when index equals vector size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for index values equal to vector size.
     * It ensures proper error reporting when attempting to update elements beyond valid range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with two elements and attempts to call setElementAt with index 2
     * (equal to vector size), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt should throw ArrayIndexOutOfBoundsException when index equals vector size.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.setElementAt("3", 2);
    }

    /**
     * Test method to verify that setElementAt throws ArrayIndexOutOfBoundsException on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt throws ArrayIndexOutOfBoundsException when called on an empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate exception handling for empty vectors.
     * It ensures proper error reporting when attempting to update elements in an empty collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty vector and attempts to call setElementAt with index 0,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt should throw ArrayIndexOutOfBoundsException when called on an empty vector.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetElementAtOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.setElementAt("1", 0);
    }

    /**
     * Test method to verify that setElementAt correctly updates elements at the beginning of the vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt correctly updates elements at index 0 (beginning of vector).</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case behavior when updating the first element.
     * It ensures the method correctly updates the first element while preserving other elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, updates the element at index 0,
     * and verifies that the update was successful while other elements remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First element is updated, other elements unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt should correctly update elements at the beginning of the vector.</p>
     */
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

    /**
     * Test method to verify that setElementAt correctly updates elements at the end of the vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt correctly updates elements at the last index (end of vector).</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate edge case behavior when updating the last element.
     * It ensures the method correctly updates the last element while preserving other elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, updates the element at index 2,
     * and verifies that the update was successful while other elements remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Last element is updated, other elements unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt should correctly update elements at the end of the vector.</p>
     */
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

    /**
     * Test method to verify that setElementAt correctly handles setting elements to null.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt correctly updates elements to null values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's ability to handle null value assignments.
     * It ensures proper null element setting and preservation of other elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, sets the element at index 1 to null,
     * and verifies that the update was successful while other elements remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 is set to null, other elements unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt should correctly handle setting elements to null values.</p>
     */
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

    /**
     * Test method to verify that setElementAt correctly handles multiple updates to the same index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that setElementAt correctly handles multiple consecutive updates to the same index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the method's behavior with repeated updates to the same position.
     * It ensures the method preserves the last set value and maintains vector integrity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a vector with three elements, performs multiple setElementAt operations
     * on the same index, and verifies that only the last update persists while vector size remains unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 contains last set value, size remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>setElementAt should correctly handle multiple updates to the same index, preserving the last value.</p>
     */
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

    /**
     * Test method to verify that removeElementAt correctly removes element at valid index and shifts subsequent elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt correctly removes an element at a valid index, decreases the size,
     * and properly shifts subsequent elements to maintain list continuity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the core functionality of element removal at a middle index,
     * ensuring proper size management and element shifting behavior.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, removes the element at index 1,
     * and verifies that the size decreases to 2, the first element remains unchanged,
     * and the third element shifts to the second position.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 2</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt should successfully remove element at index 1, decrease size to 2,
     * and shift subsequent elements leftward.</p>
     */
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

    /**
     * Test method to verify that removeElementAt throws ArrayIndexOutOfBoundsException when index is negative.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt throws ArrayIndexOutOfBoundsException when attempting
     * to remove an element at a negative index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for invalid negative index values,
     * ensuring boundary checking is implemented correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and attempts to remove an element at index -1,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt should throw ArrayIndexOutOfBoundsException when index is negative.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.removeElementAt(-1);
    }

    /**
     * Test method to verify that removeElementAt throws ArrayIndexOutOfBoundsException when index equals size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt throws ArrayIndexOutOfBoundsException when attempting
     * to remove an element at an index equal to the current size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for index values that are
     * exactly equal to the size, which is beyond the valid range.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and attempts to remove an element at index 2,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- Current size is 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt should throw ArrayIndexOutOfBoundsException when index equals size.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.removeElementAt(2);
    }

    /**
     * Test method to verify that removeElementAt throws ArrayIndexOutOfBoundsException on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt throws ArrayIndexOutOfBoundsException when attempting
     * to remove an element from an empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when attempting to remove
     * elements from an empty collection, ensuring robustness.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and attempts to remove an element at index 0,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt should throw ArrayIndexOutOfBoundsException when called on empty vector.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveElementAtOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.removeElementAt(0);
    }

    /**
     * Test method to verify that removeElementAt correctly removes element at beginning of list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt correctly removes the first element and shifts
     * all subsequent elements leftward.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal at the beginning boundary case,
     * ensuring proper shifting of all remaining elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, removes the element at index 0,
     * and verifies that the size decreases and all elements shift leftward appropriately.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 2</p>
     * <p>- Element at index 0 is "2"</p>
     * <p>- Element at index 1 is "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt should successfully remove first element and shift all subsequent elements leftward.</p>
     */
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

    /**
     * Test method to verify that removeElementAt correctly removes element at end of list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt correctly removes the last element without
     * affecting preceding elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal at the end boundary case,
     * ensuring no unnecessary shifting occurs for preceding elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, removes the element at the last index,
     * and verifies that the size decreases while preceding elements remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 2</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is "2"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt should successfully remove last element without affecting preceding elements.</p>
     */
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

    /**
     * Test method to verify that removeElementAt correctly handles removal of null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt correctly handles removal of null elements
     * and properly shifts subsequent elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements during removal,
     * ensuring the implementation correctly handles special values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing a null element, removes the null element,
     * and verifies proper size reduction and element shifting.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 2</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElementAt should successfully remove null element and shift subsequent elements leftward.</p>
     */
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

    /**
     * Test method to verify that removeElementAt works correctly with multiple consecutive removals.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElementAt correctly handles multiple consecutive removal operations,
     * maintaining proper element positioning after each removal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the cumulative effect of multiple removal operations,
     * ensuring the implementation maintains consistency throughout sequential modifications.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with five elements, performs three consecutive removals
     * at the same index position, and verifies the final state of the vector.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 5 elements: ["1", "2", "3", "4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 2</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is "5"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Multiple removeElementAt operations should correctly remove elements and maintain
     * proper element positioning after each removal.</p>
     */
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

    /**
     * Test method to verify that insertElementAt correctly inserts element at valid middle index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt correctly inserts an element at a valid middle index,
     * increases the size, and properly shifts subsequent elements to the right.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the core functionality of element insertion at a middle index,
     * ensuring proper size management and element shifting behavior.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, inserts a new element at index 1,
     * and verifies that the size increases to 3, elements are properly positioned,
     * and subsequent elements are shifted rightward.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 3</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is "2"</p>
     * <p>- Element at index 2 is "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt should successfully insert element at middle index, increase size,
     * and shift subsequent elements rightward.</p>
     */
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

    /**
     * Test method to verify that insertElementAt throws ArrayIndexOutOfBoundsException when index is negative.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt throws ArrayIndexOutOfBoundsException when attempting
     * to insert an element at a negative index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for invalid negative index values
     * during insertion operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and attempts to insert an element at index -1,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt should throw ArrayIndexOutOfBoundsException when index is negative.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInsertElementAtNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.insertElementAt("3", -1);
    }

    /**
     * Test method to verify that insertElementAt throws ArrayIndexOutOfBoundsException when index exceeds size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt throws ArrayIndexOutOfBoundsException when attempting
     * to insert an element at an index greater than the current size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for index values that exceed
     * the current size during insertion operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements and attempts to insert an element at index 3,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- Current size is 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt should throw ArrayIndexOutOfBoundsException when index exceeds size.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInsertElementAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.insertElementAt("3", 3);
    }

    /**
     * Test method to verify that insertElementAt correctly inserts element into empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt correctly inserts an element into an empty vector
     * at the first position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion into an empty collection,
     * ensuring proper initialization and element placement.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and inserts an element at index 0,
     * verifying that the size increases to 1 and the element is correctly positioned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 1</p>
     * <p>- Element at index 0 is "1"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt should successfully insert element into empty vector at index 0.</p>
     */
    @Test
    public void testInsertElementAtEmptyVector()
    {
        vector = new VectorAdapter();

        vector.insertElementAt("1", 0);

        assertEquals("Size should be 1", 1, vector.size());
        assertEquals("Inserted element should be at index 0", "1", vector.get(0));
    }

    /**
     * Test method to verify that insertElementAt correctly inserts element at beginning of list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt correctly inserts an element at the beginning
     * and shifts all existing elements rightward.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at the beginning boundary case,
     * ensuring proper shifting of all existing elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, inserts a new element at index 0,
     * and verifies that all existing elements are shifted rightward appropriately.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 3</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is "2"</p>
     * <p>- Element at index 2 is "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt should successfully insert element at beginning and shift all existing elements rightward.</p>
     */
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

    /**
     * Test method to verify that insertElementAt correctly inserts element at end of list.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt correctly appends an element at the end
     * without affecting preceding elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at the end boundary case,
     * ensuring no unnecessary shifting occurs for preceding elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, inserts a new element at the end (index equal to size),
     * and verifies that preceding elements remain unchanged while the new element is appended.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 3</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is "2"</p>
     * <p>- Element at index 2 is "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt should successfully append element at end without affecting preceding elements.</p>
     */
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

    /**
     * Test method to verify that insertElementAt correctly handles insertion of null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt correctly handles insertion of null elements
     * and properly shifts subsequent elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements during insertion,
     * ensuring the implementation correctly handles special values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, inserts a null element at the middle,
     * and verifies proper size increase, element positioning, and shifting.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 3</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is null</p>
     * <p>- Element at index 2 is "3"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>insertElementAt should successfully insert null element and shift subsequent elements rightward.</p>
     */
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

    /**
     * Test method to verify that insertElementAt works correctly with multiple consecutive insertions.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that insertElementAt correctly handles multiple consecutive insertion operations,
     * maintaining proper element positioning after each insertion.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the cumulative effect of multiple insertion operations,
     * ensuring the implementation maintains consistency throughout sequential modifications.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, performs two consecutive insertions
     * at adjacent positions, and verifies the final state of the vector with all elements
     * in correct order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 4</p>
     * <p>- Element at index 0 is "1"</p>
     * <p>- Element at index 1 is "2"</p>
     * <p>- Element at index 2 is "3"</p>
     * <p>- Element at index 3 is "4"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>Multiple insertElementAt operations should correctly insert elements and maintain
     * proper element ordering after each insertion.</p>
     */
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

    /**
     * Test method to verify that addElement correctly appends element to non-empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addElement correctly appends an element to a non-empty vector,
     * increasing the size and maintaining existing elements in their positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the append functionality of addElement on a non-empty collection,
     * ensuring proper size management and element preservation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, appends a third element using addElement,
     * and verifies that the size increases to 3 while existing elements remain unchanged.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 3</p>
     * <p>- Existing elements remain unchanged</p>
     * <p>- New element is appended at the end</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addElement should successfully append element to non-empty vector while preserving existing elements.</p>
     */
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

    /**
     * Test method to verify that addElement correctly adds element to empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addElement correctly adds an element to an empty vector,
     * changing its state from empty to non-empty with proper element placement.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the initialization functionality of addElement on an empty collection,
     * ensuring proper state transition and element positioning.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, adds an element using addElement,
     * and verifies that the vector transitions from empty to containing one element at index 0.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 1</p>
     * <p>- Element is placed at index 0</p>
     * <p>- Vector is no longer empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addElement should successfully initialize empty vector with first element at index 0.</p>
     */
    @Test
    public void testAddElementToEmptyVector()
    {
        vector = new VectorAdapter();
        vector.addElement("1");

        assertEquals("Size should be 1 after adding element", 1, vector.size());
        assertEquals("Element should be added at index 0", "1", vector.get(0));
        assertFalse("Vector should not be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that addElement correctly handles null element insertion.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addElement correctly handles the insertion of null elements,
     * increasing size and properly storing the null value.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null values during element addition,
     * ensuring the implementation supports null elements as required by the collection interface.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, adds a null element using addElement,
     * and verifies that the size increases and the null value is correctly stored.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter size is 1</p>
     * <p>- Element at index 0 is null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addElement should successfully add null element and store it as a valid collection element.</p>
     */
    @Test
    public void testAddElementNullElement()
    {
        vector = new VectorAdapter();
        vector.addElement(null);

        assertEquals("Size should be 1 after adding null", 1, vector.size());
        assertNull("Element should be null", vector.get(0));
    }

    /**
     * Test method to verify that removeElement correctly removes existing element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement correctly identifies and removes an existing element,
     * returning true and properly adjusting the collection size and element positions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the successful removal of existing elements,
     * ensuring proper return value, size reduction, and element shifting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, removes the middle element,
     * and verifies successful removal, size reduction, and proper element shifting.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement returns true</p>
     * <p>- VectorAdapter size is 2</p>
     * <p>- Elements are properly shifted after removal</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement should successfully remove existing element and return true.</p>
     */
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

    /**
     * Test method to verify that removeElement correctly handles non-existing element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement correctly identifies non-existing elements,
     * returning false and leaving the collection unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of removal attempts for elements
     * not present in the collection, ensuring no modifications occur.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, attempts to remove a non-existing element,
     * and verifies that the collection remains unchanged and false is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement returns false</p>
     * <p>- VectorAdapter remains unchanged</p>
     * <p>- All elements remain in their original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement should return false and leave collection unchanged for non-existing elements.</p>
     */
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

    /**
     * Test method to verify that removeElement correctly handles null element removal.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement correctly identifies and removes null elements,
     * returning true and properly adjusting the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null element removal,
     * ensuring the implementation can identify and remove null values from the collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing a null element, removes the null element,
     * and verifies successful removal and proper collection adjustment.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement returns true</p>
     * <p>- VectorAdapter size is 2</p>
     * <p>- Remaining elements are properly positioned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement should successfully remove null element and return true.</p>
     */
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

    /**
     * Test method to verify that removeElement correctly handles empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement correctly handles removal attempts on empty vectors,
     * returning false and maintaining the empty state.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of removal operations on empty collections,
     * ensuring no exceptions are thrown and the empty state is preserved.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, attempts to remove an element,
     * and verifies that false is returned and the vector remains empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement returns false</p>
     * <p>- VectorAdapter remains empty</p>
     * <p>- Size remains 0</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement should return false and maintain empty state when called on empty vector.</p>
     */
    @Test
    public void testRemoveElementFromEmptyVector()
    {
        vector = new VectorAdapter();

        assertFalse("Should return false for empty vector", vector.removeElement("1"));
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    /**
     * Test method to verify that removeElement only removes first occurrence of element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeElement only removes the first occurrence of duplicate elements,
     * leaving subsequent occurrences intact in the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the first-occurrence removal behavior for duplicate elements,
     * ensuring proper collection management when multiple identical elements exist.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements, removes the first occurrence,
     * and verifies that only the first instance is removed while subsequent duplicates remain.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 4 elements: ["1", "2", "1", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeElement returns true</p>
     * <p>- First occurrence of "1" is removed</p>
     * <p>- Second occurrence of "1" remains</p>
     * <p>- Other elements are properly shifted</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeElement should remove only the first occurrence of duplicate elements.</p>
     */
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

    /**
     * Test method to verify that removeAllElements correctly handles empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAllElements correctly handles empty vectors,
     * maintaining the empty state without throwing exceptions.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the idempotent behavior of removeAllElements on empty collections,
     * ensuring the operation can be safely called on already-empty vectors.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, calls removeAllElements,
     * and verifies that the vector remains empty and no exceptions are thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter remains empty</p>
     * <p>- Size remains 0</p>
     * <p>- No exceptions thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAllElements should maintain empty state when called on empty vector.</p>
     */
    @Test
    public void testRemoveAllElementsEmptyVector()
    {
        vector = new VectorAdapter();
        vector.removeAllElements();

        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    /**
     * Test method to verify that removeAllElements correctly clears vector with multiple elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAllElements correctly clears a vector containing multiple elements,
     * resetting it to empty state regardless of content.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the complete clearance functionality of removeAllElements,
     * ensuring all elements are removed and the vector returns to empty state.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements, calls removeAllElements,
     * and verifies that the vector becomes completely empty.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- Size is 0</p>
     * <p>- No elements remain</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAllElements should completely clear vector regardless of content.</p>
     */
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

    /**
     * Test method to verify that removeAllElements correctly clears vector with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAllElements correctly clears a vector containing only null elements,
     * handling special values appropriately during clearance.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements during complete clearance,
     * ensuring the operation works correctly with special values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing only null elements, calls removeAllElements,
     * and verifies complete clearance regardless of null content.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter is empty</p>
     * <p>- Size is 0</p>
     * <p>- All null elements are removed</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAllElements should clear vector completely even when containing only null elements.</p>
     */
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

    /**
     * Test method to verify that clone correctly handles empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone correctly creates a copy of an empty vector,
     * producing a non-null empty clone with proper size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the cloning functionality for empty vectors,
     * ensuring proper object creation and state preservation.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, clones it, and verifies that the clone
     * is non-null, empty, and has the correct size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Clone is non-null</p>
     * <p>- Clone size is 0</p>
     * <p>- Clone is empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone should produce a non-null empty vector when cloning empty vector.</p>
     */
    @Test
    public void testCloneEmptyVector()
    {
        vector = new VectorAdapter();
        VectorAdapter vec = (VectorAdapter) vector.clone();

        assertNotNull("Clone should not be null", vec);
        assertEquals("Clone size should match original", vector.size(), vec.size());
        assertTrue("Clone should be empty", vec.isEmpty());
    }

    /**
     * Test method to verify that clone correctly copies vector with multiple elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone correctly creates a deep copy of a vector with multiple elements,
     * preserving all element values and order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate complete element preservation during cloning,
     * ensuring all values and their order are maintained in the clone.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with multiple elements, clones it,
     * and verifies that all elements are preserved in the correct order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Clone contains all original elements</p>
     * <p>- Element order is preserved</p>
     * <p>- All element values match original</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone should produce an exact copy with all elements preserved in order.</p>
     */
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

    /**
     * Test method to verify that clone correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone correctly handles vectors containing null elements,
     * preserving null values in the cloned collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements during cloning,
     * ensuring special values are correctly preserved in the clone.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing only null elements, clones it,
     * and verifies that all null values are preserved in the clone.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Clone contains 3 null elements</p>
     * <p>- All null values are preserved</p>
     * <p>- Clone size matches original</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone should preserve null elements exactly as in the original vector.</p>
     */
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

    /**
     * Test method to verify that clone produces independent copy.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clone produces a truly independent copy where modifications
     * to the original do not affect the clone and vice versa.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the independence of cloned objects,
     * ensuring modifications to either original or clone don't affect the other.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter, clones it, modifies both original and clone,
     * and verifies that changes in one don't affect the other.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Original and clone can be modified independently</p>
     * <p>- Changes to original don't affect clone</p>
     * <p>- Changes to clone don't affect original</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clone should produce an independent copy that can be modified separately from original.</p>
     */
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

    /**
     * Test method to verify that toArray returns empty array for empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray correctly returns an empty array when called on an empty vector,
     * maintaining proper array length and null safety.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the boundary case of toArray operation on empty collections,
     * ensuring proper array creation and length handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, calls toArray, and verifies that a non-null
     * empty array is returned with length matching the vector size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returned array is non-null</p>
     * <p>- Array length is 0</p>
     * <p>- No elements in array</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray should return non-null empty array for empty vector.</p>
     */
    @Test
    public void testToArrayEmptyVector()
    {
        vector = new VectorAdapter();
        Object[] arr = vector.toArray();

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should match vector size", 0, arr.length);
    }

    /**
     * Test method to verify that toArray correctly converts vector with multiple elements to array.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray correctly converts a vector with multiple elements
     * to an array, preserving element order and values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the core functionality of toArray for non-empty collections,
     * ensuring complete and accurate element transfer to array format.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, calls toArray, and verifies
     * that the returned array contains all elements in correct order with matching values.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returned array is non-null</p>
     * <p>- Array length matches vector size</p>
     * <p>- All elements preserved in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray should return array containing all vector elements in correct order.</p>
     */
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

    /**
     * Test method to verify that toArray correctly handles vector with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray correctly handles vectors containing null elements,
     * preserving null values in the returned array.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements during array conversion,
     * ensuring special values are correctly transferred to the array.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing only null elements, calls toArray,
     * and verifies that all null values are preserved in the returned array.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returned array is non-null</p>
     * <p>- Array contains 3 null elements</p>
     * <p>- All null values preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray should preserve null elements in returned array.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) returns empty array for empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) correctly handles empty vectors when provided
     * with an empty reference array, returning appropriate empty array.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the typed array conversion for empty collections,
     * ensuring proper array type and length handling.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, calls toArray with empty reference array,
     * and verifies that an empty array is returned.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     * <p>- Reference array is empty (length 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Returned array is non-null</p>
     * <p>- Array length is 0</p>
     * <p>- Empty array returned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should return empty array for empty vector with empty reference array.</p>
     */
    @Test
    public void testToArrayWithReferenceEmptyVector()
    {
        vector = new VectorAdapter();
        Object[] array = new Object[0];
        Object[] arr = vector.toArray(array);

        assertNotNull("Returned array should not be null", arr);
        assertEquals("Array length should be 0", 0, arr.length);
    }

    /**
     * Test method to verify that toArray(T[]) returns same array when size matches exactly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) returns the reference array itself when
     * its length exactly matches the vector size, with elements properly copied.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the optimization case where reference array
     * size matches collection size, ensuring same array return and proper element copying.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, calls toArray with
     * same-sized reference array, and verifies same array return with correct elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- Reference array length is 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Same array reference returned</p>
     * <p>- All elements correctly copied</p>
     * <p>- Array contents match vector</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should return same array when size matches exactly.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) handles larger reference arrays correctly.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) correctly handles reference arrays larger
     * than vector size, with proper null termination and existing element preservation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior with oversized reference arrays,
     * ensuring proper null termination and preservation of existing array elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with two elements, calls toArray with larger
     * reference array containing existing values, and verifies proper null termination
     * and element preservation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- Reference array length is 5 with existing values</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Same array reference returned</p>
     * <p>- Vector elements copied to beginning</p>
     * <p>- Null termination after vector elements</p>
     * <p>- Existing elements beyond null preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should null-terminate and preserve existing elements in larger arrays.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) creates new array when reference is too small.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) creates a new array when the reference array
     * is smaller than vector size, with all elements properly copied.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the array creation behavior for undersized
     * reference arrays, ensuring proper new array allocation and element copying.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, calls toArray with
     * smaller reference array, and verifies new array creation with all elements copied.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 elements: ["1", "2", "3"]</p>
     * <p>- Reference array length is 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- New array created</p>
     * <p>- New array length matches vector size</p>
     * <p>- All elements correctly copied</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should create new array when reference array is too small.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) handles null elements in larger arrays.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) correctly handles null elements when
     * using larger reference arrays, with proper null termination.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the combination of null elements and
     * oversized reference arrays, ensuring proper null handling and termination.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with null elements, calls toArray with
     * larger reference array, and verifies proper null preservation and termination.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 3 null elements</p>
     * <p>- Reference array length is 5</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Same array reference returned</p>
     * <p>- Null elements preserved</p>
     * <p>- Proper null termination after vector elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should handle null elements correctly with proper termination.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) throws NullPointerException for null array reference.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) throws NullPointerException when provided
     * with a null array reference, ensuring proper null validation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the exception handling for null array references,
     * ensuring proper error detection and reporting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, calls toArray with null reference,
     * and expects NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- Reference array is null</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     * <p>- No array returned</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should throw NullPointerException for null array reference.</p>
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayWithReferenceNullArray()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.toArray(null);
    }

    /**
     * Test method to verify that toArray(T[]) null-terminates correctly for empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) correctly null-terminates when converting
     * empty vector using reference array with existing values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the null-termination behavior for empty collections
     * with non-empty reference arrays, ensuring proper array modification.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, calls toArray with reference array
     * containing values, and verifies proper null-termination and value preservation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is empty (size 0)</p>
     * <p>- Reference array contains existing values</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Same array reference returned</p>
     * <p>- First element set to null</p>
     * <p>- Subsequent elements preserved</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should null-terminate empty vector and preserve subsequent elements.</p>
     */
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

    /**
     * Test method to verify that toArray(T[]) handles zero-length reference arrays.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that toArray(T[]) correctly handles zero-length reference arrays
     * by creating a new array of appropriate size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of zero-length reference arrays,
     * ensuring proper new array creation and element copying.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements, calls toArray with zero-length
     * reference array, and verifies new array creation with proper elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with 2 elements: ["1", "2"]</p>
     * <p>- Reference array length is 0</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- New array created</p>
     * <p>- New array length matches vector size</p>
     * <p>- All elements correctly copied</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toArray(T[]) should create new array for zero-length reference arrays.</p>
     */
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

    /**
     * Test method to verify that get() returns correct elements at valid indices.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the get() method correctly returns elements at specified valid indices
     * in a non-empty VectorAdapter, ensuring proper element retrieval functionality.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate basic element retrieval functionality for valid indices.
     * This ensures that the VectorAdapter maintains proper element ordering and indexing.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements ["1", "2", "3"] and verifies that
     * get() returns the correct element at each index position (0, 1, 2).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty</p>
     * <p>- Three elements ["1", "2", "3"] are added to the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter contains three elements in correct order</p>
     * <p>- get() method returns expected elements at each index</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get() should return "1" at index 0, "2" at index 1, and "3" at index 2.</p>
     */
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

    /**
     * Test method to verify that get() throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the get() method throws ArrayIndexOutOfBoundsException when
     * attempting to access an element using a negative index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for invalid negative indices,
     * ensuring the VectorAdapter maintains index boundary integrity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"] and attempts to call
     * get(-1), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with three elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get(-1) should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.get(-1);
    }

    /**
     * Test method to verify that get() throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the get() method throws ArrayIndexOutOfBoundsException when
     * attempting to access an element using an index equal to the vector's size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for indices at the upper boundary,
     * ensuring the VectorAdapter maintains index boundary integrity.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"] and attempts to call
     * get(3) (where size is 3), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with three elements ["1", "2", "3"]</p>
     * <p>- Vector size is 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get(3) should throw ArrayIndexOutOfBoundsException when vector size is 3.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.get(3);
    }

    /**
     * Test method to verify that get() throws ArrayIndexOutOfBoundsException on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the get() method throws ArrayIndexOutOfBoundsException when
     * attempting to access any element from an empty VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for empty collections,
     * ensuring the VectorAdapter maintains index boundary integrity even when empty.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and attempts to call get(0), expecting an
     * ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get(0) should throw ArrayIndexOutOfBoundsException on empty vector.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.get(0);
    }

    /**
     * Test method to verify that get() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the get() method correctly returns null values for elements
     * that were explicitly set to null in the VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null elements, ensuring the
     * VectorAdapter maintains and returns null values correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three null elements and verifies that
     * get() returns null for each index position (0, 1, 2).</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty</p>
     * <p>- Three null elements are added to the vector</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- VectorAdapter contains three null elements</p>
     * <p>- get() method returns null for each index</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>get() should return null for all indices (0, 1, 2).</p>
     */
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

    /**
     * Test method to verify that set() correctly replaces elements and returns previous values at valid indices.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() method correctly replaces elements at specified indices
     * and returns the previous values, while maintaining vector integrity.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element replacement functionality, ensuring proper
     * return values and vector state maintenance.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], replaces the element
     * at index 1 with "4", and verifies the return value, updated element, and unchanged elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 is updated to "4"</p>
     * <p>- Other elements remain unchanged</p>
     * <p>- Vector size remains 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(1, "4") should return "2", element at index 1 should be "4", and other elements should remain unchanged.</p>
     */
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

    /**
     * Test method to verify that set() throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() method throws ArrayIndexOutOfBoundsException when
     * attempting to replace an element using a negative index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for invalid negative indices
     * during element replacement operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2"] and attempts to call
     * set(-1, "3"), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(-1, "3") should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.set(-1, "3");
    }

    /**
     * Test method to verify that set() throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() method throws ArrayIndexOutOfBoundsException when
     * attempting to replace an element using an index equal to the vector's size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for indices at the upper boundary
     * during element replacement operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2"] and attempts to call
     * set(2, "3") (where size is 2), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     * <p>- Vector size is 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(2, "3") should throw ArrayIndexOutOfBoundsException when vector size is 2.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.set(2, "3");
    }

    /**
     * Test method to verify that set() throws ArrayIndexOutOfBoundsException on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() method throws ArrayIndexOutOfBoundsException when
     * attempting to replace any element in an empty VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for empty collections
     * during element replacement operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and attempts to call set(0, "1"), expecting an
     * ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty (size 0)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- VectorAdapter remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(0, "1") should throw ArrayIndexOutOfBoundsException on empty vector.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.set(0, "1");
    }

    /**
     * Test method to verify that set() correctly replaces the first element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() method correctly replaces the first element (index 0)
     * and returns the previous value, while maintaining other elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element replacement at the beginning of the vector,
     * ensuring proper handling of boundary conditions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], replaces the first element
     * with "0", and verifies the return value, updated element, and unchanged subsequent elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First element is updated to "0"</p>
     * <p>- Other elements remain unchanged</p>
     * <p>- Vector size remains 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(0, "0") should return "1", element at index 0 should be "0", and other elements should remain unchanged.</p>
     */
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

    /**
     * Test method to verify that set() correctly replaces the last element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() method correctly replaces the last element
     * and returns the previous value, while maintaining other elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element replacement at the end of the vector,
     * ensuring proper handling of boundary conditions.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], replaces the last element
     * with "4", and verifies the return value, updated element, and unchanged previous elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Last element is updated to "4"</p>
     * <p>- Other elements remain unchanged</p>
     * <p>- Vector size remains 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(2, "4") should return "3", element at index 2 should be "4", and other elements should remain unchanged.</p>
     */
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

    /**
     * Test method to verify that set() correctly handles null values.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() method correctly replaces elements with null values
     * and returns the previous values.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null values during element replacement,
     * ensuring the VectorAdapter can store and retrieve null values correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], replaces the element
     * at index 1 with null, and verifies the return value, updated element, and unchanged elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 is updated to null</p>
     * <p>- Other elements remain unchanged</p>
     * <p>- Vector size remains 3</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>set(1, null) should return "2", element at index 1 should be null, and other elements should remain unchanged.</p>
     */
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

    /**
     * Test method to verify that set() works correctly with multiple consecutive replacements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the set() method correctly handles multiple consecutive
     * replacements at the same index position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of multiple replacements at the same index,
     * ensuring the VectorAdapter maintains consistency through sequential operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], performs three consecutive
     * set operations at index 1 with different values, and verifies the final state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element at index 1 contains the last set value "6"</p>
     * <p>- Vector size remains 3</p>
     * <p>- Other elements remain unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After multiple set operations at index 1, the element should contain the last set value "6".</p>
     */
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

    /**
     * Test method to verify that add() correctly appends elements to a non-empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add() method correctly appends elements to a non-empty
     * VectorAdapter and returns true, while maintaining existing elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element appending functionality to non-empty vectors,
     * ensuring proper return values and vector state maintenance.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with one element ["1"], adds a second element "2",
     * and verifies the return value, size, and element order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with one element ["1"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector contains two elements ["1", "2"]</p>
     * <p>- Size is 2</p>
     * <p>- Elements are in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add("2") should return true, size should be 2, elements should be ["1", "2"].</p>
     */
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

    /**
     * Test method to verify that add() correctly adds elements to an empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add() method correctly adds elements to an empty
     * VectorAdapter and returns true, updating the vector state appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element addition to empty vectors,
     * ensuring proper initialization and state management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, adds an element "1",
     * and verifies the return value, size, element presence, and emptiness state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector contains one element ["1"]</p>
     * <p>- Size is 1</p>
     * <p>- Vector is not empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add("1") should return true, size should be 1, element at index 0 should be "1", isEmpty() should return false.</p>
     */
    @Test
    public void testAddToEmptyVector()
    {
        vector = new VectorAdapter();

        assertTrue("Should return true", vector.add("1"));
        assertEquals("Size should be 1 after adding element", 1, vector.size());
        assertEquals("Element should be added at index 0", "1", vector.get(0));
        assertFalse("Vector should not be empty", vector.isEmpty());
    }

    /**
     * Test method to verify that add() correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add() method correctly appends null elements to a
     * VectorAdapter and returns true.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null values during element addition,
     * ensuring the VectorAdapter can store and retrieve null values correctly.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, adds a null element,
     * and verifies the return value, size, and element value.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector contains one null element</p>
     * <p>- Size is 1</p>
     * <p>- Element at index 0 is null</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add(null) should return true, size should be 1, get(0) should return null.</p>
     */
    @Test
    public void testAddNullElement()
    {
        vector = new VectorAdapter();

        assertTrue("Should return true", vector.add(null));
        assertEquals("Size should be 1 after adding null", 1, vector.size());
        assertNull("Element should be null", vector.get(0));
    }

    /**
     * Test method to verify that remove(Object) returns true and removes existing element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(Object) method correctly removes an existing element
     * from the VectorAdapter, returns true, and maintains proper element ordering after removal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate successful element removal functionality, ensuring
     * proper return values, size adjustment, and element shifting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], removes element "2",
     * and verifies the return value, size reduction, and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "2" is removed from the vector</p>
     * <p>- Size is reduced to 2</p>
     * <p>- Elements are properly shifted: ["1", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove("2") should return true, size should be 2, elements should be ["1", "3"].</p>
     */
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

    /**
     * Test method to verify that remove(Object) returns false for non-existing element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(Object) method returns false when attempting to
     * remove a non-existing element, leaving the VectorAdapter unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of non-existing elements during
     * removal operations, ensuring vector integrity is maintained.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], attempts to remove
     * non-existing element "4", and verifies the return value and unchanged vector state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector remains unchanged: ["1", "2", "3"]</p>
     * <p>- Size remains 3</p>
     * <p>- All elements remain in original positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove("4") should return false, size should remain 3, all elements should remain unchanged.</p>
     */
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

    /**
     * Test method to verify that remove(Object) correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(Object) method correctly identifies and removes
     * null elements from the VectorAdapter, returning true and maintaining proper element ordering.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null values during removal
     * operations, ensuring null elements can be successfully removed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", null, "3"], removes the null element,
     * and verifies the return value, size reduction, and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Null element is removed from the vector</p>
     * <p>- Size is reduced to 2</p>
     * <p>- Elements are properly shifted: ["1", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(null) should return true, size should be 2, elements should be ["1", "3"].</p>
     */
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

    /**
     * Test method to verify that remove(Object) returns false on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(Object) method returns false when attempting to
     * remove any element from an empty VectorAdapter, leaving the empty state unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of removal operations on empty
     * collections, ensuring vector integrity is maintained.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, attempts to remove element "1",
     * and verifies the return value and unchanged empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector remains empty</p>
     * <p>- Size remains 0</p>
     * <p>- isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove("1") should return false, size should remain 0, isEmpty() should return true.</p>
     */
    @Test
    public void testRemoveFromEmptyVector()
    {
        vector = new VectorAdapter();

        assertFalse("Should return false for empty vector", vector.remove("1"));
        assertEquals("Size should remain 0", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    /**
     * Test method to verify that remove(Object) only removes first occurrence of element.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(Object) method only removes the first occurrence
     * of a duplicate element, leaving subsequent occurrences unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of duplicate elements during removal
     * operations, ensuring only the first occurrence is removed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "1", "3"], removes the first
     * occurrence of "1", and verifies the return value, size reduction, and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "1", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First occurrence of "1" is removed</p>
     * <p>- Size is reduced to 3</p>
     * <p>- Elements are properly shifted: ["2", "1", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove("1") should return true, size should be 3, elements should be ["2", "1", "3"].</p>
     */
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

    /**
     * Test method to verify that add(int, Object) inserts element at valid middle position.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add(int, Object) method correctly inserts an element
     * at a specified middle index, shifting subsequent elements to the right.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at middle positions, ensuring
     * proper element shifting and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "3"], inserts element "2" at index 1,
     * and verifies the size increase and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "2" is inserted at index 1</p>
     * <p>- Size is increased to 3</p>
     * <p>- Elements are properly ordered: ["1", "2", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After insertion at index 1, size should be 3, elements should be ["1", "2", "3"].</p>
     */
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

    /**
     * Test method to verify that add(int, Object) inserts element at beginning.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add(int, Object) method correctly inserts an element
     * at the beginning (index 0), shifting all existing elements to the right.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at the beginning of the vector,
     * ensuring proper element shifting and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["2", "3"], inserts element "1" at index 0,
     * and verifies the size increase and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "1" is inserted at index 0</p>
     * <p>- Size is increased to 3</p>
     * <p>- Elements are properly ordered: ["1", "2", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After insertion at index 0, size should be 3, elements should be ["1", "2", "3"].</p>
     */
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

    /**
     * Test method to verify that add(int, Object) inserts element at end.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add(int, Object) method correctly inserts an element
     * at the end position (index equal to size), effectively appending the element.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion at the end of the vector,
     * ensuring no unnecessary element shifting occurs.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2"], inserts element "3" at index 2,
     * and verifies the size increase and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "3" is inserted at index 2</p>
     * <p>- Size is increased to 3</p>
     * <p>- Elements are properly ordered: ["1", "2", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After insertion at index 2, size should be 3, elements should be ["1", "2", "3"].</p>
     */
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

    /**
     * Test method to verify that add(int, Object) inserts element into empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add(int, Object) method correctly inserts an element
     * into an empty VectorAdapter at index 0, initializing the vector properly.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element insertion into empty vectors,
     * ensuring proper initialization and state management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, inserts element "1" at index 0,
     * and verifies the size increase and correct element placement.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "1" is inserted at index 0</p>
     * <p>- Size is increased to 1</p>
     * <p>- Vector contains single element "1"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After insertion at index 0, size should be 1, element at index 0 should be "1".</p>
     */
    @Test
    public void testAddAtIndexEmptyVector()
    {
        vector = new VectorAdapter();

        vector.add(0, "1");

        assertEquals("Size should be 1 after insertion", 1, vector.size());
        assertEquals("Inserted element should be at index 0", "1", vector.get(0));
    }

    /**
     * Test method to verify that add(int, Object) throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add(int, Object) method throws ArrayIndexOutOfBoundsException
     * when attempting to insert an element at a negative index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for invalid negative indices
     * during insertion operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2"], attempts to insert element "3"
     * at index -1, expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add(-1, "3") should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.add(-1, "3");
    }

    /**
     * Test method to verify that add(int, Object) throws ArrayIndexOutOfBoundsException for index greater than size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add(int, Object) method throws ArrayIndexOutOfBoundsException
     * when attempting to insert an element at an index greater than the current size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for indices beyond the
     * current size during insertion operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2"], attempts to insert element "3"
     * at index 3 (where size is 2), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     * <p>- Vector size is 2</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>add(3, "3") should throw ArrayIndexOutOfBoundsException when vector size is 2.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddAtIndexGreaterThanSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.add(3, "3");
    }

    /**
     * Test method to verify that add(int, Object) correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the add(int, Object) method correctly inserts null elements
     * at specified positions, shifting subsequent elements appropriately.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null values during insertion
     * operations, ensuring null elements can be successfully inserted.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "3"], inserts a null element at index 1,
     * and verifies the size increase and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Null element is inserted at index 1</p>
     * <p>- Size is increased to 3</p>
     * <p>- Elements are properly ordered: ["1", null, "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After insertion at index 1, size should be 3, element at index 1 should be null.</p>
     */
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

    /**
     * Test method to verify that remove(int) returns correct element and removes from valid position.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(int) method correctly removes an element at a specified
     * middle index, returns the removed element, and shifts subsequent elements to the left.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal from middle positions, ensuring
     * proper return values, size adjustment, and element shifting.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], removes element at index 1,
     * and verifies the return value, size reduction, and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Element "2" is removed from index 1</p>
     * <p>- Size is reduced to 2</p>
     * <p>- Elements are properly shifted: ["1", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(1) should return "2", size should be 2, elements should be ["1", "3"].</p>
     */
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

    /**
     * Test method to verify that remove(int) throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(int) method throws ArrayIndexOutOfBoundsException
     * when attempting to remove an element at a negative index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for invalid negative indices
     * during removal operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2"], attempts to remove element
     * at index -1, expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(-1) should throw ArrayIndexOutOfBoundsException.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveAtIndexNegativeIndex()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.remove(-1);
    }

    /**
     * Test method to verify that remove(int) throws ArrayIndexOutOfBoundsException for index equal to size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(int) method throws ArrayIndexOutOfBoundsException
     * when attempting to remove an element at an index equal to the current size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for indices at the
     * upper boundary during removal operations.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], attempts to remove element
     * at index 3 (where size is 3), expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     * <p>- Vector size is 3</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Vector remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(3) should throw ArrayIndexOutOfBoundsException when vector size is 3.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveAtIndexEqualToSize()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        vector.remove(3);
    }

    /**
     * Test method to verify that remove(int) throws ArrayIndexOutOfBoundsException on empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(int) method throws ArrayIndexOutOfBoundsException
     * when attempting to remove any element from an empty VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for removal operations
     * on empty collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter, attempts to remove element at index 0,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized and empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     * <p>- Vector remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(0) should throw ArrayIndexOutOfBoundsException on empty vector.</p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveAtIndexOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.remove(0);
    }

    /**
     * Test method to verify that remove(int) correctly removes element from beginning.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(int) method correctly removes the first element
     * (index 0), returns the removed element, and shifts all subsequent elements to the left.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal from the beginning of the vector,
     * ensuring proper element shifting and size management.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], removes the first element,
     * and verifies the return value, size reduction, and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- First element "1" is removed</p>
     * <p>- Size is reduced to 2</p>
     * <p>- Elements are properly shifted: ["2", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(0) should return "1", size should be 2, elements should be ["2", "3"].</p>
     */
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

    /**
     * Test method to verify that remove(int) correctly removes element from end.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(int) method correctly removes the last element,
     * returns the removed element, and maintains other elements unchanged.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate element removal from the end of the vector,
     * ensuring no unnecessary element shifting occurs.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3"], removes the last element,
     * and verifies the return value, size reduction, and unchanged remaining elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Last element "3" is removed</p>
     * <p>- Size is reduced to 2</p>
     * <p>- Remaining elements unchanged: ["1", "2"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(2) should return "3", size should be 2, elements should be ["1", "2"].</p>
     */
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

    /**
     * Test method to verify that remove(int) correctly handles null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(int) method correctly removes null elements
     * from specified positions, returns null, and shifts subsequent elements to the left.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper handling of null values during removal
     * operations, ensuring null elements can be successfully removed.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", null, "3"], removes the null element at index 1,
     * and verifies the return value, size reduction, and correct element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", null, "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Null element is removed from index 1</p>
     * <p>- Size is reduced to 2</p>
     * <p>- Elements are properly shifted: ["1", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>remove(1) should return null, size should be 2, elements should be ["1", "3"].</p>
     */
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

    /**
     * Test method to verify that remove(int) works correctly with multiple consecutive removals.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the remove(int) method correctly handles multiple consecutive
     * removal operations, maintaining proper element shifting and size management.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the behavior of multiple sequential removal operations,
     * ensuring vector consistency through complex modification scenarios.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with elements ["1", "2", "3", "4", "5"], performs three
     * consecutive removals at index 1, and verifies the final size and element ordering.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized with elements ["1", "2", "3", "4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Elements at indices 1, 2, 3 are sequentially removed</p>
     * <p>- Size is reduced to 2</p>
     * <p>- Elements are properly shifted: ["1", "5"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>After three consecutive removals at index 1, size should be 2, elements should be ["1", "5"].</p>
     */
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

    /**
     * Test method to verify that clear() works correctly on an empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that calling clear() on an empty vector maintains size zero and empty state.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case of clearing an already empty vector,
     * ensuring the method handles this scenario correctly without errors.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and calls clear(). It then verifies that
     * the size remains 0 and the isEmpty() method returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter is initialized as empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector size remains 0</p>
     * <p>- Vector isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clear() should maintain empty state without errors</p>
     */
    @Test
    public void testClearOnEmptyVector()
    {
        vector = new VectorAdapter();
        vector.clear();

        assertEquals("Size should be 0 after clearing empty vector", 0, vector.size());
        assertTrue("Vector should remain empty", vector.isEmpty());
    }

    /**
     * Test method to verify that clear() removes all elements from a vector with multiple elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clear() correctly removes all elements from a non-empty vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the primary functionality of clear() when
     * the vector contains multiple elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three elements, calls clear(), and verifies
     * that the size becomes 0 and isEmpty() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains 3 elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector size becomes 0</p>
     * <p>- Vector isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clear() should remove all elements and set size to 0</p>
     */
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

    /**
     * Test method to verify that clear() works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that clear() correctly handles vectors containing null elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate clear() functionality when the vector contains
     * multiple null values, ensuring proper handling of null elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three null elements, calls clear(), and verifies
     * that the size becomes 0 and isEmpty() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains 3 null elements</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Vector size becomes 0</p>
     * <p>- Vector isEmpty() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>clear() should remove all elements including null values</p>
     */
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

    /**
     * Test method to verify containsAll() returns true for empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() returns true when checking against an empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where the specified collection is empty,
     * which should always return true according to the collection contract.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a non-empty VectorAdapter and an empty VectorAdapter, then verifies
     * that containsAll() returns true when checking against the empty collection.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true for empty collection</p>
     */
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

    /**
     * Test method to verify containsAll() returns true when all elements are present.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly identifies when all elements
     * of the specified collection are present.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the normal case where the vector contains
     * all elements of the specified collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters where the main vector contains all elements
     * of the test vector, then verifies that containsAll() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when all elements are present</p>
     */
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

    /**
     * Test method to verify containsAll() returns false when some elements are missing.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly identifies when some elements
     * of the specified collection are missing.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the case where the vector does not contain
     * all elements of the specified collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters where the main vector is missing some elements
     * of the test vector, then verifies that containsAll() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter contains elements: ["1", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return false when some elements are missing</p>
     */
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

    /**
     * Test method to verify containsAll() returns false when all elements are missing.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly identifies when none of the
     * elements of the specified collection are present.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where the vector contains
     * none of the elements from the specified collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with completely different elements,
     * then verifies that containsAll() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter contains elements: ["4", "5", "6"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return false when all elements are missing</p>
     */
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

    /**
     * Test method to verify containsAll() works correctly with different element order.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() is order-independent and returns true
     * when all elements are present regardless of order.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that containsAll() focuses on element presence
     * rather than element order.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with the same elements but in different order,
     * then verifies that containsAll() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter contains elements: ["2", "3", "1"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true regardless of element order</p>
     */
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

    /**
     * Test method to verify containsAll() works correctly with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly handles duplicate elements
     * in the main vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that containsAll() works correctly when
     * the main vector contains duplicate elements that satisfy the collection requirements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a main VectorAdapter with duplicate elements and a test
     * VectorAdapter with unique elements, then verifies that containsAll() returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "1", "2"]</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when duplicates satisfy element requirements</p>
     */
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

    /**
     * Test method to verify containsAll() works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() correctly handles null elements
     * in both collections.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that containsAll() can properly check
     * for the presence of null elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates VectorAdapters containing null elements and verifies
     * that containsAll() correctly identifies their presence.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", null, "3"]</p>
     * <p>- Test VectorAdapter contains elements: [null, "1"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when null elements are present</p>
     */
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

    /**
     * Test method to verify containsAll() returns false for empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() returns false when called on an
     * empty vector with a non-empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where the main vector
     * is empty but the specified collection is not.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty main VectorAdapter and a non-empty test
     * VectorAdapter, then verifies that containsAll() returns false.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter is empty</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns false</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return false for empty vector with non-empty collection</p>
     */
    @Test
    public void testContainsAllEmptyVector()
    {
        vector = new VectorAdapter();

        VectorAdapter vec = new VectorAdapter();
        vec.add("1");
        vec.add("2");

        assertFalse("Should return false for empty vector", vector.containsAll(vec));
    }

    /**
     * Test method to verify containsAll() throws NullPointerException for null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() throws NullPointerException when
     * passed a null collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when null
     * is passed as the collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls containsAll() with null,
     * expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should throw NullPointerException for null collection</p>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.containsAll(null);
    }

    /**
     * Test method to verify containsAll() returns true when checking against itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that containsAll() returns true when a vector
     * is checked against itself.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the reflexive property of containsAll()
     * when the collection is the same instance.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls containsAll() with itself as
     * parameter, then verifies it returns true.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- containsAll() returns true</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>containsAll() should return true when collection is itself</p>
     */
    @Test
    public void testContainsAllSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertTrue("Should return true when collection is itself", vector.containsAll(vector));
    }

    /**
     * Test method to verify addAll() works correctly with non-empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly appends all elements from
     * a non-empty collection and returns true.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the normal case of adding multiple
     * elements from another collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters and adds all elements from one to
     * the other, then verifies the return value and the resulting elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2"]</p>
     * <p>- Test VectorAdapter contains elements: ["3", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Main VectorAdapter contains 4 elements in correct order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and append all elements</p>
     */
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

    /**
     * Test method to verify addAll() returns false for empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() returns false when attempting to
     * add an empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where the specified
     * collection is empty.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and attempts to add an empty collection,
     * then verifies the return value and unchanged size.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2"]</p>
     * <p>- Test VectorAdapter is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns false</p>
     * <p>- Main VectorAdapter size remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return false for empty collection</p>
     */
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

    /**
     * Test method to verify addAll() works correctly when adding to empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly populates an empty vector
     * with elements from another collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the case where the target vector
     * is initially empty.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and adds all elements from
     * a non-empty collection, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter is empty</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Main VectorAdapter contains all added elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and populate empty vector</p>
     */
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

    /**
     * Test method to verify addAll() works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly handles collections
     * containing null elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that addAll() can properly add
     * null elements to a vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and adds a collection containing
     * null elements, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains element: ["1"]</p>
     * <p>- Test VectorAdapter contains elements: [null, null]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Main VectorAdapter contains null elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and add null elements correctly</p>
     */
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

    /**
     * Test method to verify addAll() throws NullPointerException for null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() throws NullPointerException when
     * passed a null collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when null
     * is passed as the collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls addAll() with null,
     * expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw NullPointerException for null collection</p>
     */
    @Test(expected = NullPointerException.class)
    public void testAddAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.addAll(null);
    }

    /**
     * Test method to verify addAll() works correctly when adding collection to itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll() correctly handles the case where
     * a vector is added to itself.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the special case of adding a
     * collection to itself, which should duplicate all elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and adds itself to itself, then
     * verifies the result contains duplicated elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- VectorAdapter contains duplicated elements: ["1", "2", "1", "2"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and duplicate elements when adding to itself</p>
     */
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

    /**
     * Test method to verify removeAll() works correctly when all elements are present.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns true and removes all elements
     * when the specified collection contains all elements of the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the case where every element in the vector
     * is also present in the specified collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with identical elements and calls removeAll(),
     * then verifies the return value and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Main VectorAdapter becomes empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and remove all elements</p>
     */
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

    /**
     * Test method to verify removeAll() works correctly when some elements are present.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns true and removes only the
     * elements present in the specified collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the case where only some elements in the vector
     * are present in the specified collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with overlapping elements and calls removeAll(),
     * then verifies the return value and remaining elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3", "4"]</p>
     * <p>- Test VectorAdapter contains elements: ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Main VectorAdapter retains elements: ["1", "4"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and remove only matching elements</p>
     */
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

    /**
     * Test method to verify removeAll() returns false for empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns false when the specified
     * collection is empty.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where no elements are
     * specified for removal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls removeAll() with an empty collection,
     * then verifies the return value and unchanged state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns false</p>
     * <p>- Main VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return false for empty collection</p>
     */
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

    /**
     * Test method to verify removeAll() returns false when no elements are present.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns false when the specified
     * collection contains no elements present in the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the case where there are no common
     * elements between the vector and the specified collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with completely different elements
     * and calls removeAll(), then verifies the return value and unchanged state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter contains elements: ["4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns false</p>
     * <p>- Main VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return false when no elements match</p>
     */
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

    /**
     * Test method to verify removeAll() works correctly with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() removes all occurrences of duplicate
     * elements present in the specified collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that removeAll() removes all instances
     * of elements, including duplicates.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements and calls removeAll()
     * with a collection containing the duplicate element, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "2", "3"]</p>
     * <p>- Test VectorAdapter contains element: ["2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Main VectorAdapter retains elements: ["1", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should remove all occurrences of duplicate elements</p>
     */
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

    /**
     * Test method to verify removeAll() works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() correctly removes null elements
     * when specified in the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that removeAll() can properly handle
     * and remove null elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing null elements and calls removeAll()
     * with a collection containing null, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", null, "3", null]</p>
     * <p>- Test VectorAdapter contains element: [null]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- Main VectorAdapter retains elements: ["1", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should remove all null elements when specified</p>
     */
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

    /**
     * Test method to verify removeAll() returns false for empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns false when called on an
     * empty vector with a non-empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where the vector is empty
     * but the specified collection is not.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and calls removeAll() with a
     * non-empty collection, then verifies the return value and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter is empty</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns false</p>
     * <p>- Main VectorAdapter remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return false for empty vector</p>
     */
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

    /**
     * Test method to verify removeAll() throws NullPointerException for null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() throws NullPointerException when
     * passed a null collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when null
     * is passed as the collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls removeAll() with null,
     * expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should throw NullPointerException for null collection</p>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.removeAll(null);
    }

    /**
     * Test method to verify removeAll() works correctly when removing itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that removeAll() returns true and empties the vector
     * when called with itself as the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the special case where a vector removes
     * all its own elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls removeAll() with itself,
     * then verifies the return value and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- removeAll() returns true</p>
     * <p>- VectorAdapter becomes empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>removeAll() should return true and empty the vector when removing itself</p>
     */
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

    /**
     * Test method to verify retainAll() works correctly with empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() returns true and empties the vector
     * when called with an empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where no elements are
     * to be retained.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls retainAll() with an empty collection,
     * then verifies the return value and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns true</p>
     * <p>- Main VectorAdapter becomes empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return true and empty the vector for empty collection</p>
     */
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

    /**
     * Test method to verify retainAll() works correctly with no elements in common.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() returns true and empties the vector
     * when there are no common elements with the specified collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the case where the vector and collection
     * have no elements in common.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with completely different elements
     * and calls retainAll(), then verifies the return value and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter contains elements: ["4", "5"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns true</p>
     * <p>- Main VectorAdapter becomes empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return true and empty the vector when no elements match</p>
     */
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

    /**
     * Test method to verify retainAll() returns false when all elements are in common.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() returns false when the specified
     * collection contains all elements of the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the case where all elements are to be retained.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with identical elements and calls retainAll(),
     * then verifies the return value and unchanged state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3"]</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns false</p>
     * <p>- Main VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return false when all elements are retained</p>
     */
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

    /**
     * Test method to verify retainAll() works correctly with some elements in common.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() returns true and keeps only the common
     * elements when some elements are in common.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the normal case where only some elements
     * are to be retained.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters with overlapping elements and calls retainAll(),
     * then verifies the return value and remaining elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3", "4"]</p>
     * <p>- Test VectorAdapter contains elements: ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns true</p>
     * <p>- Main VectorAdapter retains elements: ["2", "3"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return true and keep only common elements</p>
     */
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

    /**
     * Test method to verify retainAll() works correctly with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() keeps all occurrences of duplicate
     * elements when specified in the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that retainAll() preserves duplicate
     * elements when they are in the specified collection.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with duplicate elements and calls retainAll()
     * with a collection containing the duplicate element, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "2", "3"]</p>
     * <p>- Test VectorAdapter contains element: ["2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns true</p>
     * <p>- Main VectorAdapter retains elements: ["2", "2"]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should preserve all occurrences of duplicate elements</p>
     */
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

    /**
     * Test method to verify retainAll() works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() correctly retains null elements
     * when specified in the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that retainAll() can properly handle
     * and retain null elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter containing mixed elements and calls retainAll()
     * with a collection containing null, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", null, "3", null]</p>
     * <p>- Test VectorAdapter contains element: [null]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns true</p>
     * <p>- Main VectorAdapter retains elements: [null, null]</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should retain all null elements when specified</p>
     */
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

    /**
     * Test method to verify retainAll() returns false for empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() returns false when called on an
     * empty vector with a non-empty collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where the vector is empty
     * but the specified collection is not.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and calls retainAll() with a
     * non-empty collection, then verifies the return value and empty state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter is empty</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns false</p>
     * <p>- Main VectorAdapter remains empty</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return false for empty vector</p>
     */
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

    /**
     * Test method to verify retainAll() throws NullPointerException for null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() throws NullPointerException when
     * passed a null collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when null
     * is passed as the collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls retainAll() with null,
     * expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should throw NullPointerException for null collection</p>
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.retainAll(null);
    }

    /**
     * Test method to verify retainAll() returns false when retaining itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that retainAll() returns false when called with
     * itself as the collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the special case where a vector retains
     * all its own elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls retainAll() with itself,
     * then verifies the return value and unchanged state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- retainAll() returns false</p>
     * <p>- VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>retainAll() should return false when retaining itself</p>
     */
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

    /**
     * Test method to verify addAll(index, collection) works correctly with non-empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) returns true and inserts
     * all elements at the specified position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the normal case of inserting multiple
     * elements at a specific index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters and inserts all elements from one into
     * the other at a specific index, then verifies the return value and element order.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "4"]</p>
     * <p>- Test VectorAdapter contains elements: ["2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3", "4"] in order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return true and insert elements at correct position</p>
     */
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


    /**
     * Test method to verify addAll(index, collection) returns false for empty collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) returns false when
     * the specified collection is empty.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the edge case where no elements are
     * inserted at the specified index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls addAll(index, collection) with
     * an empty collection, then verifies the return value and unchanged state.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2"]</p>
     * <p>- Test VectorAdapter is empty</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns false</p>
     * <p>- Main VectorAdapter remains unchanged</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should return false for empty collection</p>
     */
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

    /**
     * Test method to verify addAll(index, collection) works correctly at the beginning.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) correctly inserts
     * elements at the beginning of the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion at index 0.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters and inserts all elements from one into
     * the beginning of the other, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["3", "4"]</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3", "4"] in order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should insert elements at the beginning correctly</p>
     */
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

    /**
     * Test method to verify addAll(index, collection) works correctly at the end.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) correctly appends
     * elements at the end of the vector.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion at the end index.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapters and appends all elements from one to
     * the end of the other, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "2"]</p>
     * <p>- Test VectorAdapter contains elements: ["3", "4"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Main VectorAdapter contains elements: ["1", "2", "3", "4"] in order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should append elements at the end correctly</p>
     */
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

    /**
     * Test method to verify addAll(index, collection) works correctly with empty vector.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) correctly populates
     * an empty vector with elements from another collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate insertion into an empty vector.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and inserts all elements from
     * a non-empty collection at index 0, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter is empty</p>
     * <p>- Test VectorAdapter contains elements: ["1", "2", "3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Main VectorAdapter contains all added elements</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should populate empty vector correctly</p>
     */
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

    /**
     * Test method to verify addAll(index, collection) throws ArrayIndexOutOfBoundsException for negative index.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) throws ArrayIndexOutOfBoundsException
     * when called with a negative index.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for invalid
     * negative index values.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls addAll() with a negative index,
     * expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     * <p>- Test VectorAdapter contains element: ["3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw ArrayIndexOutOfBoundsException for negative index</p>
     */
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

    /**
     * Test method to verify addAll(index, collection) throws ArrayIndexOutOfBoundsException for index greater than size.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) throws ArrayIndexOutOfBoundsException
     * when called with an index greater than the current size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling for index values
     * beyond the current vector size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls addAll() with an index greater
     * than the size, expecting an ArrayIndexOutOfBoundsException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     * <p>- Test VectorAdapter contains element: ["3"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- ArrayIndexOutOfBoundsException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw ArrayIndexOutOfBoundsException for index bigger than size</p>
     */
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

    /**
     * Test method to verify addAll(index, collection) throws NullPointerException for null collection.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) throws NullPointerException
     * when passed a null collection.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate proper exception handling when null
     * is passed as the collection parameter.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and calls addAll(index, collection) with null,
     * expecting a NullPointerException to be thrown.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- NullPointerException is thrown</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should throw NullPointerException for null collection</p>
     */
    @Test(expected = NullPointerException.class)
    public void testAddAllAtIndexNullCollection()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");

        vector.addAll(1, null);
    }

    /**
     * Test method to verify addAll(index, collection) works correctly with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) correctly inserts
     * null elements at the specified position.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that addAll() can properly handle
     * and insert null elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and inserts a collection containing
     * null elements at a specific index, then verifies the result.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Main VectorAdapter contains elements: ["1", "3"]</p>
     * <p>- Test VectorAdapter contains elements: [null, null]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- Main VectorAdapter contains null elements at correct positions</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should insert null elements correctly</p>
     */
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

    /**
     * Test method to verify addAll(index, collection) works correctly when adding to itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that addAll(index, collection) correctly handles
     * the case where a vector is inserted into itself.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the special case of inserting a vector
     * into itself, which should duplicate all elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter and inserts itself at a specific index,
     * then verifies the result contains duplicated elements.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter contains elements: ["1", "2"]</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- addAll() returns true</p>
     * <p>- VectorAdapter contains duplicated elements: ["1", "1", "2", "2"] in order</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>addAll() should duplicate elements when adding to itself</p>
         */
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

    /**
     * Test method to verify that equals() returns true when comparing a VectorAdapter to itself.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals() method correctly identifies a VectorAdapter instance as equal to itself,
     * ensuring reflexivity of the equals operation.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the reflexive property of the equals method according to the Java specification.
     * This ensures consistency with the general contract for Object.equals().</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance with three elements and then calls equals() on the same instance.
     * The method should return true to satisfy the reflexive property.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter instance is initialized with three string elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals() method returns true when comparing the vector to itself</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>equals() should return true when comparing a VectorAdapter instance to itself.</p>
     */
    @Test
    public void testEqualsSelf()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertTrue("Should return true for same object", vector.equals(vector));
    }

    /**
     * Test method to verify that equals() returns true when comparing two VectorAdapters with identical elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals() method correctly identifies two VectorAdapter instances with identical
     * elements in the same order as equal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the equals method's ability to correctly compare two separate collections
     * with the same content, ensuring proper value-based equality.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two separate VectorAdapter instances, both containing the same three string elements
     * in the same order. The equals() method is called to compare them.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Two VectorAdapter instances are initialized with identical elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals() method returns true when comparing the two vectors</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>equals() should return true when comparing two VectorAdapters with identical elements in the same order.</p>
     */
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

    /**
     * Test method to verify that equals() returns false when comparing VectorAdapters of different sizes.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals() method correctly identifies VectorAdapter instances with different
     * sizes as not equal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the equals method properly handles the case where collections
     * have different sizes, which should immediately indicate inequality.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapter instances - one with three elements and another with two elements.
     * The equals() method is called to compare them.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- First VectorAdapter instance has three elements: "1", "2", "3"</p>
     * <p>- Second VectorAdapter instance has two elements: "1", "2"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals() method returns false when comparing the two vectors</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>equals() should return false when comparing VectorAdapters of different sizes.</p>
     */
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

    /**
     * Test method to verify that equals() returns false when comparing VectorAdapters with different elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals() method correctly identifies VectorAdapter instances with different
     * elements as not equal, even when they have the same size.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the equals method properly compares element values and detects
     * differences in content between collections of the same size.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapter instances of the same size but with different elements at the same
     * positions. The equals() method is called to compare them.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Both VectorAdapter instances have three elements</p>
     * <p>- First vector contains: "1", "2", "3"</p>
     * <p>- Second vector contains: "1", "4", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals() method returns false when comparing the two vectors</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>equals() should return false when comparing VectorAdapters with different elements.</p>
     */
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

    /**
     * Test method to verify that equals() returns false when comparing VectorAdapters with elements in different order.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals() method correctly identifies VectorAdapter instances with the same
     * elements but in different order as not equal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the equals method considers element order when comparing lists,
     * as required by the List interface contract.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapter instances with the same elements but in different order.
     * The equals() method is called to compare them.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Both VectorAdapter instances have the same three elements: "1", "2", "3"</p>
     * <p>- First vector has order: "1", "2", "3"</p>
     * <p>- Second vector has order: "1", "3", "2"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals() method returns false when comparing the two vectors</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>equals() should return false when comparing VectorAdapters with elements in different order.</p>
     */
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

    /**
     * Test method to verify that equals() returns true when comparing VectorAdapters with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals() method correctly handles null elements when comparing
     * VectorAdapter instances.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the equals method properly compares collections containing
     * null values, ensuring correct null-safety in equality comparisons.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapter instances with identical elements including null values at
     * the same positions. The equals() method is called to compare them.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Both VectorAdapter instances have three elements: "1", null, "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals() method returns true when comparing the two vectors</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>equals() should return true when comparing VectorAdapters with identical elements including null values.</p>
     */
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

    /**
     * Test method to verify that equals() returns true when comparing empty VectorAdapters.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals() method correctly identifies two empty VectorAdapter
     * instances as equal.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate the equals method's handling of empty collections,
     * ensuring that two empty lists are considered equal.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two empty VectorAdapter instances and calls equals() to compare them.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Both VectorAdapter instances are empty (no elements)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals() method returns true when comparing the two vectors</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>equals() should return true when comparing two empty VectorAdapters.</p>
     */
    @Test
    public void testEqualsEmptyLists()
    {
        vector = new VectorAdapter();
        VectorAdapter vec = new VectorAdapter();

        assertTrue("Should return true for empty lists", vector.equals(vec));
    }

    /**
     * Test method to verify that equals() returns false when comparing a VectorAdapter to a non-list object.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the equals() method correctly returns false when comparing a VectorAdapter
     * to an object of a different type.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the equals method properly handles type incompatibility
     * and returns false when comparing to objects of different types.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter instance and compares it to a String object using equals().</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter instance is initialized with three elements: "1", "2", "3"</p>
     * <p>- Comparison object is a String with value "1"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- equals() method returns false when comparing the vector to a String</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>equals() should return false when comparing a VectorAdapter to a non-list object.</p>
     */
    @Test
    public void testEqualsNonListObject()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertFalse("Should return false for non-list object", vector.equals("1"));
    }

    /**
     * Test method to verify the hashCode() of an empty VectorAdapter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the hashCode() method returns consistent values for empty VectorAdapter instances.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the hashCode method produces consistent results for empty
     * collections, which is important for proper functioning in hash-based collections.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two empty VectorAdapter instances and verifies that their hash codes are equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Two empty VectorAdapter instances are created</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Both VectorAdapter instances return the same hashCode value</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hashCode() should return the same value for all empty VectorAdapters.</p>
     */
    @Test
    public void testHashCodeOfEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("Hash code of empty vector should match empty vector", new VectorAdapter().hashCode(), vector.hashCode());
    }

    /**
     * Test method to verify the hashCode() of VectorAdapters with multiple elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the hashCode() method returns equal values for VectorAdapter instances
     * with identical elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the hashCode method produces equal values for collections
     * with equal content, which is required by the general contract of hashCode().</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapter instances with identical elements and verifies that their
     * hash codes are equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Two VectorAdapter instances are created with identical elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Both VectorAdapter instances return the same hashCode value</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hashCode() should return equal values for VectorAdapters with identical elements.</p>
     */
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

    /**
     * Test method to verify the hashCode() of VectorAdapters with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the hashCode() method returns consistent values for VectorAdapter instances
     * containing null elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the hashCode method properly handles null values and produces
     * consistent results for collections with identical null elements.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates two VectorAdapter instances with identical null elements and verifies that their
     * hash codes are equal.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- Two VectorAdapter instances are created with identical null elements: null, null, null</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- Both VectorAdapter instances return the same hashCode value</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>hashCode() should return equal values for VectorAdapters with identical null elements.</p>
     */
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

    /**
     * Test method to verify the toString() of an empty VectorAdapter.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the toString() method returns the correct string representation
     * for an empty VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method produces the standard empty
     * collection representation, which is important for debugging and logging purposes.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates an empty VectorAdapter and verifies that its toString() method returns
     * the expected string representation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter instance is empty (no elements)</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toString() returns "[]"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toString() should return "[]" for an empty VectorAdapter.</p>
     */
    @Test
    public void testToStringEmptyVector()
    {
        vector = new VectorAdapter();

        assertEquals("String representation of empty vector should match", "[]", vector.toString());
    }

    /**
     * Test method to verify the toString() of a VectorAdapter with multiple elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the toString() method returns the correct string representation
     * for a VectorAdapter with multiple elements.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method properly formats collections
     * with multiple elements, including proper comma separation and bracket placement.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three string elements and verifies that its toString()
     * method returns the expected string representation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter instance contains three elements: "1", "2", "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toString() returns "[1, 2, 3]"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toString() should return "[1, 2, 3]" for a VectorAdapter with elements "1", "2", "3".</p>
     */
    @Test
    public void testToStringMultipleElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        assertEquals("String representation of multiple elements should match", "[1, 2, 3]", vector.toString());
    }

    /**
     * Test method to verify the toString() of a VectorAdapter with null elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the toString() method correctly represents null elements
     * in a VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method properly handles null values
     * within collections, representing them as "null" in the output string.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with a null element between two string elements and
     * verifies that its toString() method returns the expected representation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter instance contains three elements: "1", null, "3"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toString() returns "[1, null, 3]"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toString() should return "[1, null, 3]" for a VectorAdapter with elements "1", null, "3".</p>
     */
    @Test
    public void testToStringNullElements()
    {
        vector = new VectorAdapter();
        vector.add("1");
        vector.add(null);
        vector.add("3");

        assertEquals("String representation with null elements should match", "[1, null, 3]", vector.toString());
    }

    /**
     * Test method to verify the toString() of a VectorAdapter with duplicate elements.
     *
     * <p><b>Summary:</b></p>
     * <p>This test verifies that the toString() method correctly represents duplicate elements
     * in a VectorAdapter.</p>
     *
     * <p><b>Test Case Design:</b></p>
     * <p>The test is designed to validate that the toString method properly handles duplicate values
     * within collections, representing each occurrence in the output string.</p>
     *
     * <p><b>Test Description:</b></p>
     * <p>The test creates a VectorAdapter with three identical string elements and verifies that
     * its toString() method returns the expected representation.</p>
     *
     * <p><b>Pre-Condition:</b></p>
     * <p>- VectorAdapter instance contains three identical elements: "1", "1", "1"</p>
     *
     * <p><b>Post-Condition:</b></p>
     * <p>- toString() returns "[1, 1, 1]"</p>
     *
     * <p><b>Expected Results:</b></p>
     * <p>toString() should return "[1, 1, 1]" for a VectorAdapter with three "1" elements.</p>
     */
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
