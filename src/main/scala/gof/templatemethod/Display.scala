package com.github.kuramapommel.scalagof.gof.templatemethod

sealed abstract class AbstractDisplay {

  def open: Unit

  def print: Unit

  def close: Unit

  final def display = {
    open
    for ( _ <- 0 until 5 ) print
    close
  }
}

final case class CharDisplay( ch: Char ) extends AbstractDisplay {

  override def open = System.out.print( "<<" )

  override def print = System.out.print( ch )

  override def close = println( ">>" )
}

final case class StringDisplay( string: String ) extends AbstractDisplay {

  val width = string.getBytes.length

  override def open = printLine

  override def print = println( "|%s|".format( string ) )

  override def close = printLine

  private[this] def printLine = {
    System.out.print( "+" )
    for ( _ <- 0 until width ) System.out.print( "-" )
    println( "+" )
  }
}