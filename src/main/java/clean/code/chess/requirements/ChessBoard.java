package clean.code.chess.requirements;

import java.util.HashMap;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;
    public static int MAX_NUMBER_OF_PAWNS = 7; // for each color

    private Piece[][] spots;
    private HashMap<Pawn, Position> b_pawns = new HashMap<>();
    private HashMap<Pawn, Position> w_pawns = new HashMap<>();

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
        if (!IsLegalBoardPosition(xCoordinate, yCoordinate)) {
            pawn.setXCoordinate(-1);
            pawn.setYCoordinate(-1);
            return;
        }
        if (pieceColor.equals(PieceColor.BLACK)) {
            if (b_pawns.size() < MAX_NUMBER_OF_PAWNS) {
                    putOnBoard(pawn, xCoordinate, yCoordinate);
                    b_pawns.put(pawn, new Position(xCoordinate, yCoordinate));
            }
        } else if (pieceColor.equals(PieceColor.WHITE)) {
            if (w_pawns.size() < MAX_NUMBER_OF_PAWNS) {
                    putOnBoard(pawn, xCoordinate, yCoordinate);
                    w_pawns.put(pawn, new Position(xCoordinate, yCoordinate));
            }
        }
    }

    private void putOnBoard(Pawn pawn, int xCoordinate, int yCoordinate) {
        spots[xCoordinate][yCoordinate] = Piece.PAWN;
        pawn.setChessBoard(this);
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 7 || xCoordinate < 0 || yCoordinate >= 7 || yCoordinate < 0)
            return false;
        return spots[xCoordinate][yCoordinate] == Piece.EMPTY;
    }
}
