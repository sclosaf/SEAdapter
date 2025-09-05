package myAdapter;

import java.util.NoSuchElementException;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;

/**
 * An adapter that implements the HEnumeration interface to iterate over elements
 * of a VectorAdapter or a specific restricted portion of it by the use
 * of starting and ending indexes.
 *
 * @author      Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see         HEnumeration
 * @see         VectorAdapter
 */
public class EnumerationAdapter implements HEnumeration
{
    /**
     * The current position of this enumeration cursor. The next element to be returned
     * by nextElement() is at this index. This cursor moves only forward.
     */
    private int cursor = 0;

    /**
     * The exclusive upper bound of the enumeration range.
     */
    private final int end;

    /**
     * The VectorAdapter being enumerated. This vector provides access to the
     * underlying elements that this enumeration iterates over.
     */
    private VectorAdapter vect;

    /**
     * Constructs an EnumerationAdapter that iterates over all the elements
     * of the specified VectorAdapter.
     *
     * @param vect  the VectorAdapter to enumerate over.
     * @throws NullPointerException if the specified VectorAdapter is null.
     */
    EnumerationAdapter(VectorAdapter vect)
    {
        this(vect, vect.offset(), vect.offset() + vect.size());
    }

    /**
     * Constructs an EnumerationAdapter that iterates over a specified range
     * of elements in the specified VectorAdapter.
     *
     * @param vect   the VectorAdapter to enumerate over.
     * @param start  the starting index (inclusive) of the range to enumerate.
     * @param end    the ending index (exclusive) of the range to enumerate.
     * @throws NullPointerException if the specified VectorAdapter is null.
     * @throws IllegalArgumentException if the start index is greater than the end index.
     * @throws IndexOutOfBoundsException if the start or end index is out of the vector's bounds.
     */
    EnumerationAdapter(VectorAdapter vect, int start, int end)
    {
        if(vect == null)
            throw new NullPointerException();

        if(start > end)
            throw new IllegalArgumentException();

        if(start < vect.offset() || end > vect.offset() + vect.size())
            throw new IndexOutOfBoundsException();

        this.vect = vect;
        this.cursor = start;
        this.end = end;
    }

    /**
     * Tests if this enumeration contains more elements.
     *
     * @return  <code>true</code> if and only if this enumeration object
     *           contains at least one more element to provide;
     *          <code>false</code> otherwise.
     */
    public boolean hasMoreElements()
    {
        return cursor < end;
    }

    /**
     * Returns the next element of this enumeration if this enumeration
     * object has at least one more element to provide.
     *
     * @return     the next element of this enumeration.
     * @exception  NoSuchElementException  if no more elements exist.
     */
    public synchronized Object nextElement()
    {
        if(!hasMoreElements())
            throw new NoSuchElementException();

        Object obj = vect.elementAt(cursor - vect.offset());
        cursor++;

        return obj;
    }
}
