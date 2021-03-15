package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.money.BettingMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(new Nickname("pika"));
    }

    @Test
    @DisplayName("플레이어 생성 성공")
    void createPlayerSucceed() {
        assertThat(player).isEqualTo(new Player(new Nickname("pika")));
    }

    @Test
    @DisplayName("플레이어 카드 추가 성공")
    void receiveCard() {
        player.firstDraw(
                new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.SPADE, Denomination.THREE)
        );
        player.draw(new Card(Shape.SPADE, Denomination.FOUR));
        Cards cards = player.getCurrentCards();
        assertThat(cards.getCards().get(2)).isEqualTo(new Card(Shape.SPADE, Denomination.FOUR));
    }

    @Test
    @DisplayName("플레이어 카드 반환 성공")
    void getPlayerCards() {
        player.firstDraw(
                new Card(Shape.SPADE, Denomination.FOUR),
                new Card(Shape.CLOVER, Denomination.THREE)
        );
        player.draw(new Card(Shape.HEART, Denomination.ACE));

        Cards cards = player.getCurrentCards();
        assertTrue(cards.getCards().containsAll(Arrays.asList(new Card(Shape.SPADE, Denomination.FOUR),
                new Card(Shape.CLOVER, Denomination.THREE),
                new Card(Shape.HEART, Denomination.ACE))));
    }

    @Test
    @DisplayName("카드를 더 뽑을 수 있는지 확인")
    void canDraw() {
        player.firstDraw(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.DIAMOND, Denomination.THREE)
        );
        player.draw(new Card(Shape.DIAMOND, Denomination.KING));
        assertTrue(player.canDraw());
    }

    @Test
    @DisplayName("카드를 뽑을 수 없는 경우 확인")
    void cannotDraw() {
        player.firstDraw(
                new Card(Shape.DIAMOND, Denomination.KING),
                new Card(Shape.DIAMOND, Denomination.THREE)
        );
        player.draw(new Card(Shape.DIAMOND, Denomination.JACK));
        assertFalse(player.canDraw());
    }

    @Test
    @DisplayName("베팅 성공")
    void bettingSucceed() {
        player.betting(new BettingMoney("10000"));
        assertNotNull(player.bettingMoney);
    }

    @Test
    @DisplayName("베팅은 한 번만 가능")
    void justOneBetting() {
        assertThatThrownBy(() -> {
            player.betting(new BettingMoney("10000"));
            player.betting(new BettingMoney("10000"));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}