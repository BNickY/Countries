package model;

public class Country {

    private String countryFlag;
    private String countryName;
    private String countryCapital;

    public Country(){}

    public Country(String countryFlag, String countryName, String countryCapital) {
        this.countryFlag = countryFlag;
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }
}