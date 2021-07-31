package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.IceShieldPower;

//冰盾
public class IceShield extends DeadCellsCard{
    public static final String BASE_ID = "deadCells:IceShield";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/IceShield.png";

    public IceShield(){
        super(BASE_ID,STRINGS.NAME,IMG,2,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);


        this.baseBlock = 12;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBlock(4);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new IceShieldPower(abstractPlayer,magicNumber),this.magicNumber));
    }
}