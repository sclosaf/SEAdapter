package myAdapter;

import myException.IllegalStateException;

import java.util.Vector;

import java.util.NoSuchElementException;

import java.lang.NullPointerException;
import java.lang.IndexOutOfBoundsException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/*
 * Implemento in java micro
 * Interfaccia 1.4.2
 * No @Override
 * RICONTROLL tutto
 * controlla metodi usati su vec.
 */

public class VectorAdapter implements HList
{
    protected Vector vec;

    public VectorAdapter(int initialCapacity, int capacityIncrement)
    {
        if(initialCapacity < 0)
            throw new IllegalArgumentException();

        vec = new Vector(initialCapacity, capacityIncrement);
    }

    public VectorAdapter(int initialCapacity)
    {
        this(initialCapacity, 0);
    }

    public VectorAdapter()
    {
        this(10);
    }

    public VectorAdapter(HCollection c)
    {
        this(c.size(), 0);
        addAll(c);
    }

    public synchronized void copyInto(Object[] anArray)
    {
        if(anArray == null)
            throw new NullPointerException();

        if(anArray.length < size())
            throw new IndexOutOfBoundsException();

        System.arraycopy(vec.toArray(), beginIndex(), anArray, 0, size());
    }

    public void trimToSize()
    {
        vec.trimToSize();
    }

    public synchronized void ensureCapacity(int minCapacity)
    {
        vec.ensureCapacity(minCapacity);
    }

    public synchronized void setSize(int newSize)
    {
        if(newSize < 0)
            throw new ArrayIndexOutOfBoundsException();

        vec.setSize(newSize);
    }

    public synchronized int capacity()
    {
        return vec.capacity();
    }

    public synchronized int size()
    {
        return vec.size();
    }

    public synchronized boolean isEmpty()
    {
        return size() == 0;
    }

    public HEnumeration elements()
    {
        return new EnumerationAdapter(this, beginIndex(), beginIndex() + size());
    }

    public boolean contains(Object elem)
    {
        return indexOf(elem) != -1;
    }

    public int indexOf(Object elem)
    {
        return indexOf(elem, 0);
    }

    public synchronized int indexOf(Object elem, int index)
    {
        if(index < 0)
            throw new IndexOutOfBoundsException();

        if(index >= size())
            return -1;

        if(elem == null)
        {
            for(int i = beginIndex() + index; i < beginIndex() + size(); i++)
                if(elementAt(i - beginIndex()) == null)
                    return i - beginIndex();
        }
        else
        {
            for(int i = beginIndex() + index; i < beginIndex() + size(); i++)
                if(elem.equals(elementAt(i - beginIndex())))
                    return i - beginIndex();
        }

        return -1;
    }

    public int lastIndexOf(Object elem)
    {
        return lastIndexOf(elem, size() - 1);
    }

    public synchronized int lastIndexOf(Object elem, int index)
    {
        if(index >= size())
            throw new IndexOutOfBoundsException();

        if(index < 0)
            return -1;

        if(elem == null)
        {
            for(int i = beginIndex() + index; i >= beginIndex(); i--)
                if(elementAt(i - beginIndex()) == null)
                    return i - beginIndex();
        }
        else
        {
            for(int i = beginIndex() + index; i >= beginIndex(); i--)
                if(elem.equals(elementAt(i - beginIndex())))
                    return i - beginIndex();
        }

        return -1;
    }

    public synchronized Object elementAt(int index)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        return vec.elementAt(beginIndex() + index);
    }

    public synchronized Object firstElement()
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return vec.elementAt(beginIndex());
    }

    public synchronized Object lastElement()
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return vec.elementAt(beginIndex() + size() - 1);
    }

    public synchronized void setElementAt(Object obj, int index)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.setElementAt(obj, beginIndex() + index);
    }

    public synchronized void removeElementAt(int index)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.removeElementAt(beginIndex() + index);
    }

    public synchronized void insertElementAt(Object obj, int index)
    {
        if(index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        vec.insertElementAt(obj, beginIndex() + index);
    }

    public synchronized void addElement(Object obj)
    {
        insertElementAt(obj, size());
    }

    public synchronized boolean removeElement(Object obj)
    {
        int index = indexOf(obj);

        if(index == -1)
            return false;

        removeElementAt(index);
        return true;
    }

    public synchronized void removeAllElements()
    {
        for(int i = size() - 1; i >= 0; i--)
            removeElementAt(i);

        setSize(0);
    }

    public synchronized Object clone()
    {
        VectorAdapter cloned = new VectorAdapter(size());

        HIterator iter = iterator();

        while(iter.hasNext())
            cloned.addElement(iter.next());

        return cloned;
    }

    public synchronized Object[] toArray()
    {
        Object[] arr = new Object[size()];
        System.arraycopy(vec.toArray(), beginIndex(), arr, 0, size());
        return arr;
    }

    public synchronized Object[] toArray(Object[] a)
    {
        if(a == null)
            throw new NullPointerException();

        if(a.length < size())
            a = new Object[size()];

        System.arraycopy(vec.toArray(), beginIndex(), a, 0, size());

        if(a.length > size())
            a[size()] = null;

        return a;
    }

    public synchronized Object get(int index)
    {
        return elementAt(index);
    }

    public synchronized Object set(int index, Object element)
    {
        Object obj = elementAt(index);
        setElementAt(element, index);

        return obj;
    }

    public synchronized boolean add(Object o)
    {
        addElement(o);
        return true;
    }

    public boolean remove(Object o)
    {
        return removeElement(o);
    }

    public void add(int index, Object element)
    {
        insertElementAt(element, index);
    }

    public synchronized Object remove(int index)
    {
        Object removed = elementAt(index);
        removeElementAt(index);

        return removed;
    }

    public void clear()
    {
        removeAllElements();
    }

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

    public synchronized boolean addAll(HCollection c)
    {
        return addAll(size(), c);
    }

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

    public synchronized int hashCode()
    {
        return vec.hashCode();
    }

    public synchronized String toString()
    {
        return vec.toString();
    }

    public HList subList(int fromIndex, int toIndex)
    {
        if(fromIndex > toIndex)
            throw new IllegalArgumentException();

        if(fromIndex < 0 || toIndex > size())
            throw new IndexOutOfBoundsException();

        return new SubListAdapter(vec, beginIndex() + fromIndex, beginIndex() + toIndex);
    }

    protected void removeRange(int fromIndex, int toIndex)
    {
        subList(fromIndex, toIndex).clear();
    }

    public HIterator iterator()
    {
        return new IteratorAdapter(this, beginIndex(), beginIndex() + size());
    }

    public HListIterator listIterator()
    {
        return new ListIteratorAdapter(this, beginIndex(), beginIndex() + size());
    }

    public HListIterator listIterator(int index)
    {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        return new ListIteratorAdapter(this, beginIndex() + index, beginIndex() + size());
    }

    protected int beginIndex()
    {
        return 0;
    }
}
