package wht.wyw.LeetCode;

/**
 * 接雨水问题
 */
public class CatchRain_leetcode_42 {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height1 = {4, 2, 0, 3, 2, 5};
        System.out.println(violenceMathod(height));
        System.out.println(violenceMathod(height1));
        System.out.println(DPMathod(height));
        System.out.println(DPMathod(height1));
        System.out.println(doublePC(height));
        System.out.println(doublePC(height1));
    }

    //暴力算法
    public static int violenceMathod(int[] height) {
        int leftMax;
        int rightMax;
        int sum = 0;
        for (int i = 1; i < height.length; i++) {
            leftMax = getMax(height, 0, i);
            rightMax = getMax(height, i, height.length - 1);
            int secondMax = Math.min(leftMax, rightMax);
            if (secondMax > height[i])
                sum += (secondMax - height[i]);
        }
        return sum;
    }

    public static int getMax(int[] arr, int index1, int index2) {
        int max = arr[index1];
        for (int i = index1 + 1; i <= index2; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    // 动态规划
    public static int DPMathod(int[] height) {
        int[] rightMax = new int[height.length]; // 表示下标i以及之前的元素的最大值
        int[] leftMax = new int[height.length]; // 表示下标i以及之后的元素的最大值;
        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];
        int sum = 0;
        for (int i = 1; i < height.length; i++)
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        for (int i = height.length - 2; i >= 0; i--)
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        for (int i = 1; i < height.length; i++) {
            int secondMax = Math.min(rightMax[i], leftMax[i]);
            if (secondMax > height[i])
                sum += (secondMax - height[i]);
        }
        return sum;
    }

    // 再对动态规划算法进行改进,即双指针代替rightMax和rightMax数组
    public static int doublePC(int[] height) {
        int rightMax = 0;
        int leftMax = 0;
        int right = height.length - 1;
        int left = 0;
        int sum = 0;
        while (left < right) {
            rightMax = Math.max(rightMax, height[right]);
            leftMax = Math.max(leftMax, height[left]);
            if (height[left] < height[right]){ // leftMax < rightMax 也是一样的
                sum += (leftMax - height[left]);
                left++;
            }else {
                sum += (rightMax - height[right]);
                right--;
            }
        }
        return sum;
    }

     // 单调栈
    public static int monotonousStack(int[] height){
        
    }
}
