package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.*;

import java.util.List;

public class Card {

    // Json Variables
    private String name;
    private Integer attack;
    private Integer health;
    private Rarity rarity;
    private Race race;
    private Integer techLevel;
    private List<Mechanics> mechanics;

    private String id;
    private Integer cost;
    private Type type;

    //
    private Deathrattle deathrattle;


    // Non JSON variables
    private Boolean dead = false;
    private Boolean gold;

    public void Initialize() {
        dead = false;
        gold = id.contains("BaconUps");
    }


    public void Attack(Card c) {
        Integer defenderAttack = c.getAttack();
        Integer defenderHealth = c.getHealth();
        List<Mechanics> defenderMechanics = c.getMechanics();

        // Handle Attacker Damage
        if (defenderMechanics.contains(Mechanics.DIVINE_SHIELD)) {
            c.getMechanics().remove(Mechanics.DIVINE_SHIELD);
        } else {
            if (this.mechanics.contains(Mechanics.POISONOUS)) {
                c.setDead(true);
            } else {
                defenderHealth -= this.attack;
                if (defenderHealth <= 0) {
                    c.setDead(true);
                } else {
                    c.setHealth(defenderHealth);
                }
            }
        }

        // Handle Defender Damage
        if (this.getMechanics().contains(Mechanics.DIVINE_SHIELD)) {
            this.getMechanics().remove(Mechanics.DIVINE_SHIELD);
        } else {
            if (c.getMechanics().contains(Mechanics.POISONOUS)) {
                this.setDead(true);
            } else {
                this.health -= defenderAttack;
                if (this.health <= 0) {
                    this.setDead(true);
                } else {
                    this.setHealth(defenderHealth);
                }
            }
        }
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    /**
     *
     * @return Card's deathrattle or null if none.
     */
    public Deathrattle getDeathrattle() {
        return deathrattle;
    }

    /**
     *
     * @return Whether the card is dead.
     */
    public Boolean getDead() {
        return dead;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return Card's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Card's attack value
     */
    public Integer getAttack() {
        return attack;
    }

    /**
     *
     * @return Mana cost to play the card.
     */
    public Integer getCost() {
        return cost;
    }

    /**
     *
     * @return Card's health value
     */
    public Integer getHealth() {
        return health;
    }

    /**
     *
     * @return Card's mechanics, full list can be found in Mechanics.
     */
    public List<Mechanics> getMechanics() {
        return mechanics;
    }

    /**
     *
     * @return Card's raroty, full list can be found in Rarity.
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     *
     * @return Card's type, such as minion, hero, enchantment. A full list can be found in Type.
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @return Card's tech level or the tavern tier at which it can be found/bought.
     */
    public Integer getTechLevel() {
        return techLevel;
    }

    /**
     *
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
