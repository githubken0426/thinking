package cn.thinking.design.pattern.chapter06_Command.advanced.command;

import cn.thinking.design.pattern.chapter06_Command.advanced.receiver.ChargeDoor;

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
