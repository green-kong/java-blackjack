package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.result.Result;

class PrizeCalculatorTest {
    public static Stream<Arguments> provideCalculateResults() {
        return Stream.of(
                Arguments.of(Result.BLACKJACK_WIN, new Money(1500)),
                Arguments.of(Result.WIN, new Money(1000)),
                Arguments.of(Result.TIE, new Money(0)),
                Arguments.of(Result.LOSE, new Money(-1000))
        );
    }

    @ParameterizedTest(name = "승무패 결과에 따른 최종 상금을 계산한다.")
    @MethodSource("provideCalculateResults")
    void calculate(Result result, Money expected) {
        Money money = new Money(1000);

        Money prize = PrizeCalculator.calculateByResult(money, result);

        assertThat(prize).isEqualTo(expected);
    }

}
