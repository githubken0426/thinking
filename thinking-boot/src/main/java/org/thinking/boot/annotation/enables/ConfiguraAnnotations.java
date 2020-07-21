package org.thinking.boot.annotation.enables;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Enable* 三大类
 * @author kun.f.wang
 * 
 */
@EnableScheduling //1.Import Configuration
@EnableAsync //2.Condition Selector
@EnableAspectJAutoProxy //3.AutoProxyRegistrar
public class ConfiguraAnnotations {

}
