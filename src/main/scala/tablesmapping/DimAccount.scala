package tablesmapping

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


/**
  * Created by kalit_000 on 8/20/17.
  */

object DimAccount {

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

   val ds=spark.read
     .option("header","false")
     .option("inferSchema","true")
     .option("delimiter","|")
     .csv(sourcefile)
     .toDF(DimAccountNames:_*)
     .as[DimAccountClass]

    ds.show(10)

    val prop=new java.util.Properties()
    prop.setProperty("driver",DBUtils.mysqldriver)
    prop.setProperty("user",DBUtils.mysql_username)
    prop.setProperty("password",DBUtils.mysql_password)

    ds.write.mode("overwrite").jdbc(DBUtils.mysqlurl,"DimAccount",prop)


  }

}
