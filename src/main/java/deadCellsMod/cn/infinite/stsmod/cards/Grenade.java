package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.DamageAllButOneEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;

public class Grenade extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Grenade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/Grenade.png";

    public Grenade(){
        super(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ENEMY);

        this.damageType = DamageInfo.DamageType.NORMAL;
        this.damage = this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = baseDamage/2;
        this.tags.add(DeadCellsTags.GRENADE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(2);
            this.upgradeMagicNumber(this.baseDamage / 2 - this.baseMagicNumber);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
        addToTop(new DamageAction(paramAbstractMonster,new DamageInfo(paramAbstractPlayer,this.damage), AbstractGameAction.AttackEffect.FIRE));
        for(AbstractMonster monster:AbstractDungeon.getMonsters().monsters){
            if (!monster.equals(paramAbstractMonster)){
                addToTop(new DamageAction(monster,new DamageInfo(paramAbstractPlayer,this.damage/2), AbstractGameAction.AttackEffect.FIRE,true));
            }
        }
    }

   /* //对其他敌人造成伤害一半的伤害,需要先计算出原伤害/2后的修正的伤害
    @Override
    public void applyPowers() {
        //applyPowers用于修正伤害值,计入易伤等...
        super.applyPowers();
        //将/2并修正后的伤害赋值给MagicNumber
        this.baseMagicNumber = this.damage/2;
    }*/

   /* @Override
    public float calculateModifiedCardDamage(AbstractPlayer player, float tmp) {
        return super.calculateModifiedCardDamage(player, tmp);
    }*/

   /* @Override
    public AbstractCard makeCopy() {
        AbstractCard card = new Grenade();
        if (this.upgraded) {
            card.upgrade();
        }
        return card;
    }*/
}
