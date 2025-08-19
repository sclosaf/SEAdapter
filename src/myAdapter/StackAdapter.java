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

    public Object pop()
    {
        if(isEmpty())
            throw new EmptyStackException();

        return remove(size() - 1);
    }

    public Object peek()
    {
        if(isEmpty())
            throw new EmptyStackException();

        return lastElement();
    }

    public boolean empty()
    {
        return size() == 0;
    }

    public int search(Object o)
    {
        int index = lastIndexOf(o);

        return index != -1 ? size() - index : -1;
    }
}
