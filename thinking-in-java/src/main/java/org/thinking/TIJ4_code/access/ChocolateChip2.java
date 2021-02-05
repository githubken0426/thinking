package org.thinking.TIJ4_code.access;

//: access/ChocolateChip2.java
import org.thinking.TIJ4_code.access.cookie2.*;

public class ChocolateChip2 extends Cookie {
	public ChocolateChip2() {
		System.out.println("ChocolateChip2 constructor");
	}

	public void chomp() {
		bite();
	} // Protected method

	public static void main(String[] args) {
		ChocolateChip2 x = new ChocolateChip2();
		x.chomp();
	}
} /*
 * Output: Cookie constructor ChocolateChip2 constructor bite
 */// :~
