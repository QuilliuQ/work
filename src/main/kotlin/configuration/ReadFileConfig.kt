package configuration

import bet.mgs.platform.*
import org.slf4j.LoggerFactory
import java.io.File

class ReadFileConfig {
    companion object{
        private var FILE_PATH = ""
        var TIME_OUT: Long = 120_000
        private val LOG = LoggerFactory.getLogger(this::class.java)
    }
    fun readConfig(args:Array<String>){
        val filePath = when {
            (args.isNotEmpty() && args[0].length > 1) -> args[0]
            System.getProperty("os.name").lowercase().startsWith("windows") -> getCurrentFilePath() + "tables.conf"
            else -> "/etc/tables.conf"
        }
        LOG.info("config file path $filePath load config")
        for (lns in File(filePath).readLines()) {
            readFile(lns)
        }
        if (FILE_PATH.isEmpty()){
            LOG.info("get backup file path")
            try {
                var file = File(".")
                LOG.info("create file ${file.absolutePath}")
                val absPath = file.absolutePath
                file = File(absPath.substring(0, absPath.length - 1) + "backup")
                LOG.info("check folder ${file.exists()}")
                if (!file.exists()) {
                    LOG.info("create folder ${file.absolutePath}")
                    file.mkdir()
                }
                FILE_PATH = file.absolutePath
                LOG.info("backup File Path $FILE_PATH")
            }catch (e : Exception){
                LOG.error("error while create backup file", e)
                e.printStackTrace()
            }
        }
        LOG.info("try start application")
    }

    private fun getCurrentFilePath(): String{
        val absPath = File(".").absolutePath
        return absPath.substring(0, absPath.length - 1)
    }

    private fun readFile(line:String){
        LOG.info(line)
        if (line.length > 3 && line.first() != '#') {
            val params = line.split(" ")
            if (params.size > 1) {
                when (params[0]) {
                    "server_address" -> SERVICE_MANAGEMENT_ADDRESS = params[1].replace(" ", "")
                    "server_port" -> SERVICE_MANAGEMENT_PORT = params[1].replace(" ", "").toInt()
                    "auth_key" -> SERVICE_KEY = params[1].replace(" ", "")
                    "debug" -> DEBUG = params[1].replace(" ", "").toBoolean()
                    "file_path" -> FILE_PATH = params[1].replace(" ", "")
                    "debug_level" -> LEVEL = params[1].replace(" ", "").toInt()
                    "neon_timeout" -> TIME_OUT = params[1].replace(" ", "").toLong() * 1000
//                    "socket_port" -> SOCKET_PORT = params[1].replace(" ", "").toInt()
//                    "win_count" -> WIN_COUNT = params[1].replace(" ", "").toInt()
//                    "debug_tables" -> IS_DEBUG_MODE = params[1].replace(" ", "").toInt() == 1
                }
            }
        }
    }
}