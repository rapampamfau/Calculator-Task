package utils;

import com.calculator.utils.FileProcessor;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
public class FileProcessorTestSuite {

    @Test
    void testFileVerificationPositive() {
        //Given
        File file = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile3.txt");
        FileProcessor processor = new FileProcessor(file);

        //When
        boolean result = processor.isVerified();

        //Then
        assertTrue(result);
    }

    @Test
    void testFileVerificationNegative() {
        //Given
        File file = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile1.txt");
        FileProcessor processor = new FileProcessor(file);

        //When
        boolean result = processor.isVerified();

        //Then
        assertFalse(result);
    }

    @Test
    void testCalculateOK() {
        //Given
        File file = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile3.txt");
        FileProcessor processor = new FileProcessor(file);

        //When
        Long result = processor.calculate();

        //Then
        assertEquals(330, result);
    }

    @Test
    void testCalculateWithDivideByZero() {
        //Given
        File file = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile4.txt");
        FileProcessor processor = new FileProcessor(file);

        //When
        processor.calculate();

        //Then
        assertFalse(processor.isVerified());
    }

    @Test
    void testCalculateWithWrongInstructions() {
        //Given
        File file = new File("C:\\Development\\Java\\Calculator-Task\\src\\test\\resources\\testFile1.txt");
        FileProcessor processor = new FileProcessor(file);

        //When
        Long result = processor.calculate();

        //Then
        assertFalse(processor.isVerified());
    }
}
