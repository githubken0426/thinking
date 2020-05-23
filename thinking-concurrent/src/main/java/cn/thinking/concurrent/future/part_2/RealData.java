package cn.thinking.concurrent.future.part_2;

import java.util.concurrent.TimeUnit;

public class RealData implements Data {
	String data;
	public RealData(String data){
		try {
			//利用sleep方法来表示RealData构造过程是非常缓慢的
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data=data;
	}

	@Override
	public String getResult(){
		return data;
	}

}
