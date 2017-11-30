package com.github.kuramapommel.scalagof.gof.adapter

final case class Banner( string: String ) {

  def showWithParen = println( "(%s)".format( string ) )

  def showWithAster = println( "*%s*".format( string ) )

}

final case class PrintBanner( string: String ) extends  Print {

  val banner = Banner( string )

  override def printWeak = banner.showWithParen

  override def printStrong = banner.showWithAster

}