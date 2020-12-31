package cn.thinking.nio;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class PathTest {

	static final String prefix = "C:\\ec_workspace\\thinking\\thinking-common\\src\\main\\java\\cn\\thinking\\nio\\channel\\";

	public static void main(String[] args) {
		iteratorPaths();
		System.out.println();
		testPath();
	}

	static void testPath() {
		Path absolate = Paths.get("C:\\Windows\\setuperr.log");
		System.out.println(absolate);
		Path relative = Paths.get("C:\\Windows", "setuperr.log");
		System.out.println(relative);
		Path currentDir = Paths.get(".");
		System.out.println("currentDir:" + currentDir.toAbsolutePath());
		Path parentDir = Paths.get("..");
		System.out.println("parentDir.toAbsolutePath():" + parentDir.toAbsolutePath());
		System.out.println("parentDir.normalize():" + parentDir.normalize());

		boolean pathExists = Files.exists(absolate, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
		System.out.println(pathExists);
	}

	static void iteratorPaths() {
		Path path = Paths.get(prefix, "FileChannelTest.java");
		boolean pathExists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
		System.out.println(pathExists);
		Iterator<Path> it = path.iterator();
		while (it.hasNext()) {
			Path p = it.next();
			System.out.println(p.getFileName());
		}
	}
}
