package cn.thinking.singleton;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 13:34
 * @Description:
 */
public enum SingletonEnum {
    INSTANCE {
        @Override
        public Singleton getInstance() {
            System.out.println("INSTANCE");
            return new Singleton();
        }

        @Override
        public String sayHi() {
            return "I'm INSTANCE;";
        }
    },
    INSTANCE2 {
        @Override
        public Singleton getInstance() {
            return null;
        }

        @Override
        public String sayHi() {
            return "I'm INSTANCE2;";
        }
    },
    INSTANCE3 {
        @Override
        public Singleton getInstance() {
            return null;
        }

        @Override
        public String sayHi() {
            return "I'm INSTANCE3;";
        }
    },
    INSTANCE4 {
        @Override
        public Singleton getInstance() {
            return null;
        }

        @Override
        public String sayHi() {
            return "I'm INSTANCE4;";
        }
    };

    public abstract String sayHi();

    public abstract Singleton getInstance();
}