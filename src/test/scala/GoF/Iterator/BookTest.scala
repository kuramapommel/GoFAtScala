package com.github.kuramapommel.test.GoF.Iterator

import org.scalatest._

class BookTest extends FlatSpec with Matchers {

  "A Book" should "has name" in {
    import com.github.kuramapommel.ScalaGoF.GoF.Iterator.Book
    val name = "bookName"
    val book = Book( name )
    book.name should be( name )
  }
}
