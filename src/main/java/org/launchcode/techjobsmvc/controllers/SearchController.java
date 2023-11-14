package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobsmvc.models.JobData.findAll;
import static org.launchcode.techjobsmvc.models.JobData.findByColumnAndValue;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam("searchType") String searchType, @RequestParam(required = false)String searchTerm) {
        ArrayList <Job> jobsArray = new ArrayList<>();
        if(searchTerm == "") {
            jobsArray.addAll(findAll());

        } else if(searchTerm == "all") {
            jobsArray.addAll(findAll());
        } else {
                jobsArray.addAll(findByColumnAndValue(searchType,searchTerm));
        }
        model.addAttribute("jobs", jobsArray);
        model.addAttribute("columns", columnChoices);
        return "search";
    }
}

