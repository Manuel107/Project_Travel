

//TRAVEL PROBLEM
object TravelEurope extends App {
  //First of all we are going to write the cities as List[List[(String,Int,Int)]] so that we can use
  // the List predefined functions.
  val cities: List[List[(String, Int, Int)]] = List(
    List(("Madrid", 0, 0), ("London", 47, -12), ("Paris", 52, -12), ("Rome", 35, -15)),
    List(("London", 0, 0), ("Madrid", 0, -12), ("Paris", 52, -6), ("Berlin", 18, -17), ("Moscow", 50, -39)),
    List(("Paris", 0, 0), ("Madrid", 0, -12), ("London", 47, -6), ("Berlin", 15, -18)),
    List(("Berlin", 0, 0), ("London", 47, -17), ("Paris", 52, -15), ("Berlin", 35, -7), ("Warsaw", 22, -12)),
    List(("Rome", 0, 0), ("Madrid", 0, -15), ("Berlin", 7, -18), ("Bucharest", 11, -15)),
    List(("Warsaw", 0, 0), ("Berlin", 18, -12), ("Kyiv", 9, -5), ("Moscow", 50, -15)),
    List(("Bucharest", 0, 0), ("Rome", 35, 15), ("Kyiv", 9, -5)),
    List(("Kyiv", 0, 0), ("Moscow", 50, -12), ("Warsaw", 22, -5), ("Bucharest", 11, -5)),
    List(("Moscow", 0, 0), ("London", 47, -39), ("Warsaw", 22, -15), ("Kyiv", 9, -12))
  )

  //Now "advance" function in which situation we will have the score and the transportation cost depending
  // on the cities already visited

  def advance(visited: List[String], destiny: List[(String, Int, Int)], from: String, to: String): (String, Int, Int) = {
    val namedestiny = destiny.map(m => m._1)
    require(namedestiny.contains(to))
    val p = namedestiny.indexOf(to)
    if (visited.contains(to)) {

      (to, 0, destiny(p)._3)
    }
    else {
      (to, destiny(p)._2, destiny(p)._3)
    }
  }

  println(advance(List[String]("Madrid", "London", "Rome"), List[(String, Int, Int)](("Madrid", 0, -15), ("Berlin", 7, -18)), "Rome", "Berlin"))

  //Next function returns the set of possible cities that we can visit with the computation of the points

  def step(thecities: List[List[(String, Int, Int)]], xs: List[String], n: Int): List[(List[String], Int)] = {
    val lastcity = xs.last
    val position = thecities.map(m => m.head._1).indexOf(lastcity)
    val posibilities = thecities(position).tail
    var zs = List[(List[String], Int)]()
    for (x <- posibilities) {
      var union = xs ++ List[String](x._1)
      var (s, p, q) = advance(xs, posibilities, lastcity, x._1)
      var m = n + p + q
      zs = zs ++ List[(List[String], Int)]((union, m))
    }
    zs
  }

  //Some examples of how functions works

  println(step(cities, List[String]("Madrid"), 0))
  //Now we can define a function with all possibly trips starting and ending at Madrid

  def travel(thecities: List[List[(String, Int, Int)]], visited: List[String], m: Int): List[(List[String], Int)] = {
    var zs = List[(List[String], Int)]((List("Madrid"), 0))
    var cs = List[(List[String], Int)]()
    var c = m
    while (!(c == 0)) {
      for (x <- zs) {
        cs = cs ++ step(thecities, x._1, x._2)
      }
      zs = cs
      c = c - 1
    }
    cs.filter(m => m._1.head == m._1.last)

  }

  println(travel(cities, List[String]("Madrid"), 7))
  //Filtering we can select the best one

  def bestTravel(thecities: List[List[(String, Int, Int)]]): (List[String], Int) = {
    val xs = travel(thecities, List[String]("Madrid"), 7)
    //Select the highest score
    val maxi = xs.map(m => m._2).max
    val ys = xs.filter(m => m._2 == maxi)
    //Select the lower number of Travel
    val min = ys.map(m => m._1.length).min
    val zs = ys.filter(m => m._1.length == min)
    zs.head

  }

  println(bestTravel(cities))


}


