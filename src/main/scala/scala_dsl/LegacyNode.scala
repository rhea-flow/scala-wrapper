package scala_dsl

import org.reactive_ros.streams.messages.Topic

import scala.sys.process.Process

/**
 * @author Orestis Melkonian
 */
class LegacyNode(
    ros_package: String,
    ros_node: String,
    inputTopics: Map[String, Topic],
    outputTopics: Map[String, Topic],
    args: String*)
  extends StreamNode {

  override val name = ros_package + "/" + ros_node
  override val inputs = inputTopics
  override val outputs = outputTopics

  override def dataflow(): Unit = Process("rosrun " + ros_package + " " + ros_node).lineStream_! // TODO add rosrun arguments
}
