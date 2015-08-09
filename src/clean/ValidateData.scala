package clean

class ValidateData(numberWordsPerLine: Int, lines: List[String]) {

  def getValidData = {

    val charsToClean: List[String] = List("\"", "[", "]", "'")
    val charsToClean1: Set[Char] = Set('\"', '[', ']', ''')

    val trimmedLines = lines.map { m => m.trim() }
    val validLines = trimmedLines.filter(line => line.split("\\s+").size == numberWordsPerLine)
    val invalidLines = trimmedLines.filter(line => line.split("\\s+").size == numberWordsPerLine)

    val filtered = validLines.map(line => line.filterNot(c => charsToClean.contains(c)))

    val filtered1 = validLines.map(line => line.filterNot(c => charsToClean1.contains(c)))

    (filtered1, invalidLines)

  }
}

object ValidateData {
  def main(args: Array[String]) {

    val validInvalid = new ValidateData(4, List("this is a test[]", "this is a")).getValidData
    validInvalid._1.foreach(println)

  }

}