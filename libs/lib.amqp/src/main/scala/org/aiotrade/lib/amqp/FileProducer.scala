
package org.aiotrade.lib.amqp

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.HashMap
import java.util.Map

import com.rabbitmq.client.ConnectionParameters
import com.rabbitmq.client.Consumer
import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.AMQP.BasicProperties
import org.aiotrade.lib.amqp.datatype.ContentType

object FileProducer {

  // --- simple test
  def main(args: Array[String]) {
    val nConsumers = 5

    val host = "localhost"
    val port = 5672

    val exchange = "market.file"
    val queue = "filequeue"
    val routingKey = "faster.server.dbffile"

    val params = new ConnectionParameters
    params.setUsername("guest")
    params.setPassword("guest")
    params.setVirtualHost("/")
    params.setRequestedHeartbeat(0)

    val factory = new ConnectionFactory(params)

    val producer = new FileProducer(factory, host, port, exchange, queue, routingKey, nConsumers)
    producer.start
    val files = List(new File("pom.xml"), new File("src/test/resources/testfile.txt"))

    producer.sendFiles(files)
    System.exit(0)
  }
}

class FileProducer(cf: ConnectionFactory, host: String, port: Int, exchange: String, queue: String, routingKey: String, nConsumers: Int
) extends AMQPDispatcher(cf, host, port, exchange) {

  @throws(classOf[IOException])
  override def configure(channel: Channel): Option[Consumer] = {
    channel.exchangeDeclare(exchange, "direct", true)
    // produce to n queues.
    // we'll create these queues here whatever, so, even the consumer starts
    // later than producer, they won't miss messages that were previously sent.
    for (i <- 0 until nConsumers) {
      // count from 1, (i + 1) should be enclosed, otherwise will be 01 instead of 1
      val queuei = queue + (i + 1) 
      channel.queueDeclare(queuei, true)
      channel.queueBind(queuei, exchange, routingKey)
    }
    
    None
  }

  @throws(classOf[IOException])
  def sendFiles(files: List[File]) {
    files foreach (sendFile(_))
  }

  @throws(classOf[IOException])
  def sendFile(file: File, toName: Option[String] = None) {
    val is = new FileInputStream(file)
    val length = file.length.toInt
    val body = new Array[Byte](length)
    is.read(body)
    is.close

    sendFile(body, toName.getOrElse(file.getName))
  }

  def sendFile(body: Array[Byte], toName: String) {
    val headers: java.util.Map[String, AnyRef] = new HashMap
    headers.put("filename", toName)
    headers.put("length", body.length.asInstanceOf[AnyRef])
    val props = new BasicProperties
    props.headers = headers
    props.contentType = ContentType.OCTET_STREAM.mimeType
    props.deliveryMode = 2 // persistent
    publish(exchange, routingKey, props, body)
  }

}
