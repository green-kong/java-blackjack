package blackjack.domain.result;

public enum Result {
    BLACKJACK_WIN(1.5),
    WIN(1),
    TIE(0),
    LOSE(-1);

    private final double rate;

    Result(double rate) {
        this.rate = rate;
    }

    public double calculateRate(int value) {
        return value * this.rate;
    }
}
