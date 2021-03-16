package blackjack.domain.user;

import blackjack.domain.state.DealerTurnOver;
import blackjack.domain.state.State;

import java.math.BigDecimal;

public class Dealer extends AbstractUser {
    public static final int TURN_OVER_COUNT = 16;

    private final String name = "딜러";

    public Dealer(State state) {
        super(state, new BigDecimal("0"));
        checkTurnOver();
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= TURN_OVER_COUNT && !isFinish();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    private void checkTurnOver() {
        if (calculateScore() > TURN_OVER_COUNT) {
            changeState(new DealerTurnOver(getState().cards()));
        }
    }
}