
public abstract class SomeHash {

    protected abstract int getCapacity();

    public int hashIndex(Object key) {
        if(key != null) return Math.abs(key.hashCode() % getCapacity());
        else return 0;
    }
}