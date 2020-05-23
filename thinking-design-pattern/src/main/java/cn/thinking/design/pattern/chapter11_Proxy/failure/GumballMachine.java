package cn.thinking.design.pattern.chapter11_Proxy.failure;

/**
 * @Auther: ken.wangTJ
 * @Date: 3/20/2019 13:40
 * @Description:
 */
public class GumballMachine {
    private int count;
    private String state;
    private String location;

    public GumballMachine(int count, String state, String location) {
        this.count = count;
        this.state = state;
        this.location = location;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
