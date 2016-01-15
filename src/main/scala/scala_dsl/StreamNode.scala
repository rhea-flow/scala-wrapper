package scala_dsl

import org.reactive_ros.ReactiveTopic
import org.reactive_ros.streams.Stream
import org.reactive_ros.streams.evaluation.{EvaluationStrategy, RxjavaEvaluationStrategy}
import org.reactive_ros.streams.messages.Topic
import org.ros.message.MessageFactory
import org.ros.namespace.GraphName
import org.ros.node._

/**
 * @author Orestis Melkonian
 */
abstract class StreamNode extends AbstractNodeMain {
  val name: String = this.getClass.getSimpleName
  val inputs: Map[String, Topic] = Map.empty
  val outputs: Map[String, Topic] = Map.empty
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
  
  implicit def topics(string: String): Topic = {
    val ret: Option[Topic] = if (inputs.contains(string)) inputs.get(string) else outputs.get(string)
    if (ret.isDefined) ret.get() else null
  }
}
