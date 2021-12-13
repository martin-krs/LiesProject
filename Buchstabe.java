package de.ostfalia.gdp.s21ws.EKdI;

public class Buchstabe {
    private final char value;
    private final boolean completesSyllable;
    private final Buchstabe[] nodes;

    public Buchstabe(char value, boolean comletesSyllable, Buchstabe[] nodes){
        this.value = value;
        this.completesSyllable = comletesSyllable;
        this.nodes = nodes;
    }

    public char getValue() {
        return value;
    }

    public boolean isCompletingSyllable(){
        return completesSyllable;
    }

    public boolean isChild(char c){
        for(int i = 0; nodes != null && i<nodes.length; i++){
            if(nodes[i].getValue() == c){
                return true;
            }
        }
        return false;
    }

    public Buchstabe getChild(char c){
        for(Buchstabe b: nodes) {
            if(b.getValue() == c){
                return b;
            }
        }
        return null;
    }
}
