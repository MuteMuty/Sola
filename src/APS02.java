import java.util.Stack;

public class APS02 {

    public static void main (String[] args){
        Stack<Object> s = new Stack<>();
        String[] st = new String[] {"A", "B", "C", "D", "E", "F", "G"};
        for (String str: st) {
            s.push(str);
        }
        Stack<Object> copy = new Stack<>();
        copy.addAll(s);
        Stack<Object> copy1 = new Stack<>();
        copy1.addAll(s);
        System.out.println(s);
        obrni(s, 2, 3);
        System.out.println(s);
        obrni(copy, 2, 5);
        System.out.println(copy);
        obrni(copy1, 1, 2);
        System.out.println(copy1);
    }

    static void prepisi(Stack<Object> s, Stack<Object> s1){
        while(!s.isEmpty())
            s1.push(s.pop());
    }

    static void obrni(Stack<Object> s, int n, int m) {
        Stack<Object> s1 = new Stack<>();
        Stack<Object> s2 = new Stack<>();
        Stack<Object> s3 = new Stack<>();
        int size = s.size();
        while (!s.isEmpty()) {
            if (n + m < s.size() || s.size() <= n) {
                s1.push(s.pop());
            }else{
                s2.push(s.pop());
            }
        }
        prepisi(s2, s3);
        for (int i = 0; i < size; i++){
            if(i < n || i >= n + m)
                s.push(s1.pop());
            else {
                s.push(s3.pop());
            }
        }
    }
}
