package cn.thinking.design.pattern.chapter06_Command.advanced.command;

/**
 * 空对象，不实现任何东西
 * @author ken
 * 
 * @date 2017年7月12日 上午9:55:10
 */
public class NoCommand implements Command {

	@Override
	public void execute() {
		
	}

	@Override
	public void undo() {
		
	}

}
