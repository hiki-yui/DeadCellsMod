package deadCellsMod.cn.infinite.variables;

import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import deadCellsMod.cn.infinite.stsmod.cards.DeadCellsCard;

public class AmmunitionVariables extends DynamicVariable {
    @Override
    public String key() {
        return "AM";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).maxAmmunitionNumber != ((DeadCellsCard)abstractCard).ammunitionNumber;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).ammunitionNumber;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((DeadCellsCard)abstractCard).maxAmmunitionNumber;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return false;
    }


    @Override
    public Color getIncreasedValueColor() {
        return Color.GREEN;
    }

    @Override
    public Color getDecreasedValueColor() {
        return Color.RED;
    }

    public static class MaxAmmunitionVariables extends DynamicVariable{
        @Override
        public String key() {
            return "MAM";
        }

        @Override
        public boolean isModified(AbstractCard abstractCard) {
            return false;
        }

        @Override
        public int value(AbstractCard abstractCard) {
            return ((DeadCellsCard)abstractCard).maxAmmunitionNumber;
        }

        @Override
        public int baseValue(AbstractCard abstractCard) {
            return ((DeadCellsCard)abstractCard).maxAmmunitionNumber;
        }

        @Override
        public boolean upgraded(AbstractCard abstractCard) {
            return ((DeadCellsCard)abstractCard).ammunitionUpgraded;
        }
    }
}
