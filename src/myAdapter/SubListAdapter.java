package myAdapter;

import java.util.Vector;

import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.ArrayIndexOutOfBoundsException;

/**
 * A view of a portion of a {@code VectorAdapter} or another {@code SubListAdapter}.
 * The sublist is backed by the original vector, so non-structural changes in the
 * sublist are reflected in the original vector, and vice-versa. The sublist supports
 * all of the optional list operations supported by the backing list.
 * <p>
 * This class eliminates the need for explicit range operations. Any operation that
 * expects a list can be used as a range operation by passing a sublist view instead
 * of a whole list. For example, the following idiom removes a range of elements
 * from a list:
 * <pre>
 *     list.subList(from, to).clear();
 * </pre>
 * <p>
 * The semantics of the list returned by this method become undefined if the backing
 * list is <i>structurally modified</i> in any way other than via the returned list.
 * Structural modifications are those that change the size of the backing list, or
 * otherwise perturb it in such a fashion that iterations in progress may yield
 * incorrect results.
 *
 * @author  Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see     HList
 * @see     VectorAdapter
 * @see     HCollection
 */
public class SubListAdapter extends VectorAdapter
{
    /**
     * The starting index of the sublist view within the backing vector.
     */
    private final int offset;

    /**
     * The current number of elements in this sublist view.
     */
    private int subListSize;

    /**
     * Constructs a sublist view of the specified vector between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
     *
     * @param vec the backing vector from which the sublist is derived
     * @param fromIndex bottom endpoint (inclusive) of the sublist
     * @param toIndex top endpoint (exclusive) of the sublist
     * @throws NullPointerException if the specified vector is {@code null}
     * @throws IllegalArgumentException if {@code fromIndex} is greater than {@code toIndex}
     * @throws IndexOutOfBoundsException if {@code fromIndex} or {@code toIndex} is out of range
     *         ({@code fromIndex < 0 || toIndex > vec.size()})
     */
    public SubListAdapter(Vector vec, int fromIndex, int toIndex)
    {
        if(vec == null)
            throw new NullPointerException();

        if(fromIndex > toIndex)
            throw new IllegalArgumentException();

        if(fromIndex < 0 || toIndex > vec.size())
            throw new IndexOutOfBoundsException();

        this.vec = vec;
        this.offset = fromIndex;
        this.subListSize = toIndex - fromIndex;
    }

    /**
     * Sets the size of this sublist. If the new size is greater than the
     * current size, new <code>null</code> items are added to the end of
     * the sublist. If the new size is less than the current size, all
     * components at index <code>newSize</code> and greater are discarded.
     *
     * @param   newSize   the new size of this sublist.
     * @throws  ArrayIndexOutOfBoundsException if new size is negative.
     */
    public synchronized void setSize(int newSize)
    {
        if(newSize < 0)
            throw new ArrayIndexOutOfBoundsException();

        if(newSize > subListSize)
            for(int i = subListSize; i < newSize; i++)
                vec.insertElementAt(null, offset() + subListSize);
        else
            for(int i = subListSize - 1; i >= newSize; i--)
                vec.removeElementAt(offset() + newSize);

        subListSize = newSize;
    }

    /**
     * Returns the number of components in this sublist.
     *
     * @return  the number of components in this sublist.
     */
    public synchronized int size()
    {
        return subListSize;
    }

    /**
     * Deletes the component at the specified index. Each component in
     * this sublist with an index greater or equal to the specified
     * <code>index</code> is shifted downward to have an index one
     * smaller than the value it had previously. The size of this sublist
     * is decreased by <code>1</code>.<p>
     *
     * The index must be a value greater than or equal to <code>0</code>
     * and less than the current size of the sublist. <p>
     *
     * This method is identical in functionality to the remove method
     * (which is part of the List interface).  Note that the remove method
     * returns the old value that was stored at the specified position.
     *
     * @param      index   the index of the object to remove.
     * @exception  ArrayIndexOutOfBoundsException  if the index was invalid.
     * @see        #size()
     * @see        #remove(int)
     * @see        HList
     */
    public synchronized void removeElementAt(int index)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.removeElementAt(offset() + index);

        subListSize--;
    }


    /**
     * Inserts the specified object as a component in this sublsit at the
     * specified <code>index</code>. Each component in this sublist with
     * an index greater or equal to the specified <code>index</code> is
     * shifted upward to have an index one greater than the value it had
     * previously. <p>
     *
     * The index must be a value greater than or equal to <code>0</code>
     * and less than or equal to the current size of the sublist. (If the
     * index is equal to the current size of the sublist, the new element
     * is appended to the sublist.)<p>
     *
     * This method is identical in functionality to the add(Object, int) method
     * (which is part of the HList interface). Note that the add method reverses
     * the order of the parameters, to more closely match array usage.
     *
     * @param      obj     the component to insert.
     * @param      index   where to insert the new component.
     * @exception  ArrayIndexOutOfBoundsException  if the index was invalid.
     * @see        #size()
     * @see        #add(int, Object)
     * @see        HList
     */
    public synchronized void insertElementAt(Object obj, int index)
    {
        if(index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        vec.insertElementAt(obj, offset() + index);
        subListSize++;
    }


    /**
     * Returns a clone of this sublist. The copy will contain a
     * reference to a clone of the internal data array, not a reference
     * to the original internal data array of this <code>SubList</code> object.
     *
     * @return  a clone of this sublist.
     */
    public synchronized Object clone()
    {
        Vector vector = new Vector(vec.size());

        for(int i = 0; i < vec.size(); i++)
            vector.insertElementAt(vec.elementAt(i), i);

        return new SubListAdapter(vector, offset(), offset() + size());
    }

    /**
     * Returns a string representation of this SubList, containing
     * the String representation of each element.
     */
    public synchronized String toString()
    {
        StringBuffer bf = new StringBuffer();
        bf.append("[");

        HIterator iter = iterator();

        while(iter.hasNext())
        {
            Object o = iter.next();

            bf.append(o == this ? "(this Collection)" : String.valueOf(o));

            if(iter.hasNext())
                bf.append(", ");
        }

        bf.append("]");
        return bf.toString();
    }

    /**
     * Returns the offset of this sublist relative to the backing vector.
     * This is a helper method to manage sublist offsets throughout polymorphism.
     *
     * @return the offset of the sublist relative to its backing vector
     */
    protected int offset()
    {
        return offset;
    }
}
