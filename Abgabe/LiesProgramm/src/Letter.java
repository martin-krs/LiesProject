public class Letter {
    private final char value;
    private final boolean completesSyllable;
    private final Letter[] nodes;

    public Letter(char value, boolean comletesSyllable, Letter[] nodes) {
        this.value = value;
        this.completesSyllable = comletesSyllable;
        this.nodes = nodes;
    }

    public char getValue() {
        return value;
    }

    public boolean isCompletingSyllable() {
        return completesSyllable;
    }

    public boolean isChild(char c) {
        for(int i = 0; nodes != null && i<nodes.length; i++) {
            if(nodes[i].getValue() == c){
                return true;
            }
        }
        return false;
    }

    public Letter getChild(char c) {
        for(Letter b: nodes) {
            if(b.getValue() == c) {
                return b;
            }
        }
        return null;
    }
}
