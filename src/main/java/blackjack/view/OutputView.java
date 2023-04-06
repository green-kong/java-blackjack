package blackjack.view;

import java.util.List;

import blackjack.dto.BettingResultDto;
import blackjack.dto.PlayerInfoDto;
import blackjack.dto.ScoreInfoDto;

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

    public void printCardResults(ScoreInfoDto dealerScoreInfo, List<ScoreInfoDto> playerDtos) {
        printCardResult(dealerScoreInfo);
        playerDtos.forEach(this::printCardResult);
    }

    private void printCardResult(ScoreInfoDto scoreInfo) {
        String cards = String.join(", ", scoreInfo.getCards());
        System.out.printf("%n%s 카드: %s - 결과: %d", scoreInfo.getName(), cards, scoreInfo.getScore());
    }

    public void printBettingResults(List<BettingResultDto> bettingResults) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        int dealerResult = calculateDealerResult(bettingResults);
        System.out.printf("%n딜러: %d", dealerResult);
        bettingResults.forEach(bettingResultDto -> System.out.printf(
                "%n%s: %d원", bettingResultDto.getName(), bettingResultDto.getPrize()));
    }

    private int calculateDealerResult(List<BettingResultDto> bettingResults) {
        return bettingResults.stream()
                .mapToInt(BettingResultDto::getPrize)
                .sum() * -1;
    }
}
