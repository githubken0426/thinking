package cn.thinking.modifier._protected._02;

import cn.thinking.modifier._protected._01.Father;

public class Test {
    public static void main(String[] args) {
        Father son = new Son();
        /*
         * compile error
         */
        //System.out.println(son.id);
    }
}
