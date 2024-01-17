package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import utils.Vector2d
import utils.MapDirection

class BouncyMapTest : StringSpec({
    "canMoveTo should return true for valid position" {
        val bouncyMap = BouncyMap(height = 5, width = 5)
        bouncyMap.canMoveTo(Vector2d(2, 3)) shouldBe true
    }

    "canMoveTo should return false for position outside the map boundaries" {
        val bouncyMap = BouncyMap(height = 5, width = 5)
        bouncyMap.canMoveTo(Vector2d(6, 3)) shouldBe false
    }

    "place should add animal to the map" {
        val bouncyMap = BouncyMap(height = 5, width = 5)
        val animal = Animal(Vector2d(2, 3), MapDirection.NORTH, bouncyMap)
        bouncyMap.place(animal) shouldBe true
        bouncyMap.objectAt(Vector2d(2, 3)) shouldBe animal
    }

    "place should update animal position if it's already on the map" {
        val bouncyMap = BouncyMap(height = 5, width = 5)
        val animal = Animal(Vector2d(2, 3), MapDirection.NORTH, bouncyMap)
        bouncyMap.place(animal) shouldBe true
        val newAnimalPosition = Vector2d(3, 4)
        animal.position = newAnimalPosition
        bouncyMap.place(animal)
        bouncyMap.objectAt(newAnimalPosition) shouldBe animal
        bouncyMap.objectAt(Vector2d(2, 3)) shouldBe null
    }

    "place should find a new position for an animal if the current position is occupied" {
        val bouncyMap = BouncyMap(height = 5, width = 5)
        val animal1 = Animal(Vector2d(2, 3), MapDirection.NORTH, bouncyMap)
        val animal2 = Animal(Vector2d(2, 3), MapDirection.SOUTH, bouncyMap)
        bouncyMap.place(animal1)
        bouncyMap.place(animal2)
        bouncyMap.isOccupied(animal1.position) shouldBe true
        bouncyMap.isOccupied(animal2.position) shouldBe true
        animal1.position shouldNotBe animal2.position
    }
})