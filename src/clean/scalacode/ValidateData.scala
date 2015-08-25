package clean.scalacode

class ValidateData(numberWordsPerLine: Int, lines: List[String]) {

  def getCleanData(charsToClean: Set[Char] , validWord: List[String]) = {

    val trimmedLines = lines.map { m => m.trim() }.map(m => m.replace("\\s+", " "))
    val validLines = trimmedLines.filter(line => line.split("\\s+").size == numberWordsPerLine)
    val invalidLines = trimmedLines.filter(line => line.split("\\s+").size == numberWordsPerLine)

    val filtered: List[String] = validLines.map(line => line.filterNot(c => charsToClean.contains(c)))

    val convertToMap = mapLines(filtered, validWord)

    if (filtered.isEmpty) {
      throw new Exception("No lines were filtered")
    }

    (convertToMap, invalidLines)

  }

  private def mapLines(lines: List[String], validWordList: List[String]): Map[String, List[String]] = {
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

  private def isValidLine(line: String, validWordList: List[String]): (Boolean, Option[String]) = {

    validWordList.foreach {
      word =>
        if (line.contains(word)) {
          return (true, Some(word));
        }
    }
    return (false, None)
  }

}

object ValidateData {
  def main(args: Array[String]) {

/*    val validInvalid = new ValidateData(4, List("this is a test[]", "this is a")).getCleanData
    validInvalid._1.foreach(println)
*/
  }

}