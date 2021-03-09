import java.io.File;
import java.util.Scanner;

class Stevilo {
    double absolutnaVrednost(){
        return 0;
    }
}

/**
 * @author ...
 **/
public class DN08 {
    static final int MAX_STEVIL = 100;

    static Stevilo [] preberiStevila(String imeDatoteke) throws Exception {
        Stevilo [] stevila = new Stevilo[MAX_STEVIL];
        // TODO: ... odpri datoteko in preberi števila
        Scanner sc = new Scanner(new File(imeDatoteke));
        String s;
        int i = 0;
        while (sc.hasNext()){
            s = sc.next();
            if(s.equals("Z")){
                stevila[i] = new CeloStevilo(sc.nextInt());
                i++;
            }else if (s.equals("Q")){
                stevila[i] = new RacionalnoStevilo(sc.nextInt(), sc.nextInt());
                i++;
            }else if (s.equals("C")){
                stevila[i] = new KompleksnoStevilo(sc.nextInt(), sc.nextInt());
                i++;
            }
        }

        return stevila;
    }

    public static void main(String[] args) throws Exception {
        Stevilo [] stevila = preberiStevila(args[0]);

        for (int i = 0; i < MAX_STEVIL; i++) {
            // izpišem samo "neprazna" števila v tabeli
            if (stevila[i] == null) continue;

            System.out.printf("Absolutna vrednost stevila %s je %.2f\n", stevila[i].toString(), stevila[i].absolutnaVrednost());
        }
    }
}

class CeloStevilo extends Stevilo{
    private double n;

    CeloStevilo(){
        this.n = 0.0;
    }

    CeloStevilo(int n){
        this.n = n;
    }

    public double absolutnaVrednost(){
        return Math.abs(n);
    }

    @Override
    public String toString(){
        return (int)n + "";
    }
}

class RacionalnoStevilo  extends Stevilo{
    private double p;
    private double q;

    RacionalnoStevilo(){
        this.p = 0.0;
        this.q = 0.0;
    }

    RacionalnoStevilo(int p, int q){
        this.p = p;
        this.q = q;
    }

    public double absolutnaVrednost(){
        return Math.abs(p / q);
    }

    @Override
    public String toString(){
        return (int)p + " / " + (int)q;
    }
}

class KompleksnoStevilo extends Stevilo{
    private double re;
    private double im;

    KompleksnoStevilo(){
        this.re = 0.0;
        this.im = 0.0;
    }

    KompleksnoStevilo(int re, int im){
        this.re = re;
        this.im = im;
    }

    public double absolutnaVrednost(){
        return Math.sqrt(im * im + re * re);
    }

    @Override
    public String toString(){
        String rea = this.re + "";
        String ima = "";
        if(this.im < 0)
            ima = " " + this.im;
        else
            ima = " + " + this.im;
        return rea.substring(0, rea.indexOf(".")) + ima.substring(0, ima.indexOf(".")) + " i";
    }
}
