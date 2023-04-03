package blackjack.domain.participants;

import blackjack.domain.card.Card;

public class Dealer {
    public static final String DEALER_NAME = "딜러";
    private final Participant participant;

    public Dealer() {
        this.participant = new Participant(DEALER_NAME);
    }

    public void takeCard(Card card) {
        participant.drawCard(card);
    }

    public Participant getParticipant() {
        return participant;
    }
}
