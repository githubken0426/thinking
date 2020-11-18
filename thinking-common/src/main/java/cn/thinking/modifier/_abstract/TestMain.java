package cn.thinking.modifier._abstract;

public class TestMain {
	public static Interfacable getInterface() {
		return new Interfacable() {

			@Override
			public void open() {
				System.out.println("Interfacable open()");
			}

			@Override
			public void alarm() {
				System.out.println("Interfacable alarm()");
			}

		};
	}
	
	public static Abstractable getAbstract() {
		return new Abstractable() {
			@Override
			public void execute() {
				System.out.println("implements execute()");
			}
		};
	}

	public static void main(String[] args) {
		Abstractable as = getAbstract();
		System.out.println(as.unI);
		System.out.println(as.i);
		as.execute();
		as.close();
		Abstractable.open(10);

		System.out.println();
		Interfacable inf = getInterface();
		System.out.println("Interfacable.a = " + Interfacable.a);
		inf.testDefault();
		inf.alarm();
		inf.open();
	}
}
