package cn.thinking.modifier._abstract;

public interface Interfacable {
	public static final int a = 10;

	public void open();

	public abstract void alarm();

	public default void testDefault() {
		System.out.println("default method, params a:" + a);
	}
}
