import java.time.LocalDate;

public class Piechur extends Zolnierz {
    private int iloscMagazynkow;

    public Piechur(int iloscMagazynkow, LocalDate dataPrzysiegi, String pesel, LocalDate dataUrodzenia, Stopien stopien, String name) {
        super(dataPrzysiegi, pesel, dataUrodzenia, stopien, name);
        this.iloscMagazynkow = iloscMagazynkow;
    }

    public int getIloscMagazynkow() {
        return iloscMagazynkow;
    }

    public void ognia() {
        //TODO()
    }

    public boolean zajmijPozycje(String pozycja) {
        //TODO()
        return false;
    }
}
