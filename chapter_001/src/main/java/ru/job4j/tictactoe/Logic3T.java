package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX(Figure3T[][] table) {
        boolean result = true;
        Figure3T X = new Figure3T(true);
        for (int i = 0; i <= table.length - 1; i++) {
            if ((table[i][i] != X) || (table[i][table.length - 1 - i] != X)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = true;
        Figure3T X = new Figure3T(false);
        for (int i = 0; i <= table.length - 1; i++) {
            if ((table[i][i] != X) || (table[i][table.length - 1 - i] != X)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean hasGap() {
        return true;
    }
}
