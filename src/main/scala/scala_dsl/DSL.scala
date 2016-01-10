package scala_dsl

import java.lang.{Boolean => JBool}
import java.util.PriorityQueue

import org.reactive_ros.streams.Stream
import org.reactive_ros.streams.messages.Topic

import scala_dsl.ImplicitFunctionConversions._

class DSL extends StreamNode[Int] {
  override var name: String = "scala_dsl"
  override var inputTopics: Map[String, Topic] =
    Map(
      "LASER" -> new Topic("/scan", "LaserScan"),
      "CAMERA" -> new Topic("/camera/rgb/image_color", "Image"),
      "DEPTH" -> new Topic("/camera/depth/image", "Image"),
      "TF" -> new Topic("/tf", "TFMessage")
    )
  override var outputTopic: Topic = new Topic("/cmd_vel/geometry", std_msgs.ByteMultiArray._TYPE)


  override def dataflow(): Stream[Int] =  {
    compose(new IntNode).subscribe((s: Int) => println(s))
    Stream.just(1)
//    val g: Func1[Int, Int] = (x: Int) => x + 1
//    Stream.just(1)
//          .filter((x: Int) => x > 0)
//          .map((x: Int) => x + 1: Int)
//          .map(g)
//          .map((_ + 1): (Int => Int))
//          .loop((entry: Stream[Int]) => (entry.multiply(2) mergeSort entry.multiply(3)) mergeSort entry.multiply(5) : Stream[Int])
//          .startWith(1)
//          .distinct
//          .take(20)
//          .subscribe((t: Int) => println(t))

      /*Stream.just(1, 2, 3, 4, 5)
            .inc()
            .filter((i: Int) => i % 2 == 0)*/
//            .subscribe((i: Int) => println(i))
  }

  def compose[T](node: StreamNode[T]): Stream[T] = {

    Stream.fromInner(node.outputTopic)
  }

  /* 
    Pimping library using "implicit conversions"
  */
  class RichStream(stream: Stream[Int]) {
    def nat(): Stream[Int] =
      stream.loop((entry: Stream[Int]) => entry.inc());

    def inc(): Stream[Int] =
      stream.map((i: Int) => i + 1)

    def multiply(constant: Int): Stream[Int] =
      stream.map((i: Int) => i * constant)

    def mergeSort(other: Stream[Int]): Stream[Int] = {
      val queue: java.util.Queue[Int] = new PriorityQueue[Int]()
      Stream.zip(stream, other, (i1: Int, i2: Int) => {
          val min: Int = Math.min(i1, i2)
          val max: Int = Math.max(i1, i2)
          queue.add(max)
          if (min < queue.peek())
            min
          else {
            queue.add(min)
            queue.poll()
          }
        }: Int)
        .concatWith(Stream.from(queue))
        .distinct
    }
  }

  implicit def enrichStream(st: Stream[Int]): RichStream = new RichStream(st)


  // TEST
  class IntNode extends StreamNode[Int] {
    override var name: String = "int_node"
    override var inputTopics: Map[String, Topic] = _
    override var outputTopic: Topic = new Topic("output_test", std_msgs.ByteMultiArray._TYPE)

    override def dataflow(): Stream[Int] = Stream.just(1).nat
  }

}