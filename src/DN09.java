import java.io.File;
import java.util.Scanner;

public class DN09 {
    public static void main (String[] args) throws Exception{
        Scanner sc = new Scanner(new File(args[0]));
        String[] s;
        int steps = 0;
        int width = 0, height = 0;
        String prefix = "neki", background = "255-255-255",cleanNoSymptoms = "100-100-100",cleanSymptoms = "200-180-0",infectedNoSymptoms = "255-0-0",infectedSymptoms = "170-0-0";
        Simulation sim = new Simulation();
        while (sc.hasNext()){
            s = sc.nextLine().split("\\s+");
            if(s[0].equals("#") || s[0].equals("")){
                continue;
            }
            switch (s[0]) {
                case "numberOfHumans":
                    sim.setNumbOfHumans(Integer.parseInt(s[1]));
                    break;
                case "human":
                    new Human(Integer.parseInt(s[1]), Integer.parseInt(s[1]), Boolean.parseBoolean(s[3]), Boolean.parseBoolean(s[4]));
                    break;
                case "random":
                    java.util.Random r = new java.util.Random();
                    for (int i = 0; i < Integer.parseInt(s[1]); i++){
                        sim.tab[i].setPosition(r.nextInt(Integer.parseInt(s[7])), r.nextInt(Integer.parseInt(s[8])));
                        sim.tab[i].setHasSymptoms(getRandomBoolean(0.5));
                        sim.tab[i].setIsInfected(getRandomBoolean(0.5));
                    }
                    break;
                case "neighborhoodRadius":
                    sim.setNeighborhoodRadius(Integer.parseInt(s[1]));
                    break;
                case "steps":
                    steps = Integer.parseInt(s[1]);
                    break;
                case "probabilityInfection":
                    sim.setProbabilityInfection(Double.parseDouble(s[1]));
                    break;
                case "probabilityTrueSymptoms":
                    sim.setProbabilityTrueSymptoms(Double.parseDouble(s[1]));
                    break;
                case "probabilityFakeSymptoms":
                    sim.setProbabilityFakeSymptoms(Double.parseDouble(s[1]));
                    break;
                case "view":
                    width = Integer.parseInt(s[5]) - Integer.parseInt(s[2]);
                    height = Integer.parseInt(s[6]) - Integer.parseInt(s[3]);
                    break;
                case "outputPrefix":
                    prefix = s[1];
                    break;
                case "color":
                    switch (s[1]){
                        case "background":
                            background = s[2]+"-"+s[3]+"-"+s[4];
                            break;
                        case "cleanNoSymptoms":
                            cleanNoSymptoms = s[2]+"-"+s[3]+"-"+s[4];
                            break;
                        case "cleanSymptoms":
                            cleanSymptoms = s[2]+"-"+s[3]+"-"+s[4];
                            break;
                        case "infectedNoSymptoms":
                            infectedNoSymptoms = s[2]+"-"+s[3]+"-"+s[4];
                            break;
                        case "infectedSymptoms":
                            infectedSymptoms = s[2]+"-"+s[3]+"-"+s[4];
                            break;
                    }
            }
        }

        int ko = 0;
        boolean t = false;
        while (steps > 0){
            sim.update();
            int[][][] pix = new int[height][width][3];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    for (Human h: sim.tab){
                        if (h.getPosition()[0] == x && h.getPosition()[1] == y){
                            if (!h.getIsInfected() && !h.getHasSymptoms()){
                                pix[x][y] = new int[]{Integer.parseInt(cleanNoSymptoms.split("-")[0]), Integer.parseInt(cleanNoSymptoms.split("-")[1]), Integer.parseInt(cleanNoSymptoms.split("-")[2])};
                                t = true;
                                break;
                            }else if (!h.getIsInfected() && h.getHasSymptoms()){
                                pix[x][y] = new int[]{Integer.parseInt(cleanSymptoms.split("-")[0]), Integer.parseInt(cleanSymptoms.split("-")[1]), Integer.parseInt(cleanSymptoms.split("-")[2])};
                                t = true;
                                break;
                            }else if (h.getIsInfected() && !h.getHasSymptoms()){
                                pix[x][y] = new int[]{Integer.parseInt(infectedNoSymptoms.split("-")[0]), Integer.parseInt(infectedNoSymptoms.split("-")[1]), Integer.parseInt(infectedNoSymptoms.split("-")[2])};
                                t = true;
                                break;
                            }else if (h.getIsInfected() && h.getHasSymptoms()){
                                pix[x][y] = new int[]{Integer.parseInt(infectedSymptoms.split("-")[0]), Integer.parseInt(infectedSymptoms.split("-")[1]), Integer.parseInt(infectedSymptoms.split("-")[2])};
                                t = true;
                                break;
                            }
                        }
                    }
                    if (!t) {
                        pix[x][y] = new int[]{Integer.parseInt(background.split("-")[0]), Integer.parseInt(background.split("-")[1]), Integer.parseInt(background.split("-")[2])};
                    }else{
                        t = false;
                    }
                }
            }
            new PPMWriter().write(prefix+ko+".ppm", pix, width, height);
            ko++;
            steps--;
        }
    }

    public static boolean getRandomBoolean(double p){
        java.util.Random random = new java.util.Random();
        return random.nextDouble() < p;
    }
}





