/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package sort;

/**
 * 合并排序
 * @author:zixiao.hzx
 * @version $Id:MergeSort.java,v0.1 2016-06-02 下午5:52 zixiao.hzx Exp $$
 */
public class MergeSort {


    public static void main(String[] args){

        int[] src = { 9, 2, 7, 1, 5, 4, 2, 3, 6, 8, 3 };
//        int[] src = { 1, 4, 7, 5, 8, 9 };

        doMergeSort(src, 0, src.length - 1);

        for(int i=0;i<src.length;i++){
            System.out.print(src[i]+" ");
        }

    }

    public static int[] doMergeSort(int[] src,int begin,int end){

        int[] temp = new int[src.length];
        if(begin<end){

           int  middle = (begin + end) / 2;

            doMergeSort(src, begin, middle);
            doMergeSort(src, middle + 1, end);
            mergeOperation(src,begin,middle,end,temp);
        }


        return src;
    }

    public static void mergeOperation(int[] src,int begin,int middle,int end,int[] temp){

        int i = begin, j = middle+1, k = 0;



        while (i < middle+1 && j < end+1) {
            if (src[i] < src[j]) {
                temp[k++] = src[i++];
            } else {
                temp[k++] = src[j++];
            }
        }

        while (i<middle+1){
            temp[k++] = src[i++];
        }
        while (j<end+1){
            temp[k++] = src[j++];
        }

        System.arraycopy(temp,0,src,begin,end-begin+1);

    }
 }
