package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
import deadCellsMod.cn.infinite.stsmod.action.AddCardToHandAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

import java.util.ArrayList;

public class FerrymanSLantern extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:FerrymanSLantern";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public FerrymanSLantern(){
        this(BASE_ID,STRINGS.NAME,"img/card/FerrymanSLantern.png",0,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ENEMY);

        this.baseDamage = 8;
        this.heavyDamage = this.baseHeavyDamage= 20;
        this.changeNum = this.baseChangeNum = 1;
        this.ammunitionNumber = 0;
    }

    public FerrymanSLantern(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(3);
            this.upgradeHeavyDamage(5);
            this.upgradeName();
        }
    }

    @Override
    public void applyPowers() {
        super.heavyDamageApplyPower();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardHeavyDamage(mo);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new FerrymanSLanternII(this));
        list.add(new FerrymanSLanternIII(this));

        addToBot(new ChooseOneAction(list));
    }













    public  static class FerrymanSLanternII extends FerrymanSLantern{
        public static final String BASE_ID = "deadCells:FerrymanSLanternII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);


        public FerrymanSLanternII(){
            this(new FerrymanSLantern());
        }

        public FerrymanSLanternII(DeadCellsCard father){
            super(BASE_ID,STRINGS.NAME,"img/card/FerrymanSLantern.png",1,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ENEMY);

            this.father = father;
            this.exhaust = true;
            this.ammunitionNumber = father.changeNum;
            this.baseDamage = 8;
            if (father.upgraded){
                this.upgrade();
            }
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            father.ammunitionNumber += this.ammunitionNumber;
            father.flash();
        }

        @Override
        public void upgrade() {
            if(!this.upgraded){
                this.upgradeDamage(3);
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
        public boolean canUpgrade() {
            return false;
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }
    }

















    public  static class FerrymanSLanternIII extends FerrymanSLantern{
        public static final String BASE_ID = "deadCells:FerrymanSLanternIII";
        private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);


        public FerrymanSLanternIII(){
            this(new FerrymanSLantern());
        }

        public FerrymanSLanternIII(DeadCellsCard father){
            super(BASE_ID,STRINGS.NAME,"img/card/FerrymanSLanternIII.png",2,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ENEMY);
            this.father = father;

            this.baseDamage = 20;
            this.exhaust = true;

            if (father.upgraded){
                this.upgrade();
            }
        }

        @Override
        public void upgrade() {
            if (!this.upgraded){
                this.upgradeDamage(5);
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
            AbstractDungeon.actionManager.addToBottom(
                    new VFXAction(new BloodShotEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, abstractMonster.hb.cX,
                            abstractMonster.hb.cY,father.ammunitionNumber), 0.25F));
            for (int i = 0;i<father.ammunitionNumber;i++) {
                addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
            father.ammunitionNumber -= father.ammunitionNumber;
            father.flash();
        }

        @Override
        public void onChoseThisOption() {
            addToBot(new AddCardToHandAction(this.makeSameInstanceOf()));
        }

        @Override
        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            if (this.father!=null){
                if (this.father.ammunitionNumber <= 0){
                    return false;
                }
            }
            return super.canUse(p, m);
        }
    }
}
