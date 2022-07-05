package gc.prof;

import java.util.Random;
import java.util.Scanner;

public class Go {
    public static void main(String[] args) {
        Data data = new RandomArray(new Random());
        String out = "no";
        Scanner sc = new Scanner(System.in);
        while (!out.equals("out")) {
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    data.insert(250000);
                    System.out.println("Insert elements");
                    break;
                case 2:
                    new MergeSort().sort(data);
                    System.out.println("MergeSort");
                    break;
                case 3:
                    new InsertSort().sort(data);
                    System.out.println("InsertSort");
                    break;
                case 4:
                    new BubbleSort().sort(data);
                    System.out.println("BubbleSort");
                    break;
                case 5:
                    out = "out";
                    System.out.println("out");
                    break;
            }
        }
    }
}
