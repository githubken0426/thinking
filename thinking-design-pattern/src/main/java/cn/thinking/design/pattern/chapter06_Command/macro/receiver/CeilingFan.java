package designPattern.headfirst.chapter_06Command.macro.receiver;

/**
 * 天花板风扇
 * 使用状态实现撤销
 * @author ken
 * 
 * @date 2017年7月12日 上午10:51:01
 */
public class CeilingFan {
	public static final int HIGH=3;
	public static final int MEDIUM=2;
	public static final int LOW=1;
	public static final int OFF=0;
	String location;
	int speed;
	
	public CeilingFan(String location){
		this.location=location;
		speed=OFF;
	}
	
	public void high(){
		System.out.println(location+" 高速运行！");
		speed=HIGH;
	}
	
	public void medium(){
		System.out.println(location+" 中速运行！");
		speed=MEDIUM;
	}
	
	public void low(){
		System.out.println(location+" 低速运行！");
		speed=LOW;
	}
	
	public void off(){
		System.out.println(location+" 关闭了！");
		speed=OFF;
	}
	
	public int getSpeed(){
		return speed;
	}
}
