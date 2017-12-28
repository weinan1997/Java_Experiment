import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleGroundTest {

    @Test(expected = Exception.class)
    public void heYi() throws Exception{
        List<Creature> list = new ArrayList<>();
        Creature[] c = new Creature[8];
        list.addAll(Arrays.asList(c));
        BattleGround a = new BattleGround();
        a.HeYi(list);
    }
}