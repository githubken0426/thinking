package cn.thinking.design.pattern.chapter09_Iterator.theAnswer;

import cn.thinking.design.pattern.chapter09_Iterator.exzample.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PancakeHouseMenu {
    static List<MenuItem> menuItems = new ArrayList<MenuItem>();

    static {
        for (int i = 0; i < 10; i++) {
            MenuItem item = new MenuItem("CakeHose A" + i, "M" + i, true, 2 * i);
            menuItems.add(item);
        }
    }

    public Iterator<MenuItem> createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }
}
