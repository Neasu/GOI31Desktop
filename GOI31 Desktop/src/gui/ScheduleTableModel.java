package gui;

import javax.swing.table.AbstractTableModel;

import data.FreeLesson;
import data.Lesson;
import data.NormalLesson;
import data.ProxyLesson;

/**
 * 
 * @author Kevin
 *
 */

public class ScheduleTableModel extends AbstractTableModel {

	// Vars
	private static final long serialVersionUID = 1794107760437970394L;
	
	private String columnNames [] = {"Zeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
	private int rows = 11;
	private MainScreen ms;

	// Constructors
	public ScheduleTableModel (MainScreen ms) {
		this.ms = ms;
	}
	
	// Methods
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
		} else if (col == 0) {
			return GUIManager.getProg().getSche().getTimePair(row - 1).getTimePairAsString();
		} else {
			String temp = "";
			Lesson lesson = GUIManager.getProg().getSche().getLesson(col - 1, row - 1);
			NormalLesson nl = null;
			
			if (lesson.getClass().equals(FreeLesson.class)) {
				
				FreeLesson fl = (FreeLesson) lesson;
				
				if (fl.isEntfall()) {
					temp += "Entfall";
				} else {
					temp += "";
				}
				
				return temp;
				
			} else if (lesson.getClass().equals(NormalLesson.class) || lesson.getClass().equals(ProxyLesson.class)) {
				
				nl = (NormalLesson) lesson;
				
//				temp += " Fach: ";
				temp += " ";
				
				if (ms.getCheckBox_3()) {
					temp += nl.getShorty();
				} else {
					temp += nl.getName();
				}
				
				if (lesson.getClass().equals(ProxyLesson.class)) {
					
					temp += " Vertretung!";
				}
				
			}
			
			if (ms.getCheckBox_1()) {
//				temp += " Lehrer: ";
				temp += " ";
				temp += nl.getTeacher();
			}
			
			if (ms.getCheckBox_2()) {
//				temp += " Raum: ";
				temp += " ";
				temp += nl.getRoom();
			}
			
			
			return temp;
		}
	}

	// TODO Tooltips
	
}
