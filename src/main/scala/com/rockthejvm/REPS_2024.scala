/*
We downloaded 3 different datasets for HydroPowerGeneration, WindPowerGeneration and SolarPowerGeneration from Fingrid. The file is in JSON format
*/

package com.rockthejvm

import scala.io.Source
import java.io.{BufferedWriter,FileWriter,PrintWriter}
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import org.json4s._
import org.json4s.native.JsonMethods._

//First defining a case class to represent renewable energy data
case class RenewableEnergyData(datasetId: Int, startTime: String, endTime: String, value: Double)
object REPS_2024 extends App{

  /*
  Use case 1: The system should be designed to monitor and control the power plant's renewable
  energy sources, including solar panels, wind turbines, and hydropower.
  */

  /*
  Use case 2: The system should be capable of collecting data related to the energy generated by
  renewable sources and storing it in a file.
  */

  //For this use case, Users are asked information about energy data and data are
  //stored in respective files.
  val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
  def collectEnergyValue(source: String):Unit = {
    println("Enter datasetId:")
    val datasetId = scala.io.StdIn.readLine()

    println("Enter start time (YYYY-MM-DDTHH:MM:SS):")
    val startTime = LocalDateTime.parse(scala.io.StdIn.readLine(), formatter)

    println("Enter end time (YYYY-MM-DDTHH:MM:SS):")
    val endTime = LocalDateTime.parse(scala.io.StdIn.readLine(), formatter)

    println("Enter the value:")
    val value = scala.io.StdIn.readDouble()

    // Creating the energy data in text format
    val data = s"datasetId: $datasetId,startTime: $startTime,endTime: $endTime,value: $value"

    // Writing the data to the corresponding files
    val writer = new BufferedWriter(new FileWriter(s"${source}Energy.txt",true))
    writer.write(data)
    writer.newLine()
    writer.close()

    println(s"Energy information collected and stored in ${source}Energy.txt")
  }

  //Asking user for energy related information
  var repeat = true
  while (repeat) {
    println("Do you want to add wind energy, solar energy, or hydro energy? (Type s for solar, w for wind, h for hydro, or e to exit):")
    val sourceChoice = scala.io.StdIn.readLine()

    // Process user's choice and call collectEnergy function accordingly
    sourceChoice match {
      case "s" => collectEnergyValue("Solar")
      case "w" => collectEnergyValue("Wind")
      case "h" => collectEnergyValue("Hydro")
      case "e" => repeat = false // Exit the loop if 'e' is entered
      case _ => println("Invalid choice.")
    }
  }

  /*
  Use case 3: The system should provide a view of the power plant's energy generation and storage
  capacity, allowing operators to adjust the power plant's operation as needed. This
  view should show the data stored in the file.
  */

  /*
  Use case 4: The system should be able to analyse the data collected from renewable sources. It
  should be possible to filter the data on an hourly, daily, weekly and monthly basis. It
  should be possible to sort the data where possible. It must allow a user to search for
  required data stored in the system.
  Data Analysis should include: Mean, Median, Mode, Range, and Midrange
  */

  /*
  Use case 5:  The system should be able to detect and handle issues with renewable energy sources,
  such as low energy output, and equipment malfunction, generating alerts for the
  operators accordingly
   */

}
