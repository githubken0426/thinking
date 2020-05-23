package cn.thinking.signature.tool.enumration;

import lombok.Getter;

@Getter
public enum PartnerEnum {

    PARTNER("username", "Partner");

    private String clientId;
    private String name;

    PartnerEnum(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public static PartnerEnum of(String partnerClientId) {
        for (PartnerEnum partner : PartnerEnum.values()) {
            if (partner.getClientId().equals(partnerClientId)) {
                return partner;
            }
        }
        return null;
    }
}
