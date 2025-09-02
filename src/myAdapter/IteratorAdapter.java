package myAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;

/**
 * An adapter that implements the HIterator interface to iterate over elements
 * of a VectorAdapter. Provides a way to iterate through elements of a vector
 * or a specific portion of it, with support for element removal.
 * <p>
 * This class allows iterating through all elements of a VectorAdapter or only
 * a specific subsection defined by start and end indices. Unlike HEnumeration,
 * HIterator supports removing elements during iteration.
 * </p>
 *
 * @author      Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see         HIterator
 * @see         VectorAdapter
 * @see         EnumerationAdapter
 */
public class IteratorAdapter implements HIterator
{
    /**
     * The current position of this iterator. The next element to be returned
     * by next() is at this index. This iterator only moves forward.
     */
    protected int cursor = 0;

    /**
     * The index of the last element returned by next(), or -1 if no element
     * has been returned yet or if the element has been removed. This is used
     * to track which element should be removed when remove() is called.
     */
    protected int lastRet = -1;

    /**
     * The starting index (inclusive) of the iteration range within the vector.
     * This represents the offset of the underlying VectorAdapter.
     */
    protected int start;

    /**
     * The ending index (exclusive) of the iteration range within the vector.
     * When the cursor reaches this value, hasNext() will return false.
     */
    protected int end;

    /**
     * The VectorAdapter being iterated. This adapter provides access to the
     * underlying elements that this iterator traverses.
     */
    protected VectorAdapter vect;

    /**
     * Constructs an IteratorAdapter that iterates over a specific range
     * of elements in the specified VectorAdapter.
     *
     * @param vect   the VectorAdapter to iterate over
     * @param start  the starting index (inclusive) of the range to iterate
     * @param end    the ending index (exclusive) of the range to iterate
     * @throws NullPointerException if the specified VectorAdapter is null
     * @throws IllegalArgumentException if the start index is greater than the end index
     * @throws IndexOutOfBoundsException if the start or end index is out of the vector's bounds
     */
    IteratorAdapter(VectorAdapter vect, int start, int end)
    {
        if(vect == null)
            throw new NullPointerException();

        if(start > end)
            throw new IllegalArgumentException();

        if(start < vect.offset() || end > vect.offset() + vect.size())
            throw new IndexOutOfBoundsException();

        this.vect = vect;
        this.cursor = start;
        this.start = vect.offset();
        this.end = end;
    }

    /**
     * Returns <code>true</code> if the iteration has more elements. (In other
     * words, returns <code>true</code> if <code>next</code> would return an element
     * rather than throwing an exception.)
     *
     * @return <code>true</code> if the iterator has more elements.
     */
    public boolean hasNext()
    {
        return cursor < end;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @exception NoSuchElementException iteration has no more elements.
     */
    public synchronized Object next()
    {
        if(!hasNext())
            throw new NoSuchElementException();

        lastRet = cursor;
        Object obj = vect.elementAt(cursor - vect.offset());
        cursor++;

        return obj;
    }

    /**
     *
     * Removes from the underlying collection the last element returned by the
     * iterator (optional operation).  This method can be called only once per
     * call to <code>next</code>.  The behavior of an iterator is unspecified if
     * the underlying collection is modified while the iteration is in
     * progress in any way other than by calling this method.
     *
     * @exception UnsupportedOperationException if the <code>remove</code>
     *		operation is not supported by this HIterator.
     * @exception IllegalStateException if the <code>next</code> method has not
     *		yet been called, or the <code>remove</code> method has already
     *		been called after the last call to the <code>next</code>
     *		method.
     */
    public synchronized void remove()
    {
        if(lastRet == -1)
            throw new IllegalStateException();

        vect.removeElementAt(lastRet - vect.offset());
        cursor = lastRet;
        lastRet = -1;
        end--;
    }
}
