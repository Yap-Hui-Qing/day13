package vttp.batchb.ssf.day13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.batchb.ssf.day13.models.Registration;

// binding the form to a model
@Controller
@RequestMapping(path={"/", "index.html"})
public class IndexController {
    
    @GetMapping
    public String getIndex(Model model){

        // create the model and bind it to the form
        model.addAttribute("reg", new Registration());

        // return the form with model bindings
        return "index";
    }
}
