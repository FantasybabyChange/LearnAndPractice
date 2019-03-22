package com.fantasybaby.algorithm.induction;

import java.util.Arrays;

/**归并排序
 * @author reid.liu
 * @date 2019-03-21 20:44
 */
public class MergeSort {

    public int[] mergeSort(int[] origin){
        if(origin.length == 1){
            return  origin;
        }
        int mid = origin.length / 2;
        int[] first = Arrays.copyOfRange(origin, 0, mid);
        int[] sortFirst = mergeSort(first);
        int[] second = Arrays.copyOfRange(origin, mid, origin.length);
        int[] sortSecond = mergeSort(second);
        return merge(sortFirst,sortSecond);

    }
    public int[] merge(int[] first,int[]second){
        int[] newSort = new int[first.length+second.length];
        int firstIndex = 0;
        int secondIndex = 0;
        int newSortIndex = 0;
        while (true){
            if(firstIndex > first.length -1 || secondIndex > second.length -1){
                break;
            }
            if(first[firstIndex] <= second[secondIndex]){
                newSort[newSortIndex] = first[firstIndex];
                firstIndex++;
            }else{
                newSort[newSortIndex] = second[secondIndex];
                secondIndex++;
            }
            newSortIndex++;
        }
        if(firstIndex < first.length){
            while (newSortIndex <  newSort.length ){
                newSort[newSortIndex] = first[firstIndex];
                newSortIndex++;
                firstIndex++;
            }
        }else if(secondIndex < second.length){
            while (newSortIndex <  newSort.length ){
                newSort[newSortIndex] = second[secondIndex];
                newSortIndex++;
                secondIndex++;
            }
        }
        return  newSort;
    }

    public static void main(String[] args) {
        int [] origin = {1,4,6,8,7,9,3,5,2};
        int[] ints = new MergeSort().mergeSort(origin);
        System.out.println(Arrays.toString(ints));
    }

}
