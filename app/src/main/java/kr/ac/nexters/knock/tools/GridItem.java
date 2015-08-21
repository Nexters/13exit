package kr.ac.nexters.knock.tools;

/**
 * Created by Min on 2015-08-22.
 */
public class GridItem {
    private int mainItemID;
    private int subItemID;

    public GridItem(int mainItemID, int subItemID) {
        this.mainItemID = mainItemID;
        this.subItemID = subItemID;
    }

    public int getMainItemID() {
        return mainItemID;
    }

    public void setMainItemID(int mainItemID) {
        this.mainItemID = mainItemID;
    }

    public int getSubItemID() {
        return subItemID;
    }

    public void setSubItemID(int subItemID) {
        this.subItemID = subItemID;
    }
}
