package cn.thinking.design.pattern.chapter06_Command.macro.command;

import cn.thinking.design.pattern.chapter06_Command.macro.receiver.Light;

public class LightOnCommand implements Command {
	Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.on();
	}

	@Override
	public void undo() {
		light.off();
	}

}
