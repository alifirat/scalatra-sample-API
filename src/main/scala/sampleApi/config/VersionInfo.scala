 package sampleApi.config

import com.typesafe.config.ConfigFactory

 /**
 * Load version info from version.conf
 * If that is not available, just return blank strings for everything
 */
 object VersionInfo {

   val config = {
     try {
        ConfigFactory.load("version.conf")
     }
     catch {
       case ex:Exception => null
     }
   }

   val name = loadConfig("build.name")
   val version = loadConfig("build.version")
   val lastBuilt = loadConfig("build.lastBuilt")

   private[this] def loadConfig(property:String) : String = {

     try {
       return config.getString(property)

     }
     catch
       {
         case ex:Exception => return "Error loading version information"
       }
   }

 }
        