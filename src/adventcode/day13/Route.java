package adventcode.day13;

public class Route implements Comparable<Route>{

    private long frequency;

    private int position;

    public Route(long frequency, int position) {
        this.frequency = frequency;
        this.position = position;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int compareTo(Route route) {

        long compareFrequency= route.getFrequency();
        /* For Ascending order*/
        return (int) (this.frequency-compareFrequency);
    }
}
