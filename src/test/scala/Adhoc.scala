import org.junit.{Before, Test}
import org.rhea_core.Stream
import org.rhea_core.internal.graph.FlowGraph
import org.rhea_core.optimization.{DefaultOptimizationStrategy, OptimizationStrategy}
import rx_eval.RxjavaEvaluationStrategy
import test_data.utilities.Threads

import scala_wrapper.ImplicitConversions._

/**
 * @author Orestis Melkonian
 */
class Adhoc {
  @Before
  def setEval(): Unit = {
    Stream.evaluationStrategy = new RxjavaEvaluationStrategy
    Stream.DEBUG = true
  }

//  @Test
  def adhoc() {
    Stream.zip[Int, Int, Int](
      Stream.range(0, 1).map[Integer]((i: Integer) => i.intValue() + 1 : Integer),
      Stream.range(10, 1).map[Integer]((i: Integer) => i.intValue() + 1 : Integer),
      (i1: Int, i2: Int) => i1 + i2 : Int
    ).print()

    Threads.sleep()
  }
}
