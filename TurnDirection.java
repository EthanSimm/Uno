public enum TurnDirection {
    CLOCKWISE, COUNTERCLOCKWISE;

    public static TurnDirection[] turnDirections = TurnDirection.values();

    public static TurnDirection getTurnDirection(int id) {
        return turnDirections[id];
    }
}
