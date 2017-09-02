package config
import com.typesafe.config.ConfigFactory

/**
  * Created by kalit_000 on 8/20/17.
  */
object MySqlSourceFileSettings {
  private val config=ConfigFactory.load()

  object SourceFiles {
    private val sourceFilepath=config.getConfig("sourcefiles")

    lazy val dimaccount_source_file=sourceFilepath.getString("dimaccount_sourcefile")
    lazy val dimcurrency_sourcefile=sourceFilepath.getString("dimcurrency_sourcefile")
    lazy val dimcustomer_sourcefile=sourceFilepath.getString("dimcustomer_sourcefile")
    lazy val dimdate_sourcefile=sourceFilepath.getString("dimdate_sourcefile")
    lazy val dimdepartmentgroup_sourcefile=sourceFilepath.getString("dimdepartmentgroup_sourcefile")
    lazy val dimemployee_sourcefile=sourceFilepath.getString("dimemployee_sourcefile")
    lazy val dimgeography_sourcefile=sourceFilepath.getString("dimgeography_sourcefile")
    lazy val dimgorganization_sourcefile=sourceFilepath.getString("dimgorganization_sourcefile")
    lazy val dimproduct_sourcefile=sourceFilepath.getString("dimproduct_sourcefile")
    lazy val dimproductcategory_sourcefile=sourceFilepath.getString("dimproductcategory_sourcefile")
    lazy val dimproductsubcategory_sourcefile=sourceFilepath.getString("dimproductsubcategory_sourcefile")
    lazy val dimpromotion_sourcefile=sourceFilepath.getString("dimpromotion_sourcefile")
    lazy val dimreseller_sourcefile=sourceFilepath.getString("dimreseller_sourcefile")
    lazy val dimsalesreason_sourcefile=sourceFilepath.getString("dimsalesreason_sourcefile")
    lazy val dimsalesterritory_sourcefile=sourceFilepath.getString("dimsalesterritory_sourcefile")
    lazy val dimsscenario_sourcefile=sourceFilepath.getString("dimsscenario_sourcefile")
    lazy val factcallcenter_sourcefile=sourceFilepath.getString("factcallcenter_sourcefile")
    lazy val factcurrencyrate_sourcefile=sourceFilepath.getString("factcurrencyrate_sourcefile")
    lazy val factfinance_sourcefile=sourceFilepath.getString("factfinance_sourcefile")
    lazy val factinternetsales_sourcefile=sourceFilepath.getString("factinternetsales_sourcefile")
    lazy val factsalesquota_sourcefile=sourceFilepath.getString("factsalesquota_sourcefile")
    lazy val factsurveyresponse_sourcefile=sourceFilepath.getString("factsurveyresponse_sourcefile")
    lazy val factwholesale_sourcefile=sourceFilepath.getString("factwholesale_sourcefile")
    lazy val prospectivebuyer_sourcefile=sourceFilepath.getString("prospectivebuyer_sourcefile")

    //val sourceFileList=config.getConfig("sourcefiles")


  }

}
