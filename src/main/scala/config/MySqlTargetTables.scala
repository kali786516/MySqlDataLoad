package config

import com.typesafe.config.ConfigFactory
import config.MySqlSettings.config
import _root_.config.MySqlSettings.MySqlConnection.mysqlconnectionvalues

/**
  * Created by kalit_000 on 9/2/17.
  */
object MySqlTargetTables {
  private val config=ConfigFactory.load()

  object MySqlTargetTables {
    private val mysqlconnectionvalues=config.getConfig("mysqltargettables")

    lazy val dimaccount_targettable=mysqlconnectionvalues.getString("dimaccount_targettable")
    lazy val dimcurrency_targettable=mysqlconnectionvalues.getString("dimcurrency_targettable")
    lazy val dimcustomer_targettable=mysqlconnectionvalues.getString("dimcustomer_targettable")
    lazy val dimdate_targettable=mysqlconnectionvalues.getString("dimdate_targettable")
    lazy val dimdepartmentgroup_targettable=mysqlconnectionvalues.getString("dimdepartmentgroup_targettable")
    lazy val dimemployee_targettable=mysqlconnectionvalues.getString("dimemployee_targettable")
    lazy val dimgeography_targettable=mysqlconnectionvalues.getString("dimgeography_targettable")
    lazy val dimorganization_targettable=mysqlconnectionvalues.getString("dimorganization_targettable")
    lazy val dimproduct_targettable=mysqlconnectionvalues.getString("dimproduct_targettable")
    lazy val dimproductcategory_targettable=mysqlconnectionvalues.getString("dimproductcategory_targettable")
    lazy val dimproductsubcategory_targettable=mysqlconnectionvalues.getString("dimproductsubcategory_targettable")
    lazy val dimpromotion_targettable=mysqlconnectionvalues.getString("dimpromotion_targettable")
    lazy val dimreseller_targettable=mysqlconnectionvalues.getString("dimreseller_targettable")
    lazy val dimsalesreason_targettable=mysqlconnectionvalues.getString("dimsalesreason_targettable")
    lazy val dimsalesterritoy_targettable=mysqlconnectionvalues.getString("dimsalesterritoy_targettable")
    lazy val dimsscenario_targettable=mysqlconnectionvalues.getString("dimsscenario_targettable")
    lazy val factcallcenter_targettable=mysqlconnectionvalues.getString("factcallcenter_targettable")
    lazy val factcurrencyrate_targettable=mysqlconnectionvalues.getString("factcurrencyrate_targettable")
    lazy val factfinance_targettable=mysqlconnectionvalues.getString("factfinance_targettable")
    lazy val factinternetsales_targettable=mysqlconnectionvalues.getString("factinternetsales_targettable")
    lazy val factsalesquota_targettable=mysqlconnectionvalues.getString("factsalesquota_targettable")
    lazy val factsurveyresponse_targettable=mysqlconnectionvalues.getString("factsurveyresponse_targettable")
    lazy val factwholesale_targettable=mysqlconnectionvalues.getString("factwholesale_targettable")
    lazy val prospectivebuyer_targettable=mysqlconnectionvalues.getString("prospectivebuyer_targettable")


  }




  }
