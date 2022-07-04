package gc.prof;

import java.util.Random;

public class Go {
    public static void main(String[] args) {
        Data data = new RandomArray(new Random());
        data.insert(250000);

        new MergeSort().sort(data);
        new InsertSort().sort(data);
        new BubbleSort().sort(data);
    }
}
