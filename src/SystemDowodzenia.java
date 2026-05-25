import java.time.LocalDate;
import java.util.Scanner;

public class SystemDowodzenia {
    private Brygada brygada;
    private Magazyn glownySklad;
    private IZrodloRekrutow systemRekrutacji;

    public SystemDowodzenia(Brygada brygada, Magazyn glownySklad, IZrodloRekrutow systemRekrutacji) {
        this.brygada = brygada;
        this.glownySklad = glownySklad;
        this.systemRekrutacji = systemRekrutacji;
    }

    public void DodajJednostkę(String idJednostki) {
        brygada.dodaj(new JednostkaMala(idJednostki));
        System.out.println("Dodano nową jednostkę: " + idJednostki);
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
            System.out.println("Żołnierz " + kandydat.getImie() + " przydzielony do " + idJednostki);
        } else if (docelowa == null) {
            System.out.println("Nie znaleziono jednostki: " + idJednostki);
        } else {
            System.out.println("Nie znaleziono kandydata w systemie o PESEL: " + pesel);
        }
    }

    public void wydajWyposazenieJednostce(String idJednostki) {
        Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
        if (docelowa != null) {
            glownySklad.uzbrojJednostke(docelowa);
            System.out.println("Wydano wyposażenie dla jednostki: " + idJednostki);
        } else {
            System.out.println("Nie znaleziono jednostki do uzbrojenia.");
        }
    }

    public void awansujZolnierza(String pesel) {
        for (Zolnierz z : brygada.getZolnierze()) {
            if (z.getPesel() != null && z.getPesel().equals(pesel)) {
                z.awansuj();
                System.out.println("Sukces! Żołnierz o PESEL " + pesel + " został awansowany na wyższy stopień: " + z.getStopien());
                return;
            }
        }
        System.out.println("Nie znaleziono żołnierza o podanym PESEL w całej brygadzie.");
    }

    public void wyslijNaPoligon(String idJednostki) {
        Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
        if (docelowa != null) {
            System.out.println("Jednostka " + idJednostki + " wyruszyła na poligon!");
            for (Zolnierz z : docelowa.getZolnierze()) {
                z.wykonajRozkaz("Strzelanie do tarczy");
                for (Bron b : z.listaBroni) {
                    if (b.isSprawna()) b.uzyj(5);
                }
            }
            System.out.println("Ćwiczenia zakończone. Amunicja została zużyta.");
        } else {
            System.out.println("Nie znaleziono jednostki o ID: " + idJednostki);
        }
    }

    public void przeprowadzInspekcjeBroni(String idJednostki) {
        Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
        if (docelowa != null) {
            System.out.println("--- RAPORT Z INSPEKCJI BRONI: " + idJednostki + " ---");
            int niesprawne = 0;
            for (Zolnierz z : docelowa.getZolnierze()) {
                if (!z.SprawdzStanBroni()) {
                    niesprawne++;
                    System.out.println("UWAGA: Żołnierz " + z.getPesel() + " posiada niesprawną broń!");
                }
            }
            if (niesprawne == 0) System.out.println("Cała broń w jednostce jest w pełni sprawna.");
        } else {
            System.out.println("Nie znaleziono jednostki.");
        }
    }

    public void wyswietlStanBrygady() {
        System.out.println("--- RAPORT STAN BRYGADY ---");
        System.out.println(brygada);
    }

    public void wyswietlSkladJednostki(String idJednostki) {
        Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
        if (docelowa != null) {
            System.out.println("--- SKŁAD OSOBOWY JEDNOSTKI " + idJednostki + " ---");
            System.out.println(docelowa);
        } else {
            System.out.println("Nie znaleziono jednostki o ID: " + idJednostki);
        }
    }
    
    public void przeniesDoRezerwy(String idJednostki, String pesel) {
        Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
        if (docelowa != null) {
            boolean usunieto = docelowa.getZolnierze().removeIf(z -> z.getPesel() != null && z.getPesel().equals(pesel));
            if (usunieto) {
                System.out.println("Żołnierz o PESEL " + pesel + " został zdemobilizowany i przeniesiony do rezerwy.");
            } else {
                System.out.println("Nie znaleziono żołnierza o podanym PESEL w tej jednostce.");
            }
        } else {
            System.out.println("Nie znaleziono jednostki.");
        }
    }

    public void szpitalPolowy(String idJednostki) {
        Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
        if (docelowa != null) {
            int wyleczeni = 0;
            for (Zolnierz z : docelowa.getZolnierze()) {
                if (!z.getCzyZdatnyDoSluzby()) {
                    z.setCzyZdatnyDoSluzby(true);
                    wyleczeni++;
                }
            }
            System.out.println("Szpital polowy zakończył pracę. Wyleczono żołnierzy: " + wyleczeni);
        } else {
            System.out.println("Nie znaleziono jednostki.");
        }
    }

    public void serwisUzbrojenia(String idJednostki) {
        Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
        if (docelowa != null) {
            int naprawione = 0;
            for (Zolnierz z : docelowa.getZolnierze()) {
                for (Bron b : z.listaBroni) {
                    if (!b.isSprawna()) {
                        b.setSprawna(true);
                        naprawione++;
                    }
                }
            }
            System.out.println("Zbrojmistrz wykonał przegląd. Naprawiono sztuk broni: " + naprawione);
        } else {
            System.out.println("Nie znaleziono jednostki.");
        }
    }

    public void raportGotowosciBojowej(String idJednostki) {
        Jednostka docelowa = brygada.znajdzJednostke(idJednostki);
        if (docelowa != null) {
            int stanOsobowy = docelowa.getZolnierze().size();
            if (stanOsobowy == 0) {
                System.out.println("Jednostka " + idJednostki + " jest pusta (gotowość 0%).");
                return;
            }

            int wPelniZdolni = 0;
            for (Zolnierz z : docelowa.getZolnierze()) {
                if (z.getCzyZdatnyDoSluzby() && z.SprawdzStanBroni()) {
                    wPelniZdolni++;
                }
            }
            double procentGotowosci = ((double) wPelniZdolni / stanOsobowy) * 100;
            System.out.printf("--- GOTOWOŚĆ BOJOWA %s ---\n", idJednostki);
            System.out.printf("Stan osobowy: %d | W pełni gotowych: %d\n", stanOsobowy, wPelniZdolni);
            System.out.printf("Współczynnik gotowości: %.2f%%\n", procentGotowosci);
        } else {
            System.out.println("Nie znaleziono jednostki.");
        }
    }


    public void przeniesZolnierza(String idObecnej, String idNowej, String pesel) {
        Jednostka zrodlo = brygada.znajdzJednostke(idObecnej);
        Jednostka cel = brygada.znajdzJednostke(idNowej);

        if (zrodlo != null && cel != null) {
            Zolnierz doPrzeniesienia = null;
            for (Zolnierz z : zrodlo.getZolnierze()) {
                if (z.getPesel() != null && z.getPesel().equals(pesel)) {
                    doPrzeniesienia = z;
                    break;
                }
            }

            if (doPrzeniesienia != null) {
                zrodlo.getZolnierze().remove(doPrzeniesienia);
                cel.dodajZolnierz(doPrzeniesienia);
                System.out.println("Pomyślnie przeniesiono żołnierza " + pesel + " do jednostki " + idNowej);
            } else {
                System.out.println("Nie znaleziono żołnierza w jednostce źródłowej.");
            }
        } else {
            System.out.println("Błąd: Upewnij się, że obie jednostki istnieją.");
        }
    }



    public void dajInformacje() {
        System.out.println("\n===== SYSTEM DOWODZENIA =====");
        System.out.println("1: Dodaj jednostkę                [1 18DOZ]");
        System.out.println("2: Przydziel żołnierza            [2 18DOZ 09876543212]");
        System.out.println("3: Uzbrój jednostkę               [3 18DOZ]");
        System.out.println("4: Wyświetl stan brygady          [4]");
        System.out.println("5: Wyświetl skład jednostki       [5 18DOZ]");
        System.out.println("6: Awansuj żołnierza (PESEL)      [6 09876543212]");
        System.out.println("7: Wyślij na poligon              [7 18DOZ]");
        System.out.println("8: Inspekcja broni                [8 18DOZ]");
        System.out.println("9: Przenieś do rezerwy (usuń)     [9 18DOZ 09876543212]");
        System.out.println("10: Szpital polowy (ulecz)        [10 18DOZ]");
        System.out.println("11: Serwis uzbrojenia (napraw)    [11 18DOZ]");
        System.out.println("12: Raport gotowości bojowej      [12 18DOZ]");
        System.out.println("13: Przenieś żołnierza do innej   [13 18DOZ 19DOZ 09876543212]");
        System.out.println("14: Pomoc (wyświetl menu)         [14]");
        System.out.println("15: Wyłącz program                [15]");
        System.out.println("=============================");
    }

    public static void main(String[] args) {
        System.out.println("Podaj id brygady:");
        Scanner sc = new Scanner(System.in);

        // Uzupełnij odpowiednimi klasami, zgodnie z Twoim projektem
        SystemDowodzenia systemDowodzenia = new SystemDowodzenia(new Brygada(sc.nextLine()), new Sklad(), new ZewnetrznyRejestrWrapper());

        systemDowodzenia.dajInformacje();

        while (true) {
            System.out.print("\n> ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split(" ");

            switch (parts[0]) {
                case "1":
                    if (parts.length >= 2) systemDowodzenia.DodajJednostkę(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 1 [idJednostki]");
                    break;
                case "2":
                    if (parts.length >= 3) systemDowodzenia.przydzielNowegoZolnierza(parts[2], parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 2 [idJednostki] [pesel]");
                    break;
                case "3":
                    if (parts.length >= 2) systemDowodzenia.wydajWyposazenieJednostce(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 3 [idJednostki]");
                    break;
                case "4":
                    systemDowodzenia.wyswietlStanBrygady();
                    break;
                case "5":
                    if (parts.length >= 2) systemDowodzenia.wyswietlSkladJednostki(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 5 [idJednostki]");
                    break;
                case "6":
                    if (parts.length >= 2) systemDowodzenia.awansujZolnierza(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 6 [pesel]");
                    break;
                case "7":
                    if (parts.length >= 2) systemDowodzenia.wyslijNaPoligon(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 7 [idJednostki]");
                    break;
                case "8":
                    if (parts.length >= 2) systemDowodzenia.przeprowadzInspekcjeBroni(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 8 [idJednostki]");
                    break;
                case "9":
                    if (parts.length >= 3) systemDowodzenia.przeniesDoRezerwy(parts[1], parts[2]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 9 [idJednostki] [pesel]");
                    break;
                case "10":
                    if (parts.length >= 2) systemDowodzenia.szpitalPolowy(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 10 [idJednostki]");
                    break;
                case "11":
                    if (parts.length >= 2) systemDowodzenia.serwisUzbrojenia(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 11 [idJednostki]");
                    break;
                case "12":
                    if (parts.length >= 2) systemDowodzenia.raportGotowosciBojowej(parts[1]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 12 [idJednostki]");
                    break;
                case "13":
                    if (parts.length >= 4) systemDowodzenia.przeniesZolnierza(parts[1], parts[2], parts[3]);
                    else System.out.println("Błąd: Za mało argumentów. Użycie: 13 [idStarej] [idNowej] [pesel]");
                    break;
                case "14":
                    systemDowodzenia.dajInformacje();
                    break;
                case "15":
                case "exit":
                    System.out.println("System Dowodzenia został pomyślnie wyłączony.");
                    sc.close();
                    return;
                default:
                    System.out.println("Nieznana komenda. Wpisz '14' aby zobaczyć dostępne opcje.");
                    break;
            }
        }
    }
}