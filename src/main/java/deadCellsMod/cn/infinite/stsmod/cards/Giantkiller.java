package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

public class Giantkiller extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Giantkiller";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private boolean willChange = true;

    private int realBaseDamage = 5;
    private int realBaseChangNum = 3;

    public Giantkiller(){
        super(BASE_ID,STRINGS.NAME,"img/card/Giantkiller.png",1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ENEMY);

        this.baseDamage = 5;
        this.heavyDamage = this.baseHeavyDamage = 18;
        this.magicNumber = this.baseMagicNumber = 2;
        this.changeNum = this.baseChangeNum = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeChangeNum(2);
            this.realBaseChangNum += 2;
            this.upgradeName();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
        if (mo!=null) {
            if (mo.type == AbstractMonster.EnemyType.ELITE) {
                tmp += this.changeNum;
            }
        }
        return super.calculateModifiedCardDamage(player, mo, tmp);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0;i<this.magicNumber;i++){
            AbstractGameAction.AttackEffect effect = willChange?AbstractGameAction.AttackEffect.SLASH_VERTICAL: AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
            addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage),effect));
        }
        if (this.willChange){
            this.upgradeDamage(this.heavyDamage - this.baseDamage);
            this.upgradeMagicNumber(-1);
            this.upgradeBaseCost(2);
            this.upgradeChangeNum(realBaseChangNum);
            willChange = false;
        }else{
            this.baseDamage = this.realBaseDamage;
            this.upgradeMagicNumber(1);
            this.upgradeChangeNum(-realBaseChangNum);
            this.upgradeBaseCost(1);
            willChange = true;
        }
    }
}
