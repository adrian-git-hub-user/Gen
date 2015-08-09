package clean

class Parse(lines: List[String], identifier: Int) {

  //TODO write script to validate file being parsed is correct

  //TODO Amend to set element in list that willbe used as identifier
  //val lines: List[String] = List("100 this is line one", "200 this is line 2", "300 this is line three")

  private def wordCount = {
    val identifierList = lines.map { m => m.split("\\s+")(identifier) }
    val linesExcludingIdentifier = lines.map { line => line.split("\\s+").drop(identifier + 1).mkString(" ") }
    val linesConcat: String = linesExcludingIdentifier.mkString(" ")
    val allDistinctWords = linesConcat.split("\\s+").distinct.toList
    val wordsCount: List[(String, Int)] = linesConcat.split("\\s+").groupBy(identity).toList.map(p => (p._1, +p._2.size))
    val countedWords: List[List[(String, Int)]] = linesExcludingIdentifier.map(m => getWordCount(m))
    val missingWords: List[List[String]] = countedWords.map(m => m.map(m => m._1))
    val wordsWith0 = missingWords.map(m => getWordsNotInList(allDistinctWords, m)).map(m => (m.map(m2 => (m2, 0))))
    val mergedList: List[List[(String, Int)]] = countedWords.zip(wordsWith0).map(m => (m._1 ++ m._2).sortBy(_._1))
    identifierList.zip(mergedList)
  }

  def getWordCountWithLabels = {

    val countedLines: List[(String, List[(String, Int)])] = wordCount
    println("countedLines finished")
    val converted: List[(String, List[Int])] = countedLines.map(m => (m._1, m._2.map(m2 => m2._2)))
    var lb = new scala.collection.mutable.ListBuffer[String]
    converted.foreach(c => {
      lb += (c._1 + " " + c._2.mkString(" "));
    })

    lb
  }

  private def getWordCount(words: List[String]) = {
    words.groupBy(identity).toList.map(p => (p._1, +p._2.size))
  }

  private def getWordCount(words: String) = {
    words.split("\\s+").groupBy(identity).toList.map(p => (p._1, +p._2.size))
  }

  private def getAllDistinctWords(lines: List[String]) = {
    lines.foldRight("")((a, b) => a + " " + b).split("\\s+").distinct
  }

  private def getWordsNotInList(allWords: List[String], wordsSubSet: List[String]) = {
    allWords.diff(wordsSubSet)
  }

}