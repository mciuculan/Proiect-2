package clean.code.chess.requirements;

import java.util.HashMap;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;
    public static int MAX_NUMBER_OF_PAWNS = 16;

    private Piece[][] spots;
    private HashMap<Pawn, Position> pawns = new HashMap<>();

    public ChessBoard() {
        spots = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
        for (int i = 0; i < MAX_BOARD_WIDTH; i++) {
            for (int j = 0; j < MAX_BOARD_HEIGHT; j++) {
                spots[i][j] = Piece.EMPTY;
            }
        }
    }

    public void modifySpot(int x, int y, Piece type) {
        spots[x][y] = type;
    }

    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (pawns.size() < MAX_NUMBER_OF_PAWNS) {
            if (IsLegalBoardPosition(xCoordinate, yCoordinate)) {
                if ((pieceColor.equals(PieceColor.BLACK) && xCoordinate >= 6)
                        || pieceColor.equals(PieceColor.WHITE) && xCoordinate <= 1) {
                    spots[xCoordinate][yCoordinate] = Piece.PAWN;
                    pawn.setXCoordinate(xCoordinate);
                    pawn.setYCoordinate(yCoordinate);
                    pawns.put(pawn, new Position(xCoordinate, yCoordinate));
                }
            }
        }
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate <= 7 && xCoordinate >= 0 && yCoordinate <= 7
                && yCoordinate >= 0 && spots[xCoordinate][yCoordinate] == Piece.EMPTY;
    }
}
