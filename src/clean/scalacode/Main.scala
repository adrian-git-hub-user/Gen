package clean.scalacode

import scala.collection.mutable.ListBuffer

/**
 * Ensure first word of each line is itentifier
 *
 */
object Main {

  def main(args: Array[String]) {
    // runNormal
    //runTest
    runNormalMultipleFiles
  }

  private def runNormal = {

    val wc: Map[String, ListBuffer[String]] = runWordCount(clean.scalacode.ProdConfig.numberWordsPerLine, clean.scalacode.ProdConfig.lines, clean.scalacode.ProdConfig.charsToClean, clean.scalacode.ProdConfig.validWord)

    //Should not need to access individual counts by accessing map , as validating lines should ensure just valid data is returned.
    //val countedLines: ListBuffer[String] = wc.get("one").get
    //countedLines.foreach(println)

    //TODO - Perhaps output filename also
    println("Printing keys,sizes");
    wc.foreach(f => println(f._1 + "," + f._2.size));

  }

  //TODO
  def getDateFromFileName(fileName: String) = {
    // println("FileName : "+fileName)

    fileName.substring(fileName.length() - 14, fileName.length())
  }

  private def runNormalMultipleFiles = {

    var lb = new ListBuffer[Map[String, String]]();

    for (file <- new java.io.File("C:\\data\\mult-examples").listFiles) {
      val wc: Map[String, ListBuffer[String]] = runWordCount(clean.scalacode.ProdConfig.numberWordsPerLine, clean.scalacode.ProdConfig.getLines(file.getPath), clean.scalacode.ProdConfig.charsToClean, clean.scalacode.ProdConfig.validWord)

      //Should not need to access individual counts by accessing map , as validating lines should ensure just valid data is returned.
      //val countedLines: ListBuffer[String] = wc.get("one").get
      //countedLines.foreach(println)

      //TODO - Perhaps output filename also
      println("In next iteration");
      val toBeJson = wc.map(f => (f._1, f._2.size + "," + getDateFromFileName(file.getName)));

      lb = lb += wc.map(f => (f._1, f._2.size + "," + getDateFromFileName(file.getName)));

    }

    val mergedList = mergeMap(lb)

    mergedList
    mergedList.foreach(p => println(p._1 +"->"+p._2))

  }
  
  
    def mergeMap(mapList: ListBuffer[Map[String, String]]) = {
      val map = new scala.collection.mutable.HashMap[String, ListBuffer[String]]()
      mapList.map(m => {

        m.map(m2 => {
          if (map.contains(m2._1)) {
            val lb = map.get(m2._1).get
            lb += m2._2
          } else {
            map.put(m2._1, ListBuffer(m2._2))
          }
        })

      })
      
      map;
    }

  /**
   * For each vaid word that matches a new map entry will be created
   */
  private def runTest = {

    // GenerateLines.generate(400000, "c:\\data\\example.txt")

    val wc = runWordCount(clean.scalacode.TestConfig.numberWordsPerLine, clean.scalacode.TestConfig.lines, clean.scalacode.TestConfig.charsToClean, clean.scalacode.TestConfig.validWord)
    val countedLines: ListBuffer[String] = wc.get("valid1").get
    val countedLines2: ListBuffer[String] = wc.get("valid2").get

    println("Printing keys,sizes");
    wc.foreach(f => println(f._1 + "," + f._2.size));

    if (!countedLines.mkString(",").equals("100,1,1,0,1,200,1,1,1,0")) {
      throw new Exception("Test Failed")
    } else {
      println("Test passed.")
    }

    if (!countedLines2.mkString(",").equals("300,1,1,1")) {
      throw new Exception("Test Failed")
    } else {
      println("Test passed.")
    }

  }

  private def runWordCount(numberWordsPerLine: Int, lines: List[String], charsToClean: Set[Char], validWord: List[String]) = {
    val cleanData = new ValidateData(numberWordsPerLine, lines).getCleanData(charsToClean, validWord)

    val cd = cleanData._1.mapValues { x =>
      new clean.scalacode.Parse(x).getWordCountWithLabels
    }

    cd
  }

}

object ProdConfig {
  val numberWordsPerLine = 8;

  def getLines(fileName: String) = {
    scala.io.Source.fromFile(fileName).getLines.toList
  }

  val lines: List[String] = scala.io.Source.fromFile("c:\\data\\example.txt").getLines.toList
  val charsToClean: Set[Char] = Set('\"', '[', ']', ''')
  val validWord: List[String] = List("one", "two", "three", "four", "five", "here", "line5", "valid1", "valid2", "six");

}
object TestConfig {

  val lines: List[String] = List("100 valid1 big xtest2", "200 valid1 xtest big", "300 valid2 test big")

  //Lines which do not contain this amount of works will be filtered out
  val numberWordsPerLine = 4;

  //These characters will be removed from lines
  val charsToClean: Set[Char] = Set('\"', '[', ']', ''')

  //Size of this List should = number of entries in Map; The List of items in each Map is sorted by name
  //Lines which do not contain at least one validWord will be filtered out
  val validWord: List[String] = List("valid1", "valid2");
}