package qlkh;

/**
 *
 * @author Lilly
 */
public abstract class ListAbs {
    public int init() {return 0;}
    public abstract void clear();
    public abstract int addNew();
    public abstract int delete();
    public abstract int modify();
    public abstract void showList();
    public int importFromFile() {return 0;}
    public int exportToFile() {return 0;}
}