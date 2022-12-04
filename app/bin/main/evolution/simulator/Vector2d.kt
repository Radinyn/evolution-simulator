package evolution.simulator

class Vector2d {
    var x: Int;
    var y: Int;

    constructor(x:Int, y:Int) {
        this.x = x;
        this.y = y;
    }

    operator fun plus(other: Vector2d) {
        this.x += other.x;
        this.y += other.y;
    }

    operator fun minus(other: Vector2d) {
        this.x -= other.x;
        this.y -= other.y;
    }
}