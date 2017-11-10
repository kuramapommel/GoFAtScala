package com.github.kuramapommel.test.GoF.Iterator

import com.github.kuramapommel.ScalaGoF.GoF.Iterator.BookShelfIterator
import org.scalatest._

class BookShelfTest extends WordSpec with Matchers {
  import com.github.kuramapommel.ScalaGoF.GoF.Iterator.BookShelf

  "When the max size is initialized, A Bookshelf" should {
    val maxSize = 1
    val bookShelf = BookShelf( maxSize )

    "have the max size" in {
      bookShelf.maxSize should be( maxSize )
    }
  }

  "When a bookshelf has max size which is one" can {
    val maxSize = 1
    val bookShelf = BookShelf( maxSize )

    "append a book and should have length which is equal max size" in {
      import com.github.kuramapommel.ScalaGoF.GoF.Iterator.Book
      bookShelf.appendBook( Book( "test" )) match {
        case Right( bookShelf ) => bookShelf.length should be( maxSize )
        case _ => fail( "result is non" )
      }
    }
  }

  "An exception will be thrown" when {
    val bookShelf = BookShelf( 0 )

    "a bookshelf doesn't have books and trying get a book" in {
      bookShelf.getBookAt( 0 ) match {
        case Left( _: IndexOutOfBoundsException ) => succeed
        case _ => fail( "result is not matching type IndexOutOfBoundsException" )
      }
    }

    "a bookshelf max size is 0 and trying append a book" in {
      import com.github.kuramapommel.ScalaGoF.GoF.Iterator.Book
      bookShelf.appendBook( Book( "test" ) ) match {
        case Left( "over max size" ) => succeed
        case _ => fail( "result is not matching error message" )
      }
    }
  }

  "A bookShelf should have a iterator" in {
    import com.github.kuramapommel.ScalaGoF.GoF.Iterator.Book
    val bookShelf = BookShelf( 1 )
    bookShelf.appendBook( Book( "test" ) ) match {
      case Right( bookShelf ) => bookShelf.iterator match {
        case _ : BookShelfIterator => succeed
        case _ => fail( "result is not matching type BookShelfIterator" )
      }
      case _ => fail( "result is non" )
    }
  }
}
