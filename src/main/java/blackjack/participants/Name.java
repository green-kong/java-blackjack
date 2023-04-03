package blackjack.participants;

import java.util.Objects;

public final class Name {
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;
    private static final String NAME_ERROR_MSG = "1글자 이상 5글자 이하의 이름만 가능합니다.";
    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(NAME_ERROR_MSG);
        }

        if (value.length() < MIN_NAME_LENGTH || value.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_ERROR_MSG);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
