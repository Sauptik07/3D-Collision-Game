package physics

import physics.objects.GameObject
import physics.objects.DynamicObject
import physics.objects.StaticObject
import physics.objects.Face

/**
  * Controls and computes the simulated physics for a game. Manages dynamic object movement, gravity, and
  * object collisions
  */
object PhysicsEngine {

  def isCollision(obj1: GameObject, obj2: GameObject): Boolean = {

    //min of obj1 x y z

    val Obj1Min_x = obj1.location.x
    val Obj1Min_y = obj1.location.y
    val Obj1Min_z = obj1.location.z

    //min of obj2 x y z
    val Obj2Min_x = obj2.location.x
    val Obj2Min_y = obj2.location.y
    val Obj2Min_z = obj2.location.z

    //max of obj1 x y z
    val Obj1Max_x = obj1.location.x + obj1.dimensions.x
    val Obj1Max_y = obj1.location.y + obj1.dimensions.y
    val Obj1Max_z = obj1.location.z + obj1.dimensions.z

    //max of obj2 x y z
    val Obj2Max_x = obj2.location.x + obj2.dimensions.x
    val Obj2Max_y = obj2.location.y + obj2.dimensions.y
    val Obj2Max_z = obj2.location.z + obj2.dimensions.z


    if ((Obj1Min_x < Obj2Max_x) && (Obj2Min_x < Obj1Max_x) && (Obj1Min_y < Obj2Max_y) && (Obj2Min_y < Obj1Max_y) && (Obj1Min_z < Obj2Max_z) && (Obj2Min_z < Obj1Max_z)) {

      true
    }
    else {

      false

    }
  }

  def updateObject(D_Obj: DynamicObject, time: Double, gravity_mag: Double): Unit = {

    //Getting the velocity in the negative z direction
    D_Obj.velocity.z = D_Obj.velocity.z - (time * gravity_mag)

    //Setting the current location as previous location.
    D_Obj.previousLocation.x = D_Obj.location.x
    D_Obj.previousLocation.y = D_Obj.location.y
    D_Obj.previousLocation.z = D_Obj.location.z

    //Updating location with velocity
    D_Obj.location.x += D_Obj.velocity.x * time
    D_Obj.location.y += D_Obj.velocity.y * time
    D_Obj.location.z += D_Obj.velocity.z * time

    //Checking if the object is touching the ground
    if (D_Obj.location.z <= 0.0) {

      D_Obj.location.z = 0.0
      D_Obj.velocity.z = 0.0

      //calling the onGround function
      D_Obj.onGround()

    }

  }

  def checkStaticCollision(S_Obj: StaticObject, D_obj1: DynamicObject): Unit = {
    //condition if they two objects collide
    if (isCollision(S_Obj, D_obj1) == true) {

      //Calling collideWithStaticObject on D_obj1 with S_obj as argument
      D_obj1.collideWithStaticObject(S_Obj)

      var face_value = S_Obj.face

      //Calling collideWithDynamicObject on S_obj with D_obj as argument


      //calling collision
      if(D_obj1.previousLocation.x < D_obj1.location.x){

          face_value = Face.negativeX

        }

      if(D_obj1.previousLocation.x > D_obj1.location.x){

          face_value = Face.positiveX
        }

      if (D_obj1.previousLocation.y < D_obj1.location.y){

        face_value = Face.negativeY

      }

      if (D_obj1.previousLocation.y > D_obj1.location.y){

         face_value= Face.positiveY
      }

      if(D_obj1.previousLocation.z < D_obj1.location.z){

        face_value = Face.bottom

      }
      if(D_obj1.previousLocation.z > D_obj1.location.z){

        face_value = Face.top
      }

      if(D_obj1.previousLocation.x > S_Obj.location.x && D_obj1.previousLocation.x < S_Obj.location.x + S_Obj.dimensions.x ) {

        face_value = Face.internal
      }
      if(D_obj1.previousLocation.y > S_Obj.location.y && D_obj1.previousLocation.y < S_Obj.location.y + S_Obj.dimensions.y ) {

        face_value = Face.internal
      }
      if(D_obj1.previousLocation.z > S_Obj.location.z && D_obj1.previousLocation.z < S_Obj.location.z + S_Obj.dimensions.z ) {

        face_value = Face.internal
      }

      S_Obj.collideWithDynamicObject(D_obj1, face_value)
    }
    else S_Obj.face = -1


  }

    def updateWorld(world: World, elapsed_time: Double ): Unit = {

      for (dobj1 <- world.dynamicObjects) {

        updateObject(dobj1, elapsed_time, world.gravity)
        for (sobj <- world.staticObjects){

          checkStaticCollision(sobj, dobj1)
        }
      }
    }

//  def main(args: Array[String]): Unit = {
//    val sobj222: StaticObject = new StaticObject(new PhysicsVector(4.0, 3.0, 6.0), new PhysicsVector(3.0, 3.0, 3.0))
//    val dobj222: DynamicObject = new DynamicObject(new PhysicsVector(3.0, 2.0, 5.0), new PhysicsVector(1.5, 1.5, 1.5))
//
//    println(checkStaticCollision(sobj222,dobj222))
//    println(sobj222.face)
//  }

}
