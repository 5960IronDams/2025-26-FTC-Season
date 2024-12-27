package org.firstinspires.ftc.teamcode.auto;

import android.util.Size;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.util.List;

public class Camera {
    //region VisionPortal Properties
    private final VisionPortal _visionPortal;
    //endregion

    //region AprilTag Properties
    private final AprilTagProcessor _aprilTag;
    //endregion

    public Camera(@NonNull LinearOpMode opMode) {
        AprilTagLibrary tagLibrary = AprilTagGameDatabase.getIntoTheDeepTagLibrary();
        Position cameraPosition = new Position(DistanceUnit.INCH,
                0, 0, 0, 0);
        YawPitchRollAngles cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES,
                0, -90, 0, 0);

        _aprilTag = new AprilTagProcessor.Builder()
                .setTagLibrary(tagLibrary)
                .setCameraPose(cameraPosition, cameraOrientation)
                .build();

        String[] cameraNames = new String[]{"GBWC1"};
        Size resolution = new Size(640, 480);
        VisionPortal.StreamFormat streamFormat = VisionPortal.StreamFormat.MJPEG;
        boolean enableLiveView = true;
        boolean startStreamOnBuild = true;
        boolean autoStopLiveView = false;

        _visionPortal = new VisionPortal.Builder()
                .setCamera(opMode.hardwareMap.get(WebcamName.class, cameraNames[0]))
                .setCameraResolution(resolution)
                .enableLiveView(enableLiveView)
                .setStreamFormat(streamFormat)
                .setAutoStartStreamOnBuild(startStreamOnBuild)
                .setAutoStopLiveView(autoStopLiveView)
                .addProcessor(_aprilTag)
                .build();
    }

    //region VisionPortal Methods
    public VisionPortal.CameraState GetState() {
        return _visionPortal.getCameraState();
    }

    public Camera ResumeStreaming() {
        _visionPortal.resumeStreaming();
        return this;
    }

    public Camera StopStreaming() {
        _visionPortal.stopStreaming();
        return this;
    }

    public Camera ResumeLiveView() {
        _visionPortal.resumeLiveView();
        return this;
    }

    public Camera StopLiveView() {
        _visionPortal.stopLiveView();
        return this;
    }

    public Camera Close() {
        _visionPortal.close();
        return this;
    }
    //endregion
    public List<AprilTagDetection> GetDetections() {
        return _aprilTag.getDetections();
    }
    //region AprilTag Methods

    //endregion
}
