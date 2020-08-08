package designPattern.headfirst.chapter_06Command.anExample.command;

import designPattern.headfirst.chapter_06Command.anExample.receiver.Light;

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
