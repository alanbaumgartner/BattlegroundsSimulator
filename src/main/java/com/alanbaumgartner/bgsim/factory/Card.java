package com.alanbaumgartner.bgsim.factory;

import com.alanbaumgartner.bgsim.enums.*;

import java.util.List;

public class Card {

    private String id;
    private Integer dbfId;
    private String name;
    private String text;
    private String flavor;
    private String artist;
    private Integer attack;
    private CardClass cardClass;
    private Boolean collectible;
    private Integer cost;
    private Boolean elite;
    private Faction faction;
    private Integer health;
    private List<Mechanics> mechanics;
    private Rarity rarity;
    private CardSet set;
    private Type type;
    private Integer techLevel;
    private Integer battlegroundsPremiumDbfId;
    private Race race;

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
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @return
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     *
     * @return
     */
    public String getArtist() {
        return artist;
    }

    /**
     *
     * @return
     */
    public Integer getAttack() {
        return attack;
    }

    /**
     *
     * @return
     */
    public CardClass getCardClass() {
        return cardClass;
    }

    /**
     *
     * @return
     */
    public Boolean getCollectible() {
        return collectible;
    }

    /**
     *
     * @return
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
     * @return
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     *
     * @return
     */
    public Integer getHealth() {
        return health;
    }

    /**
     *
     * @return
     */
    public List<Mechanics> getMechanics() {
        return mechanics;
    }

    /**
     *
     * @return
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     *
     * @return
     */
    public CardSet getSet() {
        return set;
    }

    /**
     *
     * @return
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @return
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
     * @return
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
                ", text='" + text + '\'' +
                ", flavor='" + flavor + '\'' +
                ", artist='" + artist + '\'' +
                ", attack=" + attack +
                ", cardClass=" + cardClass +
                ", collectible=" + collectible +
                ", cost=" + cost +
                ", elite=" + elite +
                ", faction=" + faction +
                ", health=" + health +
                ", mechanics=" + mechanics.toString() +
                ", rarity=" + rarity +
                ", set=" + set +
                ", type=" + type +
                ", techLevel=" + techLevel +
                '}';
    }
}
