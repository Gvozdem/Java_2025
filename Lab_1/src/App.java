import java.util.Scanner;

public class App {
    public static void main(String[] args) {   
        task_1();
        task_2();
        task_3();
        task_4();
        task_5();
    }

    static void task_1()
    {
        System.out.print("Задание 1\n");
        Scanner in = new Scanner(System.in);
        
        System.out.print("Введите n: ");
        int n = in.nextInt();
        
        int count = 0;

        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            count++;
        }

        System.out.println("Количество шагов до 1: " + count + "\n");
    }

    static void task_2()
    {
        System.out.print("Задание 2\n");
        Scanner in = new Scanner(System.in);
        
        System.out.print("Введите n: ");
        int n = in.nextInt();

        int sum = 0;

        for (int i = 0; i < n; i++)
        {
            System.out.print("Введите число: ");
            int number = in.nextInt();
            
            if (i % 2 == 0) {
                sum += number;
            } else {
                sum -= number;
            }
        }
        System.out.println("Сумма: " + sum);
    }

    static void task_3()
    {
        System.out.print("Задание 3\n");
        Scanner in = new Scanner(System.in);

        System.out.print("Введите координаты клада: ");
        int xEnd = in.nextInt();
        int yEnd = in.nextInt();

        int x = 0;
        int y = 0;
        boolean isFinish = false;

        int steps = 0;

        while (true) {
            System.out.print("Введите указание (sever, ug, zapad, vostok): ");
            String direction = in.next();
            if (direction.equals("stop")) {
                break;
            }
            int distance = in.nextInt();

            // Двигаем игрока в соответствии с указанием карты
            switch (direction) {
                case "sever":
                    y += distance;
                    break;
                case "ug":
                    y -= distance;
                    break;
                case "zapad":
                    x -= distance;
                    break;
                case "vostok":
                    x += distance;
                    break;
            }

            if (!isFinish)
            {
                steps++;
            }
            if (x == xEnd && y == yEnd) {
                isFinish = true;
            }
        }
        System.out.println("Необходимо сделать шагов: " + steps);
    }
    
    static void task_4()
    {
        System.out.print("Задание 4\n");
        Scanner in = new Scanner(System.in);
        
        System.out.print("Введите количество дорог: ");
        int n = in.nextInt();

        int[] minHeights = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Введите количество туннелей: ");
            int numTunnels = in.nextInt();

            int minHeight = Integer.MAX_VALUE;
            System.out.print("Введите их высоты: ");
            for (int j = 0; j < numTunnels; j++) {
                int tunnelHeight = in.nextInt();
                minHeight = Math.min(minHeight, tunnelHeight);
            }

            minHeights[i] = minHeight;
        }

        int maxHeight = 0;
        int maxRoadIndex = 0;
        for (int i = 0; i < n; i++) {
            if (minHeights[i] > maxHeight) {
                maxHeight = minHeights[i];
                maxRoadIndex = i + 1;
            }
        }

        System.out.println("Ответ: " + maxRoadIndex + " " + maxHeight);
    }
    
    static void task_5()
    {
        System.out.print("Задание 5\n");
        Scanner in = new Scanner(System.in);
        
        System.out.print("Введите трехзначное число: ");
        int n = in.nextInt();
        

        int a = n / 100;
        int b = (n / 10) % 10;
        int c = n % 10;
        int sum = a + b + c;
        int product = a * b * c;
        if((sum % 2 == 0) && (product % 2 == 0))
        {
            System.out.print("Число является дважды чётным");
        }
        else
        {
            System.out.print("Число не является дважды чётным");
        }
    }
}