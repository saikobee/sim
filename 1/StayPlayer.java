public class StayPlayer implements Player {
    public int     guess()  { return Util.random.nextInt(3); }
    public boolean change() { return false; }
    public String  type()   { return "Stay"; }
}
