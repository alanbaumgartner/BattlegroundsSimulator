package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class TortollanShellraiser extends Deathrattle {


    @Override
    public void init() {

        type = DType.BUFF;
    }

    @Override
    public List<Card> Simulate(Card card) {
        return null;
    }

    @Override
    public void Simulate(Card card, Player player) {
        if (player.getMinions().size() > 0) {
            int buffIndex = Main.getRandomInteger(player.getMinions().size());
            Card c = player.getMinions().get(buffIndex);
            if (card.isGold()) {
                c.setAttack(c.getAttack() + 1);
                c.setHealth(c.getHealth() + 1);
            } else {
                c.setAttack(c.getAttack() + 2);
                c.setHealth(c.getHealth() + 2);
            }
        }
    }
}
