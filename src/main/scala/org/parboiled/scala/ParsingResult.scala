package org.parboiled.scala

import org.parboiled.Node
import org.parboiled.support.{InputBuffer, ValueStack}
import org.parboiled.errors.ParseError
import org.parboiled.support.{ParsingResult => PParsingResult}

object ParsingResult {
  def apply[V](result: PParsingResult[V])  = new ParsingResult[V](result)

  implicit def unwrap[V](result: ParsingResult[V]): PParsingResult[V] = result.inner
}

class ParsingResult[V](val inner: PParsingResult[V]) {
  val matched: Boolean = inner.matched
  val result: Option[V] = if (inner.resultValue != null) Some(inner.resultValue) else None
  val errors: List[ParseError] = List(inner.parseErrors.toArray(new Array[ParseError](inner.parseErrors.size)): _*)
  val parseTreeRoot: Node[V] = inner.parseTreeRoot
  val valueStack: ValueStack[Any] = inner.valueStack.asInstanceOf[ValueStack[Any]]
  val inputBuffer: InputBuffer = inner.inputBuffer

  def hasErrors = !errors.isEmpty

}