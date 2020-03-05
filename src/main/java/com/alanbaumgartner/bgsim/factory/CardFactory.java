package com.alanbaumgartner.bgsim.factory;

import com.alanbaumgartner.bgsim.Card;
import com.google.gson.Gson;

import java.io.FileReader;

public class CardFactory {

    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static Card CreateCard(String json) {
        return gson.fromJson(json, Card.class);
    }

    public static Card[] CreateCards(FileReader json) {
        return gson.fromJson(json, Card[].class);
    }
}
