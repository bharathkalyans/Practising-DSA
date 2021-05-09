import java.util.HashMap;
import java.util.Map;

public class Practise {

    public static void main(String[] args) {

    	int[] array = new int[]{999,0,12,4,-1,-1212};
    	mergeSort(array,0,array.length-1);
    	printArray(array);

    }

	public static void mergeSort(int[]a,int l,int r){

		if(l<r){
			int m = l + (r-l)/2;
			mergeSort(a,l,m);
			mergeSort(a,m+1,r);
			mergeArray(a,l,m,r);
		}


	}

	public static void mergeArray(int[]a,int l,int m,int r){

		int n1= m-l+1;
		int n2= r-m;
		int []L = new int[n1];
		int []R = new int[n2];

		int k = l;


		for(int i=0;i<n1;i++){
			L[i] = a[i+l];
		}

		for (int j=0; j<n2; j++) {
			R[j] = a[j+m+1];
		}

		int i=0,j=0;
		while(i<n1 && j<n2){
			if(L[i]<=R[j]){
				a[k] = L[i];
				i++;
			}else{
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while(i<n1){
			a[k++] = L[i++];
		}

		while(j<n2){
			a[k++] = R[j++];
		}

	}

	public boolean validPalindrome(String s) {
		int left = 0;
		int right = s.length()-1;

		while(left < right) {
			if(s.charAt(left) != s.charAt(right)) {
				return checkPalindrome(s, left, right-1) || checkPalindrome(s, left+1, right);
			}
			left++;
			right--;
		}
		return true;
	}

	private boolean checkPalindrome(String s, int left, int right) {
		while(left < right) {
			if(s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

    public static void QuickSort(int[] arr,int l,int h){

    	if(l<h){
			int p = partition(arr,l,h);
			QuickSort(arr,l,p-1);
			QuickSort(arr,p+1,h);
    	}
	}

    public static int partition(int[] arr, int l, int h){
    	int swapIndex = l-1;

    	int key = arr[h-1];


    	for (int i=l;i<h-1;i++){
    		if (arr[i]<key){
    			swapIndex++;
    			swap(arr,swapIndex,i);
			}
		}
    	swap(arr,h-1,++swapIndex);

    	return swapIndex;

	}

	public int findDuplicate(int[] nums) {
		HashMap<Integer,Integer> map = new HashMap<>();
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
			} else {
				map.put(num, map.get(num) + 1);
			}
		}
		//Second For Loop.
		int extraValue = nums[0];
		for (Map.Entry<Integer,Integer> m: map.entrySet()
			 ) {
			int x = m.getValue();
			if (x>=2) {
				break;
			}
		}

		return nums[0];
	}

    public static void InsertionSort(int []arr){
    	for(int i=1;i<arr.length;i++){
    		int key = arr[i];
    		int j = i-1;


    		while(j>=0 && arr[j]>key){
    			arr[j+1] = arr[j];
    			j--;
			}
    		arr[j+1] = key;
		}

		for (int x :
				arr) {
			System.out.println(x);
		}
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


		return new int[]{first,last};

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
		for (int x:a) {
			System.out.print(x+" ");
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
