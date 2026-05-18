public class ZewnetrznyRejestrWrapper implements IZrodloRekrutow {
    @Override
    public Kandydat pobierzRekruta(String pesel) {
        return new Kandydat(pesel, "Jan");
    }
}