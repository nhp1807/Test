package Code;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;
import javax.swing.JPanel;

public class MainGui extends JFrame {
    //Attribute
    private int width = 700;
    private int height = 500;
    private JButton btnQuanLy, btnDanhSach, btnThongTin, btnThoat;

    //Constructor
    public MainGui(){
        addControls();
        addEvents();
        showWindows();
    }

    //Add control
    public void addControls(){
        //Tao khu vuc hien thi thong tin project
        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        pnThongTin.setPreferredSize(new Dimension(0, 120));
        pnThongTin.setBackground(new Color(156, 16, 15));
        JLabel lbThongTin = new JLabel("Student management");
        lbThongTin.setHorizontalAlignment(JLabel.CENTER);
        lbThongTin.setVerticalAlignment(JLabel.CENTER);
        lbThongTin.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        lbThongTin.setForeground(Color.WHITE);
        JLabel lbCreatedBy = new JLabel("Created by nhp1807");
        lbCreatedBy.setHorizontalAlignment(JLabel.CENTER);
        lbCreatedBy.setVerticalAlignment(JLabel.CENTER);
        lbCreatedBy.setFont(new Font("Trebuchet MS", Font.ITALIC, 14));
        lbCreatedBy.setForeground(Color.WHITE);

        JLabel lbLeft = new JLabel();
        lbLeft.setIcon(new ImageIcon(Objects.requireNonNull(MainGui.class.getResource("/image/school.png"))));
        lbLeft.setVerticalAlignment(JLabel.CENTER);
        lbLeft.setHorizontalAlignment(JLabel.CENTER);
        JLabel lbRight = new JLabel();
        lbRight.setIcon(new ImageIcon(Objects.requireNonNull(MainGui.class.getResource("/image/class.png"))));
        lbRight.setVerticalAlignment(JLabel.CENTER);
        lbRight.setHorizontalAlignment(JLabel.CENTER);

        pnThongTin.add(lbThongTin, BorderLayout.CENTER);
        pnThongTin.add(lbCreatedBy, BorderLayout.SOUTH);
        pnThongTin.add(lbLeft, BorderLayout.WEST);
        pnThongTin.add(lbRight, BorderLayout.EAST);

        //Tao khu vuc hien thi cac nut chuc nang
        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
//        JPanel pnNut = new JPanel();
//        pnNut.setLayout(new GridLayout(1,3));

        btnQuanLy = new JButton("Manage");
        btnQuanLy.setIcon(new ImageIcon(Objects.requireNonNull(MainGui.class.getResource("/image/home.png"))));
        btnDanhSach = new JButton("List");
        btnDanhSach.setIcon(new ImageIcon(Objects.requireNonNull(MainGui.class.getResource("/image/list.png"))));
        btnThongTin = new JButton("Information");
        btnThongTin.setIcon(new ImageIcon(Objects.requireNonNull(MainGui.class.getResource("/image/info.png"))));
        btnThoat = new JButton("Exit");
        btnThoat.setIcon(new ImageIcon(Objects.requireNonNull(MainGui.class.getResource("/image/exit.png"))));
        customButton(btnQuanLy);
        customButton(btnDanhSach);
        customButton(btnThongTin);
        customButton(btnThoat);

        pnChucNang.add(btnQuanLy);
        pnChucNang.add(btnDanhSach);
        pnChucNang.add(btnThongTin);
        pnChucNang.add(btnThoat);
//        pnNut.add(btnQuanLy);
//        pnNut.add(btnDanhSach);
//        pnNut.add(btnThongTin);
//        pnNut.setBackground(Color.LIGHT_GRAY);
//        pnChucNang.add(pnNut);
        pnChucNang.setBackground(new Color(191, 67, 67));
        pnChucNang.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        //Container
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnThongTin, BorderLayout.NORTH);
        pnMain.add(pnChucNang, BorderLayout.CENTER);
        con.add(pnMain);
    }

    //Function
    public void customButton(JButton btn){
        btn.setFocusable(false);
        btn.setHorizontalTextPosition(JButton.CENTER);
        btn.setVerticalTextPosition(JButton.BOTTOM);
        btn.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(220, 150));
        btn.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    //Add event
    public void addEvents(){
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnQuanLy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new quanlyUI();
                setVisible(false);
            }
        });
    }
    //Show window
    public void showWindows(){
        setTitle("Quản lý sinh viên");
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Test main
    public static void main(String[] args) {
        new MainGui();
    }
}
