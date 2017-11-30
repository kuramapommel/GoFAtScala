package com.github.kuramapommel.test.GoF.Iterator

import org.scalatest._

class BookShelfTest extends WordSpec with Matchers {
  import com.github.kuramapommel.scalagof.gof.Iterator.BookShelf

  "A bookShelf" should {
    import com.github.kuramapommel.scalagof.gof.Iterator.Book
    import com.github.kuramapommel.scalagof.gof.Iterator.BookShelfIterator

    "have a iterator" in {
        BookShelf( 1 ).appendBook( Book( "test" ) ) match {
        case Right( bookShelf ) => bookShelf.iterator match {
          case _: BookShelfIterator => succeed
          case _ => fail( "result is not matching type BookShelfIterator" )
        }
        case _ => fail( "result is non" )
      }
    }
  }

  "When a bookshelf has max size which is more than 0, A Bookshelf" should {
    val maxSize = 1

    "have the max size" in {
        BookShelf( maxSize ).maxSize should be( maxSize )
    }

    "append a book and should have length which is equal max size" in {
      import com.github.kuramapommel.scalagof.gof.Iterator.Book

      BookShelf( maxSize ).appendBook( Book( "test" ) ) match {
        case Right( bookShelf ) => bookShelf.length should be( maxSize )
        case _ => fail( "result is not having bookshelf" )
      }
    }

    "get book" in {
      import com.github.kuramapommel.scalagof.gof.Iterator.Book

      ( for {
        bookshelf <- BookShelf( maxSize ).appendBook( Book( "test" ) )
        book <- bookshelf.getBookAt( 0 )
      } yield book ) match {
        case Right( _: Book ) => succeed
        case _ => fail( "result is not having Book" )
      }
    }
  }

  "An exception will be thrown" when {

    "a bookshelf doesn't have books and trying get a book" in {
      BookShelf( 0 ).getBookAt( 0 ) match {
        case Left( _: IndexOutOfBoundsException ) => succeed
        case _ => fail( "result is not matching type IndexOutOfBoundsException" )
      }
    }

    "a bookshelf max size is 0 and trying append a book" in {
      import com.github.kuramapommel.scalagof.gof.Iterator.Book

      BookShelf( 0 ).appendBook( Book( "test" ) ) match {
        case Left( "over max size" ) => succeed
        case _ => fail( "result is not matching error message" )
      }
    }
  }
}

class BookShelfIteratorTest extends WordSpec with Matchers {
  import com.github.kuramapommel.scalagof.gof.Iterator.BookShelf
  import com.github.kuramapommel.scalagof.gof.Iterator.BookShelfIterator

  "When the bookshelfiterator has a bookshelf which is having max size which is more than 0, bookshelfiterator" should {
    import com.github.kuramapommel.scalagof.gof.Iterator.Book
    val eitherBookShelf = BookShelf( 1 ).appendBook( Book( "test" ) )

    "have bookshelf" in {
      eitherBookShelf match {
        case Right( bookshelf ) => BookShelfIterator( bookshelf ).bookShelf match {
          case _: BookShelf => succeed
          case _ => fail( "result is not having BookShelf" )
        }
        case _ => fail( "test preparation exception" )
      }
    }

    "have next" in {
      eitherBookShelf match {
        case Right( bookshelf ) => BookShelfIterator( bookshelf ).hasNext should be( true )
        case _ => fail( "test preparation exception" )
      }
    }

    "get next" in {
      eitherBookShelf match {
        case Right( bookshelf ) => BookShelfIterator( bookshelf ).next match {
          case Some( _: Book ) => succeed
          case _ => fail( "result is not having Book" )
        }
        case _ => fail( "test preparation exception" )
      }
    }
  }

  "When the bookshelfiterator has a bookshelf which is having max size which is 0, bookshelfiterator" should {
    val bookshelf = BookShelf( 0 )

    "have next" in {
      BookShelfIterator( bookshelf ).hasNext should be( false )
    }

    "get next" in {
      BookShelfIterator( bookshelf ).next match {
          case None => succeed
          case _ => fail( "result is not None" )
      }
    }
  }
}
