package javabasics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMatrixAddAndSub {
Matrixaddandsub mat;
    @BeforeEach
    public void setup(){
        Matrixaddandsub mat = new Matrixaddandsub();
    }
    @Test
    public void testMatrixAdd()
    {
        int[][] matrix1={{1,3,4},{2,4,3},{3,4,5}};
        int[][] matrix2={{1,3,4},{2,4,3},{1,2,4}};
        int[][] res=new int[3][3];
        res[0][0]=2;
        res[0][1]=6;
        res[0][2]=8;
        res[1][0]=4;
        res[1][1]=8;
        res[1][2]=6;
        res[2][0]=4;
        res[2][1]=6;
        res[2][2]=9;
        int[][] output = mat.addition(matrix1,matrix2,3,3);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                assertEquals(res[i][j],output[i][j]);
            }
        }
    }
    @Test
    public void testMatrixSub()
    {
        int[][] matrix1={{1,3,4},{2,4,3},{3,4,5}};
        int[][] matrix2={{1,3,4},{2,4,3},{1,2,4}};
        int[][] res={{0,0,0},{0,0,0},{2,2,1}};
        int[][] output=mat.subtraction(matrix1,matrix2,3,3);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                assertEquals(res[i][j],output[i][j]);
            }
        }
    }


}
