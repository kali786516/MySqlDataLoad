package tablesmapping

/**
  * Created by kalit_000 on 8/21/17.
  */
import java.util.Properties

import com.datastax.driver.core.Row
import com.typesafe.config.ConfigFactory
import config._
import config.MySqlSourceFileSettings.config
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
import scala.collection.JavaConversions._
import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox
import org.springframework.context.support.ClassPathXmlApplicationContext
import utils.SparkUtils


object FileToDB {

  def main(args: Array[String]): Unit = {

    val sourcefile=args(0).toString
    val targetable=args(1).toString

    println(s"source file :- ${sourcefile}")
    println(s"target table :- ${targetable}")

    Logger.getLogger("org").setLevel(Level.WARN)
    Logger.getLogger("akka").setLevel(Level.WARN)


    val spark =SparkSession.builder
      .master("local[*]")
      .appName(targetable)
      .config("spark.driver.memory","2g")
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._


    /*
    val config = ConfigFactory.load()
    val sourceFiles = config.getConfig("sourcefiles")
    val TargetTables=config.getConfig("mysqltargettables")
    val sourceFileMap=sourceFiles.entrySet().toList.map(x => (x.getKey,x.getValue)).toMap
    val targetTableMap=TargetTables.entrySet().toList.map(x => (x.getKey,x.getValue)).toMap

    val file=sourceFileMap.get("dimaccount_sourcefile").toString().mkString("").replaceAll("Some","")
    val targetable=targetTableMap.get("dimaccount_targettable").toString().mkString("")

    println(s"file :- ${file)}")
    println(s"target table :-${targetable}")*/

    val prop=new java.util.Properties()
    prop.setProperty("driver",DBUtils.mysqldriver)
    prop.setProperty("user",DBUtils.mysql_username)
    prop.setProperty("password",DBUtils.mysql_password)


    load_table(sourcefile,targetable)


    def load_table(sourcefile:String,targetable:String)={

      val prop=new java.util.Properties()
      prop.setProperty("driver",DBUtils.mysqldriver)
      prop.setProperty("user",DBUtils.mysql_username)
      prop.setProperty("password",DBUtils.mysql_password)

      println(s"Processing Source File:-${sourcefile}")
      println(s"Writing to Target Table:-${targetable}")

      val dbschmea=spark.read.format("jdbc").options(
        Map(
          "url" -> DBUtils.mysqlurl,
          "dbtable" -> targetable,
          "user" -> DBUtils.mysql_username,
          "password" -> DBUtils.mysql_password,
          "driver" -> "com.mysql.jdbc.Driver",
          "fetchSize" -> "10000"
        )
      ).load().schema

      println(s"Got table schema for table :- ${targetable} schema :- ${dbschmea}")

      val df=spark.read.schema(dbschmea).option("header","false").option("mode", "DROPMALFORMED")
        .option("inferSchema","true")
        .option("delimiter","|")
        .csv(sourcefile)

      df.show(10,truncate = false)

      df.write.mode("overwrite").jdbc(DBUtils.mysqlurl,targetable,prop)

    }


  }


}
