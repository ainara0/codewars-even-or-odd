import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EvenOrOddTest {

  private static void doTest(int num, String expected) {
    assertEquals(expected, Kata.evenOrOdd(num), "Incorrect answer for num = " + num);
  }
  
  @Test @Order(1) @DisplayName("Should return \"Odd\" for positive odd inputs")
  public void testPositiveOddNumbers() {
    doTest(1, "Odd");
    doTest(7, "Odd");
  }
  
  @Test @Order(1) @DisplayName("Should return \"Even\" for positive even inputs")
  public void testPositiveEvenNumbers() {
    doTest( 2, "Even");
    doTest(42, "Even");
  }
  
  @Test @Order(1) @DisplayName("Should return \"Odd\" for negative odd inputs")
  public void testNegativeOddNumbers() {
    doTest(-1, "Odd");
    doTest(-7, "Odd");
  }
  
  @Test @Order(1) @DisplayName("Should return \"Even\" for negative even inputs")
  public void testNegativeEvenNumbers() {
    doTest( -2, "Even");
    doTest(-42, "Even");
  }
  
  @Test @Order(1) @DisplayName("Should return \"Even\" for zero")
  public void testZero() {
    doTest(0, "Even");
  }

  private static record TestCase(int num, String expected) { }
  
  @Test @Order(2)
  public void randomTests(){
    var rnd = java.util.concurrent.ThreadLocalRandom.current();
    var testCases = new ArrayList<TestCase>();
    for(int i = 0; i < 25; i++){
      testCases.add(new TestCase(rnd.nextInt(1, 500) *  2,     "Even"));
      testCases.add(new TestCase(rnd.nextInt(1, 500) * -2,     "Even"));
      testCases.add(new TestCase(rnd.nextInt(1, 500) *  2 + 1, "Odd" ));
      testCases.add(new TestCase(rnd.nextInt(1, 500) * -2 - 1, "Odd" ));
    }
    Collections.shuffle(testCases);
    for(var testCase: testCases) {
      doTest(testCase.num, testCase.expected);
    }
  }
}