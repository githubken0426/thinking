package cn.thinking.design.pattern.chapter_03Decorator.theAnswer;

import java.io.FilterInputStream;
import java.io.InputStream;

public class LowercaseInputStream extends FilterInputStream {
	public LowercaseInputStream(InputStream in) {
		super(in);
	}
	
}
