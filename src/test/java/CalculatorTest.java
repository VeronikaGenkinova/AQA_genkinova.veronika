import org.hillel.calculator.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    public static void init() {
        calculator = new Calculator();
        System.out.println("Create new object before calculating");
    }

    @Test
    @DisplayName("Divide test")
    public void divideTest() throws IOException {
        System.out.println("Divide test");
        int result = calculator.divideTest(28,2);
        Assertions.assertEquals(14, result, "Calculation result is wrong: " + result);
    }

    @Test
    @DisplayName("Multiply test")
    public void multiplyTest() {
        System.out.println("Multiply test");
        int result = calculator.multiplyTest(9,6);
        Assertions.assertEquals(54, result, "Calculation result is wrong: " + result);
    }

    @Test
    @DisplayName("Add test")
    public void addTest() {
        System.out.println("Add test");
        int result = calculator.addTest(17,4);
        Assertions.assertEquals(21, result, "Calculation result is wrong: " + result);
    }

    @Test
    @DisplayName("Subtract test")
    public void subtractTest() {
        System.out.println("Subtract test");
        int result = calculator.subtractTest(32, 14);
        Assertions.assertEquals(18, result, "Calculation result is wrong: " + result);
    }

    @ParameterizedTest
    @DisplayName("Multiply test with parameters")
    @MethodSource("intStreamProvider")
    public void multiplyTest(int a, int b, int expectedResult) {
        System.out.println("Multiply test with parameters");
        int actualResult = calculator.multiplyTest(a, b);
        Assertions.assertEquals(actualResult, expectedResult, "test result: " + actualResult);
    }

    public static Stream<Arguments> intStreamProvider() {
        return Stream.of(
                Arguments.arguments(3,5,15),
                Arguments.arguments(3,15,45),
                Arguments.arguments(3,25,75));
    }
}
