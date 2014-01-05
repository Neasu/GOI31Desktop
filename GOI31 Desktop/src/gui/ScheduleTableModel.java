package gui;

import javax.swing.table.AbstractTableModel;
import gui.GUIManager;

public class ScheduleTableModel extends AbstractTableModel {
	
	private String columnNames [] = {"Zeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
	private int rows = 11;

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return rows;
	}
	
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public boolean isCellEditable(int row, int col)
    { return false; }

	public Object getValueAt(int row, int col) {
		if (row == 0) {
			return columnNames[col];
		}
		
		if (col == 0) {
			return GUIManager.prog.sche.getTimePair(row - 1).getTimePairAsString();
		}
		return "YOLO";
	}

}
