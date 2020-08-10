package cn.thinking.design.pattern.chapter06_Command.macro.command;
/**
 * 宏命令模式
 * @author ken
 * 
 * @date 2017年7月10日 下午6:00:06
 */
public interface Command {
	public void execute();
	/**
	 * 撤销命令
	 * 
	 * @date 2017年7月12日 上午10:41:34
	 */
	public void undo();
}
