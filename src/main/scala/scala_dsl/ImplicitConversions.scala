package scala_dsl

import java.util.PriorityQueue

import org.rhea_core.Stream
import org.rhea_core.util.functions._

/**
 * @author Orestis Melkonian
 */
package object ImplicitConversions {
  /* New stream operators */
  class IntStream(stream: Stream[Int]) {
    def nat(): Stream[Int] =
      stream.loop((entry: Stream[Int]) => entry.inc())

    /*def even(): Stream[Int] =
      stream.filter((i: Int) => i % 2 == 0)*/

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


  implicit def intifyStream(st: Stream[Int]): IntStream = new IntStream(st)
  implicit def javaToScala_INT(i: Integer): Int = i.intValue
  implicit def javaToScala_SINT(i: Stream[Integer]): Stream[Int] =
    i.asInstanceOf[Stream[Int]]

  /*object RichStream {
    def compose[T](node: LegacyNode): Stream[T] = {
      node.run()
      Stream.from(node.outputs)
    }
  }*/

  /* Topic infix constructor */
  /*class RichString(string: String) {
    def -(topicType: String): Topic = new Topic(string, topicType)
    def ->(topic: Topic): Map[String, Topic] = Map((string, topic))
  }
  implicit def enrichString(st: String): RichString = new RichString(st)*/

  // ACTIONS
  implicit def action0(f: (() => Unit)): Action0 =
    new Action0 {
      def call(): Unit = f()
    }
  implicit def action1[A](f: (A => Unit)): Action1[A] =
    new Action1[A] {
      def call(a: A): Unit = f(a)
    }
  implicit def action2[A, B](f: ((A, B) => Unit)): Action2[A, B] =
    new Action2[A, B] {
      def call(a: A, b: B): Unit = f(a, b)
    }
  implicit def action3[A, B, C](f: ((A, B, C) => Unit)): Action3[A, B, C] =
    new Action3[A, B, C] {
      def call(a: A, b: B, c: C): Unit = f(a, b, c)
    }
  implicit def action4[A, B, C, D](f: ((A, B, C, D) => Unit)): Action4[A, B, C, D] =
    new Action4[A, B, C, D] {
      def call(a: A, b: B, c: C, d: D): Unit = f(a, b, c, d)
    }
  implicit def action5[A, B, C, D, E](f: ((A, B, C, D, E) => Unit)): Action5[A, B, C, D, E] =
    new Action5[A, B, C, D, E] {
      def call(a: A, b: B, c: C, d: D, e: E): Unit = f(a, b, c, d, e)
    }
  implicit def action6[A, B, C, D, E, G](f: ((A, B, C, D, E, G) => Unit)): Action6[A, B, C, D, E, G] =
    new Action6[A, B, C, D, E, G] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G): Unit = f(a, b, c, d, e, g)
    }
  implicit def action7[A, B, C, D, E, G, H](f: ((A, B, C, D, E, G, H) => Unit)): Action7[A, B, C, D, E, G, H] =
    new Action7[A, B, C, D, E, G, H] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H): Unit = f(a, b, c, d, e, g, h)
    }
  implicit def action8[A, B, C, D, E, G, H, I](f: ((A, B, C, D, E, G, H, I) => Unit)): Action8[A, B, C, D, E, G, H, I] =
    new Action8[A, B, C, D, E, G, H, I] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I): Unit = f(a, b, c, d, e, g, h, i)
    }
  implicit def action9[A, B, C, D, E, G, H, I, J](f: ((A, B, C, D, E, G, H, I, J) => Unit)): Action9[A, B, C, D, E, G, H, I, J] =
    new Action9[A, B, C, D, E, G, H, I, J] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J): Unit = f(a, b, c, d, e, g, h, i, j)
    }

  // FUNCTIONS
  implicit def function0[R](f: (() => R)): Func0[R] =
    new Func0[R] {
      def call(): R = f()
    }
  implicit def function1[A, R](f: A => R): Func1[A, R] =
    new Func1[A, R] {
      def call(a: A): R = f(a)
    }
  implicit def function2[A, B, R](f: ((A, B) => R)): Func2[A, B, R] =
    new Func2[A, B, R] {
      def call(a: A, b: B): R = f(a, b)
    }
  implicit def function3[A, B, C, R](f: ((A, B, C) => R)): Func3[A, B, C, R] =
    new Func3[A, B, C, R] {
      def call(a: A, b: B, c: C): R = f(a, b, c)
    }
  implicit def function4[A, B, C, D, R](f: ((A, B, C, D) => R)): Func4[A, B, C, D, R] =
    new Func4[A, B, C, D, R] {
      def call(a: A, b: B, c: C, d: D): R = f(a, b, c, d)
    }
  implicit def function5[A, B, C, D, E, R](f: ((A, B, C, D, E) => R)): Func5[A, B, C, D, E, R] =
    new Func5[A, B, C, D, E, R] {
      def call(a: A, b: B, c: C, d: D, e: E): R = f(a, b, c, d, e)
    }
  implicit def function6[A, B, C, D, E, G, R](f: ((A, B, C, D, E, G) => R)): Func6[A, B, C, D, E, G, R] =
    new Func6[A, B, C, D, E, G, R] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G): R = f(a, b, c, d, e, g)
    }
  implicit def function7[A, B, C, D, E, G, H, R](f: ((A, B, C, D, E, G, H) => R)): Func7[A, B, C, D, E, G, H, R] =
    new Func7[A, B, C, D, E, G, H, R] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H): R = f(a, b, c, d, e, g, h)
    }
  implicit def function8[A, B, C, D, E, G, H, I, R](f: ((A, B, C, D, E, G, H, I) => R)): Func8[A, B, C, D, E, G, H, I, R] =
    new Func8[A, B, C, D, E, G, H, I, R] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I): R = f(a, b, c, d, e, g, h, i)
    }
  implicit def function9[A, B, C, D, E, G, H, I, J, R](f: ((A, B, C, D, E, G, H, I, J) => R)): Func9[A, B, C, D, E, G, H, I, J, R] =
    new Func9[A, B, C, D, E, G, H, I, J, R] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J): R = f(a, b, c, d, e, g, h, i, j)
    }
}
