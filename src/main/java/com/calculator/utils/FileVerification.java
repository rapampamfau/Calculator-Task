package com.calculator.utils;

import java.io.*;
import java.util.Objects;

public class FileVerification {

    private BufferedReader reader;

    public boolean checkIfFileExist(File file) {
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkFileFormat(String fileName) {
        if (!fileName.endsWith(".txt")) {
            System.out.println("Incorrect file format. (Should end with .txt)");
            return false;
        } else {
            return true;
        }
    }

    public boolean verifyAllInstructions(File file) {
        try {
            reader = new BufferedReader(new FileReader(file));
            String currentLine;
            String previousLine = "";
            while ((currentLine = reader.readLine()) != null) {
                if (!previousLine.equals("")) {
                    String[] previousLineContent = previousLine.split(" ");
                    if (previousLineContent.length != 2) {
                        System.out.println("Incorrect number of instructions.");
                    }
                    if (!checkFirstColumnSyntax(previousLineContent[0]) ||
                            !checkSecondColumnSyntax(previousLineContent[1])) {
                        System.out.println("Incorrect instruction syntax.");
                    }
                    if (!checkForDivideByZeros(currentLine)) {
                        System.out.println("Incorrect instruction, you can't divide by 0.");
                    }
                }
                previousLine = currentLine;
            }
            if (verifyLastLine(file)) {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public String getLastLine(File file) {

        String fileLastLine = "";
        String currentLine;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                fileLastLine = currentLine;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return fileLastLine;
    }

    private boolean verifyLastLine(File file) {
        String lastLine = getLastLine(file);
        String[] lineContent = lastLine.split(" ");
        try {
            if (lineContent.length != 2) {
                System.out.println("Incorrect number of instructions.");
                return false;
            }
            Long.parseLong(lineContent[1]);
            return Objects.equals(lineContent[0], "apply");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private boolean checkFirstColumnSyntax(String str) {
        switch (str.toLowerCase()) {
            case "add", "subtract", "divide", "multiply" -> {
                return true;
            }
        }
        return false;
    }

    private boolean checkSecondColumnSyntax(String str) {
        boolean flag = false;
        try {
            Long.parseLong(str);
            flag = true;
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input, second column should be an integer");
        }
        return flag;
    }

    private boolean checkForDivideByZeros(String str) {
        String[] lineContent = str.split(" ");
        String instruction = lineContent[0];
        long number = 0;
        if (checkSecondColumnSyntax(lineContent[1])) {
            number = Long.parseLong(lineContent[1]);
        }
        return !instruction.equalsIgnoreCase("divide") || number != 0;
    }
}
