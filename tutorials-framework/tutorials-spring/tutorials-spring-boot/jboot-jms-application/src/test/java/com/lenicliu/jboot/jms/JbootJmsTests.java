package com.lenicliu.jboot.jms;

import javax.jms.JMSException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class JbootJmsTests {

	@Autowired
	private Producer producer;

	@Test
	public void testSendMessage() throws InterruptedException, JMSException {
		Assert.assertEquals("Hey, lenicliu: Could you please email to customers?", producer.sendMessage("Could you please email to customers?"));
	}
}