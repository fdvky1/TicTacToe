package tictactoe;
import java.util.*;

public class TicTacToe {
    static String[] board;
    static String turn;
    static int difficulty;
    
    //method untuk memilih angka secara acak
    static int randomPick(){
        String[] filteredBoard = Arrays.stream(board).filter(v -> !"X".equals(v) && !"O".equals(v)).toArray(String[]::new);
        Random index = new Random();
        return Integer.parseInt(filteredBoard[index.nextInt(filteredBoard.length - 1)]);
    }
    
    //method untuk menentukan pemenang
    static String checkWinner(){
        for (int i = 0; i < 8; i++) { 
            String line = null; 
            switch (i) {
            case 0: 
                line = board[0] + board[1] + board[2]; 
            break; 
            case 1: 
                line = board[3] + board[4] + board[5]; 
            break;
            case 2:
                line = board[6] + board[7] + board[8]; 
            break;
            case 3:
                line = board[0] + board[3] + board[6]; 
            break;
            case 4: 
                line = board[1] + board[4] + board[7]; 
            break;
            case 5:
                line = board[2] + board[5] + board[8]; 
            break;
            case 6:
                line = board[0] + board[4] + board[8];
            break;
            case 7:
                line = board[2] + board[4] + board[6];
            break;
        }
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) { 
                return "O"; 
            }
        }
        for (int i = 0; i < 9; i++) {
            if (Arrays.asList(board).contains(String.valueOf(i + 1))) { 
                break;
            }else if (i == 8) {
                return "draw";
            }
        }
        
        System.out.print("Sekarang giliran " + turn + ", silahkan masukkan nomor slot: "); 
        
        return null;
    } 
    
    
    //method untuk menampilkan papan tictactoe 
    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |"); 
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |"); 
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |"); 
        System.out.println("|---|---|---|");
    }

    public static void main(String[] args){ 
        Scanner in = new Scanner(System.in);
        System.out.println("=================================");
        System.out.println("===========TIC TAC TOE===========");
        System.out.println("=================================");
        System.out.println("\nPilih Tingkat Kesulitan");
        System.out.println("1) Mudah");
        System.out.println("2) Normal");
        System.out.println("3) Susah");
        System.out.println("4) Main dengan teman");
        System.out.print("Masukkan urutannya saja(1,2,3,4): ");
        difficulty = in.nextInt();
        board = new String[9];
        turn = "X";
        String winner = null; 
        for (int i = 0; i < 9; i++) { 
            board[i] = String.valueOf(i + 1); 
        }
        printBoard();
        System.out.print("X akan mulai duluan, masukkan nomor untuk menaruh X: ");
        while (winner == null) {
          int numInput = 0;
            if(difficulty != 4 && "O".equals(turn)){
                if(difficulty != 1){
                    for (int i = 0; i < 7; i++) { 
                        String line = null; 
                        switch (i) {
                            case 0: 
                                line = board[0] + board[1] + board[2]; 
                            break; 
                            case 1: 
                                line = board[3] + board[4] + board[5]; 
                            break;
                            case 2:
                                line = board[6] + board[7] + board[8]; 
                            break;
                            case 3:
                                line = board[0] + board[3] + board[6]; 
                            break;
                            case 4: 
                                line = board[1] + board[4] + board[7]; 
                            break;
                            case 5:
                                line = board[2] + board[5] + board[8]; 
                            break;
                            case 6:
                                line = board[0] + board[4] + board[8];
                            break;
                            case 7:
                                line = board[2] + board[4] + board[6];
                            break;
                        }
                        String[] lines = line.split("");
                        //ketika tingkat kesulitannya normal maka program akan mencegah pemain X untuk menang
                        if("XX".equals(lines[0]+lines[1]) && !"X".equals(lines[2]) && !"O".equals(lines[2])){
                            numInput = Integer.parseInt(lines[2]);
                        } else if("XX".equals(lines[1]+lines[2]) && !"X".equals(lines[0]) && !"O".equals(lines[0])){
                            numInput = Integer.parseInt(lines[0]);
                        } else if("XX".equals(lines[0]+lines[2]) && !"X".equals(lines[1]) && !"O".equals(lines[1])){
                            numInput = Integer.parseInt(lines[1]);
                        }
                        if(difficulty == 3){
                          //ketika tingkat kesulitannya susah maka program akan memilih untuk memenangkan permainan saat pemain X lengah
                            if("OO".equals(lines[0]+lines[1]) && !"X".equals(lines[2]) && !"O".equals(lines[2])){
                                numInput = Integer.parseInt(lines[2]);
                            } else if("OO".equals(lines[1]+lines[2]) && !"X".equals(lines[0]) && !"O".equals(lines[0])){
                                numInput = Integer.parseInt(lines[0]);
                            } else if("OO".equals(lines[0]+lines[2]) && !"X".equals(lines[1]) && !"O".equals(lines[1])){
                                numInput = Integer.parseInt(lines[1]);
                            }  
                        }
                    }
                }
                if(numInput == 0) {
                  //program akan memilih nomor acak jika numInput serupa dengan 0
                  //hal ini dapat terjadi jika tingkatt kesulitannya mudah atau program belum memilih angka tertentu 
                  numInput = randomPick();
                }
                System.out.println(numInput);
            }else{
                numInput = in.nextInt();
            }
            try {
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.print("Nomor yang anda masukkan tidak valid, masukkan ulang nomor slot: "); 
                    continue;
                }
            }catch (InputMismatchException e) { 
                System.out.print("Nomor yang anda masukkan tidak valid, masukkan ulang nomor slot: "); 
                continue;
            }
            if (board[numInput - 1].equals(String.valueOf(numInput))) { 
              board[numInput - 1] = turn;
              if (turn.equals("X")) {
                  turn = "O"; 
              } else {
                  turn = "X";
              }
              printBoard();
              winner = checkWinner();
            } else {
                System.out.print("Slot sudah diambil; masukkan nomor lain: "); 
            }
        } 

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("Permainan berakhir dengan skor seri"); 
        } else { 
            System.out.println("Selamat! " + winner + " telah memenangkan permainan");
        }
    }
}
