package org.thinking.volume12.exception.chapter8.lost;

public class LostMessage {
	public static void main(String[] args) {
		try {
			try {
				LostMessage.f();
			} finally {
				try {
					LostMessage.dispose();
				} catch (HoHumException e) {
					e.printStackTrace();
//					System.out.println(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
//			System.out.println(e);
		}
	}

	private static void f() throws VeryImportantException {
		throw new VeryImportantException();
	}

	private static void dispose() throws HoHumException {
		throw new HoHumException();
	}

}
