package frc.team7308.robot.util;

public class Vector2D {
    public float x;
    public float y;
    public float magnitude;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
        this.calculateMagnitude();
    }

    public Vector2D(float magnitude, float angle, boolean magAngle) {
        if (magAngle) {
            this.magnitude = magnitude;
            this.x = (float) (Math.cos(angle) * magnitude);
            this.y = (float) (Math.sin(angle) * magnitude);
        } else {
            this.x = x;
            this.y = y;
            this.calculateMagnitude();
        }
    }

    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        this.calculateMagnitude();
    }

    public void setAngle(float angle) {
        
    }

    public void zero() {
        this.x = magnitude;
        this.y = 0.0f;
    }

    private void calculateMagnitude() {
        this.magnitude = (float) (Math.sqrt(this.x * this.x + this.y * this.y));
    }
}