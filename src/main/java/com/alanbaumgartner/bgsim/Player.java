package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.Hero;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private Integer health;
    private Integer tier;
    private Hero hero;
    private List<Card> minions;
    private Deque<Card> attackers;

    public Player(Player copy) {
        health = copy.health;
        tier = copy.tier;
        hero = copy.hero;
        minions = new ArrayList<>();
        for (Card c : copy.getMinions()) {
            minions.add((Card) c.clone());
        }
        attackers = new LinkedList<>(minions);
    }

    public Player(List<Card> minions) {
        this.minions = minions;
        this.health = 40;
        this.tier = 1;
        this.hero = Hero.DEATHWING;
    }

    public Integer getTier() {
        return tier;
    }

    public List<Card> getTauntMinions() {
        return minions.stream().filter(c -> c.getMechanics().contains(Mechanics.TAUNT)).collect(Collectors.toList());
    }

    public Integer getNumMinions() {
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

    public void addCard(Integer index, Card c) {
        if (minions.size() < 7) {
            minions.add(index, c);
            reorderQueue(index, c);
        }
    }

    private void reorderQueue(Integer index, Card c) {
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