package org.thinking.TIJ4_code.generics;

//: generics/LimitsOfInference.java
import org.thinking.TIJ4_code.typeinfo.pets.*;
import java.util.*;

public class LimitsOfInference {
	static void f(Map<Person, List<? extends Pet>> petPeople) {
	}

	public static void main(String[] args) {
		// f(New.map()); // Does not compile
	}
} /// :~
