package vttp.batchb.ssf.day13.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Registration {
    
    // annotating model with constraints
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min=2, max=32, message = "Name must be between 2 and 32 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Must be a valid email")
    private String email;

    private String comments;

    @Override
    public String toString() {
        return "Registration [name=" + name + ", email=" + email + ", comments=" + comments + "]";
    }

    // property name - name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // property name - email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // // property name - emailAddress
    // public String getEmailAddress() {
    //     return email;
    // }
    // public void setEmailAddress(String email) {
    //     this.email = email;
    // }

    // property name - comments
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }


}
