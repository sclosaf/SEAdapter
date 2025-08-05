package myAdapter;

import java.util.Vector;
import java.util.Enumeration;

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
        {}

        public Object nextElement()
        {}
    }

    private class IteratorAdapter implements HIterator
    {
        private int cursor = 0;
        private int lastRet = -1;

        public boolean hasNext()
        {}

        public Object next()
        {}

        public void remove()
        {}
    }

    private class ListIteratorAdapter extends IteratorAdapter implements HListIterator
    {
        ListIteratorAdapter()
        {}

        ListIteratorAdapter(int index)
        {}

        boolean hasPrevious()
        {}

        Object previous()
        {}

        int nextIndex()
        {}

        int previousIndex()
        {}

        void set(Object o)
        {}

        void add(Object o)
        {}
    }
}
