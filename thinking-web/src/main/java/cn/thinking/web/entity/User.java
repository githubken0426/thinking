package cn.thinking.web.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class User {

    private Integer id;
    @NotEmpty(message="账号不能为空")
    @Length(min = 4, max = 30, message = "账号长度在6到30个字符之间")
    private String account;

    @NotEmpty(message="密码不能为空")
    @Length(min = 6, max = 10, message = "密码长度在6到10个字符之间")
    private String password;
    private String realname;

    @Length(min = 11, max = 11, message = "手机号的长度必须是11位")
    private String telephone;
    private String isDeliverer;
    private Integer deliverCount;
    private Integer roleId;
    private Date createTime;
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getIsDeliverer() {
        return isDeliverer;
    }

    public void setIsDeliverer(String isDeliverer) {
        this.isDeliverer = isDeliverer == null ? null : isDeliverer.trim();
    }

    public Integer getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(Integer deliverCount) {
        this.deliverCount = deliverCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}