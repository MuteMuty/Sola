package Izziv2;

import java.util.Random;

public class Izziv2 {
    static class Oseba{
        String ime, priimek;
        int letoR;

        Oseba(){
            Random r = new Random();
            String[] imena = {"Michael", "Christopher", "Jessica", "Matthew", "Ashley", "Jennifer", "Joshua", "Amanda", "Daniel", "David", "James", "Robert", "John", "Joseph", "Andrew", "Ryan", "Brandon", "Jason", "Justin", "Sarah", "William", "Jonathan", "Stephanie", "Brian", "Nicole", "Nicholas", "Anthony", "Heather", "Eric", "Elizabeth"};
            String[] priimki = {"Woolard", "Winstanley", "Ferrell", "Rendle", "Sheckles", "Camotta", "Newbury", "Wafford", "Mccullagh", "Broatch", "Taylot", "Jardine", "Hamblin", "Boardus", "Brackenbury", "Blacket", "Hutton", "Laurenson", "Eubanks", "Crawford", "Mckone", "Amundson", "Carthorpe", "Livsey", "Tatum", "Hardbattle", "Pruner", "Noell", "Sevil", "Collyer"};
            this.ime = imena[r.nextInt(imena.length)];
            this.priimek = priimki[r.nextInt(priimki.length)];
            this.letoR = 1910 + r.nextInt(110);
        }

        public String toString(){
            return this.ime + " " + this.priimek + ", " + this.letoR;
        }
    }

    public static void main(String[] args) throws CollectionException {
        LinkedSequence<Oseba> l = new LinkedSequence<Oseba>();

        Random r = new Random();
        int st = 10 + r.nextInt(50);
        System.out.println("st moznih oseb: " + st);
        int add = 0, ins = 0, set = 0, del = 0;
        for (int i = 0; i < st; i++){
            int doo;
            if (l.isEmpty())
                doo = r.nextInt(2);
            else
                doo = r.nextInt(4);
            switch (doo){
                case 0:
                    l.add(new Oseba());
                    add++;
                    break;
                case 1:
                    l.insert(r.nextInt(l.size() + 1), new Oseba());
                    ins++;
                    break;
                case 2:
                    l.set(r.nextInt(l.size()), new Oseba());
                    set++;
                    break;
                case 3:
                    l.delete(r.nextInt(l.size()));
                    del++;
                    break;
            }
        }
        System.out.println("vel tab: " + l.size());
        System.out.println("add: " + add);
        System.out.println("ins: " + ins);
        System.out.println("set: " + set);
        System.out.println("del: " + del + "\n");
        for (int i = 0; i < l.size(); i++){
            System.out.println(l.get(i).toString());
        }
    }
}
