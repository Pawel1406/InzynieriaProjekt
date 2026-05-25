import java.util.ArrayList;
import java.util.List;

public class Brygada extends Jednostka {
    private List<Jednostka> podjednostki = new ArrayList<>();

    public Brygada(String idJednostki) {
        super(idJednostki);
    }

    @Override
    public void dodaj(Jednostka jednostka) {
        podjednostki.add(jednostka);
    }

    @Override
    public List<Zolnierz> getZolnierze() {
        List<Zolnierz> zolnierze = new ArrayList<>();
        for (Jednostka jednostka : podjednostki) {
            zolnierze.addAll(jednostka.getZolnierze());
        }
        return zolnierze;
    }

    public List<Jednostka> getPodjednostki() {
        return podjednostki;
    }

    public Jednostka znajdzJednostke(String id) {
        for (Jednostka j : podjednostki) {
            if (j.getIdJednostki().equals(id)) {
                return j;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Brygada{" +
                "podjednostki=" + podjednostki +
                '}';
    }
}