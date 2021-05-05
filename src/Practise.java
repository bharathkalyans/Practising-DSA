import java.util.Arrays;
import java.util.Comparator;

public class Practise {

    public static void main(String[] args) {

		System.out.println("------------------------------");
		int x = minJumps(new int[]{2,3,0,1,1},5,0);

		System.out.println("Min Jumps is :: " + x);
    }



	public static int minJumps(int[] nums,int n,int currPos){
    	if (currPos>=n-1)
    		return 0;


    	int minJump = Integer.MAX_VALUE;

    	int maxSteps = nums[currPos];

    	while(maxSteps>0){
			minJump = Math.min(minJump,minJumps(nums,n,currPos + maxSteps) + 1);
			maxSteps -= 1;
		}


    	return minJump;

	}





	public int[] searchRange(int[] nums, int target) {
//		int first = -1,last = -1;
//		boolean first_flag = false,last_flag = false;
//		int low =0,high = nums.length - 1;
//
//
//		while(low<high){
//			if(first_flag && last_flag)
//				break;
//
//			if(nums[low] == target && !first_flag){
//				first = low;
//				first_flag = true;
//			}
//			if(nums[high] == target && !last_flag){
//				last = high;
//				last_flag = true;
//			}
//			low++;
//			high--;
//		}
//
//
//		int [] a = new int[]{first,last};
//
//		return a;
		//Worst Solution Ever for a Simple Problem.

		int first =-1,last = -1;

		for(int i =0;i< nums.length;i++)
			if (nums[i] == target){
				first = i;
			break;
			}

		for (int i = nums.length-1;i>=0;i--)
			if (nums[i] == target){
				last = i;
			break;
			}


		int [] a = new int[]{first,last};

		return a;

	}

	public int uniquePathsWithObstaclesDP(int[][] grid) {

		int R = grid.length;
		int C = grid[0].length;

		if (grid[0][0] == 1) {
			return 0;
		}

		grid[0][0] = 1;


		for (int i = 1; i < R; i++) {
			grid[i][0] = (grid[i][0] == 0 && grid[i - 1][0] == 1) ? 1 : 0;
		}


		for (int i = 1; i < C; i++) {
			grid[0][i] = (grid[0][i] == 0 && grid[0][i - 1] == 1) ? 1 : 0;
		}


		for (int i = 1; i < R; i++) {
			for (int j = 1; j < C; j++) {
				if (grid[i][j] == 0) {
					grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
				} else {
					grid[i][j] = 0;
				}
			}
		}


		return grid[R - 1][C - 1];
	}
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

    	int m = obstacleGrid.length;
    	int n = obstacleGrid[m-1].length;

    	return Uniquepaths(obstacleGrid, m -1 , n - 1,0,0);
	}
	public static int Uniquepaths(int[][] grid,int m,int n,int i,int j){
    	if(grid[0][0] == 1)
    		return 0;
    	if (i == m && j == n && grid[i][j]!=1){
    		if(grid[i][j] == 0)
    			return 1;
    		else return 0;
    	}
    	else {
    		if (i > m )
    			return 0;
    		if (j > n)
    			return 0;
    		if (grid[i][j] ==1)
    		  	return 0;

    		return Uniquepaths(grid,m,n,i+1,j) + Uniquepaths(grid,m,n,i,j+1);
		}
	}
    public static  int uniquePathsDP(int m,int n){

    	int[][] dp =new int[m][n];

    	for(int  i=0;i<m;i++)
    		dp[i][0] = 1;

    	for (int i=0;i<n;i++)
    		dp[0][i] = 1;

    	for(int i =1;i<m; i++){
    		for (int j = 1; j<n; j++){
    			dp[i][j] = dp[i-1][j]+dp[i][j-1];
    		}
		}

    	return dp[m-1][n-1];
	}
	public static int uniquePaths(int m, int n) {
		return up(1,1,m,n);
	}

	public static int up(int i,int j,int m,int n){
    	if(i > m )
    		return 0;
    	if (j>n)
    		return 0;
    	if( i == m && j == n )
    		return 1;
    	else {
    		return ( up(i+1,j,m,n) ) + up(i,j+1,m,n);
		}
	}


	public static boolean isPowerOfThree(int n) {
		int i = 1;

		while(i < n){
			i = i * 3;
		}

		return i == n;
	}

    public static void transposeOfMatrix(int[][] matrix){
		int n = matrix.length;
		for(int i =0;i<n;i++){
			for(int j = i+1;j<n;j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}
    public static void printMatrix(int[][]a){
    	for (int[] x: a){
    		for (int y : x){
				System.out.print(y+" ");
    		}
			System.out.println();
		}
	}
	public  static void reverse(int[][] matrix){
		int n = matrix.length;
		for(int i=0;i<n;i++){
			for(int j=0;j<n/2;j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][n-j-1];
				matrix[i][n-j-1] = temp;
			}
		}
	}


	//Kadane's Algorithm.
    public static int maxSubArray(int[] nums,int n){
//    	int res = nums[0];
//
//    	for(int i = 1;i < n;  i++ ){
//			System.out.println("Curr Res is :: " + res);
//    		res = Math.max(nums[i] + res,res);
//		}
//
				//WRONG ANSWER!!!!!!!!! because I am changing the main variable which was supposed to
//		hold my max sum.
//    	return  res;
		int max=nums[0];

		int curr_sum=max;

		for(int i=1;i<nums.length;i++){

			curr_sum=Math.max(nums[i],nums[i]+curr_sum);
			max=Math.max(max,curr_sum);
		}
		return max;
	}
	public int maxSubArray(int[] nums) {
		int max_sum = Integer.MIN_VALUE;
		int n = nums.length;

		for(int i=0;i<n;i++){
			int curr_sum = nums[i];
			for(int j = i+1 ;j<n;j++){
				curr_sum += nums[j];
				max_sum = Math.max(max_sum,curr_sum);
			}
		}
		return max_sum;
	}

    public static void printArray(int[] a){
		for (int x:a
			 ) {
			System.out.println(x);
		}
	}
   	public static int trapRainWater(int[] heights){

	   	int water = 0;

	   	int n = heights.length;
	   	int[] leftSide = new int[n];
	   	int[] rightSide = new int[n];

	   	leftSide[0] = heights[0];
		for(int i = 1 ; i < n ;i++){
			leftSide[i] = Math.max(heights[i],leftSide[i-1]);
		}
		printArray(leftSide);

		rightSide[n-1] = heights[n-1];
		for (int j = n-2;j >= 0 ;j-- ) {
			rightSide[j] = Math.max(rightSide[j+1],heights[j]);
		}

	   	System.out.println("-----------");
		printArray(rightSide);


		for(int i = 1 ;i < n-1 ;i++){
			water += Math.min(rightSide[i],leftSide[i]) - heights[i];
		}

		return water;
   }


    public static void leadersOfArray(int[] arr,int size){
		for (int i = 0; i < size; i++) {
			int j;
			for (j = i + 1; j < size; j++) {
				if (arr[i] < arr[j])
					break;
			}
			if (j == size)
				System.out.print(arr[i] + " ");
		}

    }

    public static void leftRotate(int[] arr,int d,int n){
    		reverseArray(arr,0,d-1);
    		reverseArray(arr,d,n-1);
    		reverseArray(arr,0,n-1);
    		for (int i=0;i<n ;i++ ) {
    			System.out.println(arr[i]);
    		}
    }

	public static void reverseArray(int[] arr,int low,int high){
		while(low<high){
			swap(arr,low,high);
			low++;
			high--;
		}
	}

    public static int countInSubSets(int[] arr,int sum,int n){
        if(n == 0)
            return (sum == 0)?1:0;

        return countInSubSets(arr,sum,n-1) + countInSubSets(arr,sum - arr[n-1],n-1);
    }

    public static void reverseArrayFull(int[] arr){
		int low = 0,high = arr.length;

		while(low<high){
			swap(arr,low,high);
			low++;
			high--;
		}
	}
	public static void swap(int [] arr,int i,int j){
		int temp = arr[i];
		arr[i]  =  arr[j];
		arr[j]  =  temp;
	}
	public static void removeDuplicates(int[] arr){
		int n= arr.length;
		int res = 1;
		for (int i=1;i<n ;i++ ) {
			if (arr[i-1]!= arr[i]) {
				arr[res] = arr[i];
				res++;
			}
		}

		for (int i=0 ;i<res ;i++ ) {
			System.out.println(arr[i]);
		}
	}
}

class MyComp implements Comparator<Integer> {

	public int compare(Integer a,Integer b){
		return a%2 - b%2;
	}
}