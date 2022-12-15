package evolution.simulator

data class Statistics(
    val numOfIterations: ULong,
    val animalCount: UInt,
    val plantCount: UInt,
    val emptyTiles: UInt,
    val bestGenome: Genome,
    val averageEnergy: Double,
    val averageLifespan: Double, 
)

// // liczby wszystkich zwierząt,
// // liczby wszystkich roślin,
// // liczby wolnych pól,
// // najpopularniejszych genotypów,
// // średniego poziomu energii dla żyjących zwierząt,
// // średniej długości życia zwierząt dla martwych zwierząt (wartość uwzględnia wszystkie nieżyjące zwierzęta - od początku symulacji),
