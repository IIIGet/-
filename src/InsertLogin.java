import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sun May 09 20:56:27 CST 2021
 */



/**
 * @author Brainrain
 */
public class InsertLogin extends JFrame {
    public static void main(String args[]){
        new InsertLogin();
    }
    public InsertLogin() {
        initComponents();
    }

    private void initComponents() {


        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textID = new JTextField();
        label2 = new JLabel();
        textNAME = new JTextField();
        textNUM = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        textPRICE = new JTextField();
        button1 = new JButton();
        textTips = new JTextField();
        label5 = new JLabel();
        buttonDELE = new JButton();
        buttonGo = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5546\u54c1ID");
        contentPane.add(label1);
        label1.setBounds(65, 30, 60, 30);
        contentPane.add(textID);
        textID.setBounds(160, 30, 145, 30);

        //---- label2 ----
        label2.setText("\u5546\u54c1\u540d\u79f0");
        contentPane.add(label2);
        label2.setBounds(60, 75, 65, 30);
        contentPane.add(textNAME);
        textNAME.setBounds(160, 75, 145, 30);
        contentPane.add(textNUM);
        textNUM.setBounds(160, 120, 145, 30);

        //---- label3 ----
        label3.setText("\u5546\u54c1\u6570\u91cf");
        contentPane.add(label3);
        label3.setBounds(60, 120, 65, 30);

        //---- label4 ----
        label4.setText("\u5546\u54c1\u4ef7\u683c");
        contentPane.add(label4);
        label4.setBounds(60, 165, 65, 30);
        contentPane.add(textPRICE);
        textPRICE.setBounds(160, 165, 145, 30);

        //---- button1 ----
        button1.setText("\u589e\u52a0");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(60, 225), button1.getPreferredSize()));
        contentPane.add(textTips);
        textTips.setBounds(290, 225, 150, 30);

        //---- label5 ----
        label5.setText("\u63d0\u793a");
        contentPane.add(label5);
        label5.setBounds(345, 195, 45, 25);

        //---- buttonDELE ----
        buttonDELE.setText("\u5220\u9664");
        contentPane.add(buttonDELE);
        buttonDELE.setBounds(new Rectangle(new Point(180, 225), buttonDELE.getPreferredSize()));

        //---- buttonGo ----
        buttonGo.setText("\u9000\u51fa");
        contentPane.add(buttonGo);
        buttonGo.setBounds(new Rectangle(new Point(360, 30), buttonGo.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(480, 330));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        textTips.setText("删除只需要输入商品序号");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn1=Conn.getconn();
                String ID1=textID.getText();
                int ROSE=0;//中值判断，判断是否能够执行插入语句

                String NAME1=textNAME.getText();
                String NUM1=textNUM.getText();
                String PRICE1=textPRICE.getText();
                String sql="SELECT*FROM repertory WHERE code= ?";//查询语句，用于判断是否重复
                //判断是否输入值为空
                if(ID1.length()<1||NAME1.length()<1||NUM1.length()<1||PRICE1.length()<1){
//                   JFrame nu=new NULL();
//                    nu.setDefaultCloseOperation(nu.HIDE_ON_CLOSE);
//                    nu.setVisible(true);
                        textTips.setText("输入值不能为空！");
                    ROSE=1;
                }
                else{ //输入值不为空的情况下，则进行重复排查
                    int ID2 = Integer.valueOf(ID1).intValue();//值转换
                    try {
                        PreparedStatement pr=conn1.prepareStatement(sql);
                        pr.setInt(1,ID2);
                        int fp=pr.executeUpdate();
                        if(fp>0){//返回值大于0，则有重复情况。
                            textTips.setText("商品序号重复！，出现重复商品");
                            ROSE=1;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                int ID = Integer.valueOf(ID1).intValue();
                long NUM=Integer.valueOf(NUM1).longValue();
                Double PRICE = Integer.valueOf(PRICE1).doubleValue();
//                String sql="INSERT INTO repertory VALUES(5,面包,10,1)";
                if(ROSE!=1){
                try{
                    PreparedStatement ps= conn1.prepareStatement("INSERT INTO repertory(code,name,amt,price) VALUES(?,?,?,?)");
                    //PreparedStatement ps= conn1.prepareStatement("INSERT INTO repertory VALUES(5,面包,10,1)");
                    ps.setInt(1,ID);
                    ps.setString(2,NAME1);
                    ps.setLong(3,NUM);
                    ps.setDouble(4,PRICE);
                    int f=ps.executeUpdate();
                    if(f>0)//查看是否成功
                    {
                        textTips.setText("插入数据成功");
                    }
                    else
                    {
                        textTips.setText("插入数据失败");
                    }

                    ps.close();
                    conn1.close();
                    System.out.println("连接成功");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }}
        });
        //删除按钮
        buttonDELE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn1=Conn.getconn();
                String ID1=textID.getText();
                int ROSE=0;//中值判断，判断是否能够执行插入语句
                String sql="SELECT*FROM repertory WHERE code= ?";
                if(ID1.length()<1){
//                   JFrame nu=new NULL();
//                    nu.setDefaultCloseOperation(nu.HIDE_ON_CLOSE);
//                    nu.setVisible(true);
                    textTips.setText("商品序号不能为空！");
                    ROSE=1;
                }
                else{ //输入值不为空的情况下，则进行重复排查
                    int ID3 = Integer.valueOf(ID1).intValue();//值转换
                    try {
                        PreparedStatement pr=conn1.prepareStatement(sql);
                        pr.setInt(1,ID3);
                        int fp=pr.executeUpdate();
                        if(fp==0){//返回值等于0，没有该数据。
                            textTips.setText("没有该商品！请重新输入");
                            ROSE=1;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                int ID4 = Integer.valueOf(ID1).intValue();
                if(ROSE!=1){
                    try{
                        PreparedStatement ps= conn1.prepareStatement("DELETE FROM repertory WHERE code = ?");
                        //PreparedStatement ps= conn1.prepareStatement("INSERT INTO repertory VALUES(5,面包,10,1)");
                        ps.setInt(1,ID4);
                        int f=ps.executeUpdate();
                        if(f>0)//查看是否成功
                        {
                            textTips.setText("删除数据成功");
                        }
                        else
                        {
                            textTips.setText("删除数据失败");
                        }

                        ps.close();
                        conn1.close();
                        //System.out.println("连接成功");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        buttonGo.addActionListener(new ActionListener() {//退出按钮
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textID;
    private JLabel label2;
    private JTextField textNAME;
    private JTextField textNUM;
    private JLabel label3;
    private JLabel label4;
    private JTextField textPRICE;
    private JButton button1;
    private JTextField textTips;
    private JLabel label5;
    private JButton buttonDELE;
    private JButton buttonGo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}
