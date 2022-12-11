package evolution.simulator

abstract class AbstractMapEntity {
    private var position: Vector2d
        get() = field

    constructor(position: Vector2d) {
        this.position = position
    }

}