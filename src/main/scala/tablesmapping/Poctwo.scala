package tablesmapping

/**
  * Created by kalit_000 on 8/26/17.
  */
import java.util.Properties

import config.MySqlSourceFileSettings
import domain.DimAccountNames
import org.apache.spark.SparkConf
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark._
import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}
import org.apache.spark.sql.types._
import org.apache.spark.SparkContext
import utils.DBUtils

object Poctwo {

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


    import org.apache.spark.sql.Row

    val dim_account_source_file=MySqlSourceFileSettings.SourceFiles
    val sourcefile=dim_account_source_file.dimaccount_source_file

    val csvSplit = "accountkey,parentaccountkey,accountcodealternatekey,parentaaccountcodealternatekey,accountdescription,accounttype,operator,custommembers,valuetype,custommemberoptions"
    val schema = StructType(csvSplit.split(",").map(fieldName ⇒ StructField(fieldName, StringType, true)))


    val csvSplittwo = "accountkeythree|int,parentaccountkey|int,accountcodealternatekey|int,parentaaccountcodealternatekey|int,accountdescription|String,accounttype|String," +
      "operator|String,custommembers|String,valuetype|String,custommemberoptions|String"

    val dbschmea=spark.read.format("jdbc").options(
      Map(
        "url" -> DBUtils.mysqlurl,
        "dbtable" -> "DimAccount",
        "user" -> DBUtils.mysql_username,
        "password" -> DBUtils.mysql_password,
        "driver" -> "com.mysql.jdbc.Driver",
        "fetchSize" -> "10000"
      )
    ).load().schema

    println(dbschmea)

   //
    // df.show(10,truncate = false)



    //val rows = df.select("defectDescription").collect().map(_.getString(0)).mkString(" ")

    def getdatatype(dbcolumndatatype:String):Array[StructField] ={
      return dbcolumndatatype.split(",").map(x => x.split("\\|").toList.toArray).map(x => StructField(x(0),x(1) match {
                                                                                               case "string" => StringType
                                                                                               case "int" => IntegerType
                                                                                               case "Timestamp" => TimestampType
                                                                                               case _ => StringType}))

    }


   //https://stackoverflow.com/questions/35990117/spark-dataframe-not-respecting-schema-and-considering-everything-as-string
    val test=getdatatype(csvSplittwo)

    val schematest=StructType(getdatatype(csvSplittwo))

    println(schematest)

   // val schematwo = StructType(csvSplit.split(",").map(x => x.split("|")).map(fieldName ⇒ StructField(fieldName(0), StringType, true)))

    //println(schema)


    spark.read.schema(schematest).option("header","false")
      .option("inferSchema","true")
      .option("delimiter","|")
      .csv(sourcefile).show(10)

    val fileschema=spark.read.option("header","false").option("delimiter","|").csv(sourcefile).schema

    println(fileschema)




    /*
    val df =sqlContext.read.format("com.databricks.spark.csv")
            .option("header","false")
            .option("inferSchema", "false")
            .schema(csvSchema)
            .load("/Users/kalit_000/Downloads/AdventureWorksSQLDW2012/DimAccount.txt")

    df.show(10,truncate = false)

    sc.stop()
    */



  }

}
