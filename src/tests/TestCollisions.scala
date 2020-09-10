package tests

import org.scalatest._
import physics.{PhysicsEngine, PhysicsVector}
import physics.objects.GameObject

class TestCollisions extends FunSuite {

  test("Tests for accurate collision detection between two objects- with given sample") {

    // Sample test case for objective 1. After you write isCollision, uncomment the assert line.
    // These two objects do collide so isCollision should return true. Add more tests for comprehensive testing.
    val obj1: GameObject = new GameObject(new PhysicsVector(4.0, 3.0, 6.0), new PhysicsVector(3.0, 3.0, 3.0))
    val obj2: GameObject = new GameObject(new PhysicsVector(3.0, 2.0, 5.0), new PhysicsVector(1.5, 1.5, 1.5))

    //checking collision
    assert(PhysicsEngine.isCollision(obj1, obj2), "\n" + obj1 + "\n" + obj2)
  }

  test("testing if there are all positives and no negatives") {

    //To check if there are just positive values

    val obj12: GameObject = new GameObject(new PhysicsVector(4.0, 3.0, 6.0), new PhysicsVector(3.0, 3.0, 3.0))

    //Check if obj12 has positive values in x,y and z
    assert(obj12.location.x >= 0)
    assert(obj12.location.y >= 0)
    assert(obj12.location.z >= 0)

    assert(obj12.dimensions.x >= 0)
    assert(obj12.dimensions.y >= 0)
    assert(obj12.dimensions.z >= 0)



    val obj22: GameObject = new GameObject(new PhysicsVector(3.0, 2.0, 999.0), new PhysicsVector(1.5, 1.5, 1.5))
    //Check if obj22 has positive values in x,y and z
    assert(obj22.location.x >= 0)
    assert(obj22.location.y >= 0)
    assert(obj22.location.z >= 0)

    assert(obj22.dimensions.x >= 0)
    assert(obj22.dimensions.y >= 0)
    assert(obj22.dimensions.z >= 0)

    //testing for collision
    assert(PhysicsEngine.isCollision(obj12, obj22)==false, "\n" + obj12 + "\n" + obj22)

  }

  test("One Dimension or not") {

    val obj13: GameObject = new GameObject(new PhysicsVector(4.0, 3.0, 4.0), new PhysicsVector(3.0, 3.0, 3.0))
    assert(obj13.location.x != null && obj13.location.y !=null && obj13.location.z != null )
    assert(obj13.dimensions.x != null && obj13.dimensions.y !=null && obj13.dimensions.z != null )



    val obj23: GameObject = new GameObject(new PhysicsVector(3.0, 2.0, 6.0), new PhysicsVector(1.5, 1.5, 1.5))
    assert(obj23.location.x != null && obj23.location.y !=null && obj23.location.z != null )
    assert(obj23.dimensions.x != null && obj23.dimensions.y !=null && obj23.dimensions.z != null )



    assert(PhysicsEngine.isCollision(obj13, obj23), "\n" + obj13 + "\n" + obj23)

  }

  test("negative"){
    val obj13: GameObject = new GameObject(new PhysicsVector(-4.0, 3.0, 6.0), new PhysicsVector(3.0, 3.0, 3.0))
    val obj23: GameObject = new GameObject(new PhysicsVector(-3.0,2.0, 5.0), new PhysicsVector(1.5, 1.5, 1.5))

    assert(PhysicsEngine.isCollision(obj13,obj23)==true, "okay")

    val obj14: GameObject = new GameObject(new PhysicsVector(-4.0, -3.0,- 6.0), new PhysicsVector(-3.0, -3.0, -3.0))
    val obj24: GameObject = new GameObject(new PhysicsVector(-3.0,-2.0, -5.0), new PhysicsVector(-1.5, -1.5, -1.5))

    assert(PhysicsEngine.isCollision(obj14,obj24)==false, "okay")


  }


}
