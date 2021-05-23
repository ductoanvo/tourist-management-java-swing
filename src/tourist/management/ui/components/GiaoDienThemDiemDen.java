package tourist.management.ui.components;

import tourist.management.dao.DiemDenDAO;
import tourist.management.database.ConnectDB;
import tourist.management.entity.DiemDen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class GiaoDienThemDiemDen extends JFrame implements MouseListener, ActionListener {
    private final JTextField txtmaDiemDen;
    private final JTextField txttenDiemDen;
    private final JTextField txttenTinh;
    private final JButton btnThemDiemDen;
    private final JTable tableDiemDen;
    private final DefaultTableModel modelDiemDen;
    private JButton btnTimDiemDen;
    private JTextField txtTimDiemDen;
    
    private final DiemDenDAO diemDenDAO;
    JPanel pnlGiaoDienThemDiemDen = new JPanel(new BorderLayout());

    public GiaoDienThemDiemDen() {

        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        diemDenDAO = new DiemDenDAO();

        pnlGiaoDienThemDiemDen.setPreferredSize(new Dimension(600, 600));
        pnlGiaoDienThemDiemDen.setBorder(BorderFactory.createTitledBorder("Địa điểm đến"));


        JPanel pnlGiaoDienThemDiemDenNourth = new JPanel();
        pnlGiaoDienThemDiemDenNourth.setPreferredSize(new Dimension(600, 100));
        pnlGiaoDienThemDiemDen.add(pnlGiaoDienThemDiemDenNourth, BorderLayout.NORTH);
        pnlGiaoDienThemDiemDenNourth.add(new JLabel("Mã điểm đến"));
        txtmaDiemDen = new JTextField(43);
        pnlGiaoDienThemDiemDenNourth.add(txtmaDiemDen);
        pnlGiaoDienThemDiemDenNourth.add(new JLabel("Tên điểm đến"));
        txttenDiemDen = new JTextField(43);
        pnlGiaoDienThemDiemDenNourth.add(txttenDiemDen);
        pnlGiaoDienThemDiemDenNourth.add(new JLabel("Tên tỉnh"));
        txttenTinh = new JTextField(46);
        pnlGiaoDienThemDiemDenNourth.add(txttenTinh);

        JPanel pnlGiaoDienThemDiemDenCenter = new JPanel(new BorderLayout());
        pnlGiaoDienThemDiemDenCenter.setPreferredSize(new Dimension(600, 450));
        pnlGiaoDienThemDiemDenCenter.setBorder(BorderFactory.createTitledBorder("Danh sách điểm đến"));
        pnlGiaoDienThemDiemDen.add(pnlGiaoDienThemDiemDenCenter, BorderLayout.CENTER);
        String[] header = {"Mã Điểm Đến", "Tên Điểm Đến", "Tên tỉnh"};
        modelDiemDen = new DefaultTableModel(header, 0);
        tableDiemDen = new JTable(modelDiemDen);
        pnlGiaoDienThemDiemDenCenter.add(new JScrollPane(tableDiemDen), BorderLayout.CENTER);

        for (DiemDen diemDen : diemDenDAO.getAllDiemDen()) {
            modelDiemDen.addRow(new Object[]{
                    diemDen.getMaDiemDen(),
                    diemDen.getTenDiemDen(),
                    diemDen.getTenTinh()
            });
        }


        JPanel pnlGiaoDienThemDiemDenSouth = new JPanel();
        pnlGiaoDienThemDiemDenSouth.setPreferredSize(new Dimension(600, 50));
        pnlGiaoDienThemDiemDen.add(pnlGiaoDienThemDiemDenSouth, BorderLayout.SOUTH);
        btnThemDiemDen = new JButton("Thêm Điểm Đến");
		txtTimDiemDen = new JTextField(20);
        btnTimDiemDen = new JButton("Tìm Điểm Đến");
        
        
        pnlGiaoDienThemDiemDenSouth.add(txtTimDiemDen);
		pnlGiaoDienThemDiemDenSouth.add(btnTimDiemDen);
		pnlGiaoDienThemDiemDenSouth.add(btnThemDiemDen);


        tableDiemDen.addMouseListener(this);
        btnThemDiemDen.addActionListener(this);
        btnTimDiemDen.addActionListener(this);
    }

    public JPanel createGiaoDienThemDiemDen() {
        return pnlGiaoDienThemDiemDen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = tableDiemDen.getSelectedRow();
        txtmaDiemDen.setText(String.valueOf(tableDiemDen.getValueAt(row, 0)));
        txttenDiemDen.setText(String.valueOf(tableDiemDen.getValueAt(row, 1)));
        txttenTinh.setText(String.valueOf(tableDiemDen.getValueAt(row, 2)));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThemDiemDen)) {
        	if(ktRangBuoc()) {
        		String maDiemDen = txtmaDiemDen.getText();
                String tenDiemDen = txttenDiemDen.getText();
                String tenTinh = txttenTinh.getText();

                DiemDen diemDen = new DiemDen(maDiemDen, tenDiemDen, tenTinh);

                try {
                    diemDenDAO.createDiemDen(diemDen);
                    modelDiemDen.addRow(new Object[]{
                            diemDen.getMaDiemDen(),
                            diemDen.getTenDiemDen(),
                            diemDen.getTenTinh()
                    });
                } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(this, "Trùng");
                }
        	}
        }
        else if(o.equals(btnTimDiemDen)){
			String name = txtTimDiemDen.getText();

			ArrayList<DiemDen> dsdiemDen  = new ArrayList<DiemDen>();
			try {
				if (name.length() > 0) {
					dsdiemDen = diemDenDAO.getDiemDenTheoTen(name);
					DefaultTableModel tableModel = (DefaultTableModel) tableDiemDen.getModel();
					tableModel.setRowCount(0);

					for (DiemDen diemDen : dsdiemDen) {
						tableModel.addRow(new Object[] { 
								diemDen.getMaDiemDen(),
								diemDen.getTenDiemDen(),
								diemDen.getTenTinh()
						});
					}

					tableModel.fireTableDataChanged();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ");

			}
		}
        

    }
    
    
    private boolean ktRangBuoc() {
		String maDiemDen = txtmaDiemDen.getText().trim();
		String tenDiemDen = txttenDiemDen.getText().trim();
		String tenTinh = txttenTinh.getText().trim();


		if (!(maDiemDen.length() > 0 && maDiemDen.matches("^(DD)[0-9]{6}"))) {
			JOptionPane.showMessageDialog(this, " Mã điểm đến bắt đầu bằng 2 ký tự “DD”, theo sau là 6 ký tự là số");
			return false;
		}

		if (!(tenDiemDen.length() > 0 && tenDiemDen.matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$"))) {
			JOptionPane.showMessageDialog(this,"Tên điểm đến không chứa các ký tự số và ký tự đặc biệt.");
			return false;
		}
		if (!(tenTinh.length() > 0 && tenTinh.matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$"))) {
			JOptionPane.showMessageDialog(this,"Tên tỉnh không chứa các ký tự số và ký tự đặc biệt.");
			return false;
		}
		

		return true;

	}
    
}
