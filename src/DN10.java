import java.util.TreeSet;

public class DN10 {
    public static void main(String[] args){
        TreeSet<String> prvi = new TreeSet<>(getVsiPodnizi(args[0]));
        for (String s: args){
            prvi.retainAll(getVsiPodnizi(s));
        }
        int naj = 0;
        String str = null;
        for (String s : prvi) {
            if (s.length() > naj) {
                naj = s.length();
                str = s;
            }
        }
        System.out.print(str);
    }

    static TreeSet<String> getVsiPodnizi(String niz){
        TreeSet<String> tab = new TreeSet<>();
        for (int i = 0; i < niz.length(); i++) {
            for (int j = i + 1; j <= niz.length(); j++) {
                tab.add(niz.substring(i, j));
            }
        }
        return tab;
    }
}
