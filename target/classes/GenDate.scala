import java.text._
import java.util._

object GenDate extends App {

  /*'[ { "date": "2014-1-01", "in": "30", "out": "21" }, { "date": "2014-1-02", "in": "35", "out": "32" }, { "date": "2014-1-03", "in": "23", "out": "34" }, { "date": "2014-1-04", "in": "20", "out": "15" }, { "date": "2014-1-05", "in": "34", "out": "20" }, { "date": "2014-1-06", "in": "22", "out": "34" }, {   } ]';
  */

  var dateJson = "[ { \"date\": \"2014-1-01\", \"in\": \"30\", \"out\": \"21\" }"
  dateJson += "]"

  val sDate = "2015-1-1" //yyyy-MM-dd
 // val eDate = "2015-6-1"

  val format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
  var date = format.parse(sDate);

  val c = Calendar.getInstance();
    
  for (a <- 1 to 150) {
    c.setTime(date);
    c.add(Calendar.DATE, 1); // number of days to add
    
    date = c.getTime
    val dt = format.format(date); // dt is now the new date
    println("{\"date\":\"" + dt + "\",\"in\":\"" + getRand() + "\",\"out\":\"" + getRand() + "\"}")
    println(",")
  }

  def getRand() = {

    val rand = new Random();

    val randomNum = rand.nextInt((20 - 10) + 1) + 10;

    randomNum;
  }

}