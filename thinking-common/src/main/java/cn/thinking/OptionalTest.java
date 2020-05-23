package cn.thinking;



import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: ken.wangTJ
 * @Date: 6/25/2019 17:45
 * @Description:
 */
public class OptionalTest {
    public static void main(String[] args) {
        List<String> lists = Arrays.asList("c", "d", "f");
        lists = null;
        String base64Str = StringUtils.EMPTY;
        Path path = Paths.get("");
        Optional<Path> files=Optional.ofNullable(Paths.get(""));

        System.out.println(files.isPresent());
    }

}
