/**
 * Diese Klasse modelliert R?ume in der Welt von Zuul.
 * 
 * Diese Klasse ist Teil der Anwendung "Die Welt von Zuul".
 * "Die Welt von Zuul" ist ein sehr einfaches textbasiertes 
 * Adventure-Game.
 * 
 * Ein "src.Raum" repr?sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein src.Raum ist mit anderen R?umen ?ber Ausg?nge verbunden.
 * M?gliche Ausg?nge liegen im Norden, Osten, S?den und Westen.
 * F?r jede Richtung h?lt ein src.Raum eine Referenz auf den
 * benachbarten src.Raum.
 * 
 * @author  Michael K?lling und David J. Barnes
 * @version 2016.02.29
 */
public class Raum 
{
    public String beschreibung;
    public Raum nordausgang;
    public Raum suedausgang;
    public Raum ostausgang;
    public Raum westausgang;

    /**
     * Erzeuge einen src.Raum mit einer Beschreibung. Ein src.Raum
     * hat anfangs keine Ausg?nge. Eine Beschreibung hat die Form 
     * "in einer K?che" oder "auf einem Sportplatz".
     * @param beschreibung  die Beschreibung des Raums
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;
    }

    /**
     * Definiere die Ausg?nge dieses Raums. Jede Richtung
     * f?hrt entweder in einen anderen src.Raum oder ist 'null'
     * (kein Ausgang).
     * @param norden  der Nordausgang
     * @param osten   der Ostausgang
     * @param sueden  der S?dausgang
     * @param westen  der Westausgang
     */
    public void setzeAusgaenge(Raum norden, Raum osten,
                               Raum sueden, Raum westen) 
    {
        if(norden != null) {
            nordausgang = norden;
        }
        if(osten != null) {
            ostausgang = osten;
        }
        if(sueden != null) {
            suedausgang = sueden;
        }
        if(westen != null) {
            westausgang = westen;
        }
    }

    /**
     * @return  die Beschreibung dieses Raums
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }
}
