package deadCellsMod.cn.infinite.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import deadCellsMod.cn.infinite.stsmod.cards.DeadCellsCard;
import deadCellsMod.cn.infinite.stsmod.cards.FerrymanSLantern;

public class AmNumberVariable  extends DynamicVariable {
    @Override
    public String key() {
        return "AB";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return FerrymanSLantern.AmNumber != 0;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return FerrymanSLantern.AmNumber;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return false;
    }
}
