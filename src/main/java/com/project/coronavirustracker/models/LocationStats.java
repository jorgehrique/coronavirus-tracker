package com.project.coronavirustracker.models;

import org.apache.commons.csv.CSVRecord;

import java.util.Objects;

public class LocationStats implements Comparable {

    private String country;
    private String state;
    private Integer latestTotalCases;
    private Integer lastDayTotalCases;
    private Integer lastWeekTotalCases;

    public LocationStats() {
        this.country = "";
        this.state = "";
        this.latestTotalCases = 0;
        this.lastDayTotalCases = 0;
        this.lastWeekTotalCases = 0;
    }

    public LocationStats(CSVRecord csvRecord) {
        this.country = csvRecord.get("Country/Region");
        this.state = csvRecord.get("Province/State");
        this.latestTotalCases = Integer.parseInt(csvRecord.get(csvRecord.size() - 1));
        this.lastDayTotalCases = Integer.parseInt(csvRecord.get(csvRecord.size() - 2));
        this.lastWeekTotalCases = Integer.parseInt(csvRecord.get(csvRecord.size() - 8));
    }

    public Integer getDiffFromPrevDay(){
        return this.latestTotalCases - lastDayTotalCases;
    }

    public Integer getDiffFromPrevWeek(){
        return this.latestTotalCases - lastWeekTotalCases;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(Integer latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public Integer getLastDayTotalCases() {
        return lastDayTotalCases;
    }

    public void setLastDayTotalCases(Integer lastDayTotalCases) {
        this.lastDayTotalCases = lastDayTotalCases;
    }

    public Integer getLastWeekTotalCases() {
        return lastWeekTotalCases;
    }

    public void setLastWeekTotalCases(Integer lastWeekTotalCases) {
        this.lastWeekTotalCases = lastWeekTotalCases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationStats that = (LocationStats) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(state, that.state) &&
                Objects.equals(latestTotalCases, that.latestTotalCases) &&
                Objects.equals(lastDayTotalCases, that.lastDayTotalCases) &&
                Objects.equals(lastWeekTotalCases, that.lastWeekTotalCases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, state, latestTotalCases, lastDayTotalCases, lastWeekTotalCases);
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", lastDayTotalCases=" + lastDayTotalCases +
                ", lastWeekTotalCases=" + lastWeekTotalCases +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        LocationStats ls = (LocationStats) o;
        if(this.latestTotalCases > ls.getLatestTotalCases()){
            return -1;
        } else if(this.latestTotalCases < ls.getLatestTotalCases()){
            return 1;
        } else {
            return 0;
        }
    }

    public LocationStats agrupar(LocationStats ls){
        this.state = "";
        this.latestTotalCases += ls.getLatestTotalCases();
        this.lastDayTotalCases += ls.getLastDayTotalCases();
        this.lastWeekTotalCases += ls.getLastWeekTotalCases();
        return this;
    }
}
