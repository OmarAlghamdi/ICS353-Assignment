import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.IllegalArgumentException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class matrixMultiply{
    // matrixMultiply opcode n base inputfilename outputfilename
    // NOTE: input file name must conform to the requirement in the project document
    // NOTE2: By project document definition, opcode = 0 -> iterative, opcode = 1 -> strassen
    public static void main(String[] args) {
        try{

        // Input checks: assume a sensible user that inputs proper input values. We could do
        // input checks though. I am only checking for the number of arguments though
        if(args.length == 1){
            try(PrintWriter writer = new PrintWriter(new File(args[0]))){
                StringBuilder sb = new StringBuilder();
                sb.append("n,");
                sb.append("Iter,");
                sb.append("Stras,");
                sb.append("base");
                sb.append('\n');
                writer.write(sb.toString());
                writer.flush();
                sb.setLength(0);
                // Write the CSV headers and clear the stringbuilder - asaad

                for(int i=0;i<1000;i++){    // 1000 Iterations, and we iterate 10 times per iteration 
                System.out.println("\n"+i+":");
                Random r = new Random();    // for each algorithm to take the average time.
                int n = r.nextInt(4000-30+1) + 30;
                int b = 64;
                long[][] matA, matB;
                matA = generateMatrix(n);
                matB = generateMatrix(n);
                int j = 10;
                long iterativeTime = 0; long strassenTime = 0;
                int[] bases = new int[]{64, 256, 512};
                b = bases[r.nextInt(bases.length)];
                while(j > 0){
                System.out.print(j+"\t");
                long startT = System.nanoTime();
                iterative(matA, matB, n);
                long endT = System.nanoTime();
                iterativeTime = iterativeTime + (endT - startT);
    
                startT = System.nanoTime();

                strassen(matA, matB, b, n);
                endT = System.nanoTime();
                strassenTime = strassenTime + (endT - startT);
                j--;
                }
                sb.append(n);
                sb.append(",");
                sb.append(iterativeTime/10);
                sb.append(",");
                sb.append(strassenTime/10);
                sb.append(",");
                sb.append(b);
                sb.append('\n');
                writer.write(sb.toString());
                writer.flush();
                sb.setLength(0);
            }
            
            
            
            }catch (IOException e) {System.out.println(e);}
 


        }
        if(args.length < 5){
            throw new IllegalArgumentException("Insufficient Arguments");
            
        }
        
        int optype = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int base = Integer.parseInt(args[2]);
        String inputf = args[3];
        String outputf = args[4];
        // TEMP INPUT GENERATION DISABLED - USE TEXTFILES
        // int x = 1;
        // long[][] a = new long[n][n];
        // long[][] b = new long[n][n];       // Defined two test nxn test matrices - asaad 
        
        // while(x <= 25)      
        //     for (int i = 0; i < n; i++)
        //         for (int j = 0; j < n; j++) //populate matrix a until (n^2)th element - asaad
        //             a[i][j] = x++;
        // while(x <= 50)          // same
        //     for (int i = 0; i < n; i++) 
        //         for (int j = 0; j < n; j++) 
        //             b[i][j] = x++;
    Scanner fr = new Scanner(new File(inputf)); 
    switch(optype){
    case 0: exportMatrix(iterative(readMatrix(fr, n), readMatrix(fr, n), n), outputf, n);  break;
    case 1: exportMatrix(strassen(readMatrix(fr, n), readMatrix(fr, n), base, n), outputf, n); break;
    default: break;
    }
    }catch(IllegalArgumentException e){System.out.println(e);}
    catch(FileNotFoundException e){System.out.println(e);}
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
    public static long[][] strassen(long[][] a, long[][] b, int base, int n){
        
        if(n <= base){
            return iterative(a, b, n);
        }
        
        a = matrixPadding(a, n);    // Padding applied if needed to a and b.
        b = matrixPadding(b, n);
        
        int m = a.length/2;        // Divide the array into two halves. 
        long[][] a11, a12, a21, a22;    
        long[][] b11, b12, b21, b22;
        long[][] s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
        long[][] m1, m2, m3, m4, m5, m6, m7;
        long[][] c11, c12, c21, c22;
        long[][] c;

        // initalizations
        s1 = new long[m][m]; s2 = new long[m][m]; s3 = new long[m][m];
        s4 = new long[m][m]; s5 = new long[m][m]; s6 = new long[m][m];
        s7 = new long[m][m]; s8 = new long[m][m]; s9 = new long[m][m];
        s10 = new long[m][m];
        m1 = new long[m][m]; m2 = new long[m][m]; m3 = new long[m][m];
        m4 = new long[m][m]; m5 = new long[m][m]; m6 = new long[m][m];
        m7 = new long[m][m];
        a11 = new long[m][m]; a12 = new long[m][m];
        a21 = new long[m][m]; a22 = new long[m][m];
        b11 = new long[m][m]; b12 = new long[m][m];
        b21 = new long[m][m]; b22 = new long[m][m];
        c11 = new long[m][m]; c12 = new long[m][m];
        c21 = new long[m][m]; c22 = new long[m][m];
        c = new long[a.length][a.length];

        // partition into 4 Regions
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
            a11[i][j] = a[i][j];
            a12[i][j] = a[i][(m)+j];
            a21[i][j] = a[(m)+i][j];
            a22[i][j] = a[(m)+i][(m)+j];
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
            b11[i][j] = b[i][j];
            b12[i][j] = b[i][(m)+j];
            b21[i][j] = b[(m)+i][j];
            b22[i][j] = b[(m)+i][(m)+j];
            }
        }

        // doing add/dub opertations to avoid redundency
        s1 = matrixSubtraction(b12, b22);
        s2 = matrixAddition(a11, a12);
        s3 = matrixAddition(a21, a22);
        s4 = matrixSubtraction(b21, b11);
        s5 = matrixAddition(a11, a22);
        s6 = matrixAddition(b11, b22);
        s7 = matrixSubtraction(a12, a22);
        s8 = matrixAddition(b21, b22);
        s9 = matrixSubtraction(a11, a21);
        s10 = matrixAddition(b11, b12);
        
        // DIVIDE STEP
        m1 = strassen(a11, s1, base, m);
        m2 = strassen(s2, b22, base, m);
        m3 = strassen(s3, b11, base, m);
        m4 = strassen(a22, s4, base, m);
        m5 = strassen(s5, s6, base, m);
        m6 = strassen(s7, s8, base, m);
        m7 = strassen(s9, s10, base, m);

        c11 = matrixAddition(matrixSubtraction(matrixAddition(m5, m4), m2), m6);
        c12 = matrixAddition(m1, m2);
        c21 = matrixAddition(m3, m4);
        c22 = matrixSubtraction(matrixSubtraction(matrixAddition(m5, m1), m3), m7);

        // combine the solution
        for(int i=0;i<c11.length;i++){
            for(int j=0;j<c11.length;j++){
                c[i][j] = c11[i][j];
                c[i][(m)+j] = c12[i][j];
                c[(m)+i][j] = c21[i][j];
                c[(m)+i][(m)+j] = c22[i][j];

            }
        }
        // return the result in the original size
        return trim(c, n);
    }

    public static long[][] matrixAddition(long[][] a, long[][] b){
        // Straightforward matrix addition
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
    public static long[][] matrixSubtraction(long[][] a, long[][] b){
        // Straightforward matrix subtraction
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }
    public static long[][] trim(long[][] c, int n){
        long[][] tc = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tc[i][j] = c[i][j];
            }
        }
        return tc;
    }
    public static long[][] readMatrix(Scanner fr, int n){
        // Assuming that the input file follows the format where:
        // The first n digits separated by a single whitespace belong to matrix A
        // The last n digits separated by a single whitespace belong to matrix B
        // NOTE: A scanner was supplied as an input parameter due to the fact we
        // need to continute reading from where we stopped after returning the matrix A.
        long[][] c = new long[n][n];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                if(fr.hasNextLong()){
                c[i][j] = fr.nextLong();
                }
            }

        }
        return c;
    }
    public static void exportMatrix(long[][] c, String filename, int n) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(filename);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                writer.print(c[i][j]);
                if(j!=n-1) writer.print(" ");
            }
            if(i!=n-1) writer.println();
            writer.flush(); // By using flush, the matrix will be written line by line.
        }
    }
    public static long[][] generateMatrix(int n) {
        
		long[][] matrix = new long[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = (java.util.concurrent.ThreadLocalRandom.current().nextLong(101)*(Math.round(Math.random()))*(-1));
			}
		}
		return matrix;
}
}