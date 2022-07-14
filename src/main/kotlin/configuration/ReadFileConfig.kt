package configuration

import bet.mgs.platform.*
import org.slf4j.LoggerFactory
import java.io.File

class ReadFileConfig {

    private var FILE_PATH = ""
    var TIME_OUT: Long = 120_000
    private val LOG = LoggerFactory.getLogger(this::class.java)
    fun readConfig(args:Array<String>){
        val filePath = when {
            (args.isNotEmpty() && args[0].length > 1) -> args[0]
            System.getProperty("os.name").lowercase().startsWith("windows") -> getCurrentFilePath() + "tables.conf"
            else -> "/etc/tables.conf"
        }
        LOG.info("config file path $filePath load config")
        if (File(filePath).exists()){
            for (lns in File(filePath).readLines()) {
                readLine(lns)
            }
        }
        else{
            SERVICE_MANAGEMENT_ADDRESS = "spinwin.mgs.bet"
            SERVICE_MANAGEMENT_PORT = 8000
            SERVICE_KEY = "73a7670592134d4dbf361ed2c41e6cfb"
            LOG.info("File not Exists")
        }

        LOG.info("try start application")
    }

    private fun getCurrentFilePath(): String{
        val absPath = File(".").absolutePath
        return absPath.substring(0, absPath.length - 1)
    }

    private fun readLine(line:String){
        LOG.info(line)
        if (line.first() != '#') {
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
            else
            {
                LOG.error("line $line incorrect")
            }
        }
    }
}