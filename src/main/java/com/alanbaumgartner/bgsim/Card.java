package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.enums.Race;
import com.alanbaumgartner.bgsim.enums.Rarity;
import com.alanbaumgartner.bgsim.enums.Type;

import java.util.HashSet;

public class Card implements Cloneable {

    // Json Variables
    private String name;
    private int attack;
    private int health;
    private Rarity rarity;
    private Race race;
    private Integer techLevel;
    private HashSet<Mechanics> mechanics;

    private String id;
    private int cost;
    private Type type;

    // Non JSON variables
    private boolean dead = false;
    private boolean gold = false;
    private boolean reborn = false;

    public boolean hasReborn() {
        return reborn;
    }

    public void setReborn(boolean reborn) {
        this.reborn = reborn;
    }

    private int player;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public Boolean isGold() {
        return gold;
    }

    public void setGold(Boolean gold) {
        this.gold = gold;
    }

    public void init() {
        if (mechanics == null) {
            mechanics = new HashSet<>();
        }
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    public void attack(Card c) {
        // Handle Attacker Damage
        if (c.getMechanics().contains(Mechanics.DIVINE_SHIELD)) {
            c.getMechanics().remove(Mechanics.DIVINE_SHIELD);
        } else {
            if (this.getMechanics().contains(Mechanics.POISONOUS)) {
                c.setHealth(0);
            } else {
                c.setHealth(c.getHealth() - this.attack);
            }
        }

        // Handle Defender Damage
        if (this.getMechanics().contains(Mechanics.DIVINE_SHIELD)) {
            this.getMechanics().remove(Mechanics.DIVINE_SHIELD);
        } else {
            if (c.getMechanics().contains(Mechanics.POISONOUS)) {
                this.setHealth(0);
            } else {
                this.setHealth(this.health - c.getAttack());
            }
        }
    }

    /**
     * @return Whether the card is dead.
     */
    public Boolean isDead() {
        return dead;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @return Card's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Card's attack value
     */
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @return Mana cost to play the card.
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return Card's health value
     */
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            setDead(true);
        }
    }

    /**
     * @return Card's mechanics, full list can be found in Mechanics.
     */
    public HashSet<Mechanics> getMechanics() {
        return mechanics;
    }

    /**
     * @return Card's raroty, full list can be found in Rarity.
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * @return Card's type, such as minion, hero, enchantment. A full list can be found in Type.
     */
    public Type getType() {
        return type;
    }

    /**
     * @return Card's tech level or the tavern tier at which it can be found/bought.
     */
    public Integer getTechLevel() {
        return techLevel;
    }

    /**
     * @return Card's race, or more commonly known as a Tribe. A full list can be found in Race.
     */
    public Race getRace() {
        return race;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", attack=" + attack +
                ", cost=" + cost +
                ", health=" + health +
                ", mechanics=" + mechanics.toString() +
                ", rarity=" + rarity +
                ", type=" + type +
                ", techLevel=" + techLevel +
                '}';
    }
}
