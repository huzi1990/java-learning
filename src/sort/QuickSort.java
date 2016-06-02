/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package sort;

/**
 * 快速排序
 * @author:zixiao.hzx
 * @version $Id:QuickSort.java,v0.1 2016-06-02 下午1:37 zixiao.hzx Exp $$
 */
public class QuickSort {

    public static void main(String[] args){

        int[] compareSrc = { 9,2,7, 1, 5, 4, 2, 3, 6, 8 ,3};

        int[] result1=doQuickSort(compareSrc,0,compareSrc.length-1);

        for(int i=0;i<result1.length;i++){
            System.out.print(result1[i]+" ");
        }

    }

    public static int[] doQuickSort(int[] src,int leftIndex,int rightIndex){



        if(leftIndex<rightIndex){
            rightIndex=doSubQuickSort(src,leftIndex,rightIndex);
            doQuickSort(src, leftIndex, rightIndex-1);
            doQuickSort(src, rightIndex + 1, src.length - 1);
        }

        return src;

    }

    private static int doSubQuickSort(int[]src,int leftIndex,int rightIndex){

        int compareInt = src[leftIndex];

        while(leftIndex<rightIndex){
            while(leftIndex<rightIndex&&src[rightIndex]>=compareInt)
                rightIndex--;
            src[leftIndex] = src[rightIndex];
            while (leftIndex<rightIndex&&src[leftIndex]<compareInt)
                leftIndex++;
            src[rightIndex] = src[leftIndex];
        }
        //交换比较值
        src[rightIndex] = compareInt;
        return rightIndex;

    }


}
