package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task3 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

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
      "AfGH ANIstan" -> Map(),
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "34")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  test("test 4 - Incorrect/nonexistent region code") {
    val testCases: Map[String, Map[String, Int]] = Map(
      "Afghanistan" -> Map(),
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, "ge")
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }


}
