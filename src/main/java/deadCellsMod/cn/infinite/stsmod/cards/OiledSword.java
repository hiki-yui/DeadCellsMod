package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.OilPower;

public class OiledSword extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:OiledSword";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/OiledSword.png";

    public OiledSword(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ENEMY);

        this.baseDamage = 8;
        this.heavyDamage = this.baseHeavyDamage = this.baseDamage * 2;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(3);
            this.upgradeHeavyDamage(6);
            this.upgradeName();
        }
    }

    @Override
    public void applyPowers() {
        this.superApplyPowers();
        this.isDamageModified = false;
        this.heavyDamage = this.damage*2;
        this.superApplyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        this.superCalculateCardDamage(mo);
        this.heavyDamage = this.damage*2;
        this.superCalculateCardDamage(mo);
    }

    @Override
    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
        return super.calculateModifiedCardDamage(player, mo, tmp);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.applyPowers();
        this.calculateCardDamage(abstractMonster);
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,
                abstractMonster.hasPower("deadCells:BurnsPower")?this.heavyDamage:this.damage), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ApplyPowerAction(abstractMonster,abstractPlayer,new OilPower(abstractMonster,this.magicNumber)));
    }
}
