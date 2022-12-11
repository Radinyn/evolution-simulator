package evolution.simulator

enum class MapType {
    /* kula ziemska - lewa i prawa krawędź mapy zapętlają się
        (jeżeli zwierzak wyjdzie za lewą krawędź, to pojawi się po prawej stronie - a jeżeli za prawą, to po lewej);
        górna i dolna krawędź mapy to bieguny - nie można tam wejść (jeżeli zwierzak próbuje wyjść poza te krawędzie mapy,
        to pozostaje na polu na którym był, a jego kierunek zmienia się na odwrotny). */
    GLOBE,
    /*  piekielny portal - jeżeli zwierzak wyjdzie poza krawędź mapy,
        to trafia do magicznego portalujego energia zmniejsza się o pewną wartość (taką samą jak w przypadku generacji potomka),
        a następnie jest teleportowany w nowe, losowe miejsce na mapie. */
    PORTAL
}

enum class GrowthType {
    /*  zalesione równiki - preferowany przez rośliny jest poziomy pas pól w centralnej części mapy (udający równik i okolice) */
    EQUATOR,
    /*  toksyczne trupy - rośliny preferują te pola, na których zwierzęta umierają najrzadziej - rosną na tych polach,
        na których najmniej zwierząt skończyło swój żywot w trakcie symulacji. */
    CORPSES
}

enum class MutationType {
    /*  pełna losowość - mutacja zmienia gen na dowolny inny gen */
    RANDOM,
    /*  lekka korekta - mutacja zmienia gen o 1 w górę lub w dół (np. gen 3 może zostać zamieniony na 2 lub 4, a gen 0 na 1 lub 7) */
    CORRECTION
}

enum class AnimalBehavior {
    /*  pełna predestynacja - zwierzak zawsze wykonuje kolejno geny, jeden po drugim */
    STRICT,
    /*  nieco szaleństwa - w 80% przypadków zwierzak po wykonaniu genu aktywuje gen następujący zaraz po nim,
    w 20% przypadków przeskakuje jednak do innego, losowego genu */
    RANDOM
}   

data class SimulationParameters(
    val height: Int,                    // wysokość mapy,
    val width: Int,                     // szerokość mapy,
    val mapType: MapType,               // wariant mapy
    val initPlantsNum: Int,             // startowa liczba roślin
    val plantEnergy: Int,               // energia zapewniana przez zjedzenie jednej rośliny
    val plantGrowthRate: Int,           // liczba roślin wyrastająca każdego dnia
    val plantGrowthType: GrowthType,    // wariant wzrostu roślin
    val initAnimalNum: Int,             // startowa liczba zwierzaków
    val initAnimalEnergy: Int,          // startowa energia zwierzaków
    val stuffedThreshold: Int,          // energia konieczna, by uznać zwierzaka za najedzonego (i gotowego do rozmnażania)
    val reproductionCost: Int,          // energia rodziców zużywana by stworzyć potomka
    val mutationMinNum: Int,            // minimalna liczba mutacji u potomków (może być równa 0)
    val mutationMaxNum: Int,            // maksymalna liczba mutacji u potomków
    val mutationType: MutationType,     // wariant mutacji
    val genomeLength: Int,              // długość genomu zwierzaków
    val animalBehavior: AnimalBehavior, // wariant zachowania zwierzaków 
)

/*
Dzień 1:
1 Usuwamy nieżywe zwirzątka
2 genotyp aktywujemy pierwszy gen i każde zwierzątko porusza się według niego
3 konsekwencje ruchów
 - może wejśc na planta i zjada go (obsługa tego zdarzenia)
 - jak juz wszyskich obsłużymy wchodzi druga faza czyli rozmnażanie (kazdy jeden dzień to generacja)
 - jeśli dwa zeirzątka to romnażają się bierzemy energie z rodziców i tworzymy niowe
    - dwa zwierzątka mają swoje genotypy (1 5 7 0 0) (2 3 1 0)
    - działą zasada zachowania energi suma energi po operacji musi być taka sama
    - stowrzenei dziacka kosztuje ileś energii PARAMETR Z1 - PARAMETR Z2 - PARAMETR Z3(dziecko) = 2*PARAMETR
    - krzyżowanie opis w skrypcie
    - na tymsamym polu pojawi się dziecko
4 Roślny wzrost roślin
 */

/*
warianty:

 */