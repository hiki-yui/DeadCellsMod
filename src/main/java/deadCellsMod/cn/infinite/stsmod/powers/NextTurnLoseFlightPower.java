package deadCellsMod.cn.infinite.stsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;


public class NextTurnLoseFlightPower extends AbstractPower implements CloneablePowerInterface {
    public static final String BASE_ID = "deadCells:NextTurnLoseFlightPower";
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private AbstractCreature source;

    public NextTurnLoseFlightPower(int amount,AbstractCreature owner,AbstractCreature source){
        this.ID=BASE_ID;
        this.name = strings.NAME;
        this.amount = amount;
        this.owner = owner;
        this.source = source;
        this.updateDescription();
        this.isTurnBased=true;
        this.type = PowerType.DEBUFF;
        ImgUtils.setPowerImg(this);

    }

    @Override
    public void updateDescription() {
        this.description = strings.DESCRIPTIONS[0]+amount+strings.DESCRIPTIONS[1]+1+strings.DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractPower power = owner.getPower("Flight");
        if (power!=null){
            addToBot(new ReducePowerAction(owner,source,"Flight",1));
        }
        addToBot(new ReducePowerAction(owner,source,BASE_ID,1));
    }

    @Override
    public AbstractPower makeCopy() {
        return new NextTurnLoseFlightPower(this.amount,this.owner,this.source);
    }
}
