package myAdapter;

import java.util.Vector;
import java.util.Enumeration;

import java.util.NoSuchElementException;

import java.lang.IllegalStateException;
import java.lang.IndexOutOfBoundsException;

public class VectorAdapter implements HList
{
    protected Vector vector;

    public VectorAdapter(int initialCapacity, int capacityIncrement)
    {
        vector = new Vector(initialCapacity, capacityIncrement);
    }

    public VectorAdapter(int initialCapacity)
    {
        this(initialCapacity, 0);
    }

    public VectorAdapter()
    {
        this(10, 0);
    }

    public VectorAdapter(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        vector = new Vector(c.size(), 0);
        this.addAll(c);
    }

    public void copyInto(Object[] anArray)
    {}

    public void trimToSize()
    {}

    public void ensureCapacity(int minCapacity)
    {}

    public void setSize(int newSize)
    {}

    public int capacity()
    {}

    public int size()
    {}

    public boolean isEmpty()
    {}

    public HEnumeration elements()
    {}

    public boolean contains(Object elem)
    {}

    public int indexOf(Object elem)
    {}

    public int indexOf(Object elem, int index)
    {}

    public int lastIndexOf(Object elem)
    {}

    public int lastIndexOf(Object elem, int index)
    {}

    public Object elementAt(int index)
    {}

    public Object firstElement()
    {}

    public Object lastElement()
    {}

    public void setElementAt(Object obj, int index)
    {}

    public void removeElementAt(int index)
    {}

    public void insertElementAt(Object obj, int index)
    {}

    public void addElement(Object obj)
    {}

    public boolean removeElement(Object obj)
    {}

    public void removeAllElements()
    {}

    public Object clone()
    {}

    public Object[] toArray()
    {}

    public Object[] toArray(Object[] a)
    {}

    public Object get(int index)
    {}

    public Object set(int index, Object element)
    {}

    public boolean add(Object o)
    {}

    public boolean remove(Object o)
    {}

    public void add(int index, Object element)
    {}

    public Object remove(int index)
    {}

    public void clear()
    {}

    public boolean containsAll(HCollection c)
    {}

    public boolean addAll(HCollection c)
    {}

    public boolean removeAll(HCollection c)
    {}

    public boolean retainAll(HCollection c)
    {}

    public boolean addAll(int index, HCollection c)
    {}

    public boolean equals(Object o)
    {}

    public int hashCode()
    {}

    public String toString()
    {}

    public HList subList(int fromIndex, int toIndex)
    {}

    protected void removeRange(int fromIndex, int toIndex)
    {}

    public HIterator iterator()
    {}

    public HListIterator listIterator()
    {}

    public HListIterator listIterator(int index)
    {}

    private class EnumerationAdapter implements HEnumeration
    {
        private int cursor = 0;

        public boolean hasMoreElements()
        {
            return cursor < size();
        }

        public Object nextElement()
        {
            if(!hasMoreElements())
                throw new NoSuchElementException();

            return get(cursor++);
        }
    }

    private class IteratorAdapter implements HIterator
    {
        protected int cursor = 0;
        protected int lastRet = -1;

        public boolean hasNext()
        {
            return cursor < size();
        }

        public Object next()
        {
            if(!hasNext())
                throw new NoSuchElementException();

            lastRet = cursor;
            return get(cursor++);
        }

        public void remove()
        {
            if(lastRet == -1)
                throw new IllegalStateException();

            VectorAdapter.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }

    private class ListIteratorAdapter extends IteratorAdapter implements HListIterator
    {
        ListIteratorAdapter()
        {}

        ListIteratorAdapter(int index)
        {
            if(index < 0 || index > size())
                throw new IndexOutOfBoundsException();

            cursor = index;
        }

        public boolean hasPrevious()
        {
            return cursor > 0;
        }

        public Object previous()
        {
            if(!hasPrevious())
                throw new NoSuchElementException();

            lastRet = cursor;
            return get(cursor--);
        }

        public int nextIndex()
        {
            return cursor;
        }

        public int previousIndex()
        {
            return cursor--;
        }

        public void set(Object o)
        {
            if(lastRet == -1)
                throw new IllegalStateException();

            VectorAdapter.this.set(lastRet, o);
        }

        public void add(Object o)
        {
            VectorAdapter.this.add(cursor, o);

            cursor++;
            lastRet = -1;
        }
    }
}
