package blackjack.domain.participants;

import java.util.List;

import blackjack.domain.card.Card;

public final class Participants {
    private final Dealer dealer = new Dealer();
    private final Players players = new Players();

    public void addPlayers(List<Player> playerList) {
        for (Player player : playerList) {
            players.addPlayer(player);
        }
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public void drawToDealer(Card card) {
        dealer.takeCard(card);
    }

    public Participant getDealerInfo() {
        return dealer.getParticipant();
    }

    public boolean isDealerDrawable() {
        return dealer.isUnderScore();
    }
}
