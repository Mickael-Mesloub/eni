import java.util.ArrayList;
import java.util.List;

public class EnumExampleApp {
    public static void main(String[] args) {
        Piece pieceBlanche = new Piece();
        Piece pieceNoire = new Piece();
        pieceNoire.color = PieceColor.Noire;

        ArrayList<Piece> pieces = new ArrayList<Piece>(List.of(pieceBlanche, pieceNoire));

        for(Piece piece : pieces) {
            System.out.println(piece.color);
        }
    }
}