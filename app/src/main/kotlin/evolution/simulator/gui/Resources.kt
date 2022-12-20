package evolution.simulator.gui

import javafx.scene.image.Image
import java.io.FileInputStream

class Resources {
    val plantImage = Image(FileInputStream("src/main/resources/plant.png"))
    val plantImageWidth = 40.0
    val plantImageHeight = 40.0

    val animalImage = Image(FileInputStream("src/main/resources/animal.png"))
    val animalImageWidth = 40.0
    val animalImageHeight = 40.0
}