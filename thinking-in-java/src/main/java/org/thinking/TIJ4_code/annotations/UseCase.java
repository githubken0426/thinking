//: annotations/UseCase.java
package org.thinking.TIJ4_code.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
	public int id();

	public String description() default "no description";
} /// :~
