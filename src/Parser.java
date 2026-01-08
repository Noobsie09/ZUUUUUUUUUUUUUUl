import java.util.Scanner;

/**
 * Diese Klasse ist Teil der Anwendung "Die Welt von Zuul".
 * "Die Welt von Zuul" ist ein sehr einfaches textbasiertes 
 * Adventure-Game. 
 * 
 * Dieser src.Parser liest Benutzereingaben und wandelt sie in
 * Befehle f�r das Adventure-Game um. Bei jedem Aufruf
 * liest er eine Zeile von der Konsole und versucht, diese als
 * einen src.Befehl aus bis zu zwei W�rtern zu interpretieren. Er
 * liefert den src.Befehl als ein Objekt der Klasse src.Befehl zur�ck.
 * 
 * Der src.Parser verf�gt �ber einen Satz an bekannten Befehlen. Er
 * vergleicht die Eingabe mit diesen Befehlen. Wenn die Eingabe
 * keinen bekannten src.Befehl enth�lt, dann liefert der src.Parser ein als
 * unbekannter src.Befehl gekennzeichnetes Objekt zur�ck.
 * 
 * @author  Michael K�lling und David J. Barnes
 * @version 2016.02.29
 */
class Parser 
{
    private Befehlswoerter befehle;  // h�lt die g�ltigen Befehlsw�rter
    private Scanner leser;           // Lieferant f�r eingegebene Befehle

    /**
     * Erzeuge einen src.Parser, der Befehle von der Konsole einliest.
     */
    public Parser() 
    {
        befehle = new Befehlswoerter();
        leser = new Scanner(System.in);
    }

    /**
     * @return Den n�chsten src.Befehl des Benutzers.
     */
    public Befehl liefereBefehl() 
    {
        String eingabezeile;   // f�r die gesamte Eingabezeile
        String wort1 = null;
        String wort2 = null;

        System.out.print("> ");     // Eingabeaufforderung

        eingabezeile = leser.nextLine();
        
        // Finde bis zu zwei W�rter in der Zeile
        Scanner zerleger = new Scanner(eingabezeile);
        if(zerleger.hasNext()) {
            wort1 = zerleger.next();     // erstes Wort lesen
            if (zerleger.hasNext()) {
                wort2 = zerleger.next();    // zweites Wort lesen
                // Hinweis: Wir ignorieren den Rest der Eingabezeile.
            }
        }
        
        // Jetzt pr�fen, ob der src.Befehl bekannt ist. Wenn ja, erzeugen
        // wir das passende src.Befehl-Objekt. Wenn nicht, erzeugen wir
        // einen unbekannten src.Befehl mit 'null'.
        if(befehle.istBefehl(wort1)) {
            return new Befehl(wort1, wort2);
        }
        else {
            return new Befehl(null, wort2);
        }
    }

}
