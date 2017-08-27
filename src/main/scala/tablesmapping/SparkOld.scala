package tablesmapping

import org.apache.spark.SparkConf
import org.apache.log4j.Logger
import org.apache.log4j.Level
import java.sql._

import org.apache.spark._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, TimestampType}
import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}
import org.apache.spark.sql.types._
import org.apache.spark.SparkContext

/**
  * Created by kalit_000 on 8/27/17.
  */
object SparkOld extends App {




    Logger.getLogger("org").setLevel(Level.WARN)
    Logger.getLogger("akka").setLevel(Level.WARN)

    val conf = new SparkConf().setMaster("local[2]").setAppName("DatabaseSelectExample")
    val sc = new SparkContext(conf)

    implicit val sqlContext = new SQLContext(sc)


    import org.apache.spark.sql.functions._
    import sqlContext.implicits._

    import org.apache.spark.sql.Row

    val csvSplittwo = "accountkey|string,parentaccountkey|int"

    // sc.parallelize(csvSplittwo.split(",").mkString("").split("|").map(x => x(0))).foreach(println)

     val testtwo=sc.parallelize(csvSplittwo.split(","))

    println(csvSplittwo.split(",").map(x => x.split("\\|")))




    def getdatatype(dbcolumndatatype:String):Seq[StructField] ={
      return dbcolumndatatype.split(",").map(x => x.split("\\|")).map(x => StructField(x(0),x(1) match {
        case "string" => StringType
        case "int" => IntegerType
        case "Timestamp" => TimestampType
        case _ => StringType}))

    }

  def getdatatypetwo(dbcolumndatatype:RDD[String]):RDD[StructField] ={
       dbcolumndatatype.map(x => x.split("|")).map(x => StructField(x(0),x(1) match {
      case "string" => StringType
      case "int" => IntegerType
      case "Timestamp" => TimestampType
      case _ => StringType}))

  }


      val test=getdatatypetwo(testtwo)

      val schematest=StructType(getdatatype(csvSplittwo))

      println(schematest)









}
