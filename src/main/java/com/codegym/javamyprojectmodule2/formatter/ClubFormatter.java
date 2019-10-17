package com.codegym.javamyprojectmodule2.formatter;

import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ClubFormatter implements Formatter<Club> {

    private ClubService clubService;

    @Autowired
    public ClubFormatter(ClubService clubService) {
        this.clubService = clubService;
    }

    @Override
    public Club parse(String text, Locale locale) throws ParseException {
        return clubService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(Club object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}

