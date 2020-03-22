package com.project.coronavirustracker.services;

import com.project.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private Collection<LocationStats> allStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 12 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvStringReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvStringReader);

        Collection<LocationStats> newStats = new HashSet<>();
        for (CSVRecord record : records) {
            newStats.add(new LocationStats(record));
        }
        this.allStats = agruparPaises(newStats);
    }

    public Collection<LocationStats> getAllStats(){
        return this.allStats;
    }

    private Collection<LocationStats> agruparPaises(Collection<LocationStats> collection){
        Collection<LocationStats> agrupada = new ArrayList<>();

        for(LocationStats ls : collection){

            List<LocationStats> temp = collection
                    .stream()
                    .filter(obj -> obj.getCountry().equals(ls.getCountry()))
                    .collect(Collectors.toList());

            LocationStats ag = new LocationStats();
            ag.setCountry(ls.getCountry());
            temp.forEach(obj -> ag.agrupar(obj));
            agrupada.add(ag);
        }
        return new HashSet<>(agrupada);
    }
}
