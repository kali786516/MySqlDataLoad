package tablesmapping

/**
  * Created by kalit_000 on 8/26/17.
  */
import java.util.Properties

import com.datastax.driver.core.Row
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType
import config._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import domain._
import org.apache.calcite.avatica.ColumnMetaData.StructType
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.types._
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql._

import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.apache.spark.sql.types._
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import utils.DBUtils



object Poc {

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

    val dim_account_source_file=MySqlSourceFileSettings.SourceFiles
    val sourcefile=dim_account_source_file.dimaccount_source_file

    spark.read.format("jdbc").options(
      Map(
        "url" -> DBUtils.mysqlurl,
        "dbtable" -> "DimAccount",
        "user" -> DBUtils.mysql_username,
        "password" -> DBUtils.mysql_password,
        "driver" -> "com.mysql.jdbc.Driver",
        "fetchSize" -> "10000"
      )
    ).load().printSchema()



      //.as[DimAccountClass]

    /*
    import org.apache.spark.sql.Row
    val headerDescs : String = "Name,Age,Location"
    val headerSchema = StructType(headerDescs.split(",").map(fieldName => StructField(fieldName, StringType, true)))

    val headerRDD = sc.parallelize(Seq(headerDescs.split(","))).map(x=>Row(x(0),x(1),x(2)))

    val headerSchema = StructType(headerDescs.split(",").map(fieldName => StructField(fieldName, StringType, true)))


    val headerRDD = sc.parallelize(Seq(headerDescs.split(","))).map(x=>Row(x(0),x(1),x(2)))

    val headerDf = sqlContext.createDataFrame(headerRDD, headerSchema)


     headerDf.printSchema

    val vRDD = sc.textFile("..**filepath**.").map(_.split(",")).map(a => Row.fromSeq(a))

    val headerDf = sqlContext.createDataFrame(vRDD , headerSchema)

    val df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true") // Use first line of all files as header
      .option("inferSchema", "true") // Automatically infer data types
      .load("cars.csv")
    */













  }

}
