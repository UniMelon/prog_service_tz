
public abstract class SomeHash {

    protected abstract int getCapacity();

    public final int hashFunc(final int hashCode){
        return (hashCode < getCapacity()) ?
                hashCode : hashFunc(hashCode - getCapacity());
    }
}