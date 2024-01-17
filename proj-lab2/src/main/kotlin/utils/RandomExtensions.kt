package utils

import model.IWorldMap

fun IWorldMap.randomPosition(mapSize: Vector2d): Vector2d{
    var emptyPos: ArrayList<Vector2d> = ArrayList()
    for (i in 0 until (mapSize.x)){
        for (j in 0 until (mapSize.y)){
            emptyPos.add(Vector2d(i,j))
        }
    }
    return emptyPos.random()
}

fun IWorldMap.randomFreePosition(mapSize: Vector2d): Vector2d? {
    var emptyPos: ArrayList<Vector2d> = ArrayList()
    for (i in 0 until (mapSize.x)){
        for (j in 0 until (mapSize.y))
        {
            if (!this.isOccupied(Vector2d(i,j))) {
                emptyPos.add(Vector2d(i, j))
            }
        }
    }
    return emptyPos.randomOrNull()
}