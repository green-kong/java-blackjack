package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements Participant {

    private final String name;
    private final ArrayList<Card> cards;
    private final ScoreRule scoreRule;

    public Dealer(String name, ArrayList<Card> cards, ScoreRule scoreRule) {
        this.name = name;
        this.cards = cards;
        this.scoreRule = scoreRule;
    }

    @Override
    public void receiveCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showCards() {
        return cards;
    }
}
