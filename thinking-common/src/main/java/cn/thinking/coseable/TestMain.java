package cn.thinking.coseable;

import java.io.FileInputStream;

import cn.thinking.CommonConfiguration;

public class TestMain {
	public static void main(String[] args) throws Exception {
		try (FileInputStream fileInputA = new FileInputStream(CommonConfiguration.DESKTOP_PATH + "\\async.api.bss.yml");
				FileInputStream fileInputB = new FileInputStream(CommonConfiguration.DESKTOP_PATH + "\\async.api.bss.yml");
				AutoCloseable closeable = new AutoCloseableImpl()) {

		}
	}
}
