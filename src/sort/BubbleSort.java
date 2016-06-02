/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package sort;

/**
 * 冒泡排序算法
 * @author:zixiao.hzx
 * @version $Id:BubbleSort.java,v0.1 2016-05-29 下午11:29 zixiao.hzx Exp $$
 */
public class BubbleSort {

    public static void main(String[] args){

        int[] compareSrc = { 7,1,5, 4, 2, 3, 6,8};

        int[] result1 = doBubbleSort(compareSrc);

        int[] result2 = doBubbleSort(compareSrc);

        for(int i=0;i<result1.length;i++){
            System.out.print(result1[i]+" ");
        }

        for(int i=0;i<result2.length;i++){
            System.out.print(result2[i]+" ");
        }
    }

    public static int[] doBubbleSort(int[] src){

        for(int i=0;i<src.length;i++){

            for(int j=0;j<src.length-i-1;j++){
                if(src[j]>src[j+1]){
                    swap(src, j);
                }
            }

        }

        return src;
    }

    public static int[] doOptimizeBubbleSort(int[] src){

        //判断需要排序的长度
        int compareLength = src.length;

        while (compareLength>0) {
            for (int j = 0; j < compareLength - 1; j++) {
                if (src[j] > src[j + 1]) {
                    swap(src, j);
                    compareLength = j;
                }
                //已经是排序好了
                compareLength = 0;
            }
        }

        return src;
    }

    private static void swap(int []src,int j){
        int temp = src[j];
        src[j] = src[j+1];
        src[j+1] = temp;
    }


}
