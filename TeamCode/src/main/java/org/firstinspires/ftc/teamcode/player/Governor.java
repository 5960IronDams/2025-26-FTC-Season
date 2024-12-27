package org.firstinspires.ftc.teamcode.player;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.Locale;

public class Governor {
    private final LinearOpMode _opMode;

    private double _min = 1;
    private double _max = 1;
    private final long SLEEP_DELAY = 300;

    private double _active = 1;
    private boolean _isEnabled = true;

    public Governor(@NonNull LinearOpMode opMode) {
        _opMode = opMode;
    }

    /**
     * Will set the minimum governor limit and set the active governor to the min.
     * @param min Defaults to 1. The minimun % to apply to the motor power. Range:=0-1
     * @return The current instance
     */
    public Governor setMin(double min) {
        _min = min;
        _active = min;
        return this;
    }

    /**
     * Will set the maximum governor limit and set the active governor to the max.
     * @param max Defaults to 1. The maximum % to apply to the motor power. Range:=0-1
     * @return The current instance
     */
    public Governor setMax(double max) {
        _max = max;
        _active = max;
        return this;
    }

    /**
     * Will toggle between the minimum & maximum governor settings.
     * @param changeTriggered Will run in an opMode loop. This tells us if the user is activating a change or not.
     * @return The current instance
     */
    public Governor toggleActive(boolean changeTriggered) {
        if (changeTriggered) {
            _active = _active == _min ? _max : _min;
            _opMode.sleep(SLEEP_DELAY);
        }
        return this;
    }

    /**
     * Gets the active governor value.
     * @return The active governor %. Range:=0-1
     */
    public double getActive() {
        return _isEnabled ? _active : 1.0;
    }

    public Governor toggleIsEnabled(boolean changeTriggered) {
        if (changeTriggered) {
            _isEnabled = !_isEnabled;
            _opMode.sleep(SLEEP_DELAY);
        }
        return this;
    }

    public void getTelemetry() {
        _opMode.telemetry.addLine(String.format(Locale.US, "Governor: IsEnabled:=%B, Min:=%.2f, Max:=%.2f, Active:=%.2f", _isEnabled, _min, _max, _active));
    }
}
