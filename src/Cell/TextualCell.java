package Cell;

public class TextualCell extends Cell<String>{

    public TextualCell(int row, int col, String value, Cell<?>[][] matrixCells) throws Exception{
        super(row, col, value, matrixCells);
    }
    @Override
    public boolean isNumeric() {
        return false;
    }
    @Override
    public String convert(String value) {
        return value;
    }
    @Override
    public boolean isValid(String value) {
        return true;
    }
}
