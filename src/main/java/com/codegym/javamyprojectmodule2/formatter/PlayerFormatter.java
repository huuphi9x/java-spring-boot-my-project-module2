package com.codegym.javamyprojectmodule2.formatter;

import com.codegym.javamyprojectmodule2.model.National;
import com.codegym.javamyprojectmodule2.model.Player;
import com.codegym.javamyprojectmodule2.service.NationalService;
import com.codegym.javamyprojectmodule2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PlayerFormatter implements Formatter<Player> {

    private PlayerService playerService;

    @Autowired
    public PlayerFormatter(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public Player parse(String text, Locale locale) throws ParseException {
        return playerService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(Player object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
