package myAdapter;

import java.util.NoSuchElementException;

/**
 * An iterator over a collection. HIterator takes the place of HEnumeration in
 * the Java collections framework.  HIterators differ from enumerations in two
 * ways: <ul>
 *	<li> HIterators allow the caller to remove elements from the
 *	    underlying collection during the iteration with well-defined
 * 	    semantics.
 *	<li> Method names have been improved.
 * </ul>
 *
 * @author  Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see     HCollection
 * @see     HListIterator
 * @see     HEnumeration
 */
public interface HIterator
{
    /**
     * Returns <code>true</code> if the iteration has more elements. (In other
     * words, returns <code>true</code> if <code>next</code> would return an element
     * rather than throwing an exception.)
     *
     * @return <code>true</code> if the iterator has more elements.
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @exception NoSuchElementException iteration has no more elements.
     */
    Object next();

    /**
     *
     * Removes from the underlying collection the last element returned by the
     * iterator (optional operation).  This method can be called only once per
     * call to <code>next</code>.  The behavior of an iterator is unspecified if
     * the underlying collection is modified while the iteration is in
     * progress in any way other than by calling this method.
     *
     * @exception UnsupportedOperationException if the <code>remove</code>
     *		 operation is not supported by this HIterator.
     * @exception IllegalStateException if the <code>next</code> method has not
     *		 yet been called, or the <code>remove</code> method has already
     *		 been called after the last call to the <code>next</code>
     *		 method.
     */
    void remove();
}
