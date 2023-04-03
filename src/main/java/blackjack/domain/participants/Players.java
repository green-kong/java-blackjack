package blackjack.domain.participants;

import java.util.ArrayList;
import java.util.List;

public final class Players {
    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
