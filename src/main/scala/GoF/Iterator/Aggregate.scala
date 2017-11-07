package com.github.kuramapommel.ScalaGoF.GoF.Iterator

trait Aggregate[T] {
  def iterator : Iterator[T]
}