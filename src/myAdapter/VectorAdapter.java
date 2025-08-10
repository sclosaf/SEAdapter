package myAdapter;

import java.util.Vector;
import java.util.Enumeration;

import java.util.NoSuchElementException; // YES

import java.lang.NullPointerException; // YES
import java.lang.IllegalStateException; // NO
import java.lang.IndexOutOfBoundsException; // YES
import java.lang.ArrayIndexOutOfBoundsException; // YES
import java.lang.IllegalArgumentException; // YES

/*
 * Implemento in java micro
 * Interfaccia 1.4.2
 * No @Override
 * CONTROLLARE ECCEZIONI MANCANTI
 */

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
        if(newSize < 0)
            throw new ArrayIndexOutOfBoundsException();

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

    public boolean contains(Object elem) // YES
    {
        return indexOf(elem, 0) != -1;
    }

    public int indexOf(Object elem) // YES
    {
        return indexOf(elem, 0);
    }

    public int indexOf(Object elem, int index) // YES
    {
        if(index < 0)
            throw new IndexOutOfBoundsException();

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
            throw new IndexOutOfBoundsException();

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
        setSize()
    }

    public void insertElementAt(Object obj, int index) // YES
    {
        if(index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        vec.insertElementAt(obj, index);
    }

    public void addElement(Object obj) // YES
    {
        vec.addElement(obj);
    }

    public boolean removeElement(Object obj) // YES
    {
        return vec.removeElement(obj);
    }

    public void removeAllElements() // YES
    {
        vec.removeAllElements();
    }

    public Object clone()
    {
        VectorAdapter newVec = VectorAdapter(size());

        HIterator iter = iterator();

        while(iter.hasNext())
            newVec.addElement(iter.next());

        return newVec
    }

    public Object[] toArray()
    {
        Object[] arr = new Object[size()];

        HIterator iter = iterator();

        for(int i = 0; iter.hasNext(); i++)
            arr[i] = iter.next();

        return arr;
    }

    public Object[] toArray(Object[] a)
    {
        if(a == null)
            throw new NullPointerException();

        if(a.length < size())
            a = new Object[size()];

        System.arraycopy(vec, 0, a, 0, size());

        if(a.length > size())
            a[size()] = null;

        return a;
    }

    public Object get(int index)
    {
        return elementAt(index);
    }

    public Object set(int index, Object element)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        Object obj = elementAt(index);
        setElementAt(element, index);

        return obj;
    }

    public boolean add(Object o)
    {
        add(size(), o);
        return true;
    }

    public boolean remove(Object o)
    {
        return removeElement(o);
    }

    public void add(int index, Object element)
    {
        if(index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        insertElementAt(element, index);
    }

    public Object remove(int index)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        removeElementAt(index);
    }

    public void clear()
    {
        removeAllElements();
    }

    public boolean containsAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        HIterator iter = c.iterator();

        while(iter.hasNext())
            if(!contains(iter.next()))
                return false;

        return true;
    }

    public boolean addAll(HCollection c)
    {
        return addAll(size(), c);
    }

    public boolean removeAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

        HIterator iter = c.iterator();

        boolean changed = false;

        while(iterator.hasNext())
        {
            Object elem = iterator.next();

            while(indexOf(elem) != -1)
            {
                remove(indexOf(elem));
                changed = true;
            }
        }

        return changed;
    }

    public boolean retainAll(HCollection c)
    {
        if(c == null)
            throw new NullPointerException();

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

    public boolean addAll(int index, HCollection c)
    {
        if(index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        if(c == null)
            throw new NullPointerException();

        HIterator iter = c.iterator();

        boolean changed = false;

        for(int i = 0; i < c.size(); i++)
        {
            insertElementAt(index + i, c.next())
            changed = true;
        }

        return changed;
    }

    public boolean equals(Object o) // YES
    {
        if(o == this)
            return true;

        if(!(o instanceof HList))
            return false;

        HListIterator it1 = listIterator();
        HListIterator it2 = ((HList) o).listIterator();

        while (it1.hasNext() && it2.hasNext())
        {
            Object o1 = it1.next();
            Object o2 = it2.next();

            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }

        return !(it1.hasNext() || it2.hasNext());
    }

    public int hashCode() // YES
    {
        return vec.hashcode();
    }

    public String toString() // YES
    {
        return vec.toString();
    }

    // TO CONTINUE HERE

    public HList subList(int fromIndex, int toIndex)
    {
        if(fromIndex > toIndex)
            throw new IllegalArgumentException();

        if(fromIndex < 0 || toIndex > size())
            throw new IndexOutOfBoundsException();


    }

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
        private final int end;

        public EnumerationAdapter()
        {
            this(0, size());
        }

        public EnumerationAdapter(int start, int end)
        {
            this.cursor = start;
            this.end = end;
        }

        public boolean hasMoreElements()
        {
            return cursor < end;
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
        protected final int start;
        protected final int end;

        public IteratorAdapter()
        {
            this(0, size());
        }

        public IteratorAdapter(int start)
        {
            this(start, size());
        }

        public IteratorAdapter(int start, int end)
        {
            this.cursor = start;
            this.start = start;
            this.end = end;
        }

        public boolean hasNext()
        {
            return cursor < end;
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
                throw new IllegalStateException(); // DA CAMBIARE

            VectorAdapter.this.removeElementAt(lastRet);
            cursor = lastRet;
            lastRet = -1;
            end--;
        }
    }

    private class ListIteratorAdapter extends IteratorAdapter implements HListIterator
    {
        ListIteratorAdapter()
        {
            super();
        }

        public ListIteratorAdapter(int start)
        {
            super(start, size());
        }

        public ListIteratorAdapter(int start, int end)
        {
            super(start, end);
        }

        ListIteratorAdapter(int index)
        {
            if(index < 0 || index > size())
                throw new IndexOutOfBoundsException();

            cursor = index;
        }

        public boolean hasPrevious()
        {
            return cursor > start;
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
            return cursor - start;
        }

        public int previousIndex()
        {
            return cursor - start - 1;
        }

        public void set(Object o)
        {
            if(lastRet == -1)
                throw new IllegalStateException(); // CAMBIARE

            VectorAdapter.this.setElementAt(lastRet, o);
        }

        public void add(Object o)
        {
            VectorAdapter.this.addElementAt(cursor, o);

            cursor++;
            lastRet = -1;
            end++;
        }
    }

    private class SubListAdapter implements HList
    {
        private final int offset;
        private int subListSize;

        public SubList(int fromIndex, int toIndex)
        {
            this.offset = fromIndex;
            this.subListSize = toIndex - fromIndex;
        }

        public int size()
        {
            return subListSize;
        }

        public boolean isEmpty()
        {
            subLisSize == 0;
        }

        public boolean contains(Object o)
        {
            int index = super.indexOf(o, offset);

            return index < offset + subListSize;
        }

        public HIterator iterator()
        {

        }

        public Object[] toArray()
        {}

        public Object[] toArray(Object[] a)

        public boolean add(Object o)

        public boolean remove(Object o)

        public boolean containsAll(HCollection c)

        public boolean addAll(HCollection c)

        public boolean removeAll(HCollection c)

        public boolean retainAll(HCollection c)

        public void clear()

        public boolean equals(Object o)

        public int hashCode()

        public boolean addAll(int index, HCollection c)

        public Object get(int index)

        public Object set(int index, Object element)

        public void add(int index, Object element)

        public Object remove(int index)

        public int indexOf(Object o)

        public int lastIndexOf(Object o)

        public HListIterator listIterator()

        public HListIterator listIterator(int index)

        public HList subList(int fromIndex, int toIndex)
    }
}
