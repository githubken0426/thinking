package designPattern.headfirst.chapter_06Command.anExample.command;

import designPattern.headfirst.chapter_06Command.anExample.receiver.ChargeDoor;

public class ChargeDoorOpenCommand implements Command {
	ChargeDoor door;
	public ChargeDoorOpenCommand(ChargeDoor door){
		this.door=door;
	}
	@Override
	public void execute() {
		door.up();
	}
}
