package tests

import org.scalatest._
import pbd.PaleBlueDot

class ApplicationObjective extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("test 1 - returns list"){
    val testCases: Map[List[Double],List[String]] = Map(
      List(42.541924,1.513953) -> List("ad","la massana","04"),
      List(42.612046,-83.128178) -> List("us","troy","MI"),
      List(-18.403488,31.418752) -> List("zw","marondera","04"),
      List(84.716508,12.354632) -> List("sj","ny-alesund","00"),
      List(90.0000,135.0000) -> List("sj","ny-alesund","00"))
      for ((input, expectedOutput) <- testCases) {
        val computedOutput = PaleBlueDot.closestCity(citiesFilename, input): List[String]
        assert(computedOutput == expectedOutput)
      }
  }
}
