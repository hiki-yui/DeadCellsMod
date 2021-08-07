package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

//施虐者的匕首
public class SadistStiletto extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:SadistStiletto";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/SadistStiletto.png";
    private static final int BASE_MAGIC_NUMBER = 1;

    public SadistStiletto(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.ENEMY);

        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber( 1);
            this.upgradeDamage(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0;i<this.magicNumber;i++){
            addToBot(new DamageAction(abstractMonster,
                    new DamageInfo(abstractPlayer,this.damage),
                    AbstractGameAction.AttackEffect.SLASH_VERTICAL,true));
        }
        /*this.magicNumber = this.baseMagicNumber = BASE_MAGIC_NUMBER;*/
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        /*this.baseMagicNumber = BASE_MAGIC_NUMBER;*/
        this.isMagicNumberModified = this.baseMagicNumber != this.magicNumber;
    }

    @Override
    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
        if (mo!=null) {
            int debuffNum = 0;
            for (AbstractPower power : mo.powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF) {
                    debuffNum++;
                }
            }
            this.magicNumber = this.baseMagicNumber + debuffNum;
            tmp += debuffNum;
            this.initializeDescription();
        }else{
            this.magicNumber = this.baseMagicNumber;
            this.initializeDescription();
        }
        return super.calculateModifiedCardDamage(player, mo, tmp);
    }
}
