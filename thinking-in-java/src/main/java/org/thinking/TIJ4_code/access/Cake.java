//: access/Cake.java
// Accesses a class in a separate compilation unit.
package org.thinking.TIJ4_code.access;

class Cake {
	public static void main(String[] args) {
		Pie x = new Pie();
		x.f();
	}
} /*
	 * Output: Pie.f()
	 */// :~
