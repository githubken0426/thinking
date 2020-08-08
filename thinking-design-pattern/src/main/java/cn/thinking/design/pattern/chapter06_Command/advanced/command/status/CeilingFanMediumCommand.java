package cn.thinking.design.pattern.chapter06_Command.advanced.command.status;

import cn.thinking.design.pattern.chapter06_Command.advanced.command.Command;
import cn.thinking.design.pattern.chapter06_Command.advanced.receiver.CeilingFan;

public class CeilingFanMediumCommand implements Command {
	CeilingFan ceilingFan;
	public CeilingFanMediumCommand(CeilingFan ceilingFan){
		this.ceilingFan=ceilingFan;
	}
	int prevSpeed;
	
	@Override
	public void execute() {
		prevSpeed=ceilingFan.getSpeed();
		ceilingFan.medium();
	}

	@Override
	public void undo() {
		if(prevSpeed==CeilingFan.HIGH)
			ceilingFan.high();
		else if(prevSpeed==CeilingFan.MEDIUM)
			ceilingFan.medium();
		else if(prevSpeed==CeilingFan.LOW)
			ceilingFan.low();
		else if(prevSpeed==CeilingFan.OFF)
			ceilingFan.off();
	}

}
