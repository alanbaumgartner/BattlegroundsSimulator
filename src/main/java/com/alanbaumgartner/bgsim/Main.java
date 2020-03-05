package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.enums.Rarity;
import com.alanbaumgartner.bgsim.enums.Type;
import com.alanbaumgartner.bgsim.factory.CardFactory;

import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

    static Random rand;

    /**
     *
     */
    static List<Card> All;

    /**
     *
     */
    static List<Card> BGAll;

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
        rand = new Random();





        // Ghastcoiler cannot summon itself and the rest are no longer in the set.
        List<String> invalidDeathrattles = Arrays.asList("Ghastcoiler", "Piloted Sky Golem", "Mounted Raptor", "Sated Threshadon", "Tortollan Shellraiser");
        // Boogeymonster is no longer in the set.
        List<String> invalidLegendaries = Arrays.asList("The Boogeymonster");
        // Tokens cannot be spawned from shredder and Annoy-o-Tron is no longer in the set.
        List<String> invalidTwoCost = Arrays.asList("Amalgam", "Big Bad Wolf", "Hyena", "Vault Safe", "Guard Bot", "Annoy-o-Tron");

        BGAll = new ArrayList<>();
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
            if (c.getTechLevel() != null) {
                BGAll.add(c);
            } else if (c.getType() == Type.HERO) {
                Heroes.add(c);
            } else if (c.getType() == Type.ENCHANTMENT) {
                Enchantments.add(c);
            }
        }

        System.out.println(BGAll.size());

        for (Card c : BGAll) {
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


            }
        }

//        for (Card c : BGAll) {
//            System.out.println(c.getName());
//        }

//        for (Card c : TwoCost) {
//            System.out.println("Twocost:" + c.getName());
//        }
//
//        for (Card c : Legendary) {
//            System.out.println("legendaries:" + c.getName());
//        }
//
//        for (Card c : Deathrattle) {
//            System.out.println("deathrattles:" + c.getName());
//        }

    }

    /**
     *
     */
    public static void main(String[] args) {

        Player one = new Player(new ArrayList(Arrays.asList(BGAll.get(0))));
        Player two = new Player(new ArrayList(Arrays.asList(BGAll.get(1), BGAll.get(1))));

        System.out.println(one.getMinions());
        System.out.println(two.getMinions());

        Board b = new Board(one, two);
        b.simulate();


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
