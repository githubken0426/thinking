package designPattern.headfirst.chapter_06Command.macro.command;

import designPattern.headfirst.chapter_06Command.macro.receiver.Light;

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
