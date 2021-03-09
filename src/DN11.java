import java.io.File;

public class DN11 {
    public static void main (String [] args){
        if (args.length != 3) {
            System.out.print("Argumenti niso ustrezno podani!");
            return;
        }
        File dir = new File(args[0]);
        if(!dir.exists()) {
            System.out.print("Direktorij ne obstaja!");
            return;
        }
        DatotecniSistem ds = new DatotecniSistem(args[0]);
        try {
            if (args[1].equals("x"))
                args[1] = "1024";
            Integer.parseInt(args[1]);
        }catch (NumberFormatException e) {
            System.out.print("Drugi argument mora biti stevilka globine ali pa x!");
            return;
        }
        ds.posodobiSistem(Integer.parseInt(args[1]));
        switch (args[2]){
            case "list":
                ds.izpisiVsebino();
                break;
            case "du":
                ds.izpisiVelikost();
                break;
            case "self":
                //ds.izpisiSamoreference();
                break;
            case "grep":
                break;
            case "diff":
                break;
            default:
                System.out.print("Tretji argument ni pravilno podan!");
                break;
        }
    }
}
