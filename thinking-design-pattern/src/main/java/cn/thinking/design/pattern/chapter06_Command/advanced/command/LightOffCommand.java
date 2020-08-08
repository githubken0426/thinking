package cn.thinking.design.pattern.chapter06_Command.advanced.command;

import cn.thinking.design.pattern.chapter06_Command.advanced.receiver.Light;

public class LightOffCommand implements Command {
	Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.off();
	}

	@Override
	public void undo() {
		light.on();
	}

}
