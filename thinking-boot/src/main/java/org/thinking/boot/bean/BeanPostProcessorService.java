package org.thinking.boot.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//@Component
//public class BeanPostProcessorService implements BeanPostProcessor {
//
//	@Override
//	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("* BeanPostProcessor's postProcessBeforeInitialization:" + beanName);
//		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
//	}
//
//	@Override
//	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("* BeanPostProcessor's postProcessAfterInitialization:" + beanName);
//		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
//	}
//
//}
