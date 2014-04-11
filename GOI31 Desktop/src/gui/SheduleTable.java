package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import data.FreeLesson;
import data.Lesson;
import data.ProxyLesson;

public class SheduleTable extends JTable {

	private Color red = Color.RED;
	private Color yel = Color.YELLOW;

	private static final long serialVersionUID = 1L;

	public SheduleTable(AbstractTableModel model) {
		super(model);
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component comp = super.prepareRenderer(renderer, row, column);
		JComponent jc = (JComponent) comp;// for Custom JComponent

		comp.setForeground(Color.black);
		comp.setBackground(Color.WHITE);

		if (column == 0 || row == 0) {
			comp.setBackground(Color.LIGHT_GRAY);
		} else {
			Lesson lesson = GUIManager.getProg().getSche().getLesson(column - 1, row - 1);

			if (lesson.getClass().equals(FreeLesson.class)) {

				FreeLesson fl = (FreeLesson) lesson;

				if (fl.isEntfall()) {
					comp.setBackground(red);
				}
			} else if (lesson.getClass().equals(ProxyLesson.class)) {
				comp.setBackground(yel);
			}
		}

		convertRowIndexToView(0);

		return comp;
	}
}
