public class Human{
    Position position;
    private boolean isInfected;
    private boolean hasSymptoms;

    Human(){
        this.position = new Position();
        this.isInfected = false;
        this.hasSymptoms = false;
    }

    Human(int x, int y){
        this.position = new Position(x, y);
        this.isInfected = false;
        this.hasSymptoms = false;
    }

    Human(int x, int y, boolean isInfected, boolean hasSymptoms){
        this.position = new Position(x, y);
        this.isInfected = isInfected;
        this.hasSymptoms = hasSymptoms;
    }

    int[] getPosition(){
        return new int[] {this.position.getX(), this.position.getY()};
    }

    boolean getIsInfected(){
        return this.isInfected;
    }

    boolean getHasSymptoms(){
        return this.hasSymptoms;
    }

    void setPosition(int x, int y){
        this.position = new Position(x, y);
    }

    void setIsInfected(boolean infected){
        this.isInfected = infected;
    }

    void setHasSymptoms(boolean symptoms){
        this.hasSymptoms = symptoms;
    }
}
