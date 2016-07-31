import java.util.PriorityQueue

import org.junit.Test
import org.rhea_core.Stream
import rx_eval.RxjavaEvaluationStrategy
import test_data.utilities.Threads

import collection.JavaConverters._
import scala_dsl.ImplicitConversions._

/**
  * @author Orestis Melkonian
  */
class HammingNumbers {
  @Test
  def hamming() {
    Stream.evaluationStrategy = new RxjavaEvaluationStrategy

    /*Stream.just(1)
      .loop(
        (entry: Stream[Int]) =>
          (entry.multiply(2) mergeSort entry.multiply(3))
            mergeSort
          entry.multiply(5) : Stream[Int]
      )
      .startWith(1)
      .distinct
      .take(20)
      .subscribe((i: Int) => println(i))*/

    val s: Stream[Int] = Stream.nat

    s.take(10).inc().print()
//    Stream.nat.take(10).print()

    Threads.sleep()
  }
}
