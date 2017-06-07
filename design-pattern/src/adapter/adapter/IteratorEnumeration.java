package adapter.adapter;

import adapter.adaptee.Enumeration;
import adapter.adaptee.Iterator;

/**
 * @author seongheum.park
 */

public class IteratorEnumeration implements Enumeration {
    Iterator iterator;

    public IteratorEnumeration(Iterator iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public boolean nextElement() {
        return iterator.next();
    }
}
