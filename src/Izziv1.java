public class Izziv1 {
    public static void main(String [] args) throws CollectionException {
        Stack<String> s = new ArrayDeque<>();
        Deque<String> d = new ArrayDeque<>();
        Sequence<String> z = new ArrayDeque<>();
        s.push("ABC");
        s.push("DEF");
        s.push("GHI");
        System.out.print("Stack: ");
        while(!s.isEmpty()){
            System.out.print(s.top() + " ");
            d.enqueueFront(s.pop() + " ");
        }
        System.out.print("\nDeque: ");
        while(!d.isEmpty()){
            System.out.print(d.back() + " ");
            z.add(d.dequeueBack());
        }
        System.out.println("\nSequence: ");
        for(int i = 0; i < z.size(); i++)
            System.out.print((i + 1) + "." + z.get(i) + " ");
    }
}
