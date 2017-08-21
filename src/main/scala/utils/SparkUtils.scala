package utils

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

/**
  * Created by kalit_000 on 8/20/17.
  */
object SparkUtils {

  def getSparkContext(appName:String)={

    Logger.getLogger("org").setLevel(Level.WARN)
    Logger.getLogger("akka").setLevel(Level.WARN)


    val spark =SparkSession.builder
      .master("local[*]")
      .appName(appName)
      .config("spark.driver.memory","2g")
      .enableHiveSupport()
      .getOrCreate()

   // import spark.implicits._

  }


}
