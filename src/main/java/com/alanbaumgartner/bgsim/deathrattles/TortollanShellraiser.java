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
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        owner.removeCard(card);
        if (owner.getMinions().size() <= 0) {
            return null;
        }
        int buffIndex = Main.getRandomInteger(owner.getMinions().size());
        Card c = owner.getMinions().get(buffIndex);
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
