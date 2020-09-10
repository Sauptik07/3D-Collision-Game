package tests

import org.scalatest._
import physics.{PhysicsEngine, PhysicsVector}
import physics.objects.DynamicObject
import physics.objects.StaticObject
import physics.objects.Face


class TestStaticCollision extends FunSuite {

  test("checking for collision") {

    val sobj111: StaticObject = new StaticObject(new PhysicsVector(4.0, 3.0, 6.0), new PhysicsVector(3.0, 3.0, 3.0))
    val dobj111: DynamicObject = new DynamicObject(new PhysicsVector(3.0, 2.0,5.0), new PhysicsVector(1.5, 1.5, 1.5))
    //    val facehit: Int = 0

    PhysicsEngine.checkStaticCollision(sobj111, dobj111)

    assert(sobj111.collideWithDynamicObjectCalled == true)
    assert(sobj111.otherObject == dobj111)
    assert(sobj111.face == 0)

    assert(dobj111.collideWithStaticObjectCalled == true)
    assert(dobj111.staticObject == sobj111)
    //calling the iscollision to check if collides
  }
  test("checking for no collision") {

    val sobj111: StaticObject = new StaticObject(new PhysicsVector(4.0, 3.0, 6.0), new PhysicsVector(3.0, 3.0, 3.0))
    val dobj111: DynamicObject = new DynamicObject(new PhysicsVector(3.0, 2.0,999.0), new PhysicsVector(1.5, 1.5, 1.5))
    //    val facehit: Int = 0

    PhysicsEngine.checkStaticCollision(sobj111, dobj111)

    assert(sobj111.collideWithDynamicObjectCalled == false)
//    assert(sobj111.otherObject == dobj111)
    assert(sobj111.face == -1)

    assert(dobj111.collideWithStaticObjectCalled == false)
//    assert(dobj111.staticObject == sobj111)
    //calling the iscollision to check if collides
  }

}
