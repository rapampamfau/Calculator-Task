package com.calculator;

import com.calculator.utils.FileProcessor;

import java.io.File;
import java.util.Scanner;

public class CalculatorFunctions {

    private Scanner scan = new Scanner(System.in);
    private File file;
    private FileProcessor fileProcessor;

    public void run() {
        showInstructions();
        enterPath();
        fileProcessor = new FileProcessor(file);
        if (fileProcessor.isVerified()) {
            System.out.println("Result: " + fileProcessor.calculate());
        } else {
            System.out.println("Try to fix issues in file and try again.");
        }
    }
    private void showInstructions() {
        System.out.println("        <<<<<<<<< CALCULATOR THAT IGNORES MATHEMATICAL PRECEDENCE >>>>>>>>> \n" +
                "To run a calculator please copy full path to a file with commands that ends with .txt\n" +
                "the body of file should contain a binary operator and number separated by space\n" +
                "last instruction should be \"apply\" and a number. Example: \n" +
                "   add 3\n" +
                "   multiply 5\n" +
                "   apply 2\n"
        );
    }

    private void enterPath() {
        System.out.println("\n" + "Enter path to .txt file:");
        file = new File(scan.nextLine());
    }
}
