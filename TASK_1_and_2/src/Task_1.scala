import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
object Task_1{



  def main(args:Array[String]) {
    //Punkt 1
    val days: List[String] = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    var pointOneResult: String = ""

    //1a
    for (day <- days)
      pointOneResult = pointOneResult + day + ",";

    pointOneResult = pointOneResult.dropRight(1);
    println("1a: " + pointOneResult)

    //1b
    pointOneResult = ""
    for (day <- days) {
      if (day.startsWith("P"))
        pointOneResult = pointOneResult + day + ",";
    }

    pointOneResult = pointOneResult.dropRight(1);
    println("1b: " + pointOneResult)

    //1c
    var i = 0
    pointOneResult = ""
    while (i < days.size) {
      pointOneResult = pointOneResult + days(i) + ",";
      i += 1
    }

    pointOneResult = pointOneResult.dropRight(1);
    println("1c: " + pointOneResult)


    //Punkt 2

    //2a
   // var pointTwoResult = stringConnectStartToEnd(days, 0)
   // println("2a: " + pointTwoResult)

    var pointTwoResult = stringConnectStartToEnd2(days)
    pointTwoResult = pointTwoResult.dropRight(1);
    println("2a: " + pointTwoResult)

    //2b
    //pointTwoResult = stringConnectEndToStart(days, days.size - 1)
    //println("2b: " + pointTwoResult)

    pointTwoResult = stringConnectEndToStart2(days)
    pointTwoResult = pointTwoResult.dropRight(1);
    println("2b: " + pointTwoResult)

    //Punkt 3
    var pointThreeResult = stringTailConnectStartToEnd(days)
    pointThreeResult = pointThreeResult.dropRight(1);
    println("3: " + pointThreeResult)

    //Punkt 4
    //4a
    var pointFourResult = days.foldLeft("")((m, n) => m + "," +n)
    pointFourResult = pointFourResult.drop(1);
    println("4a: " + pointFourResult)

    //4b
    pointFourResult = days.foldRight("")((m, n) => m + "," +n)
    pointFourResult = pointFourResult.dropRight(1)
    println("4b: " + pointFourResult)

    //4c
    pointFourResult = days.foldLeft("")((m, n) => if(n.startsWith("P"))m + "," +n else m)
    pointFourResult = pointFourResult.drop(1);
    println("4c: " + pointFourResult)

    //Punkt 5
    val products = Map("Śmietana" -> 1.99,"Mleko" -> 2.29,"Serek Wiejski" -> 1.69, "Monte" -> 2.49 )
    val discountedProducts = products map (n => n._1 -> n._2 * 0.9 )
    println("5: " +discountedProducts)

    //Punkt 6
    val tuple = (1, "TEST", products)
    printThreeValues(tuple);

    //Punkt 7
    var key = "Śmietana";
    var pointSevenResult = products.get(key)
    if(pointSevenResult.isDefined)
      println("7: " + key + " kosztuje "+ pointSevenResult.get)
    else
      println("7: " + key + " nie znajduje sie w asortymencie")

     key = "Maślanka"
     pointSevenResult = products.get(key)
    if(pointSevenResult.isDefined)
      println("7: " + key + " kosztuje "+ pointSevenResult.get)
    else
      println("7: " + key + " nie znajduje sie w asortymencie")

    //Punkt 8
    val listOfNumbers: ListBuffer[Int] =  ListBuffer(1,2,0,0,3,0,4,5,0)
    var pointEightResult = removeZeros(listOfNumbers,listOfNumbers.size -1)
    println("8: " + pointEightResult)

    //Punkt 9
    val pointNineResult = IncrementValues(listOfNumbers)
    println("9: " + pointNineResult)

   // Punkt 10
    val listOfRealNumbers :List[Int] =  List(-6,-7, -4, -5,1,12,14,5)
    val pointTenResult =filterValues(listOfRealNumbers);
    println("10: " + pointTenResult)
  }

  def filterValues(numbers: List[Int]): List[Int] ={
    numbers filter (x => (-5<=x && x<=12)) map (x => x.abs)
 }

  def IncrementValues(numbers : ListBuffer[Int]): ListBuffer[Int] ={
    numbers map (n => n+1)
  }

  def removeZeros(numbers : ListBuffer[Int], n:Int): ListBuffer[Int] ={
    if(n == -1)  numbers
    else if(numbers(n) == 0) {
      numbers.remove(n);
      removeZeros( numbers, n-1)
    }else
      removeZeros(numbers, n - 1)
  }

  def printThreeValues(tuple: (Any, Any, Any)): Unit = {
    println("6: 1)" + tuple._1 + " 2)"+ tuple._2 +" 3)"+ tuple._3 )
  }

  def stringTailConnectStartToEnd (days: List[String]): String = {
    @tailrec
    def iter(days: List[String], resultString: String, n: Int): String = {
      if (n == days.size) resultString: String
      else iter(days, days(n) + "," + resultString, n + 1)
    }
    iter(days, "", 0)
  }

  def stringConnectStartToEnd(days: List[String], n:Int): String=
  {
    if(n == days.size -1 ) days(n)
    else  days(n) + "," + stringConnectStartToEnd(days,  n +1)
  }

  def stringConnectEndToStart(days: List[String], n:Int): String=
  {
    if(n == 0) days(n)
    else  days(n) + "," + stringConnectEndToStart(days,  n -1)
  }

  def stringConnectStartToEnd2(days: List[String]): String=
  {
    if(days.isEmpty)
      ""
    else  days.head + "," + stringConnectStartToEnd2(days.tail)
  }

  def stringConnectEndToStart2(days: List[String]): String=
  {
    if(days.isEmpty)
      ""
    else  days.last + "," + stringConnectEndToStart2(days.dropRight(1))
  }
}

