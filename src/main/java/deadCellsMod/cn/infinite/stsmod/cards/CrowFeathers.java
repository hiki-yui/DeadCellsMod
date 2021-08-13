package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.NextTurnLoseFlightPower;

public class CrowFeathers extends DeadCellsCard {
    public static final String ID = "deadCells:CrowFeathers";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static  final String IMG = "img/card/CrowFeathers.png";

    public CrowFeathers(){
        super(ID,strings.NAME,IMG,2,strings.DESCRIPTION,
                CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);



        this.baseMagicNumber = 1;
        this.baseDamage = 6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(this.cost - 1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,
                new deadCellsMod.cn.infinite.stsmod.powers.FlightPower(abstractPlayer,this.baseMagicNumber)));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,
                new FlameBarrierPower(abstractPlayer,this.damage),this.damage));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,
                new NextTurnLoseFlightPower(baseMagicNumber,abstractPlayer,abstractPlayer)));
    }
    /*@Override
    public AbstractCard makeCopy(){
        AbstractCard card = new CrowFeathers();
        if (upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
