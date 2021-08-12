package deadCellsMod.cn.infinite.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import deadCellsMod.cn.infinite.stsmod.cards.DeadCellsCard;

//烧伤
public class BurnsVariable extends DynamicVariable {
    @Override
    public String key() {
        return "Burns";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).baseBurnNumber != ((DeadCellsCard)abstractCard).burnNumber;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if (((DeadCellsCard)abstractCard).burnNumber > 0) {
            return ((DeadCellsCard) abstractCard).burnNumber;
        }else{
            return ((DeadCellsCard) abstractCard).baseBurnNumber;
        }
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).baseBurnNumber;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).burnUpGraded;
    }
}
