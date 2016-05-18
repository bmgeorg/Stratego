package model;

public class GameModel {
	Piece[][] board;
	boolean turn;
	Color color;
	boolean gameOver;
	
	GameModel(Piece[][] initialState, Color color) {
		board = initialState;
		this.color = color;
		if(color == Color.RED) turn = true;
		else turn = false;
		gameOver = false;
	}
	
	boolean MovePiece(int fromRow, int fromCol, int toRow, int toCol) {
		if(gameOver) return false;
		if(!turn) return false;
		if(board[fromRow][fromCol] == null) return false;
		if(board[fromRow][fromCol].getColor() != color) return false;
		if(board[toRow][toCol] != null && 
				board[toRow][toCol].getColor() == color) return false;
		
		if(board[fromRow][fromCol].getType() == Type.FLAG) return false;
		if(board[fromRow][fromCol].getType() == Type.BOMB) return false;
		
		if(toRow == fromRow && toCol == fromCol) return false;
		if(toRow != fromRow && toCol != fromCol) return false;
		
		if(board[toRow][toCol].getType() == Type.LAKE) return false;
		
		if(board[fromRow][fromCol].getType() == Type.SCOUT) {
			if(toRow == fromRow) {
				for(int i = fromCol + 1; i < toCol; i++) {
					if(board[toRow][i] != null) return false;
				}
			}
			if(toCol == fromCol) {
				for(int i = fromRow + 1; i < toRow; i++) {
					if(board[i][toCol] != null) return false;
				}
			}
			if(board[toRow][toCol] != null) {
				if(board[toRow][toCol].getType() == Type.FLAG) {
					gameOver = true;
					board[toRow][toCol] = board[fromRow][fromCol];
					board[fromRow][fromCol] = null;
				}
				else {
					if(board[toRow][toCol].getRank() < board[fromRow][fromCol].getRank()) {
						board[toRow][toCol] = board[fromRow][fromCol];
					}
					board[fromRow][fromCol] = null;
				}
			}
			else {
				board[toRow][toCol] = board[fromRow][fromCol];
				board[fromRow][fromCol] = null;
			}
		}
		else {
			if(toRow == fromRow && Math.abs(toCol - fromCol) != 1) return false;
			if(toCol == fromCol && Math.abs(toRow - fromRow) != 1) return false;
			if(board[toRow][toCol].getType() == Type.FLAG) {
				gameOver = true;
				board[toRow][toCol] = board[fromRow][fromCol];
				board[fromRow][fromCol] = null;
			}
			if(board[fromRow][fromCol].getType() == Type.MINER) {
				if(board[toRow][toCol] != null) {
					if(board[toRow][toCol].getType() == Type.BOMB || 
							board[toRow][toCol].getRank() < board[fromRow][fromCol].getRank()) {
						board[toRow][toCol] = board[fromRow][fromCol];
					}
				}
				else {
					board[toRow][toCol] = board[fromRow][fromCol];
				}
				board[fromRow][fromCol] = null;
			}
			else if(board[fromRow][fromCol].getType() == Type.SPY) {
				if(board[toRow][toCol] != null) {
					if(board[toRow][toCol].getType() != Type.BOMB) {
						board[toRow][toCol] = board[fromRow][fromCol];
					}
				}
				else {
					board[toRow][toCol] = board[fromRow][fromCol];
				}
				board[fromRow][fromCol] = null;
			}
			else {
				if(board[toRow][toCol] != null) {
					if(board[toRow][toCol].getRank() < board[fromRow][fromCol].getRank()) {
						board[toRow][toCol] = board[fromRow][fromCol];
					}
					board[fromRow][fromCol] = null;
				}
				else {
					board[toRow][toCol] = board[fromRow][fromCol];
					board[fromRow][fromCol] = null;
				}
			}
		}
		
		turn = false;
		return true;
	}
	// Checks if the local player has any valid moves
	boolean CanMove() {
		for(int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if(board[row][col] != null) {
					if(board[row][col].getType() != Type.LAKE && 
							board[row][col].getType() != Type.FLAG && 
							board[row][col].getType() != Type.BOMB &&
							board[row][col].getColor() == color) {
						if(row + 1 < 10 && (board[row + 1][col] == null || 
								(board[row + 1][col].getType() != Type.LAKE &&
								board[row + 1][col].getColor() != color))) {
							return true;
						}
						if(col + 1 < 10 && (board[row][col + 1] == null || 
								(board[row][col + 1].getType() != Type.LAKE &&
								board[row][col + 1].getColor() != color))) {
							return true;
						}
						if(row - 1 >= 0 && (board[row - 1][col] == null || 
								(board[row - 1][col].getType() != Type.LAKE &&
								board[row - 1][col].getColor() != color))) {
							return true;
						}
						if(col - 1 >= 0 && (board[row][col - 1] == null || 
								(board[row][col - 1].getType() != Type.LAKE &&
								board[row][col - 1].getColor() != color))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
