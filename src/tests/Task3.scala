package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task3 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  // RETURNS MAP FOR ALL CITIES IN A COUNTRY

  test("test 1 - Country names are properly capitalized") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "Afghanistan" -> Map("asadabad" -> 48400, "asmar" -> 15708),
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile,citiesFilename,input,"34")
      assert(computedOutput == expectedOutput,input + " -> " + computedOutput)
    }
  }

  test("test 2 - Country names are incorrectly capitalized") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "AfGHANIstan" -> Map("asadabad" -> 48400, "asmar" -> 15708),
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "34")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  test("test 3 - Incorrect/nonexistent countries") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "AfGH ANIstan" -> Map()
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "34")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  test("test 4 - Incorrect/nonexistent region code") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "Afghanistan" -> Map()
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "ge")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }

  // RETURN LIST OF CITY NAMES IN GIVEN COUNTRY ABOVE AVG POPULATION

  test("test 5(task3pt2) - Correct country") {
    val testCases: Map[String, List[String]] = Map(
      "Andorra" -> List("les escaldes")
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }

  test("test 6(task3pt2) - wrong capitalization country country") {
    val testCases: Map[String, List[String]] = Map(
      "AndoRRa" -> List("les escaldes")
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  test("test 7(task3pt2) - Incorrect country") {
    val testCases: Map[String, List[String]] = Map(
      "And orr a" -> List()
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }


}
