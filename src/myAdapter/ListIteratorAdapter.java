package myAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

public class ListIteratorAdapter extends IteratorAdapter implements HListIterator
{
    public ListIteratorAdapter(VectorAdapter vect, int start, int end)
    {
        super(vect, start, end);
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
        Object obj = vect.elementAt(cursor - vect.beginIndex());
        cursor--;

        return obj;
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
            throw new IllegalStateException();

        vect.setElementAt(o, lastRet - vect.beginIndex());
    }

    public void add(Object o)
    {
        vect.insertElementAt(o, cursor - vect.beginIndex());

        cursor++;
        lastRet = -1;
        end++;
    }
}
