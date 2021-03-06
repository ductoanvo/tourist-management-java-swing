package tourist.management.ui.layouts;

import tourist.management.dao.DatVeDAO;
import tourist.management.database.ConnectDB;
import tourist.management.entity.DatVe;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GiaoDienLichSuDatVe extends JFrame implements ActionListener {
    private final DefaultTableModel model;
    private final JTable table;
    private final JPanel pnlSouth;
    private final JTextField txtTim;
    private final JButton btnTim;

    private final JPanel pnlGiaoDienLichSuDatVe = new JPanel(new BorderLayout());
    private final DatVeDAO lichSuDatVeDAO;
    private final JButton btnXoaTim;
    private Object String;
    private JTextField txtXoaTim;

    public GiaoDienLichSuDatVe() {
        // kết nối CSDL

        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lichSuDatVeDAO = new DatVeDAO();
        pnlGiaoDienLichSuDatVe.setPreferredSize(new Dimension(600, 600));
        pnlGiaoDienLichSuDatVe.setBorder(BorderFactory.createTitledBorder("Lịch sử đặt vé"));

        String[] header = {"Mã Khách Hàng", "Mã Chuyến Đi", "Mã Nhân Viên", "Ngày Đặt Vé"};
        model = new DefaultTableModel(header, 0);
        table = new JTable(model);

        TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                if (value instanceof LocalDateTime) {
                    value = ((LocalDateTime) value).format(dateTimeFormatter);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        table.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
        table.setShowGrid(false);
        table.setRowHeight(20);

        JSplitPane spl = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        spl.setBottomComponent(new JScrollPane(table));
        pnlGiaoDienLichSuDatVe.add(spl);

        pnlSouth = new JPanel();
        pnlSouth.add(txtTim = new JTextField(10));
        pnlSouth.add(btnTim = new JButton("Tìm theo mã khách hàng"));
        pnlSouth.add(btnXoaTim = new JButton("Xóa rỗng"));
        pnlGiaoDienLichSuDatVe.add(pnlSouth, BorderLayout.SOUTH);

// load dữ liệu lên JTABLE
        for (DatVe datVe : lichSuDatVeDAO.getAllDatVe()) {
            model.addRow(new Object[]{datVe.getKhachHang().getMaKhachHang(), datVe.getChuyenDi().getMaChuyenDi(),
                    datVe.getNhanVien().getMaNhanVien(), datVe.getNgayDatVe()});
        }

        btnTim.addActionListener(this);
        btnXoaTim.addActionListener(this);
    }

    public JPanel createGiaoDienLichSuDatVe() {
        return pnlGiaoDienLichSuDatVe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(btnTim)) {
            String name = txtTim.getText();

            List<DatVe> lsDatVe;
            try {
                if (name.length() > 0) {
                    lsDatVe = lichSuDatVeDAO.getThongTinDatVeTheoMaKH(name);
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.setRowCount(0);


                    for (DatVe lichSuDatVe : lsDatVe) {
                        tableModel.addRow(new Object[]{lichSuDatVe.getKhachHang().getMaKhachHang(),
                                lichSuDatVe.getChuyenDi().getMaChuyenDi(), lichSuDatVe.getNhanVien().getMaNhanVien(),
                                lichSuDatVe.getNgayDatVe()});
                    }

                    tableModel.fireTableDataChanged();
                } else if (name.equals("")) {
                    JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ");
            }
        }
        if (o.equals(btnXoaTim)) {
            txtTim.setText("");
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);
            //lấy DefaultTableModel từ JTable và sau đó thêm Cột vào đó.

            for (DatVe datVe : lichSuDatVeDAO.getAllDatVe()) {
                model.addRow(new Object[]{datVe.getKhachHang().getMaKhachHang(), datVe.getChuyenDi().getMaChuyenDi(),
                        datVe.getNhanVien().getMaNhanVien(), datVe.getNgayDatVe()});
            }
        }

    }


}
