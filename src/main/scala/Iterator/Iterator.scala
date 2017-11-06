package com.github.kuramapommel.ScalaGoF.Iterator

trait Iterator[T] {
  
  def hasNext() : Boolean

  def next() : T
}