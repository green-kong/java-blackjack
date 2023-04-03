package blackjack.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participants.Name;

class NameTest {
    @Test
    @DisplayName("이름을 생성한다.")
    void construct() {
        Name name = new Name("폴로");

        assertThat(name).isEqualTo(new Name("폴로"));
    }

    @Test
    @DisplayName("이름이 다섯글자가 넘는 경우 예외가 발생한다.")
    void constructFailByLength() {
        assertThatThrownBy(() -> new Name("asdfgg"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1글자 이상 5글자 이하의 이름만 가능합니다.");
    }

    @Test
    @DisplayName("이름에 공백만 있는 경우 예외가 발생한다.")
    void constructFailByBlank() {
        assertThatThrownBy(() -> new Name("  "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1글자 이상 5글자 이하의 이름만 가능합니다.");
    }
}
