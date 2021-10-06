
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;


public class Main extends JFrame {
    private JButton prevBtn,nextBtn,overBtn;
    private JLabel nameLbl,surnameLbl,basicSalaryLbl,specialityLbl,yearLbl,omsLbl,overtimeLbl;
    private JTextField nameTF,surnameTF,basicSalaryTF,specialityTF,yearTF,omsTF,overtimeTF;

    private ArrayList<Employees> empList;
    private int lastEmployee;
    private int current=1;


    public Main() throws HeadlessException {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        prevBtn = new JButton("Previous");
        nextBtn=new JButton("Next");
        overBtn= new JButton("Add overtime hours");

        nameTF= new JTextField("",10);
        nameTF.setEditable(false);
        nameLbl = new JLabel("Name: ");

        surnameTF= new JTextField("",10);
        surnameTF.setEditable(false);
        surnameLbl = new JLabel("Surname: ");

        basicSalaryTF= new JTextField("",10);
        basicSalaryTF.setEditable(false);
        basicSalaryLbl = new JLabel("Basic Salary: ");

        specialityTF= new JTextField("",10);
        specialityTF.setEditable(false);
        specialityLbl = new JLabel("Speciality: ");

        yearTF= new JTextField("",10);
        yearTF.setEditable(false);
        yearLbl = new JLabel("Year of recruitment : ");

        omsTF= new JTextField("",10);
        omsTF.setEditable(false);
        omsLbl = new JLabel("OMS: ");

        overtimeTF= new JTextField("",10);
        overtimeLbl = new JLabel("Overtime Hours: ");

        empList = new ArrayList();

        this.setLayout(new BorderLayout(5,5));
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(7,2,10,3));
        infoPanel.add(nameLbl);
        infoPanel.add(nameTF);
        infoPanel.add(surnameLbl);
        infoPanel.add(surnameTF);
        infoPanel.add(basicSalaryLbl);
        infoPanel.add(basicSalaryTF);
        infoPanel.add(specialityLbl);
        infoPanel.add(specialityTF);
        infoPanel.add(yearLbl);
        infoPanel.add(yearTF);
        infoPanel.add(omsLbl);
        infoPanel.add(omsTF);
        infoPanel.add(overtimeLbl);
        infoPanel.add(overtimeTF);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(prevBtn);
        buttonPanel.add(overBtn);
        buttonPanel.add(nextBtn);

        this.add(infoPanel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                load();
                System.exit(0);
            }
        });

        createEntries();


        displayEntry(current);

        prevBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                current=current-1;
                displayEntry(current);
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((lastEmployee+1)==(current+1))
                    JOptionPane.showMessageDialog(nextBtn, "No more employees to display", "Array List End\n" ,JOptionPane.ERROR_MESSAGE);
                else {
                    current=current+1;
                    displayEntry(current);  }
            }
        });

        overBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOvertime(current);
            }
        });

    }

    private void addOvertime(int cur){
        Employees tmp_emp;
        tmp_emp=(Employees)empList.get(cur-1);
        String str1 = overtimeTF.getText();
        int over= Integer.valueOf(str1);
        tmp_emp.setOvertime(over);
        tmp_emp.finalSal(over);
    }

    private void displayEntry(int emplEntry){

        Employees tmp_emp;
        tmp_emp=(Employees)empList.get(emplEntry-1);

        nameTF.setText(tmp_emp.getName());
        surnameTF.setText(tmp_emp.getSurname());
        String sal,ho;
        sal=String.valueOf(tmp_emp.getBasicSalary());
        basicSalaryTF.setText(sal);
        yearTF.setText(tmp_emp.getYear());
        specialityTF.setText(tmp_emp.getSpeciality());
        ho= String.valueOf(tmp_emp.getOms());
        omsTF.setText(ho);
        overtimeTF.setText("");
    }

    private void createEntries(){
        int counter=0;
        try {
            FileReader in = new FileReader("Employees.txt");
            BufferedReader buff = new BufferedReader(in);
            String line;
            String[] tokens;
            int yper=0;

            while (buff.ready()) {
                line = buff.readLine();
                tokens = line.split("#");
                float bas = (float)Float.valueOf(tokens[2]);
                int ymo = Integer.valueOf(tokens[5]);

                Employees temp = new Employees(tokens[0], tokens[1],bas,tokens[3],tokens[4],ymo,yper);
                empList.add(temp);
                counter++;
            }
            lastEmployee=counter;
            in.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Can't find Employees.txt", "File Access Error\n" +
                    ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Can't read Employees.txt", "File Access Error\n" +
                    ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }


    private void load(){
        try {
            FileWriter out = new FileWriter("emp.txt");
            BufferedWriter file = new BufferedWriter(out);

            StringBuilder str = new StringBuilder();

            Employees tmp;
            for (int i =0; i<empList.size(); i++) {
                tmp = (Employees)empList.get(i);
                str.append(tmp.toString());
                str.append("\n");
            }

            file.write(str.toString().trim());
            file.close();

        } catch (IOException ex) {

        }

    }


}
