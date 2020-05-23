package entity.initialize;

public class DesetEagle extends Gun {
	public static int bullet = 15;
	static {
		System.out.println("Static block! DesetEagle==沙漠之鹰");
	}

	public DesetEagle(int bullet) {
		super(bullet);
	}

}
