package com.github.kuramapommel.ScalaGoF.GoF.Iterator

trait Iterator[T] {
  
  def hasNext : Boolean

  def next : Option[T]
}