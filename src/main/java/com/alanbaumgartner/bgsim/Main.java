package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.CardSet;
import com.alanbaumgartner.bgsim.enums.Type;
import com.alanbaumgartner.bgsim.factory.Card;
import com.alanbaumgartner.bgsim.factory.CardFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/main/cards.json");

        Card[] cards = CardFactory.CreateCards(fr);

        Stream<Card> bg = Arrays.stream(cards).filter(p -> p.getTechLevel() != null && p.getType() == Type.MINION);
        Stream<Card> heroes = Arrays.stream(cards).filter(p -> p.getType() == Type.HERO && p.getSet() == CardSet.BATTLEGROUNDS);

//        System.out.println();

//        System.out.println(heroes);

//        System.out.println(bg.count());
        for (Object c : heroes.toArray()) {
            Card a = (Card) c;
            System.out.println(a);
        }

    }

}
