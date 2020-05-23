package cn.thinking.commons;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Auther: kun.f.wang
 * @Date: 2/12/2019 14:42
 * @Description:
 */
public class LocalDateTest {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());

        Duration duration = Duration.between(LocalDateTime.now(),LocalDateTime.parse("2019-02-12T14:48:42.477"));
        //<=
        System.out.println(duration.toMillis()/1000);
    }
}
