package com.inventory.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    private String userName;
    private String password;
    private String email;
    private String mobile;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dob;
    private String age;
    private String role;
    private String token;
    private Date createdOn;
    private String createdBy;
    private Date updatedOn;
    private String updatedBy;
}
