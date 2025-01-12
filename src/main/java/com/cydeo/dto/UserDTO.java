package com.cydeo.dto;

import com.cydeo.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    // @NotBlank => field should not be null, "" or "  "
    // @NotEmpty => field should not be null, "" or empty collection
    // @NotNull => field should not be null

    @JsonIgnore
    private Long id;

    @NotBlank(message = "First Name is a required field")
    @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long")
    private String firstName;

    @NotBlank(message = "Last Name is a required field")
    @Size(max = 15, min = 2, message = "Last Name must be between 2 and 15 characters long")
    private String lastName;

    @NotBlank(message = "Email is a required field")
    @Email(message = "Email must be in a valid email format")
    private String userName;

    @NotBlank(message = "Phone Number is a required field")
    @Pattern(regexp = "^\\d{10}$", message = "Phone Number should be 10 characters long, and can only " +
            "include digits")
    private String phone;

    @NotBlank(message = "Password is a required field")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}", message = "The password must be at " +
            "least 4 characters long and include at least 1 uppercase letter, 1 lowercase letter " +
            "and 1 digit")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passWord;

    @NotBlank(message = "Passwords should match")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassWord;

    @NotNull(message = "Please select a Gender")
    private Gender gender;

    @NotNull(message = "Please select a Role")
    private RoleDTO role;


    public void setPassWord(String passWord) {
        this.passWord = passWord;
        checkConfirmPassword();
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
        checkConfirmPassword();
    }

    private void checkConfirmPassword(){
        if (passWord == null || confirmPassWord == null) return;

        else if (!passWord.equals(confirmPassWord)) {

            confirmPassWord = null;
        }

    }

}
