package blackjack.view;

import java.util.List;
import java.util.Scanner;

import blackjack.domain.participants.Name;
import utils.Converter;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);


    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return List.of(input.split(","));
    }

    public int readBettingAmount(Name name) {
        System.out.printf("%n%s의 배팅 금액은?%n", name.getValue());
        return Converter.convertToInt(scanner.nextLine());
    }
}
