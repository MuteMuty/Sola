import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DatotecniSistem {
    Direktorij rootDir;
    ArrayList<Element> tabDir = new ArrayList<>();
    String koren;
    String pot;
    int globina = 0;

    DatotecniSistem(String ime){
        this.koren = new File(ime).getName();
        this.pot = new File(ime).getAbsolutePath();
        this.rootDir = new Direktorij(this.koren);
        this.tabDir.add(rootDir);
        this.globina++;
    }

    public void izpisiVsebino() {
        for (Element e : this.tabDir){
            System.out.println(e.toString());
        }
    }

    public void posodobiSistem(int globina){
        File dir = new File(this.pot);
        File[] fList = dir.listFiles();
        if(fList != null && this.globina <= globina)
            for (File file : fList) {
                if (file.isFile()) {
                    this.tabDir.add(new Datoteka(this.globina, file.getName()));
                } else if (file.isDirectory()) {
                    this.tabDir.add(new Direktorij(this.globina, file.getName()));
                    this.globina++;
                    String prej = this.pot;
                    this.pot = file.toString();
                    posodobiSistem(globina);
                    this.pot = prej;
                    this.globina--;
                }
            }
    }

    public void izpisiVelikost() {
        try {
            Path folder = Paths.get(this.koren);
            long size = Files.walk(folder)
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
            System.out.printf("Skupaj: %.2f MB", (double) size / (1024 * 1024));
        }catch (IOException e){
            System.out.print("Napaka: " + e.getMessage());
        }
    }

    //public void izpisiSamoreference() {
    //    try {
    //        System.out.println("Svoje ime vsebujejo naslednje datoteke:");
    //        for (Element e : this.tabDir){
    //            File f = new File(e.getIme() + "");
    //            if (f.isFile()) {
    //                Scanner scanner = new Scanner(f);
    //                while (scanner.hasNext()) {
    //                    String s = scanner.next();
    //                    if (s.equals(e.getIme()) || s.substring(0, s.length() - 1).equals(e.getIme())) {
    //                        System.out.println(e.toString());
    //                    }
    //                }
    //            }
    //        }
    //    }catch (NullPointerException | FileNotFoundException e){
    //        e.printStackTrace();
    //    }
    //}

}
