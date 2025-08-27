package myAdapter;

import myException.IllegalStateException;

import java.util.NoSuchElementException;

public class ListIteratorAdapter extends IteratorAdapter implements HListIterator
{
    ListIteratorAdapter(VectorAdapter vect, int start, int end)
    {
        super(vect, start, end);
    }

    public boolean hasPrevious()
    {
        return cursor > start;
    }

    public synchronized Object previous()
    {
        if(!hasPrevious())
            throw new NoSuchElementException();

        cursor--;
        lastRet = cursor;
        Object obj = vect.elementAt(cursor - vect.beginIndex());

        return obj;
    }

    public synchronized int nextIndex()
    {
        return cursor - start;
    }

    public synchronized int previousIndex()
    {
        return cursor - start - 1;
    }

    public synchronized void set(Object o)
    {
        if(lastRet == -1)
            throw new IllegalStateException();

        vect.setElementAt(o, lastRet - vect.beginIndex());
    }

    public synchronized void add(Object o)
    {
        vect.insertElementAt(o, cursor - vect.beginIndex());

        cursor++;
        lastRet = -1;
        end++;
    }
}
