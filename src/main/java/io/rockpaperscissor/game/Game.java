package io.rockpaperscissor.game;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public static final int OBJECTS = 5;
	public static final int ROCK = 1, PAPER = 2, SCISSOR = 3, LIZARD = 4, FROK=5;
	public static final int PLAYER1 = 1, PLAYER2 = 2;

	private int player1;
	private int player2;
	private int tie;

	public static void main(String[] args) {
		int user_choice=0;
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to ROCK-PAPER-SCISSOR game");
		Game game = new Game();		
		do {			
			game.startGame();
			game.showResult();
			System.out.println("Play 1 to play again and 0 to terminate");
			while(!input.hasNextInt()) {				
				String input_not_integer = input.next();
				System.out.println("Please enter input as 0 or 1");
			}
			do {
				user_choice=input.nextInt();
				if(user_choice==0) {
					System.out.println("Thank you for playing.");
					return;
				}
				if(user_choice!=1) {
					System.out.println("Please enter input as 0 or 1");
				}
			}while(user_choice!=1);
		}while(user_choice!=0);
	}

	public void startGame() {

		int counter = 100;
		player1=0;
		player2=0;
		tie=0;
		do {

			int choice1 = getPlayerChoice(PLAYER1); // getting input for player1
			int choice2 = getPlayerChoice(PLAYER2); // getting input for player2

			if (compareSelection(choice1, choice2)) {
				counter--;
			}

		} while (counter > 0);
	}

	/**
	 * Shows the result of game with number of wins for each player.
	 */
	private void showResult() {
		System.out.println("Player 1 wins " + player1 + " out of 100 times");
		System.out.println("Player 2 wins " + player2 + " out of 100 times");
		System.out.println("Tie " + tie + " out of 100 times");

		if (player1 > player2) {
			System.out.println("Winner is player1 (" + player1 + " to " + player2 + " wins)");
		}else if(player1 == player2) {
			System.out.println("Scores are equal)");
		} else {
			System.out.println("Winner is player2 (" + player2 + " to " + player1 + " wins)");
		}
	}

	/**
	 * Compares the selection of both players to decide the winner of the round
	 * 
	 * @param choice1
	 * @param choice2
	 * @return true if round succeeded with values, false otherwise
	 */
	public boolean compareSelection(int choice1, int choice2) {
		boolean player1_input = choice1 == PAPER;
		boolean player2_input = (choice2 > 0 && choice2 <= 3);

		if (player1_input && player2_input) {
			switch (choice2) {

			case ROCK:
				player1++;
				break;

			case SCISSOR:
				player2++;
				break;

			case PAPER:
			default:
				tie++;
				break;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines the choice of the player <br>
	 * If player1 then returns PAPER <br>
	 * If player2 then returns random selection out of ROCK, PAPER & SCISSOR
	 * 
	 * @param player
	 * @return
	 */
	public int getPlayerChoice(int player) {
		if (player < 1 || player > 2) {
			return 0;
		}
		if (player == 1) {
			return PAPER;
		} else {
			Random r = new Random();
			int random = r.nextInt(OBJECTS) + 1;
			return random;
		}
	}

	public int getPlayer1() {
		return player1;
	}

	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	public int getPlayer2() {
		return player2;
	}

	public void setPlayer2(int player2) {
		this.player2 = player2;
	}

	public int getTie() {
		return tie;
	}

	public void setTie(int tie) {
		this.tie = tie;
	}

}
