public abstract class Bron {
    private String nazwa;
    private boolean sprawna;

    public Bron(String nazwa) {
        this.nazwa = nazwa;
        this.sprawna = true;
    }

    public boolean isSprawna() {
        return sprawna;
    }

    public void setSprawna(boolean sprawna) {
        this.sprawna = sprawna;
    }

    public abstract void uzyj();

    public abstract void uzyj(int iloscStrzalow);
}