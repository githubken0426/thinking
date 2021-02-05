package org.thinking.volume12.exception.chapter6.chain;

public class MainTest {
	public static void main(String[] args) {
		DynamicFields df = new DynamicFields(1);
		System.out.println(df);
		try {
			df.setField("key_d", " value_d");
			df.setField("number1", 47);
			df.setField("number2", null);
			System.out.println(df);
		} catch (DynamicFieldException e) {
			// printStackTrace()自动调用getCause();
			e.printStackTrace();
		}
	}
}
