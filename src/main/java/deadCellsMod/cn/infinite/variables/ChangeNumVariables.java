package deadCellsMod.cn.infinite.variables;

import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import deadCellsMod.cn.infinite.stsmod.cards.DeadCellsCard;

public class ChangeNumVariables extends DynamicVariable {
    @Override
    public String key() {
        return "CN";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).baseChangeNum != ((DeadCellsCard)abstractCard).changeNum;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if (((DeadCellsCard)abstractCard).changeNum<=0){
            ((DeadCellsCard)abstractCard).changeNum = ((DeadCellsCard)abstractCard).baseChangeNum;
        }
        return ((DeadCellsCard)abstractCard).changeNum;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).baseChangeNum;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).changeNumUpGraded;
    }
}
