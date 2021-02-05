package org.thinking.volume12.exception.chapter6.rethorw;

public class ReThorwing {
	public static void main(String[] args) {
		 try {
		 		g();
		 } catch (Exception e) {
		 		e.printStackTrace(System.out);
		 }

		try {
			h();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static void f() throws Exception {
		throw new Exception("Throw from f();");
	}

	public static void g() throws Exception {
		try {
			f();
		} catch (Exception e) {
			System.out.println("g():e.printStackTrace():");
			e.printStackTrace(System.out);
			throw e;
		}
	}

	/**
	 * 重抛异常会把异常抛到上级异常处理， try后续 catch子句将被忽略，异常对象的所有信息都会被保存。
	 * 
	 * @throws Exception
	 * @throws           
	 * @date 2018年8月31日 下午2:53:44
	 */
	public static void h() throws Exception {
		try {
			f();
		} catch (Exception e) {
			System.out.println("h():e.printStackTrace():");
			e.printStackTrace(System.out);
			/**
			 * fillInStackTrace()方法返回一个Throwable对象 抛出的异常点从fillInStackTrace()方法调用点开始。
			 */
			System.out.println("h():e.fillInStackTrace():");
			throw (Exception) e.fillInStackTrace();
		}
	}

}
