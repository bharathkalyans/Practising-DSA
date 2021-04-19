public class Practise {

    public static void main(String[] args) {
       int[] a = {10,15,25};

        System.out.println(countInSubSets(a,25,a.length));
    }

    public static int countInSubSets(int[] arr,int sum,int n){
        if(n == 0)
            return (sum == 0)?1:0;

        return countInSubSets(arr,sum,n-1) + countInSubSets(arr,sum - arr[n-1],n-1);
    }
}
