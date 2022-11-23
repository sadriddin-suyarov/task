package org.example;

public class City {
    private Integer cityCode;
    private String cityName;

    public City(Integer cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityCode=" + cityCode +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
