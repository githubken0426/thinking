package cn.thinking.design.pattern.chapter06_Command.advanced.command;

import cn.thinking.design.pattern.chapter06_Command.advanced.receiver.ChargeDoor;

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
