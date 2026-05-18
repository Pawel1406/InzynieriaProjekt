import java.util.ArrayList;
import java.util.List;

public class JednostkaMala extends Jednostka {


    public JednostkaMala(String idJednostki) {
        super(idJednostki);
    }

    public void dodajZolnierza(Zolnierz zolnierz) {
        super.dodajZolnierz(zolnierz);
    }

    @Override
    public List<Zolnierz> getZolnierze() {
        return super.getZolnierze();
    }

    @Override
    public void dodaj(Jednostka jednostka) {
        throw new UnsupportedOperationException("Nie mozna dodac jednostki do malej jednostki");
    }
}