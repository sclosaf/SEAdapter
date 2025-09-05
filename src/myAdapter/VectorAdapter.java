package myAdapter;

import myException.IllegalStateException;

import java.util.Vector;

import java.util.NoSuchElementException;

import java.lang.NullPointerException;
import java.lang.IndexOutOfBoundsException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * The <code>VectorAdapter</code> class implements a growable array of
 * objects. Like an array, it contains components that can be
 * accessed using an integer index. However, the size of a
 * <code>VectorAdapter</code> can grow or shrink as needed to accommodate
 * adding and removing items after the <code>VectorAdapter</code> has been created.<p>
 *
 * Each vector tries to optimize storage management by maintaining a
 * <code>capacity</code> and a <code>capacityIncrement</code>. The
 * <code>capacity</code> is always at least as large as the vector
 * size; it is usually larger because as components are added to the
 * vector, the vector's storage increases in chunks the size of
 * <code>capacityIncrement</code>. An application can increase the
 * capacity of a vector before inserting a large number of
 * components; this reduces the amount of incremental reallocation. <p>
 *
 * As of the Java 2 platform v1.2, this class has been retrofitted to
 * implement HList, so that it becomes a part of Java's collection framework.
 * Unlike the new collection implementations, VectorAdapter is synchronized.<p>
 *
 * The HIterators returned by VectorAdapter's iterator and listIterator
 * methods are <em>fail-fast</em>: if the VectorAdapter is structurally modified
 * at any time after the HIterator is created, in any way except through the
 * HIterator's own remove or add methods, the HIterator will throw a
 * ConcurrentModificationException.  Thus, in the face of concurrent
 * modification, the HIterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 * The HEnumerations returned by VectorAdapter's elements method are <em>not</em>
 * fail-fast.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <code>ConcurrentModificationException</code> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:  <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i></p>
 *
 * @author  Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see     HCollection
 * @see     HList
 */
public class VectorAdapter implements HList
{
    /**
     * The Vector adaptee into which the components of the adapter are stored.
     * Each adapted operation is applied directly to this VectorAdapter, in order to
     * conform the interface.
     * */
    protected Vector vec;

    /**
     * Constructs an empty vector with the specified initial capacity and
     * capacity increment.
     *
     * @param   initialCapacity     the initial capacity of the vector.
     * @param   capacityIncrement   the amount by which the capacity is
     *                              increased when the vector overflows.
     * @exception IllegalArgumentException if the specified initial capacity
     *               is negative
     */
    public VectorAdapter(int initialCapacity, int capacityIncrement)
    {
        if(initialCapacity < 0)
            throw new IllegalArgumentException();

        vec = new Vector(initialCapacity, capacityIncrement);
    }

    /**
     * Constructs an empty vector with the specified initial capacity and
     * with its capacity increment equal to zero.
     *
     * @param   initialCapacity   the initial capacity of the vector.
     * @exception IllegalArgumentException if the specified initial capacity
     *               is negative
     */
    public VectorAdapter(int initialCapacity)
    {
        this(initialCapacity, 0);
    }

    /**
     * Constructs an empty vector so that its internal data array
     * has size <code>10</code> and its standard capacity increment is
     * zero.
     */
    public VectorAdapter()
    {
        this(10);
    }

    /**
     * Constructs a vector containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this
     *       vector.
     * @throws NullPointerException if the specified collection is null.
     */
    public VectorAdapter(HCollection c)
    {
        this(c.size(), 0);
        addAll(c);
    }

    /**
     * Copies the components of this vector into the specified array. The
     * item at index <code>k</code> in this vector is copied into component
     * <code>k</code> of <code>anArray</code>. The array must be big enough to hold
     * all the objects in this vector, else an
     * <code>IndexOutOfBoundsException</code> is thrown.
     *
     * @param   anArray   the array into which the components get copied.
     * @throws  NullPointerException if the given array is null.
     */
    public synchronized void copyInto(Object[] anArray)
    {
        if(anArray == null)
            throw new NullPointerException();

        if(anArray.length < size())
            throw new IndexOutOfBoundsException();

        System.arraycopy(vec.toArray(), offset(), anArray, 0, size());
    }

    /**
     * Trims the capacity of this vector to be the vector's current
     * size. If the capacity of this vector is larger than its current
     * size, then the capacity is changed to equal the size by replacing
     * its internal data array, kept in the field <code>elementData</code>,
     * with a smaller one. An application can use this operation to
     * minimize the storage of a vector.
     */
    public void trimToSize()
    {
        vec.trimToSize();
    }

    /**
     * Increases the capacity of this vector, if necessary, to ensure
     * that it can hold at least the number of components specified by
     * the minimum capacity argument.
     *
     * <p>If the current capacity of this vector is less than
     * <code>minCapacity</code>, then its capacity is increased by replacing its
     * internal data array, kept in the field <code>elementData</code>, with a
     * larger one.  The size of the new data array will be the old size plus
     * <code>capacityIncrement</code>, unless the value of
     * <code>capacityIncrement</code> is less than or equal to zero, in which case
     * the new capacity will be twice the old capacity; but if this new size
     * is still smaller than <code>minCapacity</code>, then the new capacity will
     * be <code>minCapacity</code>.
     *
     * @param minCapacity the desired minimum capacity.
     */
    public synchronized void ensureCapacity(int minCapacity)
    {
        vec.ensureCapacity(minCapacity);
    }

    /**
     * Sets the size of this vector. If the new size is greater than the
     * current size, new <code>null</code> items are added to the end of
     * the vector. If the new size is less than the current size, all
     * components at index <code>newSize</code> and greater are discarded.
     *
     * @param   newSize   the new size of this vector.
     * @throws  ArrayIndexOutOfBoundsException if new size is negative.
     */
    public synchronized void setSize(int newSize)
    {
        if(newSize < 0)
            throw new ArrayIndexOutOfBoundsException();

        vec.setSize(newSize);
    }

    /**
     * Returns the current capacity of this vector.
     *
     * @return  the current capacity (the length of its internal
     *          data array, kept in the field <code>elementData</code>
     *          of this vector).
     */
    public synchronized int capacity()
    {
        return vec.capacity();
    }

    /**
     * Returns the number of components in this vector.
     *
     * @return  the number of components in this vector.
     */
    public synchronized int size()
    {
        return vec.size();
    }

    /**
     * Tests if this vector has no components.
     *
     * @return  <code>true</code> if and only if this vector has
     *          no components, that is, its size is zero;
     *          <code>false</code> otherwise.
     */
    public synchronized boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Returns an enumeration of the components of this vector. The
     * returned <code>HEnumeration</code> object will generate all items in
     * this vector. The first item generated is the item at index <code>0</code>,
     * then the item at index <code>1</code>, and so on.
     *
     * @return  an enumeration of the components of this vector.
     * @see     HEnumeration
     * @see     HIterator
     */
    public HEnumeration elements()
    {
        return new EnumerationAdapter(this, offset(), offset() + size());
    }

    /**
     * Tests if the specified object is a component in this vector.
     *
     * @param   elem   an object.
     * @return  <code>true</code> if and only if the specified object
     * is the same as a component in this vector, as determined by the
     * <code>equals</code> method; <code>false</code> otherwise.
     */
    public boolean contains(Object elem)
    {
        return indexOf(elem) != -1;
    }

    /**
     * Searches for the first occurence of the given argument, testing
     * for equality using the <code>equals</code> method.
     *
     * @param   elem   an object.
     * @return  the index of the first occurrence of the argument in this
     *          vector, that is, the smallest value <code>k</code> such that
     *          <code>elem.equals(elementData[k])</code> is <code>true</code>;
     *          returns <code>-1</code> if the object is not found.
     */
    public int indexOf(Object elem)
    {
        return indexOf(elem, 0);
    }

    /**
     * Searches for the first occurence of the given argument, beginning
     * the search at <code>index</code>, and testing for equality using
     * the <code>equals</code> method.
     *
     * @param   elem    an object.
     * @param   index   the non-negative index to start searching from.
     * @return  the index of the first occurrence of the object argument in
     *          this vector at position <code>index</code> or later in the
     *          vector, that is, the smallest value <code>k</code> such that
     *          <code>elem.equals(elementData[k]) &amp;&amp; (k &gt;= index)</code> is
     *          <code>true</code>; returns <code>-1</code> if the object is not
     *          found. (Returns <code>-1</code> if <code>index</code> &gt;= the
     *          current size of this <code>VectorAdapter</code>.)
     * @exception  IndexOutOfBoundsException  if <code>index</code> is negative.
     * @see        Object#equals(Object)
     */
    public synchronized int indexOf(Object elem, int index)
    {
        if(index < 0)
            throw new IndexOutOfBoundsException();

        if(index >= size())
            return -1;

        if(elem == null)
        {
            for(int i = offset() + index; i < offset() + size(); i++)
                if(elementAt(i - offset()) == null)
                    return i - offset();
        }
        else
        {
            for(int i = offset() + index; i < offset() + size(); i++)
                if(elem.equals(elementAt(i - offset())))
                    return i - offset();
        }

        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified object in
     * this vector.
     *
     * @param   elem   the desired component.
     * @return  the index of the last occurrence of the specified object in
     *          this vector, that is, the largest value <code>k</code> such that
     *          <code>elem.equals(elementData[k])</code> is <code>true</code>;
     *          returns <code>-1</code> if the object is not found.
     */
    public int lastIndexOf(Object elem)
    {
        return lastIndexOf(elem, size() - 1);
    }


    /**
     * Searches backwards for the specified object, starting from the
     * specified index, and returns an index to it.
     *
     * @param  elem    the desired component.
     * @param  index   the index to start searching from.
     * @return the index of the last occurrence of the specified object in this
     *          vector at position less than or equal to <code>index</code> in
     *          the vector, that is, the largest value <code>k</code> such that
     *          <code>elem.equals(elementData[k]) &amp;&amp; (k &lt;= index)</code> is
     *          <code>true</code>; <code>-1</code> if the object is not found.
     *          (Returns <code>-1</code> if <code>index</code> is negative.)
     * @exception  IndexOutOfBoundsException  if <code>index</code> is greater
     *             than or equal to the current size of this vector.
     */
    public synchronized int lastIndexOf(Object elem, int index)
    {
        if(index >= size())
            throw new IndexOutOfBoundsException();

        if(index < 0)
            return -1;

        if(elem == null)
        {
            for(int i = offset() + index; i >= offset(); i--)
                if(elementAt(i - offset()) == null)
                    return i - offset();
        }
        else
        {
            for(int i = offset() + index; i >= offset(); i--)
                if(elem.equals(elementAt(i - offset())))
                    return i - offset();
        }

        return -1;
    }

    /**
     * Returns the component at the specified index.<p>
     *
     * This method is identical in functionality to the get method
     * (which is part of the HList interface).
     *
     * @param      index   an index into this vector.
     * @return     the component at the specified index.
     * @exception  ArrayIndexOutOfBoundsException  if the <code>index</code>
     *             is negative or not less than the current size of this
     *             <code>VectorAdapter</code> object.
     *             given.
     * @see	  HList
     */
    public synchronized Object elementAt(int index)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        return vec.elementAt(offset() + index);
    }

    /**
     * Returns the first component (the item at index <code>0</code>) of
     * this vector.
     *
     * @return     the first component of this vector.
     * @exception  NoSuchElementException  if this vector has no components.
     */
    public synchronized Object firstElement()
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return vec.elementAt(offset());
    }

    /**
     * Returns the last component of the vector.
     *
     * @return  the last component of the vector, i.e., the component at index
     *          <code>size()&nbsp;-&nbsp;1</code>.
     * @exception  NoSuchElementException  if this vector is empty.
     */
    public synchronized Object lastElement()
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return vec.elementAt(offset() + size() - 1);
    }

    /**
     * Sets the component at the specified <code>index</code> of this
     * vector to be the specified object. The previous component at that
     * position is discarded.<p>
     *
     * The index must be a value greater than or equal to <code>0</code>
     * and less than the current size of the vector. <p>
     *
     * This method is identical in functionality to the set method
     * (which is part of the HList interface). Note that the set method reverses
     * the order of the parameters, to more closely match array usage.  Note
     * also that the set method returns the old value that was stored at the
     * specified position.
     *
     * @param      obj     what the component is to be set to.
     * @param      index   the specified index.
     * @exception  ArrayIndexOutOfBoundsException  if the index was invalid.
     * @see        #size()
     * @see        HList
     * @see        #set(int, java.lang.Object)
     */
    public synchronized void setElementAt(Object obj, int index)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.setElementAt(obj, offset() + index);
    }

    /**
     * Deletes the component at the specified index. Each component in
     * this vector with an index greater or equal to the specified
     * <code>index</code> is shifted downward to have an index one
     * smaller than the value it had previously. The size of this vector
     * is decreased by <code>1</code>.<p>
     *
     * The index must be a value greater than or equal to <code>0</code>
     * and less than the current size of the vector. <p>
     *
     * This method is identical in functionality to the remove method
     * (which is part of the HList interface).  Note that the remove method
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
    }

    /**
     * Inserts the specified object as a component in this vector at the
     * specified <code>index</code>. Each component in this vector with
     * an index greater or equal to the specified <code>index</code> is
     * shifted upward to have an index one greater than the value it had
     * previously. <p>
     *
     * The index must be a value greater than or equal to <code>0</code>
     * and less than or equal to the current size of the vector. (If the
     * index is equal to the current size of the vector, the new element
     * is appended to the VectorAdapter.)<p>
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
    }

    /**
     * Adds the specified component to the end of this vector,
     * increasing its size by one. The capacity of this vector is
     * increased if its size becomes greater than its capacity. <p>
     *
     * This method is identical in functionality to the add(Object) method
     * (which is part of the HList interface).
     *
     * @param   obj   the component to be added.
     * @see     #add(Object)
     * @see     HList
     */
    public synchronized void addElement(Object obj)
    {
        insertElementAt(obj, size());
    }

    /**
     * Removes the first (lowest-indexed) occurrence of the argument
     * from this vector. If the object is found in this vector, each
     * component in the vector with an index greater or equal to the
     * object's index is shifted downward to have an index one smaller
     * than the value it had previously.<p>
     *
     * This method is identical in functionality to the remove(Object)
     * method (which is part of the HList interface).
     *
     * @param   obj   the component to be removed.
     * @return  <code>true</code> if the argument was a component of this
     *          vector; <code>false</code> otherwise.
     * @see     HList#remove(Object)
     * @see     HList
     */
    public synchronized boolean removeElement(Object obj)
    {
        int index = indexOf(obj);

        if(index == -1)
            return false;

        removeElementAt(index);
        return true;
    }

    /**
     * Removes all components from this vector and sets its size to zero.<p>
     *
     * This method is identical in functionality to the clear method
     * (which is part of the HList interface).
     *
     * @see     #clear
     * @see     HList
     */
    public synchronized void removeAllElements()
    {
        for(int i = size() - 1; i >= 0; i--)
            removeElementAt(i);

        setSize(0);
    }

    /**
     * Returns a clone of this vector. The copy will contain a
     * reference to a clone of the internal data array, not a reference
     * to the original internal data array of this <code>VectorAdapter</code> object.
     *
     * @return  a clone of this vector.
     */
    public synchronized Object clone()
    {
        VectorAdapter cloned = new VectorAdapter(size());

        HIterator iter = iterator();

        while(iter.hasNext())
            cloned.addElement(iter.next());

        return cloned;
    }

    /**
     * Returns an array containing all of the elements in this VectorAdapter
     * in the correct order.
     */
    public synchronized Object[] toArray()
    {
        Object[] arr = new Object[size()];
        System.arraycopy(vec.toArray(), offset(), arr, 0, size());
        return arr;
    }

    /**
     * Returns an array containing all of the elements in this VectorAdapter in the
     * correct order; the runtime type of the returned array is that of the
     * specified array.  If the VectorAdapter fits in the specified array, it is
     * returned therein.  Otherwise, a new array is allocated with the runtime
     * type of the specified array and the size of this VectorAdapter.<p>
     *
     * If the VectorAdapter fits in the specified array with room to spare
     * (i.e., the array has more elements than the VectorAdapter),
     * the element in the array immediately following the end of the
     * VectorAdapter is set to null.  This is useful in determining the length
     * of the VectorAdapter <em>only</em> if the caller knows that the
     * VectorAdapter does not contain any null elements.
     *
     * @param a the array into which the elements of the VectorAdapter are to
     *		be stored, if it is big enough; otherwise, a new array of the
     * 		same runtime type is allocated for this purpose.
     * @return an array containing the elements of the VectorAdapter.
     * @exception ArrayStoreException the runtime type of a is not a supertype
     * of the runtime type of every element in this VectorAdapter.
     * @throws NullPointerException if the given array is null.
     */
    public synchronized Object[] toArray(Object[] a)
    {
        if(a == null)
            throw new NullPointerException();

        if(a.length < size())
            a = new Object[size()];

        System.arraycopy(vec.toArray(), offset(), a, 0, size());

        if(a.length > size())
            a[size()] = null;

        return a;
    }

    /**
     * Returns the element at the specified position in this VectorAdapter.
     *
     * @param index index of element to return.
     * @return object at the specified index
     * @exception ArrayIndexOutOfBoundsException index is out of range (index
     * 		 &lt; 0 || index &gt;= size()).
     */
    public synchronized Object get(int index)
    {
        return elementAt(index);
    }

    /**
     * Replaces the element at the specified position in this VectorAdapter with the
     * specified element.
     *
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *		 (index &lt; 0 || index &gt;= size()).
     */
    public synchronized Object set(int index, Object element)
    {
        Object obj = elementAt(index);
        setElementAt(element, index);

        return obj;
    }

    /**
     * Appends the specified element to the end of this VectorAdapter.
     *
     * @param o element to be appended to this VectorAdapter.
     * @return true (as per the general contract of HCollection.add).
     */
    public synchronized boolean add(Object o)
    {
        addElement(o);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element in this VectorAdapter
     * If the VectorAdapter does not contain the element, it is unchanged.  More
     * formally, removes the element with the lowest index i such that
     * <code>(o==null ? get(i)==null : o.equals(get(i)))</code> (if such
     * an element exists).
     *
     * @param o element to be removed from this VectorAdapter, if present.
     * @return true if the VectorAdapter contained the specified element.
     */
    public boolean remove(Object o)
    {
        return removeElement(o);
    }

    /**
     * Inserts the specified element at the specified position in this VectorAdapter.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @exception ArrayIndexOutOfBoundsException index is out of range
     *		 (index &lt; 0 || index &gt; size()).
     */
    public void add(int index, Object element)
    {
        insertElementAt(element, index);
    }

    /**
     * Removes the element at the specified position in this VectorAdapter.
     * shifts any subsequent elements to the left (subtracts one from their
     * indices).  Returns the element that was removed from the VectorAdapter.
     *
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     * 		 &lt; 0 || index &gt;= size()).
     * @param index the index of the element to removed.
     * @return element that was removed
     */
    public synchronized Object remove(int index)
    {
        Object removed = elementAt(index);
        removeElementAt(index);

        return removed;
    }

    /**
     * Removes all of the elements from this VectorAdapter.  The VectorAdapter will
     * be empty after this call returns (unless it throws an exception).
     */
    public void clear()
    {
        removeAllElements();
    }

    /**
     * Returns true if this VectorAdapter contains all of the elements in the
     * specified HCollection.
     *
     * @param   c a collection whose elements will be tested for containment
     *          in this VectorAdapter
     * @return true if this VectorAdapter contains all of the elements in the
     *	      specified collection.
     * @throws NullPointerException if the specified collection is null.
     */
    public synchronized boolean containsAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        if(c == this)
            return true;

        HIterator iter = c.iterator();

        while(iter.hasNext())
            if(!contains(iter.next()))
                return false;

        return true;
    }

    /**
     * Appends all of the elements in the specified HCollection to the end of
     * this VectorAdapter, in the order that they are returned by the specified
     * HCollection's HIterator.  The behavior of this operation is undefined if
     * the specified HCollection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified HCollection is this VectorAdapter, and this VectorAdapter is nonempty.)
     *
     * @param c elements to be inserted into this VectorAdapter.
     * @return <code>true</code> if this VectorAdapter changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     */
    public synchronized boolean addAll(HCollection c)
    {
        return addAll(size(), c);
    }

    /**
     * Removes from this VectorAdapter all of its elements that are contained in the
     * specified HCollection.
     *
     * @param c a collection of elements to be removed from the VectorAdapter
     * @return true if this VectorAdapter changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     */
    public synchronized boolean removeAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        if(c == this)
        {
            if(this.isEmpty())
                return false;

            this.removeAllElements();
            return true;
        }

        HIterator iter = c.iterator();
        boolean changed = false;

        while(iter.hasNext())
        {
            Object elem = iter.next();

            while(contains(elem))
            {
                remove(elem);
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Retains only the elements in this VectorAdapter that are contained in the
     * specified HCollection.  In other words, removes from this VectorAdapter all
     * of its elements that are not contained in the specified HCollection.
     *
     * @param c a collection of elements to be retained in this VectorAdapter
     *          (all other elements are removed)
     * @return true if this VectorAdapter changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     */
    public synchronized boolean retainAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        if(c == this)
            return false;

        HIterator iter = iterator();
        boolean changed = false;

        while(iter.hasNext())
        {
            if(!c.contains(iter.next()))
            {
                iter.remove();
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Inserts all of the elements in in the specified HCollection into this
     * VectorAdapter at the specified position.  Shifts the element currently at
     * that position (if any) and any subsequent elements to the right
     * (increases their indices).  The new elements will appear in the VectorAdapter
     * in the order that they are returned by the specified HCollection's
     * iterator.
     *
     * @param index index at which to insert first element
     *		   from the specified collection.
     * @param c elements to be inserted into this VectorAdapter.
     * @return <code>true</code> if this VectorAdapter changed as a result of the call.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *		 &lt; 0 || index &gt; size()).
     * @throws NullPointerException if the specified collection is null.
     */
    public synchronized boolean addAll(int index, HCollection c)
    {
        if(index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        if(c == null)
            throw new NullPointerException();

        if(c == this)
        {
            int size = this.size();
            VectorAdapter clone = (VectorAdapter) this.clone();

            for(int i = 0; i < size; i++)
                insertElementAt(clone.elementAt(i), index + i);

            return true;
        }

        HIterator iter = c.iterator();
        boolean changed = false;

        for(int i = 0; i < c.size(); i++)
        {
            insertElementAt(iter.next(), index + i);
            changed = true;
        }

        return changed;
    }

    /**
     * Compares the specified Object with this VectorAdapter for equality.  Returns
     * true if and only if the specified Object is also a HList, both HLists
     * have the same size, and all corresponding pairs of elements in the two
     * HLists are <em>equal</em>.  (Two elements <code>e1</code> and
     * <code>e2</code> are <em>equal</em> if <code>(e1==null ? e2==null :
     * e1.equals(e2))</code>.)  In other words, two HLists are defined to be
     * equal if they contain the same elements in the same order.
     *
     * @param o the Object to be compared for equality with this VectorAdapter.
     * @return true if the specified Object is equal to this VectorAdapter
     */
    public synchronized boolean equals(Object o)
    {
        if(o == this)
            return true;

        if(!(o instanceof HList))
            return false;

        if(this.size() != ((HList) o).size())
            return false;

        HIterator it1 = this.listIterator();
        HIterator it2 = ((HList) o).listIterator();

        while(it1.hasNext() && it2.hasNext())
        {
            Object o1 = it1.next();
            Object o2 = it2.next();

            if(!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }

        return true;
    }

    /**
     * Returns the hash code value for this VectorAdapter.
     */
    public synchronized int hashCode()
    {
        return vec.hashCode();
    }

    /**
     * Returns a string representation of this VectorAdapter, containing
     * the String representation of each element.
     */
    public synchronized String toString()
    {
        return vec.toString();
    }

    /**
     * Returns a view of the portion of this HList between fromIndex,
     * inclusive, and toIndex, exclusive.  (If fromIndex and ToIndex are
     * equal, the returned HList is empty.)  The returned HList is backed by this
     * HList, so changes in the returned HList are reflected in this HList, and
     * vice-versa.  The returned HList supports all of the optional HList
     * operations supported by this HList.<p>
     *
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).   Any operation that expects
     * a HList can be used as a range operation by operating on a subList view
     * instead of a whole HList.  For example, the following idiom
     * removes a range of elements from a HList:
     * <pre>
     *	   list.subList(from, to).clear();
     * </pre>
     * Similar idioms may be constructed for indexOf and lastIndexOf,
     * and all of the algorithms in the HCollections class can be applied to
     * a subList.<p>
     *
     * The semantics of the HList returned by this method become undefined if
     * the backing list (i.e., this HList) is <i>structurally modified</i> in
     * any way other than via the returned HList.  (Structural modifications are
     * those that change the size of the HList, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList.
     * @param toIndex high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this HList.
     * @throws IndexOutOfBoundsException endpoint index value out of range
     *         <code>(fromIndex &lt; 0 || toIndex &gt; size)</code>
     * @throws IllegalArgumentException endpoint indices out of order
     *	      <code>(fromIndex &gt; toIndex)</code>
     */
    public HList subList(int fromIndex, int toIndex)
    {
        if(fromIndex > toIndex)
            throw new IllegalArgumentException();

        if(fromIndex < 0 || toIndex > size())
            throw new IndexOutOfBoundsException();

        return new SubListAdapter(vec, offset() + fromIndex, offset() + toIndex);
    }

    /**
     * Removes from this HList all of the elements whose index is between
     * fromIndex, inclusive and toIndex, exclusive.  Shifts any succeeding
     * elements to the left (reduces their index).
     * This call shortens the ArrayList by (toIndex - fromIndex) elements.  (If
     * toIndex==fromIndex, this operation has no effect.)
     *
     * @param fromIndex index of first element to be removed.
     * @param toIndex index after last element to be removed.
     */
    protected void removeRange(int fromIndex, int toIndex)
    {
        subList(fromIndex, toIndex).clear();
    }

    /**
     * Returns an iterator over the elements in this list in proper
     * sequence. <p>
     *
     * This implementation returns a straightforward implementation of the
     * iterator interface, relying on the backing list's <code>size()</code>,
     * <code>get(int)</code>, and <code>remove(int)</code> methods.<p>
     *
     * @return an iterator over the elements in this list in proper sequence.
     */
    public HIterator iterator()
    {
        return new IteratorAdapter(this, offset(), offset() + size());
    }

    /**
     * Returns an iterator of the elements in this list (in proper sequence).
     * This implementation returns <code>listIterator(0)</code>.
     *
     * @return an iterator of the elements in this list (in proper sequence).
     *
     * @see #listIterator(int)
     */
    public HListIterator listIterator()
    {
        return listIterator(0);
    }

    /**
     * Returns a list iterator of the elements in this list (in proper
     * sequence), starting at the specified position in the list.  The
     * specified index indicates the first element that would be returned by
     * an initial call to the <code>next</code> method.  An initial call to
     * the <code>previous</code> method would return the element with the
     * specified index minus one.
     *
     * This implementation returns a straightforward implementation of the
     * <code>HListIterator</code> interface that extends the implementation of the
     * <code>HIterator</code> interface returned by the <code>iterator()</code> method.
     * The <code>HListIterator</code> implementation relies on the backing list's
     * <code>get(int)</code>, <code>set(int, Object)</code>, <code>add(int, Object)</code>
     * and <code>remove(int)</code> methods.
     *
     * @param index index of the first element to be returned from the list
     *		   iterator (by a call to the <code>next</code> method).
     *
     * @return a list iterator of the elements in this list (in proper
     * 	      sequence), starting at the specified position in the list.
     *
     * @throws IndexOutOfBoundsException if the specified index is out of
     *		 range (<code>index &lt; 0 || index &gt; size()</code>).
     */
    public HListIterator listIterator(int index)
    {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        return new ListIteratorAdapter(this, offset() + index, offset() + size());
    }

    /**
     * Returns the offset of this sublist relative to the backing vector.
     * This is a helper method to manage sublist offset throughout polymorphism.
     *
     * @return the offset of the list relative to its VectorAdapter.
     */
    protected int offset()
    {
        return 0;
    }
}
