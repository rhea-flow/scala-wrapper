package scala_dsl

import org.reactive_ros.ReactiveTopic
import org.reactive_ros.streams.Stream
import org.reactive_ros.streams.messages.Topic
import org.reactive_ros.util.functions.{Func1, Action1}
import org.ros.namespace.GraphName
import org.ros.node.{ConnectedNode, AbstractNodeMain}


class Listener extends AbstractNodeMain {
  override def getDefaultNodeName: GraphName = GraphName of "scala_dsl"

  private val topic: Topic = new Topic("chatter", std_msgs.String._TYPE)

  override def onStart(connectedNode: ConnectedNode) {
    val topic: Stream[std_msgs.String] = Stream.from("chatter", std_msgs.String._TYPE)

    Stream.setReactiveTopic(new ReactiveTopic(connectedNode))

    topic.subscribe(new Action1[std_msgs.String] {
      override def call(t: std_msgs.String): Unit = println(t.getData)
    })

    val s: Stream[Integer] = Stream.just(0, 1, 2)
    s.inc().subscribe(new Action1[Integer] {
      override def call(t: Integer): Unit = println(t)
    })
  }

  // Pimping library using "implicit conversion"
  class RichStream(stream: Stream[Integer]) {
    def inc(): Stream[Integer] = stream.map(new Func1[Integer, Integer] {
      override def call(t: Integer): Integer = t + 1
    })
  }

  implicit def enrichStream(st: Stream[Integer]): RichStream = new RichStream(st)
}