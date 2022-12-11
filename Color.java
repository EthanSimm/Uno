public enum Color {
    RED, YELLOW, BLUE, GREEN, WILD;

    public static Color[] colors = Color.values();

    public static Color getColor(int id) {
        return colors[id];
    }
}
