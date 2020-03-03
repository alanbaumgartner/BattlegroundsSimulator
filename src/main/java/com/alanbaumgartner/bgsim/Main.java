package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.CardSet;
import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.enums.Rarity;
import com.alanbaumgartner.bgsim.enums.Type;
import com.alanbaumgartner.bgsim.factory.Card;
import com.alanbaumgartner.bgsim.factory.CardFactory;

import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    /**
     *
     */
    static List<Card> All;

    /**
     *
     */
    static List<Card> Minions;

    /**
     *
     */
    static List<Card> Enchantments;

    /**
     *
     */
    static List<Card> Heroes;

    /**
     *
     */
    static List<Card> Tokens;

    /**
     *
     */
    static List<Card> Legendary;

    /**
     *
     */
    static List<Card> Deathrattle;

    /**
     *
     */
    static List<Card> TwoCost;

    /**
     *
     */
    static {
        List<String> invalidDeathrattles = Arrays.asList("Ghastcoiler");
        List<String> invalidLegendaries = Arrays.asList("The Boogeymonster");
        List<String> invalidTwoCost = Arrays.asList("Amalgam", "Big Bad Wolf", "Hyena", "Vault Safe", "Guard Bot", "Annoy-o-Tron");

        TwoCost = new ArrayList<>();
        Legendary = new ArrayList<>();
        Minions = new ArrayList<>();
        Enchantments = new ArrayList<>();
        Heroes = new ArrayList<>();
        Tokens = new ArrayList<>();
        Deathrattle = new ArrayList<>();



        try (FileReader fr = new FileReader("src/main/cards.json")) {
            All = Arrays.asList(CardFactory.CreateCards(fr));
        } catch (IOException ex) {
            System.err.println("Error reading cards from JSON.");
        }

        for (Card c : All) {
            if (c.getTechLevel() != null && c.getType() == Type.MINION) {
                Minions.add(c);
                if (!c.getId().contains("BaconUps")) {
                    if (c.getCost() == 2 && !invalidTwoCost.contains(c.getName())) {
                        TwoCost.add(c);
                    } else if (c.getRarity() == Rarity.LEGENDARY && !invalidLegendaries.contains(c.getName())) {
                        Legendary.add(c);
                    }
                    else if (!invalidDeathrattles.contains(c.getName()) && c.getMechanics() != null && c.getMechanics().contains(Mechanics.DEATHRATTLE)) {
                        Deathrattle.add(c);
                    }
                }


            } else if (c.getSet() == CardSet.BATTLEGROUNDS && c.getType() == Type.HERO) {
                Heroes.add(c);
            } else if (c.getType() == Type.ENCHANTMENT && c.getSet() == CardSet.BATTLEGROUNDS) {
                Enchantments.add(c);
            }
        }

        for (Card c : TwoCost) {
            System.out.println("Twocost:" + c.getName());
        }

        for (Card c : Legendary) {
            System.out.println("legendaries:" + c.getName());
        }

        for (Card c : Deathrattle) {
            System.out.println("deathrattles:" + c.getName());
        }

//        System.out.println(twocost);
//        System.out.println(legendaries);

    }

    /**
     *
     */
    public static void main(String[] args) throws IOException {
//        Stream<Card> bgdm = bgminions.filter(p -> !p.getId().contains("baconUps") && Arrays.asList(p.getMechanics()).contains(Mechanics.DEATHRATTLE));
//        Stream<Card> bgminions = Arrays.stream(cards).filter(p -> p.getTechLevel() != null && p.getType() == Type.MINION);

//        for (Object c : bgminions.toArray()) {
//            Card a = (Card) c;
//            if (!a.getId().contains("BaconUps"))
//            System.out.println(a.getName());
//        }



//        Stream<Card> bgminions = Arrays.stream(cards).filter(p -> p.getTechLevel() != null && p.getType() == Type.MINION);

//        Stream<Card> heroes = Arrays.stream(cards).filter(p -> p.getType() == Type.HERO && p.getSet() == CardSet.BATTLEGROUNDS);
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        gson.toJson(bgheroes.toArray(), fw1);
//        gson.toJson(bgminions.toArray(), fw2);
//        gson.toJson(bgenchantments.toArray(), fw3);
//
//
//
//        fw1.flush();
//        fw2.flush();
//        fw3.flush();
//        fw1.close();
//        fw2.close();
//        fw3.close();

//        System.out.println(bgminions.count());
//        System.out.println(bgheroes.count());
//        System.out.println(bgenchantments.count());

//        System.out.println();

//        System.out.println(heroes);

//        System.out.println(bg.count());
//        for (Object c : bgheroes.toArray()) {
//            Card a = (Card) c;
//            String name = a.getName();
//
//            System.out.println("@SerializedName(\"" + name + "\") " + name.replace(" ", "").replace("'", "").replace(".", "").toUpperCase());
//        }

    }

}
