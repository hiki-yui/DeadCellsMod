package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;


public class SpeedPower extends AbstractPower {
    public static final String BASE_ID = "deadCells:SpeedPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public SpeedPower(AbstractCreature owner,int amount){
        this.ID = BASE_ID;
        this.name = STRINGS.NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        ImgUtils.setPowerImg(this);

    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        this.flash();
        addToBot(new DrawCardAction(1));
        addToBot(new GainBlockAction(owner,1));
        addToBot(new ReducePowerAction(owner,owner,BASE_ID,1));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new ReducePowerAction(owner,owner,BASE_ID,amount));
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+1+//你每打出 1
                STRINGS.DESCRIPTIONS[1]+1+// 张牌，抽 1
                STRINGS.DESCRIPTIONS[2]+1+// 张牌，获得 1
                STRINGS.DESCRIPTIONS[3]+1+// 点 格挡 。层数减 #b
                STRINGS.DESCRIPTIONS[4];
    }
}
