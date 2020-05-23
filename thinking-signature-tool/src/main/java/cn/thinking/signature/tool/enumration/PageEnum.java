package cn.thinking.signature.tool.enumration;

public enum PageEnum {
    LOGIN("login"),
    LOGIN_SUCCESS("login-success"),
    MAIN("main"),
    OAUTH("oauth"),
    SIGNATURE("signature");
    private String name;

    PageEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static PageEnum of(String name) {
        for (PageEnum vendor : PageEnum.values()) {
            if (vendor.name().equals(name)) {
                return vendor;
            }
        }
        return null;
    }
}
