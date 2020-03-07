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

    private static Random rand = new Random();;
    public static Map<String, Deathrattle> deathrattleMap = new HashMap<>();

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
    static List<Card> LegendaryPool;
    /**
     *
     */
    static List<Card> DeathrattlePool;
    /**
     *
     */
    static List<Card> TwoCostPool;

    /**
     *
     */
    static {
        Reflections reflections = new Reflections("com.alanbaumgartner.bgsim.deathrattles");
        Set<Class<? extends com.alanbaumgartner.bgsim.deathrattles.Deathrattle>> allClasses = reflections.getSubTypesOf(Deathrattle.class);
        for (Class<? extends com.alanbaumgartner.bgsim.deathrattles.Deathrattle> s : allClasses) {
            try {
                deathrattleMap.put(s.getSimpleName(), s.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        // Ghastcoiler cannot summon itself and the rest are no longer in the set.
        List<String> invalidDeathrattles = Arrays.asList("Ghastcoiler", "Piloted Sky Golem", "Mounted Raptor", "Sated Threshadon", "Tortollan Shellraiser");
        // Boogeymonster is no longer in the set.
        List<String> invalidLegendaries = Arrays.asList("The Boogeymonster");
        // Tokens cannot be spawned from shredder and Annoy-o-Tron is no longer in the set.
        List<String> invalidTwoCost = Arrays.asList("Amalgam", "Big Bad Wolf", "Hyena", "Vault Safe", "Guard Bot", "Annoy-o-Tron");

        BGAll = new ArrayList<>();
        TwoCostPool = new ArrayList<>();
        LegendaryPool = new ArrayList<>();
        Minions = new ArrayList<>();
        Enchantments = new ArrayList<>();
        Heroes = new ArrayList<>();
        Tokens = new ArrayList<>();
        DeathrattlePool = new ArrayList<>();

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

        for (Card c : BGAll) {
            if (c.getTechLevel() != null && c.getType() == Type.MINION) {
                Minions.add(c);
                if (!c.isGold()) {
                    if (c.getCost() == 2 && !invalidTwoCost.contains(c.getName())) {
                        TwoCostPool.add(c);
                    } else if (c.getRarity() == Rarity.LEGENDARY && !invalidLegendaries.contains(c.getName())) {
                        LegendaryPool.add(c);
                    } else if (!invalidDeathrattles.contains(c.getName()) && c.getMechanics() != null && c.getMechanics().contains(Mechanics.DEATHRATTLE)) {
                        DeathrattlePool.add(c);
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
        return DeathrattlePool.get(Main.getRandomInteger(DeathrattlePool.size()));
    }

    public static Card getRandomLegendary() {
        return LegendaryPool.get(Main.getRandomInteger(LegendaryPool.size()));
    }

    public static Card getRandomTwoCost() {
        return TwoCostPool.get(Main.getRandomInteger(TwoCostPool.size()));
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
