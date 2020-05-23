package cn.thinking.design.pattern.chapter11_Proxy.failure;

/**
 * @Auther: ken.wangTJ
 * @Date: 3/20/2019 13:42
 * @Description:
 */
public class GumballMonitor {
    GumballMachine machine;

    public GumballMonitor(GumballMachine machine) {
        this.machine = machine;
    }

    public String report() {
        return "Location:" + machine.getLocation() + ",count:" + machine.getCount() + ",state:" + machine.getState();
    }
}
