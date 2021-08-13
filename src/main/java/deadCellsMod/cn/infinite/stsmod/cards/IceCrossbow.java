package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import deadCellsMod.cn.infinite.stsmod.action.AddCardToHandAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.FrostbitePower;

import java.util.ArrayList;

//冰霜弩箭
public class IceCrossbow extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:IceCrossbow";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/IceCrossbow.png";


    public IceCrossbow(){
        this(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ENEMY);
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 3;
        this.heavyDamage = this.baseHeavyDamage= 12;
        this.maxAmmunitionNumber = 6;
        this.ammunitionNumber = 6;
        this.baseBlock = 4;
    }

    public IceCrossbow(String id,String name,String img,int cost,String description,CardType type,CardColor color,CardRarity rarity,CardTarget target){
        super(id,name,img,cost,description,type, color,rarity,target);
    }
    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(3);
            this.upgradeMagicNumber(1);
            this.upgradeHeavyDamage(4);
            this.upgradeBlock(2);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        applyPowers();
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new IceCrossbowII(this));
        list.add(new IceCrossbowIII(this));
        list.add(new IceCrossbowIIII(this));

        addToBot(new ChooseOneAction(list));
    }

    boolean childCanUse(){
        if (father!=null && father.ammunitionNumber<this.ammunitionNumber){
            AbstractPlayer p = AbstractDungeon.player;
            /*AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, " #b@弓箭不足", true));*/
            return false;
        }
        return true;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardHeavyDamage(mo);
    }

    @Override
    public void applyPowers() {
        this.heavyDamageApplyPower();
    }













   public static class IceCrossbowII extends IceCrossbow{

        public static final String BASE_ID = "deadCells:IceCrossbowII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

        public IceCrossbowII(DeadCellsCard father){
            super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ENEMY);


            this.father = father;
            this.baseDamage = 5;
            this.magicNumber = this.baseMagicNumber = 3;
            this.ammunitionNumber = 1;
            this.exhaust = true;
            if (father.upgraded){
                this.upgrade();
            }
        }

       @Override
       public void upgrade() {
           if (!this.upgraded){
               this.upgradeDamage(3);
               this.upgradeMagicNumber(1);
               this.upgradeName();
           }
       }

       public IceCrossbowII(){
            this(new IceCrossbow());
        }

        @Override
        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            return childCanUse();
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            addToBot(new ApplyPowerAction(abstractMonster,abstractPlayer,new FrostbitePower(abstractMonster,abstractPlayer,this.baseMagicNumber),this.baseMagicNumber));
            addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            father.ammunitionNumber -= this.ammunitionNumber;
            father.flash();
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }

       @Override
       public void applyPowers() {
           super.superApplyPowers();
       }
   }














    public static class IceCrossbowIII extends IceCrossbow{

        public static final String BASE_ID = "deadCells:IceCrossbowIII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
        private static final String IMG = "img/card/IceCrossbowIII.png";

        public IceCrossbowIII(DeadCellsCard father){
            super(BASE_ID,STRINGS.NAME,IMG,2,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ENEMY);

            this.father = father;
            this.magicNumber = this.baseMagicNumber = 12;
            this.baseDamage = 12;
            this.exhaust = true;
            this.ammunitionNumber = 2;
            if (father.upgraded){
                this.upgrade();
            }
        }

        @Override
        public void upgrade() {
            if (!this.upgraded){
                this.upgradeDamage(4);
                this.upgradeMagicNumber(4);
                this.upgradeName();
            }
        }

        public IceCrossbowIII(){
            this(new IceCrossbow());
        }


        @Override
        public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
            if (mo !=null) {
                if (mo.hasPower("deadCells:FrostbitePower")) {
                    tmp += (int)(mo.getPower("deadCells:FrostbitePower").amount/2) * this.baseMagicNumber;
                }
            }
            return super.calculateModifiedCardDamage(player, mo, tmp);
        }

        @Override
        public void applyPowers() {
            super.superApplyPowers();
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            father.ammunitionNumber -= this.ammunitionNumber;
            father.flash();
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }

        @Override
        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            return this.childCanUse();
        }
    }

















    public static class IceCrossbowIIII extends IceCrossbow{

        public static final String BASE_ID = "deadCells:IceCrossbowIIII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

        public IceCrossbowIIII(DeadCellsCard father){
            super(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.SELF);

            this.father = father;
            this.ammunitionNumber = 5;
            this.baseBlock = 4;
            this.exhaust = true;
            if (father.upgraded){
                this.upgrade();
            }
        }

        @Override
        public void upgrade() {
            this.upgradeBlock(2);
            this.upgradeName();
        }

        public IceCrossbowIIII(){
            this(new IceCrossbow());
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
            this.father.fillInAmmunition(this.ammunitionNumber);
            this.father.flash();
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }
    }


}
