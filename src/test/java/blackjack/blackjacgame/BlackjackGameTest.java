package blackjack.blackjacgame;

import static blackjackgame.Result.LOSE;
import static blackjackgame.Result.TIE;
import static blackjackgame.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.fixedCaradsGenerator.FixedCardsGenerator;
import blackjackgame.BlackjackGame;
import blackjackgame.Result;
import card.Card;
import card.Rank;
import card.Suit;
import deck.CardsGenerator;
import deck.Deck;
import dto.DealerWinningDto;
import player.Dealer;
import player.Name;
import player.Player;
import player.Players;

class BlackjackGameTest {
    BlackjackGame blackjackGame;
    Players players;
    Dealer dealer;
    Deck deck;

    @BeforeEach
    void setUp() {
        List<Card> cards = List.of(
                new Card(Rank.ACE, Suit.HEART),
                new Card(Rank.ACE, Suit.CLOVER),
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.ACE, Suit.DIAMOND)
        );
        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator(cards);
        deck = new Deck(fixedCardsGenerator);
        players = new Players();
        dealer = new Dealer();
        blackjackGame = new BlackjackGame(players, dealer, deck);
    }

    @Test
    @DisplayName("플레이어를 만들고 플레이어즈에 추가할 수 있다")
    void addPlayer() {
        blackjackGame.addPlayer(new Player(new Name("폴로")));
        blackjackGame.addPlayer(new Player(new Name("로지")));

        assertThat(players.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러에게 카드를 두 장 줄 수 있다.")
    void supplyCardsToDealer() {
        blackjackGame.supplyCardsToDealer();

        assertThat(dealer.showCards())
                .contains(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.ACE, Suit.SPADE));
    }

    @Test
    @DisplayName("플레이어들에게 카드를 두 장씩 줄 수 있다.")
    void supplyCardsToPlayers() {
        Player player1 = new Player(new Name("폴로"));
        Player player2 = new Player(new Name("로지"));
        blackjackGame.addPlayer(player1);
        blackjackGame.addPlayer(player2);

        blackjackGame.supplyCardsToPlayers();

        assertThat(player1.showCards()).contains(new Card(Rank.ACE, Suit.DIAMOND),
                new Card(Rank.ACE, Suit.SPADE));
        assertThat(player2.showCards()).contains(new Card(Rank.ACE, Suit.CLOVER),
                new Card(Rank.ACE, Suit.HEART));

    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어는 카드를 한장 추가로 받을 수 있다.")
    void supplyAdditionalCard() {
        Player player1 = new Player(new Name("폴로"));
        blackjackGame.addPlayer(player1);
        blackjackGame.supplyCardsToPlayers();

        blackjackGame.supplyAdditionalCard(0);

        assertThat(player1.showCards()).contains(new Card(Rank.ACE, Suit.DIAMOND),
                new Card(Rank.ACE, Suit.SPADE), new Card(Rank.ACE, Suit.CLOVER));
    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어가 버스트인 경우 true를 반환한다.")
    void isBust() {
        List<Card> cards = List.of(
                new Card(Rank.KING, Suit.HEART),
                new Card(Rank.KING, Suit.DIAMOND),
                new Card(Rank.KING, Suit.SPADE),
                new Card(Rank.KING, Suit.CLOVER)
        );

        Deck fixedDeck = new Deck(new FixedCardsGenerator(cards));
        blackjackGame = new BlackjackGame(players, dealer, fixedDeck);

        Player player1 = new Player(new Name("폴로"));
        blackjackGame.addPlayer(player1);
        blackjackGame.supplyCardsToPlayers();
        blackjackGame.supplyAdditionalCard(0);

        assertThat(blackjackGame.isBust(0)).isTrue();
    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어가 버스트가 아닌경우 false를 반환한다.")
    void isBustFalse() {
        Player player1 = new Player(new Name("폴로"));
        blackjackGame.addPlayer(player1);
        blackjackGame.supplyCardsToPlayers();
        blackjackGame.supplyAdditionalCard(0);

        assertThat(blackjackGame.isBust(0)).isFalse();
    }


    @Test
    @DisplayName("현재 플레이어의 인원수를 반환할 수 있다.")
    void countPlayers() {
        Player player1 = new Player(new Name("폴로"));
        Player player2 = new Player(new Name("로지"));
        blackjackGame.addPlayer(player1);
        blackjackGame.addPlayer(player2);

        assertThat(blackjackGame.countPlayer()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러에게 추가카드를 줄 수 있다.")
    void supplyAdditionalCardToDealer() {
        int beforeSize = dealer.showCards().size();
        blackjackGame.supplyAdditionalCardToDealer();
        int afterSize = dealer.showCards().size();

        assertThat(afterSize - beforeSize).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 게임 결과를 반환할 수 있다.")
    void getDealerWinningResult() {
        dealer.win();
        dealer.win();
        dealer.lose();
        dealer.tie();

        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        Name name = dealerWinningResult.getName();
        Map<Result, Integer> dealerResult = dealerWinningResult.getWinningMap();

        assertThat(name.getValue()).isEqualTo("딜러");
        assertThat(dealerResult.get(WIN)).isEqualTo(2);
        assertThat(dealerResult.get(LOSE)).isEqualTo(1);
        assertThat(dealerResult.get(TIE)).isEqualTo(1);
    }

    @Nested
    @DisplayName("딜러가 카드를 추가로 받을 수 있는지 확인하는 기능")
    class canDealerHitTest {
        @Test
        @DisplayName("딜러가 버스트가 아니고 언더스코어인 경우")
        void canDealerHitUnderScoreAndNotBust() {
            dealer.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.TWO, Suit.SPADE));

            assertThat(blackjackGame.canDealerHit()).isTrue();
        }

        @Test
        @DisplayName("딜러가 버스트인경우")
        void cantDealerHitCuzBust() {
            dealer.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.KING, Suit.SPADE));
            dealer.hit(new Card(Rank.TWO, Suit.SPADE));

            assertThat(blackjackGame.canDealerHit()).isFalse();
        }

        @Test
        @DisplayName("버스트가 아니고, 언더스코어가 아닌경우")
        void canDealerHit() {
            dealer.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.EIGHT, Suit.SPADE));

            assertThat(blackjackGame.canDealerHit()).isFalse();
        }
    }
}
