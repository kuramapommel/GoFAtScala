package com.github.kuramapommel.test.GoF.Iterator

import org.scalatest._

class BookShelfTest extends WordSpec with Matchers {
  import com.github.kuramapommel.ScalaGoF.GoF.Iterator.BookShelf

  "A Bookshelf should has max size which is constructed" in {
    val bookShelf = BookShelf( 1 )
    bookShelf.maxSize should be( 1 )
  }

  "An exception will be thrown" when {
    val bookShelf = BookShelf( 0 )

    "a bookshelf doesn't have books and trying get a book" in {
      bookShelf.getBookAt( 0 ) match {
        case Left( _: RuntimeException ) => succeed
        case _ => fail( "result is not matching type RuntimeException" )
      }
    }

    "a bookshelf max size is 0 and trying append a book" in {
      import com.github.kuramapommel.ScalaGoF.GoF.Iterator.Book
      val book = Book( "test" )
      bookShelf.appendBook( book ) match {
        case Left( _: RuntimeException ) => succeed
        case _ => fail( "result is not matching type RuntimeException" )
      }
    }
  }
}
