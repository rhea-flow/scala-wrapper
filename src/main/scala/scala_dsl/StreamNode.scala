package scala_dsl

import org.reactive_ros.ReactiveTopic
import org.reactive_ros.streams.Stream
import org.reactive_ros.streams.evaluation.{EvaluationStrategy, RxjavaEvaluationStrategy}
import org.reactive_ros.streams.messages.Topic
import org.ros.namespace.GraphName
import org.ros.node.{AbstractNodeMain, ConnectedNode}


/**
 * @author Orestis Melkonian
 */
abstract class StreamNode extends AbstractNodeMain {
  val name: String
  val inputTopics: Map[String, Topic] = null
  val outputTopic: Topic = null
  val evaluationStrategy: EvaluationStrategy = new RxjavaEvaluationStrategy(null)

  def dataflow(): Unit

  override def getDefaultNodeName: GraphName = GraphName of name
  override def onStart(connectedNode: ConnectedNode): Unit = {
    val reactiveTopic: ReactiveTopic = new ReactiveTopic(connectedNode)
    Stream.setReactiveTopic(reactiveTopic)
    evaluationStrategy.setReactiveTopic(reactiveTopic)
    Stream.setEvaluationStrategy(evaluationStrategy)
    dataflow()
  }
}
