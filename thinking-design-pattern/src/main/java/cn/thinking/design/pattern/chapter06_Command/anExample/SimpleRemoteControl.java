package cn.thinking.design.pattern.chapter06_Command.anExample;

import cn.thinking.design.pattern.chapter06_Command.anExample.command.Command;
/**
 * 调用者invoker
 * @author ken
 * 
 * @date 2017年7月12日 上午9:39:54
 */
public class SimpleRemoteControl {
	Command command;
	
	public SimpleRemoteControl(){}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void buttonWasPressed(){
		command.execute();
	}
}
