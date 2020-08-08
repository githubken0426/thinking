package designPattern.headfirst.chapter_06Command.macro.command;

import designPattern.headfirst.chapter_06Command.macro.receiver.ChargeDoor;

public class ChargeDoorDownCommand implements Command {
	ChargeDoor door;
	public ChargeDoorDownCommand(ChargeDoor door){
		this.door=door;
	}
	@Override
	public void execute() {
		door.down();
	}
	@Override
	public void undo() {
		door.up();
	}
}
