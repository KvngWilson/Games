package Chess;

public enum Color {
    WHITE, BLACK;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
