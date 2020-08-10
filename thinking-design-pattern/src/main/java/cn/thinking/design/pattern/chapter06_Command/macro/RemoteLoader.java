package cn.thinking.design.pattern.chapter06_Command.macro;

import cn.thinking.design.pattern.chapter06_Command.macro.command.*;
import cn.thinking.design.pattern.chapter06_Command.macro.receiver.*;
/**
 * 宏命令
 * @author ken
 * 
 * @date 2017年7月12日 上午11:44:01
 */
public class RemoteLoader {
	public static void main(String[] args) {
		RemoteControl control=new RemoteControl(3);
		
		Light roomLight=new Light("Room Light had");
		Light bathroomLight=new Light("Bathroom Light had");
		ChargeDoor roomDoor=new ChargeDoor("Room Door had");
		ChargeDoor bathroomDoor=new ChargeDoor("Bathroom Door had");
		
		Command ligthOn=new LightOnCommand(roomLight);
		Command ligthOff=new LightOffCommand(bathroomLight);
		
		Command doorOpen=new ChargeDoorOpenCommand(roomDoor);
		Command doorClose=new ChargeDoorDownCommand(bathroomDoor);
		
		Command[] partOn = { ligthOn, doorOpen };
		Command[] partOff = { ligthOff, doorClose };
		
		Command partOnCommand=new MacroCommand(partOn);
		Command partOffCommand=new MacroCommand(partOff);

		control.setCommand(0, partOnCommand, partOffCommand);
		System.out.println(control);
		System.out.println("开启命令");
		control.onButtonWasPushed(0);
		System.out.println("关闭命令");
		control.offButtonWasPushed(0);
		System.out.println("撤销命令");
		//调用undo,关闭的Ligth会再次被打开
		control.undoButtonWasPushed();
	}
}
