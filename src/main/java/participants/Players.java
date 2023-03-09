package participants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import card.Card;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }

    public void takeCard(int playerIndex, Card card) {
        Player player = players.get(playerIndex);
        player.hit(card);
    }

    public int count() {
        return players.size();
    }

    public boolean isBust(int playerIndex) {
        return players.get(playerIndex).isBust();
    }

    public List<PlayerResultDto> getPlayerResults() {
        return players.stream()
                .map(PlayerResultDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public void calculateWinning(Dealer dealer) {
        for (Player player : players) {
            compareScore(dealer, player);
        }
    }

    private void compareScore(Dealer dealer, Player player) {
        if (isPlayerBlackjackWin(dealer, player)) {
            player.winBlackjack();
            dealer.lose();
            return;
        }
        if (isPlayerWinDealer(dealer, player)) {
            player.win();
            dealer.lose();
            return;
        }
        if (isDealerWinPlayer(dealer, player)) {
            player.lose();
            dealer.win();
            return;
        }
        if (isDraw(dealer, player)) {
            player.tie();
            dealer.tie();
        }
    }

    private boolean isPlayerBlackjackWin(Dealer dealer, Player player) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private boolean isDraw(Dealer dealer, Player player) {
        return dealer.calculateScore() == player.calculateScore() && !player.isBust();
    }

    private boolean isDealerWinPlayer(Dealer dealer, Player player) {
        if (player.isBust()) {
            return true;
        }
        if (!player.isBlackjack() && dealer.isBlackjack()) {
            return true;
        }
        if (dealer.isBust()) {
            return false;
        }
        return dealer.calculateScore() > player.calculateScore();
    }

    private boolean isPlayerWinDealer(Dealer dealer, Player player) {
        if (player.isBust()) {
            return false;
        }
        if (dealer.isBust()) {
            return true;
        }
        return player.calculateScore() > dealer.calculateScore();
    }

    public List<PlayerWinningDto> getWinningResults() {
        return players.stream()
                .map(PlayerWinningDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<PlayerOpenDto> getPlayerCards() {
        return players.stream()
                .map(PlayerOpenDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public Name findPlayer(int playerIndex) {
        return players.get(playerIndex).getName();
    }

    public Player findPlayer(Name name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어 입니다."));
    }
}
