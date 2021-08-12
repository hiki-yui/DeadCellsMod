package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import deadCellsMod.cn.infinite.stsmod.action.GainBurnsPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

import java.util.ArrayList;


//火焰制造
public class Pyrotechnics extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:Pyrotechnics";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/Pyrotechnics.png";
    DeadCellsCard father;

    public Pyrotechnics(String id,String name,int cost,String description,CardType type,CardColor color,CardRarity rarity){
        this(id,name,IMG,cost,description,type, color,rarity,CardTarget.ENEMY);
    }

    public Pyrotechnics(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 4;
        this.burnNumber = this.baseBurnNumber = 5;
        this.baseHeavyDamage = 20;
        this.ammunitionNumber = this.maxAmmunitionNumber = 5;
        this.baseBlock = 4;
    }

    public Pyrotechnics(){
        this(BASE_ID,STRINGS.NAME,0,STRINGS.DESCRIPTION,CardType.SKILL,AbstractCardEnum.DEAD_CELLS,CardRarity.COMMON);
    }

    public boolean childCanUse(AbstractPlayer p, AbstractMonster m) {
        if (this.father == null){
            return false;
        }
        return father.ammunitionNumber > 0;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1);
            this.upgradeHeavyDamage(5);
            this.upgradeBlock(2);
            this.upgradeName();
        }
    }

    @Override
    public void applyPowers() {
        heavyDamageApplyPower();
    }



    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        calculateCardHeavyDamage(mo);
    }



    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        applyPowers();
        ArrayList<AbstractCard> chose = new ArrayList<>();
        chose.add(new PyrotechnicsII(this));
        chose.add(new PyrotechnicsIII(this));
        chose.add(new PyrotechnicsIIII(this));

        addToBot(new ChooseOneAction(chose));
    }

    @Override
    public AbstractCard makeCopy() {
        Pyrotechnics card = (Pyrotechnics) super.makeCopy();
        card.father = this.father;
        return card;
    }

    //以在DeadCellsCard里修正
    @Override
    public AbstractCard makeSameInstanceOf() {
        Pyrotechnics card = (Pyrotechnics) super.makeSameInstanceOf();
        this.father = card.father;
        return card;
    }








    public static class PyrotechnicsII extends Pyrotechnics{
        public static final String BASE_ID = "deadCells:PyrotechnicsII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

        public PyrotechnicsII( Pyrotechnics father){
            super(BASE_ID,STRINGS.NAME,1,STRINGS.DESCRIPTION,CardType.ATTACK, CardColor.COLORLESS,CardRarity.SPECIAL);

            this.father = father;
            this.magicNumber = this.baseMagicNumber = 4;
            this.baseDamage = 4;
            this.exhaust = true;
            if (father.upgraded){
                this.upgrade();
            }
        }

        @Override
        public void upgrade() {
            if (!this.upgraded){
                this.upgradeMagicNumber(1);
                this.upgradeName();
            }
        }

        @Override
        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            return this.childCanUse(p, m);
        }

        public PyrotechnicsII(){
            this(new Pyrotechnics());
        }


        @Override
        public void applyPowers() {
            this.superApplyPowers();
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            int tmp = this.magicNumber;
            if (this.magicNumber > father.ammunitionNumber){
                tmp = father.ammunitionNumber;
            }
            for (int i = 0;i<tmp;i++) {
                addToBot(new DamageAction(abstractMonster,new DamageInfo(AbstractDungeon.player,this.damage, DamageInfo.DamageType.NORMAL),
                        AbstractGameAction.AttackEffect.FIRE));
            }
            father.ammunitionNumber -= tmp;
            father.flash();
        }


        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }


        @Override
        public void calculateCardDamage(AbstractMonster mo) {
            this.superCalculateCardDamage(mo);
        }
    }













    public static class PyrotechnicsIII extends Pyrotechnics{
        public static final String BASE_ID = "deadCells:PyrotechnicsIII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

        public PyrotechnicsIII(Pyrotechnics father){
            super(BASE_ID,STRINGS.NAME,2,STRINGS.DESCRIPTION,CardType.ATTACK, CardColor.COLORLESS,CardRarity.SPECIAL);
            this.father = father;
            this.baseDamage = 20;
            this.burnNumber = this.baseBurnNumber = 5;
            this.exhaust = true;
            if (father.upgraded){
                this.upgrade();
            }
        }

        public PyrotechnicsIII(){
            this(new Pyrotechnics());
        }


        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            addToBot(new GainBurnsPowerAction(abstractMonster, abstractPlayer, this.burnNumber));
            father.ammunitionNumber -= 1;
            father.flash();
        }

        @Override
        public void upgrade() {
            if (!this.upgraded){
                this.upgradeDamage(5);
                this.upgradeName();
            }
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }

        @Override
        public void applyPowers() {
            this.superApplyPowers();
        }

        @Override
        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            return this.childCanUse(p, m);
        }

        @Override
        public void calculateCardDamage(AbstractMonster mo) {
            this.superCalculateCardDamage(mo);
        }

    }
















    public static class PyrotechnicsIIII extends Pyrotechnics{
        public static final String BASE_ID = "deadCells:PyrotechnicsIIII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

        public PyrotechnicsIIII(Pyrotechnics father){
            super(BASE_ID,STRINGS.NAME,0,STRINGS.DESCRIPTION,CardType.SKILL, CardColor.COLORLESS,CardRarity.SPECIAL);


            this.father = father;
            this.baseBlock = 4;
            this.magicNumber = this.baseMagicNumber = father.maxAmmunitionNumber;
            this.exhaust = true;
            this.target = CardTarget.SELF;
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

        public PyrotechnicsIIII(){
            this(new Pyrotechnics());
        }

        @Override
        public void applyPowers() {
            this.superApplyPowers();
        }

        @Override
        public void calculateCardDamage(AbstractMonster mo) {
            this.superCalculateCardDamage(mo);
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
            father.fillInAmmunition(this.magicNumber);
            father.flash();
        }
    }
}
