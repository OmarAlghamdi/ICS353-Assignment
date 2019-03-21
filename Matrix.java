import sun.misc.Unsafe;
public class Matrix{
    public static int[][] iterative(int[][] a, int[][] b, int n){
        int[][] c = new int[n][n];
        for (long i = 0; i < n; i++) 
            for (long j = 0; j < n; j++) {
                int sum = 0;
                for (long k = 0; k < n; k++)
                    sum += a[i][k] * b[k][j];
                c[i][j] = sum;
            }
        return c;
    }
    public static void print(int[][] c){
        for (int i = 0; i < c.length; i++) {
            for ( int j = 0; j < c[i].length; j++) {
               System.out.print(c[i][j] + "\t"); 
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int n = 5, x = 1;
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        while(x <= 25)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    a[i][j] = x++;
        while(x <= 50)
            for (int i = 0; i < n; i++) 
                for (int j = 0; j < n; j++) 
                    b[i][j] = x++;
        int[][] c = iterative(a, b, n);
        print(c);

    }
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
}