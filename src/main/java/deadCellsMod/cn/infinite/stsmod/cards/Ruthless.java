package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.RuthlessPower;

public class Ruthless extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Ruthless";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/sadistic_nature.png";

    public Ruthless(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.POWER, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.GOLD_POWER_CARD, DeadCellsModInitializer.GOLD_POWER_CARD_PORTRAIT);

        this.magicNumber = this.baseMagicNumber =1;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBaseCost(0);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RuthlessPower(abstractPlayer,this.magicNumber),this.magicNumber));
    }
}
