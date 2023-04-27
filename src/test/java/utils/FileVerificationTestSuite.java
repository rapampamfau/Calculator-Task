package utils;

import com.calculator.utils.FileVerification;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
public class FileVerificationTestSuite {

    private FileVerification fv = new FileVerification();

    @Test
    void testVerifyAllInstructionsGood() {
        //Given
        File file = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile3.txt");

        //When
        boolean result = fv.verifyAllInstructions(file);

        //Then
        assertTrue(result);
    }

    @Test
    void testVerifyAllInstructionsDoubleValues() {
        //Given
        File file = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile5.txt");

        //When
        boolean result = fv.verifyAllInstructions(file);

        //Then
        assertTrue(result);
    }

    @Test
    void testVerifyAllInstructionsWithDivideByZero() {
        //Given
        File file = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile4.txt");

        //When
        boolean result = fv.verifyAllInstructions(file);

        //Then
        assertFalse(result);
    }

    @Test
    void testCheckFileFormat() {
        //Given
        File file1 = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile1.txt");
        File file2 = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile2.txt");
        File file3 = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile0");

        //When
        boolean res1 = fv.checkFileFormat(file1.getPath());
        boolean res2 = fv.checkFileFormat(file2.getPath());
        boolean res3 = fv.checkFileFormat(file3.getPath());

        //Then
        assertTrue(res1);
        assertTrue(res2);
        assertFalse(res3);
    }

    @Test
    void testCheckIfFileExist() {
        //Given
        File file1 = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile1.txt");
        File file2 = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile2.txt");
        File file3 = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile0.txt");
        File file4 = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\testFile.txt");

        //When
        boolean res1 = fv.checkIfFileExist(file1);
        boolean res2 = fv.checkIfFileExist(file2);
        boolean res3 = fv.checkIfFileExist(file3);
        boolean res4 = fv.checkIfFileExist(file4);

        //Then
        assertTrue(res1);
        assertTrue(res2);
        assertFalse(res3);
        assertFalse(res4);
    }
}
