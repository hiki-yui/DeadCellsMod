package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.CorrosiveCloudPower;

public class CorrosiveCloud extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:CorrosiveCloud";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public CorrosiveCloud(){
        super(BASE_ID,STRINGS.NAME,"img/card/CorrosiveCloud.png",1,STRINGS.DESCRIPTION,CardType.POWER, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.setBackgroundTexture(DeadCellsModInitializer.RED2_PURPLE2_POWER_CARD, DeadCellsModInitializer.RED2_PURPLE2_POWER_CARD_PORTRAIT);
        this.magicNumber = this.baseMagicNumber =1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.isInnate = true;
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new CorrosiveCloudPower(abstractPlayer,this.magicNumber),this.magicNumber));
    }
}
