package myAdapter;

import java.util.EmptyStackException;

public class StackAdapter extends VectorAdapter
{
    public StackAdapter()
    {
        super();
    }

    public Object push(Object item)
    {
        addElement(item);
        return item;
    }

    public synchronized Object pop()
    {
        if(isEmpty())
            throw new EmptyStackException();

        return remove(size() - 1);
    }

    public synchronized Object peek()
    {
        if(isEmpty())
            throw new EmptyStackException();

        return lastElement();
    }

    public boolean empty()
    {
        return size() == 0;
    }

    public synchronized int search(Object o)
    {
        int index = lastIndexOf(o);

        return index != -1 ? size() - index : -1;
    }
}
