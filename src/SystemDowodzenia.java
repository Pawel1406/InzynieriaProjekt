import java.time.LocalDate;
public class SystemDowodzenia {
        private Brygada brygada;
        private Magazyn glownySklad;
        private IZrodloRekrutow systemRekrutacji;

        public SystemDowodzenia(Brygada brygada, Magazyn glownySklad, IZrodloRekrutow systemRekrutacji) {
            this.brygada = brygada;
            this.glownySklad = glownySklad;
            this.systemRekrutacji = systemRekrutacji;
        }

        public void przydzielNowegoZolnierza(String pesel, String idJednostki) {
            Kandydat kandydat = systemRekrutacji.pobierzRekruta(pesel);
            Jednostka docelowa = brygada.znajdzJednostke(idJednostki);

            if (docelowa != null && kandydat != null) {
                Piechur nowyPiechur = new Piechur(
                        LocalDate.now(),
                        kandydat.getPesel(),
                        LocalDate.of(2000, 1, 1),
                        Stopien.Szeregowy,
                        kandydat.getImie(),
                        Specjalizacja.Strzelec
                );
                docelowa.dodajZolnierz(nowyPiechur);
            }
        }

        public void wydajWyposazenieJednostce(String idJednostki) {
            Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
            if (docelowa != null) {
                glownySklad.uzbrojJednostke(docelowa);
            }
        }
    }

