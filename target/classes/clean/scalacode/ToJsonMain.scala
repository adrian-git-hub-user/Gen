package clean.scalacode

import scala.collection.mutable.ListBuffer
import scala.collection.JavaConverters._;
import scala.collection.immutable.Map
import scala.collection.immutable.List

class ToJsonMain

object ToJsonMain {

  val wc: scala.collection.mutable.Map[String, ListBuffer[String]] = {

    var lb = new ListBuffer[Map[String, String]]();

    for (file <- new java.io.File("C:\\data\\mult-examples").listFiles.filter(f => f.isFile)) {
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

    mergedList.foreach(p => println(p._1+","+p._2.mkString(",")))
    val mutableMap = scala.collection.mutable.Map() ++ mergedList
    mutableMap
  }

  def getDateFromFileName(fileName: String) = {
    fileName.substring(fileName.length() - 14, fileName.length()-4)
  }

  def mergeMap(mapList: ListBuffer[Map[String, String]]) = {
    val map : scala.collection.mutable.Map[String, ListBuffer[String]] = new scala.collection.mutable.HashMap[String, ListBuffer[String]]()
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

  private def runWordCount(numberWordsPerLine: Int, lines: List[String], charsToClean: Set[Char], validWord: List[String]) = {
    val cleanData = new ValidateData(numberWordsPerLine, lines).getCleanData(charsToClean, validWord)

    val cd = cleanData._1.mapValues { x =>
      new clean.scalacode.Parse(x).getWordCountWithLabels
    }

    cd
  }

  def getAsJava: java.util.Map[String, java.util.List[String]] = {
    val vvv = wc.map(m => m._1 -> m._2.asJava)
    return vvv.asJava
  }

}

