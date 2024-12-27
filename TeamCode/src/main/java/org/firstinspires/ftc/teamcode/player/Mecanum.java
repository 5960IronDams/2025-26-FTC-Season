package org.firstinspires.ftc.teamcode.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.FourWheelDrive;

public class Mecanum {
    private final LinearOpMode _opMode;
    private final FourWheelDrive _drive;
    private final Governor _governor;

    /**
     * Will allow the player to use all the functionality of the mecanum wheels.
     * @param opMode The linearOpMode calling the class.
     */
    public Mecanum(LinearOpMode opMode) {
        _opMode = opMode;
        _governor = new Governor(opMode)
                .setMin(0.4)
                .setMax(1.0);

        _drive = new FourWheelDrive(opMode)
            .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Accepts input from the driver's controller.
     * The left OR right trigger on game pad #1 will toggle the governor percentage.
     * The left AND right trigger on game pad #1 will enable/disable the governor.
     * The left stick x on game pad #1 will turn the robot.
     * The right stick x & y on game pad #1 will drive & strafe the robot.
     * @return The current instance
     */
    public Mecanum run() {
        boolean isPressingTrigger = _opMode.gamepad1.left_trigger != 0 || _opMode.gamepad1.right_trigger != 0;
        boolean isPressingTriggers = _opMode.gamepad1.left_trigger != 0 && _opMode.gamepad1.right_trigger != 0;

        double governor = _governor
                .toggleIsEnabled(isPressingTriggers)
                .toggleActive(isPressingTrigger)
                .getActive();

        double vertical = _opMode.gamepad1.right_stick_y;
        double horizontal = -_opMode.gamepad1.right_stick_x;
        double pivot = -_opMode.gamepad1.left_stick_x;

        double flp = (pivot + vertical + horizontal) * governor;
        double frp = (-pivot + (vertical - horizontal)) * governor;
        double rlp = (pivot + (vertical - horizontal)) * governor;
        double rrp = (-pivot + vertical + horizontal) * governor;

        _drive.setPower(flp, frp, rlp, rrp);

        return this;
    }

    public void getTelemetry() {
        _governor.getTelemetry();
        _drive.getTelemetry();
    }
}
