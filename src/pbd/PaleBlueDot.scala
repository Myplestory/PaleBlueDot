package pbd


import java.awt.Desktop
import java.net.URI
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.{BufferedSource, Source}
import scala.math._

object PaleBlueDot {


  /**
   * Task 1
   *
   * Given a country name using a mix of case (upper/lower), return the country code in all lowercase letters
   *
   * Ex. If "Heard Island and McDonald Islands#HM" is a line countriesFilename and the countryName input
   * of your method is "hEaRd IsLaNd AnD mCdOnAlD iSlAnDs" the returned value is "hm"
   *
   * If countryName is not in the file, return the empty String: ""
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param countryName       The name of the country to lookup in the file with any mix of upper/lower-case
   * @return The two letter country code for countryName in lowercase letters
   */
  def getCountryCode(countriesFilename: String, countryName: String): String = {
    val countriesFile: BufferedSource = Source.fromFile(countriesFilename)
    val countryform: String = countryName.toLowerCase()
    var holder: String = ""
    for (line <- countriesFile.getLines){
      val lowercc: String = line.toLowerCase().dropRight(3)
      if (lowercc == countryform){
        holder = line.toLowerCase().takeRight(2)
      }
    }
    holder
  }


  /**
   * Task 2
   *
   * Find the average population of cities in a country
   * regardless.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return The average population of cities in the given country
   */

  def averagePopulation(countriesFilename: String, citiesFilename: String, countryName: String): Double = {
    val codec: String = getCountryCode(countriesFilename, countryName)
    val countrycities: BufferedSource = Source.fromFile(citiesFilename)
    var poplist: List[Double] = List()
    for (line <- countrycities.getLines) {
      val splitt = line.split(',')
      if (splitt(0) == codec) {
        poplist = poplist :+ splitt(3).toDouble
      }
    }
    poplist.foldLeft(0.0)(_+_) / poplist.length.toDouble
  }

  /**
   * Task 3
   */

  /**
   * Returns a Map[cityName -> population] for all cities in the given county. The name of each
   * city should match exactly how it appears in citiesFilename and the population is read from the file
   * and converted to an Int. The country name may contain any mix of upper/lower-case letters.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @param regionCode        Two digit region code with case matching the case from the cities file
   * @return A Map containing the name and population of every city in the given country
   */
  def cityPopulations(countriesFilename: String, citiesFilename: String, countryName: String, regionCode: String): Map[String, Int] = {
    val codec2: String = getCountryCode(countriesFilename, countryName)
    val citylist: BufferedSource = Source.fromFile(citiesFilename)
    var citymap = Map.empty[String, Int]
    for (line <- citylist.getLines) {
      val split = line.split(',')
      if (split(0) == codec2) {
        if (split(2) == regionCode) {
          citymap += (split(1) -> split(3).toInt)
        }
      }
    }
    if (citymap.isEmpty) {
      println("No such city in " + countryName + " with the area code " + regionCode)
      citymap
    }
    else {
      citymap
    }
  }


  /**
   * Returns a List of city names in the given county and with above average population for that country
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return All city names in given country with a population > the average populations of cities in that country
   */
  def aboveAverageCities(countriesFilename: String, citiesFilename: String, countryName: String): List[String] = {
    var citylist: List[String] = List()
    val avgpop: Double = averagePopulation(countriesFilename: String, citiesFilename: String, countryName: String)
    val grabbedcode: String = getCountryCode(countriesFilename, countryName)
    val cityreader: BufferedSource = Source.fromFile(citiesFilename)
    for (line <- cityreader.getLines) {
      val linessplit = line.split(',')
      if (linessplit(0) == grabbedcode) {
        if(linessplit(3).toDouble > avgpop) {
          val matchingcity: String =  linessplit(1)
          citylist = citylist :+ matchingcity
        }
      }
    }
    citylist
  }


  /**
   * Application Objective
   *
   * You find yourself stranded in an unfamiliar place with no signs of civilization. You don't have much with you,
   * but you do have a locator that gives your current latitude/longitude, a csv file of cities, and your final
   * submission to the PaleBlueDot assignment from CSE116 (What luck!). You decide that finding and walking
   * directly to the closest city will give you the best chance to survive.
   *
   * Return the closest city to the given location in terms of greater circle distance which is the shortest distance
   * needed to walk along the surface of the Earth to reach a city.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param location       A location on Earth given as a List containing latitude and longitude coordinates
   * @return The city closest to the given location as a List containing country code, city name, and region
   *         exactly as they appear in the cities file (ie. the List should have exactly 3 values to return
   *         a single city
   */

  def closestCity(citiesFilename: String, location: List[Double]): List[String] = {
    val openedcityfile: BufferedSource = Source.fromFile(citiesFilename)
    // Holder variable (Checking for distance)
    var holder1: List[Double] = List()
    var holder2: List[String] = List()
    for (line <- openedcityfile.getLines.drop(1)) {
      val linessplit = line.split(',')
      val lat2: Double = linessplit(4).toDouble
      val lon2: Double = linessplit(5).toDouble
      val checker: Double = GreaterCircle(location.head, lat2, location.last, lon2)
      if (holder1.isEmpty){
        holder2 = holder2 :+ linessplit(0)
        holder2 = holder2 :+ linessplit(1)
        holder2 = holder2 :+ linessplit(2)
        holder1 = holder1 :+ checker
      }
      else {
        if (checker < holder1.head) {
          holder2 = holder2.updated(0,linessplit(0))
          holder2 = holder2.updated(1,linessplit(1))
          holder2 = holder2.updated(2,linessplit(2))
          holder1 = holder1.updated(0,checker)
        }
        else{
        }
      }
    }
    holder2
  }
  // Greater circle method
  def GreaterCircle(lat1: Double, lat2: Double, lon1: Double, lon2: Double): Double = {
    val r = 6371000.0
    val phi1: Double = (lat1 * Math.PI) / 180.0
    val phi2: Double = (lat2 * Math.PI) / 180.0
    val deltaphi: Double = (lat2 - lat1) * Math.PI / 180
    val deltalambda: Double = (lon2 - lon1) * Math.PI / 180
    val aa = Math.sin(deltaphi / 2) * Math.sin(deltaphi / 2) +
      Math.cos(phi1) * Math.cos(phi2) *
        Math.sin(deltalambda / 2) * Math.sin(deltalambda / 2)
    val cc = 2 * Math.atan2(Math.sqrt(aa), Math.sqrt(1 - aa))
    r * cc
  }

  /**
   * Helper Method
   *
   * Opens Google Maps at a specific location. The location is a List containing the latitude then longitude as Doubles
   *
   * @param location The location to open in the format List(Latitude, Longitude)
   */
  def openMap(location: List[Double]): Unit = {
    if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
      val url: String = "http://maps.google.com/maps?t=m&q=loc:" + location.head.toString + "+" + location(1).toString
      Desktop.getDesktop.browse(new URI(url))
    } else {
      println("Opening the browser not supported")
    }
  }


  def main(args: Array[String]): Unit = {
    openMap(List(43.002743, -78.7874136))
  }

}