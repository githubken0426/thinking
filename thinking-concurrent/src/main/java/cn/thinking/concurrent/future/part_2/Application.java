package cn.thinking.concurrent.future.part_2;

import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) {
        Client client = new Client();
        // 这里会立即返回，因为获取的是FutureData，而非RealData
        try {
            Data data = client.request("name");
            /**
             * 这里可以用一个sleep代替对其他业务逻辑的处理,
             * 在处理这些业务逻辑过程中，RealData也正在创建，从而充分了利用等待时间
             */
            TimeUnit.SECONDS.sleep(5);
            // 使用真实数据
            System.out.println("数据=" + data.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
