import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.path.heading.ConstantInterpolator
import com.acmerobotics.roadrunner.path.heading.LinearInterpolator
import com.acmerobotics.roadrunner.path.heading.SplineInterpolator
import com.acmerobotics.roadrunner.path.heading.TangentInterpolator
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumConstraints

object TrajectoryGen {
    private val constraints = MecanumConstraints(
        DriveConstraints(75.0, 90.0, 0.0,
            220.0.toRadians, 320.0.toRadians, 0.0),
        13.5,13.5)
    private val max_constraints = MecanumConstraints(
        DriveConstraints(50.0, 50.0, 0.0, 180.0.toRadians, 180.0.toRadians, 0.0),
        13.5,13.5)


    fun createTrajectory(): ArrayList<Trajectory> {
                        //return testOneStoneOnly()

        //return test5thStone()
        //return randomPathTest()
        //return testPathSpeed()

        //return testSplineTurn()
        //return testSplineTurn2()

//        return testLineToIntepolators()

        return buildSpline6StonesTrajectory()

//        return buildSpline5StonesTrajectory()

        //return buildFoundaton2Trajectory()

        // return buildStrafeTrajectory()

        // return buildFoundatonTrajectory()

        //return test5thStonePath()
    }

    fun testLineToIntepolators(): ArrayList<Trajectory> {

        val traj = TrajectoryBuilder(Pose2d(-40.0, -63.0, 0.0), 0.0, constraints)
            .splineToLinearHeading(Pose2d(-20.0, -32.0,0.0),0.0)// pick up first block
 //           .splineTo(Pose2d(0.0, -36.0, 0.0))
            .splineToLinearHeading(Pose2d(0.0,-41.0 , 0.0), 0.0)
            .splineToLinearHeading(Pose2d(50.0,-40.0 , 0.0), 0.0)
            .build()

        val list = ArrayList<Trajectory>()

        var builder1 = TrajectoryBuilder(Pose2d(-60.0, -40.5, 178.5.toRadians + Math.PI), 178.5.toRadians, constraints)

        builder1
            .splineTo(Pose2d(0.0, -41.5, 0.0.toRadians))
            .splineToLinearHeading(Pose2d(30.0, -40.5,0.0.toRadians), 180.0.toRadians)
            .splineToLinearHeading(Pose2d(60.0, -40.0, 0.0.toRadians), 180.0.toRadians)
//            .splineToConstantHeading(Pose2d(30.0, -40.5,0.0))
//            .splineToConstantHeading(Pose2d(49.0,-40.5, Math.PI + 180.0.toRadians))
//           .lineToConstantHeading(Vector2d(0.0,-41.0))
//            .lineToConstantHeading(Vector2d(62.0,-41.0))
//            .addMarker { println("release stone 1") }

        // 2. place first stone
        list.add(builder1.build())

        //list.add(traj)
        return list
    }

    fun buildSpline6StonesTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val startPose = Pose2d(-33.0, -63.0, 180.0.toRadians)

        var builder1 = TrajectoryBuilder(startPose, 180.0.toRadians, constraints)

        builder1
//            .strafeTo( Vector2d(-33.0, -39.0))
//            .lineTo(Vector2d(-59.0,-36.0))
            .strafeTo( Vector2d(-60.0, -40.0))
//            .addMarker(Vector2d(-50.0, -40.0), {println("spatial marker triggered ...") })
//            .addMarker { println("grab stone 1") }
        //builder1.splineTo(Pose2d(-59.0,-36.0, 180.0.toRadians))

        // 1. get first stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-60.0, -41.49, 175.0.toRadians+ Math.PI), 175.0.toRadians, constraints)

        builder1

            .splineTo(Pose2d(0.0, -41.0, 0.0.toRadians))
            .splineTo(Pose2d(62.0,-40.5, Math.PI + 180.0.toRadians))
//           .lineToConstantHeading(Vector2d(0.0,-41.0))
//            .lineToConstantHeading(Vector2d(62.0,-41.0))
//            .addMarker { println("release stone 1") }

        // 2. place first stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(62.0, -40.5, 178.0.toRadians),180.0.toRadians, constraints)

        builder1
            //.reverse()
            .splineToLinearHeading(Pose2d(-35.0,-40.5,  180.0.toRadians), 180.0.toRadians)
            //.strafeTo(Vector2d(-35.0,-35.0))
//            .addMarker { println("grab stone 2") }

        // 3. get second stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-35.0, -40.5, 0.0.toRadians),179.0.toRadians, constraints)
        builder1
            .splineToLinearHeading(Pose2d(57.0,-40.0, 179.0.toRadians + 180.0.toRadians), 180.0.toRadians)
//            .addMarker { println("release stone 2") }

        // 4. place second stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(57.0, -40.0, 180.0.toRadians), 180.0.toRadians, constraints)
        builder1
            .splineToConstantHeading(Pose2d(-18.0,-40.5, 180.0.toRadians))
//            .addMarker { println("grab stone 3") }

        // 5. get 3rd stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-18.0, -40.5, 0.0.toRadians),  180.0.toRadians,constraints)
        builder1
            //.strafeTo(Vector2d(-18.0,-38.0))
            .splineToConstantHeading(Pose2d(52.0,-40.0, 0.0.toRadians))
 //           .addMarker { println("release stone 3") }

        // 6. place 3rd stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(52.0, -40.0, 180.0.toRadians), 180.0.toRadians, constraints)
        builder1
            .splineToConstantHeading(Pose2d(-27.0,-40.5, 180.0.toRadians))
            //.strafeTo(Vector2d(-27.0,-35.0))
 //           .addMarker { println("grab stone 4") }

        // 7. get 4th stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-27.0, -40.5, 0.0.toRadians),  180.0.toRadians,constraints)
        builder1
            .splineTo(Pose2d(47.0, -40.0, 0.0.toRadians))
//            .addMarker {println("release stone 4 ...")}

        // 8. place 4th stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(47.0, -40.0, 180.0.toRadians), 180.0.toRadians, constraints)
        builder1
            .splineTo(Pose2d(-43.0, -40.5, 180.0.toRadians ))
            //.strafeTo(Vector2d(-43.0,-35.0))
//            .addMarker {println("grab stone 5 ...")}

        // 9. grab 5th stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-43.0, -40.0, 0.0.toRadians),  180.0.toRadians,constraints)
        builder1
            //.strafeTo(Vector2d(-43.0, -39.0))
            .splineTo(Pose2d(51.0,-40.0, (180.0+180.0).toRadians))
//            .splineTo(Pose2d(56.5, -37.0 - 1.5, (90.0+180.0).toRadians))
//            .forward(5.5)
            //.splineTo(Pose2d(53.0,-45.5, 270.0.toRadians))
            //.lineTo(Vector2d(55.0, -30.0))
//            .addMarker {println("release stone 5 ...")}

        //.lineTo(Vector2d(55.0, -31.0))

        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(51.0, -40.0, 180.0.toRadians), 180.0.toRadians, constraints)
        builder1
            .splineTo(Pose2d(-51.0, -40.5, 180.0.toRadians ))
            //.strafeTo(Vector2d(-43.0,-35.0))
            //.addMarker {println("grab stone 6 ...")}

        // 9. grab 6th stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-51.0, -40.5, 0.0.toRadians),  180.0.toRadians,constraints)
        builder1
            //.strafeTo(Vector2d(-43.0, -39.0))
            .splineToConstantHeading(Pose2d(55.0,-38.5, (180.0+180.0).toRadians))
            //.addMarker {println("release stone 5 ...")}

        list.add(builder1.build())

        builder1 = TrajectoryBuilder( Pose2d(55.0, -38.5, 0.0), Math.PI, constraints)
        builder1.splineTo(Pose2d(56.5, -38.5-1.5, (90.0+180.0).toRadians))

        list.add(builder1.build())


        builder1 = TrajectoryBuilder( Pose2d(56.5, -40.0, 90.0.toRadians), Math.PI, constraints)
        builder1.lineToLinearHeading(Vector2d(56.5, -30.0), 90.0.toRadians)
        list.add(builder1.build())


        builder1 = TrajectoryBuilder(Pose2d(56.5, -30.0, 270.0.toRadians),  90.0.toRadians,constraints)
        builder1
            .splineTo(Pose2d(35.0, -56.0, 180.0.toRadians))
            .splineTo( Pose2d(0.0, -40.0, 180.0.toRadians))

        list.add(builder1.build())

//        builder1 = TrajectoryBuilder(Pose2d(50.0, -52.0, 180.0.toRadians),  0.0.toRadians,constraints)
//        builder1
//            .splineTo( Pose2d(5.0, -40.0, 180.0.toRadians))
//
//        // 10. release 5th stone and park
//        list.add(builder1.build())

        return list
    }

    fun buildSpline5StonesTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val startPose = Pose2d(-33.0, -63.0, 180.0.toRadians)

        var builder1 = TrajectoryBuilder(startPose, 180.0.toRadians, constraints)

        builder1
//            .strafeTo( Vector2d(-33.0, -39.0))
//            .lineTo(Vector2d(-59.0,-36.0))
            .strafeTo( Vector2d(-60.0, -36.0))
//            .addMarker(Vector2d(-50.0, -40.0), {println("spatial marker triggered ...") })
 //           .addMarker { println("grab stone 1") }
        //builder1.splineTo(Pose2d(-59.0,-36.0, 180.0.toRadians))

        // 1. get first stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-60.0, -36.0, 0.0.toRadians), 180.0.toRadians, constraints)

        builder1
            .strafeTo(Vector2d(-60.0,-38.0))
            .strafeTo(Vector2d(0.0,-41.0))
            //.splineTo(Pose2d(0.0, -41.0, 0.0.toRadians))
            //.splineTo(Pose2d(49.0,-35.0, 180.0.toRadians))
            .splineTo(Pose2d(49.0,-35.0, 0.0.toRadians))
 //           .addMarker { println("release stone 1") }

        // 2. place first stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(49.0, -35.0, 180.0.toRadians),180.0.toRadians, constraints)

        builder1
            //.reverse()
            .splineTo(Pose2d(0.0,-37.0, 180.0.toRadians))
            .strafeTo(Vector2d(-35.0,-37.0))
            //.strafeTo(Vector2d(-35.0,-35.0))
 //           .addMarker { println("grab stone 2") }

        // 3. get second stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-35.0, -37.0, 0.0.toRadians),180.0.toRadians, constraints)
        builder1
            .strafeTo(Vector2d(-35.0,-39.0))
            .strafeTo(Vector2d(0.0,-41.0))
            //.splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .splineTo(Pose2d(60.0,-34.0, 0.0.toRadians))
 //           .addMarker { println("release stone 2") }

        // 4. place second stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(60.0, -34.0, 180.0.toRadians), 180.0.toRadians, constraints)
        builder1
            .splineTo(Pose2d(0.0,-37.0, 180.0.toRadians))
            .lineTo(Vector2d(-18.0,-37.0))
            //.strafeTo(Vector2d(-18.0,-35.0))
  //          .addMarker { println("grab stone 3") }

        // 5. get 3rd stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-18.0, -37.0, 0.0.toRadians),  180.0.toRadians,constraints)
        builder1
            //.strafeTo(Vector2d(-18.0,-38.0))
            .strafeTo(Vector2d(0.0,-41.0))
            .splineTo(Pose2d(49.0,-34.5, 0.0.toRadians))
     //       .addMarker { println("release stone 3") }

        // 6. place 3rd stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(49.0, -34.5, 180.0.toRadians), 180.0.toRadians, constraints)
        builder1
            .splineTo(Pose2d(0.0,-37.0, 180.0.toRadians))
            .lineTo(Vector2d(-27.0,-37.0))
            //.strafeTo(Vector2d(-27.0,-35.0))
     //       .addMarker { println("grab stone 4") }

        // 7. get 4th stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-27.0, -37.0, 0.0.toRadians),  180.0.toRadians,constraints)
        builder1
            //.strafeTo(Vector2d(-27.0,-38.0))
            .strafeTo(Vector2d(0.0,-41.0))
            .splineTo(Pose2d(60.0, -34.0, 0.0.toRadians))
 //           .addMarker {println("release stone 4 ...")}

        // 8. place 4th stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(60.0, -34.0, 180.0.toRadians), 180.0.toRadians, constraints)
        builder1
            .splineTo(Pose2d(0.0, -37.0, 180.0.toRadians ))
            .lineTo(Vector2d(-43.0,-37.0))
            //.strafeTo(Vector2d(-43.0,-35.0))
//            .addMarker {println("grab stone 5 ...")}

        // 9. grab 5th stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-43.0, -37.0, 0.0.toRadians),  180.0.toRadians,constraints)
        builder1
            //.strafeTo(Vector2d(-43.0, -39.0))
            .strafeTo(Vector2d(0.0, -41.0))
            .splineTo(Pose2d(55.0,-33.5, (180.0+180.0).toRadians))
            .strafeTo(Vector2d(55.0, -38.0))
//            .splineTo(Pose2d(56.5, -37.0 - 1.5, (90.0+180.0).toRadians))
//            .forward(5.5)
            //.splineTo(Pose2d(53.0,-45.5, 270.0.toRadians))
            //.lineTo(Vector2d(55.0, -30.0))
 //           .addMarker {println("release stone 5 ...")}

            //.lineTo(Vector2d(55.0, -31.0))

        list.add(builder1.build())

        builder1 = TrajectoryBuilder( Pose2d(55.0, -38.0, 0.0), Math.PI, constraints)
        builder1.splineTo(Pose2d(56.5, -38.0-1.5, (90.0+180.0).toRadians))
            .lineToConstantHeading(Vector2d(56.5, -29.0))


        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(56.5, -29.0, 270.0.toRadians),  90.0.toRadians,constraints)
        builder1
            .splineTo(Pose2d(33.0, -56.0, 180.0.toRadians))
            .splineTo( Pose2d(5.0, -40.0, 180.0.toRadians))

        // 10. release 5th stone and park
        list.add(builder1.build())

        return list
    }

    fun test5thStonePath(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val startPose = Pose2d(58.0, 35.0, 0.0.toRadians)

        var builder1 = TrajectoryBuilder(startPose, 0.0.toRadians, constraints)

        builder1.splineTo(Pose2d(59.5, 35.0, -90.0.toRadians))

//        builder1
//            .reverse()
//            .splineTo(Pose2d(0.0, 38.0, 0.0))
//            .splineTo(Pose2d(-53.0, 36.0, 0.0))
//        //    .splineTo(Pose2d(45.0,-35.5, 180.0.toRadians))

        list.add(builder1.build())

//        builder1 = TrajectoryBuilder(Pose2d(-53.0, 36.0, 0.0.toRadians), constraints)
//
//        builder1
//            .splineTo(Pose2d(0.0, 41.0, 0.0))
//            .splineTo(Pose2d(41.0, 45.0, 100.0.toRadians))
//            .strafeTo(Vector2d(5.0, 42.0))
//        //    .splineTo(Pose2d(45.0,-35.5, 180.0.toRadians))

//        list.add(builder1.build())

        return list

    }

    fun testPathSpeed(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val startPose = Pose2d(55.0, -35.0, 180.0.toRadians)

        var builder1 = TrajectoryBuilder(startPose, 180.0.toRadians, constraints)

        builder1
            .splineTo( Pose2d(0.0, -38.0, 180.0.toRadians))
            .splineTo( Pose2d(-59.0, -35.0, 180.0.toRadians))

        list.add(builder1.build())

        builder1 = TrajectoryBuilder( Pose2d(-59.0, -35.0, (178.0+180.0).toRadians), 178.0.toRadians, constraints)

            //.strafeTo(Vector2d(-59.0, -38.0))
        builder1.splineTo(Pose2d(0.0, -41.0, (180.0+180.0).toRadians))
            .splineTo( Pose2d(49.0, -35.0, (180.0+180.0).toRadians))
//            .splineTo(Pose2d(55.0,-45.5, 90.0.toRadians))
//            .lineTo(Vector2d(55.0, -31.0))
//            .splineTo(Pose2d(35.0, -58.0, 0.0.toRadians))
//            .splineTo( Pose2d(0.0, -40.0, .0.toRadians))

        list.add(builder1.build())

        return list

    }

    fun testSplineTurn(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val myPose = Pose2d(-33.0, -63.0, 0.0)
        var builder0 = TrajectoryBuilder(myPose, Math.PI/2, constraints)
        builder0.strafeTo(Vector2d(-41.0,-35.0))
        list.add(builder0.build())

        // turn 90 degree, and make heading forward
        builder0 = TrajectoryBuilder(Pose2d(-41.0, -35.0, Math.PI + Math.PI/2), Math.PI/2, constraints)
        builder0.splineTo(Pose2d(-42.5,-38.5, 0.0.toRadians + Math.PI))
        list.add(builder0.build())

        // splineTo foundation and facing foundation
        builder0 = TrajectoryBuilder(Pose2d(-42.5, -38.5, 0.0), 0.0, constraints)
        builder0.splineTo(Pose2d(0.0,-38.5, 0.0.toRadians ))
        builder0.splineTo(Pose2d(50.0,-34.0, Math.PI/2))
        list.add(builder0.build())

        return list
    }

    fun testSplineTurn3(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val myPose = Pose2d(-33.0, -63.0, 0.0)
        var builder0 = TrajectoryBuilder(myPose, Math.PI/2, constraints)
        builder0.strafeTo(Vector2d(-41.0,-35.0))
        list.add(builder0.build())

        builder0 = TrajectoryBuilder(Pose2d(-41.0, -35.0, Math.PI + Math.PI/2), Math.PI/2, constraints)

        builder0.splineTo(Pose2d(0.0,-40.0, 180.0.toRadians + Math.PI))
        builder0.splineToSplineHeading(Pose2d(50.0,-34.0, Math.PI/2), Math.PI/2)
        list.add(builder0.build())

        return list
    }

    fun testSplineTurn4(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val myPose = Pose2d(-59.0, -35.0, 0.0)
        var builder0 = TrajectoryBuilder(myPose, Math.PI, constraints)
        builder0.strafeTo(Vector2d(-59.0,-38.0))
            .splineTo(Pose2d(0.0,-41.0, Math.PI +  Math.PI))
            .splineTo(Pose2d(55.0, -45.0, (Math.PI+Math.PI/2)))

        list.add(builder0.build())

        return list
    }

    fun testSplineTurn2(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val myPose = Pose2d(57.0, 34.0, 180.0.toRadians)
        var builder0 = TrajectoryBuilder(myPose, 0.0, constraints)
        builder0
            .strafeTo(Vector2d(57.0, 37.5))
            .splineTo(Pose2d(55.5, 39.0, (90.0).toRadians))
        .lineToConstantHeading(Vector2d(56.5, 28.0))

        list.add(builder0.build())

        return list
    }

    fun buildFoundatonTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val myPose = Pose2d(15.0, -63.0, Math.PI/2)
        var builder0 = TrajectoryBuilder(myPose, Math.PI/2, constraints)
        builder0.splineTo(Pose2d(53.0, -31.0, Math.PI/2))
            .splineTo( Pose2d(35.0, -56.0, 0.0))
            .splineTo( Pose2d(5.0, -40.0, 0.0))
        list.add(builder0.build())

        return list
    }

    fun buildFoundaton2Trajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val myPose = Pose2d(61.0, -36.0, Math.PI)
        var builder0 = TrajectoryBuilder(myPose, Math.PI, max_constraints)
        builder0.splineTo(Pose2d(0.0, -40.0, Math.PI))
            .lineTo(Vector2d(-27.0,-40.0))
            .strafeTo(Vector2d(-27.0,-36.0))
            .splineTo(Pose2d(0.0, -42.0, Math.PI))
            .splineTo(Pose2d(51.0, -45.0, Math.PI/2))
//            .addMarker (4.0, { println("test marker 0....")})
//            .addMarker (Vector2d(49.0,36.0), { println("test marker spatial....")})

        list.add(builder0.build())

        builder0 = TrajectoryBuilder(Pose2d(53.0, -31.0, Math.PI/2), Math.PI/2, constraints)
        builder0
            .splineTo( Pose2d(35.0, -58.0, 0.0))
            .splineTo( Pose2d(5.0, -40.0, 0.0))

        //.lineTo( Vector2d(5.0, -40.0))
        list.add(builder0.build())

        return list
    }

    fun randomPathTest_backup():ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        var builder = TrajectoryBuilder(Pose2d(0.0, -40.0, Math.PI+Math.PI), Math.PI, constraints)

        builder.splineToLinearHeading(Pose2d(50.0, -50.0, Math.PI*1.5), Math.PI*1/2)
            .splineToSplineHeading(Pose2d(0.0, -40.0, Math.PI), Math.PI)

        list.add(builder.build())

        builder = TrajectoryBuilder(Pose2d(0.0, -40.0, Math.PI), Math.PI, constraints)

        builder.splineTo(Pose2d(-35.0, -35.0, Math.PI*3/4))
            .lineToSplineHeading(Vector2d(-30.0, -55.0), Math.PI*1/2)
            .strafeTo(Vector2d(0.0, -40.0))

        list.add(builder.build())

        builder = TrajectoryBuilder(Pose2d(0.0, -40.0, Math.PI+ Math.PI/2), Math.PI/2, constraints)
        builder
            .splineTo(Pose2d(55.0, -35.0, Math.PI))
            .lineToSplineHeading(Vector2d(-18.0, -37.0), 2*Math.PI)
        //builder0.splineTo(Pose2d(41.0, -45.0, 3*90.0.toRadians))

        //builder0.splineToLinearHeading(Pose2d(41.0, -45.0, Math.PI*1.5), Math.PI/2)

        //builder0.splineToSplineHeading(Pose2d(41.0, -45.0, Math.PI*1.5), Math.PI*1/2)

        //builder0.splineToConstantHeading(Pose2d(41.0, -45.0, Math.PI*1.5))

        //builder0.lineToSplineHeading(Vector2d(41.0, -45.0), Math.PI*1/2)
        //builder0.lineToLinearHeading(Vector2d(41.0, -45.0), Math.PI*1/2)

        //builder0.lineToConstantHeading(Vector2d(41.0, -45.0))
        //builder0.strafeTo(Vector2d(41.0, -45.0))

        //builder0.lineToConstantHeading(Vector2d(0.0, -45.0))

        list.add(builder.build())

        return list
    }

    /**
     * This is a demo to showcase different path builder functions
     * Road Runner Coordinates:
     *              (+x)
     *               |
     *               |
     *               | f (foundation)
     *               |
     * (+y) -------(0,0)--- o (Robot) --- (-y)
     *               |
     *               |  s
     *               |  s (stone)
     *               |  s
     *               |
     *              -x
     */
    fun randomPathTest():ArrayList<Trajectory> {

        val list = ArrayList<Trajectory>()
        // using road runner 0.5.0, to make robot run in reverse
        // you need to plus Math.PI to the start and ending heading
        //----------------------------------------------------------------------------
        // run reverse using splineTo, with Linear and Spline Heading interpolator
        var builder = TrajectoryBuilder(
                           Pose2d(0.0, -40.0, Math.PI+Math.PI), Math.PI, constraints)
        builder.splineToLinearHeading(
                           Pose2d(50.0, -50.0, Math.PI*1.5), Math.PI*1/2)
               .splineToSplineHeading(
                           Pose2d(0.0, -40.0, Math.PI), Math.PI)
        list.add(builder.build())

        // run forward using splienTo, with default Tangent heading interpolator
        // and also lineTo with Spline heading interpolator
        // strafeTo = lineTo with Constant heading interpolator
        builder = TrajectoryBuilder(Pose2d(0.0, -40.0, Math.PI), Math.PI, constraints)
        builder.splineTo(Pose2d(-35.0, -35.0, Math.PI*3/4))
               .lineToSplineHeading(Vector2d(-30.0, -55.0), Math.PI*1/2)
               .strafeTo(Vector2d(0.0, -40.0))
        list.add(builder.build())

        // run reverse, spline heading with multiple turns
        var currentPose = Pose2d(0.0, -40.0, Math.PI+ Math.PI/2)
        builder = TrajectoryBuilder(currentPose,Math.PI/2, constraints)
        builder.splineTo(Pose2d(55.0, -35.0, Math.PI))
               .lineToSplineHeading(Vector2d(-18.0, -37.0), Math.PI)
        list.add(builder.build())

        return list
    }

    fun randomPathTest1():ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        var builder0 = TrajectoryBuilder(Pose2d(-24.0, -60.0, 0.0), 0.0, constraints)

        builder0.strafeTo(Vector2d(-24.0, -36.0))
        list.add(builder0.build())

        return list
    }

    fun testOneStoneOnly():ArrayList<Trajectory> {

        val list = ArrayList<Trajectory>()

        var builder = TrajectoryBuilder(
            Pose2d(-18.0, -63.0, Math.PI+Math.PI), Math.PI, constraints)
        builder.lineTo(Vector2d(-35.0,-63.0))
            .strafeTo(Vector2d(-51.0,-35.0))
        list.add(builder.build())

        builder = TrajectoryBuilder(Pose2d(-51.0, -35.0, Math.PI), Math.PI, constraints)
        builder.strafeTo(Vector2d(-45.0, -62.0))
            .lineTo(Vector2d(55.0, -62.0))
        list.add(builder.build())

        // run reverse, spline heading with multiple turns
        var currentPose = Pose2d(55.0, -62.0, Math.PI)
        builder = TrajectoryBuilder(currentPose,Math.PI, constraints)
        builder.strafeTo(Vector2d(55.0, -34.0))
        list.add(builder.build())

        currentPose = Pose2d(55.0, -34.0, Math.PI)
        builder = TrajectoryBuilder(currentPose,Math.PI, constraints)
        builder.strafeTo(Vector2d(55.0, -62.0))
            .lineTo(Vector2d(0.0, -62.0))

        list.add(builder.build())

        return list
    }

    fun test5thStone():ArrayList<Trajectory> {

        val list = ArrayList<Trajectory>()

        var builder = TrajectoryBuilder(
            Pose2d(59.0, -35.0, Math.PI), Math.PI, constraints)
        builder.splineTo(Pose2d(0.0, -38.0, Math.PI))
            .lineTo(Vector2d(-52.0, -38.0))
            .strafeTo(Vector2d(-52.0, -36.0))
        list.add(builder.build())


         builder = TrajectoryBuilder(
            Pose2d(-52.0, -36.0, Math.PI+Math.PI), Math.PI, constraints)
        builder.strafeTo(Vector2d(-52.0, -38.0))
            .strafeTo(Vector2d(0.0, -42.0))
            .splineToSplineHeading(Pose2d(50.0, -30.0, Math.PI/2), Math.PI*1/2)
        list.add(builder.build())

//        builder = TrajectoryBuilder(
//            Pose2d(0.0, -42.0, Math.PI+Math.PI), Math.PI, constraints)
//        builder
//            .splineToSplineHeading(Pose2d(50.0, -30.0, Math.PI/2), Math.PI*1/2)

//        var builder = TrajectoryBuilder(
//            Pose2d(-52.0, -36.0, Math.PI+Math.PI), Math.PI, constraints)
//        builder.strafeTo(Vector2d(-52.0,-38.0))
//            .strafeTo(Vector2d(0.0,-42.0))
//            .splineToLinearHeading(Pose2d(50.0, -50.0, Math.PI*3/2),  Math.PI*1/2)
//
//        list.add(builder.build())

        return list
    }


    fun drawOffbounds1() {
        GraphicsUtil.fillRect(Vector2d(0.0, -63.0), 18.0, 18.0, false) // robot against the wall
    }

//    fun drawOffbounds2() {
//        GraphicsUtil.fillRect(Vector2d(0.0, -24.0), 12.0, 8.0,true) // block against the bridge
//        GraphicsUtil.fillFoundationRect(Vector2d(60.0, -45.0), 34.5, 18.5)
//    }
}

val Double.toRadians get() = (Math.toRadians(this))

// dump routine
/*builder
    .setReversed(true)
    .splineTo(Pose2d(-12.0, -42.0, 180.0.toRadians))// y = -39 is halfway between the skybridge and partner with 6" on either side, y = -42 gives 3" of room for other robot
    .splineTo(Pose2d(28.0, -42.0, 180.0.toRadians))
    .splineTo(Pose2d(48.0, -33.0, -90.0.toRadians))
    .lineTo(Vector2d(48.0, -26.0), ConstantInterpolator(-90.0.toRadians))*/


// weird reversed profiles
/* builder
     .setReversed(true)
     .splineTo(Pose2d(-12.0, -42.0, 180.0.toRadians), SplineInterpolator(180.0.toRadians, startPose.heading))// y = 39 is halfway between the skybridge and partner with 6" on either side
     .splineTo(Pose2d(28.0, -42.0, 180.0.toRadians), SplineInterpolator(180.0.toRadians, 180.0.toRadians))
     .splineTo(Pose2d(48.0, -33.0, -90.0.toRadians), SplineInterpolator(180.0.toRadians, -90.0.toRadians))
     .lineTo(Vector2d(48.0, -26.0), ConstantInterpolator(-90.0.toRadians))*/