public enum Value {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_TWO, SKIP, REVERSE, WILD, WILD_DRAW_FOUR;

    public static Value[] values = Value.values();

    public static Value getValue(int id) {
        return values[id];
    }
}
