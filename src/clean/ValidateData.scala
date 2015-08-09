package clean

class ValidateData(numberWordsPerLine: Int, lines: List[String]) {

  def getValidData = {

    val charsToClean: List[String] = List("\"", "[", "]", "'")

    val trimmedLines = lines.map { m => m.trim() }
    val validLines = trimmedLines.filter(line => line.split("\\s+").size == numberWordsPerLine)
    val invalidLines = trimmedLines.filter(line => line.split("\\s+").size == numberWordsPerLine)

    val filtered = validLines.map(line => line
      .replace(charsToClean(0), "")
      .replace(charsToClean(1), "")
      .replace(charsToClean(2), "")
      .replace(charsToClean(3), ""))

    (filtered, invalidLines)

  }
}

object ValidateData {
  def main(args: Array[String]) {

    val validInvalid = new ValidateData(4, List("this is a test[]", "this is a")).getValidData
    validInvalid._1.foreach(println)

  }

}