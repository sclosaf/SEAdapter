package myAdapter;

public interface HListIterator extends HIterator
{
    boolean hasPrevious();

    Object previous();

    int nextIndex();

    int previousIndex();

    void set(Object o);

    void add(Object o);
}
