package evolution.simulator
import java.io.File
import kotlin.reflect.full.memberProperties

class CSVSaver(private var delim: String) {
    
    private var file: File? = null

    fun setFile(new_file: File) {
        file = new_file
        val topRow: String = SimulationStatistics::class.memberProperties.map{it.name}.joinToString(delim) + "\n"
        file!!.writeText(topRow)
    }

    fun write(statistics: SimulationStatistics) {
        file!!.appendText(statistics.toCSV(delim) + "\n")
    }

    fun hasFile(): Boolean {
        return file != null
    }
}