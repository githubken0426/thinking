package cn.thinking.web.entity;

public class Setmeal {
    private Integer id;
    private String name;
    private String category;
    private Double price;
    private Integer deliverys;
    private Double perWeight;
    private Integer month;
    private Boolean isSelected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDeliverys() {
        return deliverys;
    }

    public void setDeliverys(Integer deliverys) {
        this.deliverys = deliverys;
    }

    public Double getPerWeight() {
        return perWeight;
    }

    public void setPerWeight(Double perWeight) {
        this.perWeight = perWeight;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        isSelected = isSelected;
    }
}
