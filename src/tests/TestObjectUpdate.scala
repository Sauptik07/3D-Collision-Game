package tests

import org.scalatest._
import physics.{PhysicsEngine, PhysicsVector}
import physics.objects.DynamicObject

class TestObjectUpdate extends FunSuite{


  test("checking if velocity updates") {

    val d_obj: DynamicObject = new DynamicObject(new PhysicsVector(4.0, 3.0, 0.0), new PhysicsVector(3.0, 3.0, 3.0))
    val t: Double = 5.0
    val mag_gravity: Double = 10.0

    //assert initial velocity is 0
    assert(d_obj.velocity.x == 0.0)
    assert(d_obj.velocity.y == 0.0)
    assert(d_obj.velocity.z == 0.0)

    //call update object
    PhysicsEngine.updateObject(d_obj, t, mag_gravity)

    //assert if velocity in only z is getting updated
    assert(d_obj.velocity.z == 0.0)
    assert(d_obj.velocity.x == 0.0 )
    assert(d_obj.velocity.y == 0.0 )

  }

  test("location is updating") {

    val d_obj: DynamicObject = new DynamicObject(new PhysicsVector(4.0, 3.0, 6.0), new PhysicsVector(3.0, 3.0, 3.0))
    val t: Double = 5.0
    val mag_gravity: Double = 10.0

    assert(t >= 0)
    assert(mag_gravity == 10.0)



    //assert initial location
    assert(d_obj.location.x == 4.0)
    assert(d_obj.location.y == 3.0)
    assert(d_obj.location.z == 6.0)


    //call update object
    PhysicsEngine.updateObject(d_obj, t, mag_gravity)

    //assert location updates
    assert(d_obj.location.x == 4.0)
    assert(d_obj.location.y == 3.0)
    assert(d_obj.location.z == 0.0)

  }

  test("location at 0") {

    val d_obj: DynamicObject = new DynamicObject(new PhysicsVector(0.0, 0.0, 0.0), new PhysicsVector(3.0, 3.0, 3.0))
    val t: Double = 0.0
    val mag_gravity: Double = 10.0

    assert(t >= 0.0)
    assert(mag_gravity == 10.0)



    //assert initial location
    assert(d_obj.location.x == 0.0)
    assert(d_obj.location.y == 0.0)
    assert(d_obj.location.z == 0.0)



    //call update object
    PhysicsEngine.updateObject(d_obj, t, mag_gravity)


    assert(d_obj.location.x == 0.0)
    assert(d_obj.location.y == 0.0)
    assert(d_obj.location.z == 0.0)

    assert(d_obj.velocity.z == 0.0)
    assert(d_obj.velocity.y == 0.0)
    assert(d_obj.velocity.x == 0.0)
    assert(d_obj.onGroundCalled==true)


  }

  test("checking for always earth") {

    val d_obj2: DynamicObject = new DynamicObject(new PhysicsVector(2.0, 3.0, 0.0), new PhysicsVector(3.0, 3.0, 3.0))
    val t: Double = 10.0
    val mag_gravity: Double = 10.0

//    assert(t == 0)
    //    assert(mag_gravity == 10.0)


    //assert initial location
    assert(d_obj2.location.x == 2.0)
    assert(d_obj2.location.y == 3.0)
    assert(d_obj2.location.z == 0.0)



    //call update object
    PhysicsEngine.updateObject(d_obj2, t, mag_gravity)


    assert(d_obj2.location.x == 2.0)
    assert(d_obj2.location.y == 3.0)
    assert(d_obj2.location.z == 0.0)

    assert(d_obj2.velocity.z == 0.0)
    assert(d_obj2.velocity.y == 0.0)
    assert(d_obj2.velocity.x == 0.0)
    assert(d_obj2.onGroundCalled==true)


  }

}
