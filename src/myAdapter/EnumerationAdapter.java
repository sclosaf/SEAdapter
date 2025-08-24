package myAdapter;

import java.util.NoSuchElementException;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;

public class EnumerationAdapter implements HEnumeration
{
    private int cursor = 0;
    private final int end;
    private VectorAdapter vect;

    public EnumerationAdapter(VectorAdapter vect)
    {
        this(vect, vect.beginIndex(), vect.beginIndex() + vect.size());
    }

    public EnumerationAdapter(VectorAdapter vect, int start, int end)
    {
        if(vect == null)
            throw new NullPointerException();

        if(start > end)
            throw new IllegalArgumentException();

        if(start < vect.beginIndex() || end > vect.beginIndex() + vect.size())
            throw new IndexOutOfBoundsException();

        this.vect = vect;
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

        Object obj = vect.elementAt(cursor - vect.beginIndex());
        cursor++;

        return obj;
    }
}
