public class Direktorij extends Element {
    int nivo = 0;
    String ime = "";

    Direktorij(String ime){
        this.ime = ime;
    }

    Direktorij(int nivo, String ime){
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
        return s + "+ Direktorij <" + this.ime + ">";
    }
}
