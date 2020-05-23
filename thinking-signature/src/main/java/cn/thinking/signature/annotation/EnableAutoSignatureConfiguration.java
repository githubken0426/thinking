package cn.thinking.signature.annotation;

import cn.thinking.signature.selector.AutoSignatureConfigurationSelector;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AutoConfigurationPackage
@Import(AutoSignatureConfigurationSelector.class)
public @interface EnableAutoSignatureConfiguration {
}
