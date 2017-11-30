package com.github.kuramapommel.scalagof.gof.Iterator

trait Aggregate[T] {
  def iterator : Iterator[T]
}