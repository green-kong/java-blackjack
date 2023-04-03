package blackjack.participants;

import blackjack.card.Card;
import blackjack.hand.Hand;

public final class Participant {
    private final Name name;
    private Hand hand;

    public Participant(String name) {
        this.name = new Name(name);
    }

    public void drawCard(Card card) {
        hand = hand.hit(card);
    }
}
