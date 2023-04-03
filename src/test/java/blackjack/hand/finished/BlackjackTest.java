package blackjack.hand.finished;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Cards;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.finished.Blackjack;
import blackjack.domain.hand.finished.Bust;
import blackjack.domain.hand.finished.Stay;
import blackjack.domain.result.Result;

class BlackjackTest {
    Hand hand;

    public static Stream<Arguments> provideBattleResults() {
        return Stream.of(
                Arguments.of(new Bust(new Cards(new ArrayList<>())), Result.BLACKJACK_WIN),
                Arguments.of(new Stay(new Cards(new ArrayList<>())), Result.BLACKJACK_WIN),
                Arguments.of(new Blackjack(new Cards(new ArrayList<>())), Result.TIE)
        );
    }

    @BeforeEach
    void setUp() {
        hand = new Blackjack(new Cards(new ArrayList<>()));
    }

    @ParameterizedTest(name = "상대방이 블랙잭인 경우는 TIE를 나머지의 경우는 BLACKJACK_WIN을 반환한다.")
    @MethodSource("provideBattleResults")
    void battle(Hand other, Result expected) {
        Result result = hand.battle(other);

        assertThat(result).isEqualTo(expected);
    }
}
