package blackjackGame;

import java.util.List;

import deck.Deck;
import player.Dealer;
import player.Player;
import player.PlayerResultDto;
import player.Players;

public class BlackjackGame {
    public static final int FIRST_DRAW_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void supplyCardsToDealer() {
        for (int i = 0; i < FIRST_DRAW_COUNT; i++) {
            dealer.hit(deck.drawCard());
        }
    }

    public void supplyCardsToPlayers() {
        int playerCount = players.count();
        for (int i = 0; i < playerCount; i++) {
            supplyCardToPlayer(i);
        }
    }

    private void supplyCardToPlayer(int i) {
        for (int j = 0; j < FIRST_DRAW_COUNT; j++) {
            players.takeCard(i, deck.drawCard());
        }
    }

    public void supplyAdditionalCard(int playerIndex) {
        players.takeCard(playerIndex, deck.drawCard());
    }

    public boolean isBust(int playerIndex) {
        return players.isBust(playerIndex);
    }

    public int countPlayer() {
        return players.count();
    }

    public boolean canDealerHit() {
        return !dealer.isBust() && dealer.isUnderScore();
    }

    public void supplyAdditionalCardToDealer() {
        dealer.hit(deck.drawCard());
    }

    public PlayerResultDto getDealerResult() {
        return PlayerResultDto.fromDealer(dealer);
    }


    public List<PlayerResultDto> getPlayerResults() {
        return players.getPlayerResults();
    }
}
