import java.time.LocalDate;

public class Medyk extends Zolnierz{
    private String certyfikat;
    private int iloscApteczek;

    public Medyk(Zolnierz zolnierz, String certyfikat, int iloscApteczek) {
        super(zolnierz);
        this.certyfikat = certyfikat;
        this.iloscApteczek = iloscApteczek;
    }

    public Medyk(LocalDate dataPrzysiegi, String pesel, LocalDate dataUrodzenia, Stopien stopien, String name, String certyfikat, int iloscApteczek) {
        super(dataPrzysiegi, pesel, dataUrodzenia, stopien, name);
        this.certyfikat = certyfikat;
        this.iloscApteczek = iloscApteczek;
    }
}
