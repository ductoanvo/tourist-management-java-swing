package tourist.management.ui.layouts;

import tourist.management.dao.ChuyenDiDAO;
import tourist.management.dao.DatVeDAO;
import tourist.management.dao.KhachHangDAO;
import tourist.management.database.ConnectDB;
import tourist.management.entity.ChuyenDi;
import tourist.management.entity.DatVe;
import tourist.management.entity.KhachHang;
import tourist.management.entity.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GiaoDienDatVe extends JFrame implements ActionListener {
    private final JPanel pnlNor;
    private final JPanel pnlCen;
    private final DefaultTableModel model;
    private final JTable table;
    private final JPanel pnlSou;
    private final JButton btnThem;
    private final JButton btnXoaRong;
    private final JLabel lblMaKhachHang;
    private final JTextField txtMaKhachHang;
    private final JLabel lblHoKhachHang;
    private final JTextField txtHoKhachHang;
    private final JLabel lblTenKhachHang;
    private final JTextField txtTenKhachHang;
    private final JLabel lblEmail;
    private final JTextField txtEmail;
    private final JLabel lblCMND;
    private final JTextField txtCMND;
    private final JLabel lblSoDienThoai;
    private final JTextField txtSoDienThoai;
    private final JLabel lblNgaySinh;
    private final JTextField txtNgaySinh;
    private final JLabel lblGioiTinh;
    private final ButtonGroup groupGioiTinh;
    private final JRadioButton radNam;
    private final JRadioButton radNu;
    private final JPanel pnlNor_bottom;
    private final JPanel pnlNor_top;
    private final JLabel lblTieuDe;
    private final JTextField txtTimDiemDen;
    private final JButton btntimDiemDen;
    private final JButton btnCapNhatChuyenDi;
    private final JButton btnXemDanhSachKhachhang;

    private final ChuyenDiDAO chuyenDiDAO;
    private final KhachHangDAO khachHangDAO;
    private final DatVeDAO datVeDAO;

    private final JPanel pnlGiaoDienDatVe = new JPanel();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public GiaoDienDatVe() {

        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        chuyenDiDAO = new ChuyenDiDAO();
        khachHangDAO = new KhachHangDAO();
        datVeDAO = new DatVeDAO();


        pnlGiaoDienDatVe.setPreferredSize(new Dimension(600, 600));
        pnlGiaoDienDatVe.setBorder(BorderFactory.createTitledBorder("?????t v??"));


        //Ph???n North
        pnlGiaoDienDatVe.add(pnlNor = new JPanel());
        pnlNor.setPreferredSize(new Dimension(570, 210));
        pnlGiaoDienDatVe.add(pnlNor, BorderLayout.NORTH);

        pnlNor.add(pnlNor_top = new JPanel());
        pnlNor.add(pnlNor_top, BorderLayout.SOUTH);
        pnlNor_top.setPreferredSize(new Dimension(570, 50));
        pnlNor_top.add(lblTieuDe = new JLabel("TH??NG TIN ?????T V??", JLabel.CENTER));
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));

        pnlNor.add(pnlNor_bottom = new JPanel());
        pnlNor.add(pnlNor_bottom, BorderLayout.SOUTH);
        pnlNor_bottom.setPreferredSize(new Dimension(570, 170));
        pnlNor_bottom.add(lblMaKhachHang = new JLabel("M?? Kh??ch H??ng"));
        pnlNor_bottom.add(txtMaKhachHang = new JTextField(57));
        pnlNor_bottom.add(lblHoKhachHang = new JLabel("H??? Kh??ch H??ng"));
        pnlNor_bottom.add(txtHoKhachHang = new JTextField(23));
        pnlNor_bottom.add(lblTenKhachHang = new JLabel("T??n Kh??ch H??ng"));
        pnlNor_bottom.add(txtTenKhachHang = new JTextField(22));
        pnlNor_bottom.add(lblEmail = new JLabel("Email"));
        pnlNor_bottom.add(txtEmail = new JTextField(63));
        pnlNor_bottom.add(lblCMND = new JLabel("CMND"));
        pnlNor_bottom.add(txtCMND = new JTextField(63));
        pnlNor_bottom.add(lblSoDienThoai = new JLabel("S??T"));
        pnlNor_bottom.add(txtSoDienThoai = new JTextField(21));
        pnlNor_bottom.add(lblNgaySinh = new JLabel("Ng??y sinh"));
        pnlNor_bottom.add(txtNgaySinh = new JTextField(18));
        pnlNor_bottom.add(lblGioiTinh = new JLabel("Gi???i t??nh"));
        pnlNor_bottom.add(radNam = new JRadioButton("Nam"));
        pnlNor_bottom.add(radNu = new JRadioButton("N???"));
        groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(radNam);
        groupGioiTinh.add(radNu);
        radNam.setSelected(true);

        //Ph???n Center
        pnlGiaoDienDatVe.add(pnlCen = new JPanel(new BorderLayout()));
        pnlGiaoDienDatVe.add(pnlCen, BorderLayout.CENTER);
        pnlCen.setPreferredSize(new Dimension(570, 250));
        pnlCen.setBorder(BorderFactory.createTitledBorder("Danh S??ch Chuy???n ??i"));

        pnlCen.setLayout(new BoxLayout(pnlCen, BoxLayout.PAGE_AXIS));
        String[] columnName = {"M?? chuy???n ??i", "??i???m xu???t ph??t", "??i???m ?????n", "Ng??y gi??? ??i", "Ng??y gi??? ?????n", "Bi???n s??? xe"};

        //table
        model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);
        table.setShowGrid(false);
        table.setRowHeight(20);

        JScrollPane TablePane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlCen.add(TablePane);

        List<ChuyenDi> danhSachChuyenDi = chuyenDiDAO.getAllChuyenDi();


        for (ChuyenDi chuyenDi : danhSachChuyenDi) {
            model.addRow(new Object[]{
                    chuyenDi.getMaChuyenDi(),
                    chuyenDi.getDiemXuatPhat().getMaDiemXuatPhat(),
                    chuyenDi.getDiemDen().getMaDiemDen(),
                    dateTimeFormatter.format(chuyenDi.getNgayGioDi()),
                    dateTimeFormatter.format(chuyenDi.getNgayGioDen()),
                    chuyenDi.getBienSoXe()
            });
        }

        //Ph???n South
        pnlGiaoDienDatVe.add(pnlSou = new JPanel());
        pnlSou.setPreferredSize(new Dimension(570, 70));
        pnlGiaoDienDatVe.add(pnlSou, BorderLayout.SOUTH);
        pnlSou.add(btnThem = new JButton("?????t v??"));
        pnlSou.add(btnXoaRong = new JButton("X??a r???ng"));
        txtTimDiemDen = new JTextField(15);
        pnlSou.add(txtTimDiemDen);
        btntimDiemDen = new JButton("T??m theo m?? ??i???m ?????n");
        pnlSou.add(btntimDiemDen);
        btnCapNhatChuyenDi = new JButton("C???p nh???t chuy???n ??i");
        pnlSou.add(btnCapNhatChuyenDi);
        btnXemDanhSachKhachhang = new JButton("Danh s??ch kh??ch h??ng");
        pnlSou.add(btnXemDanhSachKhachhang);

        btnThem.addActionListener(this);
        btnXoaRong.addActionListener(this);
        btntimDiemDen.addActionListener(this);
        btnCapNhatChuyenDi.addActionListener(this);
        btnXemDanhSachKhachhang.addActionListener(this);
    }

    public JPanel createGiaoDienDatVe() {
        return pnlGiaoDienDatVe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            datVeHandler();
        } else if (o.equals(btnXoaRong)) {
            xoaRongHandler();
        } else if (o.equals(btntimDiemDen)) {
            timChuyenDiTheodiemDen();
        } else if (o.equals(btnCapNhatChuyenDi)) {
            capNhatChuyenDi();
        } else if (o.equals(btnXemDanhSachKhachhang)) {
            new GiaoDienTraCuuKhachHang().setVisible(true);
        }
    }

    private void datVeHandler() {
        int row = table.getSelectedRow();
        if (row != -1) {
            String maKhachHang = txtMaKhachHang.getText();
            String hoKhachhang = txtHoKhachHang.getText();
            String tenKhachhang = txtTenKhachHang.getText();
            boolean gioiTinh = radNu.isSelected();
            LocalDate ngaySinh;
            String soCMND = txtCMND.getText();
            String soDienThoai = txtSoDienThoai.getText();
            String email = txtEmail.getText();

            try {
                ngaySinh = LocalDate.parse(txtNgaySinh.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException dateTimeParseException) {
                JOptionPane.showMessageDialog(this, "Ng??y sinh kh??ng h???p l???");
                txtNgaySinh.selectAll();
                txtNgaySinh.requestFocus();
                return;
            }

            if (!Pattern.matches("^(kh|KH)\\d{6}", maKhachHang)) {
                JOptionPane.showMessageDialog(this, "M?? kh??ch h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i!");
                txtMaKhachHang.selectAll();
                txtMaKhachHang.requestFocus();
                return;
            } else if (!Pattern.matches("^[a-zA-Z_????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\\s]+$", hoKhachhang)) {
                JOptionPane.showMessageDialog(this, "H??? kh??ch h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i!");
                txtHoKhachHang.selectAll();
                txtHoKhachHang.requestFocus();
                return;
            } else if (!Pattern.matches("^[a-zA-Z_????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\\s]+$", tenKhachhang)) {
                JOptionPane.showMessageDialog(this, "T??n kh??ch h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i!");
                txtTenKhachHang.selectAll();
                txtTenKhachHang.requestFocus();
                return;
            } else if (!Pattern.matches("^(\\w{1,})@(\\w{1,})(\\.(\\w{2,})){1,}", email)) {
                JOptionPane.showMessageDialog(this, "Email kh??ng h???p l???, vui l??ng ki???m tra l???i!");
                txtSoDienThoai.selectAll();
                txtSoDienThoai.requestFocus();
                return;
            } else if (!Pattern.matches("\\d{12}|\\d{9}", soCMND)) {
                JOptionPane.showMessageDialog(this, "S??? CMND kh??ng h???p l???, vui l??ng ki???m tra l???i!");
                txtCMND.selectAll();
                txtCMND.requestFocus();
                return;
            } else if (!Pattern.matches("^(0)(96|97|98|32|33|34|35|36|37|38|39|88|91|94|83|84|85|86|81|82|89|90|93|70|79|77|76|78|92|56|58|99|59){1}\\d{7}", soDienThoai)) {
                JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng h???p l???, vui l??ng ki???m tra l???i!");
                txtSoDienThoai.selectAll();
                txtSoDienThoai.requestFocus();
                return;
            }
            String maNhanVien = "NV000001";
            KhachHang khachHang = new KhachHang(
                    maKhachHang,
                    hoKhachhang,
                    tenKhachhang,
                    gioiTinh,
                    ngaySinh,
                    soCMND,
                    soDienThoai,
                    email
            );
            DatVe datVe = new DatVe(
                    khachHang,
                    new ChuyenDi(table.getValueAt(row, 0).toString()),
                    new NhanVien(maNhanVien),
                    LocalDateTime.now()
            );


            List<KhachHang> danhSachKhachHang = khachHangDAO.getAllKhachHang();
            if (danhSachKhachHang.contains(khachHang))
                return;
            khachHangDAO.createKhachHang(khachHang);
            datVeDAO.createDatVe(datVe);
            JOptionPane.showMessageDialog(this, "?????t v?? th??nh c??ng!");
            xoaRongHandler();
        }
        else{
            JOptionPane.showMessageDialog(this, "Ch??a ch???n chuy???n ??i!");
        }
    }

    private void xoaRongHandler() {
        txtMaKhachHang.setText("");
        txtHoKhachHang.setText("");
        txtEmail.setText("");
        txtTenKhachHang.setText("");
        txtCMND.setText("");
        txtSoDienThoai.setText("");
        txtNgaySinh.setText("");
        txtMaKhachHang.requestFocus();
        table.clearSelection();
    }

    private void timChuyenDiTheodiemDen() {
        String name = txtTimDiemDen.getText();
        List<ChuyenDi> dsChuyenDi = new ArrayList<>();
        try {
            if (name.length() > 0) {
                dsChuyenDi = chuyenDiDAO.getChuyenDiTheoTen(name);
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setRowCount(0);

                for (ChuyenDi chuyenDi : dsChuyenDi) {
                    tableModel.addRow(new Object[]{
                            chuyenDi.getMaChuyenDi(),
                            chuyenDi.getDiemXuatPhat().getMaDiemXuatPhat(),
                            chuyenDi.getDiemDen().getMaDiemDen(),
                            chuyenDi.getMaChuyenDi(),
                            chuyenDi.getNgayGioDen(),
                            chuyenDi.getBienSoXe()
                    });
                }
                tableModel.fireTableDataChanged();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, "D??? li???u nh???p v??o kh??ng h???p l???");
        }
    }

    public void capNhatChuyenDi() {
        model.setRowCount(0);
        for (ChuyenDi chuyenDi : chuyenDiDAO.getAllChuyenDi()) {
            model.addRow(new Object[]{
                    chuyenDi.getMaChuyenDi(),
                    chuyenDi.getDiemXuatPhat().getMaDiemXuatPhat(),
                    chuyenDi.getDiemDen().getMaDiemDen(),
                    dateTimeFormatter.format(chuyenDi.getNgayGioDi()),
                    dateTimeFormatter.format(chuyenDi.getNgayGioDen()),
                    chuyenDi.getBienSoXe()
            });
        }
    }
}
