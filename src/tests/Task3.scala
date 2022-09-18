package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task3 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  // RETURNS MAP FOR ALL CITIES IN A COUNTRY

  test("test 1 - Country names are properly capitalized / map") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "United Arab Emirates" -> Map("abu dhabi" -> 603687)
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "01")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }

  test("test 2 - Wrong area code") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "United Arab Emirates" -> Map()
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "011")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }

  test("test 3 - Only one city in country to map") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "Aruba" -> Map("oranjestad" -> 29998)
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "00")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  test("test 3.5 - Multiple cities mapped") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "Antigua and Barbuda" -> Map("all saints" -> 2526, "liberta" -> 1668, "swetes" -> 727)
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "06")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }

  test("test 4 - Incorrect/nonexistent countries") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "AfGH ANIstan" -> Map()
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "34")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  // RETURNS LIST FOR ALL CITIES IN A COUNTRY GREATER THAN AVG

  test("test 5 - Returns list of greater than avg pop") {
    val testCases: Map[String, List[String]] = Map(
      "Aruba" -> List()
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
}
