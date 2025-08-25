package fr.jbadaire.avaj.models;

import fr.jbadaire.avaj.exceptions.BadCoordinatesException;

public class Coordinates {

    private long longitude;
    private long latitude;
    private byte height;

    Coordinates(long longitude, long latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = (byte) Math.min(height, 100);
    }

    public Coordinates add(long longitude, long latitude, byte height) {
        this.longitude += longitude;
        this.latitude += latitude;
        this.height = (byte) Math.min(this.height + height, 100);
        return this;
    }

    public Coordinates add(Coordinates coordinates) {
        return this.add(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
    }

    public Coordinates substract(long longitude, long latitude, byte height) throws BadCoordinatesException {
        if (longitude > this.longitude || latitude > this.latitude) {
            throw new BadCoordinatesException("The result of the substraction is negative.");
        }
        this.longitude -= longitude;
        this.latitude -= latitude;
        this.height = (byte) Math.max(this.height - height, 0);
        return this;
    }

    public Coordinates substract(Coordinates coordinates) throws BadCoordinatesException {
        return this.substract(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
    }

    public static Coordinates from(long longitude, long latitude, int height) throws BadCoordinatesException {
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new BadCoordinatesException("Coordinates are invalid, The coordinates must be positive.");
        }
        return new Coordinates(longitude, latitude, height);
    }

    public static Coordinates from(String longitudeStr, String latitudeStr, String heightStr) throws BadCoordinatesException {
        try {
            final long longitude = Long.parseLong(longitudeStr);
            final long latitude = Long.parseLong(latitudeStr);
            final int height = Integer.parseInt(heightStr);
            return Coordinates.from(longitude, latitude, height);
        } catch (NumberFormatException exception) {
            throw new BadCoordinatesException("Unable to transform coordinates string(s) to integer(s).");
        }
    }

    public long getLongitude() {
        return longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public byte getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", height=" + height +
                '}';
    }
}
