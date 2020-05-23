package cn.thinking.design.pattern.chapter09_Iterator.theAnswer;

import cn.thinking.design.pattern.chapter09_Iterator.exzample.MenuItem;

import java.util.Iterator;

public class Waitress {
    DinnerMenu dinnerMenu;
    PancakeHouseMenu pancakeHouseMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinnerMenu dinnerMenu) {
        this.dinnerMenu = dinnerMenu;
        this.pancakeHouseMenu = pancakeHouseMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> it1 = pancakeHouseMenu.createIterator();
        showIterator(it1);
        Iterator<MenuItem> it2 = dinnerMenu.createIterator();
        showIterator(it2);
    }

    private void showIterator(Iterator<MenuItem> it) {
        while (it.hasNext()) {
            MenuItem item = it.next();
            System.out.println("名称:" + item.getName() + ",价格:" + item.getPrice() + ",描述" + item.getDescription());
        }
    }

    public static void main(String[] args) {
        DinnerMenu dinner = new DinnerMenu();
        PancakeHouseMenu pancake = new PancakeHouseMenu();
        Waitress tress = new Waitress(pancake, dinner);
        tress.printMenu();
    }
}
