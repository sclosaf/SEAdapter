package myAdapter;

import java.util.Vector;

import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.ArrayIndexOutOfBoundsException;

public class SubListAdapter extends VectorAdapter
{
    private final int offset;
    private int subListSize;

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

    public synchronized void setSize(int newSize)
    {
        if(newSize < 0)
            throw new ArrayIndexOutOfBoundsException();

        if(newSize > subListSize)
            for(int i = subListSize; i < newSize; i++)
                vec.insertElementAt(null, beginIndex() + subListSize);
        else
            for(int i = subListSize - 1; i >= newSize; i--)
                vec.removeElementAt(beginIndex() + newSize);

        subListSize = newSize;
    }

    public synchronized int size()
    {
        return subListSize;
    }

    public synchronized void removeElementAt(int index)
    {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        vec.removeElementAt(beginIndex() + index);

        subListSize--;
    }

    public synchronized void insertElementAt(Object obj, int index)
    {
        if(index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        vec.insertElementAt(obj, beginIndex() + index);
        subListSize++;
    }

    public synchronized Object clone()
    {
        Vector vector = new Vector(vec.size());

        for(int i = 0; i < vec.size(); i++)
            vector.insertElementAt(vec.elementAt(i), i);

        return new SubListAdapter(vector, beginIndex(), beginIndex() + size());
    }

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

    protected int beginIndex()
    {
        return offset;
    }
}
