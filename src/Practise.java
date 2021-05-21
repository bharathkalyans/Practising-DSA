import java.math.BigInteger;
import java.util.*;


public class Practise {

    public static void main(String[] args) {

    	int[] a =new int[]{1,3,5,7,12,101010};
		LongestConsecutiveSubSequence(a);
		LongestConsecutiveSubSequence(a,a.length);

    }

    public static void LongestConsecutiveSubSequence(int[] arr,int n){
    	//Time Complexity O(n) and Space Complexity O(n)!
		if (n == 0 )
			System.out.println("No Elements Shit Head!");

		if (n == 1)
			System.out.println(arr[0]);

		int count = 1;
		int max = Integer.MIN_VALUE;

		HashSet<Integer> map = new HashSet<>();


		for (int x:arr)map.add(x);

		for (int i=0;i<n;i++){
			if (!map.contains(arr[0]-1)){
				int element = arr[i];
				while(map.contains(element+1)){
					count++;
				}

				if (max < element - arr[i])
					max = element - arr[i];
			}
		}

		System.out.println("The Longest Sub Sequence is :: " + max);

	}

    public static void LongestConsecutiveSubSequence(int[] arr){
    	//Time Complexity is O(NlogN + N)!
    	int n = arr.length;
    	if (n == 0 )
			System.out.println("No Elements Shit Head!");

    	if (n == 1)
			System.out.println(arr[0]);


    	Arrays.sort(arr);
    	int max = Integer.MIN_VALUE;
    	int count = 1;
    	for (int i=1;i<n;i++){
    		if (arr[i-1]+1 == arr[i]){
    			count++;
			}
    		else {
    			max = Math.max(max,count);
				count = 1;
			}
    		max = Math.max(max,count);
		}

		System.out.println("The Longest Sub Sequence is :: "+ max);

	}

	public static int LongestSubArrayWithGivenSum(int[] arr, int n, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0, maxLen = 0;

		for (int i = 0; i < n; i++) {
			sum += arr[i];
			if (sum == k)
				maxLen = i + 1;
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
			if (map.containsKey(sum - k)) {
				if (maxLen < (i - map.get(sum - k)))
					maxLen = i - map.get(sum - k);
			}
		}
		return maxLen;
	}

    public static void SubArrayWithGivenSum(int[] arr,int sum){
    	int n = arr.length;

    	HashMap<Integer,Integer> map = new HashMap<>();
		int start = 0;
		int end = -1;

    	int pre_sum = 0;
    	for (int i=0;i<n;i++){
    		pre_sum += arr[i];

    		if (pre_sum-sum == 0) {
    			start = 0;
    			end = i;
    			break;
			}

    		if (map.containsKey(pre_sum - sum)){
    			start = map.get(pre_sum-sum)+1;
    			end = i;
    			break;
			}

    		map.put(pre_sum,i);

		}
    	if (end !=-1){
			System.out.println("Found from " +start+" to "+ end);
		}else System.out.println("Not Found!!");

	}


    public static void convertToSquareMatrix(int[]matrix[]){
    	int m = matrix.length;
    	int n = matrix[0].length;

    	int p = Math.max(m, n);

    	int[][] result = new int[p][p];

    	for (int i = 0;i<p;i++)
    		for (int j=0;j<p;j++)
    			result[i][j] = 1;


    	for(int i=0;i<m;i++){
    		for (int j=0;j<n;j++){
    			result[i][j] = matrix[i][j];
			}
		}

    	printArray(result);


	}

    public static void subArrayWithSumZero(int[] arr,int n){

    	int[] prefixArray = new int[n];
    	int startIndex = 0,endIndex = 0;
    	boolean flag = false;
    	prefixArray[0] = arr[0];
    	for (int i=1;i<n;i++){
    		prefixArray[i] = arr[i]+prefixArray[i-1];
		}

    	HashMap<Integer,Integer> map = new HashMap<>();

    	for (int i=0;i<n;i++){
    		if (map.containsKey(prefixArray[i])){
    			startIndex = map.get(prefixArray[i]);
    			endIndex = i;
    			flag = true;
    			break;
    		}else {
				map.put(prefixArray[i],i);
			}
		}
    	if (flag) System.out.println("The SubArray Containing '0' is from index " + (startIndex+1) +" to "+ endIndex+" !");
		else System.out.println("No SubArray with Sum Zero!!");

	}

    public static void countPairsInAUnSortedArray(int[] arr,int n,int k){

    	HashSet<Integer> set = new HashSet<>();
    	int count = 0;

    	for (int x :arr){
			if (set.contains(k-x)){
				count++;
			}
			set.add(x);
    	}
		System.out.println("Count of Pairs :: " + count);
    }

    public static void MatrixMultiplication(int[][] A,int[][] B){

    	int m,n,p,q;
    	m = A.length;
    	n = A[0].length;
    	p = B.length;
    	q = B[0].length;

    	if (n!=p)
    		return;

    	int[][] C = new int[m][q];

    	for (int i=0;i<m;i++){
    		for (int j=0;j<q;j++){
				C[i][j] = 0;
				for (int k=0;k<n;k++){
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
    	printArray(C);

	}


    //O(R + C) Time Complexity!
    public static boolean searchInSortedMatrix(int[][] matrix, int element){

    	int n = matrix.length;

    	int i= 0,j = n-1;

    	while(i<n && j>=0){
    		if (element == matrix[i][j]) return true;
    		else  if(matrix[i][j] < element) i++;
    		else j--;
		}
    	return false;
	}

    public static void reverseMatrixTopToDown(int[][] matrix){
    	for (int i=0;i<matrix[0].length;i++){
    		int low=0,high = matrix.length-1;

    		while (low<high){
    			int temp = matrix[low][i];
    			matrix[low][i] = matrix[high][i] ;
    			matrix[high][i] = temp;
    			low++;
    			high--;
			}
		}
	}

    public static void boundaryOfMatrix(int[][] matrix){
    	for (int i=0;i<matrix[0].length;i++){
			System.out.print(matrix[0][i]+" ");
		}

		System.out.println();

    	for (int i = 0;i<matrix[1].length;i++){
			System.out.print(matrix[i][matrix.length-1]+" ");
		}

		System.out.println();

    	for (int i=matrix.length-1;i>=0;i--){
			System.out.print(matrix[matrix.length-1][i]+" ");
		}

		System.out.println();

    	for (int i= matrix.length-1;i>=0;i--){
			System.out.print(matrix[i][0]+" ");
		}

		System.out.println();

	}

    public static void snakePatternOfMatrix(int[][] matrix){
    	for (int i=0;i<matrix.length;i++){
    		if (i%2 == 0){
    			for (int j=0;j<matrix[i].length;j++){
					System.out.print(matrix[i][j]+" ");
				}
			}else {
    			for (int j = matrix[i].length-1;j>=0;j--){
					System.out.print(matrix[i][j]+" ");
				}
			}
			System.out.println();
		}
	}

    public static void JaggedArray(int n){
    	int[][] jaggedArray = new int[n][];

    	for (int i=0;i<n;i++){
    		jaggedArray[i] = new int[i+1];
    		for (int j=0;j<jaggedArray[i].length;j++)
    			jaggedArray[i][j] = 0;
		}

    	printArray(jaggedArray);
	}

    public static int[][] rotateMatrixBy90(int[][] matrix){
    	int n=matrix.length;
    	int[][] dup = new int[n][n];

    	for (int i=0; i<n; i++) {
    		for (int j=0; j<n; j++) {
    			dup[j][i] = matrix[i][j];
    		}
    	}

    	reverse(dup);

    	return dup;
    }

    public static void printArray(int[][] array){
    	for (int[] a: array){
    		for (int x:a)
				System.out.print(x+" ");
			System.out.println();
    	}
	}

    public static BigInteger factorialOfLargeNumber(Integer n){
		BigInteger factorial = new BigInteger("1");

		for (int i = 2;i<=n;i++)
			factorial = factorial.multiply(BigInteger.valueOf(i));

		return factorial;
	}

    public static Double Ceil(double number){
    	int num = (int)number;

    	if((double)num == number)
    		return (double)num;
    	else return (double)num+1;

	}

	public static Double Floor(double number){
    	int num = (int)number;

    	return (double)num;

	}

  	private static final List<Integer> l = new ArrayList<>();
    public  static void permute(String str,String cur){
    	if (str.length() == 0){
			System.out.println(cur+" ");
			l.add(Integer.parseInt(cur));
    	}

    	for (int i=0;i<str.length();i++){
    		String newCurrString = cur + str.charAt(i);
    		String newString = str.substring(0,i) + str.substring(i+1);
    		permute(newString,newCurrString);
		}
	}
    public static String nextPermutation(String str){
    	permute(str,"");

		int number = Integer.parseInt(str);
		int index = 0;
		for (int i=0;i<l.size();i++){
			int x  = l.get(i);
			if (x == number){
				index = i;
				break;
			}
		}

		if (index == l.size()-1)
			return "-1";
		else return l.get(index+1).toString();
	}

	public static void printDuplicates(String word){
		HashMap<Character,Integer> map = new HashMap<>();
		for(int i=0;i<word.length();i++){
			if (!map.containsKey(word.charAt(i))){
				map.put(word.charAt(i),1);
			}else {
				map.put(word.charAt(i),map.get(word.charAt(i))+1);
			}

		}
		for (Map.Entry<Character,Integer> m : map.entrySet()){
			int x = m.getValue();
			if (x>1)
				System.out.println(m.getKey());
		}
	}

    public static void MergeIntervals(Interval[] arr){
		Arrays.sort(arr, new Comparator<>() {
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}

		});

		int resIndex = 0;
		for (int i=1;i<arr.length;i++){
			if(arr[i].start<=arr[resIndex].end){
				arr[resIndex].end = Math.max(arr[resIndex].end,arr[i].end);
				arr[resIndex].start = Math.min(arr[resIndex].start,arr[resIndex].start);
			}else{
				resIndex++;
				arr[resIndex] = arr[i];
			}
		}

		System.out.println(arr[resIndex].start +" :: " + arr[resIndex].end);

		for (int i = 0; i <=resIndex; i++) {
			System.out.print("[" + arr[i].start + ","
					+ arr[i].end + "]");
		}
	}

	public static String reverseWords(String S) {
		String[] set = S.split(" ");

		Stack<String> stack = new Stack<>();

		String ans ="";
		for(String x : set){
			stack.push(x);
		}

		for (int i=stack.size()-1;i>=0;i--){
			ans += stack.pop() +" ";
		}

		return ans;

	}

    public static void SegregateArrayII(int[] arr){
    	int l=0,h=arr.length-1;

    	int mid= 0;
    	while(mid<h){
    		switch (arr[mid]){
				case 0:swap(arr,l,mid);
						l++;
						mid++;
						break;
				case 1: mid++;
						break;
				case 2:swap(arr,mid,h);
						h--;
						break;
			}
		}
	}

    public static void SegregateArray(int[] arr){
		int l = 0, h = arr.length - 1;
		while(l<h){
			if (arr[l] == 1){
				swap(arr,l,h);
				h--;
			}else
				l++;

		}
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

    	return UniquePaths(obstacleGrid, m -1 , n - 1,0,0);
	}
	public static int UniquePaths(int[][] grid, int m, int n, int i, int j){
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

    		return UniquePaths(grid,m,n,i+1,j) + UniquePaths(grid,m,n,i,j+1);
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


	// Kadane's Algorithm.
    public static int maxSubArray(int[] nums,int n){
/**
 * int dp[] = new int[n];
 * int max_sum = 0;
 * dp[0] = Math.MAX(nums[0],0);
 * for(int i=1;i< nums.length;i++){
 *     dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
 *     max_sum = Math.max(max_sum,dp[i]);
 * }
 * return max_sum;
* */
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

class Interval{
	int start,end;
	Interval(int start,int end){
		this.start = start;
		this.end = end;
	}
}