import org.junit.Test
import org.rhea_core.Stream
import rx_eval.RxjavaEvaluationStrategy
import test_data.TestInfo
import test_data.utilities.Colors
import scala.collection.JavaConverters._

/**
  * @author Orestis Melkonian
  */
class Tester {

  @Test
  def single_thread(): Unit = {
    Stream.evaluationStrategy = new RxjavaEvaluationStrategy
    for (test: TestInfo <- test_data.TestData.tests().asScala) {
      print(test.name + ": ")
      if (test.equality) Colors.println(Colors.GREEN, "Passed")
      else {
        Colors.print(Colors.RED, "Failed")
        println(test.q1 + " != " + test.q2)
      }
    }
  }

  @Test
  def multi_thread(): Unit = {
    Stream.evaluationStrategy = new RxjavaEvaluationStrategy(true)
    for (test: TestInfo <- test_data.TestData.tests().asScala) {
      print(test.name + ": ")
      if (test.equality) Colors.println(Colors.GREEN, "Passed")
      else {
        Colors.print(Colors.RED, "Failed")
        println(test.q1 + " != " + test.q2)
      }
    }
  }
}
