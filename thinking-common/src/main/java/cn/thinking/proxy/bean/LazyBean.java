package cn.thinking.proxy.bean;

public class LazyBean {
	private String name;
	private int age;
	private PropertyBean lazyloader;
	private PropertyBean dispatcher;

	public LazyBean(String name, int age, PropertyBean lazyloader, PropertyBean dispatcher) {
		this.name = name;
		this.age = age;
		this.lazyloader = lazyloader;
		this.dispatcher = dispatcher;
	}

	/**
	 * 使用cglib进行懒加载 对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
	 * 在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了
	 * （在CGLib的实现中只要去访问该对象内属性的getter方法，就会自动触发代理类回调）
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public PropertyBean getLazyloader() {
		return lazyloader;
	}

	public void setLazyloader(PropertyBean lazyloader) {
		this.lazyloader = lazyloader;
	}

	public PropertyBean getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(PropertyBean dispatcher) {
		this.dispatcher = dispatcher;
	}

}
