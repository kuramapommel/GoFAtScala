package com.github.kuramapommel.scalagof.gof.Iterator

trait Iterator[T] {
  
  def hasNext : Boolean

  def next : Option[T]
}