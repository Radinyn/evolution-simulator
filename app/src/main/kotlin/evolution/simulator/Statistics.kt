package evolution.simulator

data class Statistics(
<<<<<<< HEAD
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
=======
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
>>>>>>> radinyn/dec15
