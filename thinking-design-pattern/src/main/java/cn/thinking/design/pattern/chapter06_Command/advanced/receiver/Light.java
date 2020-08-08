package cn.thinking.design.pattern.chapter06_Command.advanced.receiver;

public class Light {
	String position;
	public Light(String position){
		this.position=position;
	}
	public void on(){
		System.out.println(position+" on!");
	}
	
	public void off(){
		System.out.println(position+" off!");
	}
}
