package org.thinking.TIJ4_code.generics;

//: generics/ExplicitTypeSpecification.java
import org.thinking.TIJ4_code.typeinfo.pets.*;
import java.util.*;
import org.thinking.TIJ4_code.net.mindview.util.*;

public class ExplicitTypeSpecification {
	static void f(Map<Person, List<Pet>> petPeople) {
	}

	public static void main(String[] args) {
		f(New.<Person, List<Pet>>map());
	}
} /// :~
