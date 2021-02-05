//: typeinfo/WildcardClassReferences.java
package org.thinking.TIJ4_code.typeinfo;

public class WildcardClassReferences {
  public static void main(String[] args) {
    Class<?> intClass = int.class;
    intClass = double.class;
  }
} ///:~
