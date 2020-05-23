package cn.thinking.bit;

public class BitMaskOperation {
	public static void main(String[] args) {
		BitPermission.enable(BitPermission.ALLOW_DELETE);
		BitPermission.enable(BitPermission.ALLOW_INSERT);
		BitPermission.enable(BitPermission.ALLOW_SELECT);
		BitPermission.enable(BitPermission.ALLOW_UPDATE);
		System.out.println(Integer.toBinaryString(BitPermission.getFlag()));
	}
}
