//: annotations/Testable.java
package org.thinking.TIJ4_code.annotations;

import org.thinking.TIJ4_code.net.mindview.atunit.*;

public class Testable {
	public void execute() {
		System.out.println("Executing..");
	}

	@Test
	void testExecute() {
		execute();
	}
} /// :~
