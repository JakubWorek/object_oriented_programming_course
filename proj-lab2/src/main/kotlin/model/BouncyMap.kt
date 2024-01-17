package model

import utils.Vector2d
import utils.randomFreePosition
import utils.randomPosition

class BouncyMap (private val height: Int = 0, private val width: Int = 0): IWorldMap {
    var animals: HashMap<Vector2d, Animal> = HashMap()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position < Vector2d(width, height) && position > Vector2d(0, 0)
    }

    override fun place(animal: Animal): Boolean {
        if(animals.containsValue(animal)){
            for (pos in animals.keys){
                if (animals.get(pos)==animal){
                    animals.remove(pos)
                    break
                }
            }
        }
        if (!canMoveTo(animal.position)){
            return false
        }
        if (!isOccupied(animal.position) && canMoveTo(animal.position)){
            animals.put(animal.position, animal)
        }else {
            val randomFree: Vector2d = randomPosition(Vector2d(width,height))
            if (!isOccupied(randomFree)) {
                animal.position = randomFree
                animals.put(randomFree, animal)
            } else {
                val randomPos: Vector2d? = randomFreePosition(Vector2d(width,height))
                if (randomPos != null) {
                    animal.position = randomPos
                    animals.put(randomPos, animal)
                } else {
                    val toSwap = animals.keys.random()
                    animals.remove(toSwap)
                    animal.position = toSwap
                    animals[animal.position] = animal
                }
            }
        }
        return true
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return animals.containsKey(position)
    }

    override fun objectAt(position: Vector2d): Any? {
        return animals[position]
    }
}