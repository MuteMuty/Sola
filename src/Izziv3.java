import java.util.Random;

public class Izziv3 {
    public static void main(String[] args){
        System.out.println("   n       |     linearno  |   dvojisko  |");
        System.out.println("---------+--------------+------------------");
        for (int i = 100000; i < 1000000; i += 10000)
            System.out.printf("%10d |%14d |%13d%n", i, timeLinear(i), timeBinary(i));
    }

    private static int[] generateTable(int n){
        int[] tab = new int[n];
        for (int i = 0; i < n; i++)
            tab[i] = i + 1;
        return tab;
    }

    static int findLinear(int[] a, int v){
        for (int i = 0; i < a.length; i++)
            if (a[i] == v)
                return i;
        return -1;
    }

    static int findBinary(int[] a, int l, int r, int v){
        int srednji = (r + l) / 2;
        if (l > r)
            return -1;
        if (a[srednji] == v)
            return srednji;
        else if (v < a[srednji])
            return findBinary(a, l, srednji - 1, v);
        else
            return findBinary(a, srednji + 1, r, v);
    }

    static long timeLinear(int n){
        int[] tab = generateTable(n);
        Random r = new Random();
        int rez;
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++)
            rez = findLinear(tab, r.nextInt(n) + 1);
        long executionTime = System.nanoTime() - startTime;
        return executionTime / 1000;
    }

    static long timeBinary(int n){
        int[] tab = generateTable(n);
        Random r = new Random();
        int rez;
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++)
            rez = findBinary(tab, 0, 99, r.nextInt(n) + 1);
        long executionTime = System.nanoTime() - startTime;
        return executionTime / 1000;
    }
}
/*
   n       |     linearno  |   dvojisko  |
---------+--------------+------------------
    100000 |         20166 |          338
    110000 |         17620 |           95
    120000 |         13469 |          101
    130000 |         16001 |           93
    140000 |         14678 |           72
    150000 |         17942 |           78
    160000 |         18837 |           99
    170000 |         17180 |           74
    180000 |         22654 |           79
    190000 |         21623 |           97
    200000 |         23266 |           74
    210000 |         25876 |           80
    220000 |         26341 |           74
    230000 |         24669 |           95
    240000 |         29097 |           72
    250000 |         26156 |           77
    260000 |         37241 |           79
    270000 |         28263 |           78
    280000 |         28621 |           79
    290000 |         38180 |           76
    300000 |         38125 |           78
    310000 |         43461 |           78
    320000 |         50209 |           77
    330000 |         49493 |           81
    340000 |         52159 |           78
    350000 |         40731 |           81
    360000 |         37284 |           81
    370000 |         48771 |           74
    380000 |         55820 |           75
    390000 |         50305 |           75
    400000 |         43529 |           73
    410000 |         51513 |           86
    420000 |         48786 |          111
    430000 |         50783 |           77
    440000 |         48789 |           74
    450000 |         69761 |           80
    460000 |         52058 |           77
    470000 |         65643 |           76
    480000 |         55233 |           84
    490000 |         51358 |           74
    500000 |         62553 |           80
    510000 |         49478 |           76
    520000 |         53895 |           73
    530000 |         57114 |           78
    540000 |         59219 |           76
    550000 |         58038 |           81
    560000 |         57570 |           83
    570000 |         83230 |           77
    580000 |         82761 |           75
    590000 |         88883 |           76
    600000 |         89593 |           75
    610000 |         77244 |           79
    620000 |         79832 |           79
    630000 |         75092 |           78
    640000 |         75778 |           80
    650000 |         70644 |           77
    660000 |         84139 |           78
    670000 |         70330 |           78
    680000 |        105995 |           76
    690000 |         88732 |           70
    700000 |         83986 |           96
    710000 |         78035 |           74
    720000 |         82637 |           74
    730000 |         99367 |           27
    740000 |         64346 |           20
    750000 |         91500 |           20
    760000 |         69816 |           20
    770000 |         73648 |           20
    780000 |         79529 |           20
    790000 |         90438 |           28
    800000 |         89055 |           20
    810000 |         83468 |           20
    820000 |        100364 |           20
    830000 |         83474 |           21
    840000 |        118850 |           23
    850000 |         97575 |           20
    860000 |         89317 |           20
    870000 |         79872 |           28
    880000 |         82318 |           20
    890000 |         96156 |           20
    900000 |         84580 |           20
    910000 |         97284 |           18
    920000 |         93406 |           20
    930000 |         88778 |           29
    940000 |         86937 |           20
    950000 |         92630 |           30
    960000 |         96923 |           23
    970000 |        141828 |           23
    980000 |        144413 |           25
    990000 |        122822 |           20

e)
*časi se razlikujejo zaradi hardwera
*za majhne tabele bi biu lahko linearen za velike pa definitivo dvojiški
*pri linearnem je skoraj kvadratno pri binarnem pa predvidevam da je začel uporabljati
več procesorske moči saj se čas iskanja manjša z velikostjo tabele
*predvidevam da konstantni a pri meni je še boljša časovna odvisnost od konstantne

 */