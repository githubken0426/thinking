package designPattern.headfirst.chapter_06Command.macro.receiver;
/**
 * 接收者
 * @author ken
 * 
 * @date 2017年7月12日 上午9:41:37
 */
public class ChargeDoor {
	String position;
	public ChargeDoor(String position) {
		this.position=position;
	}

	Light light;
	
	public void setLight(Light light) {
		this.light = light;
	}

	public void up() {
		System.out.println(position+" open!");
	}

	public void down() {
		System.out.println(position+" close!");
	}

	public void lighOn() {
		light.on();
	}

	public void lightOff() {
		light.off();
	}

}
