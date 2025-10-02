import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private Mark cpu; //
    private Mark opponent;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.cpu = cpu;
        this.opponent = (cpu == Mark.X)?Mark.O : Mark.X;
        this.numExploredNodes = 0;
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;
        int bestScore = Integer.MIN_VALUE;
        ArrayList<Move> bestMoves =  new ArrayList<>();

        for(Move move:  board.getAvailableMoves())
        {
            board.play(move,cpu);
            int score = minMax(board,false);

            board.setMark(move.getRow(), move.getCol(), Mark.EMPTY); //Backtrack

            if(score > bestScore)
            {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            }
            else if(score == bestScore)
            {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }

    private int minMax(Board board, boolean isMax){
        numExploredNodes++;
        int eval = board.evaluate(cpu);
        if(eval == 100 || eval == -100 || board.isFull())
        {
            return eval;
        }

        if(isMax)
        {
            int bestScore = Integer.MIN_VALUE;
            for(Move move: board.getAvailableMoves())
            {
                board.play(move,cpu);
                int score = minMax(board,false);
                board.setMark(move.getRow(),move.getCol(),Mark.EMPTY);//Backtrack
                bestScore = Math.max(bestScore, score);
            }
            return bestScore;
        }
        else
        {
            int bestScore = Integer.MAX_VALUE;
            for(Move move: board.getAvailableMoves())
            {
                board.play(move, opponent);
                int score = minMax(board,true);
                board.setMark(move.getRow(), move.getCol(), Mark.EMPTY);
                bestScore = Math.min(bestScore,score);
            }
            return bestScore;
        }
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;
        int bestScore = Integer.MIN_VALUE;
        ArrayList<Move> bestMoves = new ArrayList<Move>();

        for(Move move: board.getAvailableMoves())
        {
            board.play(move,cpu);
            int score = alphaBeta(board, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            board.setMark(move.getRow(), move.getCol(), Mark.EMPTY); //Backtrack

            if(score > bestScore)
            {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            }
            else if(score == bestScore)
            {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }

    private int alphaBeta(Board board, boolean isMax, int alpha, int beta)
    {
        numExploredNodes++;
        int eval = board.evaluate(cpu);
        if(eval == 100 || eval == -100 || board.isFull())
        {
            return eval;
        }
        if(isMax)
        {
            int bestScore = Integer.MIN_VALUE;
            for(Move move : board.getAvailableMoves())
            {
                board.play(move, cpu);
                int score = alphaBeta(board,false, alpha, beta);
                board.setMark(move.getRow(), move.getCol(), Mark.EMPTY);
                alpha = Math.max(bestScore,score);
                if(beta <= alpha) break;
            }
            return bestScore;
        }
        else
        {
            int bestScore = Integer.MIN_VALUE;
            for(Move move : board.getAvailableMoves())
            {
                board.play(move, opponent);
                int score = alphaBeta(board, true, alpha, beta);
                board.setMark(move.getRow(), move.getCol(), Mark.EMPTY);
                beta = Math.min(bestScore, score);
                if(beta <= alpha) break;
            }
            return bestScore;
        }
    }



}
