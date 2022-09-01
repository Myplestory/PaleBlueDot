package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("test 1 - countries that have proper capitalization") {
    val testCases: Map[String, Double] = Map(
      "Andorra" -> 8409.5,
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  test("test 2 - countries that have wrong capitalization") {
    val testCases: Map[String, Double] = Map(
      "AnDoRra" -> 8409.5,
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  test("test 3 - not real countries/not in data file") {
    val testCases: Map[String, Double] = Map(
      "" -> 0.0,
      "And orra" -> 0.0,
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
}
