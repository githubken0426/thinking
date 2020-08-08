package designPattern.headfirst.chapter_06Command.macro.command;

import designPattern.headfirst.chapter_06Command.macro.receiver.ChargeDoor;

public class ChargeDoorOpenCommand implements Command {
	ChargeDoor door;
	public ChargeDoorOpenCommand(ChargeDoor door){
		this.door=door;
	}
	@Override
	public void execute() {
		door.up();
	}
	@Override
	public void undo() {
		door.down();
	}
}
