package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public abstract class DeadCellsPower extends AbstractPower {

    public DeadCellsPower(String baseId, PowerStrings strings, AbstractCreature owner, int amount,PowerType type, boolean isTurnBased ){
        this.name = strings.NAME;
        this.ID = baseId;
        this.amount = amount;
        this.owner = owner;
        this.type = type;
        this.isTurnBased = isTurnBased;
        ImgUtils.setPowerImg(this);
        this.updateDescription();
    }
}
