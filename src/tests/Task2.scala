package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("test 1 - one city in the country") {
    val testCases: Map[String, Double] = Map(
      "Aruba" -> 29998
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }

  // have to create epsilon for double tolerance
  test("test 2 - Double tolerance / Off by 1") {
    val epsilon: Double = 0.1
    val testCases: Map[String, Double] = Map(
      "Andorra" -> 8409.51
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(Math.abs(computedOutput - expectedOutput) < epsilon)
    }
  }

  test("test 3 - Requires exact capitalization") {
    val testCases: Map[String, Double] = Map(
      "Andorra" -> 8409.5,
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
  test("test 3 - Requires exact capitalization") {
    val testCases: Map[String, Double] = Map(
      "Andorra" -> 8409.5,
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }

  // Tests if returned value is result of integer division
  test("test 3 - Int Division") {
    val testCases: Map[String, Int] = Map(
      "Andorra" -> 8409,
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(computedOutput == expectedOutput, input + " -> " + computedOutput)
    }
  }
}




