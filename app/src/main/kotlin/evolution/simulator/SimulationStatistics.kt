package evolution.simulator

import kotlin.reflect.full.memberProperties

data class SimulationStatistics(
    // ilość iteracji symulacji
    var numOfIterations: ULong,

    // liczba wszystkich zwierząt,
    var animalCount: UInt,

    // liczba wszystkich roślin,
    var plantCount: UInt,

    // liczba wolnych pól,
    var emptyTiles: UInt,

    // najpopularniejszych genotypów,
    var bestGenome: List<Genome>,

    // średniego poziomu energii dla żyjących zwierząt,
    var averageEnergy: Double,

    // średniej długości życia zwierząt dla martwych zwierząt (wartość uwzględnia wszystkie nieżyjące zwierzęta - od początku symulacji),
    var averageLifespan: Double,
) {
    constructor() : this(
        0u,0u,0u,0u, listOf(),0.0,0.0,
    )

    fun toCSV(delim: String): String {
        return SimulationStatistics::class.memberProperties.map{it.get(this).toString()}.joinToString(delim)
    }
}
