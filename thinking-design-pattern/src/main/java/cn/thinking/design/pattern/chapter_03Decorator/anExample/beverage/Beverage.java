package cn.thinking.design.pattern.chapter_03Decorator.anExample.beverage;

/**
 * 抽象组件
 * 
 * @author ken
 * 2017-6-15 下午02:55:56
 */
public abstract class Beverage {
	public String description="unkone beverage";
	public abstract double cost();
	
	public String getDescription() {
		return description;
	}
}
