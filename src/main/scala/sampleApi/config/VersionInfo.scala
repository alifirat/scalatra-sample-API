package sampleApi.config

import com.typesafe.config.ConfigFactory

 /**
 * Load version info from version.conf
 * If that is not available, just return blank strings for everything
 */
 object VersionInfo {

   val config = {
     try {
        Some(ConfigFactory.load("version.conf"))
     }
     catch {
       case ex:Exception => None
     }
   }

   val name = loadConfigValue("build.name")
   val version = loadConfigValue("build.version")
   val lastBuilt = loadConfigValue("build.lastBuilt")

   private[this] def loadConfigValue(property:String) : String = {
     try {
       config match {
         case Some(c) => c.getString(property)
         case None => "Error loading version information"
       }
     }
     catch
       {
         case ex:Exception => "Error loading version information"
       }
   }
 }
