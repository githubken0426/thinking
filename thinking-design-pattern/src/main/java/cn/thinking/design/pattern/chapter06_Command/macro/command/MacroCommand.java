package designPattern.headfirst.chapter_06Command.macro.command;

/**
 * 宏命令实现
 * @author ken
 * 
 * @date 2017年7月12日 上午11:43:46
 */
public class MacroCommand implements Command {
	Command[] commands;
	public MacroCommand(Command[] commands){
		this.commands=commands;
	}
	
	@Override
	public void execute() {
		for (Command command : commands) {
			command.execute();
		}
	}

	@Override
	public void undo() {
		for (Command command : commands) {
			command.undo();
		}
	}

}
