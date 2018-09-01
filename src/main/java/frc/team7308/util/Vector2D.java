package frc.team7308.utils;

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
            this.x = Math.cos(angle) * magnitude;
            this.y = Math.sin(angle) * magnitude;
        } else {
            this(magnitude, angle);
        }
    }

    public add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        this.calculateMagnitude();
    }

    public setAngle(float angle) {
        
    }

    public zero() {
        this.x = magnitude;
        this.y = 0.0;
    }

    private calculateMagnitude() {
        this.magnitude = Math.sqrt(this.x * this.x + this.y * this.y);
    }
}