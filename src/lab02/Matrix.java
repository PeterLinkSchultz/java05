package lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Matrix {
     ArrayList<ArrayList<Integer>> source = new ArrayList<>();
     private int strings;
     private final int columns;

     Matrix(int stringsNumber, int columnsNumber) {
          strings = stringsNumber;
          columns = columnsNumber;
     }

     public void exec() {
          initMatrix();
          System.out.println("Matrix before removing");
          printMatrix();
          removeMinMax();
          System.out.println("Matrix after removing");
          printMatrix();
     }

     private void initMatrix() {
          Random random = new Random();

          for (int i = 0; i < strings; i++) {
               ArrayList<Integer> string = new ArrayList<Integer>();

               for (int j = 0; j < columns; j++) {
                    string.add(random.nextInt(100));
               }

               source.add(string);
          }
     }

     void removeMinMax() {
          Integer min = null;
          int index = 0;

          for (int i = 0; i < strings; i++) {
               ArrayList<Integer> string = source.get(i);
               Integer max = Collections.max(string);

               if (min == null || max < min) {
                    min = max;
                    index = i;
               }
          }

          strings--;
          source.remove(index);
     }

     private void printMatrix() {
          for (int i = 0; i < strings; i++) {
               ArrayList<Integer> string = source.get(i);

               for (int j = 0; j < columns; j++) {
                  System.out.print(string.get(j) + " ");
               }
               System.out.print("\n");
          }
     }
}
