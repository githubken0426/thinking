package cn.thinking.design.pattern.chapter09_Iterator.exzample;

import java.util.ArrayList;
import java.util.List;

/**
 * 煎饼屋菜单
 * 
 * @ClassName: PancakeHouseMenu
 * @Description:
 * @author ken
 * @date 2017年9月4日 下午5:45:24
 */
public class PancakeHouseMenu {
	List<MenuItem> menuItems;

	public PancakeHouseMenu() {
		menuItems = new ArrayList<MenuItem>();
		addItem("KB~s", "Pancakes with scrambled eggs", true, 2.99);
		addItem("Regular", "Pancakes with fried eggs,sausage", false, 2.99);
		addItem("Blueberry Pancakes", "Pancakes with scrambled eggs", true, 3.49);
		addItem("Waffiles", "Waffiles,with your choice of blueberries", true, 3.59);
	}

	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem item = new MenuItem(name, description, vegetarian, price);
		menuItems.add(item);
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
}
