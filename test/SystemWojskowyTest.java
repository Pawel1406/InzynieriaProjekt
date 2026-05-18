import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
public class SystemWojskowyTest {
    @Test
    public void testAwansujStopien() {
        Stopien stopien = Stopien.Szeregowy;
        stopien = stopien.awansuj();
        assertEquals(Stopien.StarszySzeregowy, stopien);
    }

    @Test
    public void testAwansujGeneral() {
        Stopien stopien = Stopien.GeneralArmii;
        stopien = stopien.awansuj();
        assertEquals(Stopien.GeneralArmii, stopien);
    }

    @Test
    public void testKompozytDodawanieZolnierzy() {
        Brygada brygada = new Brygada("BRY-1");
        JednostkaMala j1 = new JednostkaMala("PLUT-1");
        JednostkaMala j2 = new JednostkaMala("PLUT-2");

        Piechur p1 = new Piechur(LocalDate.now(), "111", LocalDate.now(), Stopien.Szeregowy, "Jan", Specjalizacja.Strzelec);
        Piechur p2 = new Piechur(LocalDate.now(), "222", LocalDate.now(), Stopien.Kapral, "Adam", Specjalizacja.Medyk);

        j1.dodajZolnierza(p1);
        j1.dodajZolnierza(p1);
        j1.dodajZolnierza(p1);
        j2.dodajZolnierza(p2);

        brygada.dodaj(j1);
        brygada.dodaj(j2);


        assertEquals(4, brygada.getZolnierze().size());
    }

    @Test
    public void testKompozytWyjatekMalaJednostka() {
        JednostkaMala j1 = new JednostkaMala("PLUT-1");
        JednostkaMala j2 = new JednostkaMala("PLUT-2");

        assertThrows(UnsupportedOperationException.class, () -> {
            j1.dodaj(j2);
        });
    }

    @Test
    public void testFasadaPrzydzielZolnierza() {
        Brygada brygada = new Brygada("BRY-1");
        JednostkaMala j1 = new JednostkaMala("PLUT-1");
        brygada.dodaj(j1);

        Sklad sklad = new Sklad();
        ZewnetrznyRejestrWrapper wrapper = new ZewnetrznyRejestrWrapper();

        SystemDowodzenia fasada = new SystemDowodzenia(brygada, sklad, wrapper);

        fasada.przydzielNowegoZolnierza("12345678901", "PLUT-1");

        assertEquals(1, brygada.getZolnierze().size());
    }

    @Test
    public void testFasadaWydajWyposazenie() {
        Brygada brygada = new Brygada("BRY-1");
        JednostkaMala j1 = new JednostkaMala("PLUT-1");
        brygada.dodaj(j1);

        Piechur p1 = new Piechur(LocalDate.now(), "111", LocalDate.now(), Stopien.Szeregowy, "Jan", Specjalizacja.Strzelec);
        j1.dodajZolnierza(p1);

        Sklad sklad = new Sklad();
        ZewnetrznyRejestrWrapper wrapper = new ZewnetrznyRejestrWrapper();

        SystemDowodzenia fasada = new SystemDowodzenia(brygada, sklad, wrapper);

        fasada.wydajWyposazenieJednostce("PLUT-1");

        assertEquals(1, p1.listaBroni.size());
    }
}

