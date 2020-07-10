package org.thinking.boot.condition;

import org.springframework.stereotype.Service;

@Service
public interface CommandService {
	default String showCommand() {
		return "CommandService default";
	}
}
