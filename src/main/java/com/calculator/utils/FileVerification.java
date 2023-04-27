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

    public boolean verifyLastLine(File file) {
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
                    if (!checkSyntax(previousLineContent[0])) {
                        System.out.println("Incorrect instruction syntax.");
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

    private boolean checkSyntax(String str) {
        switch (str.toLowerCase()) {
            case "add", "subtract", "divide", "multiply" -> {
                return true;
            }
        }
        return false;
    }
}
