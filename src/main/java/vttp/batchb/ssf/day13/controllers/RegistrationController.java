package vttp.batchb.ssf.day13.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.batchb.ssf.day13.models.Registration;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    static final String REG_LIST = "regList";

    private String prevName = "";

    // POST /registration/obj
    // model attribute -- binding form to object
    // mapping form fields - @ModelAttribute 
    // valid -- for validation
    @PostMapping("/obj")
    public String postRegistrationObj(Model model,
    // instantiates Registration, injects the form fields into the object and passes it to request handler
    // syntactic validation -- validate the data captured from the form by the model
    @Valid @ModelAttribute("reg") Registration registration,
    // BindingResult must follow @Valid immediately
    // BindingResult contains the validation results
    BindingResult bindings,HttpSession sess){

        System.out.printf("--- bindings: %b\n", bindings.hasErrors());
        System.out.printf("--- registration: %s\n", registration);
        
        if (bindings.hasErrors()) {

            // FieldError err = new FieldError("reg", "name", "This is an error message");
            // model.addAttribute("reg", registration);
            
            // if there are validation errors, return to the form and report the errors
            return "index";

        }

        if ("fred".equals(registration.getName().toLowerCase())){
            // semantic validation
            FieldError err = new FieldError("reg", "name", "You cannot use the name fred");
            bindings.addError(err);

            ObjectError objErr = new ObjectError("globalError", "error 1");
            bindings.addError(objErr);
            objErr = new ObjectError("globalError", "error 2");
            bindings.addError(objErr);

            return "index";
        }

        // check if session has the list --> new session will not have a list
        List<Registration> regList = (List<Registration>) sess.getAttribute(REG_LIST);

        if (regList == null){
            // if new session then regList is null
            // initialise session by creating a list
            regList = new LinkedList<>();
            // add to the session
            sess.setAttribute(REG_LIST, regList);
        }

        regList.add(registration);

        model.addAttribute("email", registration.getEmail());
        model.addAttribute("reg", registration);
        model.addAttribute("regList", regList);

        return "registered";

    }
    
    // POST /registration
    @PostMapping
    public String postRegistration(
        // typically use multivalue map
        // mapping form fields using MultiValueMap - use @RequestBody to map payload to MultiValueMap
        @RequestBody MultiValueMap<String,String> form,
        @RequestBody String rawBody,
        Model model){

        Registration reg = new Registration();
        reg.setName(form.getFirst("name"));
        reg.setEmail(form.getFirst("email"));

        System.out.printf("------ form: %s\n", form);
        System.out.printf("------ rawBody: %s\n", rawBody);
        System.out.printf("------ prevName: %s\n", prevName);

        model.addAttribute("prevName", prevName);

        prevName = reg.getName();

        model.addAttribute("reg", reg);

        return "registered";

    }
}

