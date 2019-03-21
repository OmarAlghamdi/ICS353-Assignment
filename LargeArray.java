import sun.misc.Unsafe;
class largeArray{
    private static final int BYTE = 4;
    private long size;
    private long address;
    public largeArray(long size){
        this.size = size;
        address = getUnsafe().allocateMemory(size*size*BYTE);
    }
    public void set(long i, long j, int value){
        getUnSafe().putByte(address + (i*size+j)*BYTE, value);
    }
    public int get(long i, long j){
        return getUnSafe().getByte(address + (i*size+j)*BYTE);

    }
    public long size() {
        return size;
    }
        
}