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
        //Vérifier lignes
        for(int i = 0; i < 3; i++)
        {
            Mark firstMark = board[i][0];
            if(firstMark != Mark.EMPTY && firstMark == board[i][1] && firstMark == board[i][2])
            {
                if(firstMark.equals(mark))
                {
                    return 100;
                }

                return -100;
            }
        }

        //Vérifier colonnes
        for(int i = 0; i < 3; i++)
        {
            Mark firstMark = board[0][i];
            if(firstMark != Mark.EMPTY && firstMark == board[1][i] && firstMark == board[2][i])
            {
                if(firstMark.equals(mark))
                {
                    return 100;
                }

                return -100;
            }
        }

        //Vérifier diagonales
        Mark firstMarkDiagOne = board[0][0];
        if(firstMarkDiagOne != Mark.EMPTY && firstMarkDiagOne == board[1][1] && firstMarkDiagOne == board[2][2])
        {
            if(firstMarkDiagOne.equals(mark))
            {
                return 100;
            }

            return -100;
        }

        Mark firstMarkDiagTwo = board[0][2];
        if(firstMarkDiagTwo != Mark.EMPTY && firstMarkDiagTwo == board[1][1] && firstMarkDiagTwo == board[2][0])
        {
            if(firstMarkDiagTwo.equals(mark))
            {
                return 100;
            }

            return -100;
        }

        //Sinon, cela veut dire que le tableau est plein sans ligne complète, donc c'est un match nul
        return 0;
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
