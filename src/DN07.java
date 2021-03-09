import java.awt.*;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class DN07 {
    public static void main (String[] args) {
        int[] t = Arrays.stream(args[0].split(",")).mapToInt(Integer::parseInt).toArray();
        narisiStolpce(t);
    }

    public static void narisiStolpce(int [] t){
        int dolTab = t.length;
        double prazno = 1.0 / (dolTab + 1) / (dolTab + 1);
        double enWidth = 1.0 / (dolTab + 1);
        double enX = prazno + enWidth / 2.0;
        IntSummaryStatistics stat = Arrays.stream(t).summaryStatistics();
        int max = stat.getMax();
        StdDraw.setScale(0,1);
        for (int i = 0; i < dolTab; i++){
            if(i % 3 == 0)
                StdDraw.setPenColor(Color.RED);
            else if (i % 3 == 1)
                StdDraw.setPenColor(Color.GREEN);
            else
                StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(enX, 0, enWidth / 2, 0.95 / max * t[i]);
            enX = enX + prazno + enWidth;
        }
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.setPenRadius(0.01);
        StdDraw.rectangle(0.5,0.5,0.5,0.5);
    }
}
