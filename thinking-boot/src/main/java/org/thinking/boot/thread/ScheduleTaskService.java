package org.thinking.boot.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTaskService {
	private static SimpleDateFormat formate = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void test() {
		System.out.println("ScheduleTaskService:" + formate.format(new Date()));
	}
}
