package com.kasusa.communityaccessmanagement.datacls;

public class xiaoqu {
    public Long id;
    public String name;
    public String area;
    public String city;
    public String province;


    public xiaoqu(Long id, String name, String area, String city, String province) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.city = city;
        this.province = province;
    }

    @Override
    public String toString() {
        return "xiaoqu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
