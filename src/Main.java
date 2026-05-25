void main()
{
    SystemDowodzenia systemDowodzenia=new SystemDowodzenia(new Brygada("1234"),new Sklad(),new ZewnetrznyRejestrWrapper());
    systemDowodzenia.przydzielNowegoZolnierza("05123498762","123");
}

