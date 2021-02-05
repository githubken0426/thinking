//: annotations/database/Uniqueness.java
// Sample of nested annotations
package org.thinking.TIJ4_code.annotations.database;

public @interface Uniqueness {
  Constraints constraints()
    default @Constraints(unique=true);
} ///:~
