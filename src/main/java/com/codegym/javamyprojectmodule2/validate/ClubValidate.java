package com.codegym.javamyprojectmodule2.validate;

import com.codegym.javamyprojectmodule2.model.Club;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ClubValidate implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Club.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Club club = (Club) target;
        String name = club.getName();
        String stadium = club.getStadium();
//        String tournaments = club.getTournaments();
//        String national = club.getNational();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stadium", "stadium.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tournaments", "tournaments.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "national", "national.empty");
    }
}
