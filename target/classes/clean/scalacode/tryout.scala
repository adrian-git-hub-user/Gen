package clean.scalacode

object tryout extends App {

  //val validWord: List[String] = List("valid1", "valid2", "six");
  //val lines: List[String] = List("this is a test", "this is a valid1")
  //val lines: List[String] = scala.io.Source.fromFile("c:\\data\\example.txt").getLines.toList

  def validLines(lines: List[String], validWordList: List[String]): Map[String, List[String]] = {
    val validLines = lines.map { m =>
      val validLine: (Boolean, Option[String]) = isValidLine(m, validWordList)
      if (validLine._1) {
        Some(validLine._2.get, m)
      } else
        None
    }

    val vl: List[(String, String)] = validLines.flatten
    val map = vl.groupBy(_._1).mapValues(group => group.map(kv => kv._2))

    map
  }

  def isValidLine(line: String, validWordList: List[String]): (Boolean, Option[String]) = {

    validWordList.foreach {
      word =>
        if (line.contains(word)) {
          return (true, Some(word));
        }
    }
    return (false, None)
  }

  //map.foreach(p => { println("************************" + p._1 + "*****************************"); p._2.foreach { x => println(x) } })

  //  println(map.size)
  // val str = map.map(pair => pair._1+"="+pair._2).mkString("?","&","")

}