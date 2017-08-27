package tablesmapping

/**
  * Created by kalit_000 on 8/21/17.
  */
import java.util.Properties

import com.datastax.driver.core.Row
import config._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import domain._
import org.apache.calcite.avatica.ColumnMetaData.StructType
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Dataset, Encoders, SaveMode, SparkSession}
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.types.StructType
import utils._
import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox
import org.springframework.context.support.ClassPathXmlApplicationContext


object FileToDB {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.WARN)
    Logger.getLogger("akka").setLevel(Level.WARN)


    val spark =SparkSession.builder
      .master("local[*]")
      .appName("Fraud Detector")
      .config("spark.driver.memory","2g")
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._

    //val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()


  }


}
