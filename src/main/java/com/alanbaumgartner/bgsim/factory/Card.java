package com.alanbaumgartner.bgsim.factory;

import com.alanbaumgartner.bgsim.Deathrattle;
import com.alanbaumgartner.bgsim.enums.*;

import java.util.List;

public class Card {

    // Json Variables
    private String id;
    private Integer dbfId;
    private String name;
    private Integer attack;
    private CardClass cardClass;
    private Integer cost;
    private Boolean elite;
    private Integer health;
    private List<Mechanics> mechanics;
    private Rarity rarity;
    private CardSet set;
    private Type type;
    private Integer techLevel;
    private Integer battlegroundsPremiumDbfId;
    private Race race;

    //
    private Deathrattle deathrattle;

    // Combat instance variables
    private Boolean dead;


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

    public Deathrattle Die() {


        return null;
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

    // Getters

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
     * @return
     */
    public Integer getDbfId() {
        return dbfId;
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
     * @return Card's class affiliation, possible values found in CardClass
     */
    public CardClass getCardClass() {
        return cardClass;
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
     * @return
     */
    public Boolean getElite() {
        return elite;
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
     * @return Card's set, full list can be found in CardSet.
     */
    public CardSet getSet() {
        return set;
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
     * @return
     */
    public Integer getBattlegroundsPremiumDbfId() {
        return battlegroundsPremiumDbfId;
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
                ", dbfId=" + dbfId +
                ", name='" + name + '\'' +
                ", attack=" + attack +
                ", cardClass=" + cardClass +
                ", cost=" + cost +
                ", elite=" + elite +
                ", health=" + health +
                ", mechanics=" + mechanics.toString() +
                ", rarity=" + rarity +
                ", set=" + set +
                ", type=" + type +
                ", techLevel=" + techLevel +
                '}';
    }
}
