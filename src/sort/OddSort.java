/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 奇数排序
 * @author:zixiao.hzx
 * @version $Id:OddSort.java,v0.1 2016-06-02 下午8:22 zixiao.hzx Exp $$
 */
public class OddSort {

//    public static int result = 0;

    public static void main(String[] args) {

        //        Scanner scanner = new Scanner(System.in);
        //        while (scanner.hasNext()){
        //            scanner.nextLine().split("");
        //        }
        //

        Scanner in  = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            List<Integer> integerList = new LinkedList<Integer>();
            for(int i=0;i<n;i++){
                integerList.add(i);
            }
            //        doFilter(integerList);
            System.out.println(doFilter(integerList));
        }




    }


    public static int doFilter(List<Integer> integerList){

        int result=0;
        List<Integer> resultList = new ArrayList<Integer>();
        if (integerList.size()>1){
            for(int i=0;i<integerList.size();i++){
                if((i+1)%2==0){
                    resultList.add(integerList.get(i));
                }
            }
            result= doFilter(resultList);
        }else {
            result =integerList.get(0);
        }

        return result;
    }
}
