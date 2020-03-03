package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.factory.Card;

import java.util.List;

public interface Deathrattle {
	public void Simulate(Card c, List<Card> playerOneCards, List<Card> playerTwoCards);
}
