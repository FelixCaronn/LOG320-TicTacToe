import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        this.board = new Mark[][]{{Mark.EMPTY, Mark.EMPTY, Mark.EMPTY},
                {Mark.EMPTY, Mark.EMPTY, Mark.EMPTY},
                {Mark.EMPTY, Mark.EMPTY, Mark.EMPTY}};
    }

    public Mark getMark(int i, int j) {
        return board[i][j];
    }

    public void setMark(int i, int j, Mark type)
    {
        this.board[i][j] = type;
    }
    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        int x = m.getRow();
        int y = m.getCol();

        if(this.board[x][y] == Mark.EMPTY)
        {
            this.board[x][y] = mark;
        }
    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){

    }

    //Fonction qui retourne toutes les cases disponibles
    public ArrayList<Move> getAvailableMoves()
    {
        ArrayList<Move> moves = new ArrayList<Move>();
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(getMark(i,j) == Mark.EMPTY)
                {
                    moves.add(new Move(i,j));
                }
            }
        }
        return moves;
    }

    public boolean isFull(){
        return this.getAvailableMoves().isEmpty();
    }
}
