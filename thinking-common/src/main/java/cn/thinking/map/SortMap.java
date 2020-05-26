package cn.thinking.map;

import java.util.*;
import java.util.Map.Entry;

/**
 * Map排序
 * 
 * @author ken 2017-6-8 上午11:36:17
 */
class Sorted implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.compareToIgnoreCase(o2);
	}
}

public class SortMap {
	/**
	 * key值忽略大小排序
	 * @param map
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * 2017-6-8 下午12:01:44
	 */
	static Map<String, Object> keySortedMap(Map<String, Object> map)
			throws InstantiationException, IllegalAccessException {
		/*
		 * Map<String, Object> storedMap = new TreeMap<String, Object>(
		 * Sorted.class.newInstance());
		 */
		Map<String, Object> storedMap = new TreeMap<String, Object>(
				new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						return o1.compareToIgnoreCase(o2);
					}
				});
		storedMap.putAll(map);
		return storedMap;
	}
	/**
	 * value忽略大小排序
	 * @param originalMap
	 * @return
	 * 2017-6-8 下午12:01:56
	 */
	static Map<String,Object> valueSortedMap(Map<String, Object> originalMap){
		Map<String, Object> storedMap = new LinkedHashMap<String, Object>();
		List<Entry<String, Object>> entryList = new ArrayList<Entry<String, Object>>(
				originalMap.entrySet());
		Collections.sort(entryList, new Comparator<Entry<String, Object>>() {
			@Override
			public int compare(Entry<String, Object> o1,
					Entry<String, Object> o2) {
				return ((String) o1.getValue()).compareToIgnoreCase((String) o2.getValue());
			}
		});
		Iterator<Entry<String, Object>> set = entryList.iterator();
		while (set.hasNext()) {
			Entry<String, Object> m = set.next();
			storedMap.put(m.getKey(), m.getValue());
		}
		return storedMap;
	}
	/**
	 * 测试方法
	 * @param args
	 * 2017-6-8 下午12:02:13
	 */
	public static void main(String[] args) {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("KFC", "kfc");
		map.put("WNBA", "wnba");
		map.put("NBA", "nba");
		map.put("CBA", "cba");
		map.put("ACB", "dCB");
		try {
			Map<String, Object> resultMap = keySortedMap(map);
			for (Entry<String,Object> en : resultMap.entrySet()) {
				System.out.println("key="+en.getKey()+",value="+en.getValue());
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		System.out.println("value 排序");
		Map<String, Object> resultMap = valueSortedMap(map);
		for (Entry<String,Object> en : resultMap.entrySet()) {
			System.out.println("key="+en.getKey()+",value="+en.getValue());
		}
	}
}
