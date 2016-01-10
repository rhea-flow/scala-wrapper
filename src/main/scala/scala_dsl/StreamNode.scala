package scala_dsl

import org.reactive_ros.ReactiveTopic
import org.reactive_ros.streams.Stream
import org.reactive_ros.streams.evaluation.{EvaluationStrategy, RxjavaEvaluationStrategy}
import org.reactive_ros.streams.messages.Topic
import org.reactive_ros.streams.output.InnerTopicOutput
import org.ros.namespace.GraphName
import org.ros.node.{AbstractNodeMain, ConnectedNode}


/**
 * @author Orestis Melkonian
 */
abstract class StreamNode[T] extends AbstractNodeMain {
  var name: String
  var inputTopics: Map[String, Topic]
  var outputTopic: Topic // topic of type [T]
  var evaluationStrategy: EvaluationStrategy = new RxjavaEvaluationStrategy(null)

  def dataflow(): Stream[T]

  override def getDefaultNodeName: GraphName = GraphName of name
  override def onStart(connectedNode: ConnectedNode): Unit = {
    val reactiveTopic: ReactiveTopic = new ReactiveTopic(connectedNode)
    Stream.setReactiveTopic(reactiveTopic)
    val strategy: EvaluationStrategy = evaluationStrategy
    strategy.setReactiveTopic(reactiveTopic)
    Stream.setEvaluationStrategy(strategy)
    dataflow().subscribe(new InnerTopicOutput(outputTopic))
  }
}
