package cn.thinking.callback.synchronize;

public class SyncMain {
	public static void main(String[] args) {
		new Student("jack").callBack(123.23,325.20);
		
		new Seller("Seller").callBack(1203.23,4325.26);
	}
}
