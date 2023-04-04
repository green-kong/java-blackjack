package blackjack.controller;

import java.util.Arrays;

public enum Command {
    Y,
    N;

    public static Command find(String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y또는 n 만 입력 가능합니다."));
    }

    public boolean isYes() {
        return this == Y;
    }
}
