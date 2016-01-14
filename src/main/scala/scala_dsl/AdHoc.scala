package scala_dsl

import org.reactive_ros.streams.messages.Topic
import org.reactive_ros.util.functions._
import scala_dsl.ImplicitConversions._

/**
 * @author Orestis Melkonian
 */
class AdHoc extends StreamNode {
  override val name: String = "scala_dsl"
  override val inputTopics: Map[String, Topic] =
    ("LASER" -> ("/scan"-"LaserScan")) ++
    ("CAMERA" -> ("/camera/rgb/image_color"-"Image")) ++
    ("DEPTH" -> ("/camera/depth/image"-"Image")) ++
    ("TF" -> ("/tf"-"TFMessage"))

  override def dataflow(): Unit = {

    // Test lambda conversions
    /*val a0: Action0 = () => print("default")
    val a1: Action1[Int] = (x: Int) => print(x)
    val a2: Action2[Int, Int] = (x: Int, x2: Int) => print(x + x2)
    val a3: Action3[Int, Int, Int] = (x: Int, x2: Int, x3: Int) => print(x + x2 + x3)

    val f0: Func0[Int] = () => 2
    val f1: Func1[Int, Int] = (x: Int) => x + 1
    val f2: Func2[Int, Int, Int] = (x: Int, x2: Int) => x + x2
    val f3: Func3[Int, Int, Int, Int] = (x: Int, x2: Int, x3: Int) => x + x2 + x3

    val b0: Func0[Boolean] = () => true
    val b1: Func1[Int, Boolean] = (x: Int) => x % 2 == 0
    val b2: Func2[Int, Int, Boolean] = (x: Int, x2: Int) => x + x2 % 2 == 0*/


    // Compose pre-existing ros nodes
    /*RichStream.compose[Int](new HammingNumbers)
              .inc()
              .subscribe((x: Int) => println(x))

    val chatTopic: Topic = new Topic("chatter", std_msgs.String._TYPE)
    val chatter: StreamNode = StreamNode.createFromLegacy("roscpp_tutorials", "talker", chatTopic, "")
    RichStream.composeLegacy[std_msgs.String](chatter)
      .subscribe((msg: std_msgs.String) => println(msg.getData))*/
  }
}
