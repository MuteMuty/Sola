public class Datoteka extends Element {
    int nivo = 0;
    String ime = "";

    Datoteka(String ime){
        this.ime = ime;
    }

    Datoteka(int nivo, String ime){
        this.nivo = nivo;
        this.ime = ime;
    }

    public String getIme(){
        return this.ime;
    }

    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i < this.nivo; i++){
            s += "   ";
        }
        return s + "- " + this.ime;
    }
}
