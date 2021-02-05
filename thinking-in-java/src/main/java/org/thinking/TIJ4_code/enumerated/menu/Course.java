//: enumerated/menu/Course.java
package org.thinking.TIJ4_code.enumerated.menu;

import org.thinking.TIJ4_code.net.mindview.util.*;

public enum Course {
	APPETIZER(Food.Appetizer.class), 
	MAINCOURSE(Food.MainCourse.class), 
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class);
	
	private Food[] values;

	private Course(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}
} /// :~
