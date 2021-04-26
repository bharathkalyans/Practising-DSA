public class Practise {

    public static void main(String[] args) {
		int[][] a = new int[][]{{1,2},{3,4}};
		reverse(a);
		printMatrix(a);
    }


    public static void transposeOfMatrix(int[][] matrix){

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
