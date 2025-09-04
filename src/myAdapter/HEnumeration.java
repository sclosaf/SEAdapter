package myAdapter;

import java.util.NoSuchElementException;

/**
 * An object that implements the HEnumeration interface generates a
 * series of elements, one at a time. Successive calls to the
 * <code>nextElement</code> method return successive elements of the
 * series.
 * <p>
 * For example, to print all elements of a vector <i>v</i>:
 * <blockquote><pre>
 *     for (HEnumeration e = v.elements() ; e.hasMoreElements() ;) {
 *         System.out.println(e.nextElement());<br>
 *     }
 * </pre></blockquote>
 * <p>
 * Methods are provided to enumerate through the elements of a
 * vector, the keys of a hashtable, and the values in a hashtable.
 * HEnumerations are also used to specify the input streams to a
 * <code>SequenceInputStream</code>.
 * <p>
 * NOTE: The functionality of this interface is duplicated by the HIterator
 * interface.  In addition, HIterator adds an optional remove operation, and
 * has shorter method names.  New implementations should consider using
 * HIterator in preference to HEnumeration.
 *
 * @author  Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see     HIterator
 * @see     HEnumeration#nextElement()
 * @see     VectorAdapter
 * @see     VectorAdapter#elements()
 */
public interface HEnumeration
{
    /**
     * Tests if this enumeration contains more elements.
     *
     * @return  <code>true</code> if and only if this enumeration object
     *           contains at least one more element to provide;
     *          <code>false</code> otherwise.
     */
    boolean hasMoreElements();

    /**
     * Returns the next element of this enumeration if this enumeration
     * object has at least one more element to provide.
     *
     * @return     the next element of this enumeration.
     * @exception  NoSuchElementException  if no more elements exist.
     */
    Object nextElement();
}
