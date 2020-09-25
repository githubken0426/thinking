package cn.thinking.annotation;

import java.lang.annotation.Annotation;

public class MainTest {

	public static void main(String[] args) {
		AnnotationInherited inherited = AnnotaionInitiationSub.class.getAnnotation(AnnotationInherited.class);
		System.out.println(inherited.name());
		AnnotationInheritedMessage message = AnnotaionInitiation.class.getAnnotation(AnnotationInheritedMessage.class);
		for (String msg : message.messages())
			System.out.println(msg);
		//获取注解的注解
		for (Annotation ann : AnnotaionInitiation.class.getAnnotations()) {
			for (Annotation a : ann.annotationType().getAnnotations()) {
				System.out.println(a);
			}
		}
		System.out.println();
		System.out.println("*************************************");
		new AnnotaionInitiationSub();
	}
}
