package cn.thinking.proxy.filter;

import net.sf.cglib.proxy.FixedValue;

/**
 * 锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值
 *
 * @author ken
 * 2016-12-7 上午09:50:13
 */
public class TargetResultFixed implements FixedValue {

    @Override
    public Object loadObject() throws Exception {
        System.out.print("锁定结果:");
        Object obj = 888;
        return obj;
    }

}
