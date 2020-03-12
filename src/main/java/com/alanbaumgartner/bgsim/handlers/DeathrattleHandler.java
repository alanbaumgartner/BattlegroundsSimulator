package com.alanbaumgartner.bgsim.handlers;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.deathrattles.Deathrattle;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.List;

public class DeathrattleHandler extends Handler {

    public DeathrattleHandler(Player one, Player two) {
        super(one, two);
    }

    @Override
    public void update(Card c) {
        int index = getIndexAndRemove(c);
        Player player = c.getPlayer();
        Player opponent = player.equals(one) ? two : one;

        if (!c.getMechanics().contains(Mechanics.DEATHRATTLE)) {
            return;
        }

        Deathrattle dr = Main.getDeathrattle(c);
        List<Card> summons = null;
        switch (dr.getType()) {
            case BUFF:
                dr.Simulate(c, player);
                break;
            case SUMMON:
                summons = dr.Simulate(c);
                break;
            case ATTACK:
                dr.Simulate(c, opponent);
                break;
        }

        if (summons != null) {
            for (Card card : summons) {
                player.addCard(index, card);
            }
        }

    }

    //    public int handleDeaths() {
//        int totalDeaths = 0;
//        int deaths;
//        do {
//            deaths = 0;
//            Iterator<Card> iter = one.getMinions().iterator();
//            while (iter.hasNext()) {
//                Card c = iter.next();
//                if (c.getHealth() <= 0) {
//                    c.setDead(true);
//                    deaths++;
//                    deaths += handleDeathrattle(c, getIndexAndRemove(c, iter));
//                }
//            }
//            iter = two.getMinions().iterator();
//            while (iter.hasNext()) {
//                Card c = iter.next();
//                if (c.getHealth() <= 0) {
//                    c.setDead(true);
//                    deaths++;
//                    deaths += handleDeathrattle(c, getIndexAndRemove(c, iter));
//                }
//            }
//            totalDeaths += deaths;
//        } while (deaths != 0);
//        return totalDeaths;
//    }
//
    private int getIndexAndRemove(Card card) {
        int index = card.getPlayer().indexOfCard(card);
        card.getPlayer().removeCard(card);
        return index;
    }
//
//    private int handleDeathrattle(Card card, int index) {
//        if (!card.getMechanics().contains(Mechanics.DEATHRATTLE)) {
//            return 0;
//        }
//        Deathrattle dr = Main.getDeathrattle(card);
//        List<Card> killed = null;
//        switch (dr.getType()) {
//            case BUFF:
//            case SUMMON:
//                killed = dr.Simulate(card, card.getPlayer(), card.getPlayer() == one ? one : two, index);
//                break;
//            case ATTACK:
//                killed = dr.Simulate(card, card.getPlayer(), card.getPlayer() == one ? two : one, index);
//                break;
//        }
//        return killed == null ? 0 : killed.size();
//    }
}
