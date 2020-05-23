package cn.thinking.design.pattern.chapter11_Proxy.failure;

/**
 * @Auther: ken.wangTJ
 * @Date: 3/20/2019 13:47
 * @Description:
 */
public class GumballMahineTest {
    public static void main(String[] args) {
        GumballMachine machine=new GumballMachine(10,"start","TJ");
        GumballMonitor monitor=new GumballMonitor(machine);
        monitor.report();
    }

}
