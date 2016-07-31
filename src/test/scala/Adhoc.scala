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

    Stream.just(0, 1, 2).print()
  }
}
