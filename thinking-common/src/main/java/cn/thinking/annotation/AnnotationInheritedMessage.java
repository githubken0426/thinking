package cn.thinking.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AnnotationInherited虽然使用了@Inherited注解，但是AnnotationInheritedMessage并不能继承这个属性
 * 子类.class.isAnnotationPresent(AnnotationInheritedMessage.class)依然返回false
 * @author kun.f.wang
 */
@AnnotationInherited(name = "@AnnotationInherited")
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AnnotationInheritedMessage{

	public String[] messages() default {};
}
