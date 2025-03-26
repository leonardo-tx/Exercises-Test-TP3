package org.example.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegisterDTO {
    private Gender gender;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;
    private boolean agreeTerms;
    private boolean dataPrivacy;
    private boolean receiveNewsletter;
    private boolean receiveOffers;
}
