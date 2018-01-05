import javax.swing.*;

public class Ground extends JFrame{

    private Field field;

    Ground(Field f){
        field = f;
        initUI();
    }


    public void initUI(){
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getW(), field.getH());
        setLocationRelativeTo(null);
        setTitle("Ground");
    }
}
