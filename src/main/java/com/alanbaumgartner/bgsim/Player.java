package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.Hero;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.*;

public class Player {

    private int health;
    private int tier;
    private Hero hero;
    private List<Card> minions;
    private Deque<Card> attackers;

    private Set<Card> deadMechs;

    public Player(Player copy) {
        health = copy.health;
        tier = copy.tier;
        hero = copy.hero;
        minions = new ArrayList<>();
        for (Card c : copy.getMinions()) {
            Card cloned = (Card) c.clone();
            cloned.setPlayer(this);
            minions.add(cloned);
        }
        attackers = new LinkedList<>(minions);
    }

    public Player(List<Card> minions) {
        this.minions = minions;
        this.health = 40;
        this.tier = 1;
        this.hero = Hero.DEATHWING;
    }

    public int indexOfCard(Card c) {
        return Math.max(minions.indexOf(c), 0);

    }

    public int getTier() {
        return tier;
    }

    public List<Card> getTauntMinions() {
        ArrayList<Card> list = new ArrayList<>();
        for (Card c : minions) {
            if (c.getMechanics().contains(Mechanics.TAUNT)) {
                list.add(c);
            }
        }
        return list;
//        return minions.stream().filter(c -> c.getMechanics().contains(Mechanics.TAUNT)).collect(Collectors.toList());
    }

    public int getNumMinions() {
        return minions.size();
    }

    public List<Card> getMinions() {
        return minions;
    }

    public Card getNextAttacker() {
        return requeue();
    }

    private Card requeue() {
        Card card = attackers.remove();
        attackers.add(card);
        return card;
    }

    public void removeCard(Card c) {
        this.minions.remove(c);
        this.attackers.remove(c);
    }

    public void addCard(int index, Card c) {
        c.setPlayer(this);
        if (minions.size() < 7) {
            minions.add(index, c);
            reorderQueue(index, c);
        }
    }

    private void reorderQueue(int index, Card c) {
        int size = attackers.size();
        for (int i = 0; i < size + 1; i++) {
            if (i == index) {
                attackers.add(c);
            }
            requeue();
        }
    }

    public void heroPower() {
        switch (hero) {
            case DEATHWING:
                break;
            case NEFARIAN:
                break;
            case PATCHESTHEPIRATE:
                break;
            case RAGNAROSTHEFIRELORD:
                break;
            case PROFESSORPUTRICIDE:
                break;
            case THEGREATAKAZAMZARAK:
                break;
            case GIANTFIN:
                break;
            default:
                break;
        }
    }
}