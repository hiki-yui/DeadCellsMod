package deadCellsMod.cn.infinite.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import deadCellsMod.cn.infinite.stsmod.cards.DeadCellsCard;

public class HeavyDamageVariables extends DynamicVariable {

    @Override
    public String key() {
        return "HD";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).baseHeavyDamage != ((DeadCellsCard)abstractCard).heavyDamage;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if (((DeadCellsCard)abstractCard).heavyDamage<=0){
            ((DeadCellsCard)abstractCard).heavyDamage = ((DeadCellsCard)abstractCard).baseHeavyDamage;
        }
        return ((DeadCellsCard)abstractCard).heavyDamage;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).baseHeavyDamage;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).heavyDamageUpgraded;
    }

}
