import Cities.cities

import scala.annotation.tailrec

trait MoveFunctions {
  /*
  Given a city and a list of cities, returns the position of the city
   */
  def mapIndexof[A](xs: List[List[(A, Int, Int)]], n: A): Int = xs.map(x => x.head._1).indexOf(n)
  /*
  Given a city and a list of cities, returns if the list contains (or not) the city
   */
  def mapContains[A](xs: List[(A, Int, Int)], n: A): Boolean = xs.map(x => x._1).contains(n)
  /*
  Given a list of visited cities and different destinations, return (if the city to go to is on the destiny)
  the cityToGo and the money earned.
   */
  def advance(visited: List[String], destinies: List[(String, Int, Int)], citytoGo: String): (String, Int, Int) = {
    require(mapContains(destinies,citytoGo))
    val p = destinies.map(m => m._1).indexOf(citytoGo)

    if(visited.contains(citytoGo)) (citytoGo,0,destinies(p)._3)
    else (citytoGo,destinies(p)._2,destinies(p)._3)
  }
  /*
  Given all the cities, a list of cities and the money (after have taken a step). It returns the different combinations for
  connect the last city to the rest of the city with the calculation of the money earned (advance).
  e.g
  If we have in our computation ('Madrid','London',0)
  --> it will return
    (Madrid, London, Madrid),-12), (Madrid, London, Paris),46), (Madrid, London, Berlin),1), (Madrid, London, Moscow),11)
   */
  def step(xs: List[String], n: Int): List[(List[String], Int)] = {
    val position = mapIndexof(cities,xs.last)

    val posibilities = cities(position).tail

    def aux(ys: List[(String,Int,Int)]): List[(List[String],Int)] = {
      ys match {
        case l::ls =>
          val union = xs ++ List[String](l._1)
          val (_,p,q) = advance(xs,posibilities,l._1)
          List[(List[String],Int)]((union,n+p+q)) ++ aux(ls)

        case _ => List()
      }
    }
    aux(posibilities)
  }
  /*
  This recursive function will return next level on the depth-first-search algorithm
  */
  def nextSearch(ps: List[(List[String],Int)]): List[(List[String],Int)] = {
    if(ps.nonEmpty){
      val h = ps.head
      step(h._1,h._2) ++ nextSearch(ps.tail)
    }
    else List()
  }
  /*
  With this, we will calculate our function acc. As many times as parameters of days we have indicated
   */
  @tailrec
  final def searchManyDays(ps: List[(List[String],Int)], n:Int): List[(List[String],Int)] = {
    if(n==0) ps
    else searchManyDays(nextSearch(ps),n-1)
  }
  /*
  Given the list of the cities, money to start and a number of days to travel. It will return all the possible combinations starting in madrid.
  At the end it will select those which start and end in the same city.
   */
  def travel(maxnumberDays: Int): List[(List[String], Int)] = {
    val startCity = List[(List[String], Int)]((List("Madrid"), 0))
    val combinations = searchManyDays(startCity,maxnumberDays)

    combinations.filter(m => m._1.head == m._1.last)
  }
  }