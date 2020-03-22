package com.project.coronavirustracker.controllers;

import com.project.coronavirustracker.models.LocationStats;
import com.project.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CoronaVirusDataService dataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> lista = new ArrayList<>(dataService.getAllStats());
        Collections.sort(lista);

        Integer totalReportedCases = dataService
                .getAllStats()
                .stream()
                .mapToInt(ls -> ls.getLatestTotalCases())
                .sum();

        Integer totalNewCases = dataService
                .getAllStats()
                .stream()
                .mapToInt(ls -> ls.getLatestTotalCases() - ls.getLastDayTotalCases())
                .sum();

        model.addAttribute("locationStats", lista);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
