package com.github.kuramapommel.test.GoF.Iterator

import org.scalatest._

class BookTest extends FlatSpec with Matchers {

  "A Book" should "have name" in {
    import com.github.kuramapommel.ScalaGoF.GoF.Iterator.Book
    val name = "bookName"
    Book( name ).name should be( name )
  }
}
