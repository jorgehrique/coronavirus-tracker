package com.project.coronavirustracker.models;

import org.apache.commons.csv.CSVRecord;

public class LocationStats {

    private String country;
    private String state;
    private Integer latestTotalCases;
    private Integer lastDayTotalCases;
    private Integer lastWeekTotalCases;

    public LocationStats() {}

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
    public String toString() {
        return "LocationStats{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", lastDayTotalCases=" + lastDayTotalCases +
                ", lastWeekTotalCases=" + lastWeekTotalCases +
                '}';
    }
}
