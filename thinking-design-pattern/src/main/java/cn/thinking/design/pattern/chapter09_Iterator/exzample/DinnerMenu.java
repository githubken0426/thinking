package cn.thinking.design.pattern.chapter09_Iterator.exzample;

/**
 * 餐厅菜单
 * 
 * @ClassName: DinnerMenu
 * @Description:
 * @author ken
 * @date 2017年9月4日 下午5:55:54
 */
public class DinnerMenu {
	static final int MAX_ITEMS = 6;
	int numberOfItems;
	MenuItem[] menuItems;

	public DinnerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];

	}

	public void addItem(String name, String description, boolean vegetarian, double price) {
		if (numberOfItems > MAX_ITEMS)
			throw new UnsupportedOperationException();
		MenuItem item = new MenuItem(name, description, vegetarian, price);
		menuItems[numberOfItems] = item;
		numberOfItems += 1;
	}

	public MenuItem[] getMenuItems() {
		return menuItems;
	}
}
