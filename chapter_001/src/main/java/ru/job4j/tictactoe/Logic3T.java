package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = true;
        for (int i = 0; i <= table.length - 1; i++) {
            if ((!table[i][i].hasMarkX())) {
                result = false;
                break;
            }
        }

        if (!result) {
            result = true;
            for (int i = 0; i <= table.length - 1; i++) {
                if ((!table[i][table.length - 1 - i].hasMarkX())) {
                    result = false;
                    break;
                }
            }
        }

        if (!result) {
            for (int j = 0; j <= table.length - 1; j++) {
                if (result) {
                    break;
                } else {
                    result = true;
                }
                for (int i = 0; i <= table.length - 1; i++) {
                    if ((!table[i][j].hasMarkX())) {
                        result = false;
                        break;
                    }
                }

            }
        }

        if (!result) {
            for (int j = 0; j <= table.length - 1; j++) {
                if (result) {
                    break;
                } else {
                    result = true;
                }
                for (int i = 0; i <= table.length - 1; i++) {
                    if ((!table[j][i].hasMarkX())) {
                        result = false;
                        break;
                    }
                }

            }
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = true;
        for (int i = 0; i <= table.length - 1; i++) {
            if ((!table[i][i].hasMarkO())) {
                result = false;
                break;
            }
        }

        if (!result) {
            result = true;
            for (int i = 0; i <= table.length - 1; i++) {
                if ((!table[i][table.length - 1 - i].hasMarkO())) {
                    result = false;
                    break;
                }
            }
        }

        if (!result) {
            for (int j = 0; j <= table.length - 1; j++) {
                if (result) {
                    break;
                } else {
                    result = true;
                }
                for (int i = 0; i <= table.length - 1; i++) {
                    if ((!table[i][j].hasMarkO())) {
                        result = false;
                        break;
                    }
                }

            }
        }

        if (!result) {
            for (int j = 0; j <= table.length - 1; j++) {
                if (result) {
                    break;
                } else {
                    result = true;
                }
                for (int i = 0; i <= table.length - 1; i++) {
                    if ((!table[j][i].hasMarkO())) {
                        result = false;
                        break;
                    }
                }

            }
        }
        return result;
    }

    public boolean hasGap() {
        int count = 0;
        boolean result = true;
        for (int j = 0; j <= table.length - 1; j++) {
            for (int i = 0; i <= table.length - 1; i++) {
                if (table[i][j].hasMarkX()) {
                    count++;
                }
                if (table[i][j].hasMarkO()) {
                    count++;
                }
            }
        }
        if (count > 8) {
            result = false;
        }
        return result;
    }
}