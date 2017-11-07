package com.github.kuramapommel.ScalaGoF.GoF.Iterator

final case class BookShelfIterator( bookShelf: BookShelf ) extends Iterator[Book] {
  private[this] var index = 0

  override def hasNext = index < bookShelf.length

  override def next = {
    val tmpIndex = index
    index = index + 1
    bookShelf.getBookAt( tmpIndex ) match {
      case Right( book ) => Some( book )
      case Left( _ ) => None
    }
  }
}

final case class BookShelf( maxSize: Int ) extends Aggregate[Book] {
  private[this] var books : Array[Book] = Array.empty

  private[this] def tryEither[T]( f: => T )( implicit onError: Throwable => Either[Throwable,T] = { t:Throwable => Left( t ) }): Either[Throwable,T] = {
    try{
      Right( f )
    } catch {
      case c => onError( c )
    }
  }

  def getBookAt( index: Int ) = tryEither { books( index ) }

  def appendBook( book: Book ) = {
    val tmpArray = books :+ book
    if ( tmpArray.length > maxSize ) {
      Left( new RuntimeException )
    } else {
      books = books :+ book
      Right( this )
    }
  }

  def length = books.length

  override def iterator = BookShelfIterator( this )
}