package myAdapter;

import java.util.Vector;
import java.util.Enumeration;

import java.util.NoSuchElementException;

import java.lang.IllegalStateException;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

public class VectorAdapter implements HList
{
    protected Vector vec;

    public VectorAdapter(int initialCapacity, int capacityIncrement) // YES
    {
        if(initialCapacity < 0)
            throw new IllegalArgumentException();

        vec = new Vector(initialCapacity, capacityIncrement);
    }

    public VectorAdapter(int initialCapacity) // YES
    {
        this(initialCapacity, 0);
    }

    public VectorAdapter() // YES
    {
        this(10);
    }

    public VectorAdapter(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        this(c.size(), 0);
        addAll(c);
    }

    public void copyInto(Object[] anArray) // YES
    {
        if(anArray == null)
            throw new NullPointerException();

        if(anArray.length < vec.size())
            throw new IndexOutOfBoundsException();

        System.arraycopy(vec.toArray(), 0, anArray, 0, vec.size());
    }

    public void trimToSize() // YES
    {
        vec.trimToSize();
    }

    public void ensureCapacity(int minCapacity) // YES
    {
        vec.ensureCapacity(minCapacity);
    }

    public void setSize(int newSize) // YES
    {
        vec.setSize(newSize);
    }

    public int capacity() // YES
    {
        return vec.capacity();
    }

    public int size() // YES
    {
        return vec.size();
    }

    public boolean isEmpty() // YES
    {
        return size() == 0;
    }

    public HEnumeration elements() // YES
    {
        return new EnumerationAdapter();
    }

    // TO CONTINUE, CHECK FROM HERE THE NEXT FIVE / SIX

    public boolean contains(Object elem) // YES
    {
        return indexOf(elem) != -1;
    }

    public int indexOf(Object elem) // YES
    {
        return indexOf(elem, 0);
    }

    public int indexOf(Object elem, int index) // YES
    {
        if(index < 0)
            throw new ArrayIndexOutOfBoundsException();

        if(index > size())
            return -1;

        if(elem == null)
        {
            for(int i = index; i < size(); i++)
                if(elementAt(i) == null)
                    return i;
        {
        else
        {
            for(int i = index; i < size(); i++)
                if(elem.equals(elementAt(i)))
                    return i;
        }

        return -1;
    }

    public int lastIndexOf(Object elem) // YES
    {
        return lastIndexOf(elem, size()--);
    }

    public int lastIndexOf(Object elem, int index) // YES
    {
        if(index >= size())
            throw new ArrayIndexOutOfBoundsException();

        if(index < 0)
            return -1;

        if(elem == null)
        {
            for(int i = index; i >= 0; i--)
                if(elementAt(i) == null)
                    return i;
        }
        else
        {
            for(int i = index; i >= 0; i--)
                if(elem.equals(elementAt(i)))
                    return i;
        }

        return -1;
    }

    public Object elementAt(int index) // YES
    {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        return vec.elementAt(index);
    }

    public Object firstElement() // YES
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return vec.elementAt(0);
    }

    public Object lastElement() // YES
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return vec.elementAt(size()--);
    }

    public void setElementAt(Object obj, int index) // YES
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.setElementAt(obj, index);
    }

    public void removeElementAt(int index) // YES
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.removeElementAt(index);
    }

    public void insertElementAt(Object obj, int index) // YES
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.
    }

    public void addElement(Object obj) // YES
    {}

    public boolean removeElement(Object obj) // YES
    {}

    public void removeAllElements() // YES
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

    public boolean equals(Object o) // YES
    {}

    public int hashCode() // YES
    {}

    public String toString() // YES
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

            return elementAt(cursor++);
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
            return elementAt(cursor++);
        }

        public void remove()
        {
            if(lastRet == -1)
                throw new IllegalStateException();

            VectorAdapter.this.removeElementAt(lastRet);
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
            return elementAt(cursor--);
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

            VectorAdapter.this.setElementAt(lastRet, o);
        }

        public void add(Object o)
        {
            VectorAdapter.this.addElementAt(cursor, o);

            cursor++;
            lastRet = -1;
        }
    }
}
