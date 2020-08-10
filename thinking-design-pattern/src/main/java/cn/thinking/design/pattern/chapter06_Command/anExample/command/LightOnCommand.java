package cn.thinking.design.pattern.chapter06_Command.anExample.command;

import cn.thinking.design.pattern.chapter06_Command.anExample.receiver.Light;

public class LightOnCommand implements Command {
	Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.on();
	}

}
