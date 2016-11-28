package bridge;

import XmlMethods.SchemaFile;
import extractInformation.UsedDataBase;

public class PrintTable {
    public String[] getColFromSchemma(final String tableName) {
	return SchemaFile.read(UsedDataBase.getUsedDataBase(), tableName).split(",");
    }

    public void show(final String table[][], final String tableName, String[] data) {

	for (int i = 0; i < table.length; i++) {
	    for (int j = 0; j < table[i].length; j++) {
		table[i][j] = table[i][j].replaceAll("_", " ");
	    }
	}

	if (data[0].equals("*")) {
	    data = getColFromSchemma(tableName);
	}
	try {
	    if (table.length == 0) {
		throw new RuntimeException();
	    }
	    final int rows = table.length;
	    final int cols = data.length;
	    final int[] maxSize = method(data, table, rows, cols);

	    printMethod(table, maxSize);

	} catch (final Exception e) {
	    System.out.println("sql command failed");
	}
    }

    private int[] method(final String[] data, final String[][] table, final int rows, final int cols) {
	int maxSize[];
	maxSize = new int[data.length];
	for (int i = 0; i < data.length; i++) {
	    maxSize[i] = data[i].length();
	}

	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		if (maxSize[j] < table[i][j].length()) {
		    maxSize[j] = table[i][j].length();
		}
	    }
	}

	for (int i = 0; i < maxSize.length; i++) {
	}
	for (int i = 0; i < maxSize.length; i++) {
	    System.out.print("+-");
	    for (int j = 0; j < maxSize[i]; j++) {
		System.out.print("-");
	    }
	    System.out.print("-");
	}
	System.out.println("+");
	System.out.print("|");
	for (int i = 0; i < data.length; i++) {
	    final int maxSizeNow = maxSize[i];
	    System.out.print(" ");
	    System.out.print(data[i]);
	    for (int j = 0; j < maxSizeNow + 2 - data[i].length() - 1; j++) {
		System.out.print(" ");
	    }
	    if (data.length != i + 1) {
		System.out.print("|");
	    }
	}
	System.out.println("|");
	for (int i = 0; i < maxSize.length; i++) {
	    System.out.print("+-");
	    for (int j = 0; j < maxSize[i]; j++) {
		System.out.print("-");
	    }
	    System.out.print("-");
	}
	System.out.println("+");
	System.out.print("|");
	return maxSize;
    }

    private void printMethod(final String[][] table, final int[] maxSize) {
	for (int i = 0; i < table.length; i++) {
	    for (int k = 0; k < table[i].length; k++) {
		System.out.print(" ");
		System.out.print(table[i][k]);
		final int restSpace = maxSize[k] - table[i][k].length() + 1;
		for (int j = 0; j < restSpace; j++) {
		    System.out.print(" ");
		}
		System.out.print("|");
	    }
	    System.out.println();
	    if (table.length != i + 1) {
		System.out.print("|");
	    }
	}
	for (int i = 0; i < maxSize.length; i++) {
	    System.out.print("+-");
	    for (int j = 0; j < maxSize[i]; j++) {
		System.out.print("-");
	    }
	    System.out.print("-");
	}
	System.out.println("+");
    }

}
