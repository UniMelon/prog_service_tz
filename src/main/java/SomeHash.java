
public abstract class SomeHash {

    protected abstract int getCapacity();

    protected int hashFunc1(int key) {
        return Math.abs(key % getCapacity());
    }

    protected int hashFunc2(int key) {
        int hash = (int) Math.abs(1 + key % (Math.pow(2, getCapacity())));

        if (hash % 2 == 0) ++hash;

        return hash;
    }
}