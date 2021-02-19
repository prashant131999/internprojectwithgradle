package eccomapp.cache;

public class Entry {
    int key;
    int value;
    Entry left;
    Entry right;

    public Entry(int key, int value){
        this.key=key;
        this.value=value;
    }
}