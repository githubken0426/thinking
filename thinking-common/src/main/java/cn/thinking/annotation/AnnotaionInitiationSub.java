package cn.thinking.annotation;

public class AnnotaionInitiationSub extends AnnotaionInitiation {
	public AnnotaionInitiationSub() {
		System.out.println(AnnotaionInitiationSub.class.getCanonicalName());

		System.out.println("AnnotaionInitiationSub.class.isAnnotationPresent(AnnotationInherited.class):"
				+ AnnotaionInitiationSub.class.isAnnotationPresent(AnnotationInherited.class));
		System.out.println("AnnotaionInitiationSub.class.isAnnotationPresent(AnnotationInheritedMessage.class):"
				+ AnnotaionInitiationSub.class.isAnnotationPresent(AnnotationInheritedMessage.class));
	}

}
