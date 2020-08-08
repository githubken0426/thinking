package designPattern.headfirst.chapter_06Command.macro;

import designPattern.headfirst.chapter_06Command.macro.command.Command;
import designPattern.headfirst.chapter_06Command.macro.command.NoCommand;

/**
 * 遥控器
 * 
 * @author ken
 * 
 * @date 2017年7月12日 上午9:51:20
 */
public class RemoteControl {
	Command[] onCommands;
	Command[] offCommands;
	Command unCommand;

	public RemoteControl(int num) {
		onCommands = new Command[num];
		offCommands = new Command[num];
		Command noCommand = new NoCommand();
		for (int i = 0; i < num; i++) {
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
		unCommand=noCommand;
	}
	/**
	 * 
	 * @param slot 开关的位置
	 * @param onCommand 开启命令
	 * @param offCommand 关闭命令
	 * @date 2017年7月12日 上午10:09:07
	 */
	public void setCommand(int slot, Command onCommand, Command offCommand) {
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}

	public void onButtonWasPushed(int slot) {
		onCommands[slot].execute();
		unCommand=onCommands[slot];
	}

	public void offButtonWasPushed(int slot) {
		offCommands[slot].execute();
		unCommand=offCommands[slot];
	}
	public void undoButtonWasPushed(){
		unCommand.undo();
	}
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("____RemoteControl__\n");
		for(int i=0;i<onCommands.length;i++){
			sb.append("[slot");
			sb.append(i);
			sb.append("]");
			sb.append(onCommands[i].getClass().getSimpleName());
			sb.append(" ");
			sb.append(offCommands[i].getClass().getSimpleName());
			sb.append("\n");
		}
		return sb.toString();
	}
}
