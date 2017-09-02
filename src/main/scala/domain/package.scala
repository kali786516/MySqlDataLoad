import utils.DBUtils

/**
  * Created by kalit_000 on 8/20/17.
  */
package object domain {
  case class DimAccountClass(accountkey:Int,
                                                                          parentaccountkey:Int,
                                                                          accountcodealternatekey:Int,
                                                                          parentaaccountcodealternatekey:Int,
                                                                          accountdescription:String,
                                                                          accounttype:String,
                                                                          operator:String,
                                                                          custommembers:String,
                                                                          valuetype:String,
                                                                          custommemberoptions:String)
  val DimAccountNames=Seq("accountkey","parentaccountkey","accountcodealternatekey","parentaaccountcodealternatekey","accountdescription","accounttype","operator","custommembers"
    ,"valuetype","custommemberoptions")





}
