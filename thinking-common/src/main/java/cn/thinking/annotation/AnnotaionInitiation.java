package cn.thinking.annotation;

@AnnotationInherited(name = "This is AnnotationInherited name.")
@AnnotationInheritedMessage(messages = { "msg_a", "msg_b" })
public class AnnotaionInitiation {

	public AnnotaionInitiation() {
		System.out.println(AnnotaionInitiation.class.getCanonicalName());
		System.out.println("AnnotaionInitiation.class.isAnnotationPresent(AnnotationInherited.class):"
				+ AnnotaionInitiation.class.isAnnotationPresent(AnnotationInherited.class));
		System.out.println("AnnotaionInitiation.class.isAnnotationPresent(AnnotationInheritedMessage.class):"
				+ AnnotaionInitiation.class.isAnnotationPresent(AnnotationInheritedMessage.class));
		
		System.out.println();
	}
}
