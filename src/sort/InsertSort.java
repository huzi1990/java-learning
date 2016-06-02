/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package sort;

/**
 * 插入排序
 * @author:zixiao.hzx
 * @version $Id:InsertSort.java,v0.1 2016-05-30 下午8:16 zixiao.hzx Exp $$
 */
public class InsertSort {

    public static void main(String[] args){

        int[] compareSrc = { 7, 1, 5, 4, 2, 3, 6, 8 };

        int[] result1=doInsertSort(compareSrc);

        for(int i=0;i<result1.length;i++){
            System.out.print(result1[i]+" ");
        }
    }


    public static int[] doInsertSort(int[] src){


        for(int i=0;i<src.length;i++){

            int temp = src[i];

            for(int j=i-1;j>=0&&temp<src[j];j--){
                src[j + 1] = src[j];
                src[j] = temp;
            }


        }

        return src;
    }
}
