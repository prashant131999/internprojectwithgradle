package javabasics;

import java.util.Scanner;

public class Matrixaddandsub {
    /**
     * this method  will print addition of mat1 and mat2
     * @param mat1 1st matrix to add
     * @param mat2 2nd matrix to add
     * @param row  number of rows in the matrix
     * @param col  number of cols in the matrix
     *
     */
    public static int[][] addition(int mat1[][], int mat2[][], int row, int col) {
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i][j] = mat1[i][j] + mat2[i][j];

            }
        }
        return res;


    }

    /**
     * this method  will print addition of mat1 and mat2
     * @param mat1 1st matrix to subtract
     * @param mat2 2nd matrix to subtract
     * @param row  number of rows in matrix
     * @param col  number of cols in matrix
     */
    public static int[][] subtraction(int mat1[][], int mat2[][], int row, int col) {
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i][j] = mat1[i][j] - mat2[i][j];

            }
        }
        return res;

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows");
        int row = sc.nextInt();
        System.out.println("Enter the number of columns");
        int col = sc.nextInt();
        int[][] mat1 = new int[row][col];
        int[][] mat2 = new int[row][col];
        System.out.println("Enter the elements of 1st martix row wise \n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat1[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter the elements of 2nd martix row wise \n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat2[i][j] = sc.nextInt();
            }
        }
        int [][] res;
        res = new int[row][col];
        res=addition(mat1, mat2, row, col);
        System.out.println("Addition of above two matrix\n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(res[i][j]);
            }
            System.out.println();

        }
        int [][] sub;
        sub= new int[row][col];
        System.out.println("Subtraction of above two matrix\n");
        sub=subtraction(mat1, mat2, row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(sub[i][j]);
            }
            System.out.println();

        }

    }
}
