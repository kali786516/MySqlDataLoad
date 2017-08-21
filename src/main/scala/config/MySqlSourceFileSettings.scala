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

  }

}
