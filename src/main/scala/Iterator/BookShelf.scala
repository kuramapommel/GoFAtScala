package com.github.kuramapommel.ScalaGoF.Iterator

case class BookShelfIterator( bookShelf: BookShelf ) extends Iterator[Book] {
  private[this] var index = 0

  def hasNext() = index < bookShelf.length

  def next() = {
    val tmpIndex = index
    index = index + 1
    bookShelf.getBookAt( tmpIndex )
  }
}

case class BookShelf( maxSize: Int ) extends Aggregate[Book] {
  private[this] var books : Array[Book] = Array.empty

  def getBookAt( index: Int ) = books( index )

  def appendBook( book: Book ) = {
    val tmpArray = books :+ book
    if ( tmpArray.length > maxSize ) {
      Left( new RuntimeException )
    }

    books = books :+ book
    Right( books )
  }

  def length = books.length

  def iterator = BookShelfIterator.apply( this )
}