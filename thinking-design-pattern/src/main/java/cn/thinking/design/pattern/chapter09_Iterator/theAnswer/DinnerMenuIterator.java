package cn.thinking.design.pattern.chapter09_Iterator.theAnswer;

import cn.thinking.design.pattern.chapter09_Iterator.exzample.MenuItem;

import java.util.Iterator;

public class DinnerMenuIterator implements Iterator<MenuItem> {
    MenuItem[] menuItems;
    int position = 0;

    public DinnerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if (position >= menuItems.length || menuItems[position] == null)
            return false;
        return true;
    }

    @Override
    public MenuItem next() {
        MenuItem item = menuItems[position];
        position++;
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
