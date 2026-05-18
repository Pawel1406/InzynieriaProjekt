public class Sklad implements Magazyn {
    @Override
    public void uzbrojJednostke(Jednostka jednostka) {
        for (Zolnierz z : jednostka.getZolnierze()) {
            z.DodajBron(new BronPalna("Karabin Szturmowy", 30));
        }
    }
}