package com.codegym.javamyprojectmodule2.formatter;

import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.model.Position;
import com.codegym.javamyprojectmodule2.service.NationalService;
import com.codegym.javamyprojectmodule2.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class PositionFormatter implements Formatter<Position> {

        private PositionService positionService;

        @Autowired
        public PositionFormatter(PositionService positionService) {
            this.positionService = positionService;
        }

        @Override
        public Position parse(String text, Locale locale) throws ParseException {
            return positionService.findById(Long.parseLong(text)).get();
        }

        @Override
        public String print(Position object, Locale locale) {
            return "[" + object.getId() + ", " + object.getName() + "]";
        }
    }
