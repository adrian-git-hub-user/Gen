package clean.scalacode

class PositionData(identifierPosition: Int, removeFieldsAtPositions: List[Int]) {

  private def remove(lines: List[String]): List[String] = {

    if (lines.isEmpty) {
      return lines;
    } else {
      val sl = lines.map(m => m.split(" ").toList)
      val upd = sl.map(line =>
        for {
          (x, i) <- line.zipWithIndex
          if !removeFieldsAtPositions.contains(i)
        } yield x)

      return upd.map { m => m.mkString(" ") }
    }
  }

  private def move(lines: List[String]): List[String] = {

    if (identifierPosition == 0) {
      return lines;
    } else {
      val u = lines.map(line => {
        val sl = line.split(" ").toList;
        val elementAtPos = sl(identifierPosition)
        (elementAtPos :: dropIndex(sl, identifierPosition)).mkString(" ")
      })

      u
    }
  }

  private def dropIndex[T](xs: List[T], n: Int): List[T] = {
    val (l1, l2) = xs splitAt n
    l1 ::: (l2 drop 1)
  }

  def update(lines: List[String]) = {
    move(remove(lines))
  }

}

object PositionData {
  def main(args: Array[String]) {

    //val lines: List[String] = List("100 one one this one one this one", "200 one is line 2 this this one", "300 this is line three this this one")
    val lines: List[String] = scala.io.Source.fromFile("c:\\data\\example.txt").getLines.toList

    println("# : " + lines.size)
    // new PositionData().move(lines).foreach { println }

    //val l = List("this is a test dsf as fa sdf af sad")

    val charsToClean: Set[Char] = Set('\"', '[', ']', ''')
    val validWord: List[String] = List("valid1", "valid2", "six");

    val validInvalid = new ValidateData(8, lines).getCleanData(charsToClean ,  validWord)

    new PositionData(3, List(0, 1, 2, 4)).update(validInvalid._2).foreach { println }

    //countedLines.foreach(println)
  }
}