package com.codegym.javamyprojectmodule2.formatter;

import com.codegym.javamyprojectmodule2.model.Club;
import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.service.ClubService;
import com.codegym.javamyprojectmodule2.service.NationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class NationalFormatter implements Formatter<National> {

    private NationalService nationalService;

    @Autowired
    public NationalFormatter(NationalService nationalService) {
        this.nationalService = nationalService;
    }

    @Override
    public National parse(String text, Locale locale) throws ParseException {
        return nationalService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(National object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}

