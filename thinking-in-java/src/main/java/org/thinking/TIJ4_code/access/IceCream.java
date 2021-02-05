//: access/IceCream.java
// Demonstrates "private" keyword.
package org.thinking.TIJ4_code.access;

class Sundae {
	private Sundae() {
	}

	static Sundae makeASundae() {
		return new Sundae();
	}
}

public class IceCream {
	public static void main(String[] args) {
		// ! Sundae x = new Sundae();
		Sundae x = Sundae.makeASundae();
	}
} /// :~
