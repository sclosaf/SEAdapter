package myAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;


/**
 * An adapter that implements the HListIterator interface to iterate over elements
 * of a VectorAdapter. Provides bidirectional iteration with support for element
 * modification, insertion, and removal.
 *
 * @author      Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see         HListIterator
 * @see         IteratorAdapter
 * @see         VectorAdapter
 */
public class ListIteratorAdapter extends IteratorAdapter implements HListIterator
{
    /**
     * Constructs a ListIteratorAdapter that iterates over a specific range
     * of elements in the specified VectorAdapter.
     *
     * @param vect   the VectorAdapter to iterate over.
     * @param start  the starting index (inclusive) of the range to iterate.
     * @param end    the ending index (exclusive) of the range to iterate.
     * @throws NullPointerException if the specified VectorAdapter is null.
     * @throws IllegalArgumentException if the start index is greater than the end index.
     * @throws IndexOutOfBoundsException if the start or end index is out of the vector's bounds.
     */
    ListIteratorAdapter(VectorAdapter vect, int start, int end)
    {
        super(vect, start, end);
    }

    /**
     * Returns <code>true</code> if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words, returns
     * <code>true</code> if <code>previous</code> would return an element rather than
     * throwing an exception.)
     *
     * @return <code>true</code> if the list iterator has more elements when
     *	     traversing the list in the reverse direction.
     */
    public boolean hasPrevious()
    {
        return cursor > start;
    }

    /**
     * Returns the previous element in the list.  This method may be called
     * repeatedly to iterate through the list backwards, or intermixed with
     * calls to <code>next</code> to go back and forth.  (Note that alternating
     * calls to <code>next</code> and <code>previous</code> will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list.
     *
     * @exception NoSuchElementException if the iteration has no previous
     *            element.
     */
    public synchronized Object previous()
    {
        if(!hasPrevious())
            throw new NoSuchElementException();

        cursor--;
        lastRet = cursor;
        Object obj = vect.elementAt(cursor - vect.offset());

        return obj;
    }

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to <code>next</code>. (Returns list size if the list iterator is at the
     * end of the list.)
     *
     * @return the index of the element that would be returned by a subsequent
     * 	     call to <code>next</code>, or list size if list iterator is at end
     *	     of list.
     */
    public synchronized int nextIndex()
    {
        return cursor - start;
    }

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to <code>previous</code>. (Returns -1 if the list iterator is at the
     * beginning of the list.)
     *
     * @return the index of the element that would be returned by a subsequent
     * 	     call to <code>previous</code>, or -1 if list iterator is at
     *	     beginning of list.
     */
    public synchronized int previousIndex()
    {
        return cursor - start - 1;
    }

    /**
     * Replaces the last element returned by <code>next</code> or
     * <code>previous</code> with the specified element (optional operation).
     * This call can be made only if neither <code>HListIterator.remove</code> nor
     * <code>HListIterator.add</code> have been called after the last call to
     * <code>next</code> or <code>previous</code>.
     *
     * @param o the element with which to replace the last element returned by
     *          <code>next</code> or <code>previous</code>.
     * @exception IllegalArgumentException if some aspect of the specified
     *		element prevents it from being added to this list.
     * @exception IllegalStateException if neither <code>next</code> nor
     *	        <code>previous</code> have been called, or <code>remove</code> or
     *		<code>add</code> have been called after the last call to
     * 		<code>next</code> or <code>previous</code>.
     */
    public synchronized void set(Object o)
    {
        if(lastRet == -1)
            throw new IllegalStateException();

        vect.setElementAt(o, lastRet - vect.offset());
    }

    /**
     * Inserts the specified element into the list (optional operation).  The
     * element is inserted immediately before the next element that would be
     * returned by <code>next</code>, if any, and after the next element that
     * would be returned by <code>previous</code>, if any.  (If the list contains
     * no elements, the new element becomes the sole element on the list.)
     * The new element is inserted before the implicit cursor: a subsequent
     * call to <code>next</code> would be unaffected, and a subsequent call to
     * <code>previous</code> would return the new element.  (This call increases
     * by one the value that would be returned by a call to <code>nextIndex</code>
     * or <code>previousIndex</code>.)
     *
     * @param o the element to insert.
     * @exception IllegalArgumentException if some aspect of this element
     *            prevents it from being added to this list.
     */
    public synchronized void add(Object o)
    {
        vect.insertElementAt(o, cursor - vect.offset());

        cursor++;
        lastRet = -1;
        end++;
    }
}
