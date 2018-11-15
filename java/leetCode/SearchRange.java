package leetCode;

/**
 * Created by liyong on 16/7/10.
 */
public class SearchRange {
  public int[] searchRange(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return null;
    }

    int[] arr= new int[2];
    arr[0]=-1;
    arr[1]=-1;

    binarySearch(nums, 0, nums.length-1, target, arr);

    return arr;
  }

  public void binarySearch(int[] nums, int left, int right, int target, int[] arr){
    if(right<left)
      return;

    if(nums[left]==nums[right] && nums[left]==target){
      arr[0]=left;
      arr[1]=right;
      return;
    }

    int mid = left+(right-left)/2;


    if(nums[mid]<target){
      binarySearch(nums, mid+1, right, target, arr);
    }else if(nums[mid]>target){
      binarySearch(nums, left, mid-1, target, arr);
    }else{
      arr[0]=mid;
      arr[1]=mid;

      //handle duplicates - left
      int t1 = mid;
      while(t1 >left && nums[t1]==nums[t1-1]){
        t1--;
        arr[0]=t1;
      }

      //handle duplicates - right
      int t2 = mid;
      while(t2 < right&& nums[t2]==nums[t2+1]){
        t2++;
        arr[1]=t2;
      }
      return;
    }
  }

//  public int[] searchRange(int[] nums, int target) {
//    int[] result = new int[2];
//    result[0] = -1;
//    result[1] = -1;
//    int i = 0;
//    for (; i < nums.length ; i++) {
//      if (target == nums[i]) {
//        if (-1 == result[0]) {
//          result[0] = i;
//        }
//      } else {
//        if (-1 != result[0]) {
//          result[1] = i-1;
//          return result;
//        }
//      }
//    }
//    if (result[1] < result[0]) {
//      result[1] = i-1;
//    }
//    return result;
//  }
}
