package lab01;
import java.util.Random;

public class ArrayRandomizer {
    public Integer[] baseArray = new Integer[50];
    public Integer[] finishArray = new Integer[50];

    ArrayRandomizer() {
        initArray();
    }

    public void exec() {
        System.out.println("Array before mixed");
        printArray(baseArray);
        mixeArray();
        System.out.println("Array after mixed");
        printArray(finishArray);
    }

    private void initArray() {
        for (int i = 0; i < baseArray.length; i++) {
            baseArray[i] = i;
        }
    }

    void mixeArray() {
        Random random = new Random();
        for (int i = 0; i < baseArray.length; i++) {
            while (true) {
                int newPosition = random.nextInt(baseArray.length);

                if (checkPosition(newPosition, i)) {
                    finishArray[newPosition] = baseArray[i];
                    break;
                }
            }
        }
    }

    private void printArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + " " + array[i]);
        }
    }

    private boolean checkPosition(int newPosition, int oldPosition){
        return newPosition != oldPosition && finishArray[newPosition] == null;
    }
}
