package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("test 1 - Int Division") {
    val testCases: List[String] = List("Aruba", "Andorra", "Japan")
    for (test <- testCases) {
      assert(PaleBlueDot.averagePopulation(countriesFile, citiesFilename, test).isInstanceOf[Double])
    }
  }

  test("test 2 - Double tolerance / Off by 1") {
    val epsilon: Double = 0.1
    val testCases: Map[String, Double] = Map(
      "Andorra" -> 8409.5,
      "Japan" -> 134539.08
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(Math.abs(computedOutput - expectedOutput) < epsilon)
    }
  }

  test("test 3 - one city in the country / requires exact case") {
    val epsilon: Double = 1.0
    val testCases: Map[String, Double] = Map(
      "Aruba" -> 29998.0,
      "Andorra" -> 8409.5,
      "ArUbA" -> 29998.0,
      "AnDOrRa" -> 8409.5
    )
    for ((input, expectedOutput) <- testCases) {
      val computedOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert(Math.abs(computedOutput - expectedOutput) < epsilon)
    }
  }
}








