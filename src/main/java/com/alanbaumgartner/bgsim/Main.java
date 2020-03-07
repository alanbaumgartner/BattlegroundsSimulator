package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.deathrattles.Deathrattle;
import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.enums.Rarity;
import com.alanbaumgartner.bgsim.enums.Type;
import com.alanbaumgartner.bgsim.factory.CardFactory;
import org.reflections.Reflections;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {

    public static Random rand;
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
            if (c.getMechanics() == null) {
                c.init();
            }
            if (c.getTechLevel() != null) {
                BGAll.add(c);
                if (c.getId().contains("BaconUps")) {
                    c.setGold(true);
                }
            } else if (c.getType() == Type.HERO) {
                Heroes.add(c);
            } else if (c.getType() == Type.ENCHANTMENT) {
                Enchantments.add(c);
            }
        }

        Reflections reflections = new Reflections("com.alanbaumgartner.bgsim.deathrattles");
        Set<Class<? extends com.alanbaumgartner.bgsim.deathrattles.Deathrattle>> allClasses = reflections.getSubTypesOf(Deathrattle.class);
        for (Card c : All) {
            for (Class<? extends com.alanbaumgartner.bgsim.deathrattles.Deathrattle> s : allClasses) {
                if (c.getName().replace(" ", "").equalsIgnoreCase(s.getSimpleName())) {
//                    try {
//                        c.setDeathrattle(s.getConstructor().newInstance());
//                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }

//        System.out.println(BGAll.size());


        for (Card c : BGAll) {
            if (c.getTechLevel() != null && c.getType() == Type.MINION) {
                Minions.add(c);
                if (!c.isGold()) {
                    if (c.getCost() == 2 && !invalidTwoCost.contains(c.getName())) {
                        TwoCost.add(c);
                    } else if (c.getRarity() == Rarity.LEGENDARY && !invalidLegendaries.contains(c.getName())) {
                        Legendary.add(c);
                    } else if (!invalidDeathrattles.contains(c.getName()) && c.getMechanics() != null && c.getMechanics().contains(Mechanics.DEATHRATTLE)) {
                        Deathrattle.add(c);
                    }
                }


            }
        }

//        int i = 0;
//        for (Card c : BGAll) {
//            System.out.println(c.getName() + i);
//            i++;
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

    public static Integer getRandomInteger(Integer max) {
        return rand.nextInt(max);
    }

    public static Integer getUniqueRandomInteger(Integer max, List<Integer> exclude) {
        Integer random = rand.nextInt(max);
        while (exclude.contains(random)) {
            random = rand.nextInt(max);
        }
        return random;
    }

    public static Card getRandomDeathrattle() {
        return Deathrattle.get(Main.getRandomInteger(Deathrattle.size()));
    }

    public static Card getRandomLegendary() {
        return Legendary.get(Main.getRandomInteger(Legendary.size()));
    }

    public static Card getRandomTwoCost() {
        return TwoCost.get(Main.getRandomInteger(TwoCost.size()));
    }

    public static void main(String[] args) {
        Player one = new Player(new ArrayList(Arrays.asList(BGAll.get(5).clone())));
        Player two = new Player(new ArrayList(Arrays.asList(BGAll.get(5).clone())));
//        System.out.println(BGAll.get(5).getDeathrattle() != null);
//        Player two = new Player(new ArrayList(Arrays.asList(BGAll.get(228).clone(), BGAll.get(69).clone())));
        Simulation sim = new Simulation(one, two, 10000);
        sim.simulate();

    }

}
