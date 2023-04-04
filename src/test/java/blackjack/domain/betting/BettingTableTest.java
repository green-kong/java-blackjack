package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participants.Player;

class BettingTableTest {
    BettingTable bettingTable;

    @BeforeEach
    void setUp() {
        bettingTable = new BettingTable();
    }

    @Test
    @DisplayName("init 테스트 players를 받아서 모두 null로 처리한다.")
    void init() {
        Player player1 = new Player("폴로");
        Player player2 = new Player("로지");
        Player player3 = new Player("마코");
        List<Player> players = List.of(player1, player2, player3);

        bettingTable.init(players);

        assertAll(
                () -> assertThat(bettingTable.getMoney(player1)).isNull(),
                () -> assertThat(bettingTable.getMoney(player2)).isNull(),
                () -> assertThat(bettingTable.getMoney(player3)).isNull()
        );
    }

    @Test
    @DisplayName("player와 money를 받아서 저장한다.")
    void put() {
        Player player = new Player("폴로");

        bettingTable.put(player, new Money(1000));

        assertThat(bettingTable.getMoney(player)).isEqualTo(new Money(1000));
    }
}
