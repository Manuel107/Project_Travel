object Cities {
  /*
  Here we have the representation of the map with a List of List[(String, Int, Int)]
   */
  val cities: List[List[(String, Int, Int)]] =
    List(
    List(("Madrid", 0, 0), ("London", 47, -12), ("Paris", 52, -12), ("Rome", 35, -15)),
    List(("London", 0, 0), ("Madrid", 0, -12), ("Paris", 52, -6), ("Berlin", 18, -17), ("Moscow", 50, -39)),
    List(("Paris", 0, 0), ("Madrid", 0, -12), ("London", 47, -6), ("Berlin", 15, -18)),
    List(("Berlin", 0, 0), ("London", 47, -17), ("Paris", 52, -15), ("Rome", 35, -7), ("Warsaw", 22, -12)),
    List(("Rome", 0, 0), ("Madrid", 0, -15), ("Berlin", 7, -18), ("Bucharest", 11, -15)),
    List(("Warsaw", 0, 0), ("Berlin", 18, -12), ("Kyiv", 9, -5), ("Moscow", 50, -15)),
    List(("Bucharest", 0, 0), ("Rome", 35, 15), ("Kyiv", 9, -5)),
    List(("Kyiv", 0, 0), ("Moscow", 50, -12), ("Warsaw", 22, -5), ("Bucharest", 11, -5)),
    List(("Moscow", 0, 0), ("London", 47, -39), ("Warsaw", 22, -15), ("Kyiv", 9, -12))
    )

}
