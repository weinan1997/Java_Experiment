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
        setSize(Field.width, Field.height + 20);
        setLocationRelativeTo(null);
        setTitle("Ground");
    }
}
