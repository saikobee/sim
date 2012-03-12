public abstract class Sector {
    protected int number;

    public Sector(int number) {
        this.number = number;
    }

    public String toString() {
        return "Sector";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
