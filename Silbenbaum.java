package de.ostfalia.gdp.s21ws.EKdI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Silbenbaum {
    //Baum ist unten, sonst scrollt man ne halbe Stunde zum wichtigen Code
    private final ArrayList<String> loesung = new ArrayList<>();

    /**
     * fügt silbe hinzu, indem der gesamte text an silbeFinden() übergeben wird, die genutzen Buchstaben
     * werden vorne abgeschnitten, bis der gesamte text verarbeitet wurde
     * @param s der text, aus dem die silben gefunden werden sollen
     * @return gibt ein array mit allen silben des parameterstrings zurück
     */
    public String[] getSilben(String s) {
        s = s.toLowerCase();
        s = stringBereinigen(s);
        while (0 < s.length()) {
            if(root.isChild(s.charAt(0))) {
                    loesung.add(silbeFinden(root.getChild(s.charAt(0)), s, "" + s.charAt(0)));
                    s = s.substring(loesung.get(loesung.size() - 1).length());
            }else {
                loesung.add(""  + s.charAt(0));
                s = s.substring(1);
            }
        }
        loesungBereinigen();
        return loesung.toArray(new String[0]);
    }
    /**
     * rekursionsschritt: ganz normal
     *rekursionanker: text muss länger sein als silbe, buchstabe muss möglicher nachfolger sein und silbenende
     * oder der text ist länger als silbe+1, übernächster buchstabe ist nachfolger des buchstaben, der auch nach-
     * folger des ersten sein muss, übernächster ist silbenende
     * @param b erst wurzel der buchstabenbaums, später buchstaben des silbenpfads, der genutzt wird
     * @param text der Text, in dem die silben gefunden werden sollen
     * @param silbe bereits der silbe zugewiesene buchstaben
     * @return fertige silbe
     */
    private String silbeFinden(Buchstabe b, String text, String silbe) {
        if ((text.length()>silbe.length() && b.isChild(text.charAt(silbe.length())) &&
            b.getChild(text.charAt(silbe.length())).isCompletingSyllable())
            || (text.length()>silbe.length()+1 && b.isChild(text.charAt(silbe.length()))
            && b.getChild(text.charAt(silbe.length())).isChild(text.charAt(silbe.length()+1))
            && b.getChild(text.charAt(silbe.length())).getChild(text.charAt(silbe.length()+1)).isCompletingSyllable())){

            silbe = silbeFinden(b.getChild(text.charAt(silbe.length())), text, silbe + text.charAt(silbe.length()));
        }
              return silbe;
    }

    /**
     * ersetzt Leerzeichen in der Liste mit der fertigen Lösung
     * durch das Wort "LEER", wodurch die Pause gesprochen werden kann
     */
    private void loesungBereinigen(){
        for(int i = 0; i<loesung.size(); i++) {
            if(loesung.get(i).equals(" ")) {
                loesung.set(i, "LEER");
            }
        }
    }

    /**
     * erstetzt alle Sonderzeichen im Text durch Leerzeichen. So kann am Satzende eine längere Pause
     * erreicht werden
     * @param s der String, aus dem danach die Silben getrennt werden sollen
     * @return zurückgegeben wird ein sonderzeichenfreier String
     */
    public String stringBereinigen(String s){
        StringBuilder a = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            if((s.charAt(i)>96 && s.charAt(i)<123) || s.charAt(i) == 'ä' ||
                s.charAt(i) == 'ß' || s.charAt(i) == 'ö' || s.charAt(i) == 'ü'
                || s.charAt(i)>47 && s.charAt(i)<58) {
                a.append(s.charAt(i));
            }else{
                a.append(" ");
            }
        }
        return a.toString();
    }

    //TODO: vierte Ebene
    Buchstabe a3 = new Buchstabe('a', true, null);
    Buchstabe b3 = new Buchstabe('b', true, null);
    Buchstabe c3 = new Buchstabe('c', true, null);
    Buchstabe h = new Buchstabe('h', true, null);
    //TODO: dritte Ebene
    Buchstabe a2 = new Buchstabe('a', false, new Buchstabe[]{a3, b3, c3});
    Buchstabe b2 = new Buchstabe('b', false, new Buchstabe[]{a3, b3, c3});
    Buchstabe c2 = new Buchstabe('c', false, new Buchstabe[]{a3, b3, c3});
    Buchstabe c = new Buchstabe('c', false, new Buchstabe[]{h});
    //TODO: zweite Ebene
    Buchstabe a1 = new Buchstabe('a', true, new Buchstabe[]{a2, b2, c2});
    Buchstabe b1 = new Buchstabe('b', true, new Buchstabe[]{a2, b2, c2});
    Buchstabe c1 = new Buchstabe('c', true, new Buchstabe[]{a2, b2, c2});
    Buchstabe s = new Buchstabe('s', true, new Buchstabe[]{c});
    //TODO: Wurzel
    Buchstabe root = new Buchstabe('-', false,new Buchstabe[]{a1,b1,c1,s});
}