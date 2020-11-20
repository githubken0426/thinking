package cn.thinking.initialize;

import entity.initialize.Gun;
import entity.initialize.Rifle;

public class GunMain {
	public static void main(String[] args) {
		try {
			// 会执行静态块代码(不会执行构造函数)
			Class.forName("entity.initialize.Gun");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("**********");
		Gun gun = new Rifle(16);
	}
}
