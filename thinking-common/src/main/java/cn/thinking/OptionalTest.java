package cn.thinking;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Auther: ken.wangTJ
 * @Date: 6/25/2019 17:45
 * @Description:
 */
public class OptionalTest {
	static {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "b");
		System.out.println(map);
	}

	static {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "c");
		System.out.println(map);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<String> lists = Arrays.asList("c", "d", "f");
		lists = null;
		String base64Str = StringUtils.EMPTY;
		Path path = Paths.get("");
		Optional<Path> files = Optional.ofNullable(Paths.get(""));

		System.out.println(files.isPresent());
	}
}
