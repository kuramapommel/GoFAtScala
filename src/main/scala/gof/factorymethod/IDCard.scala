package com.github.kuramapommel.scalagof.gof.factorymethod

import com.github.kuramapommel.scalagof.gof.factorymethod.framework._


final object IDCard extends Factory {
  import scala.collection.mutable._

  private[this] var owners = ArrayBuffer.empty[String]

  override protected def createProduct( owner: String ) = IDCard( owner )

  override protected def registerProduct( product: Product ) = owners += product.asInstanceOf[IDCard].owner

}

final case class IDCard( owner: String ) extends Product {

  println( "%sのカードを作ります。".format( owner ) )

  override def use = println( "%sのカードを使います。".format( owner ) )

}
