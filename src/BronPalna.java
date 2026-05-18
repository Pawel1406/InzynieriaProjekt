public class BronPalna extends Bron {
    private int amunicja;

    public BronPalna(String nazwa, int amunicja) {
        super(nazwa);
        this.amunicja = amunicja;
    }

    public void uzyj(int iloscStrzalow) {
        for (int i = 0; i < iloscStrzalow; i++) {
            if (amunicja > 0) {
                amunicja--;
            } else {
                break;
            }
        }
    }

    @Override
    public void uzyj() {
        if (amunicja > 0) {
            amunicja--;
        }
    }
}