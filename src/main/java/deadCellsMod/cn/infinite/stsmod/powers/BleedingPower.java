package deadCellsMod.cn.infinite.stsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class BleedingPower extends AbstractPower implements CloneablePowerInterface {
    public static final String BASE_ID = "deadCells:BleedingPower";
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    /*public static int damage = 1;*/

    public BleedingPower(AbstractCreature owner,int amount){
        this.ID=BASE_ID;
        this.name = strings.NAME;
        this.amount = amount;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;
        this.updateDescription();
        ImgUtils.setPowerImg(this);
    }

    @Override
    public void updateDescription() {
        int needUseCard = 1;
        this.description = strings.DESCRIPTIONS[0]+ needUseCard +strings.DESCRIPTIONS[1]+(this.amount==1?1:this.amount/2)+strings.DESCRIPTIONS[2]+(this.amount==1?1:this.amount/2)+strings.DESCRIPTIONS[3];
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        this.flash();
        addToBot(new LoseHPAction(this.owner,null,this.amount==1?1:this.amount/2));
        addToBot(new ReducePowerAction(owner,owner,ID,this.amount==1?1:this.amount/2));
    }

    @Override
    public AbstractPower makeCopy() {
        return new BleedingPower(this.owner,this.amount);
    }
}
