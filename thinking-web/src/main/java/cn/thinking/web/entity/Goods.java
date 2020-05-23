package cn.thinking.web.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Goods {
    private Integer id;
    private String skuCode;
    @NotEmpty(message="标题不能为空")
    @Length(min = 1, max = 200, message = "标题长度在1到100个字符之间")
    private String goodsTitle;
    @Length(min = 0, max = 200, message = "备注长度在0到200个字符之间")
    private String goodsDescription;
    private Double primePrice;
    private Double promotionPrice;
    private String goodsUnit;
    private Integer stock;
    private String pictures;
    private Date upTime;
    private Date downTime;
    private String shelves;
    private String ration;
    private String rationNum;
    private Date createTime;
    private Date modifyTime;
    private List<String> pictureList;
    private String preview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle == null ? null : goodsTitle.trim();
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription == null ? null : goodsDescription.trim();
    }

    public Double getPrimePrice() {
        return primePrice;
    }

    public void setPrimePrice(Double primePrice) {
        this.primePrice = primePrice;
    }

    public Double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public String getShelves() {
        return shelves;
    }

    public void setShelves(String shelves) {
        this.shelves = shelves == null ? null : shelves.trim();
    }

    public String getRation() {
        return ration;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
        if (StringUtils.isNotBlank(pictures)) {
            this.pictureList = Arrays.asList(pictures.split(","));
            this.preview = pictureList.get(0);
        }
    }

    public void setRation(String ration) {
        this.ration = ration == null ? null : ration.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRationNum() {
        return rationNum;
    }

    public void setRationNum(String rationNum) {
        this.rationNum = rationNum;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}