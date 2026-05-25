import java.time.LocalDate;

public class Piechur extends Zolnierz {
    private Specjalizacja specjalizacja;

    public Piechur(LocalDate dataPrzysiegi, String pesel, LocalDate dataUrodzenia, Stopien stopien, String name, Specjalizacja specjalizacja) {
        super(dataPrzysiegi, pesel, dataUrodzenia, stopien, name);
        this.specjalizacja = specjalizacja;
    }

    public Specjalizacja getSpecjalizacja() {
        return specjalizacja;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}