package evolution.simulator

data class AnimalStatistics (
    //  ile ma energii,
    val animalEnergy: Int,

    //  ile zjadł roślin,
    val plantCount: Int,

    //  ile posiada dzieci,
    val offspringCount: Int,

    //  jaki ma genom,
    val animalGenome: Genome?,

    //  która jego część jest aktywowana,
    val currentGeneIndex: Int,

    //  ile dni już żyje (jeżeli żyje),
    val currentAge: Int,

    //  którego dnia zmarło (jeżeli żywot już skończyło).
    val deathDay: ULong?
) {
    constructor() : this(
        0,0,0, null,0,0,null
    )
}