package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class TortollanShellraiser extends Deathrattle {


    static {
        type = DType.BUFF;
    }


    @Override
    public List<Card> Simulate(Card card, Player player, List<Card> cards) {
        player.removeCard(card);
        if (cards.size() <= 0) {
            return null;
        }
        int index = Main.getRandomInteger(cards.size());
        Card c = cards.get(index);
        if (card.isGold()) {
            c.setAttack(c.getAttack() + 1);
            c.setHealth(c.getHealth() + 1);
        } else {
            c.setAttack(c.getAttack() + 2);
            c.setHealth(c.getHealth() + 2);
        }
        return null;
    }


}
