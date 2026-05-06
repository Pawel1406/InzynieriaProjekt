import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Zolnierz extends Czlowiek {
    private Stopien stopien;
    private LocalDate dataPrzysiegi;
    public List<Bron> listaBroni = new ArrayList<>();

    public Zolnierz(LocalDate dataPrzysiegi, String pesel, LocalDate dataUrodzenia, Stopien stopien, String name) {
        super(pesel, dataUrodzenia, name);
        this.dataPrzysiegi = dataPrzysiegi;
        this.stopien = stopien;
    }

    public Zolnierz(Zolnierz zolnierz) {
        super(zolnierz.getPesel(), zolnierz.getDataUrodzenia(), zolnierz.getName());
        this.stopien = zolnierz.stopien;
        this.dataPrzysiegi = zolnierz.dataPrzysiegi;
    }

    public void awansuj() {
        this.stopien.awansuj();
    }

    public void PrzydzielDoJednostki(Jednostka jednostka) {
        //TODO()
    }

    public Stopien getStopien() {
        return stopien;
    }

    public LocalDate getDataPrzysiegi() {
        return dataPrzysiegi;
    }

    public boolean SprawdzStanBroni() {
        //TODO()
        return false;
    }

    public void DodajBron(Bron bron) {
        listaBroni.add(bron);
    }

    public boolean wykonajRozkaz(String msg) {
        //TODO(switchCase)
        return false;
    }

}
