package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Locale;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.tableChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "") //default GET mapping
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
//    ArrayList<Job> jobs = new ArrayList();
    @PostMapping(value = "search")
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam String searchTerm) {

        ArrayList<Job> jobs;


        if (searchTerm.equalsIgnoreCase("all") || searchTerm.equals("")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs in the category " + columnChoices.get(searchType) + "including keyword: " + tableChoices.get(searchTerm));
        }
        model.addAttribute(searchType, "searchType");//are these doing anything?
        model.addAttribute(searchTerm, "searchTerm");
        model.addAttribute("jobs", jobs);
        model.addAttribute("tableChoices", tableChoices);
        model.addAttribute("columns", columnChoices);
        return"results";
    }

}
