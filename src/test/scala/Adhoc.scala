import org.junit.Test
import org.rhea_core.Stream
import rx_eval.RxjavaEvaluationStrategy
import test_data.utilities.Threads

//import scala.languageFeature.implicitConversions._
import scala_dsl.ImplicitConversions._

/**
 * @author Orestis Melkonian
 */
class Adhoc {
  @Test
  def adhoc() {
    Stream.evaluationStrategy = new RxjavaEvaluationStrategy

    // TODO fix map -> zip
    Stream.zip[Int, Int, Int](
      Stream.range(0, 1),
      Stream.range(10, 1),
      (i1: Int, i2: Int) => i1 + i2 : Int
    ).print()

    Threads.sleep()
  }
}
