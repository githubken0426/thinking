package cn.thinking.design.pattern.chapter06_Command.anExample.receiver;
/**
 * 接收者
 * @author ken
 * 
 * @date 2017年7月12日 上午9:41:37
 */
public class ChargeDoor {
	Light light;
	
	public void setLight(Light light) {
		this.light = light;
	}

	public void up() {
		System.out.println("door up");
	}

	public void down() {
		System.out.println("door down");
	}

	public void lighOn() {
		light.on();
	}

	public void lightOff() {
		light.off();
	}

}
