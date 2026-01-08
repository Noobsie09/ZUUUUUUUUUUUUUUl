
/**
 *  Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul".
 *  "Die Welt von Zuul" ist ein sehr einfaches, textbasiertes
 *  Adventure-Game. Ein Spieler kann sich in einer Umgebung bewegen,
 *  mehr nicht. Das src.Spiel sollte auf jeden Fall ausgebaut werden,
 *  damit es interessanter wird!
 * 
 *  Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und
 *  an ihr die Methode "spielen" aufgerufen werden.
 * 
 *  Diese Instanz erzeugt und initialisiert alle anderen Objekte
 *  der Anwendung: Sie legt alle R?ume und einen src.Parser an und
 *  startet das src.Spiel. Sie wertet auch die Befehle aus, die der
 *  src.Parser liefert, und sorgt f?r ihre Ausf?hrung.
 * 
 * @author  Michael K?lling und David J. Barnes
 * @version 2016.02.29
 */

public class Spiel 
{
    private Parser parser;
    private Raum aktuellerRaum;
        
    /**
     * Erzeuge ein src.Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        raeumeAnlegen();
        parser = new Parser();
    }

    /**
     * Erzeuge alle R?ume und verbinde ihre Ausg?nge miteinander.
     */
    private void raeumeAnlegen()
    {
        Raum draussen, hoersaal, cafeteria, labor, buero;
      
        // die R?ume erzeugen
        draussen = new Raum("vor dem Haupteingang der Universität");
        hoersaal = new Raum("in einem Vorlesungssaal");
        cafeteria = new Raum("in der Cafeteria der Uni");
        labor = new Raum("in einem Rechnerraum");
        buero = new Raum("im Verwaltungsbüro der Informatik");
        
        // die Ausg?nge initialisieren
        draussen.setzeAusgaenge(null, hoersaal, labor, cafeteria);
        hoersaal.setzeAusgaenge(null, null, null, draussen);
        cafeteria.setzeAusgaenge(null, draussen, null, null);
        labor.setzeAusgaenge(draussen, buero, null, null);
        buero.setzeAusgaenge(null, null, null, labor);

        aktuellerRaum = draussen;  // das src.Spiel startet draussen
    }

    /**
     * Die Hauptmethode zum Spielen. L?uft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und f?hren sie aus, bis das src.Spiel beendet wird.
                
        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Danke f?r dieses src.Spiel. Auf Wiedersehen.");
    }

    /**
     * Einen Begr??ungstext f?r den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zu Zuul!");
        System.out.println("Zuul ist ein neues, unglaublich langweiliges src.Spiel.");
        System.out.println("Tippen Sie 'help', wenn Sie Hilfe brauchen.");
        System.out.println();
        System.out.println("Sie sind " + aktuellerRaum.gibBeschreibung());
        System.out.print("Ausgänge: ");
        if(aktuellerRaum.nordausgang != null) {
            System.out.print("north ");
        }
        if(aktuellerRaum.ostausgang != null) {
            System.out.print("east ");
        }
        if(aktuellerRaum.suedausgang != null) {
            System.out.print("south ");
        }
        if(aktuellerRaum.westausgang != null) {
            System.out.print("west ");
        }
        System.out.println();
    }

    /**
     * Verarbeite einen gegebenen src.Befehl (f?hre ihn aus).
     * @param befehl   der zu verarbeitende src.Befehl.
     * @return true    wenn der src.Befehl das src.Spiel beendet, false sonst
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        if(befehl.istUnbekannt()) {
            System.out.println("Ich weiss nicht, was Sie meinen ...");
            return false;
        }
        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help")) {
            hilfstextAusgeben();
        }
        else if (befehlswort.equals("go")) {
            wechsleRaum(befehl);
        }
        else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        
        return moechteBeenden;
    }

    // Implementierung der Benutzerbefehle:

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlsw?rter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Sie haben sich verlaufen. Sie sind allein.");
        System.out.println("Sie irren auf dem Unigelände herum.");
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
        System.out.println("   go quit help");
    }

    /**
     * Versuche, in eine Richtung zu gehen. Wenn es einen Ausgang gibt,
     * wechsele in den neuen src.Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    private void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin m?chten Sie gehen?");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen, den src.Raum zu verlassen.
        Raum naechsterRaum = null;
        if(richtung.equals("north")) {
            naechsterRaum = aktuellerRaum.nordausgang;
        }
        if(richtung.equals("east")) {
            naechsterRaum = aktuellerRaum.ostausgang;
        }
        if(richtung.equals("south")) {
            naechsterRaum = aktuellerRaum.suedausgang;
        }
        if(richtung.equals("west")) {
            naechsterRaum = aktuellerRaum.westausgang;
        }

        if (naechsterRaum == null) {
            System.out.println("Dort ist keine T?r!");
        }
        else {
            aktuellerRaum = naechsterRaum;
            System.out.println("Sie sind " + aktuellerRaum.gibBeschreibung());
            System.out.print("Ausgänge: ");
            if(aktuellerRaum.nordausgang != null) {
                System.out.print("north ");
            }
            if(aktuellerRaum.ostausgang != null) {
                System.out.print("east ");
            }
            if(aktuellerRaum.suedausgang != null) {
                System.out.print("south ");
            }
            if(aktuellerRaum.westausgang != null) {
                System.out.print("west ");
            }
            System.out.println();
        }
    }

    /**
     * "quit" wurde eingegeben. ?berpr?fe den Rest des Befehls,
     * ob das src.Spiel wirklich beendet werden soll.
     * @return true  wenn der src.Befehl das src.Spiel beendet, false sonst
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        else {
            return true;  // Das src.Spiel soll beendet werden.
        }
    }
}
