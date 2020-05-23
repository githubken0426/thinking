package cn.thinking.signature.annotation;
import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SignatureRequestRequired {

}
