import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Zolnierz {
    private Stopien stopien;
    private LocalDate dataPrzysiegi;
    public List<Bron> listaBroni = new ArrayList<>();
    private boolean czyZdatnyDoSluzby;
    private String pesel;
    private LocalDate dataUrodzenia;
    private String name;

    public Zolnierz(LocalDate dataPrzysiegi, String pesel, LocalDate dataUrodzenia, Stopien stopien, String name) {
        this.dataPrzysiegi = dataPrzysiegi;
        this.stopien = stopien;
        this.czyZdatnyDoSluzby = true;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.name = name;
    }

    public Zolnierz(Zolnierz zolnierz) {
        this.stopien = zolnierz.stopien;
        this.dataPrzysiegi = zolnierz.dataPrzysiegi;
        this.czyZdatnyDoSluzby = zolnierz.czyZdatnyDoSluzby;
        this.pesel = zolnierz.pesel;
        this.dataUrodzenia = zolnierz.dataUrodzenia;
        this.name = zolnierz.name;
    }

    public Stopien getStopien() {
        return stopien;
    }

    public LocalDate getDataPrzysiegi() {
        return dataPrzysiegi;
    }

    public boolean getCzyZdatnyDoSluzby() {
        return czyZdatnyDoSluzby;
    }

    public void setCzyZdatnyDoSluzby(boolean czyZdatnyDoSluzby) {
        this.czyZdatnyDoSluzby = czyZdatnyDoSluzby;
    }

    public void awansuj() {
        this.stopien = this.stopien.awansuj();
    }

    public void PrzydzielDoJednostki(Jednostka jednostka) {
        jednostka.dodajZolnierz(this);
    }

    public boolean SprawdzStanBroni() {
        for (Bron bron : listaBroni) {
            if (!bron.isSprawna()) {
                return false;
            }
        }
        return true;
    }

    public void DodajBron(Bron bron) {
        listaBroni.add(bron);
    }

    public boolean wykonajRozkaz(String msg) {
        if (msg != null && !msg.isEmpty()) {
            return true;
        }
        return false;
    }
}