package blackjack.hand.finished;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.card.Rank;
import blackjack.card.Shape;
import blackjack.hand.Hand;
import blackjack.result.Result;

class StayTest {
    Hand hand;

    @BeforeEach
    void setUp() {
        List<Card> cardList = List.of(
                Card.of(Rank.TEN, Shape.HEART),
                Card.of(Rank.SEVEN, Shape.HEART)
        );
        hand = new Stay(new Cards(cardList));
    }

    @Test
    @DisplayName("점수가 같으면 TIE를 반환한다.")
    void battleTie() {
        List<Card> cardList = List.of(
                Card.of(Rank.TEN, Shape.HEART),
                Card.of(Rank.SEVEN, Shape.HEART)
        );
        Stay stay = new Stay(new Cards(cardList));

        Result result = hand.battle(stay);

        assertThat(result).isEqualTo(Result.TIE);
    }

    @Test
    @DisplayName("점수가 높으면 WIN을 반환한다.")
    void battleWin() {
        List<Card> cardList = List.of(
                Card.of(Rank.TEN, Shape.HEART),
                Card.of(Rank.SIX, Shape.HEART)
        );
        Stay stay = new Stay(new Cards(cardList));

        Result result = hand.battle(stay);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("점수가 낮으면 lose를 반환한다.")
    void battleLose() {
        List<Card> cardList = List.of(
                Card.of(Rank.TEN, Shape.HEART),
                Card.of(Rank.EIGHT, Shape.HEART)
        );
        Stay stay = new Stay(new Cards(cardList));

        Result result = hand.battle(stay);

        assertThat(result).isEqualTo(Result.LOSE);
    }


    @Test
    @DisplayName("상대방이 blackjack이면 lose를 반환한다.")
    void battleLoseByBlackJack() {
        var other = new Blackjack(new Cards(new ArrayList<>()));

        Result result = hand.battle(other);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("상대방이 bust이면 WIN를 반환한다.")
    void battleWinByBust() {
        var other = new Bust(new Cards(new ArrayList<>()));

        Result result = hand.battle(other);

        assertThat(result).isEqualTo(Result.WIN);
    }
}
