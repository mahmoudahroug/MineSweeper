package engine;

public class Cell {
    private boolean isVisible;
    private boolean flagged;
    private boolean mine;
    private int adjacents;

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(int adjacents) {
        this.adjacents = adjacents;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        if(this.flagged != flagged){
            Game.flagCount += flagged? -1: 1;
        }
        this.flagged = flagged;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String toString(){
        return isVisible? "O" : "X";
    }


}
