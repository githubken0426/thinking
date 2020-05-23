package entity.initialize;

public class Gun {
	public static int bullet;
	static {
		System.out.println("Static block! Gun==》我有" + bullet + "发子弹！");
	}

	public Gun() {
		System.out.println("没有子弹");
	}

	public Gun(int bull) {
		bullet = bull;
		System.out.println("Gun==》我现在真的有" + bullet + "发子弹！");
	}
}
