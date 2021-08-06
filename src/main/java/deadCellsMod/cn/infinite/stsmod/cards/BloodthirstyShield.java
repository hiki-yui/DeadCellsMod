package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.BloodthirstyShieldPower;

//鲜血盾
public class BloodthirstyShield extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:BloodthirstyShield";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/BloodthirstyShield.png";

    public BloodthirstyShield(){
        super(BASE_ID,STRINGS.NAME,IMG,2,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.baseBlock = 12;
        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,this.block,true));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new BloodthirstyShieldPower(abstractPlayer,this.magicNumber),this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBlock(4);
            this.upgradeMagicNumber(1);
        }
    }
}
