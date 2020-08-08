package cn.thinking.design.pattern.chapter03_Decorator.me;

/**
 * 抽象装饰者
 * 
 * @author ken
 * 2017-6-15 下午02:56:49
 */
public abstract class CondimentDecorator extends Beverage {
	
	public abstract double cost();
	
	public abstract String getDescription();
	
}
