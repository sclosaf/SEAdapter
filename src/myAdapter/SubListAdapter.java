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

        subListSize = newSize;
    }

    public synchronized int size()
    {
        return subListSize;
    }

    public synchronized Object clone()
    {
        SubListAdapter cloned = new SubListAdapter(vec, offset, offset + size());

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
