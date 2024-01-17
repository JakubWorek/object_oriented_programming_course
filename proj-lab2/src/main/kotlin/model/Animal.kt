package model

import utils.MapDirection
import utils.MoveDirection
import utils.Vector2d
import utils.toUnitVector

class Animal (var position: Vector2d, var direction: MapDirection, private var map: IWorldMap) {
    fun isAt(positionToCheck: Vector2d?): Boolean {
        return position == positionToCheck
    }

    fun move(direction: MoveDirection) {
        when (direction) {
            MoveDirection.RIGHT -> this.direction = this.direction.next()
            MoveDirection.LEFT -> this.direction = this.direction.previous()
            else -> {
                var directionVector: Vector2d = this.direction.toUnitVector()
                if (direction === MoveDirection.BACKWARD) {
                    directionVector = directionVector.opposite()
                }
                val newLocation: Vector2d = position.add(directionVector)
                if (map.canMoveTo(newLocation)) {
                    position = newLocation
                }
            }
        }
    }

    override fun toString(): String = when (direction) {
        MapDirection.EAST -> "E"
        MapDirection.WEST -> "W"
        MapDirection.NORTH -> "N"
        MapDirection.SOUTH -> "S"
        else -> ""
    }
}