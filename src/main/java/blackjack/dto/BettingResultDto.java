package blackjack.dto;

import blackjack.domain.betting.Money;
import blackjack.domain.participants.Name;

public class BettingResultDto {
    private final String name;
    private final int prize;

    private BettingResultDto(String name, int prize) {
        this.name = name;
        this.prize = prize;
    }

    public static BettingResultDto of(Name name, Money money) {
        return new BettingResultDto(name.getValue(), money.getValue());
    }

    public String getName() {
        return name;
    }

    public int getPrize() {
        return prize;
    }
}
