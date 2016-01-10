package scala_dsl

import org.reactive_ros.streams.messages.Topic
import org.reactive_ros.util.functions._
import java.lang.{Boolean => JBool}

/**
 * @author Orestis Melkonian
 */
package object ImplicitFunctionConversions {
  // TOPICS
  implicit def topics(topic: Topic): List[Topic] = List(topic)

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
  implicit def function1[A, R](f: ((A) => R)): Func1[A, R] =
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

  // BOOLEAN FUNCTIONS
  implicit def bfunc0(f: ((() => Boolean))): Func0[JBool] =
    new Func0[JBool] {
      def call(): JBool = f().booleanValue()
    }
  implicit def bfunc1[A](f: ((A => Boolean))): Func1[A, JBool] =
    new Func1[A, JBool] {
      def call(a: A): JBool = f(a).booleanValue()
    }
  implicit def bfunc2[A, B](f: ((A, B) => Boolean)): Func2[A, B, JBool] =
    new Func2[A, B, JBool] {
      def call(a: A, b: B): JBool = f(a, b).booleanValue
    }
  implicit def bfunc3[A, B, C](f: ((A, B, C) => Boolean)): Func3[A, B, C, JBool] =
    new Func3[A, B, C, JBool] {
      def call(a: A, b: B, c: C): JBool = f(a, b, c).booleanValue()
    }
  implicit def bfunc4[A, B, C, D](f: ((A, B, C, D) => Boolean)): Func4[A, B, C, D, JBool] =
    new Func4[A, B, C, D, JBool] {
      def call(a: A, b: B, c: C, d: D): JBool = f(a, b, c, d).booleanValue()
    }
  implicit def bfunc5[A, B, C, D, E](f: ((A, B, C, D, E) => Boolean)): Func5[A, B, C, D, E, JBool] =
    new Func5[A, B, C, D, E, JBool] {
      def call(a: A, b: B, c: C, d: D, e: E): JBool = f(a, b, c, d, e).booleanValue()
    }
  implicit def bfunc6[A, B, C, D, E, G](f: ((A, B, C, D, E, G) => Boolean)): Func6[A, B, C, D, E, G, JBool] =
    new Func6[A, B, C, D, E, G, JBool] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G): JBool = f(a, b, c, d, e, g).booleanValue()
    }
  implicit def bfunc7[A, B, C, D, E, G, H](f: ((A, B, C, D, E, G, H) => Boolean)): Func7[A, B, C, D, E, G, H, JBool] =
    new Func7[A, B, C, D, E, G, H, JBool] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H): JBool = f(a, b, c, d, e, g, h).booleanValue()
    }
  implicit def bfunc8[A, B, C, D, E, G, H, I](f: ((A, B, C, D, E, G, H, I) => Boolean)): Func8[A, B, C, D, E, G, H, I, JBool] =
    new Func8[A, B, C, D, E, G, H, I, JBool] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I): JBool = f(a, b, c, d, e, g, h, i).booleanValue()
    }
  implicit def bfunc9[A, B, C, D, E, G, H, I, J](f: ((A, B, C, D, E, G, H, I, J) => Boolean)): Func9[A, B, C, D, E, G, H, I, J, JBool] =
    new Func9[A, B, C, D, E, G, H, I, J, JBool] {
      def call(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J): JBool = f(a, b, c, d, e, g, h, i, j).booleanValue()
    }
}
