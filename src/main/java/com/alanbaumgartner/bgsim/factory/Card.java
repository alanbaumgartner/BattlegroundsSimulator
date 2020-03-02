package com.alanbaumgartner.bgsim.factory;

import com.alanbaumgartner.bgsim.enums.*;

import java.util.Arrays;

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
    private Mechanics[] mechanics;
    private Rarity rarity;
    private CardSet set;
    private Type type;
    private Integer techLevel;

    public String getId() {
        return id;
    }

    public Integer getDbfId() {
        return dbfId;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getArtist() {
        return artist;
    }

    public Integer getAttack() {
        return attack;
    }

    public CardClass getCardClass() {
        return cardClass;
    }

    public Boolean getCollectible() {
        return collectible;
    }

    public Integer getCost() {
        return cost;
    }

    public Boolean getElite() {
        return elite;
    }

    public Faction getFaction() {
        return faction;
    }

    public Integer getHealth() {
        return health;
    }

    public Mechanics[] getMechanics() {
        return mechanics;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public CardSet getSet() {
        return set;
    }

    public Type getType() {
        return type;
    }

    public Integer getTechLevel() {
        return techLevel;
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
                ", mechanics=" + Arrays.toString(mechanics) +
                ", rarity=" + rarity +
                ", set=" + set +
                ", type=" + type +
                ", techLevel=" + techLevel +
                '}';
    }
}
