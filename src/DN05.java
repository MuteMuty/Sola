import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DN05 {
    public static Random rand = new Random();
    public static void main (String[] args) throws Exception{
        if (args.length == 1){
            int[][]tab = preberiLabirint(args[0]);
            int[] res = poisciResitev(tab);
            for (int i = 0; i < res.length; i++){
                System.out.print(res[i] + " ");
            }
            shraniResitev(res, args[0]);
        }else if (args.length == 2){
            int[][]tab = preberiLabirint(args[0]);
            izrisiLabirint(tab);
            System.out.println();
            int[]res = preberiResitev(args[1]);
            if(preveriResitev(tab, res))
                System.out.print("Pravilna resitev!");
            else
                System.out.print("Nepravilna resitev!");
            System.out.println();
        }else if (args.length == 3){
            narediLabirint(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        }
    }

    public static int[][] preberiLabirint(String ime) throws Exception{
        Scanner sc = new Scanner(new File(ime));
        ime = ime.replaceAll("[^\\d]", " ");
        ime = ime.trim();
        ime = ime.replaceAll(" +", " ");
        int sirina = Integer.parseInt(ime.substring(0, ime.indexOf(" "))) * 2 - 1;
        int visina = Integer.parseInt(ime.substring(ime.indexOf(" ") + 1)) * 2 - 1;
        int[][] tab = new int[visina][sirina];
        int i = 0, j = 0;
        while (sc.hasNext()){
            tab[i][j] = sc.nextInt();
            j++;
            if(j == sirina) {
                i++;
                j = 0;
            }
        }
        return tab;
    }

    public static void izrisiLabirint(int[][] labirint){
        for (int i = 0; i < labirint[0].length + 2; i++){
            System.out.print("# ");
        }
        System.out.println();
        for (int i = 0; i < labirint.length; i++){
            System.out.print("# ");
            for (int j = 0; j < labirint[i].length; j++){
                if(labirint[i][j] == 1)
                    System.out.print("  ");
                else
                    System.out.print("# ");
            }
            System.out.println("# ");
        }
        for (int i = 0; i < labirint[0].length + 2; i++){
            if(i == labirint[0].length)
                System.out.print("  ");
            else
                System.out.print("# ");
        }
    }

    public static int[] preberiResitev(String ime) throws Exception{
        Scanner sc1 = new Scanner(new File(ime));
        int dolzina = sc1.nextLine().length() / 3;
        sc1.close();
        Scanner sc = new Scanner(new File(ime));
        int [] resitev = new int[dolzina];
        int i = 0;
        while (sc.hasNext() && i < dolzina){
            resitev[i] = sc.nextInt();
            i++;
        }
        return resitev;
    }

    public static boolean preveriResitev(int[][] labirint, int[] resitev){
        int j = 0;
        int k = 0;
        for (int i = 0; i < resitev.length; i++){
            if(labirint[j][k] != 1)
                return false;
            if(resitev[i] == 3)
                k--;
            else if(resitev[i] == 4)
                k++;
            else if(resitev[i] == 5)
                j--;
            else if(resitev[i] == 6)
                j++;

        }
        return true;
    }

    public static int[][] narediLabirint(int sirina, int visina, double verjetnost){
        int[][] tab = new int[2 * visina - 1][2 * sirina - 1];
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[i].length; j++){
                tab[i][j] = 1;
            }
        }
        int ran1 = 0;
        int ran2 = 0;
        while(ran1 % 2 == 0) {
            ran1 = rand.nextInt(2 * visina - 1);
        }while(ran2 % 2 == 0) {
            ran2 = rand.nextInt(2 * visina - 1);
        }
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[i].length; j++){
                if(i == ran1)
                    tab[i][j] = 0;
                if(j == ran2)
                    tab[i][j] = 0;
            }
        }
        int[] st = new int[100];
        for (int i = 0; i < st.length; i++){
            if(i < (int) (verjetnost * 100))
                st[i] = 1;
            else
                st[i] = 0;
        }
        int t;
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[i].length; j++){
                if(j % 2 == 1 && tab[i][j] == 0 && i % 2 == 0 ||j % 2 == 0 && tab[i][j] == 0 && i % 2 == 1){
                    t = rand.nextInt(100);
                    tab[i][j] = st[t];
                }
            }
        }
        return tab;
    }

    public static int[] poisciResitev(int[][] labirint) throws InterruptedException {
        int[][] lab = new int[labirint.length + 2][labirint[0].length + 2];
        for (int i = 0; i < labirint.length; i++){
            for (int j = 0; j < labirint[i].length; j++){
                lab[i + 1][j + 1] = labirint[i][j];
            }
        }

        String temp = "", pot = "";
        int kaj;
        int i = 1, j = 1;
        while(i != lab.length - 2 || j != lab[i].length - 2){
            if (lab[i - 1][j] == 1) {
                temp += "5";
            }
            if (lab[i][j + 1] == 1) {
                temp += "4";
            }
            if (lab[i + 1][j] == 1) {
                temp += "6";
            }
            if (lab[i][j - 1] == 1) {
                temp += "3";
            }
            kaj = selectAChar(temp);
            if(kaj == 5){
                i--;
                lab[i][j] = 6;
                pot += "5";
            }else if (kaj == 4){
                j++;
                lab[i][j] = 3;
                pot += "4";
            }else if (kaj == 6){
                i++;
                lab[i][j] = 5;
                pot += "6";
            }else if (kaj == 3){
                j--;
                lab[i][j] = 4;
                pot += "3";
            }else{
                if(lab[i][j] == 5){
                    lab[i][j] = 0;
                    i--;
                }else if(lab[i][j] == 4){
                    lab[i][j] = 0;
                    j++;
                }else if(lab[i][j] == 6){
                    lab[i][j] = 0;
                    i++;
                }else if(lab[i][j] == 3){
                    lab[i][j] = 0;
                    j--;
                }
                if (pot.length() > 0)
                    pot = pot.substring(0, pot.length() - 1);
            }
            temp = "";

            //for (int k = 0; k<lab.length;k++){
            //    for (int l = 0; l<lab[k].length;l++){
            //        System.out.print(lab[k][l]);
            //    }
            //    System.out.println();
            //}
            //System.out.printf("pot: %s\ni: %d, j: %d\n",pot,i,j);
            //TimeUnit.SECONDS.sleep(1);
            //System.out.println();

        }
        int[] resitev = new int[pot.length()];
        for (int k = 0; k < resitev.length; k++){
            resitev[k] = Integer.parseInt(pot.charAt(k) + "");
        }

        return resitev;
    }

    public static void shraniResitev(int[] resitev, String ime) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter("resitev-" + ime);
        for (int i = 0; i < resitev.length; i++){
            writer.print(resitev[i] + " ");
        }
        writer.close();
    }

    public static int selectAChar(String s){
        if(s.isEmpty())
            return 0;
        Random random = new Random();
        int index = random.nextInt(s.length());
        return s.charAt(index) - 48;
    }

}
