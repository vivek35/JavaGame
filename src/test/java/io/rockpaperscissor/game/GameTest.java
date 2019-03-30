package io.rockpaperscissor.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import io.rockpaperscissor.game.Game;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
	@Spy
	@InjectMocks
	private Game game;

	@Test
	public void startGame_NormalInput_Success() {

		game.startGame();

		verify(game, times(100)).getPlayerChoice(Game.PLAYER1);
		verify(game, times(100)).getPlayerChoice(Game.PLAYER2);
		verify(game, times(100)).compareSelection(anyInt(), anyInt());
	}

	@Test
	public void getPlayerChoice_WhenInputIsNegative_ShouldReturnZero() {

		int playerNumber = -1;
		int expectedChoice = 0;
		assertEquals(expectedChoice, game.getPlayerChoice(playerNumber));
	}

	@Test
	public void getPlayerChoice_WhenInvalidPlayerInput_ShouldReturnZero() {

		int playerNumber = 3;
		int expectedChoice = 0;
		assertEquals(expectedChoice, game.getPlayerChoice(playerNumber));
	}

	@Test
	public void getPlayerChoice_WhenPlayer1Input_ShouldReturnPaper() {

		int playerNumber = Game.PLAYER1;
		int expectedChoice = Game.PAPER;
		assertEquals(expectedChoice,game.getPlayerChoice(playerNumber));

	}

	@Test
	public void getPlayerChoice_WhenPlayer2Input_ShouldReturnRandomChoice() {

		int playerNumber = Game.PLAYER2;
		int actualChoice = game.getPlayerChoice(playerNumber);
		ArrayList<Integer> rock_paper_scissor = new ArrayList<>();
		rock_paper_scissor.add(1);
		rock_paper_scissor.add(2);
		rock_paper_scissor.add(3);
		assertTrue(rock_paper_scissor.contains(actualChoice));
	}

	@Test
	public void compareSelection_WhenChoice1NotPaper_ShouldReturnFalse() {
		int choice1 = Game.ROCK;
		int choice2 = Game.PAPER;

		assertFalse(game.compareSelection(choice1, choice2));
	}

	@Test
	public void compareSelection_WhenChoice2IsRock_Player1ShouldWin() {
		int choice1 = Game.PAPER;
		int choice2 = Game.ROCK;

		Game game = new Game();
		assertEquals(true, game.compareSelection(choice1, choice2));

		int expectedPlayer1Win = 1;
		int expectedPlayer2Win = 0;
		int expectedTie = 0;

		assertEquals(expectedPlayer1Win, game.getPlayer1());
		assertEquals(expectedPlayer2Win, game.getPlayer2());
		assertEquals(expectedTie, game.getTie());
	}

	@Test
	public void compareSelection_WhenChoice2IsScissor_Player2ShouldWin() {
		int choice1 = Game.PAPER;
		int choice2 = Game.SCISSOR;

		Game game = new Game();
		assertEquals(true, game.compareSelection(choice1, choice2));

		int expectedPlayer1Win = 0;
		int expectedPlayer2Win = 1;
		int expectedTie = 0;

		assertEquals(expectedPlayer1Win, game.getPlayer1());
		assertEquals(expectedPlayer2Win, game.getPlayer2());
		assertEquals(expectedTie, game.getTie());
	}

	@Test
	public void compareSelection_WhenChoice2IsPaper_ShouldTie() {

		game.setPlayer1(0);
		game.setPlayer2(0);
		game.setTie(0);

		int choice1 = Game.PAPER;
		int choice2 = Game.PAPER;

		assertEquals(true, game.compareSelection(choice1, choice2));

		int expectedPlayer1Win = 0;
		int expectedPlayer2Win = 0;
		int expectedTie = 1;

		assertEquals(expectedPlayer1Win, game.getPlayer1());
		assertEquals(expectedPlayer2Win, game.getPlayer2());
		assertEquals(expectedTie, game.getTie());
	}
}
