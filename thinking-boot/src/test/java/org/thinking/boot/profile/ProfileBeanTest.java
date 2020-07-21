package org.thinking.boot.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProfileConfig.class })
@ActiveProfiles(profiles = { "prod" })
public class ProfileBeanTest {
	@Autowired
	private BeanService beanService;

	@Test
	public void profileTest() {
		String result = beanService.getContent();
		Assert.assertEquals("prod", result);
	}
}
