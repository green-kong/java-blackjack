package blackjack.domain.participants;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.running.Hit;
import blackjack.domain.result.Result;

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

    public Name getName() {
        return name;
    }

    public List<String> getCardNames() {
        return hand.getCards().getNames();
    }

    public boolean isDrawable() {
        return hand.isDrawable();
    }
}
