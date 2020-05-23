package cn.thinking.design.pattern.chapter09_Iterator.theAnswer;

import cn.thinking.design.pattern.chapter09_Iterator.exzample.MenuItem;

import java.util.Iterator;

public class DinnerMenu {
    static MenuItem[] menuItems = new MenuItem[10];

    static {
        for (int i = 0; i < 10; i++) {
            MenuItem item = new MenuItem("Dinner A" + i, "M" + i, true, 2 * i);
            menuItems[i] = item;
        }
    }

    public Iterator<MenuItem> createIterator() {
        return new DinnerMenuIterator(menuItems);
    }

}