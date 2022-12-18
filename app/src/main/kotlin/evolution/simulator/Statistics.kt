package evolution.simulator

data class Statistics(
    // ilość iteracji symulacji
    val numOfIterations: ULong,

    // liczba wszystkich zwierząt,
    val animalCount: UInt,

    // liczba wszystkich roślin,
    val plantCount: UInt,

    // liczba wolnych pól,
    val emptyTiles: UInt,

    // najpopularniejszych genotypów,
    val bestGenome: Genome,

    // średniego poziomu energii dla żyjących zwierząt,
    val averageEnergy: Double,

    // średniej długości życia zwierząt dla martwych zwierząt (wartość uwzględnia wszystkie nieżyjące zwierzęta - od początku symulacji),
    val averageLifespan: Double,
)
