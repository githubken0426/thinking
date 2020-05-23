package cn.thinking.outmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * args -verbose:gc -Xmx50m -Xms50m
 * 堆是用来存储对象的，当然对象不一定都存在堆里（由于逃逸技术的发展）。
 * 那么堆如果溢出了，一定是不能被杀掉的对象太多了。
 * 模拟 Heap 内存溢出，只要不断创建对象并保持有引用存在即可。
 * @author ken
 * 2016-12-9 上午11:05:54
 */
public class HeapOverFlow {
	public static class HeapObject{
		
	}
	public static void main(String[] args) {
		List<HeapObject> list=new ArrayList<HeapObject>();
		while(true){
			list.add(new HeapObject());
		}
	}
}
