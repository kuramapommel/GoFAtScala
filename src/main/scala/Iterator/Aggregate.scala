package com.github.kuramapommel.ScalaGoF.Iterator

trait Aggregate[T] {
  def iterator : Iterator[T]
}