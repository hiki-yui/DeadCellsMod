package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.RobPower;

public class Rob extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Rob";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public Rob(){
        super(BASE_ID,STRINGS.NAME,"img/card/Rob.png",1,STRINGS.DESCRIPTION,CardType.POWER, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.SELF);
        this.setBackgroundTexture(DeadCellsModInitializer.GOLD_POWER_CARD, DeadCellsModInitializer.GOLD_POWER_CARD_PORTRAIT);
        this.magicNumber = this.baseMagicNumber = 20;
        this.changeNum = this.baseChangeNum = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeChangeNum(2);
            this.upgradeMagicNumber(5);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RobPower(this.magicNumber),this.magicNumber));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,this.changeNum),this.changeNum));
    }


}
