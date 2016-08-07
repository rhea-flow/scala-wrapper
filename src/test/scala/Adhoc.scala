import java.util.PriorityQueue

import org.junit.Test
import org.rhea_core.Stream
import rx_eval.RxjavaEvaluationStrategy

import scala_dsl.ImplicitConversions._

/**
 * @author Orestis Melkonian
 */
class Adhoc {
  @Test
  def twelveTone() {
    Stream.evaluationStrategy = new RxjavaEvaluationStrategy

    Stream.zip[Int, Int, Int](
      Stream.just(0, 1, 2),
      Stream.just(-0, -1, -2),
      (i1: Int, i2: Int) => i1 + i2 : Int
    ).print()
  }
}
