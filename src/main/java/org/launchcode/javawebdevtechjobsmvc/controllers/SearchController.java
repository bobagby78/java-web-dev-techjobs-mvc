package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Locale;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

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
    ArrayList<Job> jobs = new ArrayList();
    @PostMapping(value = "")
    public String displaySearchResults (Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        model.addAttribute(searchType, "searchType");
        model.addAttribute(searchTerm, "searchTerm");
        jobs=JobData.findAll();
        if (searchTerm.toLowerCase().equals("all") || searchTerm == null){
            jobs=JobData.findAll();
        }
        return "results";
    }

}
