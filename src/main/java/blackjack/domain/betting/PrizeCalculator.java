package blackjack.domain.betting;

import blackjack.domain.result.Result;

public class PrizeCalculator {
    public static Money calculateByResult(Money money, Result result) {
        return new Money((int) result.calculateRate(money.getValue()));
    }
}
