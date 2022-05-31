package Code;

import Model.SinhVien;
import connect.Service;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class quanlyUI extends JFrame {
    private JButton btnTimKiem, btnThem, btnXoa, btnLuu, btnQuayLai;
    private DefaultTableModel dtmTableDanhSach;
    private JTable tblDanhSach;
    private Vector<SinhVien> dsSinhVien;
    private int rowSelected;
    private JTextField jtfSearch;

    public quanlyUI(){
        addControls();
        addEvents();
        showWindows();
    }

    private void addControls(){
        //Tao panel danh sach
        JPanel pnDanhSach = new JPanel();
        pnDanhSach.setLayout(new BorderLayout());
        pnDanhSach.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        dtmTableDanhSach = new DefaultTableModel();
        dtmTableDanhSach.addColumn("Họ và tên");
        dtmTableDanhSach.addColumn("Khoá");
        dtmTableDanhSach.addColumn("MSSV");
        dtmTableDanhSach.addColumn("Lớp");
        dtmTableDanhSach.addColumn("GPA");
        dtmTableDanhSach.addColumn("ĐRL");
        tblDanhSach = new JTable(dtmTableDanhSach);
        Font headerFont = new Font("Arial", Font.PLAIN,15);
        JTableHeader tblHeader = tblDanhSach.getTableHeader();
        tblHeader.setFont(headerFont);
        tblDanhSach.setDefaultEditor(Object.class, null);
        //tblDanhSach.getColumnModel().getColumn(1).setPreferredWidth(80);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tblDanhSach.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tblDanhSach.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tblDanhSach.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        tblDanhSach.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        hienThiDanhSachSinhVien();
        JScrollPane scrollDanhSach = new JScrollPane(tblDanhSach, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnDanhSach.add(scrollDanhSach, BorderLayout.CENTER);

        Font font = new Font("Trebuchet MS", Font.PLAIN, 18);

        //Tao panel tim kiem
        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new GridBagLayout());
        pnTimKiem.setPreferredSize(new Dimension(100, 70));
        jtfSearch = new JTextField(25);
        jtfSearch.setPreferredSize(new Dimension(30,35));
        btnTimKiem = new JButton("Find");
        btnTimKiem.setFont(font);
        btnTimKiem.setPreferredSize(new Dimension(100,35));
        pnTimKiem.add(jtfSearch);
        pnTimKiem.add(btnTimKiem);

        //Tao panel chuc nang
        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new GridBagLayout());
        pnChucNang.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        GridBagConstraints gbc = new GridBagConstraints();
        btnThem = new JButton("Add");
        btnThem.setFont(font);
        btnThem.setPreferredSize(new Dimension(120,50));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8,0,8,0);
        pnChucNang.add(btnThem,gbc);
        btnXoa = new JButton("Delete");
        btnXoa.setFont(font);
        btnXoa.setPreferredSize(new Dimension(120,50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnChucNang.add(btnXoa,gbc);
        btnLuu = new JButton("Save");
        btnLuu.setFont(font);
        btnLuu.setPreferredSize(new Dimension(120,50));
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnChucNang.add(btnLuu,gbc);
        btnQuayLai = new JButton("Back");
        btnQuayLai.setFont(font);
        btnQuayLai.setPreferredSize(new Dimension(120,50));
        gbc.gridx = 0;
        gbc.gridy = 6;
        pnChucNang.add(btnQuayLai,gbc);

        //Tao container
        Container con = getContentPane();
        JPanel pnQuanLyDS = new JPanel();
        pnQuanLyDS.setLayout(new BorderLayout());
        pnQuanLyDS.add(pnDanhSach, BorderLayout.CENTER);
        pnQuanLyDS.add(pnChucNang, BorderLayout.EAST);
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnQuanLyDS, BorderLayout.CENTER);
        pnMain.add(pnTimKiem, BorderLayout.NORTH);
        con.add(pnMain);

    }

    private void addEvents(){
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ThemSinhVienGUI();
                setVisible(false);
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowSelected = tblDanhSach.getSelectedRow();
                if(rowSelected != -1){
                    String MSSV = (String) tblDanhSach.getValueAt(rowSelected, 2);
                    int chon = JOptionPane.showConfirmDialog(quanlyUI.this, "Xác nhận xoá?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if(chon == JOptionPane.YES_OPTION){
                        boolean status = Service.xoaMonAn(MSSV);
                        if(status){
                            locThongTin();
                            JOptionPane.showMessageDialog(null, "Xoá sinh viên thành công");
                        }
                    }
                }
            }
        });
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                locThongTin();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                locThongTin();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                locThongTin();
            }
        });
        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanlyUI.this.setVisible(false);
                new MainGui();
            }
        });
    }

    public void showWindows(){
        this.setTitle("Quản lý sinh viên");
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void docDuLieu() {
        dsSinhVien = Service.layDanhSachSinhVien();
    }

    public void locThongTin() {
        docDuLieu();
        String duLieuLoc = jtfSearch.getText();
        Vector<SinhVien> dsSinhVienDaLoc = new Vector<>();
        for (SinhVien sinhVien : dsSinhVien) {
            if (sinhVien.getMssv().toUpperCase().contains(duLieuLoc.trim().toUpperCase())
                    || sinhVien.getTen().toUpperCase().contains(duLieuLoc.trim().toUpperCase())) {
                dsSinhVienDaLoc.add(sinhVien);
            }
        }
        if (duLieuLoc.trim().length() > 0) {
            hienThiDanhSachSinhVien(dsSinhVienDaLoc);
        } else {
            hienThiDanhSachSinhVien();
        }
    }

    private void hienThiDanhSachSinhVien(){
        docDuLieu();
        dtmTableDanhSach.setRowCount(0);
        for(SinhVien sinhVien : dsSinhVien){
            Vector<Object> vec = new Vector<>();
            vec.add(sinhVien.getTen());
            vec.add(sinhVien.getKhoa());
            vec.add(sinhVien.getMssv());
            vec.add(sinhVien.getLop());
            vec.add(sinhVien.getGpa());
            vec.add(sinhVien.getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }

    private void hienThiDanhSachSinhVien(Vector<SinhVien> dsSinhVienDaLoc){
        dtmTableDanhSach.setRowCount(0);
        //docDuLieu();
        for(SinhVien sinhVien : dsSinhVienDaLoc){
            Vector<Object> vec = new Vector<>();
            vec.add(sinhVien.getTen());
            vec.add(sinhVien.getKhoa());
            vec.add(sinhVien.getMssv());
            vec.add(sinhVien.getLop());
            vec.add(sinhVien.getGpa());
            vec.add(sinhVien.getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }

    public static void main(String[] args) {
        quanlyUI ui = new quanlyUI();
        ui.showWindows();
    }
}
