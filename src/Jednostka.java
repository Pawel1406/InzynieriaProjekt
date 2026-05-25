import java.util.ArrayList;
import java.util.List;

public abstract class Jednostka {
    private String idJednostki;
    private Stan stan;
    private List<Zolnierz> zolnierze = new ArrayList<>();

    public Jednostka(String idJednostki) {
        this.idJednostki = idJednostki;
        this.stan = Stan.W_KOSZARACH;
    }

    public String getIdJednostki() {
        return idJednostki;
    }

    public void setStan(Stan stan) {
        this.stan = stan;
    }
    public void dodajZolnierz(Zolnierz z) {
        zolnierze.add(z);
    }

    public  List<Zolnierz> getZolnierze(){
        return zolnierze;
    }
    public abstract void dodaj(Jednostka jednostka);

    @Override
    public String toString() {
        return "Jednostka{" +
                "idJednostki='" + idJednostki + '\'' +
                ", stan=" + stan +
                ", zolnierze=" + zolnierze +
                '}';
    }
}