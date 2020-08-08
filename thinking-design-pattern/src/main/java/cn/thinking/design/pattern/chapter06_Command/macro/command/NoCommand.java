package designPattern.headfirst.chapter_06Command.macro.command;

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
