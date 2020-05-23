package cn.thinking.callback.asynchronous;

public class AsyncMain {
	public static void main(String[] args) {
		//asynchronous call back
		new Student("jack").callBack(123.23, 789.20);
		
		new Seller("ken").callBack(3123.23, 5789.20);
	}
}
