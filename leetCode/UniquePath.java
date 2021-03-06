/*
	A robot is located at the top-left corner of a m x n grid 
	(marked 'Start' in the diagram below).

	The robot can only move either down or right at any point in time. 
	The robot is trying to reach the bottom-right corner of the grid 
	(marked 'Finish' in the diagram below).

	How many possible unique paths are there?
	Note: m and n will be at most 100.
*/

public class UniquePath
{
	public static int uniquePaths(int m, int n) 
    {
        int[][] buffer = new int[m+1][n+1]; // the initial value is 0 since int[] is a class
        return uniquePathsWBuffer(m,n,buffer);
    }
    
    private static int uniquePathsWBuffer(int m, int n, int[][] buffer)
    {
        if (m==1 || n==1)
        {
            return 1; // if m or n is 0, it means they are already in the edge indicating there is only one way
        }
        if (buffer[m][n] != 0)
        {
            return buffer[m][n];
        }
        else
        {
            int temp = uniquePathsWBuffer(m-1,n,buffer) + uniquePathsWBuffer(m,n-1,buffer);
            buffer[m][n] = temp;
            return temp;
        }
    }
}