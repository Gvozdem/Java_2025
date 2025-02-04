import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        //������� 1
        Scanner in = new Scanner(System.in);
        System.out.print("������� ������: ");
        String input = in.nextLine();
        System.out.println("���������� ��������� ��� ������������� ��������: " + task_1(input));
        
        //������� 2
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] merged = task_2(arr1, arr2);
        System.out.print("\n������������ ������: ");
        for (int num : merged) {
            System.out.print(num + " ");
        }
        System.out.println();

        //������� 3
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("\n������������ ����� ����������: " + task_3(arr));
        System.out.println();
        
        //������� 4
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] rotated = task_4(matrix);
        for (int[] row : rotated) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    
        //������� 5
        int[] arr3 = {1, 2, 3, 4, 5};
        int target = 7;
        int[] pair = task_5(arr3, target);
        if (pair != null) {
            System.out.println("\n���� ��������� � ������ " + target + ": " + pair[0] + " � " + pair[1]);
        } else {
            System.out.println("����� ���� �� ����������");
        }
    
        //������� 6
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int totalSum = task_6(matrix2);
        System.out.println("\n����� ���� ���������: " + totalSum);

        //������� 7
        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] maxInRows = task_7(matrix3);
        System.out.print("\n������������ �������� � ������ ������: ");
        for (int num : maxInRows) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println();
        
        //������� 8
        int[][] matrix4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] rotated2 = task_8(matrix4);

        for (int[] row : rotated2) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static String task_1(String input) {
        int n = input.length();
        int maxLength = 0;
        int start = 0;
        int end = 0;
        String result = "";

        boolean[] seen = new boolean[128];

        while (end < n) {
            if (!seen[input.charAt(end)]) {
                seen[input.charAt(end)] = true;
                if (end - start + 1 > maxLength) {
                    maxLength = end - start + 1;
                    result = input.substring(start, end + 1);
                }
                end++;
            } else {
                seen[input.charAt(start)] = false;
                start++;
            }
        }

        return result;
    }

    public static int[] task_2(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] result = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        // ���� ���� �������� � ����� ��������
        while (i < n1 && j < n2) {
            if (arr1[i] < arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < n1) {
            result[k++] = arr1[i++];
        }

        while (j < n2) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static int task_3(int[] arr) {
        int n = arr.length;
        int maxSum = arr[0];
        int currentSum = arr[0];

        for (int i = 1; i < n; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static int[][] task_4(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = matrix[i][j];
            }
        }

        return result;
    }

    public static int[] task_5(int[] arr, int target) {
        Set<Integer> complement = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            int complement_value = target - arr[i];
            if (complement.contains(complement_value)) {
                return new int[]{arr[i], complement_value};
            }
            complement.add(arr[i]);
        }

        return null;
    }

    public static int task_6(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sum += num;
            }
        }
        return sum;
    }

    public static int[] task_7(int[][] matrix) {
        int rows = matrix.length;
        int[] maxElements = new int[rows];

        for (int i = 0; i < rows; i++) {
            int maxElement = Integer.MIN_VALUE;
            for (int j = 0; j < matrix[i].length; j++) {
                maxElement = Math.max(maxElement, matrix[i][j]);
            }
            maxElements[i] = maxElement;
        }

        return maxElements;
    }

    public static int[][] task_8(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[n - 1 - j][i];
            }
        }

        return result;
    }
}

