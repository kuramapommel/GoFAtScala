package com.github.kuramapommel.scalagof.gof.factorymethod.framework

trait Factory {

  final def create( owner: String ) = {
    val p = createProduct( owner )
    registerProduct( p )
    p
  }

  protected def createProduct( owner: String ): Product

  protected def registerProduct( product: Product ): Unit

}
