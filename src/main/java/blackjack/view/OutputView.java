package blackjack.view;

import blackjack.dto.PlayerInfoDto;

public class OutputView {
    public void printDealerFirstOpen(PlayerInfoDto dealerInfo) {
        System.out.println(dealerInfo.getName() + ": " + dealerInfo.getCards().get(0));
    }

    public void printPlayerInfo(PlayerInfoDto playerInfoDto) {
        String cardNames = String.join(", ", playerInfoDto.getCards());
        System.out.println(playerInfoDto.getName() + ": " + cardNames);
    }

    public void printDealerDrawAdditionalCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
    }
}
