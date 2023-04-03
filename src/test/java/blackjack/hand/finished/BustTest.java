package blackjack.hand.finished;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Cards;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.finished.Blackjack;
import blackjack.domain.hand.finished.Bust;
import blackjack.domain.hand.finished.Stay;
import blackjack.domain.result.Result;

class BustTest {

    public static Stream<Arguments> provideBustBattleResult() {
        return Stream.of(
                Arguments.of(new Blackjack(new Cards(new ArrayList<>()))),
                Arguments.of(new Bust(new Cards(new ArrayList<>()))),
                Arguments.of(new Stay(new Cards(new ArrayList<>())))
        );
    }

    @ParameterizedTest(name = "bust인 경우 상대방의 상태와 상관없이 Lose를 반환한다")
    @MethodSource("provideBustBattleResult")
    void battle(Hand other) {
        Hand hand = new Bust(new Cards(new ArrayList<>()));

        Result result = hand.battle(other);

        assertThat(result).isEqualTo(Result.LOSE);
    }
}
