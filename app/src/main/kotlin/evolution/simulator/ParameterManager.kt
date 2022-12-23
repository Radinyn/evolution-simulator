package evolution.simulator
import java.io.File
import com.google.gson.Gson;

class ParameterManager {
    private val gson = Gson()
    
    fun save(file: File, params: SimulationParameters) {
        file.writeText(gson.toJson(params))
    }

    fun load(file: File): SimulationParameters {
        return gson.fromJson(file.readText(), SimulationParameters::class.java)
    }
}