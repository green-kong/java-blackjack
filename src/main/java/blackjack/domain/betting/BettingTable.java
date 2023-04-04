package blackjack.domain.betting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.participants.Player;

public class BettingTable {
    private final Map<Player, Money> bettingTable = new LinkedHashMap<>();

    public void init(List<Player> players) {
        for (Player player : players) {
            bettingTable.put(player, null);
        }
    }

    public void put(Player player, Money money) {
        bettingTable.put(player, money);
    }
}
