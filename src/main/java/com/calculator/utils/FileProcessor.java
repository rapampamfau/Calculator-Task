package com.calculator.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

    private boolean isVerified = false;
    private final File file;
    private final FileVerification fv;
    private Long actualNumber;

    public FileProcessor(File file) {
        this.file = file;
        this.fv = new FileVerification();
        this.actualNumber = getNumberFromLastLine();
    }

    private void verifyFile() {
        if (fv.checkIfFileExist(file) && fv.checkFileFormat(file.getPath()) && fv.verifyAllInstructions(file)) {
            isVerified = true;
        }
    }

    public Long calculate() {
        if (isVerified) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String currentLine;
                String previousLine = "";
                while ((currentLine = reader.readLine()) != null) {
                    if (!previousLine.equals("")) {
                        String[] content = previousLine.split(" ");
                        String operator = content[0];
                        Long numberFromInstruction = Long.decode(content[1]);
                        actualNumber = decodeInstructions(operator, actualNumber, numberFromInstruction);
                    }
                    previousLine = currentLine;
                }
            } catch (IOException e) {
                System.out.println("Error with reading file.");
            }
        }
        return actualNumber;
    }

    public Long decodeInstructions(String operator, Long actualNumber, Long numberFromInstruction) {
        switch (operator.toLowerCase()) {
            case "add" -> {
                actualNumber = actualNumber + numberFromInstruction;
            }
            case "subtract" -> {
                actualNumber = actualNumber - numberFromInstruction;
            }
            case "divide" -> {
                actualNumber = actualNumber / numberFromInstruction;
            }
            case "multiply" -> {
                actualNumber = actualNumber * numberFromInstruction;
            }
        }
        return actualNumber;
    }

    private Long getNumberFromLastLine() {
        verifyFile();
        if (isVerified) {
            String lastLine = fv.getLastLine(file);
            String[] lastLineContent = lastLine.split(" ");
            return Long.decode(lastLineContent[1]);
        } else {
            System.out.println("File not verified.");
            return -1L;
        }
    }

    public boolean isVerified() {
        return isVerified;
    }
}
