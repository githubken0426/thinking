package cn.thinking.proxy.bean;

/**
 * 实体类 没有实现任何接口
 * @author ken 2016-12-7 上午09:37:52
 */
public class Book {
	public String create() {
		System.out.println("调用create();");
		return "返回create";
	}
	public String query() {
		System.out.println("调用query();");
		return "返回query";
	}
	//此方法为了锁定方法返回值，FixedValue接口测试。
	public Integer update(int count) {
		System.out.println("调用update();");
		return count;
	}
	public String delete() {
		System.out.println("调用delete();");
		return "返回delete";
	}
	
	public String del(String value) {
		return value;
	}
	
	public String toString() {
		return "Book: " + getClass();
	}
}
