package model;

public class CountryInfo {

    private String countryInfoFlag;
    private String countryInfoName;
    private String countryInfoCapital;
    private String countryInfoRegion;
    private String countryInfoPhoneCode;
    private String countryInfoInternetDomain;
    private String countryInfoPopulation;
    private String countryInfoTerritory;
    private String countryInfoDensity;
    private String countryInfoLanguage;
    private String countryInfoCurrency;

    public CountryInfo(){}

    public CountryInfo(String countryInfoFlag, String countryInfoName,
                       String countryInfoCapital, String countryInfoRegion,
                       String countryInfoPhoneCode, String countryInfoInternetDomain,
                       String countryInfoPopulation, String countryInfoTerritory,
                       String countryInfoDensity, String countryInfoLanguage,
                       String countryInfoCurrency) {
        this.countryInfoFlag = countryInfoFlag;
        this.countryInfoName = countryInfoName;
        this.countryInfoCapital = countryInfoCapital;
        this.countryInfoRegion = countryInfoRegion;
        this.countryInfoPhoneCode = countryInfoPhoneCode;
        this.countryInfoInternetDomain = countryInfoInternetDomain;
        this.countryInfoPopulation = countryInfoPopulation;
        this.countryInfoTerritory = countryInfoTerritory;
        this.countryInfoDensity = countryInfoDensity;
        this.countryInfoLanguage = countryInfoLanguage;
        this.countryInfoCurrency = countryInfoCurrency;
    }

    public String getCountryInfoRegion() {
        return countryInfoRegion;
    }

    public void setCountryInfoRegion(String countryInfoRegion) {
        this.countryInfoRegion = countryInfoRegion;
    }

    public String getCountryInfoFlag() {
        return countryInfoFlag;
    }

    public void setCountryInfoFlag(String countryInfoFlag) {
        this.countryInfoFlag = countryInfoFlag;
    }

    public String getCountryInfoName() {
        return countryInfoName;
    }

    public void setCountryInfoName(String countryInfoName) {
        this.countryInfoName = countryInfoName;
    }

    public String getCountryInfoCapital() {
        return countryInfoCapital;
    }

    public void setCountryInfoCapital(String countryInfoCapital) {
        this.countryInfoCapital = countryInfoCapital;
    }

    public String getCountryInfoPhoneCode() {
        return countryInfoPhoneCode;
    }

    public void setCountryInfoPhoneCode(String countryInfoPhoneCode) {
        this.countryInfoPhoneCode = countryInfoPhoneCode;
    }

    public String getCountryInfoInternetDomain() {
        return countryInfoInternetDomain;
    }

    public void setCountryInfoInternetDomain(String countryInfoInternetDomain) {
        this.countryInfoInternetDomain = countryInfoInternetDomain;
    }

    public String getCountryInfoPopulation() {
        return countryInfoPopulation;
    }

    public void setCountryInfoPopulation(String countryInfoPopulation) {
        this.countryInfoPopulation = countryInfoPopulation;
    }

    public String getCountryInfoTerritory() {
        return countryInfoTerritory;
    }

    public void setCountryInfoTerritory(String countryInfoTerritory) {
        this.countryInfoTerritory = countryInfoTerritory;
    }

    public String getCountryInfoDensity() {
        return countryInfoDensity;
    }

    public void setCountryInfoDensity(String countryInfoDensity) {
        this.countryInfoDensity = countryInfoDensity;
    }

    public String getCountryInfoLanguage() {
        return countryInfoLanguage;
    }

    public void setCountryInfoLanguage(String countryInfoLanguage) {
        this.countryInfoLanguage = countryInfoLanguage;
    }

    public String getCountryInfoCurrency() {
        return countryInfoCurrency;
    }

    public void setCountryInfoCurrency(String countryInfoCurrency) {
        this.countryInfoCurrency = countryInfoCurrency;
    }
}
