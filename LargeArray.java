import  sun.misc.Unsafe;
class LargeArray{
    private static final int BYTE = 4;
    private long size;
    private long address;
    public LargeArray(long size){
        this.size = size;
        address = Unsafe.getUnsafe().allocateMemory(size*size*BYTE);
    }
    public void set(long i, long j, int value){
        Unsafe.getUnsafe().putInt(address + (i*size+j)*BYTE, value);
    }
    public int get(long i, long j){
        return Unsafe.getUnsafe().getInt(address + (i*size+j)*BYTE);
    }
    public long size() {
        return size;
    }
}