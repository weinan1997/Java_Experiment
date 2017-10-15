public class Calabash extends Creature {
    private COLOR color;
    private SENIORITY seniority;
    Calabash(COLOR color, SENIORITY seniority) {
        this.color = color;
        this.seniority = seniority;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }

    @Override
    public void report() {

    }
}

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    老大, 老二, 老三, 老四, 老五, 老六, 老七
}