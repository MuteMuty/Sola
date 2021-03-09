public abstract class Behavior{
    abstract void update(Human human, Human[] neighborhood);
}

class UnawareBehavior extends Behavior{
    void update(Human human, Human[] neighborhood){
        java.util.Random r = new java.util.Random();
        int rand = r.nextInt(8);//r radij = 1 -> 8 polji v okolici
        /*        "0"      "1"      "2"
                -1,-1     -1,0    -1,+1

                 "7"  "8/default"   "3"
                 0,-1     *us*     0,+1

                 "6"       "5"     "4"
                +1,-1     +1,0    +1,+1
         */
        switch (rand){
            case 0:
                human.position.move(-1, -1); break;
            case 1:
                human.position.move(-1, 0); break;
            case 2:
                human.position.move(-1, 1); break;
            case 3:
                human.position.move(0, 1); break;
            case 4:
                human.position.move(1, 1); break;
            case 5:
                human.position.move(1, 0); break;
            case 6:
                human.position.move(1, -1); break;
            case 7:
                human.position.move(0, -1); break;
            default:
                human.position.move(0, 0); break;
        }
    }
}

class AwareBehavior extends Behavior{
    void update(Human human, Human[] neighborhood){
        int huX = human.getPosition()[0];
        int huY = human.getPosition()[1];
        int[] no = new int[neighborhood.length];
        int i = 0;
        for (Human h: neighborhood){
            if (h.position.distance(huX, huY) == 1 && h.getIsInfected()){
                no[i++] = moore(h.getPosition()[0], h.getPosition()[1], huX, huY);
            }
        }
        int max = java.util.Arrays.stream(no).max().getAsInt();
        int choose;
        if (max < 7){
            java.util.Random r = new java.util.Random();
            choose = r.nextInt(7 - max) + max + 1;
        }else {
            choose = 8;
        }
        switch (choose){
            case 0:
                human.position.move(-1, -1); break;
            case 1:
                human.position.move(-1, 0); break;
            case 2:
                human.position.move(-1, 1); break;
            case 3:
                human.position.move(0, 1); break;
            case 4:
                human.position.move(1, 1); break;
            case 5:
                human.position.move(1, 0); break;
            case 6:
                human.position.move(1, -1); break;
            case 7:
                human.position.move(0, -1); break;
            default:
                human.position.move(0, 0); break;
        }
    }

    int moore(int x1, int y1, int x2, int y2){
        int x = x1 - x2;
        int y = y1 - y2;
        switch (x){
            case -1:
                switch (y){
                    case -1:
                        return 0;
                    case 0:
                        return 1;
                    case 1:
                        return 2;
                }
            case 0:
                switch (y){
                    case -1:
                        return 7;
                    case 0:
                        return 8;
                    case 1:
                        return 3;
                }
            case 1:
                switch (y){
                    case -1:
                        return 6;
                    case 0:
                        return 5;
                    case 1:
                        return 4;
                }
        }
        return 8;
    }
}
