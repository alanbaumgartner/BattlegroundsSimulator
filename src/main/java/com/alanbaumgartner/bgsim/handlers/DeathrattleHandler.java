package com.alanbaumgartner.bgsim.handlers;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.deathrattles.Deathrattle;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.Iterator;
import java.util.List;

public class DeathrattleHandler extends Handler {

    public DeathrattleHandler(Player one, Player two) {
        super(one, two);
    }

    public int handleDeaths() {
        int totalDeaths = 0;
        int deaths;
        do {
            deaths = 0;
            Iterator<Card> iter = one.getMinions().iterator();
            while (iter.hasNext()) {
                Card c = iter.next();
                if (c.getHealth() <= 0) {
                    c.setDead(true);
                    deaths++;
                    deaths += handleDeathrattle(c);
                }
            }
            iter = two.getMinions().iterator();
            while (iter.hasNext()) {
                Card c = iter.next();
                if (c.getHealth() <= 0) {
                    c.setDead(true);
                    deaths++;
                    deaths += handleDeathrattle(c);
                }
            }
            totalDeaths += deaths;
        } while (deaths != 0);
        return totalDeaths;
    }

    private int handleDeathrattle(Card card) {
        int index = card.getPlayer().indexOfCard(card);
        card.getPlayer().removeCard(card);
        if (!card.getMechanics().contains(Mechanics.DEATHRATTLE)) {
            return 0;
        }
        Deathrattle dr = Main.getDeathrattle(card);
        List<Card> killed = null;
        switch (dr.getType()) {
            case BUFF:
            case SUMMON:
                killed = dr.Simulate(card, card.getPlayer(), card.getPlayer() == one ? one : two, index);
                break;
            case ATTACK:
                killed = dr.Simulate(card, card.getPlayer(), card.getPlayer() == one ? two : one, index);
                break;
        }
        return killed == null ? 0 : killed.size();
    }
}
