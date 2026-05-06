import java.time.LocalDate;

public abstract class Czlowiek {
    private String pesel;
    private LocalDate dataUrodzenia;
    private String name;

    public Czlowiek(String pesel, LocalDate dataUrodzenia, String name) {
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.name = name;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
