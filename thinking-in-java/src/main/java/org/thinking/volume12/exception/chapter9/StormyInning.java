package org.thinking.volume12.exception.chapter9;

/**
 * Overridden methods may throw only the exceptions specified in their base-class versions, 
 * or exceptions derived from the base-class exceptions.
 * 
 * @author kun.f.wang
 */
public class StormyInning extends Inning implements Storm {
	/**
	 * OK to add new exceptions for constructors, 
	 * but you must deal with the base constructor exceptions.
	 * @throws RainedOutStormException
	 * @throws BaseballException
	 */
	public StormyInning() throws RainedOutStormException, BaseballException {
	}

	public StormyInning(String s) throws FoulBaseballException, BaseballException {
	}

	/**
	 * Regular methods must conform to base class.
	 */
//	 public void walk() throws PopFoul {} //Compile error

	/**
	 *  If the method doesn't already exist in the base class, the exception is OK.
	 */
	public void rainHard() throws RainedOutStormException {
	}

	/**
	 * Inning 和接口 Storm拥有共同方法event()，分别抛出不同异常。 则event()无法区分到底抛出那种异常，所以不能抛出任何异常。
	 * You can choose to not throw any exceptions,even if the base version does。
	 */
	public void event() {
	}
	/**
	 * Interface CANNOT add exceptions to existing methods from the base class
	 */
	// public void event()throws BaseballException,RainedOut{}

	/**
	 *  Overridden methods can throw inherited exceptions
	 */
	public void atBat() throws FoulBaseballException {
		throw new PopFoulBaseballException();
	}

	public static void main(String[] args) {
		try {
			StormyInning si = new StormyInning();
			si.atBat();
		} catch (PopFoulBaseballException e) {
			System.out.println("Pop foul");
		} catch (RainedOutStormException e) {
			System.out.println("Rained out");
		} catch (BaseballException e) {
			System.out.println("Generic baseball exception");
		}
		// Strike not thrown in derived version.
		try {
			// What happens if you up cast?
			Inning i = new StormyInning();
			// You must catch the exceptions from the base-class version of the method
			i.atBat();
		} catch (StrikeBaseballException e) {
			System.out.println("Strike");
		} catch (FoulBaseballException e) {
			System.out.println("Foul");
		} catch (RainedOutStormException e) {
			System.out.println("Rained out");
		} catch (BaseballException e) {
			System.out.println("Generic baseball exception");
		}
	}
}

abstract class Inning {

	public Inning() throws BaseballException {
	}

	/**
	 * 	声明异常，但是没有抛出异常，这种方式可以强制用户捕获override后的event()后增加的异常
	 * Doesn't actually have to throw anything
	 * @throws BaseballException
	 */
	public void event() throws BaseballException {
	}
	
	// Throws no checked exceptions
	public void walk() {
	} 
	
	public abstract void atBat() throws StrikeBaseballException, FoulBaseballException;
}

class BaseballException extends Exception {
	private static final long serialVersionUID = 1L;
}

class FoulBaseballException extends BaseballException {
	private static final long serialVersionUID = 1L;
}

class PopFoulBaseballException extends FoulBaseballException {
	private static final long serialVersionUID = 1L;
}

class StrikeBaseballException extends BaseballException {
	private static final long serialVersionUID = 1L;
}


class StormException extends Exception {
	private static final long serialVersionUID = 1L;
}

class RainedOutStormException extends StormException {
	private static final long serialVersionUID = 1L;
}

interface Storm {
	public void event() throws RainedOutStormException;

	public void rainHard() throws RainedOutStormException;
}
