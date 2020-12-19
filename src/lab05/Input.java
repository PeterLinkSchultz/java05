package lab05;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {
    Deque<Integer> stack = new ArrayDeque<>();

    private void inputNumber() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("enter a number ");
                String line = scanner.nextLine();

                if (line.equals("q")) break;

                try {
                    String[] splitted = line.split("");

                    for (String symbol : splitted) {
                        Integer digit = Integer.parseInt(symbol);

                        stack.push(digit);
                    }

                    printStack();
                } catch (NumberFormatException e) {
                    System.out.println("Entered string is not a number!");
                    stack.clear();
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input is empty! Exit!");
        }
    }

    public void printStack() {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.print("\n");
    }

    public void run() {
        inputNumber();
    }

}