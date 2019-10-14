import org.junit.Test
import org.rhea_core.Stream
import rx_eval.RxjavaEvaluationStrategy
import test_data.utilities.Threads

import collection.JavaConverters._
import scala_wrapper.ImplicitConversions._
import scala.collection.JavaConversions._

/**
  * @author Orestis Melkonian
  */
class HammingNumbers {
  @Test
  def hamming() {
    Stream.evaluationStrategy = new RxjavaEvaluationStrategy

    val s: Stream[Int] =
      // Stream.just(1).loop((entry: Stream[Int]) =>
      //   (entry.multiply(2) mergeSort entry.multiply(3))
      //     mergeSort
      //   entry.multiply(5) : Stream[Int])
      // .startWith(1)
      // .distinct
      Stream.just(1).loop((entry: Stream[Int]) =>
        Stream.amb(List[Stream[Int]](
          entry.multiply(2),
          entry.multiply(3)
        ))
      )

    s.take(20).printAll()

    /*val s: Stream[Int] = Stream.nat
    s.take(10).inc().print()*/

    Threads.sleep()
  }
}
