package com.github.kuramapommel.scalagof.gof

final object GoF {

  lazy val patterns = Set(
    Pattern.Iterator,
    Pattern.Adapter,
    Pattern.TemplateMethod,
    Pattern.FactoryMethod
  )

  def run = for ( pattern <- patterns ) pattern.run

  sealed trait Pattern {
    def run : Unit
  }

  final object Pattern {

    final case object Iterator extends Pattern {
      import com.github.kuramapommel.scalagof.gof.Iterator._

      private[this] val bookShelf = BookShelf( 4 )

      override def run = {
        ( for {
          _ <- bookShelf.appendBook( Book( "aaa book" ) )
          _ <- bookShelf.appendBook( Book( "bbb book" ) )
          _ <- bookShelf.appendBook( Book( "ccc book" ) )
          eitherBookShelf <- bookShelf.appendBook( Book( "ddd book" ) )
        } yield eitherBookShelf ) match {
          case Left( _ ) => print( "over range!!" )
          case Right( bookShelf ) => {
            val ite = bookShelf.iterator
            while ( ite.hasNext ) {
              println( ite.next match {
                case Some( book ) => book.name
                case None => "not exist!!"
              } )
            }
          }
        }
      }
    }

    final case object Adapter extends Pattern {
      import com.github.kuramapommel.scalagof.gof.adapter._

      override def run = {
        val p = PrintBanner( "Hello" )
        p.printWeak
        p.printStrong
      }
    }

    final case object TemplateMethod extends Pattern {
      import com.github.kuramapommel.scalagof.gof.templatemethod._

      override def run = {
        for ( display <-  List( CharDisplay( 'H' ), StringDisplay( "Hello, world." ), StringDisplay( "こんにちは。" ) ) ) display.display
      }
    }

    final case object FactoryMethod extends Pattern {
      import com.github.kuramapommel.scalagof.gof.factorymethod._

      override def run = {
        for ( idCard <- List( IDCard.create( "tom" ), IDCard.create( "john" ), IDCard.create( "damon" ) ) ) idCard.use
      }
    }
  }
}
