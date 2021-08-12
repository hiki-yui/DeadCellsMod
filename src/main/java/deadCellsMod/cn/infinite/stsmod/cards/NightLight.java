package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.NightLightPower;

public class NightLight extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:NightLight";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public NightLight(){
        super(BASE_ID,STRINGS.NAME,"img/card/NightLight.png",1,STRINGS.DESCRIPTION,CardType.POWER, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 1;
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
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new NightLightPower(this.magicNumber),this.magicNumber));
    }
}
