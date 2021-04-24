public class Practise {

    public static void main(String[] args) {
		int[][] arr = new int[][]{{2},{3,4},{6,5,7},{4,1,8,3}};
//		[[2],[3,4],[6,5,7],[4,1,8,3]]



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

		rightSide[n-1] = heights[n-1];
		for (int j = n-2;j >= 0 ;j-- ) {
			rightSide[j] = Math.max(rightSide[j-1],heights[j]);
		}

		for(int i = 1 ;i < n-2 ;i++){
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
