package cn.thinking.coseable;

public class AutoCloseableImpl implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println(AutoCloseableImpl.class.getSimpleName() + ".close()");
	}
}
