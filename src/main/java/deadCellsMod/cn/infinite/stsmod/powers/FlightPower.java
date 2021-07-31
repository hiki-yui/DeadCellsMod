package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlightPower extends com.megacrit.cardcrawl.powers.FlightPower {
    public FlightPower(AbstractCreature owner, int amount) {
        super(owner, amount);
    }

    @Override
    public void onRemove() {}

    @Override
    public void atStartOfTurn() {
        updateDescription();
    }
}
