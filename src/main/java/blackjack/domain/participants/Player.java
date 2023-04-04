package blackjack.domain.participants;

import blackjack.domain.card.Card;

public final class Player {
    private final Participant participant;

    public Player(String name) {
        this.participant = new Participant(name);
    }

    public Name getName() {
        return participant.getName();
    }

    public void takeCard(Card card) {
        participant.drawCard(card);
    }

    public Participant getInfo() {
        return participant;
    }

    public boolean isDrawable() {
        return participant.isDrawable();
    }
}
