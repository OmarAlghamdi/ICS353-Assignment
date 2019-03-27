public class Matrix{
    public static void main(String[] args) {
        int n = 5, x = 1;
        long[][] a = new long[n][n];
        long[][] b = new long[n][n];       // Defined two test nxn test matrices - asaad 
        while(x <= 25)      
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) //populate matrix a until (n^2)th element - asaad
                    a[i][j] = x++;
        while(x <= 50)          // same
            for (int i = 0; i < n; i++) 
                for (int j = 0; j < n; j++) 
                    b[i][j] = x++;

        long[][] c = iterative(a, b, n);    // Apply the iterative multiplication - asaad
        long[][] strassenc = strassen(a, b, n, 4);  // Apply strassen - asaad
  
       // print(matrixPadding(c, n)); // No need!
    
    }

    public static long[][] iterative(long[][] a, long[][] b, int n){
        long[][] c = new long[n][n];    // resultant matrix.. ok. - asaad
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++)     
                    sum += a[i][k] * b[k][j];   
                c[i][j] = sum;
            }
            /*
            Analysis:
            Example multiplication of 1x3 by 3x1 yields 1x1
                         [1]     
            [1, 2, 3] *  [2]    = [1+4+9] = [14]
                         [3]
            *Resultant matrix will have the same number of rows as the first and 
            same number of columns as the second.
            * Algorithm looks good! - asaad
            */
        return c;
    }
    public static void print(long[][] c){
        for (int i = 0; i < c.length; i++) {
            for ( int j = 0; j < c[i].length; j++) {
               System.out.print(c[i][j] + "\t"); 
            }
            System.out.println();
        }
    }

    public static long[][] matrixPadding(long [][] a, int n){
        // & is a bitwise and operation opration
        /*Analysis - asaad:
        Let's consider n=5, n>0 and 101 AND 100 yields 1.
        Let's consider n=4, n>0 and 100 AND 011 yields 0
        Oh ok good. Very hacky and edgy way to figure out whether a number is odd. cool.
        */
        if(n > 0 && (n & (n - 1)) == 0) 
            return a;
        else{
            int m =1;   // A variable m, the new padded matrix size - asaad
            while(m < n)    // As long as m is smaller than n, keep multiplying by 2. - asaad
                // left shift
                m <<= 1;
            long[][] b = new long[m][m];    // the new padded matrix
            for (int i = 0; i < n; i++) {   // then we copy over the elements.. OK. - asaad
                for (int j = 0; j < n; j++) {
                    b[i][j] = a[i][j];  
                }
            }
            return b;
        }
    }
    // NOTE: it is not efficint to add & subtract m1..m7 during multiplcation step
    public static long[][] strassen(long[][] a, long[][] b, int n, int base){
        // TODO define base case flow
        if(n < base){
            // TEMP print statements to obeserve the base case behavior
            print(a);
            System.out.println("---------");
            print(b);
            System.out.println("=========");
            //////////////////////////
            return iterative(a, b, n);
            

        }
        a = matrixPadding(a, n);    // Padding applied if needed to a and b.
        b = matrixPadding(b, n);

        int m = a.length/2;        // Divide the array into two halves. 
        // NOTE: Trying n/2 yields incorrect division. MIGHT NOT NEED n as a parameter
        long[][] a11, a12, a21, a22;    
        long[][] b11, b12, b21, b22;
        long[][] m1, m2, m3, m4, m5, m6, m7;
        long[][] c11, c12, c21, c22;
        // initalizations
        m1 = new long[m][m]; m2 = new long[m][m]; m3 = new long[m][m];
        m4 = new long[m][m]; m5 = new long[m][m]; m6 = new long[m][m];
        m7 = new long[m][m];
        a11 = new long[m][m]; a12 = new long[m][m];
        a21 = new long[m][m]; a22 = new long[m][m];
        b11 = new long[m][m]; b12 = new long[m][m];
        b21 = new long[m][m]; b22 = new long[m][m];
        c11 = new long[m][m]; c12 = new long[m][m];
        c21 = new long[m][m]; c22 = new long[m][m];
        
        // TODO partition
        long[][] ma1 = new long[m][m]; long[][] mb1 = new long[m][m];
        long[][] ma2 = new long[m][m]; long[][] mb2 = new long[m][m];

        // Split the arrays into two halves
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                ma1[i][j]=a[i][j];
                mb1[i][j]=b[i][j];
                ma2[i][j]=a[i+m][j+m];
                mb2[i][j]=b[i+m][j+m];
            }
        }

        /////// TEMPORARY PRINT STATEMENTS to observe the recursive behavior
        System.out.println("N="+n+" M="+m+ " a.length="+a.length);
        print(ma1);
        System.out.println("---------");
        print(mb1);
        System.out.println("=========");
        print(ma2);
        System.out.println("---------");
        print(mb2);
        System.out.println("=========");
        ////////////////////////// 


        //// DIVIDE STEP
        long[][] cs = strassen(ma1, mb1, m, base);
        long[][] cs2 = strassen(ma2, mb2, m, base);
        // STOPPED HERE. TO BE CONTINUED.
        // TODO multiply
        // TODO combine
        
        return null;
    }
    // TODO partition method
    // TODO combine method
    public static long[][] matrixAddition(long[][] a, long[][] b, int n){
        // Straightforward matrix addition
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
    public static long[][] matrixSubtraction(long[][] a, long[][] b, int n){
        // Straightforward matrix subtraction
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }
}