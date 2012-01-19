public class SwapPlayer implements Player {
    public int     guess()  { return Util.random.nextInt(3); }
    public boolean change() { return true; }
}
