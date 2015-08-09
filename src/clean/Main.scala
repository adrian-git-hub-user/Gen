package clean

object Main {

  def main(args: Array[String]) {

    val numberWordsPerLine = 8;

    val lines: List[String] = List("100 one one this one one this one", "200 one is line 2 this this one", "300 this is line three this this one")
    //val lines: List[String] = scala.io.Source.fromFile("c:\\data\\example.txt").getLines.toList
    println("lines read")
    val validInvalidLines = new ValidateData(numberWordsPerLine , lines).getValidData
    println("valid data read");

    val countedLines = new clean.Parse(validInvalidLines._1, 0).getWordCountWithLabels

    countedLines.foreach(println)
  }
}