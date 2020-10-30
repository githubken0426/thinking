package cn.thinking.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainTest {

	public static void main(String[] args) {
		Map<String, String> mapString = new HashMap<>(16, 0.75f);
		for (int i = 0; i < 6; i++)
			mapString.put(i + "", "A_" + i);
		// jdk 1.7头插法
		MyMap<MapKey, String> mymap = new MyHashMap<>();
		for (int i = 0; i < 6; i++)
			mymap.put(new MapKey(i + ""), "A_" + i);
		// jdk 1.8尾插法
		Map<MapKey, String> map = new HashMap<>(16, 0.75f);
		for (int i = 0; i < 13; i++)
			map.put(new MapKey(i + ""), "A_" + i);
		new LinkedHashMap<String, String>();
		for (int i = 0; i < 10; i++)
			map.put(new MapKey(i + ""), "A_" + i);
		for (int i = 0; i < 50; i++)
			map.put(new MapKey(i + ""), "A_" + i);

		map.put(new MapKey("X"), "x");
		map.put(new MapKey("Y"), "y");
		map.put(new MapKey("Z"), "z");
		System.out.print("end:" + map);
	}

}
