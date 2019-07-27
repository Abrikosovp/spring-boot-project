package ru.sweater.controller;

public class TestController {


    public static void main(String[] args) {

        int[] arr = {15567, 31, 30, 2, 28, -1, 26, 111, 1};
        for (int a : bubbleSort(arr)) {
            System.out.println(a);
        }


    }

    private static int[] bubbleSort(int[] arr) {
        int size = arr.length;
        int tmp;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (arr[i] > arr[j]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }
}
