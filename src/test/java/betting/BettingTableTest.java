package betting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import participants.Name;
import participants.Player;

class BettingTableTest {
    BettingTable bettingTable;

    @BeforeEach
    void setUp() {
        bettingTable = new BettingTable();
    }

    @Test
    @DisplayName("베팅맵에 플레이어와 어마운트를 저장한다")
    void saveBet() {
        Player player = new Player(new Name("폴로"));
        BettingAmount bettingAmount = new BettingAmount(1000);

        bettingTable.saveBet(player, bettingAmount);

        assertThat(bettingTable.getBettingAmountByPlayer(player)).isEqualTo(bettingAmount);
    }

    @Test
    @DisplayName("베팅맵에 플레이어와 어마운트를 저장한다")
    void calculateDealerReward() {
        Player player = new Player(new Name("폴로"));
        BettingAmount bettingAmount = new BettingAmount(1000);
        player.win();

        bettingTable.saveBet(player, bettingAmount);

        assertThat(bettingTable.calculateDealerReward()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("베팅맵에 플레이어와 어마운트를 저장한다")
    void calculateDealerRewardBulk() {
        Player player1 = new Player(new Name("q"));
        Player player2 = new Player(new Name("w"));
        Player player3 = new Player(new Name("e"));
        BettingAmount bettingAmount1 = new BettingAmount(10000);
        BettingAmount bettingAmount2 = new BettingAmount(200);
        BettingAmount bettingAmount3 = new BettingAmount(3000);
        player1.winBlackjack();
        player2.winBlackjack();
        player3.lose();

        bettingTable.saveBet(player1, bettingAmount1);
        bettingTable.saveBet(player2, bettingAmount2);
        bettingTable.saveBet(player3, bettingAmount3);

        assertThat(bettingTable.calculateDealerReward()).isEqualTo(-12300);
    }
}
