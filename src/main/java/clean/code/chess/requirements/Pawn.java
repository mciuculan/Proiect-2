package clean.code.chess.requirements;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void Move(MovementType movementType, int newX, int newY) {
        if (checkLegal(movementType, newX, newY)) {
            int oldX = xCoordinate;
            int oldY = yCoordinate;
            this.xCoordinate = newX;
            this.yCoordinate = newY;
            chessBoard.modifySpot(oldX, oldY, Piece.EMPTY);
            chessBoard.modifySpot(xCoordinate, yCoordinate, Piece.PAWN);
        }
    }

    public boolean checkLegal(MovementType movementType, int x, int y) {
        if (!chessBoard.IsLegalBoardPosition(x, y))
            return false;
        if (pieceColor.equals(PieceColor.WHITE)) {
            if (movementType.equals(MovementType.MOVE)) {
                /*if (yCoordinate == 1) {
                    if (y == yCoordinate + 2 && x == xCoordinate) {
                        return true;
                    }
                }*/
                if (x != xCoordinate || y != yCoordinate + 1) {
                    return false;
                }
//                return true;
            }
            if (movementType.equals(MovementType.CAPTURE)) {
                if (x == xCoordinate + 1 && y == yCoordinate + 1)
                    return true;
                if (x == xCoordinate - 1 && y == yCoordinate + 1)
                    return true;
                return false;
            }
        }
        if (pieceColor.equals(PieceColor.BLACK)) {
            if (movementType.equals(MovementType.MOVE)) {
                /*if (yCoordinate == 6) {
                    if (y == yCoordinate - 2 && x == xCoordinate) {
                        return true;
                    }
                }*/
                if (x != xCoordinate || y != yCoordinate - 1) {
                    return false;
                }
//                return true;
            }
            if (movementType.equals(MovementType.CAPTURE)) {
                if (x == xCoordinate + 1 && y == yCoordinate - 1)
                    return true;
                if (x == xCoordinate - 1 && y == yCoordinate - 1)
                    return true;
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
