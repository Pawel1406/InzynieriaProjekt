import java.time.LocalDate;

public class Cywil extends Czlowiek {
    private final String name;

    public Cywil(String name, String pesel, LocalDate dataUrodzenia) {
        super(pesel, dataUrodzenia, name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
