package org.thinking.TIJ4_code.generics;

import java.util.*;

//: generics/NonCovariantGenerics.java
//{CompileTimeError} (Won't compile)
public class NonCovariantGenerics {
	// Compile Error: incompatible types:
	List<Fruit> flist = new ArrayList<Apple>();
} // /:~
