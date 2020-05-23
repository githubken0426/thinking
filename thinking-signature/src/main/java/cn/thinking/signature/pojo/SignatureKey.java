package cn.thinking.signature.pojo;

import java.security.PrivateKey;
import java.security.PublicKey;

public class SignatureKey {
    private PublicKey vendorPublicKey;

    private PrivateKey localPrivateKey;

    public PublicKey getVendorPublicKey() {
        return vendorPublicKey;
    }

    public void setVendorPublicKey(PublicKey vendorPublicKey) {
        this.vendorPublicKey = vendorPublicKey;
    }

    public PrivateKey getLocalPrivateKey() {
        return localPrivateKey;
    }

    public void setLocalPrivateKey(PrivateKey localPrivateKey) {
        this.localPrivateKey = localPrivateKey;
    }


}
