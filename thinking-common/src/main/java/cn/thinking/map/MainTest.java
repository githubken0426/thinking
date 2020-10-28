package cn.thinking.map;

import java.util.HashMap;
import java.util.Map;

public class MainTest {

	public static void main(String[] args) {
		Map<String, String> mapString = new HashMap<>(16, 0.75f);
		for (int i = 0; i < 6; i++)
			mapString.put(i + "", "A_" + i);
		
		Map<MapKey, String> map = new HashMap<>();
		for (int i = 0; i < 6; i++)
			map.put(new MapKey(i + ""), "A_" + i);
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
