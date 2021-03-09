public class DN06 {
    public static void main (String[] args){
        double x, y, velikost;
        for (int i = 0; i < 20; i++){
            x = Math.random();
            y = Math.random();
            velikost = Math.random() / 2;
            korona(x, y, velikost);
        }
    }

    public static void korona(double x, double y, double velikost){
        StdDraw.picture(x,y,"virus.png",velikost,velikost);
    }
}
