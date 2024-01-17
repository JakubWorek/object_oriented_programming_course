package utils

import kotlin.math.max
import kotlin.math.min

class Vector2d(val x: Int, val y: Int) {

    override fun toString(): String {
        return String.format("%d, %d", x, y)
    }

    fun precedes (other: Vector2d ): Boolean {
        return x <= other.x && y <= other.y
    }

    fun follows (other: Vector2d): Boolean{
        return x >= other.x && y >= other.y
    }

    operator fun compareTo(other: Vector2d): Int {
        if (x <= other.x && y <= other.y){
            return -1
        }
        else if(x >= other.x && y >= other.y){
            return 1
        }
        else{
            return 0
        }
    }


    fun add (other: Vector2d): Vector2d{
        return Vector2d(x + other.x, y + other.y)
    }

    operator fun plus(other: Vector2d):Vector2d{
        return Vector2d(x + other.x, y + other.y)
    }

    fun subtract(other: Vector2d): Vector2d {
        return Vector2d(x - other.x, y - other.y)
    }

    operator fun minus (other: Vector2d):Vector2d{
        return Vector2d(x - other.x, y - other.y)
    }

    fun upperRight(other: Vector2d): Vector2d {
        return Vector2d(max(x, other.x), max(y, other.y))
    }

    fun lowerLeft(other: Vector2d): Vector2d {
        return Vector2d(min(x, other.x), min(y, other.y))
    }

    fun opposite():Vector2d {
        return Vector2d(-x, -y)
    }


    @JvmName("getX1")
    fun getX():Int {
        return x;
    }

    @JvmName("getY1")
    fun getY():Int {
        return y;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector2d

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.WEST -> Vector2d(-1, 0)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.EAST -> Vector2d(1, 0)
    }
}