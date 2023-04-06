package blackjack.domain.participants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;

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

    public boolean isDealerUnderScore() {
        return dealer.isUnderScore();
    }

    public Map<Player, Result> calculateWinning() {
        Map<Player, Result> resultTable = new LinkedHashMap<>();
        List<Player> playersList = players.getPlayers();
        Participant dealerInfo = dealer.getParticipant();
        for (Player player : playersList) {
            Participant playerInfo = player.getInfo();
            Result result = playerInfo.battle(dealerInfo);
            resultTable.put(player, result);
        }
        return Collections.unmodifiableMap(new LinkedHashMap<>(resultTable));
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void stayDealer() {
        dealer.stay();
    }
}
