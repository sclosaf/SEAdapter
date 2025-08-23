package myAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;

public class IteratorAdapter implements HIterator
{
    protected int cursor = 0;
    protected int lastRet = -1;
    protected int start;
    protected int end;
    protected VectorAdapter vect;

    public IteratorAdapter(VectorAdapter vect, int start, int end)
    {
        if(vect == null)
            throw new NullPointerException();

        if(start > end)
            throw new IllegalArgumentException();

        if(start < vect.beginIndex() || end > vect.beginIndex() + vect.size())
            throw new IndexOutOfBoundsException();

        this.vect = vect;
        this.cursor = start;
        this.start = vect.beginIndex();
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
        return vect.elementAt(cursor++);
    }

    public void remove()
    {
        if(lastRet == -1)
            throw new IllegalStateException();

        vect.removeElementAt(lastRet);
        cursor = lastRet;
        lastRet = -1;
        end--;
    }
}
