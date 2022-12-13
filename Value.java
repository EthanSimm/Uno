public enum Value {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_TWO, SKIP, REVERSE, WILD, WILD_DRAW_FOUR;

    public static Value[] values = Value.values();

    public static Value getValue(int id) {
        return values[id];
    }

    public static int getValueIndex(Value value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
