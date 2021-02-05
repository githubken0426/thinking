//: enumerated/Competitor.java
// Switching one enum on another.
package org.thinking.TIJ4_code.enumerated;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
} ///:~
