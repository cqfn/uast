public class Calc {
    public static void main(String[] args) {
        final double x = calc(1, 2, 3, 4);
    }

    public double calc(final int alpha, final long beta, final float gamma, final double delta) {
        return (alpha + beta) * gamma / delta;
    }
}