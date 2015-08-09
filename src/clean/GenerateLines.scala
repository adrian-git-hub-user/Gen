package clean

class GenerateLines(numberElements: Int) {

  def getLines: List[String] = {
    val selection: List[String] = "one two three   four five six".split("\\s+").toList;

    def getRand = {
      scala.util.Random.shuffle(selection).head
    }

    val getRandomElements = (for (a <- 1 to numberElements)
      yield (a + " " + getRand + " " + " " + getRand + " " + getRand + " " + getRand + " " + getRand + " " + getRand + " " + getRand)).toList

    getRandomElements
  }
}
  object GenerateLines {
    import java.io._

    def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
      val p = new java.io.PrintWriter(f)
      try { op(p) } finally { p.close() }
    }

    def main(args: Array[String]) {

      printToFile(new File("c:\\data\\example.txt")) { p =>
        new GenerateLines(400000).getLines.foreach(p.println)
      }
      
      println("Done")

    }
  }
