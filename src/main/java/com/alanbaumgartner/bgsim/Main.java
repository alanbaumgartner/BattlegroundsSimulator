package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.deathrattles.Deathrattle;
import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.enums.Rarity;
import com.alanbaumgartner.bgsim.enums.Token;
import com.alanbaumgartner.bgsim.factory.CardFactory;
import org.reflections.Reflections;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static Map<String, Deathrattle> deathrattleMap = new LinkedHashMap<>();
	/**
	 *
	 */
	public static Map<Token, Card> Tokens;
	/**
	 *
	 */
	static ArrayList<Card> All;
	/**
	 *
	 */
	static Map<String, Card> BGAll;
	/**
	 *
	 */
	static Map<String, Card> Minions;
	static Map<String, Card> GoldMinions;
	/**
	 *
	 */
	static Map<String, Card> Enchantments;
	/**
	 *
	 */
	static Map<String, Card> Heroes;
	/**
	 *
	 */
	static Map<String, Card> LegendaryPool;
	/**
	 *
	 */
	static Map<String, Card> DeathrattlePool;
	/**
	 *
	 */
	static Map<String, Card> TwoCostPool;
	private static Random rand = new Random();
	private static Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");

	/**
	 *
	 */
	static {
		// Ghastcoiler cannot summon itself and the rest are no longer in the set.
		List<String> invalidDeathrattles = Arrays.asList("Ghastcoiler", "Piloted Sky Golem", "Mounted Raptor", "Sated Threshadon", "Tortollan Shellraiser");
		// Boogeymonster is no longer in the set.
		List<String> invalidLegendaries = Arrays.asList("The Boogeymonster");
		// Tokens cannot be spawned from shredder and Annoy-o-Tron is no longer in the set.
		List<String> invalidTwoCost = Arrays.asList("Amalgam", "Big Bad Wolf", "Hyena", "Vault Safe", "Guard Bot", "Annoy-o-Tron");

		BGAll = new LinkedHashMap<>();
		TwoCostPool = new LinkedHashMap<>();
		LegendaryPool = new LinkedHashMap<>();
		Minions = new LinkedHashMap<>();
		GoldMinions = new LinkedHashMap<>();
		Enchantments = new LinkedHashMap<>();
		Heroes = new LinkedHashMap<>();
		Tokens = new LinkedHashMap<>();
		DeathrattlePool = new LinkedHashMap<>();

		try (FileReader fr = new FileReader("src/main/resources/cards.json")) {
			All = new ArrayList<>();
			All.addAll(Arrays.asList(CardFactory.CreateCards(fr)));
		} catch (IOException ex) {
			System.err.println("Error reading cards from JSON.");
		}

		for (Card c : All) {
			if (c.getTechLevel() != null) {
				if (c.getMechanics() == null) {
					c.init();
				}
				BGAll.put(c.getName(), c);
				if (c.getId().contains("BaconUps")) {
					c.setGold(true);
				}
			}
//            else if (c.getType() == Type.HERO) {
//                Heroes.put(c.getName(), c);
//            } else if (c.getType() == Type.ENCHANTMENT) {
//                Enchantments.put(c.getName(), c);
//            }
		}

		for (Card c : All) {
			if (c.getMechanics() == null) {
				c.init();
			}
			if (c.getTechLevel() != null) {
				for (Token s : Token.values()) {
					if (s.toString().equalsIgnoreCase(c.getName().replaceAll("[^a-zA-Z0-9]", ""))) {
//                    if (s.toString().equalsIgnoreCase(c.getName().replace(" ", "").replace("-", ""))) {
						Tokens.put(s, c);
					}
				}
				if (c.getId().contains("BaconUps")) {
					c.setGold(true);
					GoldMinions.put(c.getName(), c);
				} else {
					Minions.put(c.getName(), c);
					if (c.getCost() == 2 && !invalidTwoCost.contains(c.getName())) {
						TwoCostPool.put(c.getName(), c);
					} else if (c.getRarity() == Rarity.LEGENDARY && !invalidLegendaries.contains(c.getName())) {
						LegendaryPool.put(c.getName(), c);
					} else if (!invalidDeathrattles.contains(c.getName()) && c.getMechanics().contains(Mechanics.DEATHRATTLE)) {
						DeathrattlePool.put(c.getName(), c);
					}
				}
			}
		}

		Reflections reflections = new Reflections("com.alanbaumgartner.bgsim.deathrattles");
		Set<Class<? extends com.alanbaumgartner.bgsim.deathrattles.Deathrattle>> allClasses = reflections.getSubTypesOf(Deathrattle.class);
		for (Class<? extends com.alanbaumgartner.bgsim.deathrattles.Deathrattle> s : allClasses) {
			try {
				Deathrattle dr = s.getConstructor().newInstance();
				dr.init();
				deathrattleMap.put(s.getSimpleName(), dr);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

//        int i = 0;
//        for (Map.Entry<String, Card> entry : Minions.entrySet()) {
//            if (!entry.getValue().isGold()) {
//                System.out.println(entry.getValue().getName() + i);
//            }
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

	public static int getRandomInteger(int max) {
		return rand.nextInt(max);
	}

	public static int getUniqueRandomInteger(int max, List<Integer> exclude) {
		int random = rand.nextInt(max);
		while (exclude.contains(random)) {
			random = rand.nextInt(max);
		}
		return random;
	}

	public static Card getRandomDeathrattle() {
		return (Card) DeathrattlePool.values().toArray()[(Main.getRandomInteger(DeathrattlePool.size()))];
	}

	public static Card getRandomLegendary() {
		return (Card) LegendaryPool.values().toArray()[(Main.getRandomInteger(LegendaryPool.size()))];
	}

	public static Card getRandomTwoCost() {
		return (Card) TwoCostPool.values().toArray()[(Main.getRandomInteger(TwoCostPool.size()))];
	}

	public static Deathrattle getDeathrattle(Card card) {
		Matcher matcher = pattern.matcher(card.getName());
		String s = matcher.replaceAll("");
		return Main.deathrattleMap.get(s);
	}

	public static Card getCard(String name, Map<String, Card> map) {
		return (Card) map.get(name).clone();
	}

	public static void main(String[] args) {
		Player one = new Player(new ArrayList(Arrays.asList(getCard("Ghastcoiler", Minions))));
		Player two = new Player(new ArrayList(Arrays.asList(getCard("Ghastcoiler", Minions))));
		Simulation sim = new Simulation(one, two, 10000);
		sim.simulate();

	}

}
