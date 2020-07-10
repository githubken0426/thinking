package org.thinking.boot.condition;

public class LinuxCommandService implements CommandService {

	@Override
	public String showCommand() {
		System.out.println(CommandService.super.showCommand());
		return "Linux command";
	}

}
