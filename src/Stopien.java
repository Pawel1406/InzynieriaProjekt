public enum Stopien {
    Szeregowy,
    StarszySzeregowy,
    Kapral,
    Podporucznik,
    Porucznik,
    Kapitan,
    Major,
    Podpulkownik,
    Pulkownik,
    GeneralBrygada,
    GeneralDywizji,
    GeneralBroni,
    GeneralArmii;

    public Stopien awansuj(){
        int obecnyIndex=this.ordinal();
        Stopien[] wszystkieStopnie=Stopien.values();
        if (obecnyIndex<wszystkieStopnie.length-1){
            return wszystkieStopnie[obecnyIndex+1];
        }
        return this;
    }

}
