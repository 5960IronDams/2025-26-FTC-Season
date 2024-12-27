package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Locale;

public class FourWheelDrive {
    private final LinearOpMode _opMode;

    private final DcMotor _frontLeft;
    private final DcMotor _frontRight;
    private final DcMotor _rearLeft;
    private final DcMotor _rearRight;

    public FourWheelDrive(LinearOpMode opMode) {
        _opMode = opMode;

        _frontLeft = opMode.hardwareMap.get(DcMotor.class, "fl");
        _frontRight = opMode.hardwareMap.get(DcMotor.class, "fr");
        _rearLeft = opMode.hardwareMap.get(DcMotor.class, "rl");
        _rearRight = opMode.hardwareMap.get(DcMotor.class, "rr");

        setLeftDirection(DcMotor.Direction.FORWARD)
            .setRightDirection(DcMotor.Direction.REVERSE)
            .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public FourWheelDrive setLeftDirection(DcMotor.Direction direction) {
        _frontLeft.setDirection(direction);
        _rearLeft.setDirection(direction);
        return this;
    }

    public FourWheelDrive setRightDirection(DcMotor.Direction direction) {
        _frontRight.setDirection(direction);
        _rearRight.setDirection(direction);
        return this;
    }

    public FourWheelDrive setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        _frontRight.setZeroPowerBehavior(behavior);
        _frontLeft.setZeroPowerBehavior(behavior);
        _rearRight.setZeroPowerBehavior(behavior);
        _rearLeft.setZeroPowerBehavior(behavior);
        return this;
    }

    public FourWheelDrive setMode(DcMotor.RunMode mode) {
        _frontRight.setMode(mode);
        _frontLeft.setMode(mode);
        _rearRight.setMode(mode);
        _rearLeft.setMode(mode);
        return this;
    }

    public FourWheelDrive setPower(double flp, double frp, double rlp, double rrp) {
        _frontLeft.setPower(flp);
        _frontRight.setPower(frp);
        _rearLeft.setPower(rlp);
        _rearRight.setPower(rrp);
        return this;
    }

    public void getTelemetry() {
        _opMode.telemetry.addLine(String.format(Locale.US ,"Drive: Mode:=%s", _frontLeft.getMode()));
        _opMode.telemetry.addLine(String.format(Locale.US ,"Drive: Stop Behavior:=%s", _frontLeft.getZeroPowerBehavior()));
        _opMode.telemetry.addLine("Drive: Motor Order:= fl, fr, rl, rr");
        _opMode.telemetry.addLine(String.format(Locale.US, "Drive: Direction:=%s, %s, %s, %s",
                _frontLeft.getDirection(), _frontRight.getDirection(), _rearLeft.getDirection(), _rearLeft.getDirection()));
        _opMode.telemetry.addLine(String.format(Locale.US ,"Drive: Powers:=%.2f, %.2f, %.2f, %.2f",
                _frontLeft.getPower(), _frontRight.getPower(), _rearLeft.getPower(), _rearRight.getPower()));
    }
}
