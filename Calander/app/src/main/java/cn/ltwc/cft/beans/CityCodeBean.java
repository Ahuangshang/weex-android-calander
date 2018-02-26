package cn.ltwc.cft.beans;

public class CityCodeBean {
    private String city;
    private String code;

    public CityCodeBean(String city, String code) {
        super();
        this.city = city;
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
