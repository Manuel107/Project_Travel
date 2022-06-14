import Cities.cities

object Travel extends App with MoveFunctions {

  /*
  We have declared in trait how to make all possible combinations.
  Now let's take the best one!
   */
  def bestTravel(maxNumberDays: Int): (List[String], Int) = {
    val xs = travel(maxNumberDays)
    //Select the highest score
    val maxScore = xs.map(m => m._2).max
    //Select all paths with the highest score
    val pathMaxScore = xs.filter(m => m._2 == maxScore)
    //Select the lower number of Travel with the highest score
    val minTravels = pathMaxScore.map(m => m._1.length).min
    //Finally! lets take those travels
    val zs = pathMaxScore.filter(m => m._1.length == minTravels)
    //If there are more than one, lets just take the first one
    zs.head

  }
  println(bestTravel(7))

  /*
  The best travel is (for 7 days):
  (List(Madrid, Paris, London, Moscow, Kyiv, Bucharest, Rome, Madrid),130)
   */

}
