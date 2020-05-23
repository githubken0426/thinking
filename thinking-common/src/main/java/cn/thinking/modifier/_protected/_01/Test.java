package cn.thinking.modifier._protected._01;


import cn.thinking.modifier._protected._02.Son;

public class Test {
	public static void main(String[] args) {
		Father son=new Son();
		/**
		 * 受保护属性
		 * 同一包中编译正常
		 */
		System.out.println(son.id);
		System.out.println(son.setId(100));
	}
}
