package views;

import utilities.Guard;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Product table model
 */
class RecordsTableModel extends AbstractTableModel {
    private final List<TableRecord> records;
    private final String[] columns = new String[]
            {
                    "Name",
                    "Price"
            };

    /**
     * Creates product table model
     * @param records products
     */
    public RecordsTableModel(final List<TableRecord> records) {
        Guard.notNull(records, "records");

        this.records = records;
    }

    @Override
    public int getRowCount() {
        return records.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(final int index){
        return columns[index];
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        switch(columnIndex){
            case 0:
                return records.get(rowIndex).getName();
            case 1:
                return  records.get(rowIndex).getPrice();
            default:
                throw new IllegalArgumentException();
        }
    }
}
