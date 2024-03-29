package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.DepravedPower;

public class DepravedForm extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:DepravedForm";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/depraved.png";
    static {
        try {
            Class.forName("DepravedPower");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DepravedForm(){
        super(BASE_ID,STRINGS.NAME,IMG,3,STRINGS.DESCRIPTION,CardType.POWER, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.SELF);

        this.setBackgroundTexture(DeadCellsModInitializer.RED2_PURPLE2_POWER_CARD, DeadCellsModInitializer.RED2_PURPLE2_POWER_CARD_PORTRAIT);
        this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("deadCells:DEPRAVED_FORM"));
        addToBot(new ApplyPowerAction(p,p,new DepravedPower(p,this.baseMagicNumber),this.baseMagicNumber));
        addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,6),7));
        addToBot(new ApplyPowerAction(p,p,new VulnerablePower(p,1,false),1));
    }

    /*@Override
    public AbstractCard makeCopy() {
        AbstractCard card = new DepravedForm();
        if (this.upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
