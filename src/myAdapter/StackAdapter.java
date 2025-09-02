package myAdapter;

import java.util.EmptyStackException;

/**
 * The <code>Stack</code> class represents a last-in-first-out
 * (LIFO) stack of objects. It extends class <code>Vector</code> with five
 * operations that allow a vector to be treated as a stack. The usual
 * <code>push</code> and <code>pop</code> operations are provided, as well as a
 * method to <code>peek</code> at the top item on the stack, a method to test
 * for whether the stack is <code>empty</code>, and a method to <code>search</code>
 * the stack for an item and discover how far it is from the top.
 * <p>
 * When a stack is first created, it contains no items.
 *
 * @author   Zoppolato Tommaso Vittorio (matricola: 2101844)
 * @see      VectorAdapter
 */
public class StackAdapter extends VectorAdapter
{
    /**
     * Creates an empty Stack.
     */
    public StackAdapter()
    {
        super();
    }

    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * <blockquote><pre>
     * addElement(item)</pre></blockquote>
     *
     * @param   item   the item to be pushed onto this stack.
     * @return  the <code>item</code> argument.
     * @see     VectorAdapter#addElement
     */
    public Object push(Object item)
    {
        addElement(item);
        return item;
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return     The object at the top of this stack (the last item
     *             of the <code>Vector</code> object).
     * @exception  EmptyStackException  if this stack is empty.
     */
    public synchronized Object pop()
    {
        if(isEmpty())
            throw new EmptyStackException();

        return remove(size() - 1);
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return     the object at the top of this stack (the last item
     *             of the <code>Vector</code> object).
     * @exception  EmptyStackException  if this stack is empty.
     */
    public synchronized Object peek()
    {
        if(isEmpty())
            throw new EmptyStackException();

        return lastElement();
    }

    /**
     * Tests if this stack is empty.
     *
     * @return  <code>true</code> if and only if this stack contains
     *          no items; <code>false</code> otherwise.
     */
    public boolean empty()
    {
        return size() == 0;
    }

    /**
     * Returns the 1-based position where an object is on this stack.
     * If the object <code>o</code> occurs as an item in this stack, this
     * method returns the distance from the top of the stack of the
     * occurrence nearest the top of the stack; the topmost item on the
     * stack is considered to be at distance <code>1</code>. The <code>equals</code>
     * method is used to compare <code>o</code> to the
     * items in this stack.
     *
     * @param   o   the desired object.
     * @return  the 1-based position from the top of the stack where
     *          the object is located; the return value <code>-1</code>
     *          indicates that the object is not on the stack.
     */
    public synchronized int search(Object o)
    {
        int index = lastIndexOf(o);

        return index != -1 ? size() - index : -1;
    }
}
