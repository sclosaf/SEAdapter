package myAdapter;

import myAdapter.IllegalStateException;

import java.util.Vector;
import java.util.Enumeration;

import java.util.NoSuchElementException; // YES

import java.lang.NullPointerException; // YES
import java.lang.IndexOutOfBoundsException; // YES
import java.lang.ArrayIndexOutOfBoundsException; // YES
import java.lang.IllegalArgumentException; // YES

/*
 * Implemento in java micro
 * Interfaccia 1.4.2
 * No @Override
 * CONTROLLARE ECCEZIONI MANCANTI
 * RICONTROLL tutto
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
        this(c.size(), 0);
        addAll(c);
    }

    public void copyInto(Object[] anArray) // YES
    {
        if(anArray == null)
            throw new NullPointerException();

        if(anArray.length < size())
            throw new IndexOutOfBoundsException();

        System.arraycopy(vec.toArray(), beginIndex(), anArray, 0, size());
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
        return new EnumerationAdapter(beginIndex(), beginIndex() + size());
    }

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
            throw new IndexOutOfBoundsException();

        if(index > size())
            return -1;

        if(elem == null)
            for(int i = beginIndex() + index; i < beginIndex() + size(); i++)
                if(elementAt(i) == null)
                    return i - beginIndex();
        else
            for(i = beginIndex() + index; i < beginIndex() + size(); i++)
                if(elem.equals(elementAt(i)))
                    return i - beginIndex();

        return -1;
    }

    public int lastIndexOf(Object elem) // YES
    {
        return lastIndexOf(elem, size() - 1);
    }

    public int lastIndexOf(Object elem, int index) // YES
    {
        if(index >= size())
            throw new IndexOutOfBoundsException();

        if(index < 0)
            return -1;

        if(elem == null)
        {
            for(int i = beginIndex() + index; i >= beginIndex(); i--)
                if(elementAt(i) == null)
                    return i - beginIndex();
        }
        else
        {
            for(int i = beginIndex() + index; i >= beginIndex(); i--)
                if(elem.equals(elementAt(i)))
                    return i - beginIndex();
        }

        return -1;
    }

    // CONTINUARE ZERO BASED DA QUA

    public Object elementAt(int index) // YES
    {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        return vec.elementAt(beginIndex() + index);
    }

    public Object firstElement() // YES
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return vec.elementAt(beginIndex());
    }

    public Object lastElement() // YES
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return vec.elementAt(beginIndex() + size() - 1);
    }

    public void setElementAt(Object obj, int index) // YES
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.setElementAt(obj, beginIndex() + index);
    }

    public void removeElementAt(int index) // YES
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.removeElementAt(beginIndex() + index);
        setSize(size() - 1);
    }

    public void insertElementAt(Object obj, int index) // YES
    {
        if(index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        vec.insertElementAt(obj, beginIndex() + index);
        setSize(size() + 1);
    }

    public void addElement(Object obj) // YES
    {
        addElementAt(obj, beginIndex() + size());
    }

    public boolean removeElement(Object obj) // YES
    {
        int index = indexOf(obj);

        if(index == -1)
            return false;

        removeElementAt(index);
        return true;
    }

    public void removeAllElements() // YES
    {
        for(int i = size() - 1; i >= 0; i--)
            vec.removeElementAt(beginIndex() + i);

        setSize(0);
    }

    public Object clone()
    {
        VectorAdapter cloned = new VectorAdapter(size());

        HIterator iter = iterator();

        while(iter.hasNext())
            cloned.addElement(iter.next());

        return cloned;
    }

    public Object[] toArray()
    {
        Object[] arr = new Object[size()];
        System.arraycopy(vec.toArray(), beginIndex(), arr, 0, size());
        return arr;
    }

    public Object[] toArray(Object[] a)
    {
        if(a == null)
            throw new NullPointerException();

        if(a.length < size())
            a = new Object[size()];

        System.arraycopy(vec.toArray(), beginIndex(), a, 0, size());

        if (a.length > size())
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
        addElement(o);
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

        Object removed = elementAt(index);
        removeElementAt(index);
        return removed;
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

        for(int i = 0; i < c.size(); i++)
            insertElementAt(iter.next(), index + i);

        return true;
    }

    public boolean equals(Object o) // YES
    {
        if(o == this) return true;

        if(!(o instanceof HList)) return false;

        if(this.size() != o.size()) return false;

        HIterator it1 = this.listIterator();
        HIterator it2 = o.listIterator();

        while(it1.hasNext() && it2.hasNext())
        {
            Object o1 = it1.next();
            Object o2 = it2.next();

            if(!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }

        return true;
    }

    public int hashCode() // YES
    {
        return vec.hashCode();
    }

    public String toString() // YES
    {
        return vec.toString();
    }

    public HList subList(int fromIndex, int toIndex)
    {
        if(fromIndex > toIndex)
            throw new IllegalArgumentException();

        if(fromIndex < 0 || toIndex > size())
            throw new IndexOutOfBoundsException();

        return new SubListAdapter(beginIndex() + fromIndex, beginIndex() + toIndex);
    }

    protected void removeRange(int fromIndex, int toIndex)
    {
        subList(fromIndex, toIndex).clear();
    }

    public HIterator iterator()
    {
        return new IteratorAdapter(beginIndex(), beginIndex() + size());
    }

    public HListIterator listIterator()
    {
        return new ListIteratorAdapter(beginIndex(), beginIndex() + size());
    }

    public HListIterator listIterator(int index)
    {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        return new ListIteratorAdapter(beginIndex() + index, beginIndex() + size());
    }

    protected int beginIndex()
    {
        return 0;
    }

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
            if(start > end)
                throw new IllegalArgumentException();

            if(start < 0 || end > size())
                throw new IndexOutOfBoundsException();

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
        protected int start;
        protected int end;

        public IteratorAdapter(int start, int end)
        {
            if(start > end)
                throw new IllegalArgumentException();

            if(start < 0 || end > size())
                throw new IndexOutOfBoundsException();

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
        public ListIteratorAdapter(int start, int end)
        {
            super(start, end);
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

            VectorAdapter.this.setElementAt(o, lastRet);
        }

        public void add(Object o)
        {
            VectorAdapter.this.insertElementAt(o, cursor);

            cursor++;
            lastRet = -1;
            end++;
        }
    }

    private class SubListAdapter extends VectorAdapter
    {
        private final int offset;
        private int subListSize;

        public SubListAdapter(int fromIndex, int toIndex)
        {
            if(fromIndex > toIndex)
                throw new IllegalArgumentException();

            if(fromIndex < 0 || toIndex > size())
                throw new IndexOutOfBoundsException();

            this.offset = fromIndex;
            this.subListSize = toIndex - fromIndex;
        }

        public void setSize(int newSize)
        {
            if(newSize < 0)
                throw new ArrayIndexOutOfBoundsException();

            subListSize = newSize;
        }

        public int size()
        {
            return subListSize;
        }

        public Object clone()
        {
            SubListAdapter cloned = new SubListAdapter(offset, offset + size());

            HIterator iter = iterator();

            while(iter.hasNext())
                cloned.addElement(iter.next());

            return cloned;
        }

        protected int beginIndex()
        {
            return offset;
        }
    }
}
