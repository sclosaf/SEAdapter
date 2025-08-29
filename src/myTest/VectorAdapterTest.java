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
}
