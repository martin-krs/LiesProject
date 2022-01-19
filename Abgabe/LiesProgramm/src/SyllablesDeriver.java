import java.util.ArrayList;

public class SyllablesDeriver {

    private final ArrayList<String> syllables = new ArrayList<>();

    /**
     * fügt silbe hinzu, indem der gesamte text an silbeFinden() übergeben wird, die genutzen Lettern
     * werden vorne abgeschnitten, bis der gesamte text verarbeitet wurde
     * @param s der text, aus dem die silben gefunden werden sollen
     * @return gibt ein array mit allen silben des parameterstrings zurück
     */
    public String[] getSyllables(String s) {
        s = s.toLowerCase();
        s = adjustString(s);
        while (0 < s.length()) {
            if(root.isChild(s.charAt(0))) {
                    syllables.add(findSyllables(root.getChild(s.charAt(0)), s, "" + s.charAt(0)));
                    s = s.substring(syllables.get(syllables.size() - 1).length());
            }else {
                syllables.add(""  + s.charAt(0));
                s = s.substring(1);
            }
        }
        spaces();
        return syllables.toArray(new String[0]);
    }
    /**
     * rekursionsschritt: ganz normal
     *rekursionanker: text muss länger sein als silbe, Letter muss möglicher nachfolger sein und silbenende
     * oder der text ist länger als silbe+1, übernächster Letter ist nachfolger des Lettern, der auch nach-
     * folger des ersten sein muss, übernächster ist silbenende
     * @param b erst wurzel der Letternbaums, später Lettern des silbenpfads, der genutzt wird
     * @param text der Text, in dem die silben gefunden werden sollen
     * @param syllable bereits der silbe zugewiesene Lettern
     * @return fertige silbe
     */

    private String findSyllables(Letter b, String text, String syllable) {
        if ((text.length()>syllable.length() && b.isChild(text.charAt(syllable.length())) &&
                b.getChild(text.charAt(syllable.length())).isCompletingSyllable())
                || (text.length()>syllable.length()+1 && b.isChild(text.charAt(syllable.length()))
                && b.getChild(text.charAt(syllable.length())).isChild(text.charAt(syllable.length()+1))
                && b.getChild(text.charAt(syllable.length())).getChild(text.charAt(syllable.length()+1)).isCompletingSyllable())){

            syllable = findSyllables(b.getChild(text.charAt(syllable.length())), text, syllable+ text.charAt(syllable.length()));
        }
        return syllable;
    }

    /**
     * ersetzt Leerzeichen in der Liste mit der fertigen Lösung
     * durch das Wort "LEER", wodurch die Pause gesprochen werden kann
     */
    private void spaces(){
        for(int i = 0; i<syllables.size(); i++) {
            if(syllables.get(i).equals(" ")) {
                syllables.set(i, "LEER");
            }
        }
    }

    /**
     * erstetzt alle Sonderzeichen im Text durch Leerzeichen. So kann am Satzende eine längere Pause
     * erreicht werden
     * @param s der String, aus dem danach die Silben getrennt werden sollen
     * @return zurückgegeben wird ein sonderzeichenfreier String
     */
    public String adjustString(String s){
        StringBuilder a = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            if((s.charAt(i)>96 && s.charAt(i)<123) || s.charAt(i) == '�' ||
                s.charAt(i) == '�' || s.charAt(i) == '�' || s.charAt(i) == '�'
                || s.charAt(i)>47 && s.charAt(i)<58) {
                a.append(s.charAt(i));
            }else{
                a.append(" ");
            }
        }
        return a.toString();
    }

    /*
     *Die sechsten Buchstaben der Silben
     */
    Letter r6 = new Letter('r', true, new Letter[]{});
    Letter t6 = new Letter('t', true, new Letter[]{});
    /*
     *Die fünften Buchstaben der Silben
     */
    Letter e5 = new Letter('e', false, new Letter[]{r6});
    Letter f5 = new Letter('f', false, new Letter[]{t6});
    Letter r5 = new Letter('r', true, new Letter[]{});
    Letter n5 = new Letter('n', true, new Letter[]{});
    /*
     *Die vierten Buchstaben der Silben
     */
    Letter a4 = new Letter('a', false, new Letter[]{f5});
    Letter d4 = new Letter('d', false, new Letter[]{e5});
    Letter e4 = new Letter('e', true, new Letter[]{r5});
    Letter e4_2 = new Letter('e', false, new Letter[]{n5});
    Letter g4 = new Letter('g', true, new Letter[]{});
    Letter h4 = new Letter('h', true, new Letter[]{});
    Letter l4 = new Letter('l', true, new Letter[]{});
    Letter n4 = new Letter('n', true, new Letter[]{});
    Letter s4 = new Letter('s', true, new Letter[]{});
    Letter t4 = new Letter('t', true, new Letter[]{});
    Letter t4_2 = new Letter('t', false, new Letter[]{e5});
    /*
     * Die dritten Buchstaben der Silben
     */
    Letter c3 = new Letter('c', false, new Letter[]{h4});
    Letter c3_2 = new Letter('c', true, new Letter[]{});
    Letter d3 = new Letter('d', true, new Letter[]{});
    Letter d3_2 = new Letter('d', false, new Letter[]{e4});
    Letter e3 = new Letter('e', true, new Letter[]{});
    Letter e3_3 = new Letter('e', false, new Letter[]{d4});
    Letter e3_2 = new Letter('e', true, new Letter[]{n4});
    Letter e3_4 = new Letter('e', false, new Letter[]{d4});
    Letter f3 = new Letter('f', true, new Letter[]{});
    Letter f3_2 = new Letter('f', false, new Letter[]{t4});
    Letter g3 = new Letter('g', true, new Letter[]{});
    Letter h3 = new Letter('h', true, new Letter[]{});
    Letter h3_2 = new Letter('h', true, new Letter[]{a4});
    Letter i3 = new Letter('i', true, new Letter[]{});
    Letter i3_2 = new Letter('i', false, new Letter[]{t4});
    Letter i3_3 = new Letter('i', false, new Letter[]{n4});
    Letter i3_4 = new Letter('i', false, new Letter[]{t4_2});
    Letter l3 = new Letter('l', false, new Letter[]{l4});
    Letter m3 = new Letter('m', true, new Letter[]{});
    Letter n3 = new Letter('n', true, new Letter[]{});
    Letter n3_2 = new Letter('n', true, new Letter[]{e4});
    Letter n3_3 = new Letter('n', false, new Letter[]{g4});
    Letter r3 = new Letter('r', true, new Letter[]{});
    Letter r3_2 = new Letter('r', false, new Letter[]{t4});
    Letter r3_3 = new Letter('r', false, new Letter[]{e4_2});
    Letter s3 = new Letter('s', true, new Letter[]{});
    Letter s3_2 = new Letter('s', false, new Letter[]{s4});
    Letter t3 = new Letter('t', true, new Letter[]{});

    /*
     *Die zweiten Buchstaben der Silben
     */
    Letter a2 = new Letter('a', false, new Letter[]{r3});
    Letter a2_2 = new Letter('a', true, new Letter[]{s3});
    Letter a2_3 = new Letter('a', false, new Letter[]{f3_2});
    Letter a2_4 = new Letter('a', false, new Letter[]{c3});
    Letter b2 = new Letter('b', true, new Letter[]{e3});
    Letter c2 = new Letter('c', false, new Letter[]{h3});
    Letter c2_2 = new Letter('c', true, new Letter[]{h3});
    Letter c2_3 = new Letter('c', true, new Letter[]{h3_2});
    Letter d2 = new Letter('d', true, new Letter[]{e3});
    Letter e2 = new Letter('e', true, new Letter[]{i3, n3, r3});
    Letter e2_2 = new Letter('e', true, new Letter[]{n3, r3});
    Letter e2_3 = new Letter('e', true, new Letter[]{n3});
    Letter e2_4 = new Letter('e', true, new Letter[]{i3_2, n3, r3});
    Letter e2_5 = new Letter('e', true, new Letter[]{r3_3});
    Letter e2_6 = new Letter('e', false, new Letter[]{i3_2});
    Letter e2_7 = new Letter('e', true, new Letter[]{i3_3});
    Letter e2_8 = new Letter('e', true, new Letter[]{i3, n3});
    Letter e2_9 = new Letter('e', true, new Letter[]{n3, r3});
    Letter e2_10 = new Letter('e', false, new Letter[]{r3});
    Letter e2_11 = new Letter('e', false, new Letter[]{g3, i3_4});
    Letter g2 = new Letter('g', true, new Letter[]{e3});
    Letter h2 = new Letter('h', true, new Letter[]{e3_2, t3});
    Letter i2 = new Letter('i', true, new Letter[]{e3});
    Letter i2_2 = new Letter('i', true, new Letter[]{n3_2, t3});
    Letter i2_3 = new Letter('i', false, new Letter[]{n3});
    Letter i2_4 = new Letter('i', true, new Letter[]{c3, n3_3});
    Letter i2_5 = new Letter('i', false, new Letter[]{s3_2 ,t3});
    Letter i2_6 = new Letter('i', false, new Letter[]{c3_2, e3_3, s3});
    Letter i2_7 = new Letter('i', true, new Letter[]{c3_2, e3});
    Letter i2_8 = new Letter('i', false, new Letter[]{d3_2, e3_4});
    Letter k2 = new Letter('k', true, new Letter[]{});
    Letter l2 = new Letter('l', true, new Letter[]{});
    Letter l2_2 = new Letter('l', false, new Letter[]{e3});
    Letter n2 = new Letter('n', true, new Letter[]{d3});
    Letter n2_2 = new Letter('n', true, new Letter[]{d3, t3});
    Letter n2_3 = new Letter('n', true, new Letter[]{e3});
    Letter n2_4 = new Letter('n', true, new Letter[]{d3, g3});
    Letter o2 = new Letter('o', false, new Letter[]{r3_2});
    Letter o2_2 = new Letter('o', false, new Letter[]{s3});
    Letter o2_3 = new Letter('o', false, new Letter[]{r3});
    Letter p2 = new Letter('p', true, new Letter[]{});
    Letter r2 = new Letter('r', true, new Letter[]{e3, s3});
    Letter r2_2 = new Letter('r', true, new Letter[]{});
    Letter s2 = new Letter('s', true, new Letter[]{e3});
    Letter s2_2 = new Letter('s', false, new Letter[]{t3, c3});
    Letter t2 = new Letter('t', true, new Letter[]{});
    Letter t2_2 = new Letter('t', false, new Letter[]{e3});
    Letter t2_3 = new Letter('t', true, new Letter[]{e3});
    Letter u2 = new Letter('u', true, new Letter[]{f3, s3});
    Letter u2_2 = new Letter('u', true, new Letter[]{});
    Letter u2_3 = new Letter('u', false, new Letter[]{l3});
    Letter u2_4 = new Letter('u', false, new Letter[]{m3});
    /*
     * Die ersten Buchstaben der Silben
     */
    Letter zero = new Letter('0', true, new Letter[]{});
    Letter one = new Letter('1', true, new Letter[]{});
    Letter two = new Letter('2', true, new Letter[]{});
    Letter three = new Letter('3', true, new Letter[]{});
    Letter four = new Letter('4', true, new Letter[]{});
    Letter five = new Letter('5', true, new Letter[]{});
    Letter six = new Letter('6', true, new Letter[]{});
    Letter seven = new Letter('7', true, new Letter[]{});
    Letter eight = new Letter('8', true, new Letter[]{});
    Letter nine = new Letter('9', true, new Letter[]{});
    Letter a1 = new Letter('a', true, new Letter[]{b2, c2, n2, u2});
    Letter b1 = new Letter('b', true, new Letter[]{a2, e2});
    Letter c1 = new Letter('c', true, new Letter[]{h2, k2});
    Letter d1 = new Letter('d', true, new Letter[]{a2_2, e2_2, i2});
    Letter e1 = new Letter('e', true, new Letter[]{i2_2, l2, n2_2, r2, s2, u2_2});
    Letter f1 = new Letter('f', true, new Letter[]{o2});
    Letter g1 = new Letter('g', true, new Letter[]{e2_3});
    Letter h1 = new Letter('h', true, new Letter[]{a2_3, e2_4, i2_3, t2});
    Letter i1 = new Letter('i', true, new Letter[]{c2_2, e2_5, g2, n2_3, s2_2, t2});
    Letter j1 = new Letter('j', true, new Letter[]{});
    Letter k1 = new Letter('k', true, new Letter[]{e2_6});
    Letter l1 = new Letter('l', true, new Letter[]{e2_7, i2_4, l2_2, o2_2});
    Letter m1 = new Letter('m', true, new Letter[]{e2_3, i2_5});
    Letter n1 = new Letter('n', true, new Letter[]{a2_4, d2, e2_3, g2, i2_6, t2_2, u2_3});
    Letter o1 = new Letter('o', true, new Letter[]{});
    Letter p1 = new Letter('p', true, new Letter[]{});
    Letter q1 = new Letter('q', true, new Letter[]{});
    Letter r1 = new Letter('r', true, new Letter[]{e2_3});
    Letter s1 = new Letter('s', true, new Letter[]{c2_3, e2_8, i2_7, p2, t2_3});
    Letter t1 = new Letter('t', true, new Letter[]{e2_9, u2_4});
    Letter u1 = new Letter('u', true, new Letter[]{n2_4, r2_2});
    Letter v1 = new Letter('v', true, new Letter[]{e2_10, o2_3});
    Letter w1 = new Letter('w', true, new Letter[]{e2_11, i2_8});
    Letter x1 = new Letter('x', true, new Letter[]{});
    Letter y1 = new Letter('y', true, new Letter[]{});
    Letter z1 = new Letter('z', true, new Letter[]{e2_10, u2_2});
    /*
     * Die Wurzel des Baums aus dem die Silben gebildet werden sollen
     */
    Letter root = new Letter('-', false, new Letter[]{zero, one, two, three, four, five, six, seven, eight, nine, a1, b1, c1, d1, e1, f1,
            g1, h1, i1, j1, k1, l1, m1, n1, o1, p1, q1, r1, s1, t1, u1, v1, w1, x1, y1, z1});

}