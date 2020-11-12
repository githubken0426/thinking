package cn.thinking.proxy.imaker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.thinking.proxy.bean.Book;
import org.objectweb.asm.Type;

import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 接口生成器InterfaceMaker InterfaceMaker会动态生成一个接口，该接口包含指定类定义的所有方法
 *
 * @author ken 2016-12-8 下午05:09:48
 */
public class SignatureMakerMain {

	/**
	 * @param name          方法名
	 * @param returnType    返回值类型
	 * @param argumentTypes 参数类型
	 * @param exceptions    异常类型
	 * @date 2017年7月10日 下午1:23:22
	 */
	public static Class<?> getInstance(String name, Type returnType, Type[] argumentTypes, Type[] exceptions) {
		Signature signature = new Signature(name, returnType, argumentTypes);
		InterfaceMaker interfaceMaker = new InterfaceMaker();
		interfaceMaker.add(signature, exceptions);
		Class<?> iface = interfaceMaker.create();
		return iface;
	}

	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class<?> cla = getInstance("insert", Type.INT_TYPE, new Type[] { Type.INT_TYPE, Type.FLOAT_TYPE }, new Type[0]);
		for (Method method : cla.getDeclaredMethods()) {
			System.out.println("新创建的方法:" + method.getName());
		}

		Object object = Enhancer.create(Book.class, new Class[] { cla }, new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
				if (method.getName().equals("insert")) {
					return arg[0];
				}
				return null;
			}
		});
		/**
		 * 必须指定参数类型(顺序和数量)
		 */
		Method targetMethod = object.getClass().getMethod("insert", new Class[] { int.class, Float.TYPE });
		/**
		 * 必须指定参数(顺序、数量、类型要对应上)
		 */
		Object result = targetMethod.invoke(object, new Object[] { 100, 200.1f });
		System.out.println("insert返回值:" + result);
	}
}
