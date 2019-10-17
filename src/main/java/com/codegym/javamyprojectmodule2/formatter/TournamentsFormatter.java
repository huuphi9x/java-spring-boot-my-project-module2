package com.codegym.javamyprojectmodule2.formatter;

import com.codegym.javamyprojectmodule2.model.Tournaments;
import com.codegym.javamyprojectmodule2.service.TournamentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class TournamentsFormatter implements Formatter<Tournaments> {

    private TournamentsService tournamentsService;

    @Autowired
    public TournamentsFormatter(TournamentsService tournamentsService) {
        this.tournamentsService = tournamentsService;
    }

    @Override
    public Tournaments parse(String text, Locale locale) throws ParseException {
        return tournamentsService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(Tournaments object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
