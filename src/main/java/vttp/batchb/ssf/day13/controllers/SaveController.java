package vttp.batchb.ssf.day13.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp.batchb.ssf.day13.models.Registration;

@Controller
@RequestMapping
public class SaveController {
    
    @PostMapping("/exit")
    public String postExit(HttpSession sess, Model model){
        //TODO: process POST request
        List<Registration> regList = (List<Registration>) sess.getAttribute(RegistrationController.REG_LIST);
        System.out.printf(">>> regList: %s\n", regList);

        // destroy the session
        // all session scoped object will also be destroyed
        sess.invalidate();

        // create a new Registration object to be bound to the form
        model.addAttribute("reg", new Registration());

        return "index";
    }
}
