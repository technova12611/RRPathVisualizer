import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints

object TrajectoryGenBlue {
    private val constraints = DriveConstraints(55.0, 75.0, 0.0, 220.0.toRadians, 320.0.toRadians, 0.0)

    fun createTrajectory(): ArrayList<Trajectory> {
        //return testPathSpeed();

        //return test5thStone()

        return build5StoneTrajectory()

        // return buildStrafeTrajectory()

        // return buildFoundatonTrajectory()

        //return test5thStonePath()
    }

    fun test5thStone(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val startPose = Pose2d(-55.0, 35.0, 0.0.toRadians)

        var builder1 = TrajectoryBuilder(startPose, 0.0.toRadians,constraints)

        builder1
            .splineTo( Pose2d(0.0, 41.0, 0.0.toRadians))
        //    .splineTo( Pose2d(51.0, 31.0, 270.0.toRadians))
            .splineTo( Pose2d(56.0, 34.0, 0.0.toRadians))
            .splineTo( Pose2d(57.0, 34.0, 270.0.toRadians))
            .forward(3.0)
            .splineTo( Pose2d(35.0, 56.0, 20.0.toRadians))
            .splineTo( Pose2d(5.0, 40.0, 0.0.toRadians))

        list.add(builder1.build())

        return list

    }

    fun build5StoneTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()

        var startPose = Pose2d(-33.0, 63.0, 0.0.toRadians)
//        val startPose = Pose2d(50.0, -35.0, 90.0.toRadians)
//        val startPose = Pose2d(-18.0, -35.0, 180.0.toRadians)
        var builder1 = TrajectoryBuilder(startPose, 0.0.toRadians, constraints)

        builder1
            .strafeTo(Vector2d(-59.0, 36.0))

        // 1.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-59.0, 36.0, 0.0.toRadians),0.0.toRadians, constraints)
        builder1
            .strafeTo(Vector2d(-59.0, 39.0))
            .splineTo(Pose2d(0.0,42.0, 0.0.toRadians))
            .splineTo(Pose2d(49.0,35.0, 0.0.toRadians))

        // 2.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(49.0, 35.0, Math.PI + 0.0.toRadians), 0.0.toRadians, constraints)
        builder1
            .splineTo(Pose2d(0.0,37.0, 180.0.toRadians))
            .strafeTo(Vector2d(-35.0,37.0))

        // 3.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-35.0, 37.0, 0.0.toRadians), 0.0.toRadians, constraints)
        builder1
            .strafeTo(Vector2d(-35.0,39.0))
            .splineTo(Pose2d(0.0,42.0, 0.0.toRadians))
            .splineTo(Pose2d(60.0,34.5, 0.0.toRadians))

        // 4.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(60.0, 34.5, 180.0.toRadians), 0.0.toRadians, constraints)
        builder1
            .splineTo(Pose2d(8.0,37.0, 180.0.toRadians))
            .lineTo(Vector2d(-18.0,37.0))

        // 5.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-18.0, 37.0, 0.0.toRadians), 0.0.toRadians, constraints)
        builder1
            .strafeTo(Vector2d(-18.0,39.0))
            .splineTo(Pose2d(0.0,41.0, 0.0.toRadians))
            .splineTo(Pose2d(49.0,34.0, 0.0.toRadians))

        // 6.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(49.0, 36.0, 180.0.toRadians), 0.0.toRadians, constraints)
        builder1
            .splineTo(Pose2d(0.0,37.0, 180.0.toRadians))
            .lineTo(Vector2d(-27.0,37.0))

        // 7.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-27.0, 37.0, 0.0.toRadians), 0.0.toRadians, constraints)
        builder1
            .strafeTo(Vector2d(-27.0,39.0))
            .splineTo(Pose2d(0.0,41.0, 0.0.toRadians))
            .splineTo(Pose2d(60.0,34.0, 0.0.toRadians))

        // 8.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(60.0, 34.0, 180.0.toRadians), 0.0, constraints)
        builder1
            .splineTo(Pose2d(0.0,37.0, 180.0.toRadians))
            .lineTo(Vector2d(-43.0,37.0))

        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-43.0, 37.0, 0.0.toRadians), 0.0, constraints)
        builder1
            .splineTo(Pose2d(0.0,41.0, 0.0.toRadians))
            .splineTo(Pose2d(57.0,34.0, 0.0.toRadians))

        list.add(builder1.build())


        // 9.
        builder1 = TrajectoryBuilder(Pose2d(57.0,35.0, (180.0).toRadians), (0.0).toRadians, constraints)

        builder1
            .strafeTo(Vector2d(57.0, 37.5))
            .splineTo(Pose2d(55.5, 39.0, (90.0).toRadians))
            .lineToConstantHeading(Vector2d(56.5, 29.0))

        // 10.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(56.5, 29.0, 90.0.toRadians),  270.0.toRadians,constraints)
        builder1
            .splineTo(Pose2d(33.0, 57.0, 180.0.toRadians))
            .splineTo( Pose2d(5.0, 40.0, 180.0.toRadians))

        // 10. release 5th stone and park
        list.add(builder1.build())

        return list
    }

    fun drawOffbounds1() {
        GraphicsUtil.fillRect(Vector2d(0.0, 63.0), 18.0, 18.0, false) // robot against the wall
    }

//    fun drawOffbounds2() {
//        GraphicsUtil.fillRect(Vector2d(0.0, 24.0), 12.0, 8.0,true) // block against the bridge
//    }
}
