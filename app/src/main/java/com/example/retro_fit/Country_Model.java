package com.example.retro_fit;

public class Country_Model
{
    private String flag,countries,cases,todayCases,deaths,todayDeaths,recovered,todayRecovered,active,active_permillion,recovered_permillion;

    public Country_Model()
    {

    }



    public Country_Model(String flag, String countries, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, String active, String active_permillion, String recovered_permillion) {
        this.flag = flag;
        this.countries = countries;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.todayRecovered=todayRecovered;
        this.active_permillion = active_permillion;
        this.recovered_permillion=recovered_permillion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActive_permillion() {
        return active_permillion;
    }

    public void setActive_permillion(String active_permillion) {
        this.active_permillion = active_permillion;
    }

    public String getRecovered_permillion() {
        return recovered_permillion;
    }

    public void setRecovered_permillion(String recovered_permillion) {
        this.recovered_permillion = recovered_permillion;
    }
    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }
}
