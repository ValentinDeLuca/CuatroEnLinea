/**
 * Created by Valentín on 03/08/2017.
 */
public class FourNLine {

    //este es un comentario para probar

    public static String board[][] = {
            {"-","-","-","-","-","-"}, //COLUNMA 1
            {"-","-","-","-","-","-"}, //COLUNMA 2
            {"-","-","-","-","-","-"}, //COLUNMA 3
            {"-","-","-","-","-","-"}, //COLUNMA 4
            {"-","-","-","-","-","-"}, //COLUNMA 5
            {"-","-","-","-","-","-"}, //COLUNMA 6
            {"-","-","-","-","-","-"}};//COLUNMA 7

    public static boolean ask =true;
    public static boolean gameO=false;
    public static boolean replay=true;

    public static void main (String[] args ){
        System.out.println("Four in Line");
        actualBoard(board);
        while(replay) {
            thePlays();
        }
    }

    public static void thePlays() {
        while (gameO == false) {

            //PLAYER 1

            while (ask) {
                String player = "X";
                int movida1 = Scanner.getInt("Player "+player+" insert your column(1-7): ");
                movida1 = movida1 - 1;
                if(movida1>8||movida1<0) {
                    System.out.println("This is not a column!");
                }else{
                    play(board, movida1, player);
                    System.out.print(win1(board, movida1, player) + win2(board, movida1, player));
                }
            }

            actualBoard(board);

            ask = true;
            if (gameO == true) break;

            //PLAYER 2

            while (ask) {
                String player = "O";
                int movida2 = Scanner.getInt("Player "+player+" insert your column(1-7): ");
                movida2 = movida2 - 1;if(movida2>8||movida2<0) {
                    System.out.println("This is not a column!");
                }else{
                    play(board, movida2, player);
                    System.out.print(win1(board, movida2, player) + win2(board, movida2, player));
                }
            }

            actualBoard(board);
            ask = true;
            if (gameO == true) break;
        }
        String again = Scanner.getString("Replay? YES/NO");
        if(again.toLowerCase().equals("no")){
            replay=false;
        }else{
            gameO=false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    board[i][j]="-";
                }
            }
            actualBoard(board);
        }

    }

    public static void actualBoard(String[][]board){   //METHOD PRINT
        String T="";
        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i < board.length; i++) {
                T = T + "|"+board[i][j];
            }
            T = T + "|\n";
        }
        System.out.println(T+"'1'2'3'4'5'6'7'");
    }

    public static int occupied(String [][] board, int i){
        for (int c = 0; c < board[0].length; c++) {
            if(board[i][c].equals("X")||board[i][c].equals("O")){
                return c;
            }
        }
        return 6;
    }

    public static void play(String [][]board, int i, String player){
        if(occupied(board,i)!=0){
            board[i][occupied(board,i)-1]=player;
            ask = false;
        }else{
            System.out.println("This column is full!");
        }
    }

    //VERTICAL & HORIZONTAL
    public static String win1(String[][]board, int i, String player) { //el int I seria la columna que imputeo el usuario (la ultima)
        String msg="Player "+player+" Wins!\nThe Game is Over\nThanks for Playing :D\n";

        int j = occupied(board, i);      //la fila

        if ((j <= 2)) {                 //Vertical
            if (board[i][j + 1].equals(player) && board[i][j + 2].equals(player) && board[i][j + 3].equals(player)) {
                gameO = true;
                return msg;
            }
        }

        for (int h = 0; h < 4; h++) {   //Horizontal
            if (board[h][j].equals(player) && board[h + 1][j].equals(player) && board[h + 2][j].equals(player) && board[h + 3][j].equals(player)) {
                gameO = true;
                return msg;
            }
        }
        return "";
    }

    //DIAGONAL
    public static String win2(String[][]board, int i, String player) {
        int j = occupied(board, i);  //POSICION DE FILA
        int i1=i;                   //CLON DE I (COLUMNA)
        String msg="Player "+player+" Wins!\nThe Game is Over\nThanks for Playing :D\n";

        //<EMPATE>
        int count=0;
        for (int k = 0; k < board.length; k++) {
            if(occupied(board,k)==0){
                count++;
            }
        }
        if(count==7){
            gameO=true;
            return "Nobody wins :)\n";
        }
        //</EMPATE>

        if ((j + i) <= 2) {    //done 1       down left right
            while ((j - 1) >= 0 && (i - 1) >= 0) {
                j = j - 1;
                i = i - 1;
            }

            while (j + 3 <= 5 && i + 3 <= 6) {//down left right
                if (board[i][j].equals(player) && board[i + 1][j + 1].equals(player) && board[i + 2][j + 2].equals(player) && board[i + 3][j + 3].equals(player)) {
                    gameO = true;
                    return msg;
                }
                i++;
                j++;
            }
        }

        if (j + i >= 9) {   //done  2        up right left
            while (j + 1 <= 5 && i + 1 <= 6) {
                j = j + 1;
                i = i + 1;
            }

            while (j - 3 >= 0 && i - 3 >= 0) {//un right left
                if (board[i][j].equals(player) && board[i - 1][j - 1].equals(player) && board[i - 2][j - 2].equals(player) && board[i - 3][j - 3].equals(player)) {
                    gameO = true;
                    return msg;
                }
                j = j - 1;
                i = i - 1;
            }
        }

        if(j + (6-i)<=2){   //done 3         down right left
            while (j - 1 >= 0 && i + 1 <= 6) {
                j = j - 1;
                i = i + 1;
            }
            while (j + 3 <= 5 && i - 3 >= 0) {//down right left
                if (board[i][j].equals(player) && board[i - 1][j + 1].equals(player) && board[i - 2][j + 2].equals(player) && board[i - 3][j + 3].equals(player)) {
                    gameO = true;
                    return msg;
                }
                j = j + 1;
                i = i - 1;
            }

        }

        if(j + (6-i)>=9){   //done  4        up left right
            while (j + 1 <= 5 && i - 1 >= 0) {//up left right
                j = j + 1;
                i = i - 1;
            }
            while (j - 3 >= 0 && i + 3 >= 0) {
                if (board[i][j].equals(player) && board[i + 1][j - 1].equals(player) && board[i + 2][j - 2].equals(player) && board[i + 3][j - 3].equals(player)) {
                    gameO = true;
                    return msg;
                }
                j = j - 1;
                i = i + 1;
            }
        }
                                    //CASO X SERIAN AMBOS METODOS DIVIDIDOS EN SU SENTIDO
        if(j<3){
            while ((j - 1) >= 0 && (i - 1) >= 0) {
                j = j - 1;
                i = i - 1;
            }
            while (j + 3 <= 5 && i + 3 <= 6) {//down left right
                if (board[i][j].equals(player) && board[i + 1][j + 1].equals(player) && board[i + 2][j + 2].equals(player) && board[i + 3][j + 3].equals(player)) {
                    gameO = true;
                    return msg;
                }
                i++;
                j++;
            }

            i=i1;
            j= occupied(board,i);

            while (j - 1 >= 0 && i + 1 <= 6) {//down right left
                j = j - 1;
                i = i + 1;
            }
            while (j + 3 <= 5 && i - 3 >= 0) {
                if (board[i][j].equals(player) && board[i - 1][j + 1].equals(player) && board[i - 2][j + 2].equals(player) && board[i - 3][j + 3].equals(player)) {
                    gameO = true;
                    return msg;
                }
                j = j + 1;
                i = i - 1;
            }
        }else{
            while (j + 1 <= 5 && i + 1 <= 6) {
                j = j + 1;
                i = i + 1;
            }
            while (j - 3 >= 0 && i - 3 >= 0) {//un right left
                if (board[i][j].equals(player) && board[i - 1][j - 1].equals(player) && board[i - 2][j - 2].equals(player) && board[i - 3][j - 3].equals(player)) {
                    gameO = true;
                    return msg;
                }
                j = j - 1;
                i = i - 1;
            }
            i=i1;
            j= occupied(board,i);

            while (j + 1 <= 5 && i - 1 >= 0) {//up left right
                j = j + 1;
                i = i - 1;
            }
            while (j - 3 >= 0 && i + 3 >= 0) {
                if (board[i][j].equals(player) && board[i + 1][j - 1].equals(player) && board[i + 2][j - 2].equals(player) && board[i + 3][j - 3].equals(player)) {
                    gameO = true;
                    return msg;
                }
                j = j - 1;
                i = i + 1;
            }
        }
        return "";
    }

}




















