package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import deadCellsMod.cn.infinite.stsmod.action.AddCardToHandAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

import java.util.ArrayList;

//牙签
public class Toothpick extends DeadCellsCard{

    public static final  String BASE_ID = "deadCells:Toothpick";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/Toothpick.png";

    public Toothpick(){
        this(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ENEMY);

        this.heavyDamage = this.baseHeavyDamage = 34;
        this.baseDamage = 9;
        this.ammunitionNumber = this.maxAmmunitionNumber = 1;
        this.baseBlock = 4;

    }

    public Toothpick(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            this.upgradeDamage(3);
            this.upgradeHeavyDamage(8);
            this.upgradeBlock(2);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ToothpickII(this));
        list.add(new ToothpickIII(this));
        list.add(new ToothpickIIII(this));

        addToBot(new ChooseOneAction(list));
    }

    @Override
    public void applyPowers() {
        super.heavyDamageApplyPower();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardHeavyDamage(mo);
    }














    public static class ToothpickII extends Toothpick{

        public static final String BASE_ID = "deadCells:ToothpickII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

        public ToothpickII(){
            this(new Toothpick());
        }

        public ToothpickII(DeadCellsCard father){
            super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ENEMY);

            this.father = father;
            this.baseDamage = 9;
            this.exhaust = true;
            if (father.upgraded){
                this.upgrade();
            }
        }

        @Override
        public void upgrade() {
            if (!this.upgraded){
                this.upgradeDamage(3);
                this.upgradeName();
            }
        }

        @Override
        public void applyPowers() {
            super.superApplyPowers();
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }

        @Override
        public void calculateCardDamage(AbstractMonster mo) {
            super.superCalculateCardDamage(mo);
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }

        @Override
        public boolean canUpgrade() {
            return false;
        }
    }














    public static class ToothpickIII extends Toothpick{

        public static final String BASE_ID = "deadCells:ToothpickIII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);


        public ToothpickIII(){
            this(new Toothpick());
        }

        public ToothpickIII(DeadCellsCard father){
            super(BASE_ID,STRINGS.NAME,"img/card/ToothpickIII.png",2,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ENEMY);

            this.father = father;
            this.damage = this.baseDamage =  34;
            this.ammunitionNumber = 1;
            this.exhaust = true;
            if (father.upgraded){
                this.upgrade();
            }
        }

        @Override
        public void upgrade() {
            if (!this.upgraded){
                this.upgradeDamage(8);
                this.upgradeName();
            }
        }

        @Override
        public void applyPowers() {
            super.superApplyPowers();
        }

        @Override
        public void calculateCardDamage(AbstractMonster mo) {
            super.superCalculateCardDamage(mo);
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            if (this.father!=null && this.ammunitionNumber > this.father.ammunitionNumber){
                AbstractDungeon.effectList.add(new ThoughtBubble(abstractPlayer.dialogX, abstractPlayer.dialogY, 3.0F, " 牙签已损坏", true));
            }
            if (abstractMonster != null) {
                addToBot(new VFXAction(new WeightyImpactEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
           }
            addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            father.ammunitionNumber -= this.ammunitionNumber;
            father.flash();
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }

        @Override
        public boolean canUpgrade() {
            return false;
        }

        @Override
        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            if (this.father!=null && this.ammunitionNumber > this.father.ammunitionNumber){
                /*AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, " #b@牙签已损坏", true));*/
                return false;
            }
            return super.canUse(p, m);
        }
    }
























    public static class ToothpickIIII extends Toothpick{

        public static final String BASE_ID = "deadCells:ToothpickIIII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);


        public ToothpickIIII(){
            this(new Toothpick());
        }

        public ToothpickIIII(DeadCellsCard father){
            super(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.SKILL,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.SELF);

            this.father = father;
            this.baseBlock = 4;
            this.ammunitionNumber = father.maxAmmunitionNumber;
            this.exhaust = true;
            if (father.upgraded){
                this.upgrade();
            }
        }

        @Override
        public void upgrade() {
            if (!this.upgraded){
                this.upgradeBlock(2);
                this.upgradeName();
            }
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
            this.father.fillInAmmunition(this.ammunitionNumber);
            this.father.flash();
        }


        @Override
        public void applyPowers() {

            super.superApplyPowers();
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }

        @Override
        public void calculateCardDamage(AbstractMonster mo) {
            super.superCalculateCardDamage(mo);
        }

        @Override
        public boolean canUpgrade() {
            return false;
        }
    }
}
