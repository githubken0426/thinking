package org.thinking.boot.condition;

public class WindowsCommandService implements CommandService {

	@Override
	public String showCommand() {
		System.out.println(CommandService.super.showCommand());
		return "windows command";
	}

}
