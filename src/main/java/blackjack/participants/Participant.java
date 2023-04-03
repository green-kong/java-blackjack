package blackjack.participants;

import java.util.ArrayList;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.hand.Hand;
import blackjack.hand.running.Hit;
import blackjack.result.Result;

public final class Participant {
    private final Name name;
    private Hand hand = new Hit(new Cards(new ArrayList<>()));

    public Participant(String name) {
        this.name = new Name(name);
    }

    public void drawCard(Card card) {
        hand = hand.hit(card);
    }

    public Result battle(Participant other) {
        return this.hand.battle(other.hand);
    }
}
