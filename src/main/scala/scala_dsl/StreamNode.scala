package scala_dsl

import org.reactive_ros.ReactiveTopic
import org.reactive_ros.streams.Stream
import org.reactive_ros.streams.evaluation.{EvaluationStrategy, RxjavaEvaluationStrategy}
import org.reactive_ros.streams.messages.Topic
import org.ros.message.MessageFactory
import org.ros.namespace.GraphName
import org.ros.node._
import scala.sys.process._



/**
 * @author Orestis Melkonian
 */
abstract class StreamNode extends AbstractNodeMain {
  val name: String = this.getClass.getSimpleName
  val inputTopics: Map[String, Topic] = Map.empty
  val outputTopic: Topic = null
  val evaluationStrategy: EvaluationStrategy = new RxjavaEvaluationStrategy(null)
  val messageFactory: MessageFactory = NodeConfiguration.newPrivate.getTopicMessageFactory

  def dataflow(): Unit

  override def getDefaultNodeName: GraphName = GraphName of name
  override def onStart(connectedNode: ConnectedNode): Unit = {
    val reactiveTopic: ReactiveTopic = new ReactiveTopic(connectedNode)
    Stream.setReactiveTopic(reactiveTopic)
    evaluationStrategy.setReactiveTopic(reactiveTopic)
    Stream.setEvaluationStrategy(evaluationStrategy)
    dataflow()
  }

  def run(): Unit = {
    val executor: NodeMainExecutor = DefaultNodeMainExecutor.newDefault()
    executor.execute(this, NodeConfiguration.newPrivate())
  }
}

object StreamNode {
  def createFromLegacy(ros_package: String, ros_node: String, ros_topic: Topic, args: String*): StreamNode = {
    new StreamNode {
      override val outputTopic: Topic = ros_topic

      override def dataflow(): Unit =
        Process("rosrun " + ros_package + " " + ros_node).lineStream_!

      override val name: String = "chatter_node_legacy"
    }

  }
}
