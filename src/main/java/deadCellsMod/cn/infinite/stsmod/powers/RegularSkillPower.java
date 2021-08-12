package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.action.AddCardToHandAction;
import deadCellsMod.cn.infinite.stsmod.cards.Roll;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class RegularSkillPower extends AbstractPower {

    private static final String BASE_ID = "deadCells:RegularSkillPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public RegularSkillPower(AbstractCreature owner,int amount){
        this.name = STRINGS.NAME;
        this.ID = BASE_ID;
        this.amount = amount;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        ImgUtils.setPowerImg(this);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + amount + STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        for (int i = 0;i<this.amount;i++) {
            AbstractCard roll = new Roll();
            if (!roll.exhaust){
                roll.rawDescription = roll.rawDescription + " NL 消耗 。";
                roll.exhaust = true;
            }

            if (!roll.selfRetain){
                roll.rawDescription = roll.rawDescription + " NL 保留 。";
                roll.selfRetain = true;
            }
            addToBot(new AddCardToHandAction(roll));
        }
        super.atStartOfTurn();
    }
}
