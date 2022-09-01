package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task3 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("test 1 - Country names are properly capitalized") {
    val testCases: Map[String, Double] = Map(
      "Andorra" ->
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map = PaleBlueDot.cityPopulations(countriesFile,citiesFilename,input,regionCode)
      assert(computedOutput == expectedOutput,input + " -> " + computedOutput)
    }
  }


}
